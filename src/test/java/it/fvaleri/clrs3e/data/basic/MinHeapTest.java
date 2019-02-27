package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class MinHeapTest {
    private MinHeap cut;

    @Before
    public void setUp() {
        int[] keys = { 1, 7, 3, 9, 5, 3, 4 };
        this.cut = new MinHeap(keys);
    }

    @Test
    public void test() {
        int[] e = { 1, 5, 3, 9, 7, 3, 4 };
        assertEquals(Arrays.toString(e), Arrays.toString(cut.keys));
        assertEquals(1, cut.parent(4));
        assertEquals(3, cut.left(1));
        assertEquals(4, cut.right(1));
    }
}
