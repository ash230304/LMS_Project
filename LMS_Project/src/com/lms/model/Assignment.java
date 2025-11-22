package com.lms.model;

public class Assignment {
    private int id;
    private int courseId;
    private String title;
    private String description;
    private String dueDate;

    public Assignment(int id, int courseId, String title, String description, String dueDate) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title + " (Due: " + dueDate + ")";
    }
}
