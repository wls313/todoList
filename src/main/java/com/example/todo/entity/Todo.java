package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Todo {

    private Long id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private LocalDateTime today;
    private LocalDateTime alterDay;
    private int didNot;


    public Todo(String name, String password, String exception, String description, String todo) {
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.exception = exception;
        this.description = description;
        this.today = LocalDateTime.now();
        this.alterDay = LocalDateTime.now();
    }

    public Todo(long id, String name, String password, String exception, String description, String todo, int didNot) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.exception = exception;
        this.description = description;
        this.today = LocalDateTime.now();
        this.alterDay = LocalDateTime.now();
    }
}
