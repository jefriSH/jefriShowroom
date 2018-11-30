package com.bookingmobil.jeff.bookingmobil.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.PaketActivity;
import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.ShowroomActivity;
import com.bookingmobil.jeff.bookingmobil.model.Car;

import java.util.List;

public class CarRecyclerViewAdapter extends RecyclerView.Adapter<CarRecyclerViewAdapter.CarHolder> {

    Context mContext;
    List<Car> mData;
    Dialog mDialog;
    String showRoomId;
    private String showRoomName;

    private TextView carId;
    private Button btnPilihPaket;
    private String carName;

    public CarRecyclerViewAdapter(Context mContext, List<Car> mData,String showRoomId, String showRoomName) {
        this.mContext = mContext;
        this.mData = mData;
        this.showRoomId = showRoomId;
        this.showRoomName = showRoomName;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_car, viewGroup, false);
        final CarHolder carHolder = new CarHolder(v);

        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_book);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        carId = (TextView) mDialog.findViewById(R.id.dialog_car_id);


        btnPilihPaket = (Button) mDialog.findViewById(R.id.dialog_pilih_paket);

        carHolder.item_car.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView tv_dialog_id= (TextView) mDialog.findViewById(R.id.dialog_car_id);
                TextView tv_dialog_name = (TextView) mDialog.findViewById(R.id.dialog_car_name);
                TextView tv_dialog_model = (TextView) mDialog.findViewById(R.id.dialog_car_model);
                TextView tv_dialog_kapasitas = (TextView) mDialog.findViewById(R.id.dialog_kapasitas_mesin);
                TextView tv_dialog_warna = (TextView) mDialog.findViewById(R.id.dialog_car_warna);
                TextView tv_dialog_transmisi = (TextView) mDialog.findViewById(R.id.dialog_car_transmisi);
                TextView tv_dialog_fitur = (TextView) mDialog.findViewById(R.id.dialog_car_fitur);
                TextView tv_dialog_bahan = (TextView) mDialog.findViewById(R.id.dialog_car_bahan);

                ImageView img_dialog = (ImageView) mDialog.findViewById(R.id.dialog_car_image);

                tv_dialog_id.setText(mData.get(carHolder.getAdapterPosition()).getId());
                tv_dialog_name.setText(mData.get(carHolder.getAdapterPosition()).getName());
                carName = tv_dialog_name.getText().toString();
                tv_dialog_model.setText(mData.get(carHolder.getAdapterPosition()).getModel());
                tv_dialog_kapasitas.setText("Kapasitas Mesin : " + mData.get(carHolder.getAdapterPosition()).getKapasitas_mesin());
                tv_dialog_warna.setText("Warna " + mData.get(carHolder.getAdapterPosition()).getWarna());
                tv_dialog_transmisi.setText("Transmisi : " + mData.get(carHolder.getAdapterPosition()).getTransmisi());
                tv_dialog_fitur.setText("Fitur" + mData.get(carHolder.getAdapterPosition()).getFitur_tambahan());
                tv_dialog_bahan.setText("Bahan Bakar" + mData.get(carHolder.getAdapterPosition()).getBahan_bakar());
//                img_dialog.setImageResource(mData.get(carHolder.getAdapterPosition()).getGambar());
//                Toast.makeText(mContext, "Tes Click " + String.valueOf(carHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                mDialog.show();
            }
        });

        btnPilihPaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String car = carId.getText().toString();
                Intent intent = new Intent(mContext, PaketActivity.class);
                intent.putExtra("carId", car);
                intent.putExtra("carName", carName);
                intent.putExtra("showRoomId", showRoomId);
                intent.putExtra("showRoomName", showRoomName);
                mContext.startActivity(intent);
            }
        });


        return carHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder carHolder, int i) {

        carHolder.tv_name.setText(mData.get(i).getName());
        carHolder.tv_model.setText(mData.get(i).getModel());
//        carHolder.img.setImageResource(mData.get(i).getGambar());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CarHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_car;
        private TextView tv_name;
        private TextView tv_model;
        private TextView tv_transmisi;
        private ImageView img;

        public CarHolder(@NonNull View itemView) {
            super(itemView);

            item_car = (LinearLayout) itemView.findViewById(R.id.item_car_id);
            tv_name = (TextView) itemView.findViewById(R.id.car_name);
            tv_model = (TextView) itemView.findViewById(R.id.car_model);
            img = (ImageView) itemView.findViewById(R.id.car_image);
        }
    }
}
