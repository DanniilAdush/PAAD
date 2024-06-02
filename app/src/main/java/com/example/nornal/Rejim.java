package com.example.nornal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rejim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejim);
    }
    public void dnevrejim ( View view) {
        Intent aa4 = new Intent(Rejim.this, MainActivity2.class);
        startActivity(aa4);
    }
    public void minutrejim ( View view) {
        Intent aa4 = new Intent(Rejim.this, MainActivity.class);
        startActivity(aa4);
    }
}