package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

//import dukeapps.naturecalls.ui.map.MapFragment;

public class BathroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);
    }
    public void Click(View view){
        Log.i("teasladkjalskd", "in on set click listeneer");

//        MapFragment fragment = new MapFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.navigation_map, fragment);
//
//        transaction.commit();

    }
}
