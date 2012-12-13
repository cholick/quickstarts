package com.cholick

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.headerNames.each {
            println "${it}: ${request.getHeader(it as String)}"
        }
        response.with {
            contentType = 'text/plain'
            writer << 'Hello'
            writer.flush()
            writer.close()
        }
    }

}
