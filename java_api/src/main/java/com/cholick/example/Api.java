package com.cholick.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Api extends Application<ApiConfig> {

    public static void main(String[] args) throws Exception {
        new Api().run(args);
    }

    @Override
    public void run(ApiConfig configuration, Environment environment) {
        TodoRepository todoRepository = new TodoRepository();
        TodoResource resource = new TodoResource(todoRepository);

        environment.jersey().register(resource);
    }
}
