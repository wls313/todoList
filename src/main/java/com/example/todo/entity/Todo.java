package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private LocalDate today;
    private int didNot;


    public Todo(String name, String password, String exception, String description, String todo) {
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.exception = exception;
        this.description = description;
        this.today = LocalDate.now();
        this.didNot = 0;
    }
}
