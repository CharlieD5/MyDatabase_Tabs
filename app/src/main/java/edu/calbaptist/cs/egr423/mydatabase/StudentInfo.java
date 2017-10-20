package edu.calbaptist.cs.egr423.mydatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StudentInfo extends AppCompatActivity {

    public TextView info_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        TextView info_student = (TextView) findViewById(R.id.info_student);
       int number = getIntent().getExtras().getInt("info");

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);

        dbHelper.open();

        String test2 = dbHelper.studentInfo(number);
        info_student.setText(test2);

    }

    public void info_click(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
