package com.bookingmobil.jeff.bookingmobil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.DetailBookingActivity;
import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.ShowroomActivity;
import com.bookingmobil.jeff.bookingmobil.model.Booking;
import com.bookingmobil.jeff.bookingmobil.model.Car;
import com.bookingmobil.jeff.bookingmobil.model.Paket;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;

import java.util.List;

public class BookingRecycleViewAdapter extends RecyclerView.Adapter<BookingRecycleViewAdapter.BookHolder> {

    Context mContext;
    List<Booking> mData;
    List<Car> mCar;
    List<Paket> mPaket;
    List<Showroom> mShowroom;

    public BookingRecycleViewAdapter(Context mContext, List<Booking> mData, List<Car> mCar, List<Paket> mPaket, List<Showroom> mShowroom) {
        this.mContext = mContext;
        this.mData = mData;
        this.mCar = mCar;
        this.mPaket = mPaket;
        this.mShowroom = mShowroom;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_booking, viewGroup, false);
        final BookingRecycleViewAdapter.BookHolder bookHolder = new BookingRecycleViewAdapter.BookHolder(v);

        bookHolder.item_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailBookingActivity.class);
                intent.putExtra("carId", mData.get(bookHolder.getAdapterPosition()).getCarID());
                intent.putExtra("showRoomId", mData.get(bookHolder.getAdapterPosition()).getShowroomID());
                intent.putExtra("pktId", mData.get(bookHolder.getAdapterPosition()).getPaketId());
                intent.putExtra("booking", mData.get(bookHolder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });

        return bookHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {

        bookHolder.book_title.setText("Nama Mobil " + mData.get(i).getCarName());
        bookHolder.book_showroom.setText("Paket " + mData.get(i).getPaketName());
        bookHolder.book_paket.setText("Sewa di " + mData.get(i).getShowroomName());
        bookHolder.book_status.setText("Status " + mData.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class BookHolder extends RecyclerView.ViewHolder {

        private LinearLayout item_book;
        private TextView book_title;
        private TextView book_showroom;
        private TextView book_paket;
        private TextView book_status;

        public BookHolder(@NonNull View itemView) {
            super(itemView);

            item_book = (LinearLayout) itemView.findViewById(R.id.item_book_id);
            book_title = (TextView) itemView.findViewById(R.id.booking_title);
            book_showroom = (TextView) itemView.findViewById(R.id.booking_showroom);
            book_paket = (TextView) itemView.findViewById(R.id.booking_paket);
            book_status = (TextView) itemView.findViewById(R.id.booking_status);
        }
    }
}
