package it.fvaleri.clrs3e.algorithm.advanced.dynamic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author fvaleri
 */
public class BSTProblemTest {
    private BSTProblem cut;

    @Before
    public void setUp() {
        this.cut = new BSTProblem();
    }

    @Test
    public void test() {
        double[] keysDistrib = { 0.15, 0.10, 0.05, 0.10, 0.20 };
        double[] leafsDistrib = { 0.05, 0.10, 0.05, 0.05, 0.05, 0.10 };
        double expSearchCost = cut.execute(keysDistrib, leafsDistrib);
        assertTrue(2.75 == expSearchCost);
    }
}
