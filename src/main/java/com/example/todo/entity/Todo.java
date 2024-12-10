package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class Todo {

    private Long key;
    private String id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private int didNot;
    private Date today;

    public Todo(String id ,String name, String password, String exception, String description, String todo , Date today) {

        this.id = id;
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.exception = exception;
        this.description = description;
        this.today = today;
    }
}
