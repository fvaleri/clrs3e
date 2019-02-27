package it.fvaleri.clrs3e.algorithm.basic.ms;

/**
 * Maximum Subarray.
 *
 * @author fvaleri
 */
public abstract class MSProblem {
    public abstract int[] execute(int[] a);

    protected int[] buildChanges(int[] a) {
        int[] changes = new int[a.length - 1];
        for (int i = 0; i < a.length - 1; i++) {
            changes[i] = a[i + 1] - a[i];
        }
        return changes;
    }
}
