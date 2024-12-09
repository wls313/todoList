package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TodoResponseDto findTodoByName(String name) {
        Optional<Todo> optionalTodo = todoRepository.findTodoByName(name);

        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist name = " + name);
        }

        return new TodoResponseDto(optionalTodo.get());
    }
}
