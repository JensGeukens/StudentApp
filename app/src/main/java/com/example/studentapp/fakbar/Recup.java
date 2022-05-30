package com.example.studentapp.fakbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Recup extends AppCompatActivity {
    private RequestQueue requestQueue;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakbar);
    }
    public void onBtReturn(View caller) {
        Intent intent = new Intent(this, Fakbars.class);
        startActivity(intent);
    }

    public void getDatabaseData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String requestURL = "https://studev.groept.be/api/a21pt205/count_info/" + 1 + "/";

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("re",String.valueOf(response));
                        try {
                            JSONObject curObject = response.getJSONObject(0);
                            progress = curObject.getInt("count");
                            Log.d("rere", String.valueOf(progress));
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
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


}


