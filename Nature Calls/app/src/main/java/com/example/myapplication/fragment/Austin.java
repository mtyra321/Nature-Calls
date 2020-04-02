package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Austin extends AppCompatActivity {

    private Button male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_austin);

        male = (Button)findViewById(R.id.button4);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivity();
            }
        });
    }
    public void openActivity()
    {
        Intent intent = new Intent(this,austinM.class);
        startActivity(intent);
    }



}
