package com.example.anil.hackathon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    ArrayList<Cart> cn  = new ArrayList<>();
    FirebaseDatabase database;
    ArrayAdapter<Cart> ad;
    DatabaseReference myRef;
    FirebaseUser user;
    String uid;
    ArrayList<String> k = new ArrayList<>();
    TextView tvInfo;


    Button btnPlaceOrder;
        TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        lvCart = (ListView)findViewById(R.id.lvCart);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);
        myRef = database.getReference("Cart").child(uid);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cn.clear();
                for (DataSnapshot d: dataSnapshot.getChildren())
                {

                    Cart data = d.getValue(Cart.class);

                    cn.add(data);

                    k.add(d.getKey());
                }
                ad = new ArrayAdapter<Cart>(CartActivity.this, android.R.layout.simple_list_item_1, cn);
                lvCart.setAdapter(ad);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        lvCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cart st = cn.get(i);
                showUpdateDeleteDialog(k.get(i),st.getName(), st.getPrice(), st.getQuantity());

                cn.add(st);
                return true;
            }
        });


        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://"+"paytm.com"));
                startActivity(i);



                Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void showUpdateDeleteDialog(final String k, final String name, final String price, final int quantity) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.uddialog, null);
        ab.setView(dialogView);
        final int[] counter = {quantity};


        final EditText etQuantity = (EditText)dialogView.findViewById(R.id.etQuantity);
        Button btnCartSave = (Button)dialogView.findViewById(R.id.btnCartSave);
        Button btnDeletecart = (Button)dialogView.findViewById(R.id.btnDeletecart);


        final AlertDialog a = ab.create();
        a.show();

        btnCartSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dl = FirebaseDatabase.getInstance().getReference("Cart").child(uid).child(k);
                String m = etQuantity.getText().toString();

                if(m.length() == 0) {


                    Cart s = new Cart(name, price, Integer.parseInt(m),null,null,user.getEmail());

                    dl.setValue(s);
                }


                else{
                    Cart s = new Cart(name, price, Integer.parseInt(m),null,null,user.getEmail());

                    dl.setValue(s);
                }
                Toast.makeText(CartActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                a.dismiss();

            }
        });

        btnDeletecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dl = FirebaseDatabase.getInstance().getReference("Cart").child(uid).child(k);
                dl.removeValue();
                Toast.makeText(CartActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                a.dismiss();
            }
        });




    }



}
