package com.example.todo.controller;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping // 요청
    public ResponseEntity<TodoResponseDto> createMemo(@RequestBody TodoRequestDto requestDto) {
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(todoService.saveTodo(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TodoResponseDto> findAllTodo() {

        return todoService.findAllTodo();
    }

    @GetMapping("/{name}")
    public ResponseEntity<TodoResponseDto> findTodoByName(@PathVariable String name) {

        return new ResponseEntity<>(todoService.findTodoByName(name), HttpStatus.OK);
    }

}
