package com.bookingmobil.jeff.bookingmobil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bookingmobil.jeff.bookingmobil.Adapter.PaketSpinnerAdapter;
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
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class PaketActivity extends AppCompatActivity {

    private ArrayList<Paket> pktList;
    private PaketSpinnerAdapter mAdapter;
    private String selectedPaketId;


    private String carId;
    private String carName;
    private String showRoomId;
    private String showRoomName;

//    DATE
    private TextView tvStartDate;
    private TextView tvEndDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

//    Booking
    private Button btnBook;

    FirebaseDatabase mFirebase;
    DatabaseReference myRef;
    FirebaseUser userFirebase;

    Paket selectedPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        pktList = new ArrayList<>();
        tvStartDate = (TextView) findViewById(R.id.pilih_tanggal_mulai);
        tvEndDate = (TextView) findViewById(R.id.pilih_tanggal_akhir);
        btnBook = (Button) findViewById(R.id.btnBooking);

        mFirebase = FirebaseDatabase.getInstance();
        myRef = mFirebase.getReference("paket");
        userFirebase = FirebaseAuth.getInstance().getCurrentUser();

//        Click pilih tannggal mulai
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PaketActivity.this,
                        android.R.style.Theme_Holo_Light,
                        mDateSetListener,
                        year, month, day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + " / " + month + " / " + year;
                tvStartDate.setText(date);
            }
        };

        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PaketActivity.this,
                        android.R.style.Theme_Holo_Light,
                        mDateSetListener2,
                        year, month, day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + " / " + month + " / " + year;
                tvEndDate.setText(date);
            }
        };


        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");
        carName = intent.getStringExtra("carName");
        showRoomId = intent.getStringExtra("showRoomId");
        showRoomName = intent.getStringExtra("showRoomName");

        ValueEventListener carListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG,"ambilData Key : "+ childDataSnapshot.getKey()); //displays the key for the node
                    Log.v(TAG,"ambilData Nama"+ childDataSnapshot.child("nama").getValue());   //gives the value for given keyname

                    if (carId.equals(childDataSnapshot.child("carId").getValue().toString())) {
                        pktList.add(new Paket(
                                childDataSnapshot.getKey().toString(),
                                childDataSnapshot.child("carId").getValue().toString(),
                                childDataSnapshot.child("harga").getValue().toString(),
                                childDataSnapshot.child("nama").getValue().toString(),
                                childDataSnapshot.child("overtime").getValue().toString(),
                                childDataSnapshot.child("tipe").getValue().toString()));
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(carListener);

//        Creating Spinner and fill the data

        Spinner paketSpinner = findViewById(R.id.paket_spinner);

        mAdapter = new PaketSpinnerAdapter(this, pktList);
        paketSpinner.setAdapter(mAdapter);

        paketSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedPaket = (Paket) parent.getItemAtPosition(position);

                selectedPaketId = selectedPaket.getPktId();

                Toast.makeText(PaketActivity.this, selectedPaketId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase mFirebaseBook = FirebaseDatabase.getInstance();
                DatabaseReference myRefBook;

                String carID = carId;
                String showroomID = showRoomId;
                String tgl_mulai = tvStartDate.getText().toString();
                String tgl_akhir = tvEndDate.getText().toString();
                String userId = userFirebase.getEmail();
                String paketName = selectedPaket.getNama();

                myRefBook = mFirebaseBook.getReference("bookings");

                String key = mFirebaseBook.getReference("bookings").push().getKey();

                String id = key;

                Booking pesanan = new Booking(null, carID, showroomID, selectedPaketId, tgl_mulai, tgl_akhir, "Pending", userId, carName, showRoomName, paketName);

                myRefBook.child(id).setValue(pesanan);


                Intent intent = new Intent(PaketActivity.this, MainActivity.class);
                intent.putExtra("carId", carId);
                intent.putExtra("showRoomId", showRoomId);
                intent.putExtra("pktId", selectedPaketId);
                intent.putExtra("booking", pesanan);
                startActivity(intent);
            }
        });
    }
}
