package com.example.nornal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);
    }

        public void XD (View view) {
            Intent aa2 = new Intent(Finish.this, Nachalo.class);
            startActivity(aa2);
        }

}