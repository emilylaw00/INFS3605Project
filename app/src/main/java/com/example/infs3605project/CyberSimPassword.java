package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        score = findViewById(R.id.passwordScoreLbl);
        submitBtn = findViewById(R.id.submitBtn);
        cyberSimPassword = findViewById(R.id.passwordChecker);

        score.setText(Integer.toString(scoreCount));

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String password = cyberSimPassword.getText().toString().trim();

                if (TextUtils.isEmpty(password)) {
                    cyberSimPassword.setError("Password is required.");
                    return;
                }

                if (password.length() < 6) {
                    cyberSimPassword.setError("Password must be => 8 characters");
                    return;
                }

                if(!findDigit(password)) {
                    cyberSimPassword.setError("Password must contain digits characters");
                    return;
                }

                startActivity(new Intent(getApplicationContext(), CyberSimFive.class));

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





}