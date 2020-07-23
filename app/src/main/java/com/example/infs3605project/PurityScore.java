package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PurityScore extends AppCompatActivity {

    private TextView mScore, mTotal, mFeedback;
    private Button finishBtn;
    private ConstraintLayout scoreBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_score);

        //This sets the GUI elements
        mScore = findViewById(R.id.scoreTxt);
        mFeedback = findViewById(R.id.feedbackTxt);
        finishBtn = findViewById(R.id.finishBtn);
        scoreBg = findViewById(R.id.scoreBackground);

        //received the intent and unwrap it to reveal the score

        int score = getIntent().getIntExtra("score",0);
        mScore.setText(String.valueOf(score));
        //total.setText("Out of "+ String.valueOf(getIntent().getIntExtra("total",0)));




    }
}