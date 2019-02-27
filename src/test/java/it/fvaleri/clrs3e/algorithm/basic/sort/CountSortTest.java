package it.fvaleri.clrs3e.algorithm.basic.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class CountSortTest {
    private CountSort cut;

    @Before
    public void setUp() {
        this.cut = new CountSort();
    }

    @Test
    public void naturals() {
        int[] a = { 31, 41, 134, 26, 41, 58 };
        int[] e = { 26, 31, 41, 41, 58, 134 };
        assertEquals(Arrays.toString(e), Arrays.toString(cut.execute(a)));
    }

    @Test(expected = Exception.class)
    public void integers() {
        int[] a = { -5, 8, 150, 0, -348, -2, 45 };
        assertNull(cut.execute(a));
    }
}
