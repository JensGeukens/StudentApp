package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AlmaMenu extends AppCompatActivity {
    private TableLayout mTableLayout;
    private RequestQueue requestQueue;
    private TextView txtDay;
    private int currentDay;
    private Map<Integer,String> map =new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        map.put(1,"Monday");
        map.put(2, "Tuesday");
        map.put(3,"Wednesday");
        map.put(4,"Thursday");
        map.put(5,"Friday");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alma_menu);
        mTableLayout = (TableLayout) findViewById(R.id.tableMenu);
        mTableLayout.setStretchAllColumns(true);
        txtDay = (TextView) findViewById(R.id.txtDag);
        currentDay = getDayOfWeek();
        displayDay();
        getDatabaseData(currentDay);


    }


    public void makeTable(JSONArray jsa){
        TableLayout stk = (TableLayout) findViewById(R.id.tableMenu);
        TableRow tbr0 = new TableRow(this);
        stk.removeAllViews();

        //headers 1
        // Table Headers
        //warme gerechten per dag
        TextView tv0 = new TextView( this);

        tv0.setText("Warme gerechten");
        tv0.setTextColor (Color.BLACK);
        tv0.setBackgroundColor(Color.GRAY);
        tbr0.addView(tv0);
        TextView tv1 = new TextView( this);
        tv1.setText("price");
        tv1.setTextColor (Color.BLACK);
        tv1.setBackgroundColor(Color.GRAY);
        tbr0.addView(tv1);
        stk.addView(tbr0);

        try {
            for(int i=0; i<jsa.length();i++){
                JSONObject foodItem = jsa.getJSONObject(i);
                if(String.valueOf(foodItem.get("Type")).equals("w")){
                    Log.d("f", String.valueOf(foodItem));
                    TableRow tbr = new TableRow(this);
                    TextView tv2 = new TextView(this);
                    tv2.setText(String.valueOf(foodItem.get("gerecht")));
                    tv2.setTextColor(Color.BLACK);
                    tv2.setGravity(Gravity.CENTER);
                    tbr.addView(tv2);
                    TextView tv3 = new TextView(this);
                    tv3.setText(String.valueOf(foodItem.get("price"))+" euro");
                    tv3.setTextColor(Color.BLACK);
                    tv3.setGravity(Gravity.CENTER);
                    tbr.addView(tv3);
                    stk.addView(tbr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Veggie gerechten
        TableRow tbr = new TableRow(this);
        TextView tv2 = new TextView(this);
        tv2.setText("Veggie gerecht");
        tv2.setTextColor(Color.BLACK);
        tv2.setBackgroundColor(Color.GRAY);
        tv2.setGravity(Gravity.CENTER);
        tbr.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("");
        tv3.setTextColor(Color.BLACK);
        tv3.setGravity(Gravity.CENTER);
        tbr.addView(tv3);
        stk.addView(tbr);
        try {
            for(int i=0; i<jsa.length();i++){
                JSONObject foodItem = jsa.getJSONObject(i);
                if(String.valueOf(foodItem.get("Type")).equals("v")){
                    Log.d("f", String.valueOf(foodItem));
                    TableRow tbr1 = new TableRow(this);
                    TextView tv5 = new TextView(this);
                    tv5.setText(String.valueOf(foodItem.get("gerecht")));
                    tv5.setTextColor(Color.BLACK);
                    tv5.setGravity(Gravity.CENTER);
                    tbr1.addView(tv5);
                    TextView tv6 = new TextView(this);
                    tv6.setText(String.valueOf(foodItem.get("price"))+" euro");
                    tv6.setTextColor(Color.BLACK);
                    tv6.setGravity(Gravity.CENTER);
                    tbr1.addView(tv6);
                    stk.addView(tbr1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TableRow tbr3 = new TableRow(this);
        TextView tv5 = new TextView(this);
        tv5.setText("Soepjes");
        tv5.setTextColor(Color.BLACK);
        tv5.setBackgroundColor(Color.GRAY);
        tv5.setGravity(Gravity.CENTER);
        tbr3.addView(tv5);
        TextView tv6 = new TextView(this);
        tv6.setText("");
        tv6.setTextColor(Color.BLACK);
        tv6.setGravity(Gravity.CENTER);
        tbr3.addView(tv6);
        stk.addView(tbr3);
        try {
            for(int i=0; i<jsa.length();i++){
                JSONObject foodItem = jsa.getJSONObject(i);
                if(String.valueOf(foodItem.get("Type")).equals("s")){
                    Log.d("f", String.valueOf(foodItem));
                    TableRow tbr1 = new TableRow(this);
                    TextView tv7 = new TextView(this);
                    tv7.setText(String.valueOf(foodItem.get("gerecht")));
                    tv7.setTextColor(Color.BLACK);
                    tv7.setGravity(Gravity.CENTER);
                    tbr1.addView(tv7);
                    TextView tv8 = new TextView(this);
                    tv8.setText(String.valueOf(foodItem.get("price"))+" euro");
                    tv8.setTextColor(Color.BLACK);
                    tv8.setGravity(Gravity.CENTER);
                    tbr1.addView(tv8);
                    stk.addView(tbr1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDayOfWeek(){
        int currentTime = Calendar.getInstance().getTime().getDay();
        Log.d("time",String.valueOf(currentTime));
        return currentTime;
    }
    public void getDatabaseData(int day){
        requestQueue = Volley.newRequestQueue(this);
        String requestURL = "https://studev.groept.be/api/a21pt205/getMenuType/"+day+"/";

        JsonArrayRequest sumitRequest = new JsonArrayRequest(Request.Method.GET, requestURL,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            String responseString = "";
                            for(int i=0; i<response.length();i++){
                                JSONObject curObject = response.getJSONObject(i);
                                responseString +=curObject.getString("id")+"\n";

                            }
                            makeTable(response);
                        } catch (JSONException e) {

                            Log.e("Database",e.getMessage(),e);
                        }
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

    public void displayDay(){
        TextView tv =(TextView) findViewById(R.id.txtDag);
        tv.setTextColor(Color.BLACK);
        String day = map.get(currentDay);
        Log.d("int", String.valueOf(currentDay));

        tv.setText(map.get(currentDay));

    }

    public void PressedBtnLinks(View caller){
        if(currentDay>1){
            currentDay -=1 ;
            displayDay();
            getDatabaseData(currentDay);
        }

    }
    public void pressedBtnRechts(View caller){
        if(currentDay<4){
            currentDay+=1;
            displayDay();
            getDatabaseData(currentDay);
        }
    }
    public void pressedBtnReturn(View caller){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}