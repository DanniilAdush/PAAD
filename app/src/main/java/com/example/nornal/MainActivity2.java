package com.example.nornal;



import static com.example.nornal.DbTamagochi.TABLE_NAME;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity2 extends AppCompatActivity {
    View View;

    public int hunger = 100;
    public int thirst = 100;
    public int depres = 100;
    String date;
    int day, c = 0;
    private TextView hungerBar;
    private TextView thirstBar;
    private TextView depresBar, dayBar;
    private Button hungerButton, waterButton, moodButton;
    public int d;
    Timer timer,timer1;
    Timer timer2;
    private SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hungerBar = findViewById(R.id.hunger_bar);
        thirstBar = findViewById(R.id.thirst_bar);
        depresBar = findViewById(R.id.depression_bar);
        hungerButton = findViewById(R.id.feed_button);
        waterButton = findViewById(R.id.water_button);
        moodButton = findViewById(R.id.play_button);
        dayBar = findViewById(R.id.day);

        loadData();

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
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("TIMER","tick" + hunger + " " + thirst + " " +  depres);
                day++;
                if(c == 1){
                    timer.cancel();
                    timer.purge();
                }
                a();

            }
        }, 0, 1 * 10 * 1000);
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("TIMER1","tick1" + hunger + " " + thirst + " " +  depres);
                hunger -= 10;
                thirst -= 20;
                depres -= 10;
                if(c == 1){
                    timer1.cancel();
                    timer1.purge();
                }
                a();

            }
        }, 0, 1 * 5 * 1000);
        timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                saveData();


            }
        }, 0, 1 * 10 * 1000);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void date() {
        LocalDate date = LocalDate.now(); // РїРѕР»СѓС‡Р°РµРј С‚РµРєСѓС‰СѓСЋ РґР°С‚Сѓ
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    private void death () {
        if (c == 0) {
            if (hunger <= 0 || thirst <= 0 || depres <= 0) {
                sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();

                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                }
                if (timer1 != null) {
                    timer1.cancel();
                    timer1.purge();

                }
                if (timer2 != null) {
                    timer2.cancel();
                    timer2.purge();

                }
                //РґРѕРґРµР»Р°С‚СЊ РѕСЃС‚Р°РЅРѕРІРєСѓ С‚Р°Р№РјРµСЂР° Рё sharedPreferences

                Log.d("DEATH","ON DEATH" + hunger + " " + thirst + " " +  depres);
                Intent intent = new Intent(MainActivity2.this, Finish.class);
                startActivity(intent);
                c += 1;

            }

        }
    }


    void saveData() {
        sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("SAVE","BEFORE SAVE" + day + " " + hunger + " " + thirst + " " +  depres);
        editor.putInt("day", day);
        editor.putInt("hunger", hunger);
        editor.putInt("thirst", thirst);
        editor.putInt("depres", depres);

        editor.apply();
    }

    void loadData() {
        sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        Log.d("LOAD","Before load" + day + " " + hunger + " " + thirst + " " +  depres);
        day = sharedPreferences.getInt("day", 1);
        hunger = sharedPreferences.getInt("hunger", 100);
        thirst = sharedPreferences.getInt("thirst", 100);
        depres = sharedPreferences.getInt("depres", 100);
        Log.d("LOAD","AFTER load" + hunger + " " + thirst + " " +  depres);
        dayBar.setText(Integer.toString(day));
        hungerBar.setText(Integer.toString(hunger));
        thirstBar.setText(Integer.toString(thirst));
        depresBar.setText(Integer.toString(depres));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void a() {

        hungerBar.setText(hunger + "");
        thirstBar.setText(thirst + "");
        depresBar.setText(depres + "");
        dayBar.setText("День: " + day);
        yved();
        death();


    }
}




