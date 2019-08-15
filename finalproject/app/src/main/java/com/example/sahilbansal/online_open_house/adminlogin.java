package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class adminlogin extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private Databasehelper db;
    EditText email,pass,uid;
    Button button;
    String a,b,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        uid = (EditText)findViewById(R.id.uid);
        firebaseAuth = FirebaseAuth.getInstance();
        button = (Button)findViewById(R.id.click);
        db = new Databasehelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }
    private void insert()
    {
         String mail = email.getText().toString().trim();
         String password = pass.getText().toString().trim();
         String userid = uid.getText().toString().trim();
         db.addperson();
         showperson();
    }
    private void showperson() {

        Cursor c = db.getPerson(1);
        c.moveToFirst();
        if(c != null)
        {
             a = c.getString(c.getColumnIndex(Databasehelper.COLUMN_EMAIL));
             b = c.getString(c.getColumnIndex(Databasehelper.COLUMN_PASS));
             d = c.getString(c.getColumnIndex(Databasehelper.COLUMN_UID));
        }
        valide(a,b,d);

    }
    private void valide(String a , String b , String d)
    {
        String mail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String userid = uid.getText().toString().trim();
         if(a.equals(mail)&&b.equals(password)&&d.equals(userid))
         {
              Intent intent = new Intent(adminlogin.this , approval.class);
              finish();
              startActivity(intent);
         }
    }


}
