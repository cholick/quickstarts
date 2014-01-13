package com.cholick

import spock.lang.Specification

class MainSpec extends Specification {

    def "main outputs hello"() {
        given:
        Main main = new Main()
        OutputStream out = new ByteArrayOutputStream()
        PrintStream printStream = new PrintStream(out)

        when:
        main.run(printStream)

        then:
        out.toString().trim() == 'Hello'
    }

}
