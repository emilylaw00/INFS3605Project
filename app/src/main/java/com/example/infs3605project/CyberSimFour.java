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

public class CyberSimFour extends AppCompatActivity {

    Button trashBtn, openBtn;
    TextView description,score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_four);

        description = findViewById(R.id.QuestionFourDescription);
        trashBtn = findViewById(R.id.rightBtnFour);
        openBtn = findViewById(R.id.wrongBtnFour);
        score = findViewById(R.id.fourScoreLbl);

        //retrieve the score from the intent from CyberSimOne

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");
        score.setText("$" +Integer.toString(scoreCount));
        Log.d("CSFour:", Integer.toString(scoreCount));

        //

        description.setText("Let’s take a look at the final email! What will you do with this one? \n \n" +
                "Click either the \"Open In Docs\" button or the bin button");
//
//        trashBtn.setText("Trash");
//        openBtn.setText("Open");

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("Good work! You have thoroughly checked the sender address, name and receiver name. " +
                        "You are now able to safely open the document and continue your work.", "#6EAE94", scoreCount+1000);
            }
        });

        trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog feedback
                openFeedbackDialog("Oh no! You have just deleted an important document sent " +
                        "from your senior manager! You are unable to complete your work in time and " +
                        "he is not happy with your work ethic. As a result, he resents the email, lost an important client and " +
                        "deducts $1000 from your pay. ", "#BF6F78",scoreCount-1000);
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
                Intent intent = new Intent(CyberSimFour.this, CyberSimPassword.class);
                intent.putExtra("score", score);
                startActivity(intent);


            }
        });

        alertDialog.show();

    }

}