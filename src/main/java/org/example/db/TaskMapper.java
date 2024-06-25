package org.example.db;

import org.example.core.Task;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Task(
                rs.getLong("id"),
                rs.getString("taskName"),
                rs.getString("description"),
                rs.getDate("startDate").toLocalDate(),
                rs.getDate("endDate").toLocalDate(),
                Task.Status.valueOf(rs.getString("status"))
        );
    }
}
