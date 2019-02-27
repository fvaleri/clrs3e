package it.fvaleri.clrs3e.algorithm.advanced.dynamic;

/**
 * Binary Search Tree (bottom-up).
 * We want to build a search tree with minimum expected search cost.
 * Runtime: O(n^3)
 *
 * @author fvaleri
 */
public class BSTProblem {
    public double execute(double[] p, double[] q) {
        int n = p.length;
        double[][] e = new double[n + 2][n + 2]; // expected search cost
        double[][] w = new double[n + 2][n + 2]; // probability sum
        int[][] root = new int[n + 1][n + 1]; // root key index
        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j - 1] + q[j];
                for (int r = i; r <= j; r++) {
                    double t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        return e[1][n];
    }
}
