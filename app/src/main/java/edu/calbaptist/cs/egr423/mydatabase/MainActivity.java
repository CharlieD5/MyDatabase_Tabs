package edu.calbaptist.cs.egr423.mydatabase;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import static edu.calbaptist.cs.egr423.mydatabase.R.id.listFragment2;


public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Setup viewPager
            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

            // Setup Tabs using TabLayout
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.setTabsFromPagerAdapter(pagerAdapter);
            tabLayout.setupWithViewPager(viewPager);

        dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.open();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.listFragment);


        //ListFragment listFragment2 = (ListFragment) findViewById(R.id.listFragment2);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        //ArrayAdapter adapter2 = (ArrayAdapter) listFragment2.getListAdapter();
        adapter.clear();
        //adapter2.clear();
        adapter.addAll(dbHelper.getAllStudents());
        //adapter2.addAll(dbHelper.getAllCourses());
        adapter.notifyDataSetChanged();
        //adapter2.notifyDataSetChanged();
    }



    public void addStudentButtonClick(View view) {
        Intent implicit = new Intent(getApplicationContext(), AddStudentActivity.class);
        startActivity(implicit);
    }

    public void addCourseButtonClick(View view) {
        Intent implicit = new Intent(getApplicationContext(), AddCourseActivity.class);
        startActivity(implicit);
    }

    public void deleteStudentButtonClick(View view) {
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        dbHelper.deleteStudent(((Student)adapter.getItem(0)).getId());
        adapter.remove(adapter.getItem(0));
        adapter.notifyDataSetChanged();
    }

    public void deleteCourseButtonClick(View view) {
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(listFragment2);
        ArrayAdapter adapter = (ArrayAdapter) listFragment.getListAdapter();
        dbHelper.deleteCourse(((Course)adapter.getItem(0)).getId());
        adapter.remove(adapter.getItem(0));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0:
                    return new Tab1Fragment();
                case 1:
                    return new Tab2Fragment();
                default:
                    return new Tab1Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Students";
                case 1: return "Courses";
                default: return "Students";
            }
        }
    }


}
