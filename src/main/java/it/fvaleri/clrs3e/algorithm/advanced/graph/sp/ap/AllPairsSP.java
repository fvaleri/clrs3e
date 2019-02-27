package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ap;

import it.fvaleri.clrs3e.algorithm.advanced.graph.sp.SPProblem;
import it.fvaleri.clrs3e.data.advanced.Graph;

/**
 * All-Pairs (weighted directed graph).
 * Assuming no negative-weight cycles and adj-matrix impl.
 * The subgraph induced by the i-th row of the predecessor
 * matrix should be a shortest-paths tree rooted at i.
 *
 * @author fvaleri
 */
public abstract class AllPairsSP implements SPProblem {
    public abstract int[][] execute(Graph g);

    protected void printMatrix(int[][] m) {
        int n = m[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mij = m[i][j];
                System.out.print(mij == Integer.MAX_VALUE ? "inf\t" : mij + "\t");
            }
            System.out.println();
        }
    }
}
