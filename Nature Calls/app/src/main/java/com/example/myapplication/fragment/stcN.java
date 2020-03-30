package com.example.myapplication.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class stcN extends AppCompatActivity {
EditText txtcomment;
EditText Rating;
Button btnsave;
model member;
TextView a,b;
ArrayList<String> myArrayList = new ArrayList<>();
ArrayAdapter<String> arrayAdapter;
ListView mylistView;
//Firebase myFirebase;
DatabaseReference reff;
DatabaseReference data;

//    EditText addcomment;
//    ImageView image_profile;
//    TextView post;
//
//    String postId;
//    String poblisherid;

 //   FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stc_n);
//        Firebase.setAndroidContext(this);
//
//        myFirebase = new Firebase("https://naturelogin.firebaseio.com/");
//        mylistView = (ListView) findViewById(R.id.ListView);
//
//        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        mylistView.setAdapter(myArrayAdapter);
//        myFirebase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String myChildValues = dataSnapshot.getValue(String.class);
//                myArrayList.add(myChildValues);
//                myArrayAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                myArrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });


        txtcomment = (EditText)findViewById(R.id.add_comment);
      //  b= (TextView)findViewById(R.id.add_comment);
        btnsave = (Button)findViewById(R.id.button2);
        member = new model();
        reff = FirebaseDatabase.getInstance().getReference().child("STCFemale208");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int rate = Integer.parseInt(Rating.getText().toString().trim());
                member.setComment(txtcomment.getText().toString().trim());
               // member.setRating(rate);
                reff.push().setValue(member);

        //        reff = FirebaseDatabase.getInstance().getReference().child("STCFemale208").child("M3dn4il_w04RmWbj6tP");
//                reff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String comment = dataSnapshot.child("comment").getValue().toString();
//                        String rate = dataSnapshot.child("rating").getValue().toString();
//                        a.setText(comment);
//                        b.setText(rate);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

            }
        });

        data = FirebaseDatabase.getInstance().getReference().child("STCFemal208");
        mylistView =  (ListView) findViewById(R.id.ListView);
        arrayAdapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        mylistView.setAdapter(arrayAdapter);
        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(model.class).toString();
                myArrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
