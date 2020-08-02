package com.example.infs3605project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CyberSimTwo extends AppCompatActivity {

    Button rightBtn, wrongBtn;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_two);

        description = findViewById(R.id.QuestionTwoDescription);
        rightBtn = findViewById(R.id.rightBtnTwo);
        wrongBtn = findViewById(R.id.wrongBtnTwo);

        openDialog("Your senior manager has delivered a proposal form containing sensitive client information for you to download in your email.You open your email and you realise you have to sort through a lot of emails first. ");

        description.setText("Looking at the first one, you decide to either open or trash it.");

        rightBtn.setText("Trash");
        wrongBtn.setText("Open");

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("Good work! You have successfully avoided a phishing scam. ", "#6EAE94");
            }
        });

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("You enter your personal details and realise that you have $2000 withdrawn from your bank account the next day. The sender's name is not spelt correctly. the sender's address is not the same address as the official Netflix's one. Receiver address is not your address. Netflix will never ask for personal information through email.", "#BF6F78");
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
                if(colour.equals("#6EAE94")) { //right one

                    startActivity(new Intent(getApplicationContext(), CyberSimThree.class));

                } else {
                    startActivity(new Intent(getApplicationContext(), CyberSimThree.class));
                }
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