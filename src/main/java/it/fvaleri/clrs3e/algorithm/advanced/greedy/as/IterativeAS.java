package it.fvaleri.clrs3e.algorithm.advanced.greedy.as;

/**
 * Assuming input in monotonically increasing order.
 * Runtime: O(n)
 *
 * @author fvaleri
 */
public class IterativeAS implements ASProblem {
    @Override
    public String execute(int[] s, int[] f) {
        int n = s.length - 1;
        String res = "1";
        int k = 1;
        for (int m = 2; m <= n; m++) {
            if (s[m] >= f[k]) {
                res += " " + m;
                k = m;
            }
        }
        return res.trim();
    }
}
