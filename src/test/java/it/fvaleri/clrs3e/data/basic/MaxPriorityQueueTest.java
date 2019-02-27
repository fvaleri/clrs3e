package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class MaxPriorityQueueTest {
    private MaxPriorityQueue cut;

    @Before
    public void setUp() {
        int[] keys = { 1, 7, 3, 9, 5, 3, 4 };
        this.cut = new MaxPriorityQueue(keys);
    }

    @Test
    public void maximum() {
        assertEquals(9, cut.maximum());
    }

    @Test
    public void extractMax() {
        assertEquals(9, cut.extractMax());
        assertEquals("[7, 5, 4, 1, 3, 3]", cut.toString());
    }

    @Test
    public void increaseKey() {
        cut.increaseKey(5, 10);
        assertEquals("[10, 7, 9, 1, 5, 4, 3]", cut.toString());
    }

    @Test
    public void insert() {
        cut.extractMax();
        cut.insert(15);
        assertEquals("[15, 5, 7, 1, 3, 3, 4]", cut.toString());
    }
}
