package com.example.infs3605project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {

    TextView mUserName, mUserScore, mMoney;
    ImageView mProfilePic;
    Button mLogoutBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button getDailyAwardBtn;
    public static final String SHARED_PREF = "shared_prefs";
    Users user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mUserName = v.findViewById(R.id.txtFirstName);
        mUserScore = v.findViewById(R.id.txtScore);
        mLogoutBtn = v.findViewById(R.id.logoutBtn);
        mMoney = v.findViewById(R.id.txtMoney);
        getDailyAwardBtn = v.findViewById(R.id.dailyRewardBtn);


        //preset the daily award btn
        getDailyAwardBtn.setVisibility(View.GONE);

        //method that actions the daily award function


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        Log.d("PROFILE", "USERID: " + userId);

        //assign retrieved data using getProperties

        DocumentReference dr = fStore.collection("Users").document(userId);
        dr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                mUserName.setText(documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName"));
            }
        });

        DocumentReference drr = fStore.collection("Purity Score").document(userId);
        drr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                mUserScore.setText(documentSnapshot.getString("Score"));
            }
        });

        DocumentReference drrr = fStore.collection("Users").document(userId);
        drrr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                int money = documentSnapshot.getLong("coin balance").intValue();
                mMoney.setText(Integer.toString(money));
            }
        });




        getDailyReward();

        //logout button
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),Login.class));
                getActivity().finish();
            }
        });


        return v;
    }

    public void getDailyReward() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        //getting today's date
        final String todayString = year + " " + month + " " + day;

        //return the stored data from SharedPreferences
        final SharedPreferences preferences = getActivity().getSharedPreferences("DAILYREWARDSPREFS", 0);
        //check if todayString already exists, returns false if today does not exist.
        boolean today = preferences.getBoolean(todayString, false);
        /* If the today variable is false, then we save the value to SharedPreferences.
         * We award the reward points to the coin balance.
         * */
        if (!today) {
            //make the button visible
            getDailyAwardBtn.setVisibility(View.VISIBLE);
            getDailyAwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //save to SharedPreferences
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(todayString, true);
                    editor.apply();

                    //calls the award alert dialog
                    showDialog();

                    final DocumentReference docRef = fStore.collection("Users").document(userId);

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Map<String, Object> userMap = document.getData();
                                    String fname = userMap.get("firstName").toString();
                                    String lname = userMap.get("lastName").toString();
                                    String email = userMap.get("email").toString();
                                    String birthyear = userMap.get("birthYear").toString();
                                    String degree = userMap.get("degree").toString();
                                    String findOutInfo = userMap.get("how did you find out about CyberGrad?").toString();
                                    Long coins = (Long) userMap.get("coin balance");
                                    int coinBalance = coins.intValue();

                                    user = new Users(email, fname, lname, birthyear, degree, coinBalance, findOutInfo); //user data saved

                                    updateValue(coinBalance + 250, docRef);

                                } else {
                                    Log.d("Profile frag", "No such document");
                                }

                            }

                        }

                    });

                }
            });
        }
        }

        //dailyReward dialog
        public void showDialog() {
            //inflate with the relevant XML
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.dialog_dailyreward, null);

            //create and link the btn
            Button okBtn = view.findViewById(R.id.okBtnDR);

            //create the alert dialog
            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .create();

            //defines the actions when clicked
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss(); //makes the dialog message disappear.

                }
            });

            alertDialog.show();

        }


    public void updateValue(final int newBalance, final DocumentReference docRef) {

    fStore.runTransaction(new Transaction.Function<Void>() {
        @Nullable
        @Override
        public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {

            transaction.update(docRef, "coin balance", newBalance);
            Log.d("profile", "transaction SUCCESSFULL");
            return null;
        }
        });

    }

}
