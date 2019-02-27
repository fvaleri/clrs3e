package it.fvaleri.clrs3e.algorithm.basic.ha;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author fvaleri
 */
public class RandomizedHATest {
    private RandomizedHA cut;

    @Before
    public void setUp() {
        this.cut = new RandomizedHA();
    }

    @Test
    public void test() {
        int[] a = { 5, 2, 4, 6, 1, 3 };
        int r = cut.execute(a);
        assertTrue(r >= 0);
        assertTrue(r <= a.length);
    }
}
