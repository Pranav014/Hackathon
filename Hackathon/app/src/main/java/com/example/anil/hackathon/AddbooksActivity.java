package com.example.anil.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddbooksActivity extends AppCompatActivity {
    EditText etBookName, etBookSem, etBookPrice, etOwnerName, etContact;
    Button btnBookSave;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);

        etBookName = (EditText)findViewById(R.id.etBookName);
        etBookSem = (EditText)findViewById(R.id.etBookSem);
        etBookPrice = (EditText)findViewById(R.id.etBookPrice);
        etOwnerName = (EditText)findViewById(R.id.etOwnerName);
        etContact = (EditText)findViewById(R.id.etContact);
        btnBookSave = (Button)findViewById(R.id.btnBookSave);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Books");

        btnBookSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = etBookName.getText().toString();
                String s = etBookSem.getText().toString();
                String p = etBookPrice.getText().toString();
                String o = etOwnerName.getText().toString();
                String c = etContact.getText().toString();

                Books b = new Books(n, s, o, c, Integer.parseInt(p));
                myRef.push().setValue(b);
                Toast.makeText(AddbooksActivity.this, "Books Added", Toast.LENGTH_SHORT).show();

                etBookSem.setText("");
                etBookName.setText("");
                etBookPrice.setText("");
                etOwnerName.setText("");
                etContact.setText("");
                etBookName.requestFocus();

            }
        });
    }
}
