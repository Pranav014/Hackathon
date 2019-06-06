package com.example.anil.hackathon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutusActivity extends AppCompatActivity {
    TextView tvNames,tvDetails,tvCollege;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
       tvNames=(TextView)findViewById(R.id.tvNames);
        tvCollege=(TextView)findViewById(R.id.tvCollege);
        tvDetails=(TextView)findViewById(R.id.tvDetails);


        tvNames.setText("1. Vallari Manavi"+"\n"+"2. Pranav Khismatrao"+"\n"+"3. Chetan Kumbhar"+"\n");
        tvDetails.setText("16IT1035"+"\n"+"16IT1018"+"\n"+"16IT1003");
        tvCollege.setText("Ramrao Adik Institute Of Technology");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
