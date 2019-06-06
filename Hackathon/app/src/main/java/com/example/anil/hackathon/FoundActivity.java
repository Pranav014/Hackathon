package com.example.anil.hackathon;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoundActivity extends AppCompatActivity {
    ListView lvData;
    ArrayList<lost> lf = new ArrayList<>();
    ArrayAdapter<lost> ad;
    ArrayList<String> k = new ArrayList<>();


    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        lvData = (ListView)findViewById(R.id.lvData);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("lost");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lf.clear();
                for (DataSnapshot d: dataSnapshot.getChildren())
                {
                    lost data = d.getValue(lost.class);
                    lf.add(data);
                    k.add(d.getKey());
                }
                ad = new ArrayAdapter<lost>(FoundActivity.this, android.R.layout.simple_list_item_1, lf);
                lvData.setAdapter(ad);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                lost st = lf.get(i);
                showUpdateDeleteDialog(k.get(i),st.getDesc(), st.getPno());
                return true;
            }
        });

    }

    public void showUpdateDeleteDialog(final String k, final String desc, final String pno){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        ab.setView(dialogView);



        Button btnFound = (Button)dialogView.findViewById(R.id.btnFound);

        final AlertDialog a = ab.create();
        a.show();

        btnFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("Found");
                lost lf = new lost(desc, pno);
                myRef.push().setValue(lf);
                Toast.makeText(FoundActivity.this, "Sent request to Admin", Toast.LENGTH_SHORT).show();
                a.dismiss();

            }
        });

    }
}
