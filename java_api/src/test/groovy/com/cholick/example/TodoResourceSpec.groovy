package com.cholick.example

import spock.lang.Specification

import javax.ws.rs.WebApplicationException

class TodoResourceSpec extends Specification {
    TodoRepository todoRepository
    TodoResource todoResource

    void setup() {
        todoRepository = Mock(TodoRepository)
        todoResource = new TodoResource(todoRepository)
    }

    void "create saves and returns result"() {
        given:
        Todo todo = new Todo("Mow lawn", false)
        Todo savedTodo = new Todo(1, "Mow lawn", false)

        when:
        Todo returnedTodo = todoResource.create(todo)

        then:
        1 * todoRepository.save(todo) >> savedTodo

        and:
        returnedTodo == savedTodo
    }

    void "create throws 422 when id present"() {
        given:
        Todo todo = new Todo(1, "Mow lawn", false)

        when:
        todoResource.create(todo)

        then:
        WebApplicationException ex = thrown(WebApplicationException)
        ex.response.status == 422
    }

    void "update saves and returns result"() {
        given:
        Todo todo = new Todo(1, "Mow lawn", false)
        Todo savedTodo = new Todo(1, "Mow lawn", false)

        when:
        Todo returnedTodo = todoResource.update(1L, todo)

        then:
        1 * todoRepository.save(todo) >> savedTodo

        and:
        returnedTodo == savedTodo
    }

    void "update throws 422 when path id doesn't match content"() {
        given:
        Todo todo = new Todo(1, "Mow lawn", false)

        when:
        todoResource.update(2L, todo)

        then:
        WebApplicationException ex = thrown(WebApplicationException)
        ex.response.status == 422
    }

    void "delete calls repository"() {
        when:
        todoResource.delete(1L)

        then:
        1 * todoRepository.delete(1L)
    }

    void "list returns all todos"() {
        given:
        List<Todo> todos = []

        when:
        List<Todo> returnedTodos = todoResource.list()

        then:
        1 * todoRepository.findAll() >> todos

        and:
        returnedTodos == todos
    }

    void "get returns todo"() {
        given:
        Optional<Todo> savedTodo = Optional.of(new Todo(1, "Mow lawn", false))

        when:
        Todo returnedTodo = todoResource.get(1L)

        then:
        1 * todoRepository.find(1L) >> savedTodo

        and:
        returnedTodo == savedTodo.get()
    }

    void "get throws 404 if not present"() {
        when:
        todoResource.get(1L)

        then:
        1 * todoRepository.find(1L) >> Optional.empty()

        then:
        WebApplicationException ex = thrown(WebApplicationException)
        ex.response.status == 404
    }
}
