package it.fvaleri.clrs3e.algorithm.advanced.dynamic.mco;

/**
 * Optimal paranthesization.
 * Space to store m and s tables: O(n^2)
 * Runtime: O(n^3)
 *
 * @author fvaleri
 */
public class TopDownMCO implements MCOProblem {
    @Override
    public String execute(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1]; // min scalar mult
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        Integer minScalarMult = lookupChain(m, p, 1, n);
        return minScalarMult.toString();
    }

    private int lookupChain(int[][] m, int[] p, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE) {
            return m[i][j];
        }
        if (i == j) {
            m[i][j] = 0;
        } else {
            for (int k = i; k <= j - 1; k++) {
                int q = lookupChain(m, p, i, k) + lookupChain(m, p, k + 1, j) + p[i - 1] * p[k] * p[j];
                if (q < m[i][j]) {
                    m[i][j] = q;
                }
            }
        }
        return m[i][j];
    }
}
