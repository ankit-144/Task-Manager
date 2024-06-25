package org.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Task {
    @JsonProperty
    private long id;

    @JsonProperty
    @NotNull
    private String taskName;

    @JsonProperty
    @NotNull
    private String description;

    @JsonProperty
    @NotNull
    private LocalDate startDate;

    @JsonProperty
    @NotNull
    private LocalDate endDate;

    @JsonProperty
    @NotNull
    private Status status;

    public enum Status {
        TODO,
        WORK_IN_PROGRESS,
        DONE
    }

    // Constructors
    public Task() {
    }

    public Task(long id, String taskName, String description, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
