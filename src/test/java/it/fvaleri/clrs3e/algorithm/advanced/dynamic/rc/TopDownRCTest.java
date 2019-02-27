package it.fvaleri.clrs3e.algorithm.advanced.dynamic.rc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class TopDownRCTest {
    private TopDownRC cut;

    @Before
    public void setUp() {
        this.cut = new TopDownRC();
    }

    @Test
    public void test() {
        int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        assertEquals("0", cut.execute(prices, 0));
        assertEquals("10", cut.execute(prices, 4));
        assertEquals("0", cut.execute(prices, prices.length + 1));
    }
}
