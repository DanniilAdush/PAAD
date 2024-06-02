package com.example.nornal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class MainActivity extends AppCompatActivity {
    View View;

    private int hunger = 100;
    private int thirst = 100;
    private int depres = 100;

    int day, c = 0;
    private TextView hungerBar;
    private TextView thirstBar;
    private TextView depresBar, dayBar;
    private Button hungerButton, waterButton, moodButton;
    int pet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hungerBar = findViewById(R.id.hunger_bar);
        thirstBar = findViewById(R.id.thirst_bar);
        depresBar = findViewById(R.id.depression_bar);
        hungerButton = findViewById(R.id.feed_button);
        waterButton = findViewById(R.id.water_button);
        moodButton = findViewById(R.id.play_button);
        dayBar = findViewById(R.id.day);
        a();

        hungerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hunger += 25;
                if (hunger > 100) {
                    hunger = 100;
                }
                a();


            }
        });

        // РћР±СЂР°Р±РѕС‚РєР° РЅР°Р¶Р°С‚РёСЏ РєРЅРѕРїРєРё РїРѕРёР»РєРё
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thirst += 25;
                if (thirst > 100) {
                    thirst = 100;
                }
                a();

            }
        });

        // РћР±СЂР°Р±РѕС‚РєР° РЅР°Р¶Р°С‚РёСЏ РєРЅРѕРїРєРё РІРѕСЃРїСЂРѕРёР·РІРµРґРµРЅРёСЏ
        moodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depres += 25;
                if (depres > 100) {
                    depres = 100;
                }
                a();

            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                day++;
                a();

            }
        }, 0, 1 * 10 * 1000);
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                hunger -= 10;
                depres -= 10;
                thirst -= 20;
                if(hunger < 0 ) hunger = 0;
                if(depres < 0 ) depres = 0;
                if(thirst < 0 ) thirst = 0;
                a();

            }

        }, 0, 1 * 5 * 1000);

    }

    private void yved() {
        if (hunger <= 50 || thirst <= 50 || depres <= 50) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Dmitry Nagiev")
                            .setContentText("Р”Р°РІРЅРѕ С‚С‹ РєРѕ РјРЅРµ РЅРµ Р·Р°С…РѕРґРёР»,РґСЂСѓРі СЃРµСЂРґРµС‡РЅС‹Р№");

            Notification notification = builder.build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);
        }
        if (hunger <= 25 || thirst <= 25 || depres <= 25) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle("Dmitry Nagiev")
                            .setContentText("Р›РёР±Рѕ С‚С‹ РїСЂРёС…РѕРґРёС€СЊ,Р»РёР±Рѕ СЏ СѓС…РѕР¶Сѓ");

            Notification notification = builder.build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(2, notification);
        }
        if (hunger <= 0 || thirst <= 0 || depres <= 0) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle("Director")
                            .setContentText("РР·-Р·Р° С‚РµР±СЏ СѓРІРѕР»РёР»СЃСЏ РќР°РіРёРµРІ Р”.");

            Notification notification = builder.build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(3, notification);
        }
    }

    private void death() {
        if (c == 0) {
            if (hunger <= 0 || thirst <= 0 || depres <= 0) {
                Intent intent = new Intent(MainActivity.this, Finish.class);
                startActivity(intent);
                c += 1;
            }

        }
    }


    private void a() {

        hungerBar.setText(hunger + " ");
        thirstBar.setText(thirst + " ");
        depresBar.setText(depres + " ");
        dayBar.setText("День: " + day);


        death();
         yved();


    }

}
