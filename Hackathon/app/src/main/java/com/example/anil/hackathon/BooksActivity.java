package com.example.anil.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BooksActivity extends AppCompatActivity {

    Button btnSrch, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        btnSrch = (Button)findViewById(R.id.btnSrch);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BooksActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BooksActivity.this, AddbooksActivity.class);
                startActivity(i);
            }
        });



    }
}
