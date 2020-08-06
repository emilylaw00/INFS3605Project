package com.example.infs3605project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.Map;

public class CyberSimOutro extends AppCompatActivity {

    TextView title, scoreLbl;

    Button homeBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_outro);

        title = findViewById(R.id.outroLbl);
        scoreLbl = findViewById(R.id.simsScore);
        homeBtn = findViewById(R.id.outroHomeBtn);

        //retrieve the score from the intent from CyberSimOne

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        Log.d("PROFILE", "USERID: " + userId);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");
        Log.d("CSOutro:", Integer.toString(scoreCount));


        if(scoreCount < 0) {
            title.setText("Better luck next time");
            scoreLbl.setText("0");
        } else {
            title.setText("Well done!");
            scoreLbl.setText(Integer.toString(scoreCount));
            //update the DB

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

                            updateValue(coinBalance + scoreCount, docRef);

                        } else {
                            Log.d("Profile frag", "No such document");
                        }

                    }

                }

            });

        }





        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CyberSimOutro.this, MainActivity.class));
            }
        });



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