package com.example.infs3605project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VoucherUnlockedAdapter extends RecyclerView.Adapter<VoucherLockedAdapter.MyViewHolder> {

    Context context;
    private List<Vouchers> vList;
    private VoucherLockedAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //Note this on click listener is currently not being used as we have not defined
    //functionality beyond the scope of the unlocked vouchers.
    public void setOnItemClickListener(VoucherLockedAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public MyViewHolder(View itemView, final VoucherLockedAdapter.OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener to listen for when the item has been clicked
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

    //adapter constructor
    public VoucherUnlockedAdapter(List<Vouchers> mvList, Context mContext) {
        vList = mvList;
        context = mContext;
    }

    @Override
    public VoucherLockedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_item2, parent, false);
        VoucherLockedAdapter.MyViewHolder evh = new VoucherLockedAdapter.MyViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(VoucherLockedAdapter.MyViewHolder holder, int position) {
        Vouchers voucher = vList.get(position);
        Log.d("Adapter", "RECYCLER " + position );

        //String cost =  Integer.toString(voucher.getVoucherCost());
        holder.costLabel.setText(Integer.toString(voucher.getVoucherCost()));
        holder.title.setText(voucher.getTitle());


    }

    @Override
    public int getItemCount() {
        return vList.size();
    }

}