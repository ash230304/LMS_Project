package com.lms.model;

public class Submission {
    private int id;
    private int assignmentId;
    private int studentId;
    private String filePath;
    private int grade; // -1 for not graded
    private String feedback;

    public Submission(int id, int assignmentId, int studentId, String filePath, int grade, String feedback) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.filePath = filePath;
        this.grade = grade;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getGrade() {
        return grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setId(int id) {
        this.id = id;
    }
}
