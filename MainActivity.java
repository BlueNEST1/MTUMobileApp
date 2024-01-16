package com.example.mobileproject2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText edEmail, username, edPassword;
    private Button sendDatabtn;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Broker broker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edEmail = findViewById(R.id.idEdtEmployeeEmail);
        username = findViewById(R.id.idEdtEmployeeName);
        edPassword = findViewById(R.id.idEdtEmployeePassword);
        databaseReference = FirebaseDatabase.getInstance("https://mobileproject2-1766e-default-rtdb.firebaseio.com/").getReference().child("Broker");
        broker = new Broker();
        sendDatabtn = findViewById(R.id.idBtnSendData);
        Button btnAddBroker = findViewById(R.id.btnAddBroker);
        btnAddBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BrokerListActivity.class);
                startActivity(intent);
            }
        });
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String str_name = username.getText().toString();
                String str_email = edEmail.getText().toString();
                String str_password = edPassword.getText().toString();

                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(str_name) && TextUtils.isEmpty(str_email) && TextUtils.isEmpty(str_password)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(str_name, str_email, str_password);
                }
            }
            private void addDatatoFirebase(String name, String email, String password) {
                // below 3 lines of code is used to set
                // data in our object class.
                broker.setUserName(name);
                broker.setEmail(email);
                broker.setPassword(password);

                // we are use add value event listener method
                // which is called with database reference.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // inside the method of on Data change we are setting
                        // our object class to our database reference.
                        // data base reference will sends data to firebase.
                        databaseReference.child(name).setValue(broker);
                        username.setText("");
                        edEmail.setText("");
                        edPassword.setText("");
                        // after adding this data we are showing toast message.
                        Toast.makeText(MainActivity.this, "data Added Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // if the data is not added or it is cancelled then
                        // we are displaying a failure toast message.
                        Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Broker");
//
//        myRef.setValue("conorcoakley11@gmail.com");
    }
