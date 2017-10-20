package edu.calbaptist.cs.egr423.mydatabase;

/**
 * Created by Charlie on 10/18/17.
 */

public class Course {
    private long id;
    private String code;
    private String name;
    private int credits;

    public Course(long id, String code, String name, int credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public Course(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return name;
    }
}
