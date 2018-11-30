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

import com.bookingmobil.jeff.bookingmobil.Adapter.ShowroomRecyclerViewAdapter;
import com.bookingmobil.jeff.bookingmobil.R;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class FragmentShowroomList extends Fragment {

    View v;
    private RecyclerView mRecycleView;
    private List<Showroom> listShowroom;

    FirebaseDatabase mFirebase;
    DatabaseReference myRef;

    ShowroomRecyclerViewAdapter recyclerViewAdapter;

    public FragmentShowroomList(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listShowroom = new ArrayList<>();

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("showrooms");

        ValueEventListener showRoomListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG,"ambilData Key : "+ childDataSnapshot.getKey()); //displays the key for the node
                    Log.v(TAG,"ambilData Nama"+ childDataSnapshot.child("name").getValue());   //gives the value for given keyname

                    listShowroom.add( new Showroom(
                            childDataSnapshot.getKey().toString(),
                            childDataSnapshot.child("name").getValue().toString(),
                            childDataSnapshot.child("location").getValue().toString(),
                            childDataSnapshot.child("Photo").getValue().toString()));
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
        myRef.addValueEventListener(showRoomListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_showroomlist, container, false);
        mRecycleView = (RecyclerView) v.findViewById(R.id.showroom_list);
        recyclerViewAdapter =  new ShowroomRecyclerViewAdapter(getContext(), listShowroom);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(recyclerViewAdapter);
        return v;
    }

}
