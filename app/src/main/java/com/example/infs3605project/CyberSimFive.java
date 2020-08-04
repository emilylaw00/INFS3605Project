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
import android.widget.ImageView;
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
        score.setText("$" + Integer.toString(scoreCount));
        Log.d("CSFive:", Integer.toString(scoreCount));

        //

        openDialog("You have successfully created your account on TechStar Messenger and your Large Latte finally arrives.", R.drawable.coffee);

        trashBtn.setText("Quickly leave the seat to get your coffee! It’ll be less than a minute so it doesn’t matter too much if I leave my belongings unattended.");
        openBtn.setText("Log out of TechStar Messenger, disconnect from my VPN and WiFi, and take my belongings with me before enjoying my cup of coffee. ");

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("By securing your belongings, you save the risk of having your " +
                        "belongings containing sensitive information within the hands of a stranger.", "+$5000", R.drawable.tickker, "#49B342", scoreCount+5000);
            }
        });

        trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("You turn around and see that someone has stolen your " +
                        "laptop within the few seconds you left your seat. \n \n With your laptop costing " +
                        "$1500 and sensitive company information worth over $3500, you have just lost " +
                        "$5000 in total. ", "-$5000", R.drawable.close, "#D54335", scoreCount-5000);
            }
        });

    }

    public void openFeedbackDialog(String desc, String increment, int pictype, final String colour, final int score){
        //method to call the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_feedback, null);

        //initialise the elements
        TextView descTxt = view.findViewById(R.id.dialogFeedbackDesc);
        Button okBtn = view.findViewById(R.id.DialogFeedbackBtn);
        TextView points = view.findViewById(R.id.increment);
        ConstraintLayout dialogBg = view.findViewById(R.id.feedbackDialogBg);
        ImageView pic = view.findViewById(R.id.icon);

        descTxt.setText(desc); //set the description
        dialogBg.setBackgroundColor(Color.parseColor(colour));
        pic.setImageResource(pictype);
        points.setText(increment);

        //create the dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CyberSimFive.this, CyberSimOutro.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        alertDialog.show();

    }

    public void openDialog(String desc, int pictype){
        //method to call the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_next, null);

        //initialise the elements
        TextView dialogLbl = view.findViewById(R.id.dialogFeedbackLbl);
        TextView descTxt = view.findViewById(R.id.dialogFeedbackDesc);
        Button okBtn = view.findViewById(R.id.DialogFeedbackBtn);
        ImageView pic = view.findViewById(R.id.icon);

        descTxt.setText(desc); //set the description
        dialogLbl.setText("NEXT...");
        pic.setImageResource(pictype);

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