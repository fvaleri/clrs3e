package it.fvaleri.clrs3e.algorithm.basic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class BinarySearchTest {
    @Test
    public void test() {
        int[] a = new int[] { 1, 2, 3, 3, 7, 8, 10 };
        assertEquals(1, BinarySearch.indexOf(a, 2));
        assertEquals(3, BinarySearch.indexOf(a, 3));
        assertEquals(-1, BinarySearch.indexOf(a, 100));
    }
}
