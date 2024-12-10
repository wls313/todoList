package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);

    List<TodoResponseDto> findAllTodo();

    TodoResponseDto findTodoById(Long id);

    TodoResponseDto updateTodo(Long id, String todo, String description, String exception);

    void deleteTodo(Long id);
}