package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Problems extends AppCompatActivity {
    private Toolbar mtoolbar;
    private FirebaseAuth firebaseAuth;
    private ViewPager myviewPager;
    private TabLayout myTablayout;
    private TabsPagerAdaptor mytabspageradaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        firebaseAuth = FirebaseAuth.getInstance();
        myviewPager = (ViewPager)findViewById(R.id.main_view_pager);
        mytabspageradaptor = new TabsPagerAdaptor(getSupportFragmentManager());
        myviewPager.setAdapter(mytabspageradaptor);
        myTablayout = (TabLayout)findViewById(R.id.main_tabs);
        myTablayout.setupWithViewPager(myviewPager);
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Problems");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.logout)
        {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            firebaseAuth.signOut();
            Intent intent = new Intent(Problems.this , loginorregister.class);
            finish();
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.report)
        {
             Intent intent = new Intent(Problems.this , Report.class);
             startActivity(intent);
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_problems_page,menu);
        return true;
    }
}
