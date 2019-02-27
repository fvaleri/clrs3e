package it.fvaleri.clrs3e.algorithm.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class SelectionProblemTest {
    private SelectionProblem cut;

    @Before
    public void setUp() {
        this.cut = new SelectionProblem();
    }

    @Test
    public void test() {
        int[] a = { 1, 7, 3, 0, 5, 3, 4 };
        assertEquals(4, cut.execute(a, 5));
    }
}
