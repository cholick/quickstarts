import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class TestedClass {

    public String thing1() {
        return "foo";
    }

    public String thing2() {
        return "bar";
    }

    @RunWith(JUnit4.class)
    public static class TestHarness {

        @Test
        public void testThing1() {
            Assert.assertEquals("foo", new TestedClass().thing1());
        }

        @Test
        public void testThing2() {
            Assert.assertEquals("bar", new TestedClass().thing2());
        }

        @Test
        public void testFail() {
            Assert.assertEquals(1, 2);
        }

    }

}
