package com.lms.model;

public class Badge {
    private int id;
    private String name;
    private String description;
    private String icon;

    public Badge(int id, String name, String description, String icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return icon + " " + name;
    }
}
