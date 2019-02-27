package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * Iterative, in-place.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class BubbleSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j - 1, j);
                }
            }
        }
        return a;
    }
}
