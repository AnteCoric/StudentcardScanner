package com.example.android.studentcardscanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class edit_database_activity extends AppCompatActivity {

    private EditText studentnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database_activity);

        studentnumber = (EditText) findViewById(R.id.etxtstudenten_nummer);
    }

    public void edit(View view) {
        if(studentnumber != null){

        }
    }
}
