package com.lms.model;

public class Quote {
    private String content;
    private String author;

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "\"" + content + "\" - " + author;
    }
}
