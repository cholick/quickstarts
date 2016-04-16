package com.cholick.example;

public class Main {

    private Collaborator collaborator;

    public Main(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public String foo() {
        return "foo";
    }

    public String bar() {
        return "bar";
    }

    public void throwsException() {
        throw new RuntimeException("");
    }

    public void doCollaboration() {
        this.collaborator.collaborate();
    }
}
