package com.cholick.example

import groovy.json.JsonSlurper
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.DropwizardTestSupport
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response


// export integration=1 to include this spec
@IgnoreIf({!(env['integration'] as Boolean)})
@Stepwise
class IntegrationSpec extends Specification {

    @Shared
    DropwizardTestSupport<ApiConfig> support
    @Shared
    Client client

    void setupSpec() {
        support = new DropwizardTestSupport<ApiConfig>(
                Api.class, null, ConfigOverride.config("server.applicationConnectors[0].port", "9999")
        );
        support.before()
        client = new JerseyClientBuilder(support.getEnvironment()).build("test client");
    }

    void afterSpec() {
        support.after()
    }

    void "create todos"() {
        when:
        Response response = client.target("http://localhost:${support.localPort}/")
                .request()
                .post(Entity.json('{"title": "Mow lawn","completed":false}')
        );

        then:
        response.status == 200

        when:
        response = client.target("http://localhost:${support.localPort}/")
                .request()
                .post(Entity.json('{"title": "Wash dishes","completed":true}')
        );

        then:
        response.status == 200
    }

    void "fetch todos"() {
        when:
        Response response = client.target("http://localhost:${support.localPort}/")
                .request()
                .get()

        then:
        response.status == 200

        when:
        def result = new JsonSlurper().parseText(response.entity.getText())

        then:
        result.size() == 2

        and:
        result[0].title == "Mow lawn"
        result[1].title == "Wash dishes"
    }
}
