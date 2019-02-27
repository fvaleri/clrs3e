package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.List;

/**
 * Edges can have negative weight (general case).
 * Return true if there aren't negative cycles.
 * Can be used to solve a system of difference constraints.
 * Runtime: O(VE)
 *
 * @author fvaleri
 */
public class BellmanFordSP extends SingleSourceSP {
    @Override
    public boolean execute(Graph g, Vertex s) {
        initialize(g, s);
        List<Edge> edges = g.getEdges();
        for (int i = 1; i <= g.vSize() - 1; i++) {
            for (Edge e : edges) {
                relax(e.source, e.destination, e.weight);
            }
        }
        for (Edge e : edges) {
            if (e.destination.distance > (e.source.distance + e.weight)) {
                return false;
            }
        }
        return true;
    }
}
