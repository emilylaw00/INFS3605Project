package com.example.infs3605project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.Objects;
import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {

    TextView mUserName, mUserScore, textV;
    ImageView mProfilePic;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mUserName = v.findViewById(R.id.txtFirstName);
        mUserScore = v.findViewById(R.id.txtScore);
        mProfilePic = v.findViewById(R.id.profilePic);
        //textV = v.findViewById(R.id.textView);

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
                mUserName.setText(documentSnapshot.getString("firstName"));
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


        return v;
    }
}
