package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CyberSimPassword extends AppCompatActivity {

    EditText cyberSimPassword;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_password);




        submitBtn = findViewById(R.id.submitBtn);
        cyberSimPassword = findViewById(R.id.passwordChecker);

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