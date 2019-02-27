package it.fvaleri.clrs3e.algorithm.advanced.dynamic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class LCSProblemTest {
    private LCSProblem cut;

    @Before
    public void setUp() {
        this.cut = new LCSProblem();
    }

    @Test
    public void test() {
        String x = "ABCBDAB";
        String y = "BDCABA";
        assertEquals("BCBA", cut.execute(x, y));
    }
}
