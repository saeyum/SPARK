package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class loginorregister extends AppCompatActivity {

    private Button register;
    private Button login;
    private Button admin;
    private TextView t1;
    String a;
    Databasehelper db = new Databasehelper(this);
    private FirebaseAuth firebaseAuth;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginorregister);
        register = (Button)findViewById(R.id.registered);
        t1 = (TextView)findViewById(R.id.start);
      //  Typeface typeface = Typeface.createFromAsset(getAssets(), "comic.ttf");
//        t1.setTypeface(typeface);
        login = (Button)findViewById(R.id.loginaccount);
        admin = (Button)findViewById(R.id.admin);
        firebaseAuth = FirebaseAuth.getInstance();
        layout = (RelativeLayout)findViewById(R.id.layout);
//        String a = firebaseAuth.getCurrentUser().toString();
//        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(loginorregister.this , loginactivity.class);
                 startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginorregister.this,register.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginorregister.this , adminlogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            //Toast.makeText(this, firebaseAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(loginorregister.this , Problems.class);
            finish();
            startActivity(intent);
        }
    }


    //
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(firebaseAuth.getCurrentUser()!=null)
//        {
//             Intent intent = new Intent(loginorregister.this , enterproblems.class);
//             finish();
//             startActivity(intent);
//        }
//    }
}
