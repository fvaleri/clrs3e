package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * Recursive, in-place.
 * In the avg-case (n lg n) outperforms mergesort due to its good constant factor.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class QuickSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        quickSort(a, 0, a.length - 1);
        return a;
    }

    private void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int pivot = partition(a, start, end);
            quickSort(a, start, pivot - 1);
            quickSort(a, pivot + 1, end);
        }
    }

    protected int partition(int[] a, int start, int end) {
        int x = a[end];
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if (a[j] <= x) {
                i = i + 1;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, end);
        return i + 1;
    }
}
