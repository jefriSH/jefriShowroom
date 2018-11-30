package com.bookingmobil.jeff.bookingmobil.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.model.Paket;

import java.util.List;

public class PaketRecyclerViewAdapter extends RecyclerView.Adapter<PaketRecyclerViewAdapter.PaketHolder> {

    private List<Paket> mData;
    private Context mContext;

    public PaketRecyclerViewAdapter(Context mContext, List<Paket> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PaketHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_paket, viewGroup, false);
        final PaketRecyclerViewAdapter.PaketHolder paketHolder = new PaketRecyclerViewAdapter.PaketHolder(v);
        return paketHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaketHolder paketHolder, int i) {
        paketHolder.paket_name.setText(mData.get(i).getNama());
        paketHolder.paket_harga.setText(mData.get(i).getHarga());
        paketHolder.paket_overtime.setText(mData.get(i).getOvertime());
        paketHolder.paket_tipe.setText(mData.get(i).getTipe());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class PaketHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_paket;
        private TextView paket_name;
        private TextView paket_harga;
        private TextView paket_overtime;
        private TextView paket_tipe;

        public PaketHolder(@NonNull View itemView) {
            super(itemView);

            item_paket = (LinearLayout) itemView.findViewById(R.id.item_paket_layout);
            paket_name = (TextView) itemView.findViewById(R.id.paket_name);
            paket_harga = (TextView) itemView.findViewById(R.id.paket_harga);
            paket_overtime = (TextView) itemView.findViewById(R.id.paket_overtime);
            paket_tipe = (TextView) itemView.findViewById(R.id.paket_tipe);
        }
    }
}
