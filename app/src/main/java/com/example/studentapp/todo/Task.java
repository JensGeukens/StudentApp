package com.example.studentapp.todo;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Task {
    private String task;
    private String time;
    private String date;

    public Task(String task, String time, String date) {
        this.task = task;
        this.time = time;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean checkValidTime(){
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        Date t = null;
        try {
            t = formatter.parse(time);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean checkValidDate(){
        return true;
    }
    public boolean checkValidData(){
        Log.d("is task empty", String.valueOf(getTask()));
        if(getTask().isEmpty()){
            return false;
        }
        else{return true;}
    }
}
