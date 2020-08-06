package com.example.infs3605project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;

public class VoucherUnlockedActivity extends AppCompatActivity {

    List<Vouchers> vList = new ArrayList<>();
    RecyclerView mRecyclerView;
    VoucherUnlockedAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore fStore;
    TextView coinBalance;
    Users user;
    Vouchers voucher;
    Button switchBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocked);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        backBtn = findViewById(R.id.backBtnUnlocked);
        coinBalance = findViewById(R.id.unlockedCoinBalance);
        mRecyclerView = findViewById(R.id.rcUnlocked);
        switchBtn = findViewById(R.id.unlockedSwitch);

        userId = fAuth.getCurrentUser().getUid();
        Log.d("PROFILE", "USERID: " + userId);

        DocumentReference docRef = fStore.collection("Users").document(userId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                Long money = documentSnapshot.getLong("coin balance");
                int cost = money.intValue();

                coinBalance.setText(Integer.toString(cost));
            }
        });

        createList();

        buildRecyclerView();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoucherUnlockedActivity.this, MainActivity.class));
            }
        });

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoucherUnlockedActivity.this, VoucherLockedActivity.class));
            }
        });

    }

    public void createList() {

        //Create a reference to the cities collection
        CollectionReference v = fStore.collection("Vouchers");
        // Create a query against the collection
        Query query = v.whereEqualTo("status", true).whereEqualTo(FieldPath.documentId(), userId);
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

                        vList.add(new Vouchers(vTitle, cost, status)); //user data saved
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
        mLayoutManager = new LinearLayoutManager(VoucherUnlockedActivity.this);
        mAdapter = new VoucherUnlockedAdapter(vList, VoucherUnlockedActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("MainActivity", "RECYCLER");
        mAdapter.notifyDataSetChanged();

    }

}