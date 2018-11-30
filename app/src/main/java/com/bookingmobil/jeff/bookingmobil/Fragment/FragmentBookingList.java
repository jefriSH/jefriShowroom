package com.bookingmobil.jeff.bookingmobil.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookingmobil.jeff.bookingmobil.Adapter.BookingRecycleViewAdapter;
import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.model.Booking;
import com.bookingmobil.jeff.bookingmobil.model.Car;
import com.bookingmobil.jeff.bookingmobil.model.Paket;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FragmentBookingList extends Fragment {

    View v;

    private RecyclerView mRecycleView;
    private List<Booking> listBooking;
    private List<Showroom> listShowroom;
    private List<Car> listCar;
    private List<Paket> listPaket;

    FirebaseDatabase mFirebase;
    DatabaseReference myRef;
    FirebaseUser userFirebase;

    BookingRecycleViewAdapter recyclerViewAdapter;

    String userId;

    public FragmentBookingList(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listShowroom = new ArrayList<>();
        listBooking = new ArrayList<>();
        listCar = new ArrayList<>();
        listPaket = new ArrayList<>();

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("bookings");
        userFirebase = FirebaseAuth.getInstance().getCurrentUser();
        userId = userFirebase.getEmail();


        ValueEventListener bookingListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    if(userId.equals(childDataSnapshot.child("userId").getValue().toString())) {
                        listBooking.add(new Booking(
                                childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("carID").getValue().toString(),
                                childDataSnapshot.child("showroomID").getValue().toString(),
                                childDataSnapshot.child("paketId").getValue().toString(),
                                childDataSnapshot.child("tgl_mulai").getValue().toString(),
                                childDataSnapshot.child("tgl_akhir").getValue().toString(),
                                childDataSnapshot.child("status").getValue().toString(),
                                childDataSnapshot.child("userId").getValue().toString(),
                                childDataSnapshot.child("carName").getValue().toString(),
                                childDataSnapshot.child("showroomName").getValue().toString(),
                                childDataSnapshot.child("paketName").getValue().toString()));
                    }
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        myRef.addValueEventListener(bookingListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bookinglist, container, false);
        mRecycleView = (RecyclerView) v.findViewById(R.id.booking_list);
        recyclerViewAdapter =  new BookingRecycleViewAdapter(getContext(), listBooking, listCar, listPaket, listShowroom);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(recyclerViewAdapter);
        return v;
    }
}
