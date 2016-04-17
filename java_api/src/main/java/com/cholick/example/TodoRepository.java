package com.cholick.example;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TodoRepository {
    private Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private AtomicLong idSequence = new AtomicLong(1);

    public Todo save(Todo todo) {
        if (todo.getId() != null) {
            todos.put(todo.getId(), todo);
            return todo;
        } else {
            Todo savedTodo = new Todo(
                    idSequence.getAndIncrement(), todo.getTitle(), todo.isCompleted()
            );
            todos.put(savedTodo.getId(), savedTodo);
            return savedTodo;
        }
    }

    public void delete(Long id) {
        todos.remove(id);
    }

    public Optional<Todo> find(Long id) {
        return Optional.ofNullable(todos.get(id));
    }

    public Collection<Todo> findAll() {
        return todos.values();
    }
}
