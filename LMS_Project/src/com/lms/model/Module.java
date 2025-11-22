package com.lms.model;

public class Module {
    private int id;
    private int courseId;
    private String title;

    public Module(int id, int courseId, String title) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
