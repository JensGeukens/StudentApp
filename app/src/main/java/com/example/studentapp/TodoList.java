package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodoList extends AppCompatActivity {

    RecyclerView recyclerTask;
    private RequestQueue requestQueue;
    private JSONArray theData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerTask = findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerTask.setLayoutManager(layoutManager);

        addDataToDataBase();

    }

    public void BtnAddTask(View caller){
        Intent intent = new Intent(this,TaskList.class);
        startActivity(intent);
    }

    public void addDataToDataBase() {

        requestQueue = Volley.newRequestQueue(this);
        String requestURL = "https://studev.groept.be/api/a21pt205/Taskinfo";
        Log.d("requestURL",requestURL);

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL,null,
            new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("array", String.valueOf(response));
                        String responseString = "";

                        theData = response;

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

}