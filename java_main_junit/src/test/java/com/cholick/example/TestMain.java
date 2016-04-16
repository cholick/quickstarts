package com.cholick.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class TestMain {

    private Main main;
    private Collaborator collaboratorMock;

    @Before
    public void setup() {
        collaboratorMock = Mockito.mock(Collaborator.class);
        main = new Main(collaboratorMock);
    }

    @Test
    public void testFoo() {
        Assert.assertEquals("foo", main.foo());
    }

    @Test
    public void testBar() {
        Assert.assertEquals("bar", main.bar());
    }

    @Test(expected = RuntimeException.class)
    public void testThrowsException() {
        main.throwsException();
    }

    @Test
    public void collaborates() {
        main.doCollaboration();

        Mockito.verify(collaboratorMock, Mockito.times(1)).collaborate();
    }

    @Test
    @Ignore
    public void testFail() {
        Assert.assertEquals(1, 2);
    }
}
