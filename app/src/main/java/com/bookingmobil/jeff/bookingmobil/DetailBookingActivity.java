package com.bookingmobil.jeff.bookingmobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bookingmobil.jeff.bookingmobil.model.Booking;
import com.bookingmobil.jeff.bookingmobil.model.Car;
import com.bookingmobil.jeff.bookingmobil.model.Paket;
import com.bookingmobil.jeff.bookingmobil.model.Showroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static android.support.constraint.Constraints.TAG;

public class DetailBookingActivity extends AppCompatActivity {

    private Car activeCar;
    private Paket activePaket;
    private Showroom activeShowroom;

//    IDs
    private Booking booking;
    private String showRoomId;
    private String carId;
    private String selectedPaketId;

    private TextView mobil_name;
    private TextView mobil_warna;
    private TextView mobil_fitur;
    private TextView mobil_transmisi;

    private TextView showroom_name;
    private TextView showroom_lokasi;

    private TextView paket_name;
    private TextView paket_harga;
    private TextView paket_overtiime;
    private TextView paket_tipe;

    private Button btn_batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        mobil_name = (TextView) findViewById(R.id.mobil_name);
        mobil_warna = (TextView) findViewById(R.id.mobil_warna);
        mobil_fitur = (TextView) findViewById(R.id.mobil_fitur);
        mobil_transmisi = (TextView) findViewById(R.id.mobil_transmisi);

        showroom_name = (TextView ) findViewById(R.id.showroom_name);
        showroom_lokasi = (TextView) findViewById(R.id.showroom_location);

        paket_name = (TextView) findViewById(R.id.paket_nama);
        paket_harga = (TextView) findViewById(R.id.paket_harga);
        paket_overtiime = (TextView) findViewById(R.id.paket_overtime);
        paket_tipe = (TextView) findViewById(R.id.paket_tipe);

        btn_batal = (Button) findViewById(R.id.btn_batal);

        getData();
        getShowroom(showRoomId);
        getCar(carId);
        getPkt(selectedPaketId);

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase mFirebase;
                DatabaseReference myRef;

                mFirebase = FirebaseDatabase.getInstance();
                myRef = mFirebase.getReference("bookings");

//                Hapus data booking
                myRef.child(booking.getId()).removeValue();
//                Balik ke activity sebelumnya

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        Intent getIntent = getIntent();
        carId = getIntent.getStringExtra("carId");
        showRoomId = getIntent.getStringExtra("showRoomId");
        selectedPaketId = getIntent.getStringExtra("pktId");
        booking = (Booking) getIntent().getSerializableExtra("booking");
    }

    private void getShowroom(final String id){
        FirebaseDatabase mFirebase;
        DatabaseReference myRef;

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("showrooms");

        final ValueEventListener showRoomListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    if (id.equals(childDataSnapshot.getKey())) {

                        activeShowroom = new Showroom(childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("name").getValue().toString(),
                                childDataSnapshot.child("location").getValue().toString(),
                                childDataSnapshot.child("Photo").getValue().toString());

                        Log.v(TAG,"The Key Bro : "+ activeShowroom.getId());
                    }
                }

                showroom_name.setText(activeShowroom.getName());
                showroom_lokasi.setText(activeShowroom.getLocation());
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

    private void getCar(final String id){
        FirebaseDatabase mFirebase;
        DatabaseReference myRef;

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("cars");

        ValueEventListener carListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    if (id.equals(childDataSnapshot.getKey())) {
                        activeCar = new Car(childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("name").getValue().toString(),
                                childDataSnapshot.child("model").getValue().toString(),
                                childDataSnapshot.child("warna").getValue().toString(),
                                childDataSnapshot.child("gambar").getValue().toString(),
                                childDataSnapshot.child("fitur_tambahan").getValue().toString(),
                                childDataSnapshot.child("transmisi").getValue().toString(),
                                childDataSnapshot.child("bahan_bakar").getValue().toString(),
                                childDataSnapshot.child("kapasitas_mesin").getValue().toString());
                        Log.v(TAG,"The Car Key Bro : "+ activeCar.getId());
                    }
                }

                mobil_name.setText(activeCar.getName());
                mobil_warna.setText(activeCar.getWarna());
                mobil_fitur.setText(activeCar.getFitur_tambahan());
                mobil_transmisi.setText(activeCar.getTransmisi());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(carListener);
    }

    private void getPkt(final String id){
        FirebaseDatabase mFirebase;
        DatabaseReference myRef;

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("paket");

        ValueEventListener pktListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG,"ambilData Key : "+ childDataSnapshot.getKey()); //displays the key for the node
                    Log.v(TAG,"ambilData Nama"+ childDataSnapshot.child("nama").getValue());   //gives the value for given keyname

                    if (id.equals(childDataSnapshot.getKey())) {

                        activePaket = new Paket(
                                childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("carId").getValue().toString(),
                                childDataSnapshot.child("harga").getValue().toString(),
                                childDataSnapshot.child("nama").getValue().toString(),
                                childDataSnapshot.child("overtime").getValue().toString(),
                                childDataSnapshot.child("tipe").getValue().toString());

                        Log.v(TAG,"The Nama Paket Bro : "+ activePaket.getNama());
                    }
                }

                paket_name.setText(activePaket.getNama());
                paket_harga.setText(activePaket.getHarga());
                paket_overtiime.setText(activePaket.getOvertime());
                paket_tipe.setText(activePaket.getTipe());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(pktListener);
    }
}
