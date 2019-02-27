package it.fvaleri.clrs3e.algorithm.basic.sort;

/**
 * No-comparison, linear and stable sort.
 * Each element muste be in the range 0 to k.
 * Runtime: O(n)
 *
 * @author fvaleri
 */
public class CountSort extends SortProblem {
    @Override
    public int[] execute(int[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        int[] result = new int[a.length];
        int max = maximum(a);
        int count[] = new int[max + 2];
        for (int j = 0; j < a.length; j++) {
            count[a[j]] = count[a[j]] + 1;
        }
        // count[i] now contains the number of elements equal to i
        for (int i = 1; i <= max; i++) {
            count[i] = count[i] + count[i - 1];
        }
        // count[i] now contains the number of elements less than or equal to i
        for (int j = a.length - 1; j >= 0; j--) {
            result[count[a[j]] - 1] = a[j];
            count[a[j]] = count[a[j]] - 1;
        }
        return result;
    }

    private int maximum(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
