package com.example.studentapp.fakbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.DatabaseUtilsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentapp.R;
import com.example.studentapp.fakbar.Fakbar;
import com.example.studentapp.fakbar.Fakbars;
import com.example.studentapp.todo.Activity_TodoList;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class activity_Dulci extends AppCompatActivity  {
    public Fakbar Dulci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dulci);
        Dulci = new Fakbar("Dulci",this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View v){
        switch(v.getId()){

            case R.id.btnProgress:
                int progress = Dulci.getProgress();
                setViewsProgress(progress);
                break;
            case R.id.btnEvents:
                ArrayList<String[]> events= Dulci.getEvents();
                setViewEvents(events);
                break;
            case R.id.btnReturn:
                Dulci.returnBack();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setViewEvents(ArrayList<String[]> events) {
        Log.d("event", String.valueOf(events));
        TextView event = (TextView) findViewById(R.id.textView);
        events.stream()
                .forEach(str-> event.append("Event:  " + str[0] + "    Date:  " + str[1] + "\n"+ "\n"));
    }

    private void setViewsProgress(int progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar.setMax(10);
        progressBar.setProgress(progress);
        if (progress==10){
            Snackbar timeSnackbar = Snackbar.make(findViewById(R.id.ducli_view), "Get your free drink at the bar!!", BaseTransientBottomBar.LENGTH_LONG);
            timeSnackbar.show();

        }
    }

    /*
    public void getDatabaseData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String requestURL = "https://studev.groept.be/api/a21pt205/count_info/" + 3 + "/";

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("re",String.valueOf(response));
                        try {
                            JSONObject curObject = response.getJSONObject(0);
                            progress = curObject.getInt("count");
                            Log.d("rere", String.valueOf(progress));
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
                            progressBar.setMax(10);
                            progressBar.setProgress(progress);
                        } catch (JSONException e) {

                            Log.e("Database", e.getMessage(), e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Log.e("database", e.getMessage(), e);
                    }
                }
        );
        requestQueue.add(sumitRequest);

    }
    public void onBtProgress(View caller) {

        this.getDatabaseData();

    }

    public void getDatabaseEvents() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String requestURL = "https://studev.groept.be/api/a21pt205/get_events/" + 3 + "/";

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("re", String.valueOf(response));
                        try {
                            TextView event = (TextView) findViewById(R.id.textView3);
                            if(response.length() == 0){
                                event.setText("no events planned");
                            }

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject curObject = response.getJSONObject(i);
                                text = curObject.getString("event");
                                date = curObject.getString("date");
                                Log.d("rere", date);
                                event.append("Event:  " + text + "    Date:  " + date + "\n"+ "\n");
                            }

                        } catch (JSONException e) {

                            Log.e("Database", e.getMessage(), e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Log.e("database", e.getMessage(), e);
                    }
                }
        );
        requestQueue.add(submitRequest);
    }



    public void onBtEvents(View caller) {
        this.getDatabaseEvents();
    }

     */
}

