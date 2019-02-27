package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * Recursive, not-in-place.
 * Asymptotically optimal.
 * Runtime: O(n lg n)
 *
 * @author fvaleri
 */
public class MergeSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        mergeSort(a, 0, a.length - 1);
        return a;
    }

    private void mergeSort(int[] a, int start, int end) {
        if (start < end) {
            int pivot = (start + end) / 2;
            // divide
            mergeSort(a, start, pivot);
            mergeSort(a, pivot + 1, end);
            // conquer and combine
            merge(a, start, pivot, end);
        }
    }

    private void merge(int[] a, int start, int pivot, int end) {
        int n1 = pivot - start + 1;
        int n2 = end - pivot;
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            left[i] = a[start + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = a[pivot + 1 + j];
        }
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = start; k <= end; k++) {
            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
        }
    }
}
