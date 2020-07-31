package com.example.infs3605project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EducationFragment extends Fragment {

    Button purityTestBtn;
    Button cyberSim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_education, container, false);

        purityTestBtn = v.findViewById(R.id.purityTestBtn);
        cyberSim = v.findViewById(R.id.simBtn);


        purityTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PurityTest.class));
            }
        });

        cyberSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CyberSimIntro.class));
            }
        });


        return v;



    }
}
