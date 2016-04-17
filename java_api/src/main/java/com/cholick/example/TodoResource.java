package com.cholick.example;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private TodoRepository todoRepository;

    public TodoResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @POST
    public Todo create(@Valid Todo todo) {
        if (todo.getId() != null) {
            throw new WebApplicationException("PUT should be used for existing todos", 422);
        }
        return todoRepository.save(todo);
    }

    @PUT
    @Path("{id}")
    public Todo update(@PathParam("id") Long id, @Valid Todo todo) {
        if (todo.getId() == null || !todo.getId().equals(id)) {
            throw new WebApplicationException("Todo data does not math path id", 422);
        } else {
            return todoRepository.save(todo);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        todoRepository.delete(id);
    }

    @GET
    public Collection<Todo> list() {
        return todoRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Todo get(@PathParam("id") Long id) {
        Optional<Todo> todo = todoRepository.find(id);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new WebApplicationException(404);
        }
    }
}
