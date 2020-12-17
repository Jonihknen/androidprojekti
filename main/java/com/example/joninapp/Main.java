package com.example.joninapp;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import java.util.Calendar;

import static com.example.joninapp.App.ChannelID;

public class Main extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMsg;
    String title, message;
    TimePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        picker=(TimePicker)findViewById(R.id.time);
        picker.setIs24HourView(true);
        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMsg = findViewById(R.id.edit_text_message);
    }
    public void setAlert(View v) {
        title = editTextTitle.getText().toString();
        message = editTextMsg.getText().toString();

        int hour, minute;
        hour = picker.getHour();
        minute = picker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE, minute);

        Intent intent = new Intent(getApplicationContext(), nfReceiver.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");
        intent.putExtra("title", title);
        intent.putExtra("msg", message);
        PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext()
                ,5,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pIntent);
    }

    public void sendMsg(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMsg.getText().toString();
        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(this, ChannelID)
                .setSmallIcon(R.drawable.kuva1)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setBigContentTitle(title))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
    }

}



