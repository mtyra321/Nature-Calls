package com.example.myapplication.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.UserHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_profile extends AppCompatActivity {

    EditText name, email, requested, phone;
    Button updated;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        phone = findViewById(R.id.phone_number);
        name = findViewById(R.id.name);
        email = findViewById(R.id.user_mail);
        requested = findViewById(R.id.request);
        updated = findViewById(R.id.update);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());
phone.setText(user.getPhoneNumber());
        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get all the values
                String signName = name.getText().toString();
                String userEmail = email.getText().toString();
                String requests = requested.getText().toString();
                String phoneNumber = phone.getText().toString();
                Log.i("name", signName);
                Log.i("email", userEmail);
                Log.i("req", requests);

                UserHelperClass helperClass = new UserHelperClass(signName, userEmail, requests);
                reference.child(user.getUid()).child("email").setValue(userEmail);
                reference.child(user.getUid()).child("name").setValue(signName);
                reference.child(user.getUid()).child("requests").setValue(requests);
                reference.child(user.getUid()).child("phone").setValue(phoneNumber);


                // reference.child(signName).setValue(helperClass);
                Toast.makeText(user_profile.this, "Submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
