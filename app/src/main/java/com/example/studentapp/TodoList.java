package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TodoList extends AppCompatActivity {

    RecyclerView recyclerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerTask = findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerTask.setLayoutManager(layoutManager);
    }

    public void BtnAddTask(View caller){
        Intent intent = new Intent(this,TaskList.class);
        startActivity(intent);
    }
}