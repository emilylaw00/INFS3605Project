package com.example.infs3605project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private TextView mScore, mFeedback;
    private Button finishBtn;
    private ConstraintLayout scoreBg;
    private ImageView picture;

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
        picture = findViewById(R.id.feedbackicon);

        //retrieve the stored score

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid(); //getting user ID of currently registered user
        Log.d("Purity Score", "USERID: " + userId);



        DocumentReference drr = fStore.collection("Purity Score").document(userId);
        drr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //update the data
                String score = documentSnapshot.getString("Score");
                mScore.setText(score);
                int scoreNo = Integer.parseInt(score);
                if(scoreNo <= 5) {
                    mFeedback.setText("You are extremely vulnerable to cyber threats. You have little-to-no Cybersecurity awareness and your actions can " +
                            "seriously harm the people around you or the organisation you are applying for. ");
                    picture.setImageResource(R.drawable.red);
                } else if (scoreNo < 10 && scoreNo > 5){
                    mFeedback.setText("You are highly vulnerable to cyber threats and your Cybersecurity awareness is low. " +
                            "If you do not focus on fixing these bad habits, it will negatively affect your personal life. ");
                    picture.setImageResource(R.drawable.orange1);
                } else if (scoreNo >= 10 && scoreNo < 15){
                    mFeedback.setText("You are at medium risk to cyber threats and your Cybersecurity awareness is average. " +
                            "Try and remind yourself to fix some of these bad habits before these threats escalate.");
                    picture.setImageResource(R.drawable.yellow);
                } else if (scoreNo >= 15){
                    mFeedback.setText("You are at low risk to cyber threats and your Cybersecurity awareness is high. " +
                            "Continue reading on industry news to look out for potential emerging threats.");
                    picture.setImageResource(R.drawable.green);
                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}