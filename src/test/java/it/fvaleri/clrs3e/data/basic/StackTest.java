package it.fvaleri.clrs3e.data.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author fvaleri
 */
public class StackTest {
    private Stack cut;

    @Before
    public void setUp() {
        this.cut = new Stack(10);
    }

    @Test
    public void test() {
        assertTrue(cut.isEmpty());
        cut.push(25);
        cut.push(130);
        cut.push(39);
        assertEquals(39, cut.pop());
        cut.push(111);
        assertEquals(111, cut.pop());
        assertEquals(130, cut.pop());
        assertFalse(cut.isEmpty());
        cut.multiPop(10);
        assertTrue(cut.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void overflow() {
        for (int i = 0; i < 11; i++) {
            cut.push(3);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void underflow() {
        cut.pop();
    }
}
