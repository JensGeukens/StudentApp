package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Politika extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.politika);
    }
    public void onBtReturn(View caller) {
        Intent intent = new Intent(this, Fakbars.class);
        startActivity(intent);
    }
}
