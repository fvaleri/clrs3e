package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ap;

import it.fvaleri.clrs3e.data.advanced.AMGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;

/**
 * Runtime: O(V^3 lg V)
 *
 * @author fvaleri
 */
public class RepeatSquareSP extends AllPairsSP {
    @Override
    public int[][] execute(Graph g) {
        int[][] w = ((AMGraph) g).getRawMatrix();
        int n = w[1].length;
        int[][] l = w; // shortest paths estimates
        int m = 1;
        while (m <= n - 1) {
            l = extendShortestPaths(l, l);
            m = 2 * m;
        }
        return l;
    }

    private int[][] extendShortestPaths(int[][] l, int[][] w) {
        int n = l[0].length;
        int[][] l1 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                l1[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (l[i][k] != Integer.MAX_VALUE && w[k][j] != Integer.MAX_VALUE) {
                        l1[i][j] = Math.min(l1[i][j], l[i][k] + w[k][j]);
                    }
                }
            }
        }
        return l1;
    }
}
