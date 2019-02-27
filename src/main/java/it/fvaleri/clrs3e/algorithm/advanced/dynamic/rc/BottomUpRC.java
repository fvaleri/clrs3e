package it.fvaleri.clrs3e.algorithm.advanced.dynamic.rc;

/**
 * Solving each subproblem just once.
 * Much more efficient than simple recursion that is O(2^n).
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class BottomUpRC implements RCProblem {
    @Override
    public String execute(int[] p, int n) {
        int[] r = new int[n + 1]; // revenues
        int[] s = new int[n + 1]; // lengths
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                if (q < (p[i - 1] + r[j - i])) {
                    q = p[i - 1] + r[j - i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        // building optimal solution
        return optimalSolution(n, s);
    }

    private String optimalSolution(int n, int[] s) {
        StringBuilder bestCuts = new StringBuilder();
        while (n > 0) {
            bestCuts.append(s[n]);
            n = n - s[n];
            if (n > 0) {
                bestCuts.append(",");
            }
        }
        return bestCuts.toString();
    }
}
