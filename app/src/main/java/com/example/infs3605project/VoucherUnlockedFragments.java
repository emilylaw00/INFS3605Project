package com.example.infs3605project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoucherUnlockedFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoucherUnlockedFragments extends Fragment {

    private List<Vouchers> vList;
    private RecyclerView mRecyclerView;
    private VoucherUnlockedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public VoucherUnlockedFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_unlocked, container, false);

        createList();
        mRecyclerView = v.findViewById(R.id.Recycler);
        buildRecyclerView();

        return v;
    }

    public void createList() {
        //DatabaseHelper db = new DatabaseHelper(getActivity());
        //vList = db.getUnlockedVouchers();

        //make the list from the DB
    }

    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VoucherUnlockedAdapter(vList, getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("MainActivity", "RECYCLER");
        mAdapter.notifyDataSetChanged();

    }

}