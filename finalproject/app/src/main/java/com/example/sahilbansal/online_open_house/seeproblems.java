package com.example.sahilbansal.online_open_house;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class seeproblems extends Fragment {
    ArrayList<String> uid;
    ArrayList<String> title;
    ArrayList<String> description;
    String t;
    String u;
    private DatabaseReference databaseReference;
    private DatabaseReference dr;
    private FirebaseAuth firebaseAuth;


    private ListView listView;

    public seeproblems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seeproblems, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        uid = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("permanent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    permanentprob prob = postsnapshot.getValue(permanentprob.class);

                    uid.add(prob.getUid());
                    title.add(prob.getTitle());
                    description.add(prob.getDesc());
                    Log.d("Permanent", prob.getTitle());

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        listView.setAdapter(new listv(getContext()));
        return view;
    }
    public class listv extends BaseAdapter
    {
        Context cm;
        listv(Context c)
        {
            cm= c;
        }


        @Override
        public int getCount() {
            return title.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

     @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)cm.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.seeprob_layout , null);
            TextView textView = (TextView)convertView.findViewById(R.id.text);
            textView.setText(title.get(position));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity() , approveddesc.class);
                    intent.putExtra("uid" , uid.get(position));
                    intent.putExtra("title" , title.get(position));
                    intent.putExtra("description" , description.get(position));
                    startActivity(intent);
                }
            });
            return convertView;
        }
   }
}