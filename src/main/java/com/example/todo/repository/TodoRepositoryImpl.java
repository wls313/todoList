package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todoList").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", todo.getName());
        parameters.put("password", todo.getPassword());
        parameters.put("exception", todo.getException());
        parameters.put("description", todo.getDescription());
        parameters.put("todo", todo.getTodo());
        parameters.put("today", todo.getToday()); // 현재 날짜 추가
        parameters.put("did_not", todo.getDidNot());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoResponseDto(key.longValue(), todo.getName(), todo.getPassword(), todo.getException(), todo.getException(), todo.getTodo());
    }
}
