package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss;

import it.fvaleri.clrs3e.algorithm.advanced.graph.DFSearch;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.List;

/**
 * Only with directed acyclic graphs.
 * Runtime: O(V+E)
 *
 * @author fvaleri
 */
public class DagLinearSP extends SingleSourceSP {
    @Override
    public boolean execute(Graph g, Vertex s) {
        List<Vertex> topSort = new DFSearch().topologicalSort(g);
        initialize(g, s);
        for (Vertex u : topSort) {
            for (Edge e : g.outEdges(u)) {
                relax(u, e.destination, e.weight);
            }
        }
        return true;
    }
}
