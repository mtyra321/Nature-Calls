package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import dukeapps.naturecalls.ui.map.MapFragment;

public class BathroomActivity extends AppCompatActivity {
    ArrayList<Bathroom> bathrooms;
    TextView textview;
    Button yes;
    Button no;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();
    //  DatabaseReference natureLogin = mRootRef.child("natureLogin");

    //DatabaseReference childRef = conditionRef.child("tester");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);
        textview = (TextView) findViewById(R.id.Test);
        yes = findViewById(R.id.Yes);
        no = findViewById(R.id.No);
//        String text = dataSnapshot.getValue().toString();
//        Log.i("setting textview", text);
//        textview.setText(text);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef.child("test").setValue("Yes");
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef.child("test").setValue("No");
            }
        });
    }

    public void Click(View view) {
        Log.i("teasladkjalskd", "in on set click listeneer");

//        MapFragment fragment = new MapFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.navigation_map, fragment);
//
//        transaction.commit();

    }

    protected void onStart() {
        super.onStart();

        mRootRef.child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                Log.i("setting textview", text);
                textview.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("setting textview", "failed");
            }
        });

    }
}
