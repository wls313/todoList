package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
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
        Todo todo = new Todo(requestDto.getId(), requestDto.getName(), requestDto.getPassword(), requestDto.getDescription(), requestDto.getException(), requestDto.getTodo(),requestDto.getToday());

        return todoRepository.saveTodo(todo);
    }

    @Override
    public List<TodoResponseDto> findAllTodo() {
        List<TodoResponseDto> allTodos = todoRepository.findAllTodos();

        return allTodos;

    }

    @Override
    public TodoResponseDto findTodoById(String id) {
        Optional<Todo> optionalTodo = todoRepository.findTodoById(id);

        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new TodoResponseDto(optionalTodo.get());
    }

    @Override
    public TodoResponseDto findTodoByToday(Date today) {
        Optional<Todo> optionalTodo = todoRepository.findTodoByToday(today);

        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist name = " + today);
        }

        return new TodoResponseDto(optionalTodo.get());
    }

    @Transactional
    @Override
    public TodoResponseDto updateTodo(String id, String todo, String description, String exception) {

        // 필수값 검증
        if (todo == null || id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and todo are required values.");
        }


        int updatedRow = todoRepository.updateTodo(id, todo, description, exception);
        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }


        return new TodoResponseDto(todoRepository.findTodoById(id).get());
    }

    @Override
    public void deleteTodo(String id, String password) {

        int deletedRow = todoRepository.deleteTodo(id,password);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }


}
