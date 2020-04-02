package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Smith extends AppCompatActivity {


    ArrayList<Bathroom> bathrooms;
    ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();
private Smith smith;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smith);
        listView = findViewById(R.id.List);
    }

    protected void onStart() {
        super.onStart();
        bathrooms = new ArrayList<>();
        mRootRef.child("Bathrooms").child("Bathrooms").child("Smith").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.i("setting Bathrooms", map.toString());
                //ListView.s = text;
                for (Object j : map.keySet()) {
                    Log.i("mapKeySet", j.toString());
                    Bathroom b = new Bathroom();
                    b.setBuilding("Smith");
                    b.setRoomNumber(j.toString());
                    Log.i("mapValueSet", map.get(j).toString());
                    Map<String, Object> d = (Map) map.get(j);
                    Log.i("d=", d.toString());
                    b.setDescription(d.get("Description").toString());
                    b.setRating((long) d.get("OverallPoop"));
                    b.setRatings((List) d.get("Ratings"));
                    Map<String, Object> tagsmap = (Map) d.get("Tags");
                    ArrayList<String> tags = new ArrayList<>();
                    for (Object s : tagsmap.keySet()) {
                        Log.i("tagskeyset", s.toString());
                        tags.add(s.toString());
                    }
                    b.setTags(tags);
                    Log.i("bathroom", b.toString());
                    bathrooms.add(b);
                }
               ArrayAdapter<Bathroom> adapter = new ArrayAdapter<>(getParent(), android.R.layout.simple_list_item_1, bathrooms);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                TextView tester = findViewById(R.id.Tester);
                Log.i("bathroomsstring", bathrooms.toString());
                tester.setText(bathrooms.toString());
                //   bathrooms = map;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("setting textview", "failed");
            }
        });
        //  adapter=new ArrayAdapter<String>(this,
        //     android.R.layout.simple_list_item_1,
        //   );
        //  setListAdapter(adapter);


    }

    public void addItems(View v) {
        //     ListView.add("Clicked : "+clickCounter++);
    //    adapter.notifyDataSetChanged();
    }
}
