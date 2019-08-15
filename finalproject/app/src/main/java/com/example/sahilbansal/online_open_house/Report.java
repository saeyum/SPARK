package com.example.sahilbansal.online_open_house;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity {

    String uid, use,userid,email;
    EditText editText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference data;
    FirebaseAuth firebaseAuth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        editText = (EditText)findViewById(R.id.editText);
        firebaseAuth = FirebaseAuth.getInstance();
        button = (Button)findViewById(R.id.button2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        data = firebaseDatabase.getReference("report");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adduser u = dataSnapshot.child(uid).getValue(adduser.class);
                        use = u.getUid();
                        email = u.getEmail();
                        String id = databaseReference.push().getKey();
                        //    Toast.makeText(Report.this, use, Toast.LENGTH_SHORT).show();
                        userid = firebaseAuth.getCurrentUser().getUid();
                        String problemadd = editText.getText().toString().trim();
                        final reportclass reportclass = new reportclass(problemadd,use,email);
//                      final addproblems addproblems = new addproblems(tit,desc,use,uid);
                        data.child(id).setValue(reportclass);
                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    }