package com.example.infs3605project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class VoucherActivity extends AppCompatActivity {

    private TextView coinBalance;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        coinBalance = findViewById(R.id.vCoinBalance);

        //collect score from the DB

        Button backBtn = (Button) findViewById(R.id.backBtnEditChecklist);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }






}