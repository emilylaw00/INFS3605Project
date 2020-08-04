package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CyberSimOutro extends AppCompatActivity {

    TextView title, scoreLbl;

    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_outro);

        title = findViewById(R.id.outroLbl);
        scoreLbl = findViewById(R.id.simsScore);
        homeBtn = findViewById(R.id.outroHomeBtn);

        //retrieve the score from the intent from CyberSimOne

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int scoreCount = extras.getInt("score");
        Log.d("CSOutro:", Integer.toString(scoreCount));
        scoreLbl.setText(Integer.toString(scoreCount));

        //



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CyberSimOutro.this, MainActivity.class));
            }
        });



    }
}