package com.example.studentapp.todo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentapp.MainActivity;
import com.example.studentapp.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private RequestQueue requestQueue;
    int mResource;

    public TaskListAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String task = getItem(position).getTask();
        String time = getItem(position).getTime();
        String date = getItem(position).getDate();

        Task fullTask = new Task(task,time,date);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent,false);

        TextView tvTask = (TextView) convertView.findViewById(R.id.textviewTask);
        TextView tvTime = (TextView) convertView.findViewById(R.id.texViewTime);
        TextView tvDate = (TextView) convertView.findViewById(R.id.textViewDate);

        Button btnDelete = (Button) convertView.findViewById(R.id.btnAdapter);

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("button","button clicked:" + position);
                deleteTask(task,  time,  date);
            }
        });

        tvTask.setText(task);
        tvTime.setText(time);
        tvDate.setText(date);

        return convertView;
    }

    private void deleteTask(String task, String time, String date) {
        requestQueue = Volley.newRequestQueue(mContext);
        String requestURL = "https://studev.groept.be/api/a21pt205/Delete_task/"+date+"/"+task;
        Log.d("requestURL",requestURL);

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Database","task deleted");
                        Intent intent = new Intent(mContext, Activity_TodoList.class);
                        mContext.startActivity(intent);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError e){
                        Log.e("database",e.getMessage(),e);
                        Intent intent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                    }
                }
        );

        requestQueue.add(sumitRequest);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnAdapter:
                Log.d("btn", String.valueOf(v.getId()));
                break;
        }
    }



}
