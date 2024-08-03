package com.example.uasapps.models;

import java.util.UUID;

public class TodoModel {
    private UUID id;
    private String title;

    // Konstruktor
    public TodoModel(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    // Getter dan Setter untuk id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // Getter dan Setter untuk title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}