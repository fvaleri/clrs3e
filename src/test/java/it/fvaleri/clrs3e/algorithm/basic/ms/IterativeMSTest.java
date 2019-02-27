package it.fvaleri.clrs3e.algorithm.basic.ms;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class IterativeMSTest {
    private IterativeMS cut;

    @Before
    public void setUp() {
        this.cut = new IterativeMS();
    }

    @Test
    public void test() {
        int[] stockPrices1 = { 100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97 };
        int[] maxProfit1 = { 7, 11 };
        assertEquals(Arrays.toString(maxProfit1), Arrays.toString(cut.execute(stockPrices1)));

        int[] stockPrices2 = { 100, 50, 20, 10, 5 };
        int[] maxProfit2 = new int[0];
        assertEquals(Arrays.toString(maxProfit2), Arrays.toString(cut.execute(stockPrices2)));
    }
}
