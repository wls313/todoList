package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;

import java.sql.Date;
import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);

    List<TodoResponseDto> findAllTodo();

    TodoResponseDto findTodoById(String id);

    TodoResponseDto updateTodo(String id, String todo, String description, String exception);

    void deleteTodo(String id, String password);

    TodoResponseDto findTodoByToday(Date today);
}