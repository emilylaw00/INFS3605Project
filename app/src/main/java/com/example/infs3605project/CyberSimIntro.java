package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CyberSimIntro extends AppCompatActivity {

    TextView description;
    ImageView graphic;
    Button nextBtn, startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_sim_intro);

        //ids
        description = findViewById(R.id.pageDescription);
        graphic = findViewById(R.id.pageImage);
        nextBtn = findViewById(R.id.pageNextBtn);
        startBtn = findViewById(R.id.pageStartBtn);

        //set the text
        description.setText("You have just been hired into TechStar as a 2020 technology consultant graduate. Due to current ciircumstances (COVID-19), you have not been called into work yet");



        //set the button will set the description
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    description.setText("Being a coffee lover, you decide to go to a nearby cafe to work. You walk in, order a coffee and find a nice spot to sit at the back");
                    graphic.setImageResource(R.drawable.bb);
                    nextBtn.setVisibility(View.GONE);
                    startBtn.setVisibility(View.VISIBLE);
                }

        });

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();

                startActivity(new Intent(getApplicationContext(), CyberSimOne.class));


            }
        });

    }
}