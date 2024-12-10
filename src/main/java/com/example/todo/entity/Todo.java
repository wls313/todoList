package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private int didNot;

    public Todo(String name, String password, String exception, String description, String todo) {

        this.name = name;
        this.todo = todo;
        this.password = password;
        this.exception = exception;
        this.description = description;
    }

    public Todo(long id, String name, String password, String exception, String description, String todo) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.description = description;
        this.exception = exception;
    }
}
