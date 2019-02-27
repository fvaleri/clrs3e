package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * Iterative, in-place.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class InsertionSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int key = a[j];
            int i = j - 1;
            while (i >= 0 && a[i] > key) {
                swap(a, i + 1, i);
                i--;
            }
            a[i + 1] = key;
        }
        return a;
    }
}
