package com.cholick.example

import spock.lang.Specification

class TodoRepositorySpec extends Specification {
    TodoRepository todoRepository

    void setup() {
        todoRepository = new TodoRepository()
    }

    void "save with id"() {
        when:
        todoRepository.save(new Todo(42L, "Mow lawn", true))

        and:
        Optional<Todo> todoOpt = todoRepository.find(42L)

        then:
        todoOpt.present

        and:
        todoOpt.get().id == 42L
        todoOpt.get().title == "Mow lawn"
        todoOpt.get().completed
    }

    void "save without id"() {
        when:
        Todo todo = todoRepository.save(new Todo("Mow lawn", true))

        then:
        todo.id == 1L

        when:
        Optional<Todo> todoOpt = todoRepository.find(1L)

        then:
        todoOpt.present

        and:
        todoOpt.get().id == 1L
        todoOpt.get().title == "Mow lawn"
        todoOpt.get().completed
    }

    void "save grants incrementing ids"() {
        when:
        Todo todo1 = todoRepository.save(new Todo("Mow lawn", true))
        Todo todo2 = todoRepository.save(new Todo("Mow lawn", true))
        Todo todo3 = todoRepository.save(new Todo("Mow lawn", true))

        then:
        todo1.id == 1L
        todo2.id == 2L
        todo3.id == 3L

        when:
        Optional<Todo> todo1Opt = todoRepository.find(1L)
        Optional<Todo> todo2Opt = todoRepository.find(2L)
        Optional<Todo> todo3Opt = todoRepository.find(3L)

        then:
        todo1Opt.present
        todo2Opt.present
        todo3Opt.present
    }

    void "findAll returns all todos"() {
        when:
        (1..3).each {
            todoRepository.save(new Todo("Mow lawn", true))
        }

        and:
        Collection<Todo> todos = todoRepository.findAll()

        then:
        todos.size() == 3

        and:
        todos.find { it.id == 1L }
        todos.find { it.id == 2L }
        todos.find { it.id == 3L }
    }

    void "delete removes todo"() {
        given:
        todoRepository.save(new Todo(42L, "Mow lawn", true))

        and:
        Optional<Todo> todoOpt = todoRepository.find(42L)

        expect:
        todoOpt.present
        todoRepository.findAll().size() == 1

        when:
        todoRepository.delete(42L)

        and:
        todoOpt = todoRepository.find(42L)

        then:
        !todoOpt.present
        todoRepository.findAll().size() == 0
    }
}
