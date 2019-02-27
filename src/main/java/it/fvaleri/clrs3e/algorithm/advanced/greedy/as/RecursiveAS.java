package it.fvaleri.clrs3e.algorithm.advanced.greedy.as;

/**
 * Assuming input in monotonically increasing order.
 * Runtime: O(n)
 *
 * @author fvaleri
 */
public class RecursiveAS implements ASProblem {
    @Override
    public String execute(int[] s, int[] f) {
        return recursiveActivitySelector(s, f, 0, s.length - 1).trim();
    }

    private String recursiveActivitySelector(int[] s, int[] f, int k, int n) {
        int m = k + 1;
        // find the first activity to finish
        while (m <= n && s[m] < f[k]) {
            m = m + 1;
        }
        if (m <= n) {
            return m + " " + recursiveActivitySelector(s, f, m, n);
        } else {
            return "";
        }
    }
}
