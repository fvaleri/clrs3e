package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class MaxHeapTest {
    private MaxHeap cut;

    @Before
    public void setUp() {
        int[] keys = { 1, 7, 3, 9, 5, 3, 4 };
        this.cut = new MaxHeap(keys);
    }

    @Test
    public void test() {
        int[] e = { 9, 7, 4, 1, 5, 3, 3 };
        assertEquals(Arrays.toString(e), Arrays.toString(cut.keys));
        assertEquals(1, cut.parent(4));
        assertEquals(3, cut.left(1));
        assertEquals(4, cut.right(1));
    }
}
