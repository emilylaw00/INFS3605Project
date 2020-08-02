package com.example.infs3605project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class CyberTipsFragment extends Fragment {

    Button generate;
    TextView tip;
    ImageView picture;
    String[] tips;
    int[] tipsPictures;

    Random rand = new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cyber_tips, container, false);

        tips = new String[] {"Stick to HTTPs Websites", "Be careful of what you share online", "Do not forget to log out and shut down", "Keep an eye on your devices in public areas", "Regularly back-up your computer", "Be cautious of storing passwords in browsers", "Avoid pop-ups, unknown emails and links", "Use strong password and two factor authentication", "Connect to secure wifi", "Keep up to date with Cyber-Security trends"};
        tipsPictures = new int[] {R.drawable.https1, R.drawable.privacy2, R.drawable. switch3, R.drawable.supervised4, R.drawable.backup5, R.drawable.password6, R.drawable.phishing7, R.drawable.authentication8, R.drawable.internet9, R.drawable.newspaper10};
        tip = v.findViewById(R.id.txtCyberTip);
        picture = v.findViewById(R.id.picture);
        generate = v.findViewById(R.id.btnGenerate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intRand = rand.nextInt(10);
                String cyberTip = tips[intRand];
                int cyberPic = tipsPictures[intRand];
                picture.setImageResource(cyberPic);
                tip.setText(cyberTip);
            }
        });

        return v;
    }
}
