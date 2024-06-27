package org.example.resources;

import com.codahale.metrics.annotation.Timed;
import org.example.core.Task;
import org.example.dao.TaskDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final TaskDAO taskDAO;

    public TaskResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GET
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTask(Task task) {
        taskDAO.insertTask(task.getTaskName(), task.getDescription(), task.getStartDate().toString(),
                task.getEndDate().toString(), task.getStatus().toString());
    }*/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(Task task) {
        try {
            taskDAO.insertTask(task.getTaskName(), task.getDescription(), task.getStartDate().toString(),
                    task.getEndDate().toString(), task.getStatus().toString());
            // Optionally, you can return a response with a success message or the created task ID
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            // Handle any exceptions or errors and return an appropriate error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating task").build();
        }
    }

    /*@PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask(@PathParam("id") long id, Task task) {
        taskDAO.updateTask(id, task.getTaskName(), task.getDescription(), task.getStartDate().toString(),
                task.getEndDate().toString(), task.getStatus().toString());

    }*/
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(@PathParam("id") long id, Task task) {
        boolean updateSuccessful = taskDAO.updateTask(id, task.getTaskName(), task.getDescription(),
                task.getStartDate().toString(), task.getEndDate().toString(), task.getStatus().toString());

        if (updateSuccessful) {
            return Response.ok().entity("Task updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Task not found").build();
        }
    }

    /*@PUT
    @Path("/{id}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTaskStatus(@PathParam("id") long id, Task task) {
        taskDAO.updateTaskStatus(id, task.getStatus().toString());
    }*/
    @PUT
    @Path("/{id}/status")
    public Response updateTaskStatus(@PathParam("id") long id, Task task) {
        boolean updateSuccessful = taskDAO.updateTaskStatus(id, task.getStatus().toString());

        if (updateSuccessful) {
            return Response.ok().entity("Task status updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Task not found").build();
        }
    }


    @DELETE
    @Path("/{id}")
    public void deleteTask(@PathParam("id") long id) {
        taskDAO.deleteTask(id);
    }
}
