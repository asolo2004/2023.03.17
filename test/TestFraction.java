import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFraction {
    @Test
    public void test1() {
        String res = Fraction.calculate("1/2+1/3").toString();
        assertEquals("5/6", res);
    }

    @Test
    public void testZeroFailed() {
        assertThrows(ArithmeticException.class, () -> {
            Fraction.calculate("-1/0 * 3/4 - 1/2");
        });
    }

    @Test
    public void testDoubleMinusFailed() {
        assertThrows(Exception.class, () -> {
            Fraction.calculate("--1/3 * 3/4 - 1/2*2/1");
        });
    }
    @Test
    public void test2() {
        String res = Fraction.calculate("1/2 + 1/3 * 3/4").toString();
        assertEquals("3/4", res);
    }
    @Test
    public void test3() {
        String res = Fraction.calculate("-1/-3 * 3/4 - 1/2").toString();
        assertEquals("-1/4", res);
    }
    @Test
    public void test4() {
        String res = Fraction.calculate("-1/-3 * 3/4 - 1/2 + 2/4").toString();
        assertEquals("1/4", res);
    }
    @Test
    public void test5() {
        String res = Fraction.calculate("-1/3 * 3/4 - 1/2*2/1").toString();
        assertEquals("-5/4", res);
    }
    @Test
    public void test6() {
        assertThrows(Exception.class, () -> {
            Fraction.calculate("-1/3 * 3/4 - 1/2*:2/1");
        });
    }
    @Test
    public void test7() {
        String res = Fraction.calculate("(-1/3 * 3/4 - 1/2)*2/1").toString();
        assertEquals("-3/2", res);
    }
    @Test
    public void test8() {
        String res = Fraction.calculate("((-1/3 * 3/4 - 1/2)*2/1)").toString();
        assertEquals("-3/2", res);
    }
    @Test
    public void test9() {
        String res = Fraction.calculate("(((-1/3 * 3/4 - 1/2)*2/1))").toString();
        assertEquals("-3/2", res);
    }
    @Test
    public void test10() {
        assertThrows(Exception.class, () -> {
            Fraction.calculate("(-1/3 * 3/4 - 1/2)*2/1)");
        });
    }
    @Test
    public void test11() {
        assertThrows(Exception.class, () -> {
            Fraction.calculate("(((-1/3 * 3/4 - 1/2)*2/1)");
        });
    }
    @Test
    public void test12() {
        String res = Fraction.calculate("2/1+3/1*4/1+5/1").toString();
        assertEquals("19/1", res);
    }
}

