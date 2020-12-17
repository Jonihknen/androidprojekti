package com.example.joninapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {
    public static final String ChannelID = "channel1";

    //LUODAAN KANAVA
    @Override
    public void onCreate() {
        super.onCreate();
        createNfChannel();
    }
    private void createNfChannel() {
            NotificationChannel channel = new NotificationChannel(
                    ChannelID,
                    "Channel1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
