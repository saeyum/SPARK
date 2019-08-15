package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class showdesc extends AppCompatActivity {
TextView t1 , t2;
    private TabLayout myTablayout;
   private Toolbar mtoolbar;
    private DatabaseReference databaseReference;
    String d = "delete";
    String a = "approve";
    String n,m,t,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdesc);
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        t1 = (TextView)findViewById(R.id.useruid);
        t2 = (TextView)findViewById(R.id.descr);
        Intent intent = getIntent();
        databaseReference = FirebaseDatabase.getInstance().getReference("permanent");
         n = intent.getExtras().getString("uid");
         m = intent.getExtras().getString("desc");
         t = intent.getExtras().getString("title");
         f = intent.getExtras().getString("uniqueid");
         t1.setText(n);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "comic.ttf");
        t1.setTypeface(typeface);
         t2.setText(m);
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Problems");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
            if (item.getItemId() == R.id.approve)
            {
                String id = databaseReference.push().getKey();
                permanentprob permanentprob = new permanentprob(t, m, n);
                databaseReference.child(id).setValue(permanentprob);
                Toast.makeText(this, "Problem Approved", Toast.LENGTH_SHORT).show();
             // deleteprob();
                DatabaseReference dreference = FirebaseDatabase.getInstance().getReference("problems").child(f);
                dreference.getRef().removeValue();
                Intent intent = new Intent(showdesc.this , approval.class);
                finish();
                startActivity(intent);
            }

        if(item.getItemId()==R.id.Delete)
        {
         //  deleteprob();
            DatabaseReference dreference = FirebaseDatabase.getInstance().getReference("problems").child(f);
            dreference.getRef().removeValue();
            Toast.makeText(showdesc.this, "Problem Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(showdesc.this , approval.class);
            finish();
            startActivity(intent);
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.approveprob,menu);
        return true;
    }
    public void deleteprob()
    {
//        DatabaseReference dreference = FirebaseDatabase.getInstance().getReference("problems").child(f);
//        dreference.removeValue();
//        Toast.makeText(showdesc.this, "Value Removed", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(showdesc.this , approval.class);
//        finish();
//        startActivity(intent);
     //   Toast.makeText(this, "problem is deleted3", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(showdesc.this , approval.class);
        finish();
        startActivity(intent);
    }
}
