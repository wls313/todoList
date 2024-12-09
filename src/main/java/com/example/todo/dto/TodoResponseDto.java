package com.example.todo.dto;

import com.example.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private int didnot;
    public TodoResponseDto(Todo todo) {
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.exception = todo.getException();
        this.password = todo.getPassword();
        this.todo = todo.getTodo();
        this.didnot = todo.getDidNot();
    }
}
