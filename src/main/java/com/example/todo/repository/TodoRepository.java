package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);
}
