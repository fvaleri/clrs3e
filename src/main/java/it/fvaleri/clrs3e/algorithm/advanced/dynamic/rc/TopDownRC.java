package it.fvaleri.clrs3e.algorithm.advanced.dynamic.rc;

/**
 * Solving each subproblem just once.
 * Much more efficient then simple recursion that has O(2^n) time.
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class TopDownRC implements RCProblem {
    @Override
    public String execute(int[] p, int n) {
        int[] r = new int[n + 1]; // revenues
        for (int i = 0; i <= n; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        int maxRevenue = memoizedAux(p, n, r);
        return Integer.toString(maxRevenue);
    }

    private int memoizedAux(int[] p, int n, int[] r) {
        // note: p is 0-based
        if (r[n] >= 0) {
            return r[n];
        }
        int q = Integer.MIN_VALUE;
        if (n == 0 || n > p.length) {
            q = 0;
        } else {
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i - 1] + memoizedAux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }
}
