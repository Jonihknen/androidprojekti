package com.example.joninapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import static com.example.joninapp.App.ChannelID;

public class nfReceiver extends BroadcastReceiver {
    String title, message;
    @Override
    public void onReceive(Context context, Intent intent) {

        title = intent.getExtras().getString("title");
        message = intent.getExtras().getString("msg");

        NotificationManager nfManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(context, ChannelID)
                .setSmallIcon(R.drawable.kuva1)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .build();
        nfManager.notify(1, notification);
    }


}


