package it.fvaleri.clrs3e.algorithm.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class FibonacciNumberTest {
    private FibonacciNumber cut;

    @Before
    public void setUp() {
        this.cut = new FibonacciNumber();
    }

    @Test
    public void correct() {
        assertEquals(8, cut.execute(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalid() {
        assertEquals(8, cut.execute(-6));
    }
}
