package org.example.dao;

import org.example.core.Task;
import org.example.db.TaskMapper;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public interface TaskDAO {
    @SqlQuery("SELECT * FROM tasks")
    @UseRowMapper(TaskMapper.class)
    List<Task> getAllTasks();

    @SqlUpdate("INSERT INTO tasks (taskName, description, startDate, endDate, status) " +
            "VALUES (:taskName, :description, :startDate, :endDate, :status)")
    void insertTask(@Bind("taskName") String taskName, @Bind("description") String description,
                    @Bind("startDate") String startDate, @Bind("endDate") String endDate,
                    @Bind("status") String status);

    @SqlUpdate("UPDATE tasks SET taskName = :taskName, description = :description, " +
            "startDate = :startDate, endDate = :endDate, status = :status WHERE id = :id")
    void updateTask(@Bind("id") long id, @Bind("taskName") String taskName, @Bind("description") String description,
                    @Bind("startDate") String startDate, @Bind("endDate") String endDate,
                    @Bind("status") String status);

    @SqlUpdate("UPDATE tasks SET status = :status WHERE id = :id")
    void updateTaskStatus(@Bind("id") long id, @Bind("status") String status);

    @SqlUpdate("DELETE FROM tasks WHERE id = :id")
    void deleteTask(@Bind("id") long id);
}
