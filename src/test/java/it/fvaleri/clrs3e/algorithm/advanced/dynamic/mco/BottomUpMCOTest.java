package it.fvaleri.clrs3e.algorithm.advanced.dynamic.mco;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class BottomUpMCOTest {
    private BottomUpMCO cut;

    @Before
    public void setUp() {
        this.cut = new BottomUpMCO();
    }

    @Test
    public void test() {
        int[] dimensions = { 30, 35, 15, 5, 10, 20, 25 };
        assertEquals("((A[1](A[2]A[3]))((A[4]A[5])A[6]))", cut.execute(dimensions));
    }
}
