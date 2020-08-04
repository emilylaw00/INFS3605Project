package com.example.infs3605project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CyberSimTwo extends AppCompatActivity {

    Button rightBtn, wrongBtn;
    TextView description, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_two);

        description = findViewById(R.id.QuestionTwoDescription);
        rightBtn = findViewById(R.id.rightBtnTwo);
        wrongBtn = findViewById(R.id.wrongBtnTwo);
        score = findViewById(R.id.twoScoreLbl);

        //retrieve the score from the intent from CyberSimOne

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");
        Log.d("CSTwo:", Integer.toString(scoreCount));
        score.setText("$" + Integer.toString(scoreCount));
        //

        openDialog("Your senior manager has delivered a proposal form containing sensitive " +
                "client information for you to download in your email. You open your email and you " +
                "realise you have to sort through a lot of emails first.");

        description.setText("Looking at the first one, you decide to either open or trash it. \n \n" +
                "Click on either the \"Retry Payment\" OR bin button.");

//        rightBtn.setText("Trash");
//        wrongBtn.setText("Open");

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("Good work! You have successfully avoided a phishing scam.", "#6EAE94", scoreCount+2000);
            }
        });

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("You enter your personal details and realise that you have " +
                        "$2000 withdrawn from your bank account the next day. The sender's name is not" +
                        " spelt correctly. the sender's address is not the same address as the official " +
                        "Netflix's one. Receiver address is not your address. Netflix will never ask for" +
                        " personal information through email.", "#BF6F78",scoreCount-2000);
            }
        });

    }

    public void openFeedbackDialog(String desc, final String colour, final int score){
        //method to call the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_feedback, null);

        //initialise the elements
        TextView descTxt = view.findViewById(R.id.dialogFeedbackDesc);
        Button okBtn = view.findViewById(R.id.DialogFeedbackBtn);
        ConstraintLayout dialogBg = view.findViewById(R.id.feedbackDialogBg);

        descTxt.setText(desc); //set the description
        dialogBg.setBackgroundColor(Color.parseColor(colour));

        //create the dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CyberSimTwo.this, CyberSimThree.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        alertDialog.show();

    }

    public void openDialog(String desc){
        //method to call the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_feedback, null);

        //initialise the elements
        TextView dialogLbl = view.findViewById(R.id.dialogFeedbackLbl);
        TextView descTxt = view.findViewById(R.id.dialogFeedbackDesc);
        Button okBtn = view.findViewById(R.id.DialogFeedbackBtn);

        descTxt.setText(desc); //set the description
        dialogLbl.setText("NEXT...");

        //create the dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    alertDialog.cancel();


                }

        });

        alertDialog.show();

    }



}