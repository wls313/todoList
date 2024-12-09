package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);
}
