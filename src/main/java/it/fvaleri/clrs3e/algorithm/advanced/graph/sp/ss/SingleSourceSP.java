package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss;

import it.fvaleri.clrs3e.algorithm.advanced.graph.sp.SPProblem;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

/**
 * Single-Source (weighted directed graph).
 * Assuming no negative-weight cycles reachable from the source and adj-list impl.
 * The predecessor subgraph is a shortest-paths tree rooted at source.
 *
 * @author fvaleri
 */
public abstract class SingleSourceSP implements SPProblem {
    public abstract boolean execute(Graph g, Vertex s);

    protected void initialize(Graph g, Vertex s) {
        for (Vertex v : g.getVertices()) {
            v.distance = Integer.MAX_VALUE;
            if (v.equals(s)) {
                s.distance = 0;
            }
            v.predecessor = null;
            v.key = v.distance;
        }
    }

    protected boolean relax(Vertex u, Vertex v, int w) {
        if (u == null || v == null) {
            return false;
        }
        int sum = (u.distance == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (u.distance + w);
        if (v.distance > sum) {
            v.distance = sum;
            v.predecessor = u;
            return true;
        }
        return false;
    }
}
