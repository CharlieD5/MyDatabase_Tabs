package edu.calbaptist.cs.egr423.mydatabase;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Charlie on 10/18/17.
 */

public class MyListFragment2 extends ListFragment {


    public MyListFragment2() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity());
        dbHelper.open();

       final List<Course> course = dbHelper.getAllCourses();

        setListAdapter(new ArrayAdapter<Course>(getActivity(),
                android.R.layout.simple_list_item_1,
                course));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        int newId = (int)id;
        Intent i = new Intent(getActivity(),CourseInfo.class);
        i.putExtra("info", newId);
        startActivity(i);
    }

}
