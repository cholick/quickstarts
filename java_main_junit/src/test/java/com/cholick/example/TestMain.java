package com.cholick.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestMain {

    private Main main;

    @Before
    public void setup() {
        main = new Main();
    }

    @Test
    public void testThing1() {
        Assert.assertEquals("foo", main.foo());
    }

    @Test
    public void testThing2() {
        Assert.assertEquals("bar", main.bar());
    }

    @Test(expected = RuntimeException.class)
    public void testThrowsException() {
        main.throwsException();
    }

    @Test
    @Ignore
    public void testFail() {
        Assert.assertEquals(1, 2);
    }
}
