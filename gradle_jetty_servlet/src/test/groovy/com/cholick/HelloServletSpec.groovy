package com.cholick

import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloServletSpec extends Specification {

    def "Servlet response test"() {
        given:
        HttpServletRequest request = Mock(HttpServletRequest)
        request.headerNames >> {
            new Enumeration() {
                boolean hasMoreElements() { false }

                Object nextElement() { return null }
            }
        }

        and:
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        def writer = new PrintWriter(outputStream)
        HttpServletResponse response = Mock(HttpServletResponse)
        response.writer >> { writer }

        when:
        new HelloServlet().doGet(request, response)

        then:
        new ByteArrayInputStream(outputStream.toByteArray()).text == 'Hello'

    }

}
