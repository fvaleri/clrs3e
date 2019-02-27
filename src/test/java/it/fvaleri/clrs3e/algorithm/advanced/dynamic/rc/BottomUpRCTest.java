package it.fvaleri.clrs3e.algorithm.advanced.dynamic.rc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class BottomUpRCTest {
    private BottomUpRC cut;

    @Before
    public void setUp() {
        this.cut = new BottomUpRC();
    }

    @Test
    public void test() {
        int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        int size = 7;
        assertEquals("1,6", cut.execute(prices, size));
    }
}
