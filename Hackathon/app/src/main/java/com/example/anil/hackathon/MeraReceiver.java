package com.example.anil.hackathon;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Date;


public class MeraReceiver extends BroadcastReceiver {
    public MeraReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Please drink water", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentTitle("Item Lost");
        builder.setContentText("Item has been lost please check");
        builder.setSubText(new Date().toString());

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        Intent i = new Intent(context, MeraReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 1234, i,0);





    }
}

