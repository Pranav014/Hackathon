package com.example.anil.hackathon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    ImageView ivSLogin;
    EditText etSignupRollno, etSignupPassword, etSignupRepeatPassword, etSignupEmail, etSignupPhone;
    Button btnSubmit;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ivSLogin = (ImageView)findViewById(R.id.ivSLogin);
        etSignupRollno = (EditText)findViewById(R.id.etSignupRollno);
        etSignupPassword = (EditText)findViewById(R.id.etSignupPassword);
        etSignupEmail = (EditText)findViewById(R.id.etSignupEmail);
        etSignupPhone = (EditText)findViewById(R.id.etSignupPhone);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = etSignupRollno.getText().toString();
                String s = etSignupEmail.getText().toString();
                String t = etSignupPassword.getText().toString();
                String o = etSignupPhone.getText().toString();



                user b = new user(n, s, t, o);
                myRef.push().setValue(b);
                Toast.makeText(SignupActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();

                etSignupRollno.setText("");
                etSignupEmail.setText("");
                etSignupPassword.setText("");
                etSignupPhone.setText("");

                etSignupRollno.requestFocus();
            }
        });


    }
}
