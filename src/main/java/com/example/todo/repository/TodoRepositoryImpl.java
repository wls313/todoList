package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", todo.getName());
        parameters.put("password", todo.getPassword());
        parameters.put("exception", todo.getException());
        parameters.put("description", todo.getDescription());
        parameters.put("todo", todo.getTodo());
        parameters.put("today", now);
        parameters.put("alterDay", now);
        parameters.put("did_not", 0);

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoResponseDto(key.longValue(), todo.getName(), todo.getPassword(), todo.getException(), todo.getException(), todo.getTodo(), todo.getDidNot());
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        return jdbcTemplate.query("select * from todoList", todoRowMapper());
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("exception"),
                        rs.getString("description"),
                        rs.getString("todo"),
                        rs.getInt("did_not")
                );
            }
        };
    }

    @Override
    public Optional<Todo> findTodoById(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todoList where id = ?", memoRowMapperV2(), id);

        return result.stream().findAny();
    }

    private RowMapper<Todo> memoRowMapperV2() {
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("exception"),
                        rs.getString("description"),
                        rs.getString("todo")
                );
            }

        };
    }

    @Override
    public int updateTodo(Long id, String todo, String description, String exception) {
        return jdbcTemplate.update("update todoList set todo = ?, description = ?, exception = ? where id = ?", todo,description, exception, id);
    }


}
