package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * Iterative, in-place.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class SelectionSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        for (int j = 0; j < a.length - 1; j++) {
            int smallest = j;
            for (int i = j + 1; i < a.length; i++) {
                if (a[i] < a[smallest]) {
                    smallest = i;
                }
            }
            swap(a, j, smallest);
        }
        return a;
    }
}
