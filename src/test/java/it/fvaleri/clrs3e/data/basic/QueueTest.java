package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class QueueTest {
    private Queue<Integer> cut;

    @Before
    public void setUp() {
        this.cut = new Queue<Integer>(10);
    }

    @Test
    public void test() {
        assertTrue(cut.isEmpty());
        cut.enqueue(25);
        cut.enqueue(130);
        cut.enqueue(39);
        assertEquals(25, cut.dequeue().intValue());
        cut.enqueue(111);
        assertEquals(130, cut.dequeue().intValue());
        assertEquals(39, cut.dequeue().intValue());
        assertFalse(cut.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void overflow() {
        for (int i = 0; i < 12; i++) {
            cut.enqueue(3);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void underflow() {
        cut.dequeue();
    }
}
