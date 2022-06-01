package com.example.studentapp.fakbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.MainActivity;
import com.example.studentapp.QrScanner;
import com.example.studentapp.R;

public class Fakbars extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fakbars);
    }
    public void onBtRecup(View caller) {
        Intent intent = new Intent(this, activity_Recup.class);
        startActivity(intent);
    }
    public void onBtVrg(View caller) {
        Intent intent = new Intent(this, activity_VRG.class);
        startActivity(intent);
    }
    public void onBtDulci (View caller) {
        Intent intent = new Intent(this, activity_Dulci.class);
        startActivity(intent);
    }
    public void onBtPolitika(View caller) {
        Intent intent = new Intent(this, activity_Politika.class);
        startActivity(intent);
    }

    public void onBtScan(View caller) {
        Intent intent = new Intent(this, QrScanner.class);
        startActivity(intent);
    }
    public void onBtReturn(View caller) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
