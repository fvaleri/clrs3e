package it.fvaleri.clrs3e.algorithm.basic;

import java.util.Random;

/**
 * The running time of an operation on a randomized data structure
 * is a random variable and we study its expected value (avg).
 *
 * @author fvaleri
 */
public class RandomGenerator {
    public static int random(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static void randomize(int[] a) {
        if (a != null) {
            int n = a.length;
            for (int i = 0; i < n; i++) {
                swap(a, i, random(i, n - 1));
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (a != null && i < a.length && j < a.length) {
            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
    }
}
