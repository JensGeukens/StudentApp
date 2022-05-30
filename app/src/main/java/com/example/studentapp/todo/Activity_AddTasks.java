package com.example.studentapp.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentapp.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity_AddTasks extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layouList;
    Button buttonAdd;
    Button btnSubmitList;
    private RequestQueue requestQueue;
    private final int MAX_VIEWS  = 4;
    private int currentNrViews = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        layouList = findViewById(R.id.layoutList);
        buttonAdd = findViewById(R.id.buttonAddTask);
        btnSubmitList = findViewById(R.id.BtnSubmit);

        buttonAdd.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.buttonAddTask:
                if(currentNrViews<= MAX_VIEWS){
                    currentNrViews+=1;
                    addView();}
                else{
                    Snackbar timeSnackbar = Snackbar.make(v, "can't make more than 4 task at ones", BaseTransientBottomBar.LENGTH_LONG);
                    timeSnackbar.show();
                }
                break;

            case R.id.BtnSubmit:
                if(checkIfValidAndRead(v)){
                    addDataToDataBase();
                    Intent intent = new Intent(this, Activity_TodoList.class);
                    startActivity(intent);
                }
                break;
            case R.id.btnReturn:
                Intent intent =  new Intent(this, Activity_TodoList.class);
                startActivity(intent);
        }
    }

    private void addDataToDataBase() {
        for(int i =0; i<layouList.getChildCount();i++){

            View taskView = layouList.getChildAt(i);
            EditText editTask = (EditText) taskView.findViewById(R.id.editTask);
            EditText editTime = (EditText) taskView.findViewById(R.id.editTime);
            DatePicker spinnerDate = (DatePicker) this.findViewById(R.id.spinnerDate);

            String task = editTask.getText().toString();
            String time = editTime.getText().toString();
            String year = String.valueOf(spinnerDate.getYear());
            String month = String.format("0%d",spinnerDate.getMonth());
            String day = String.valueOf(spinnerDate.getDayOfMonth());
            String date = year+month+day;

            Log.d("data","task: "+task+", time: "+time+", date: "+date);

            requestQueue = Volley.newRequestQueue(this);
            String requestURL = "https://studev.groept.be/api/a21pt205/addTask/"+task+"/"+time+"/"+date;
            Log.d("requestURL",requestURL);

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
    }

    private boolean checkIfValidAndRead(View v) {
            //refactor
        boolean result = true;
        for(int i =0; i<layouList.getChildCount();i++) {
            View taskView = layouList.getChildAt(i);
            EditText editTask = (EditText) taskView.findViewById(R.id.editTask);
            EditText editTime = (EditText) taskView.findViewById(R.id.editTime);
            DatePicker SpinnerDate = (DatePicker) taskView.findViewById(R.id.spinnerDate);

            String data = editTask.getText().toString();
            String time = editTime.getText().toString();
            String date = editTime.getText().toString();

        Task task = new Task(data, time, date);
        if (!task.checkValidTime()) {
            Snackbar timeSnackbar = Snackbar.make(v, "Give valid time and Date", BaseTransientBottomBar.LENGTH_LONG);
            timeSnackbar.show();
            return false;
        }
        if(!task.checkValidDate()) {
            Snackbar dateSnackbar = Snackbar.make(v, "Give valid date of todday", BaseTransientBottomBar.LENGTH_LONG);
            dateSnackbar.show();
            return false;
        }
        if (!task.checkValidData()) {
            Snackbar mySnackbar = Snackbar.make(v, "Can't summit empty task", BaseTransientBottomBar.LENGTH_LONG);
            mySnackbar.show();
            return false;
            }
        else{
            return result;
        }
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
        currentNrViews-=1;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}