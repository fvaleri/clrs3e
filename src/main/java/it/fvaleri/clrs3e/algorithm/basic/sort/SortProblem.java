package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * @author fvaleri
 */
abstract class SortProblem {
    public abstract int[] execute(int[] a);

    protected void swap(int[] a, int i, int j) {
        if (a != null && i < a.length && j < a.length) {
            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
    }
}
