package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getName(), requestDto.getDescription(), requestDto.getPassword(), requestDto.getException(), requestDto.getTodo());

        return todoRepository.saveTodo(todo);
    }

    @Override
    public List<TodoResponseDto> findAllTodo() {
        List<TodoResponseDto> allTodos = todoRepository.findAllTodos();

        return allTodos;

    }
}
