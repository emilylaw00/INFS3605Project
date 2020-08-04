package com.example.infs3605project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class VoucherActivity extends AppCompatActivity {

    private TextView coinBalance;
    private List<Vouchers> vList;
    private RecyclerView mRecyclerView;
    private VoucherLockedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button switchBtn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        //initialise
        coinBalance = findViewById(R.id.vCoinBalance);
        mRecyclerView = findViewById(R.id.rc);
        switchBtn = findViewById(R.id.lockedSwitch);

        //collect score from the DB

        Button backBtn = (Button) findViewById(R.id.backBtnEditChecklist);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

    }

    public void createList() {
        //creates the list to go into the recycler view from the DB

        //vList = db.getLockedVouchers();
    }

    public void buildRecyclerView() {
        //builds the recyclerview and sets the adapter
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(VoucherActivity.this);
        mAdapter = new VoucherLockedAdapter(vList, VoucherActivity.this);
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
        LayoutInflater inflater = LayoutInflater.from(VoucherActivity.this);
        View view = inflater.inflate(R.layout.dialog_voucher, null);

        Button noBtn = view.findViewById(R.id.voucherCancelBtn);
        Button okBtn = view.findViewById(R.id.voucherConfirmBtn);

        final AlertDialog alertDialog = new AlertDialog.Builder(VoucherActivity.this)
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