package com.example.anil.hackathon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class LostActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    TextView tvLostMsg;
    EditText etLostPhone, etLostItem;
    ImageView ivGallery;
    Button btnLostSave;
    ImageButton ibImage;
    FirebaseUser user;
    String uid;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        tvLostMsg = (TextView) findViewById(R.id.tvLostMsg);
        ibImage = (ImageButton) findViewById(R.id.ibImage);
        etLostPhone = (EditText) findViewById(R.id.etLostPhone);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        etLostItem = (EditText) findViewById(R.id.etLostItem);
        btnLostSave = (Button) findViewById(R.id.btnLostSave);
        ivGallery = (ImageView) findViewById(R.id.ivGallery);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("lost");

        tvLostMsg.setText("Enter Your Lost Item and you Will Find it shortly");

        btnLostSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String d = etLostItem.getText().toString();
                String p = etLostPhone.getText().toString();


                lost lf = new lost(d, p);
                myRef.child(uid).push().setValue(lf);
                Toast.makeText(LostActivity.this, "Lost Item Added", Toast.LENGTH_SHORT).show();

                etLostItem.setText("");
                etLostPhone.setText("");
                etLostItem.requestFocus();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(LostActivity.this);
                builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
                builder.setContentTitle("Item Lost");
                builder.setContentText("Item has been lost please check");
                builder.setSubText(new Date().toString());

                Intent i = new Intent(LostActivity.this, LostActivity.class);
                PendingIntent pi = PendingIntent.getActivity(LostActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);

                NotificationManager notificationManager = (NotificationManager)getSystemService(LostActivity.this.NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());






            }
        });

        ibImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            ivGallery.setImageURI(imageUri);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

}
