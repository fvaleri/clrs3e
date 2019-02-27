package it.fvaleri.clrs3e.algorithm.basic.ha;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class DeterministicHATest {
    private DeterministicHA cut;

    @Before
    public void setUp() {
        this.cut = new DeterministicHA();
    }

    @Test
    public void test() {
        int[] a = { 5, 2, 4, 6, 1, 3 };
        int r = cut.execute(a);
        assertEquals(2, r);
    }
}
