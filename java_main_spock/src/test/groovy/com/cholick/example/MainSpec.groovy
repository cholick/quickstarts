package com.cholick.example

import spock.lang.Ignore
import spock.lang.Specification

class MainSpec extends Specification {

    private Collaborator collaboratorMock

    void setup() {
        collaboratorMock = Mock(Collaborator)
    }

    void "foo should return 'foo'"() {
        given:
        Main main = new Main(collaboratorMock)

        expect:
        main.foo() == 'foo'
    }

    void "bar should return 'bar'"() {
        given:
        Main main = new Main(collaboratorMock)

        expect:
        main.bar() == 'bar'
    }

    void "throwsException should throw"() {
        given:
        Main main = new Main(collaboratorMock)

        when:
        main.throwsException()

        then:
        thrown(RuntimeException)
    }

    void "collaborates"() {
        given:
        Main main = new Main(collaboratorMock)

        when:
        main.doCollaboration()

        then:
        1 * collaboratorMock.collaborate()
    }

    @Ignore
    void "ignored"() {
        expect:
        1 == 2
    }
}
