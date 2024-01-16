package com.example.mobileproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BrokerListActivity extends AppCompatActivity {
    List<Broker> brokerArrayList = new ArrayList<>();
    DatabaseReference databaseReference;
    RecyclerView notesRV;
    Button btnAddBroker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_list);
        notesRV = findViewById(R.id.notesRV);
        databaseReference = FirebaseDatabase.getInstance("https://mobileproject2-1766e-default-rtdb.firebaseio.com/").getReference().child("Broker");
        getdata();
    }

    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                brokerArrayList.clear();//clear cache
                for (DataSnapshot datas : snapshot.getChildren()) {
                    String notes = datas.getValue().toString();
                    Broker notes1 = datas.getValue(Broker.class);
                    Log.e("lost data is ", notes1.getEmail());
                    brokerArrayList.add(notes1);
                    BrokerAdapter notesAdapter = new BrokerAdapter(brokerArrayList, BrokerListActivity.this);
                    notesRV.setAdapter(notesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(BrokerListActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}