package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ap;

import it.fvaleri.clrs3e.data.advanced.AMGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;

/**
 * Runtime: O(V^3)
 *
 * @author fvaleri
 */
public class FloydWarshallSP extends AllPairsSP {
    @Override
    public int[][] execute(Graph g) {
        int[][] w = ((AMGraph) g).getRawMatrix();
        int n = w[1].length;
        int[][] d = w; // shortest paths estimates
        for (int k = 0; k < n; k++) {
            int[][] dk = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dk[i][j] = (k == 0) ? w[i][j] : d[i][j];
                    if (d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE) {
                        dk[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    }
                }
            }
            d = dk;
        }
        return d;
    }
}
