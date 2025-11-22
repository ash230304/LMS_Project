package com.lms.model;

public class EnrolledCourse extends Course {
    private int progress;

    public EnrolledCourse(int id, String title, String description, int instructorId, int progress) {
        super(id, title, description, instructorId);
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + progress + "% Completed)";
    }
}
