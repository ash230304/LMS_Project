package com.lms.model;

public class Lesson {
    private int id;
    private int moduleId;
    private String title;
    private String content;

    public Lesson(int id, int moduleId, String title, String content) {
        this.id = id;
        this.moduleId = moduleId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
