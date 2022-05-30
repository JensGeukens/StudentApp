package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentapp.fakbar.Fakbars;
import com.example.studentapp.menu.Activity_AlmaMenu;
import com.example.studentapp.todo.Activity_TodoList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlmaKnop(View caller){
        Intent intent = new Intent(this, Activity_TodoList.class);
        startActivity(intent);
    }

    public void OnBtnFakbar(View caller){
        Intent intent = new Intent(this, Fakbars.class);
        startActivity(intent);
    }
    public void onBtnAlmaMenu(View caller){
        Intent intent = new Intent(this, Activity_AlmaMenu.class);
        startActivity(intent);
    }

}