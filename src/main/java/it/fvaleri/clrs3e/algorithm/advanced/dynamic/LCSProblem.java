package it.fvaleri.clrs3e.algorithm.advanced.dynamic;

/**
 * Longest Common Subsequence (bottom-up).
 * The longer subsequence we can find, the more similar x and y are.
 * Runtime: O(m*n)
 *
 * @author fvaleri
 */
public class LCSProblem {
    public String execute(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] c = new int[m + 1][n + 1]; // LCS lengths
        Pointer[][] b = new Pointer[m + 1][n + 1]; // solution pointers
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = Pointer.TOPLEFT; // LCS entry
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = Pointer.TOP;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = Pointer.LEFT;
                }
            }
        }
        return optimalSolution(b, x, m, n);
    }

    private String optimalSolution(Pointer[][] b, String x, int i, int j) {
        if (i == 0 || j == 0) {
            return "";
        }
        if (b[i][j].equals(Pointer.TOPLEFT)) {
            return optimalSolution(b, x, i - 1, j - 1) + x.charAt(i - 1);
        } else if (b[i][j].equals(Pointer.TOP)) {
            return optimalSolution(b, x, i - 1, j);
        } else {
            return optimalSolution(b, x, i, j - 1);
        }
    }

    public enum Pointer {
        LEFT, TOP, TOPLEFT
    }
}
