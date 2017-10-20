package edu.calbaptist.cs.egr423.mydatabase;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MyListFragment extends ListFragment {


    public MyListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHelper dbHelper = DatabaseHelper.getInstance(getActivity());
        dbHelper.open();

        final List<Student> students = dbHelper.getAllStudents();

        setListAdapter(new ArrayAdapter<Student>(getActivity(),
                android.R.layout.simple_list_item_1,
                students));

       /* final List<Course> course = dbHelper.getAllCourses();

        setListAdapter(new ArrayAdapter<Course>(getActivity(),
                android.R.layout.simple_list_item_2,
                course));*/
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        int newId = (int)id;
        Intent i = new Intent(getActivity(),StudentInfo.class);
        i.putExtra("info", newId);
        startActivity(i);

        /*int newId2 = (int)id;
        Intent i2 = new Intent(getActivity(),CourseInfo.class);
        i2.putExtra("info", newId2);
        startActivity(i2);*/
    }


}
