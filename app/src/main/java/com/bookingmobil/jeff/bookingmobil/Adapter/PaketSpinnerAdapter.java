package com.bookingmobil.jeff.bookingmobil.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.model.Paket;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PaketSpinnerAdapter extends ArrayAdapter<Paket> {

    public PaketSpinnerAdapter(Context context, ArrayList<Paket> pktList){
        super(context,0, pktList);

    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_paket,
                    parent,
                    false
            );
        }

        TextView paket_name = (TextView) convertView.findViewById(R.id.paket_name);
        TextView paket_harga = (TextView) convertView.findViewById(R.id.paket_harga);
        TextView paket_overtime = (TextView) convertView.findViewById(R.id.paket_overtime);
        TextView paket_tipe = (TextView) convertView.findViewById(R.id.paket_tipe);

        Paket paketItem = getItem(position);

        if(paketItem != null) {
            paket_name.setText(paketItem.getNama());
            paket_harga.setText(paketItem.getHarga());
            paket_overtime.setText(paketItem.getOvertime());
            paket_tipe.setText(paketItem.getTipe());
        }

        return convertView;
    }
}
