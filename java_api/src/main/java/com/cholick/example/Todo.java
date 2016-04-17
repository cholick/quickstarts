package com.cholick.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

public class Todo {
    private final Long id;
    @NotBlank
    private final String title;
    private final boolean completed;

    public Todo() {
        id = null;
        title = null;
        completed = false;
    }

    public Todo(String title, boolean completed) {
        this.id = null;
        this.title = title;
        this.completed = completed;
    }

    public Todo(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public boolean isCompleted() {
        return completed;
    }
}
