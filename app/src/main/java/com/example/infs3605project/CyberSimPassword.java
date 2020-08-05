package com.example.infs3605project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CyberSimPassword extends AppCompatActivity {

    EditText cyberSimPassword;
    Button submitBtn;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_password);

        //retrieve the score from the intent from CyberSimOne

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");

        Log.d("CSThree:", Integer.toString(scoreCount));

        //

        openDialog("From reading the proposal form that your senior manager sent you, you are required to create an account on TechStar Messenger, a tool used to communicate with the client.", R.drawable.passwordicon);

        //

        score = findViewById(R.id.passwordScoreLbl);
        submitBtn = findViewById(R.id.submitBtn);
        cyberSimPassword = findViewById(R.id.passwordChecker);

        score.setText(Integer.toString(scoreCount));

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String password = cyberSimPassword.getText().toString().trim();


                boolean atleastOneAlpha = password.matches(".*[a-zA-Z]+.*");
                if (!atleastOneAlpha) {
                    cyberSimPassword.setError("Password must contain at least one alphabetic character.");

                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    cyberSimPassword.setError("Password is required.");

                    return;
                }

                //length
                if (password.length() < 8) {
                    cyberSimPassword.setError("Password must be => 8 characters");

                    return;
                }

                //contain digits
                if(!findDigit(password)) {
                    cyberSimPassword.setError("Password must contain at least one numeric character");

                    return ;
                }

                //upper and lower case
                if(!checkCasing(password)) {
                    cyberSimPassword.setError("Password must contain both upper and lower case letters");

                    return ;
                }

                //special character
                if(!checkSpecialCharacter(password)) {
                    cyberSimPassword.setError("Password must contain at least one special character");

                    return ;
                }





                finish();
                Intent intent = new Intent(CyberSimPassword.this, CyberSimFive.class);
                intent.putExtra("score", scoreCount);

                startActivity(intent);

            }

        });

    }


    //check for digits method

    public boolean findDigit(String str) {
        char[] characters = str.toCharArray();
        for (char c : characters) {
            if (Character.isDigit(c)) {
                return true;
            }

        }

        return false;
    }

    public boolean checkCasing(String str) {
        char ch;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            }
            if(hasUpperCase && hasLowerCase)
                return true;
        }
        return false;
    }

    public boolean checkSpecialCharacter(String str) {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (specialCharactersString.contains(Character.toString(ch))) {
                return true;
            } else if (i == str.length() - 1)
                return false;
        }
        return false;
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