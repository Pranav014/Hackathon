package com.example.anil.hackathon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ImageView ivLogin;
    EditText etEmail, etPassword;
    CheckBox ckRemember;
    Button btnLogin, btnSignUp;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogin = (ImageView)findViewById(R.id.ivLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        ckRemember = (CheckBox)findViewById(R.id.ckRemember);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");


        if(user != null)
        {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = etEmail.getText().toString();
                String p = etPassword.getText().toString();
                if(e.length() == 0){
                    etEmail.setError("Please Enter Email");
                    etEmail.requestFocus();
                    return;
                }
                else if(p.length() == 0){
                    etPassword.setError("Please Enter Password");
                    etPassword.requestFocus();
                    return;
                }
                else {

                    firebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Is Successful", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(i1);
                                finish();

                                etPassword.setText("");
                                etPassword.setText("");


                            } else {
                                Toast.makeText(MainActivity.this, "Login Issue " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }



            }
        });
    }
}
