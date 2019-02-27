package it.fvaleri.clrs3e.algorithm.basic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class MatrixMultiplicationTest {
    private MatrixMultiplication cut;

    @Before
    public void setUp() {
        this.cut = new MatrixMultiplication();
    }

    @Test
    public void test() {
        int[][] a = { { 1, 2, 3 }, { 1, 2, 1 }, { 3, 2, 2 } };

        int[][] b = { { 1, 2 }, { 2, 3 }, { 3, 3 } };

        int[][] axb = { { 14, 17 }, { 8, 11 }, { 13, 18 } };

        assertEquals(Arrays.deepToString(axb), Arrays.deepToString(cut.execute(a, b)));
    }
}
