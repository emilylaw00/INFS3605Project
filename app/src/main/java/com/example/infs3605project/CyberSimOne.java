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

public class CyberSimOne extends AppCompatActivity {

    Button rightBtn, wrongBtn;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_one);

        description = findViewById(R.id.QuestionOneDescription);
        rightBtn = findViewById(R.id.rightBtn);
        wrongBtn = findViewById(R.id.wrongBtn);


        description.setText("You open your work laptop and sign in. What do you do next?");
        rightBtn.setText("Take the time to connect to my VPN. It is likely to take a while to load. You get stressed about your large workload.");
        wrongBtn.setText("Instantly search for an internet connection and connect to it. Great! I can begin my work now.");

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openDialog("By connecting to a VPN that TechStar trusts, I have ensured that my information is secure and encrypted, I can begin my work.", "#6EAE94");
            }
        });

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openDialog("By connecting to an insecure Wi-Fi, hackers are able to tamper with the connection and steal any confidential information. A VPN should be used to ensure that your information remains encrypted on public Wi-Fi. ", "#BF6F78");
            }
        });

    }

    public void openDialog(String desc, final String colour){
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
                    openDialogUpdate("You have just connected securely to a WiFi connection through a VPN. You are now ready to go");
                    alertDialog.cancel();
                } else {
                    startActivity(new Intent(getApplicationContext(), CyberSimTwo.class));
                }
            }
        });

        alertDialog.show();

    }

    public void openDialogUpdate(String desc){
        //method to call the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_feedback, null);

        //initialise the elements
        TextView descTxt = view.findViewById(R.id.dialogFeedbackDesc);
        TextView lblTxt = view.findViewById(R.id.dialogFeedbackLbl);
        Button okBtn = view.findViewById(R.id.DialogFeedbackBtn);

        descTxt.setText(desc); //set the description
        lblTxt.setText("SUCCESS!");

        //create the dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CyberSimTwo.class));
            }
        });

        alertDialog.show();

    }


}