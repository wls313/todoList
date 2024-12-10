package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    List<TodoResponseDto> findAllTodos();

    Optional<Todo> findTodoById(Long id);

    int updateTodo(Long id, String todo, String description, String exception);
}
