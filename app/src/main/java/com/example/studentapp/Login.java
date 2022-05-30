package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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


public class Login extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String Password;
    private CharSequence Name;
    private CharSequence givenPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void onBtLogin(View caller) {
        this.getDatabaseLogin();

        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }

    public void getDatabaseLogin() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        TextView name = (TextView) findViewById(R.id.editTextTextPersonName);
        Name = name.getText();
        TextView password = (TextView) findViewById(R.id.editTextTextPassword);
        givenPassword = password.getText();
        System.out.println(givenPassword);
        String requestURL = "https://studev.groept.be/api/a21pt205/getPassword/" + Name + "/";

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("re", String.valueOf(response));
                        try {
                                JSONObject curObject = response.getJSONObject(0);
                                Password = curObject.getString("Password");
                                Log.d("rere", Password);
                                if(Password.contentEquals(givenPassword)) {
                                    login();
                                }
                                else{
                                    TextView notification = (TextView) findViewById(R.id.textView5);
                                    notification.setText("Wrong credentials: try again");
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
    public void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

