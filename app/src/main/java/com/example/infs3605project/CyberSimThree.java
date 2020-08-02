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

public class CyberSimThree extends AppCompatActivity {

    Button rightBtn, wrongBtn;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_three);

        description = findViewById(R.id.QuestionThreeDescription);
        rightBtn = findViewById(R.id.rightBtnThree);
        wrongBtn = findViewById(R.id.wrongBtnThree);

        description.setText("Let’s take a look at the next email!");

        rightBtn.setText("Trash");
        wrongBtn.setText("Open");

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("Good work! You have successfully avoided a spear phishing " +
                        "& malware scam. The name of the attachment is not spelt correctly and file " +
                        "size seems inaccurate - good work!", "#6EAE94");
            }
        });

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("You download the proposal form and your computer becomes " +
                        "infected with malware. The next day, you realise that the scammers have accessed your files and " +
                        "deleted documents worth up to $2500 in value. The email sender's address is unfamiliar. The name of the " +
                        "attachment is not spelt correctly and file size seems inaccurate. Email encourages you to download " +
                        "the attachment.", "#BF6F78");
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


                } else {
                    startActivity(new Intent(getApplicationContext(), CyberSimFour.class));
                }
            }
        });

        alertDialog.show();

    }

}