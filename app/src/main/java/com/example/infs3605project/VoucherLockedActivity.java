package com.example.infs3605project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;


public class VoucherLockedActivity extends AppCompatActivity {

    TextView coinBalance;
    List<Vouchers> voucherList = new ArrayList<>();
    RecyclerView mRecyclerView;
    VoucherLockedAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Button switchBtn, backBtn;
    String userId;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Users user;
    Vouchers voucher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        //initialise
        coinBalance = findViewById(R.id.lockedCoinBalance);
        mRecyclerView = findViewById(R.id.rcLocked);
        switchBtn = findViewById(R.id.lockedSwitch);
        backBtn = findViewById(R.id.backBtnLocked);


        //collect score from the DB

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        Log.d("PROFILE", "USERID: " + userId);

        Button backBtn = (Button) findViewById(R.id.backBtnLocked);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        createList();

        buildRecyclerView();

        //display the coin balance


        DocumentReference drrr = fStore.collection("Users").document(userId);
        drrr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                int money = documentSnapshot.getLong("coin balance").intValue();
                coinBalance.setText(Integer.toString(money));
            }
        });

        //item click handler
        mAdapter.setOnItemClickListener(new VoucherLockedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                final DocumentReference docReference = fStore.collection("Vouchers").document(userId);

                docReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> userMap = document.getData();
                                String vTitle = userMap.get("title").toString();
                                String vStatus = userMap.get("status").toString();
                                Long vCost = (Long) userMap.get("cost");
                                final int cost = vCost.intValue();
                                Boolean status = parseBoolean(vStatus);

                                voucher = new Vouchers(vTitle, cost, status); //user data saved

                                //users
                                final DocumentReference dReference = fStore.collection("Users").document(userId);
                                dReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
                                                checkCost(coinBalance, cost);

                                            } else {
                                                Log.d("Profile frag", "No such document");
                                            }
                                        }
                                    }
                                });
                            } else {
                                Log.d("Profile frag", "No such document");
                            }
                        }
                    }

                });
            }
        });

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoucherLockedActivity.this, VoucherUnlockedActivity.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoucherLockedActivity.this, MainActivity.class));
            }
        });

    }

    public void createList() {

        //Create a reference to the cities collection
        CollectionReference v = fStore.collection("Vouchers");
        // Create a query against the collection
        Query query = v.whereEqualTo("status", false).whereEqualTo(FieldPath.documentId(), userId);
        // retrieve  query results asynchronously using query.get()

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document: task.getResult()){
                        Map<String, Object> userMap = document.getData();
                        String vTitle = userMap.get("title").toString();
                        String vStatus = userMap.get("status").toString();
                        Long vCost = (Long) userMap.get("cost");
                        int cost = vCost.intValue();
                        Boolean status = parseBoolean(vStatus);

                        voucherList.add(new Vouchers(vTitle, cost, status)); //user data saved
                    }

                    mAdapter.notifyDataSetChanged();

                } else {
                    Log.d("Voucher ", "ERROR with document");

                }

            }
        });

    }

    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(VoucherLockedActivity.this);
        mAdapter = new VoucherLockedAdapter(voucherList, VoucherLockedActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("LockedFrag", "RECYCLER is building");
        mAdapter.notifyDataSetChanged();


    }


    //purchase confirmation dialog
    public void openDialog(final int itemPosition){

        //the confirmation dialog
        //this will pop up when the user selects an item in the recycler view
        //user must have enough coins otherwise the toast will appear instead
        final int position = itemPosition;
        LayoutInflater inflater = LayoutInflater.from(VoucherLockedActivity.this);
        View view = inflater.inflate(R.layout.dialog_voucher, null);

        Button noBtn = view.findViewById(R.id.voucherCancelBtn);
        Button okBtn = view.findViewById(R.id.voucherConfirmBtn);

        final AlertDialog alertDialog = new AlertDialog.Builder(VoucherLockedActivity.this)
                .setView(view)
                .create();

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

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

                                updateValue(coinBalance, docRef);

                            } else {
                                Log.d("Profile frag", "No such document");
                            }
                        }
                    }
                });

                //actively removes the item from the list
                //ensures that it looks like a fluid removal
                //we want it to occur in real time without needing refresh button
                voucherList.remove(position);
                mRecyclerView.removeViewAt(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, voucherList.size());
            }

        });

        alertDialog.show();

    }

    public void updateValue(final int newBalance, final DocumentReference docRef) {

        final DocumentReference dR = fStore.collection("Vouchers").document(userId);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> userMap = document.getData();
                        String vTitle = userMap.get("title").toString();
                        String vStatus = userMap.get("status").toString();
                        Long vCost = (Long) userMap.get("cost");
                        int cost = vCost.intValue();
                        Boolean status = parseBoolean(vStatus);

                        voucher = new Vouchers(vTitle, cost, status); //user data saved

                        updateCoinBalance(newBalance-cost, docRef);

                    } else {
                        Log.d("Profile frag", "No such document");
                    }

                }

            }

        });

    }

    public void updateCoinBalance(final int newBalance, final DocumentReference docRef) {

        final DocumentReference dR = fStore.collection("Vouchers").document(userId);

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

    public void checkCost(final int currentScore, final int voucherCost) {

        if(currentScore < voucherCost) {
            Toast.makeText(this, "Sorry, you don't have enough coins!", Toast.LENGTH_SHORT ).show();
        } else {
            openDialog(0);
        }

    }










}