package it.fvaleri.clrs3e.algorithm.advanced.graph.mf;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

/**
 * Maximum Flow (max-flow min-cut theorem).
 * Assuming a connected directed graph with no self-loops (flow network).
 * Each edge has max non-negative capacity and there is no edge in reverse order.
 *
 * @author fvaleri
 */
public abstract class MFProblem {
    public abstract int execute(Graph g, Vertex s, Vertex t);

    protected int maxFlowValue(Graph g, Vertex t) {
        int f = 0;
        for (Edge e : g.inEdges(t)) {
            f += e.value;
        }
        return f;
    }
}
