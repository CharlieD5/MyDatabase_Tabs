package edu.calbaptist.cs.egr423.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static DatabaseHelper dbHelper = null;
    private SQLiteDatabase database = null;

    private static final String DATABASE_NAME = "EGR423.db";
    private static final int DATABASE_VERSION = 1;


    public static DatabaseHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        database = getWritableDatabase();

        database.execSQL("CREATE TABLE IF NOT EXISTS COURSES(" +
                "id INTEGER PRIMARY KEY, " +
                "code TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "credits INTEGER);");
    }

    public void close() {
        close();
    }


    public Student createStudent(String name, String email, String comment) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("comments", comment);

        long insertId = database.insert("STUDENTS", null, values);

        if (insertId != -1) {
            return new Student(insertId, name, email, comment);
        }

        Log.e(TAG, "Error inserting data!");
        return null;
    }

    public Course createCourse(String code, String name, int credits) {
        ContentValues values = new ContentValues();
        values.put("code", code);
        values.put("name", name);
        values.put("credits", credits);

        long insertId = database.insert("COURSES", null, values);

        if (insertId != -1) {
            return new Course(insertId, code, name, credits);
        }

        Log.e(TAG, "Error inserting data!");
        return null;
    }



    public String studentInfo(int id){
        int newID = id+1;
    Cursor cursor = database.rawQuery("select * from Students where id = \""+ newID +"\"",null );
        cursor.moveToFirst();
        Student student1 = new Student();
        student1.setId(0);
        student1.setName(cursor.getString(1));
        student1.setEmail(cursor.getString(2));
        student1.setComment(cursor.getString(3));
        cursor.close();

        String name = "Student Name: " + student1.getName() + "\nEmail: "
                + student1.getEmail() + "\nComment: " + student1.getComment();
        return name;
    }



    public String courseInfo(int id){
        int newID = id+1;
        Cursor cursor = database.rawQuery("select * from courses where id = \""+ newID +"\"",null );
        cursor.moveToFirst();
        Course c1 = new Course();
        c1.setId(0);
        c1.setCode(cursor.getString(1));
        c1.setName(cursor.getString(2));
        c1.setCredits(cursor.getInt(3));
        cursor.close();

        String name = "Course Name: " + c1.getName() + "\nCode: "
                + c1.getCode() + "\nCredits: " + c1.getCredits();
        return name;
    }


    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();

        Cursor cursor = database.rawQuery("select * from students", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = new Student();
            student.setId(cursor.getLong(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setComment(cursor.getString(3));
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }



    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        Cursor cursor = database.rawQuery("select * from courses", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course c = new Course();
            c.setId(cursor.getLong(0));
            c.setCode(cursor.getString(1));
            c.setName(cursor.getString(2));
            c.setCredits(cursor.getInt(3));
            courses.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }



    public void deleteStudent(long id) {
        database.execSQL("delete from STUDENTS where name = \"" + id + "\"");
    }

    public void deleteCourse(long id) {
        database.execSQL("delete from COURSES where name = \"" + id + "\"");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS(" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "comments TEXT);");

        database.execSQL("CREATE TABLE IF NOT EXISTS COURSES(" +
                "id INTEGER PRIMARY KEY, " +
                "code TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "credits INTEGER);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS STUDENTS");
        db.execSQL("DROP TABLE IF EXISTS COURSES");
        onCreate(db);
    }

}


