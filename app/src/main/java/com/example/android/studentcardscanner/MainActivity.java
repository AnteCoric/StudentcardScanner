package com.example.android.studentcardscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static public boolean test = true;
    public String naam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void launchDatabase(View view) {

    }

    public void launchNFC(View view) {
        Intent intent = new Intent(this, secondActivity.class);
        //mogelijke informatie dat ook door gegeven moet worden hier zetten in code (to future self)
        startActivity(intent);
    }
}
