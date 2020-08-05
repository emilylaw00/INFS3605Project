package com.example.infs3605project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VoucherUnlockedAdapter extends RecyclerView.Adapter<VoucherUnlockedAdapter.MyViewHolder> {

    Context context;
    private List<Vouchers> vList;
    private VoucherLockedAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //Note this on click listener is currently not being used as we have not defined


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View itemView, final VoucherLockedAdapter.OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.voucherUnlockedTitle);

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_item2, parent, false);
        VoucherUnlockedAdapter.MyViewHolder evh = new VoucherUnlockedAdapter.MyViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(VoucherUnlockedAdapter.MyViewHolder holder, int position) {
        Vouchers voucher = vList.get(position);
        Log.d("Adapter", "RECYCLER " + position );

        holder.title.setText(voucher.getTitle());


    }

    @Override
    public int getItemCount() {
        return vList.size();
    }

}