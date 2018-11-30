package com.bookingmobil.jeff.bookingmobil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.ShowroomActivity;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;

import java.util.List;

public class ShowroomRecyclerViewAdapter extends RecyclerView.Adapter<ShowroomRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Showroom> mData;

    public ShowroomRecyclerViewAdapter(Context mContext, List<Showroom> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_showroom, viewGroup, false);

        final MyViewHolder vHolder = new MyViewHolder(v);


        vHolder.item_showroom_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowroomActivity.class);
                intent.putExtra("showroomID", mData.get(vHolder.getAdapterPosition()).getId());
                intent.putExtra("showRoomName", mData.get(vHolder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_location.setText(mData.get(position).getLocation());
//        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout item_showroom_id;
        private TextView tv_name;
        private TextView tv_location;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_showroom_id = (LinearLayout) itemView.findViewById(R.id.item_showroom_id);
            tv_name = (TextView) itemView.findViewById(R.id.showroom_name);
            tv_location = (TextView) itemView.findViewById(R.id.showroom_location);
            img = (ImageView) itemView.findViewById(R.id.showroom_image);

        }
    }
}
