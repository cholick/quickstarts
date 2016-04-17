package com.cholick.example

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class TodoSpec extends Specification {

    void "serializes"() {
        given:
        ObjectMapper mapper = new ObjectMapper();

        when:
        String json = mapper.writeValueAsString(
                new Todo(1, "Mow lawn", false)
        )

        then:
        json == '{"id":1,"title":"Mow lawn","completed":false}'
    }

    void "title can't be empty"() {
        given:
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        and:
        Todo todo = new Todo()

        when:
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);

        then:
        violations.size() == 1

        and:
        ConstraintViolation<Todo> violation = violations.iterator().next()
        violation.propertyPath.toString() == "title"
    }
}
