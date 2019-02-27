package it.fvaleri.clrs3e.algorithm.advanced.graph.mf;

import it.fvaleri.clrs3e.algorithm.advanced.graph.BFSearch;
import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.List;

/**
 * Based on Ford-Fulkerson method.
 * Flow: capacity constraint and flow conservation.
 * Termination: if no augmenting paths exist, the flow is maximum.
 * Edge.weight = capacity ; Edge.value = flow.
 * Runtime: O(VE^2)
 *
 * @author fvaleri
 */
public class EdmondsKarpMF extends MFProblem {
    @Override
    public int execute(Graph g, Vertex s, Vertex t) {
        if (g == null || g.vSize() < 2) {
            throw new IllegalArgumentException("Invalid input");
        }
        for (Edge e : g.getEdges()) {
            e.value = 0;
        }
        Graph gf = computeResidualNetwork(g);
        List<Edge> p = findAugmentingPath(gf, s, t);
        while (p.size() != 0) {
            int cfp = computePathResCapacity(p);
            for (Edge e : p) {
                if (g.hasEdge(e.source, e.destination)) {
                    Edge uv = g.getEdge(e.source, e.destination);
                    uv.value = uv.value + cfp; // increase
                } else {
                    Edge vu = g.getEdge(e.destination, e.source);
                    vu.value = vu.value - cfp; // cancellation
                }
            }
            gf = computeResidualNetwork(g);
            p = findAugmentingPath(gf, s, t);
        }

        return maxFlowValue(g, t);
    }

    private Graph computeResidualNetwork(Graph g) {
        Graph gf = new ALGraph(true);
        List<Vertex> vertices = g.getVertices();
        for (Vertex u : vertices) {
            for (Vertex v : vertices) {
                if (g.hasEdge(u, v)) {
                    Edge uv = g.getEdge(u, v);
                    int cf = uv.weight - uv.value;
                    if (cf > 0) {
                        gf.addEdge(u, v, cf);
                    }
                } else if (g.hasEdge(v, u)) {
                    Edge vu = g.getEdge(v, u);
                    int cf = vu.value;
                    if (cf > 0) {
                        gf.addEdge(u, v, cf);
                    }
                }
            }
        }
        return gf;
    }

    private List<Edge> findAugmentingPath(Graph g, Vertex s, Vertex t) {
        return new BFSearch(g).shortestPath(s, t);
    }

    private int computePathResCapacity(List<Edge> p) {
        int cfp = Integer.MAX_VALUE;
        for (Edge e : p) {
            if (e.weight < cfp) {
                cfp = e.weight;
            }
        }
        return cfp;
    }
}
