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

public class CyberSimFive extends AppCompatActivity {

    Button trashBtn, openBtn;
    TextView description, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_five);

        description = findViewById(R.id.QuestionFiveDescription);
        trashBtn = findViewById(R.id.rightBtnFive);
        openBtn = findViewById(R.id.wrongBtnFive);
        score = findViewById(R.id.fiveScoreLbl);

        description.setText("What do you do?");

        //retrieve the score from the intent from CyberSimOne

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");
        score.setText(Integer.toString(scoreCount));
        Log.d("CSThree:", Integer.toString(scoreCount));

        //

        openDialog("You have successfully created your account on TechStar Messenger and your Large Latte finally arrives.");

        trashBtn.setText("Quickly leave the seat to get your coffee! It’ll be less than a minute so it doesn’t matter too much if I leave my belongings unattended.");
        openBtn.setText("Log out of TechStar Messenger, disconnect from my VPN and WiFi, and take my belongings with me before enjoying my cup of coffee. ");

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("By securing your belongings, you save the risk of having your " +
                        "belongings containing sensitive information within the hands of a stranger. +$5000", "#6EAE94");
            }
        });

        trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("You turn around and see that someone has stolen your " +
                        "laptop within the few seconds you left your seat. With your laptop costing " +
                        "$1500 and sensitive company information worth over $3500, you have just lost " +
                        "$5000 in total. ", "#BF6F78");
            }
        });

    }

    public void openFeedbackDialog(String desc, final String colour){
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
                finish();
                startActivity(new Intent(getApplicationContext(), CyberSimOutro.class));
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