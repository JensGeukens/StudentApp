package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Fakbars extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fakbars);
    }
    public void onBtRecup(View caller) {
        Intent intent = new Intent(this, Recup.class);
        startActivity(intent);
    }
    public void onBtVrg(View caller) {
        Intent intent = new Intent(this, VRG.class);
        startActivity(intent);
    }
    public void onBtDulci (View caller) {
        Intent intent = new Intent(this, Dulci.class);
        startActivity(intent);
    }
    public void onBtPolitika(View caller) {
        Intent intent = new Intent(this, Politika.class);
        startActivity(intent);
    }
    public void onBtReturn(View caller) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
