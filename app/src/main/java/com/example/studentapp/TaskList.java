package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layouList;
    Button buttonAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        layouList = findViewById(R.id.layoutList);
        buttonAdd = findViewById(R.id.buttonAddTask);

        buttonAdd.setOnClickListener(this);
    }

    public void onClick(View v){
        addView();
    }

    private void addView(){

        View taskView = getLayoutInflater().inflate(R.layout.new_add_task,null,false);

        EditText editText = (EditText)taskView.findViewById(R.id.editTask);
        ImageView imageClose = (ImageView)taskView.findViewById(R.id.imageRemove);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(taskView);
            }
        });

        layouList.addView(taskView);

    }

    private void removeView(View v){

        layouList.removeView(v);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}