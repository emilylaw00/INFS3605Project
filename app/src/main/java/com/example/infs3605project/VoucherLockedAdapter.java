package com.example.infs3605project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VoucherLockedAdapter extends RecyclerView.Adapter<VoucherLockedAdapter.MyViewHolder> {

        //adapter for the locked vouchers

        Context context;
        private List<Vouchers> vList;
        private OnItemClickListener mListener;

        public VoucherLockedAdapter(List<Vouchers> mvList, Context mContext) {
            vList = mvList;
            context = mContext;
        }


        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView mImageView;
            public TextView costLabel;


            public MyViewHolder(View itemView, final OnItemClickListener listener) {
                super(itemView);

                //initialising
                mImageView = itemView.findViewById(R.id.imageView);
                costLabel = itemView.findViewById(R.id.voucherPriceLbl);

                itemView.setOnClickListener(new View.OnClickListener() { //allows the recycler items to be clicked for further action
                    @Override
                    public void onClick(View v) {
                        //custom listener
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.voucher_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v, mListener);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Vouchers voucher = vList.get(position);
            Log.d("Adapter", "RECYCLER " + position );

            //set the items in the viewholder

            String cost =  Integer.toString(voucher.getVoucherCost());
            holder.costLabel.setText(cost);

        }

        @Override
        public int getItemCount() {
            return vList.size();
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }


    }
