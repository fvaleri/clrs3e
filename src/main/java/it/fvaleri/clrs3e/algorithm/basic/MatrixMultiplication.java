package it.fvaleri.clrs3e.algorithm.basic;

/**
 * Naive iterative approach.
 * Runtime: O(n^3)
 *
 * @author fvaleri
 */
public class MatrixMultiplication {
    public int[][] execute(int[][] a, int[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bRows = b.length;
        int bCols = b[0].length;
        if (aCols != bRows) {
            throw new IllegalArgumentException("Incompatible dimensions");
        }
        int[][] c = new int[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
