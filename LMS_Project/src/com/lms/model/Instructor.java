package com.lms.model;

public class Instructor extends User {
    public Instructor(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, Role.INSTRUCTOR);
    }

    @Override
    public String getDashboardLabel() {
        return "Instructor Panel - " + name;
    }
}
