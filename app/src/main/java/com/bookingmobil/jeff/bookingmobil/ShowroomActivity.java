package com.bookingmobil.jeff.bookingmobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bookingmobil.jeff.bookingmobil.Adapter.CarRecyclerViewAdapter;
import com.bookingmobil.jeff.bookingmobil.model.Car;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ShowroomActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<Car> listCar;
    private String showRoomId;
    private String showRoomName;

    FirebaseDatabase mFirebase;
    DatabaseReference myRef;

    CarRecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom);

        listCar = new ArrayList<>();

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("cars");

        Intent getIntent = getIntent();
        showRoomId = getIntent.getStringExtra("showroomID");
        showRoomName = getIntent.getStringExtra("showRoomName");

        Toast.makeText(this, "Actviity start : " +  showRoomId, Toast.LENGTH_SHORT).show();

        ValueEventListener carListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG,"ambilData Key : "+ childDataSnapshot.getKey()); //displays the key for the node
                    Log.v(TAG,"ambilData Nama"+ childDataSnapshot.child("name").getValue());   //gives the value for given keyname

                    if (showRoomId.equals(childDataSnapshot.child("showroom").getValue().toString())) {
                        listCar.add(new Car(
                                childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("name").getValue().toString(),
                                childDataSnapshot.child("model").getValue().toString(),
                                childDataSnapshot.child("warna").getValue().toString(),
                                childDataSnapshot.child("gambar").getValue().toString(),
                                childDataSnapshot.child("fitur_tambahan").getValue().toString(),
                                childDataSnapshot.child("transmisi").getValue().toString(),
                                childDataSnapshot.child("bahan_bakar").getValue().toString(),
                                childDataSnapshot.child("kapasitas_mesin").getValue().toString()));
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
        myRef.addValueEventListener(carListener);

        mRecycleView = (RecyclerView) findViewById(R.id.car_list);
        recyclerViewAdapter =  new CarRecyclerViewAdapter(this, listCar, showRoomId, showRoomName);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(recyclerViewAdapter);


    }
}
