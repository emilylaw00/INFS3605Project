package com.example.infs3605project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class CyberTipsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cyber_tips, container, false);

//        TextView tip = findViewById(R.id.txtCyberTip);
//        String[] tips = {"Stick to HTTPs Websites", "Be careful of what you share online", "Do not forget to log out and shut down", "Keep an eye on your devices in public areas", "Regularly back-up your computer", "Be cautious of storing passwords in browsers", "Avoid pop-ups, unknown emails and links", "Use strong password and (two factor) authentication", "Connect to secure wifi", "Keep up to date with Cyber-Security trends"};
//        int[] tipsPictures;
//        Random rand = new Random();
//        int intRand = rand.nextInt(10);
//        String cyberTip = tips[intRand];
//
//        tip.setText(cyberTip);

    }
}
