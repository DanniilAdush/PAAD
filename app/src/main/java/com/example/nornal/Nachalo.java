package com.example.nornal;



import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Nachalo extends AppCompatActivity {
    View view;
//    ArrayList<Tamagochi> tamagochis = new ArrayList<>();
//    ListView listView;
//    Button btnAdd;
//
//    ActorsArrayAdapter adapter;
//    DbTamagochi dbTamagochis;
//
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.nachalo);
//        dbTamagochis = new  DbTamagochi(this);
//        listView = findViewById(R.id.list);
//        btnAdd = findViewById(R.id.btnAdd);
//        adapter =
//                new ActorsArrayAdapter(this, android.R.layout.simple_list_item_1, tamagochis);
//        tamagochis.addAll(dbTamagochis.selectAll());
//        listView.setAdapter(adapter);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Nachalo.this,Record.class);
//                startActivityForResult(intent,100);
//            }
//        });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 10 && resultCode == RESULT_OK){
//            Tamagochi actor = (Tamagochi) data.getSerializableExtra("tamagochi");
//            dbTamagochis.insert(actor);
//            adapter.clear();
//            adapter.addAll(dbTamagochis.selectAll());
//
//        }
//    }
//
//    class ActorsArrayAdapter extends ArrayAdapter<Tamagochi> {
//
//        public ActorsArrayAdapter(@NonNull Context context, int resource, @NonNull List<Tamagochi> objects) {
//            super(context, resource, objects);
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.actor_list_item, null);
//            }
//           Tamagochi tam = getItem(position);
//            TextView tvFullName = convertView.findViewById(R.id.tvFullName);
//            TextView tvBirthDate = convertView.findViewById(R.id.tvBirthDate);
//
//            tvFullName.setText(tam.fullName);
//            tvBirthDate.setText(tam.birthDate);
//
//            return convertView;



    }
    public void rejim ( View view) {
        Intent aa = new Intent(Nachalo.this, Rejim.class);
        startActivity(aa);
    }
    public void record ( View view) {
        Intent aa4 = new Intent(Nachalo.this, Record.class);
        startActivity(aa4);
    }
}
