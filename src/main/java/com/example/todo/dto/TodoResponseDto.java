package com.example.todo.dto;

import com.example.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {

    private Long key;
    private String id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private int didnot;
    private Date today;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.name = todo.getName();
        this.password = todo.getPassword();
        this.description = todo.getDescription();
        this.exception = todo.getException();
        this.todo = todo.getTodo();
        this.didnot = todo.getDidNot();
        this.today = todo.getToday();
    }

    public TodoResponseDto(String id, String name, String password, String exception, String description, String todo, int didNot, Date today) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.exception = exception;
        this.description = description;
        this.todo = todo;
        this.today = today;
    }
}
