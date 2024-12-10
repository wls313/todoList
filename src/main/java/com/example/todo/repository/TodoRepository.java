package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    List<TodoResponseDto> findAllTodos();

    Optional<Todo> findTodoById(String id);

    int updateTodo(String id, String todo, String description, String exception);

    int deleteTodo(String id, String password);

    Optional<Todo> findTodoByToday(Date today);
}
