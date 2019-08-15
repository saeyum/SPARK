package com.example.sahilbansal.online_open_house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class approval extends AppCompatActivity {

    private TextView t1, t2, t3;
    private DatabaseReference databaseReference;
    private DatabaseReference dr;
    private DatabaseReference getid;
    private FirebaseAuth firebaseAuth;
    addproblems prob;
    adduser addu;
    Databasehelper db;
    String a, b, c;
    String p = "sahil";
    String q = "bansal";
    int l;
    ListView problemstitle;
    ArrayList<String> uid;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> userid;
    ArrayAdapter<String> adapter;
    Toolbar mtoolbar;
    String t;
    String u;
    String r;
    String f;
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        problemstitle = (ListView) findViewById(R.id.prob);
        uid = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        userid = new ArrayList<>();
        mtoolbar = (Toolbar)findViewById(R.id.regitertoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Problems");
       db = new Databasehelper(this);
     //   title.add("Titles");
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("problems").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    prob = postsnapshot.getValue(addproblems.class);
                    uid.add(prob.getUid());
                    title.add(prob.getTitle());
                    description.add(prob.getDecsription());
                    userid.add(prob.getUserid());
                 //   Toast.makeText(approval.this, prob.getDecsription() + prob.getTitle() + prob.getUid(), Toast.LENGTH_SHORT).show();
                }
                adapter = new ArrayAdapter<String>(approval.this, android.R.layout.simple_list_item_1, title);
                problemstitle.setAdapter(adapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
        registerForContextMenu(problemstitle);
        problemstitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                t =description.get(position);
                u = uid.get(position);
                r = title.get(position);
                f = userid.get(position);
                adapter.remove(title.get(position));
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(approval.this , errorsolve.class);
                intent.putExtra("title" , r);
                intent.putExtra("uid" , u);
                intent.putExtra("desc" , t);
                intent.putExtra("uniqueid" , f);
                finish();
               startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(approval.this , approval.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.logout)
        {
            Intent intent = new Intent(approval.this,loginorregister.class);
            finish();
            startActivity(intent);
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.admin,menu);
        return true;
    }
}