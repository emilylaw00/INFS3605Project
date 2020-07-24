package com.example.infs3605project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PurityScore extends AppCompatActivity {

    private TextView mScore, mTotal, mFeedback;
    private Button finishBtn;
    private ConstraintLayout scoreBg;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_score);

        //This sets the GUI elements
        mScore = findViewById(R.id.scoreTxt);
        mFeedback = findViewById(R.id.feedbackTxt);
        finishBtn = findViewById(R.id.finishBtn);
        scoreBg = findViewById(R.id.scoreBackground);

        //retrieve the stored score

        DocumentReference drr = fStore.collection("Purity Score").document(userId);
        drr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                mScore.setText(documentSnapshot.getString("Score"));
            }
        });

        //total.setText("Out of "+ String.valueOf(getIntent().getIntExtra("total",0)));





        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}