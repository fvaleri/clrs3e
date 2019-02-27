package it.fvaleri.clrs3e.algorithm.advanced.dynamic.mco;

/**
 * Optimal paranthesization.
 * Space to store m and s tables: O(n^2)
 * Runtime: O(n^3)
 *
 * @author fvaleri
 */
public class BottomUpMCO implements MCOProblem {
    @Override
    public String execute(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1]; // min scalar mult
        int[][] s = new int[n + 1][n + 1]; // splits
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                // check each possible split to see if it's better than all seen so far
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        // q is the best split for this subproblem so far
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return optimalSolution(s, 1, p.length - 1);
    }

    private String optimalSolution(int[][] s, int i, int j) {
        if (i == j) {
            return "A[" + i + "]";
        } else {
            return "(" + optimalSolution(s, i, s[i][j]) + optimalSolution(s, s[i][j] + 1, j) + ")";
        }
    }
}
