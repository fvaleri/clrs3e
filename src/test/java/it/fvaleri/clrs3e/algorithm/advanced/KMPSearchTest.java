package it.fvaleri.clrs3e.algorithm.advanced;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class KMPSearchTest {
    private KMPSearch cut;

    @Before
    public void setUp() {
        this.cut = new KMPSearch();
    }

    @Test
    public void validText() {
        assertEquals("[2]", cut.execute("acaabc", "aab").toString());
        assertEquals("[1, 5, 11]", cut.execute("000010001010001", "0001").toString());
        assertEquals("[]", cut.execute("abcdef", "gh").toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyText() {
        cut.execute("", "gh");
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyPattern() {
        cut.execute("abcdef", "");
    }
}
