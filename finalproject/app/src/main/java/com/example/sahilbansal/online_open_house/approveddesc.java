package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class approveddesc extends AppCompatActivity {
TextView t1,t2,t3,t4;
Button b1,b2;
int l,d;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approveddesc);
        t1 = (TextView)findViewById(R.id.textView6);
        t2 = (TextView)findViewById(R.id.textView8);
        t3 = (TextView)findViewById(R.id.like_num);
        t4 = (TextView)findViewById(R.id.dislike_num);
        b1 = (Button)findViewById(R.id.like);
        b2 = (Button)findViewById(R.id.dislike);
        Random r = new Random();
        l = r.nextInt(20 - 0) + 20;
        d = r.nextInt(10 - 0) + 10;
        t3.setText(String.valueOf(l));
        t4.setText(String.valueOf(d));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = l+1;
                t3.setText(String.valueOf(a));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = d+1;
                t4.setText(String.valueOf(b));
            }
        });
        Intent intent  = getIntent();
        String a = intent.getExtras().getString("uid");
        String b = intent.getExtras().getString("title");
        String c = intent.getExtras().getString("description");
    Typeface typeface = Typeface.createFromAsset(getAssets(), "font.ttf");
    t1.setTypeface(typeface);
    t2.setTypeface(typeface);
    t1.setText(a);
    t2.setText(c);
    }
}
