package it.fvaleri.clrs3e.algorithm.advanced.greedy.as;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class IterativeASTest {
    private IterativeAS cut;

    @Before
    public void setUp() {
        this.cut = new IterativeAS();
    }

    @Test
    public void test() {
        int[] s = { 0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
        int[] f = { 0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 };
        assertEquals("1 4 8 11", cut.execute(s, f));
    }
}
