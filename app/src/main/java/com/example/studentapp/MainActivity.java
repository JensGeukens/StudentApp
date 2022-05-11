package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlmaKnop(View caller){
        Intent intent = new Intent(this,Alma.class);
        startActivity(intent);
    }

    public void OnBtnFakbar(View caller){
        Intent intent = new Intent(this, Fakbars.class);
        startActivity(intent);
    }
    public void onBtnAlmaMenu(View caller){
        Intent intent = new Intent(this,AlmaMenu.class);
        startActivity(intent);
    }

}