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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ListView lvBooksInfo;
    ArrayList<Books> b = new ArrayList<>();
    ArrayAdapter<Books> books ;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> k = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lvBooksInfo = (ListView)findViewById(R.id.lvBooksInfo);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Books");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b.clear();
                for (DataSnapshot d: dataSnapshot.getChildren())
                {
                    Books data = d.getValue(Books.class);
                    b.add(data);
                    k.add(d.getKey());

                }
                books = new ArrayAdapter<Books>(SearchActivity.this, android.R.layout.simple_list_item_1, b);
                lvBooksInfo.setAdapter(books);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvBooksInfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Books st = b.get(i);
                showUpdateDeleteDialog(k.get(i),st.getBname(),st.getBcontact(),st.getBownername(), st.getBsem(),st.getbPrice());
                return true;
            }
        });

    }


    public void showUpdateDeleteDialog(final String k, final String bname, final String bcontact, final  String bownername, final String bsem, final int bprice ){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue, null);
        ab.setView(dialogView);



        Button btnBought = (Button)dialogView.findViewById(R.id.btnBought);

        final AlertDialog a = ab.create();
        a.show();

        btnBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("view");
                Books lf = new Books(bname,bcontact,bownername,bsem, bprice );
                myRef.push().setValue(lf);
                Toast.makeText(SearchActivity.this, "Bought", Toast.LENGTH_SHORT).show();
                a.dismiss();

                DatabaseReference dl = FirebaseDatabase.getInstance().getReference("Books").child(k);
                dl.removeValue();
                Toast.makeText(SearchActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                a.dismiss();


            }
        });

    }
}
