package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class MinPriorityQueueTest {
    private MinPriorityQueue cut;

    @Before
    public void setUp() {
        int[] keys = { 1, 7, 3, 9, 5, 3, 4 };
        this.cut = new MinPriorityQueue(keys);
    }

    @Test
    public void minimum() {
        assertEquals(1, cut.minimum());
    }

    @Test
    public void extractMin() {
        assertEquals(1, cut.extractMin());
        assertEquals("[3, 5, 3, 9, 7, 4]", cut.toString());
    }

    @Test
    public void increaseKey() {
        cut.decreaseKey(5, 2);
        assertEquals("[1, 5, 2, 9, 7, 3, 4]", cut.toString());
    }

    @Test
    public void insert() {
        cut.extractMin();
        cut.insert(2);
        assertEquals("[2, 5, 3, 9, 7, 4, 3]", cut.toString());
    }
}
