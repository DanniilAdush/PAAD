package com.example.nornal;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Tamagochi;


public class Record extends MainActivity2 {


    int key = 0;

    private SQLiteDatabase mDataBase;

    ListView bazalist;
    Button btnAdd;

    //  TamagochiArrayAdapter adapter;
    DbTamagochi dbTamagochi;
    DbTamagochi baza ;

    String[] numbers = { "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
    int date;



    Context context = this;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        ListView number = findViewById(R.id.number);
        ArrayAdapter<String> adapternum = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, numbers);
        // СѓСЃС‚Р°РЅР°РІР»РёРІР°РµРј РґР»СЏ СЃРїРёСЃРєР° Р°РґР°РїС‚РµСЂ
        number.setAdapter(adapternum);

        bazalist = findViewById(R.id.baza);
        ArrayAdapter<Integer> adapterBaza = new ArrayAdapter(this, android.R.layout.simple_list_item_1, baza.selectAll());
        // СѓСЃС‚Р°РЅР°РІР»РёРІР°РµРј РґР»СЏ СЃРїРёСЃРєР° Р°РґР°РїС‚РµСЂ
        bazalist.setAdapter(adapterBaza);
        registerForContextMenu(bazalist);
        ArrayList<Tamagochi> actors = new ArrayList<>();
        actors.sort(Collections.reverseOrder());
        baza = new DbTamagochi(this);

//        DbTamagochi dbTamagochi = new DbTamagochi(this);
//        dbTamagochi.addTamagochi(new Tamagochi(d, date));
//        dbTamagochi.addTamagochi(new Tamagochi(d, date));
//        dbTamagochi.addTamagochi(new Tamagochi(d, date));
//        dbTamagochi.addTamagochi(new Tamagochi(d, date));
//        shkala();
//        List<Tamagochi> tamagochiList = dbTamagochi.getAllTamagochi();
//        for (Tamagochi tamagochi : tamagochiList) {
//            Log.d("Tamagochi info", "NUMBER" + tamagochi.getNumber()
//                    + " , LivedDay - " + tamagochi.getLivedDay()
//                    + " , Date - " + tamagochi.getDate());//РІС‹РІРѕРґ РІ РєРѕРЅСЃРѕР»СЊ



    }
    //        class TamagochiArrayAdapter extends ArrayAdapter<Tamagochi> {
//
//        public TamagochiArrayAdapter(TamagochiArrayAdapter LivedDay, int Date, @NonNull List<Tamagochi> objects) {
//            super(LivedDay.getContext(), Date, objects);
//            adapter = new TamagochiArrayAdapter(this, android.R.layout.simple_list_item_1, actors);
//            actors.addAll(dbTamagochi.selectAll());
//            listView.setAdapter(adapter);
//        }
//
//        @NonNull
//
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.actor_list_item, null);
//            }
//            Tamagochi actor = getItem(position);
//            ListView LivedDay = convertView.findViewById(R.id.liveddays);
//            TextView Date = convertView.findViewById(R.id.date);
//
//           LivedDay.setText(actor.fullName);
//           Date.setText(actor.date);
//
//            return convertView;
//        }
//}
    public List<Tamagochi> loadRecords(Notification.Action.Builder intent) {
        List<Tamagochi> records = new ArrayList<>();

        // Р’С‹РїРѕР»РЅРµРЅРёРµ Р·Р°РїСЂРѕСЃР° Рє Р±Р°Р·Рµ РґР°РЅРЅС‹С… РґР»СЏ РёР·РІР»РµС‡РµРЅРёСЏ РІСЃРµС… Р·Р°РїРёСЃРµР№
        Cursor cursor =  mDataBase.query("records", null, null, null, null, null, "score DESC");

        // РР·РІР»РµС‡РµРЅРёРµ РґР°РЅРЅС‹С… РёР· Cursor РІ РѕР±СЉРµРєС‚ Record
        while (cursor.moveToNext()) {
            Tamagochi record = new Tamagochi();
            record.setLivedDay(Integer.parseInt(cursor.getString(Integer.parseInt("LivedDay"))));
            record.setDate(cursor.getString(Integer.parseInt("Date")));
            records.add(record);
        }

        // Р—Р°РєСЂС‹С‚РёРµ Cursor
        cursor.close();
        return  records;






//  public void deathh(){
//      if (hunger <= 0 || thirst <= 0 || depres <= 0) {
//        date.setText(format.format(new Date()));
//      }
    }
    //}
}