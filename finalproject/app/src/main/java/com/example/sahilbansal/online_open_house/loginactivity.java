package com.example.sahilbansal.online_open_house;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class loginactivity extends AppCompatActivity {
   // private Toolbar tool;
    private Button registeresd;
    private EditText e1, e2, e3;
    String useremail;
    String useruid;
    String userpassword;
    ProgressDialog progressDialog;
    Boolean yes;
    ArrayList<String> d = new ArrayList<>();
    int count = 0;
    adduser adduser = new adduser();
    ArrayList<String> uid = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
       // tool = (Toolbar) findViewById(R.id.logintoolbar);
//        setSupportActionBar(tool);
//        this.getSupportActionBar().setTitle("Register here");
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        e1 = (EditText) findViewById(R.id.email);
        progressDialog = new ProgressDialog(this);
        e2 = (EditText) findViewById(R.id.uid);
        e3 = (EditText) findViewById(R.id.password);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        registeresd = (Button) findViewById(R.id.click);
        firebaseAuth = FirebaseAuth.getInstance();

        registeresd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regi();

            }
        });

    }

    private void regi() {
        useremail = e1.getText().toString().trim();
        userpassword = e3.getText().toString().trim();
        useruid = e2.getText().toString().trim();
        progressDialog.setTitle("Creating your account");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
                 firebaseAuth.createUserWithEmailAndPassword(useremail, userpassword).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            useremail = e1.getText().toString().trim();

                            String id = firebaseAuth.getCurrentUser().getUid();
                            adduser = new adduser(useremail, useruid, userpassword, id);
                            databaseReference = FirebaseDatabase.getInstance().getReference("users");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                                        adduser = postsnapshot.getValue(com.example.sahilbansal.online_open_house.adduser.class);
                                        uid.add(adduser.getUid());
                                     //   Toast.makeText(loginactivity.this, adduser.getUid(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            String d = String.valueOf(uid.size());
                            Toast.makeText(loginactivity.this, d, Toast.LENGTH_SHORT).show();
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(adduser);
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(loginactivity.this, "Email verification link send", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(loginactivity.this, "Email verificaion not send", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            Toast.makeText(loginactivity.this, "successfull", Toast.LENGTH_SHORT).show();
                           progressDialog.dismiss();
                            Intent intent = new Intent(loginactivity.this, register.class);
                            finish();
                            startActivity(intent);


                        } else {
                            Toast.makeText(loginactivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });


            }

}
