package com.example.sahilbansal.online_open_house;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class enterproblems extends Fragment {
private EditText title;
private EditText description;
private FirebaseDatabase firebaseDatabase;
private FirebaseAuth firebaseAuth;
private DatabaseReference databaseReference;
private DatabaseReference data;
private Button submit;
    String uid;
    String use;
    String tit,desc;
    String userid;

    public enterproblems() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enterproblems, container, false);
        submit = (Button)view.findViewById(R.id.submit);
        title = (EditText)view.findViewById(R.id.title);
        description = (EditText)view.findViewById(R.id.description);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        data = FirebaseDatabase.getInstance().getReference("problems");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter();
            }
        });
        return view;
    }

    private void enter() {
        tit = title.getText().toString().trim();
        desc = description.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adduser u = dataSnapshot.child(uid).getValue(adduser.class);
                use = u.getUid();
              //  userid = firebaseAuth.getCurrentUser().getUid();
                final addproblems addproblems = new addproblems(tit,desc,use,uid);
                data.child(firebaseAuth.getCurrentUser().getUid()).setValue(addproblems);
//                Toast.makeText(getContext(), "Successfull", Toast.LENGTH_SHORT).show();
                title.setText("");
                description.setText("");
                Toast.makeText(getContext(), "Your problem is successfully submitted", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
    }

