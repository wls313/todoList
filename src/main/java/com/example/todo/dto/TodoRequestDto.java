package com.example.todo.dto;

import lombok.Getter;

import java.sql.Date;

@Getter
public class TodoRequestDto {

    private String id;
    private String name;
    private String password;
    private String exception;
    private String description;
    private String todo;
    private Date today;
}
