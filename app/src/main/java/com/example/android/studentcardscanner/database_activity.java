package com.example.android.studentcardscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class database_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_activity);
    }

    public void edit_database(View view) {
        Intent intent = new Intent(this, edit_database_activity.class);
        //mogelijke informatie dat ook door gegeven moet worden hier zetten in code (to future self)
        startActivity(intent);
    }
}
