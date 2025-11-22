package com.lms.model;

public class Student extends User {
    public Student(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, Role.STUDENT);
    }

    @Override
    public String getDashboardLabel() {
        return "Student Portal - " + name;
    }
}
