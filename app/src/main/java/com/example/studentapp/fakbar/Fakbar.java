package com.example.studentapp.fakbar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fakbar extends AppCompatActivity {
    private String name;
    public ArrayList<String[]> events;
    private int progress;
    private Context context;
    public String date;
    public String text;

    public Fakbar(String name, Context context) {
        this.name = name;
        this.events = null;
        this.progress = 0;
        this.context = context;
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String[]> getEvents() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String requestURL = "https://studev.groept.be/api/a21pt205/get_events/" + getName() + "/";

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            if(response.length() == 0){
                                events.add(new String[]{"no events planned"});
                            }

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject curObject = response.getJSONObject(i);
                                Log.d("jsonobject", String.valueOf(curObject));
                                text = curObject.getString("event");
                                date = curObject.getString("date");

                                events.add(new String[]{text, date});
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
        return events;
    }

    public int getProgress() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String requestURL = "https://studev.groept.be/api/a21pt205/count_info/" + getName() + "/";
        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("re",String.valueOf(response));
                        try {
                            JSONObject curObject = response.getJSONObject(0);
                            progress = curObject.getInt("count");
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
    return progress;
    }

    public void returnBack(){
        Intent intent = new Intent(context, Fakbars.class);
        startActivity(intent);
    }
}
