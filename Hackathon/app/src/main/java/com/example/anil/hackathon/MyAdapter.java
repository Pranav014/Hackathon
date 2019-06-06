package com.example.anil.hackathon;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<canteen> listItems;
    private Context mContext;
    FirebaseDatabase database;
    FirebaseUser user;
    String uid;
    DatabaseReference myRef;
    DatabaseReference myRef2;


    public MyAdapter(ArrayList<canteen> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final canteen itemlist = listItems.get(position);
        holder.txtTitle.setText(itemlist.getName());
        holder.txtDescription.setText(itemlist.getPrice());
        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display option menu
                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.mnu_item1:
                                Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item2:
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                uid = user.getUid();
                                database = FirebaseDatabase.getInstance();
                                myRef = database.getReference("Cart").child(uid);
                                myRef2 = database.getReference("admincart");
                                String r = holder.txtTitle.getText().toString();
                                String n = holder.txtDescription.getText().toString();
                                String email = user.getEmail();

                                Cart  can = new Cart(r, n, 1, null, null, user.getEmail());
                                myRef.push().setValue(can);
                                myRef2.push().setValue(can);
                                Toast.makeText(mContext, "Added to Cart", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtOptionDigit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtDescription = (TextView)itemView.findViewById(R.id.txtDescription);
            txtOptionDigit = (TextView)itemView.findViewById(R.id.txtOptionDigit);
        }
    }
}
