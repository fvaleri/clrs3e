package it.fvaleri.clrs3e.algorithm.basic.ha;

import it.fvaleri.clrs3e.algorithm.basic.RandomGenerator;

/**
 * We impose an uniform probability distribution of input.
 * No particular input elicits the worst-case behavior.
 * Expected cost: O(cost*ln(n))
 *
 * @author fvaleri
 */
public class RandomizedHA implements HAProblem {
    @Override
    public int execute(int[] a) {
        // randomly permuting the input
        RandomGenerator.randomize(a);
        // executing the algorithm
        int hires = new DeterministicHA().execute(a);
        return hires;
    }
}
