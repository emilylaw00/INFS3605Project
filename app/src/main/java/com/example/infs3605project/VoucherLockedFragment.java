package com.example.infs3605project;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class VoucherLockedFragment extends Fragment {



    //adapter for the bought vouchers.

    private List<Vouchers> vList;
    private RecyclerView mRecyclerView;
    private VoucherLockedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public VoucherLockedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_locked, container, false);

        //initialise
        mRecyclerView = v.findViewById(R.id.rcUnlocked);

        //call methods to create the recyclerview
        createList();
        buildRecyclerView();

        //item click handler
        mAdapter.setOnItemClickListener(new VoucherLockedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //call db and check if the user can afford the voucher based on the cost and
                //their coin balance


                //checks if the user has enough coins to buy vouchers
                /*if(currentScore < voucherCost) {
                    Toast.makeText(getActivity(), "Sorry, you don't have enough coins!", Toast.LENGTH_SHORT ).show();
                } else {
                    openDialog(position);
                }*/

            }
        });

        return v;

    }

    //additional methods

    public void createList() {
        //creates the list to go into the recycler view from the DB

        //vList = db.getLockedVouchers();
    }

    public void buildRecyclerView() {
        //builds the recyclerview and sets the adapter
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VoucherLockedAdapter(vList, getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("LockedFrag", "RECYCLER is building");
        mAdapter.notifyDataSetChanged();

    }

    public void openDialog(final int itemPosition){

        //the confirmation dialog
        //this will pop up when the user selects an item in the recycler view
        //user must have enough coins otherwise the toast will appear instead
        final int position = itemPosition;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_voucher, null);

        Button noBtn = view.findViewById(R.id.voucherCancelBtn);
        Button okBtn = view.findViewById(R.id.voucherConfirmBtn);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

                //update db

                int voucherCost = vList.get(position).getVoucherCost();
                //int currentScore = db.getScores().get(0).getScore();

                //db.updateScore(currentScore-voucherCost);

                //actively removes the item from the list
                //ensures that it looks like a fluid removal
                //we want it to occur in real time without needing refresh button
                vList.remove(position);
                mRecyclerView.removeViewAt(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, vList.size());
            }

        });

        alertDialog.show();

    }


}