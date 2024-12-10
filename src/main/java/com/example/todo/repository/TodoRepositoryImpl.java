package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
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
        jdbcInsert.withTableName("todoList").usingGeneratedKeyColumns("key");

        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", todo.getId());
        parameters.put("name", todo.getName());
        parameters.put("password", todo.getPassword());
        parameters.put("exception", todo.getException());
        parameters.put("description", todo.getDescription());
        parameters.put("todo", todo.getTodo());
        parameters.put("today", now);
        parameters.put("alterDay", now);
        parameters.put("did_not", 0);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoResponseDto(key.longValue(), todo.getId(), todo.getName(), todo.getPassword(), todo.getException(), todo.getDescription(), todo.getTodo(), todo.getDidNot(), todo.getToday());

    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        return jdbcTemplate.query("select * from todoList ORDER BY today ASC ", todoRowMapper());
    }

    private RowMapper<TodoResponseDto> todoRowMapper() {
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("exception"),
                        rs.getString("description"),
                        rs.getString("todo"),
                        rs.getInt("did_not"),
                        rs.getDate("today")
                );
            }
        };
    }

    @Override
    public Optional<Todo> findTodoById(String id) {
        List<Todo> result = jdbcTemplate.query("select * from todoList where id = ? ORDER BY today ASC ", todoRowMapperV2(), id);

        return result.stream().findAny();
    }

    @Override
    public Optional<Todo> findTodoByToday(Date today) {
        List<Todo> result = jdbcTemplate.query("select * from todoList where today = ? ORDER BY today ASC ", todoRowMapperV2(), today);

        return result.stream().findAny();
    }

    private RowMapper<Todo> todoRowMapperV2() {
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("exception"),
                        rs.getString("description"),
                        rs.getString("todo"),
                        rs.getDate("today")
                );
            }

        };
    }

    @Override
    public int updateTodo(String id, String todo, String description, String exception) {
        return jdbcTemplate.update("update todoList set todo = ?, description = ?, exception = ? ,alterDay = now() where id = ?", todo,description, exception, id);
    }


    @Override
    public int deleteTodo(String id, String password) {

        //db의 password 와 비교
        String dbPassword;
        try {
            dbPassword = jdbcTemplate.queryForObject(
                    "SELECT password FROM todoList WHERE id = ?",
                    new Object[]{id},
                    String.class
            );
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No todo found with the given id");
        }

        //같을경우 삭제 아닌경우 wrong password 라고 출력
        if (dbPassword.equals(password)) {
            return jdbcTemplate.update("delete from todoList where id = ?", id);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }
}
