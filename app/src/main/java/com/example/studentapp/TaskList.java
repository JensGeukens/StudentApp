package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TaskList extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layouList;
    Button buttonAdd;
    Button btnSubmitList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        layouList = findViewById(R.id.layoutList);
        buttonAdd = findViewById(R.id.buttonAddTask);
        btnSubmitList = findViewById(R.id.BtnSubmit);

        buttonAdd.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.buttonAddTask:
                addView();
                break;

            case R.id.BtnSubmit:

                if(checkIfValidAndRead()){
                    addDataToDataBase();
                    Intent intent = new Intent(this,TodoList.class);
                    startActivity(intent);
                }


                break;
        }
    }

    private void addDataToDataBase() {

    }

    private boolean checkIfValidAndRead() {
        boolean result = true;

            //refactor
            for(int i =0; i<layouList.getChildCount();i++){

                View taskView = layouList.getChildAt(i);
                EditText editTask = (EditText) taskView.findViewById(R.id.editTask);
                EditText editTime = (EditText) taskView.findViewById(R.id.editTime);

                editTime.getText().toString();
                //set rules
            }

        return result;
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