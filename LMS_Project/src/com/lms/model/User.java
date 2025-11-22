package com.lms.model;

public abstract class User {
    protected int id;
    protected String username;
    protected String password; // Changed from passwordHash
    protected String name;
    protected String email;
    protected Role role;
    protected int points; // Added points field

    public enum Role {
        ADMIN, INSTRUCTOR, STUDENT
    }

    public User(int id, String name, String email, String username, String password, Role role) { // Modified
                                                                                                  // constructor
                                                                                                  // signature and order
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password; // Changed from passwordHash
        this.role = role;
        this.points = 0; // Initialize points
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    } // Changed from getPasswordHash

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Abstract method to demonstrate polymorphism if needed,
    // though shared behavior is mostly common.
    public abstract String getDashboardLabel();
}
