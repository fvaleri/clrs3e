package it.fvaleri.clrs3e.algorithm.basic.ha;

/**
 * We assume an uniform probability ditribution of input.
 * Same inputs lead always to the same results.
 * Average-case cost: O(cost*ln(n))
 * Worst-case cost: O(cost*n)
 *
 * @author fvaleri
 */
public class DeterministicHA implements HAProblem {
    @Override
    public int execute(int[] a) {
        int best = 0;
        int hires = 0; // random variable
        for (int i = 0; i < a.length; i++) {
            if (a[i] > best) {
                best = a[i];
                hires++;
            }
        }
        return hires;
    }
}
