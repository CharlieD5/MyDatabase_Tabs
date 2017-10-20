package edu.calbaptist.cs.egr423.mydatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Charlie on 10/18/17.
 */

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }

    public void addCourseToDB(View view) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.open();
        EditText et = (EditText) findViewById(R.id.name);
        Course course = dbHelper.createCourse(
                ((EditText) findViewById(R.id.code)).getText().toString(),
                ((EditText) findViewById(R.id.name)).getText().toString(),
                Integer.parseInt(((EditText) findViewById(R.id.credits)).getText().toString()));

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
