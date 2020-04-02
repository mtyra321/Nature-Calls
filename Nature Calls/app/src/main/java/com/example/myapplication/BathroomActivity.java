package com.example.myapplication;

import android.content.Intent;
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
import java.util.List;

//import dukeapps.naturecalls.ui.map.MapFragment;

public class BathroomActivity extends AppCompatActivity {
    private String description;
    private List<String> tags;
    private List<Ratings> ratings;
    private String roomNumber;
    private String photo;
    private String building;
    private long rating;
    TextView test;

    //DatabaseReference childRef = conditionRef.child("tester");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);
        Intent intent = getIntent();
        description = intent.getStringExtra("Description");
        roomNumber = intent.getStringExtra("Room Number");
        building = intent.getStringExtra("Building");
        rating = intent.getLongExtra("Rating", 0);
        tags = intent.getStringArrayListExtra("Tags");
        //ratings = intent.getParcelableArrayListExtra("Ratings");
        test = findViewById(R.id.Test);
        test.setText(roomNumber);

    }

    public void Click(View view) {


    }

    protected void onStart() {
        super.onStart();

    }
}
