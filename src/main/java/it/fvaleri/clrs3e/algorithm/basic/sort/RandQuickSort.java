package it.fvaleri.clrs3e.algorithm.basic.sort;

import java.util.Random;

/**
 * Randomized, recursive, in-place.
 * This is the sorting algorithm of choice for large enough inputs.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class RandQuickSort extends QuickSort {
    @Override
    public int[] execute(int[] a) {
        randQuickSort(a, 0, a.length - 1);
        return a;
    }

    private void randQuickSort(int[] a, int start, int end) {
        if (start < end) {
            int pivot = partition(a, start, end);
            randQuickSort(a, start, pivot - 1);
            randQuickSort(a, pivot + 1, end);
        }
    }

    private int random(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    protected int partition(int[] a, int start, int end) {
        int x = random(start, end);
        super.swap(a, end, x);
        return super.partition(a, start, end);
    }
}
