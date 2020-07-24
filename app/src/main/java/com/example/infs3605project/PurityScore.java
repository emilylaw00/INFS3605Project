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
                    mFeedback.setText("That is soooooo bad");
                } else if (scoreNo < 10 && scoreNo > 5){
                    mFeedback.setText("That is not too bad");

                } else if (scoreNo >= 10 && scoreNo < 15) {
                    mFeedback.setText("It is a decent score but there is definitely lots of area for improvement");
                } else if (scoreNo >= 15) {
                    mFeedback.setText("You are absolutely so very safe! Well done");
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