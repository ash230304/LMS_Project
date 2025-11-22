package com.lms.model;

public class Notification {
    private int id;
    private int userId;
    private String message;
    private String createdAt;
    private boolean isRead;

    public Notification(int id, int userId, String message, String createdAt, boolean isRead) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return message + " (" + createdAt + ")";
    }
}
