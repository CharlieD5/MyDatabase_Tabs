package edu.calbaptist.cs.egr423.mydatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Charlie on 10/18/17.
 */

public class CourseInfo extends AppCompatActivity {
    public TextView info_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        TextView info_course = (TextView) findViewById(R.id.info_course);
        int number = getIntent().getExtras().getInt("info");

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);

        dbHelper.open();

        String test2 = dbHelper.courseInfo(number);
        info_course.setText(test2);

    }

    public void info_click(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
