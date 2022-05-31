package com.example.studentapp.todo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentapp.MainActivity;
import com.example.studentapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Activity_TodoList extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String responseString;
    public ArrayList<Task> arraylistTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        arraylistTask = new ArrayList<Task>();

        addDataToDataBase();
    }

    public void setViews(){
        Log.d("array", String.valueOf(arraylistTask));

        ListView mlistView = (ListView) findViewById(R.id.listview);
        TaskListAdapter adapter = new TaskListAdapter(this,R.layout.adapter_view_layout,arraylistTask);
        mlistView.setAdapter(adapter);
    }

    public void BtnAddTask(View caller){
        Intent intent = new Intent(this, Activity_AddTasks.class);
        startActivity(intent);
    }

    public void addDataToDataBase() {

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt205/Taskinfo";
        Log.d("requestURL",requestURL);

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL,null,
            new Response.Listener<JSONArray>()
                {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject curObject = response.getJSONObject(i);
                                Log.d("object", String.valueOf(response.getJSONObject(i)));
                                Task task = new Task(curObject.getString("task"), curObject.getString("time"), curObject.getString("date"));
                                responseString += curObject.getString("task") + ";" + curObject.getString("time") + ";" + curObject.getString("date");
                                arraylistTask.add(task);

                            }
                        }catch (JSONException e) {
                            Log.e("database", e.getMessage(), e);
                        }
                        setViews();
                        }
                    },
                    new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError e){
                        Log.e("database",e.getMessage(),e);
                }
            }
        );

        requestQueue.add(sumitRequest);
    }

    public void BtnReturn(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}