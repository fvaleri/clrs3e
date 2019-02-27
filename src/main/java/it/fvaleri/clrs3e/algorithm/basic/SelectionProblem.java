package it.fvaleri.clrs3e.algorithm.basic;

import java.util.Random;

/**
 * Average-case: O(n)
 *
 * @author fvaleri
 */
public class SelectionProblem {
    public int execute(int[] a, int i) {
        return randSelect(a, 0, a.length - 1, i);
    }

    private int randSelect(int[] a, int start, int end, int i) {
        if (start == end) {
            return a[start];
        }
        int pivot = randomPartition(a, start, end);
        int k = pivot - start + 1;
        if (i == k) {
            return a[pivot]; // the pivot value is the answer
        } else if (i < k) {
            return randSelect(a, start, pivot - 1, i);
        } else {
            return randSelect(a, pivot + 1, end, i - k);
        }
    }

    private int random(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private void swap(int[] a, int i, int j) {
        if (a != null && i < a.length && j < a.length) {
            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
    }

    private int randomPartition(int[] a, int start, int end) {
        int y = random(start, end);
        swap(a, end, y);
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
