package it.fvaleri.clrs3e.algorithm.advanced.greedy;

import it.fvaleri.clrs3e.algorithm.advanced.greedy.HuffmanCode.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class HuffmanCodeTest {
    private HuffmanCode cut;

    @Before
    public void setUp() {
        Character[] c = new Character[6];
        c[0] = new Character('a', 45);
        c[1] = new Character('b', 13);
        c[2] = new Character('c', 12);
        c[3] = new Character('d', 16);
        c[4] = new Character('e', 9);
        c[5] = new Character('f', 5);
        this.cut = new HuffmanCode(c);
    }

    @Test
    public void test() {
        assertEquals(100, cut.execute().freq);
    }
}
