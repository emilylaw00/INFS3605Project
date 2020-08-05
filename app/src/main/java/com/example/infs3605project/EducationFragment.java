package com.example.infs3605project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EducationFragment extends Fragment {

    Button purityTestBtn;
    Button cyberSim;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    TextView txtMoney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_education, container, false);

        purityTestBtn = v.findViewById(R.id.purityTestBtn);
        cyberSim = v.findViewById(R.id.simBtn);
        txtMoney = v.findViewById(R.id.txtGameMoney);


        purityTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PurityTest.class));
            }
        });

        cyberSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CyberSimIntro.class));
            }
        });

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        Log.d("PROFILE", "USERID: " + userId);

        //assign retrieved data using getProperties

        DocumentReference docRef = fStore.collection("Users").document(userId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                int money = documentSnapshot.getLong("coin balance").intValue();
                txtMoney.setText(Integer.toString(money));
            }
        });



        return v;



    }
}
