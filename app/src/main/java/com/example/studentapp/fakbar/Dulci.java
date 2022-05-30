package com.example.studentapp.fakbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.R;

public class Dulci extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dulci);
    }
    public void onBtReturn(View caller) {
        Intent intent = new Intent(this, Fakbars.class);
        startActivity(intent);
    }

}

