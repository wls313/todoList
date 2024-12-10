package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public TodoResponseDto findTodoById(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findTodoById(id);

        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist name = " + id);
        }

        return new TodoResponseDto(optionalTodo.get());
    }

    @Transactional
    @Override
    public TodoResponseDto updateTodo(Long id, String todo, String description, String exception) {

        // 필수값 검증
        if (todo == null || id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and todo are required values.");
        }

        // memo 수정
        int updatedRow = todoRepository.updateTodo(id, todo, description, exception);
        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        // 수정된 메모 조회
        return new TodoResponseDto(todoRepository.findTodoById(id).get());
    }
}
