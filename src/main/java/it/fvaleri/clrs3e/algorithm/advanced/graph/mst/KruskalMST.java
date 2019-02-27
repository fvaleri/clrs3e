package it.fvaleri.clrs3e.algorithm.advanced.graph.mst;

import it.fvaleri.clrs3e.data.advanced.DisjointSet;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * We can improve the asymptotic running time by using DisjointForest.
 * Runtime: O(E lg V)
 *
 * @author fvaleri
 */
public class KruskalMST implements MSTProblem {
    @Override
    public List<Edge> execute(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        List<Edge> mst = new LinkedList<Edge>();
        DisjointSet<Vertex> forest = new DisjointSet<Vertex>();
        for (Vertex v : g.getVertices()) {
            forest.makeSet(v);
        }
        for (Edge e : sortEdgesByWeightAsc(g)) {
            if (!forest.findSet(e.source).equals(forest.findSet(e.destination))) {
                mst.add(e);
                forest.union(e.source, e.destination);
            }
        }
        return mst;
    }

    private List<Edge> sortEdgesByWeightAsc(Graph g) {
        List<Edge> edges = new LinkedList<Edge>();
        for (Vertex v : g.getVertices()) {
            edges.addAll(g.outEdges(v));
        }
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                Integer aWeight = a.weight;
                Integer bWeight = b.weight;
                return aWeight.compareTo(bWeight);
            }
        });
        return edges;
    }
}
