package com.example.anil.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostfoundActivity extends AppCompatActivity {

    TextView tvLFMsg;
    Button btnLFLost, btnLFFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostfound);

        tvLFMsg = (TextView)findViewById(R.id.tvLFMsg);
        btnLFLost = (Button)findViewById(R.id.btnLFLost);
        btnLFFound = (Button)findViewById(R.id.btnLFFound);

        btnLFFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LostfoundActivity.this, FoundActivity.class);
                startActivity(i);

            }
        });

        btnLFLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LostfoundActivity.this, LostActivity.class);
                startActivity(i);
            }
        });
    }
}
