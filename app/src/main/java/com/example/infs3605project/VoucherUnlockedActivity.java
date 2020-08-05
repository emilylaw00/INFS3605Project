package com.example.infs3605project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class VoucherUnlockedActivity extends AppCompatActivity {

    private List<Vouchers> vList;
    private RecyclerView mRecyclerView;
    private VoucherUnlockedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocked);

        createList();
        mRecyclerView = findViewById(R.id.rcUnlocked);
        //buildRecyclerView();

    }

    public void createList() {

        //only when the recycler is active true

    }

    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(VoucherUnlockedActivity.this);
        mAdapter = new VoucherUnlockedAdapter(vList, VoucherUnlockedActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("MainActivity", "RECYCLER");
        mAdapter.notifyDataSetChanged();

    }

}