package com.example.todo.controller;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable String id) {

        return new ResponseEntity<>(todoService.findTodoById(id), HttpStatus.OK);
    }

    @GetMapping("/today/{today}")
    public ResponseEntity<TodoResponseDto> findTodoByToday(@PathVariable Date today) {

        return new ResponseEntity<>(todoService.findTodoByToday(today), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable String id,
            @RequestBody TodoResponseDto requestDto
    ) {

        return new ResponseEntity<>(todoService.updateTodo(id, requestDto.getTodo(), requestDto.getDescription(),requestDto.getException()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id ,@PathVariable String password) {
        todoService.deleteTodo(id, password);
        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
