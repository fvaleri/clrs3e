package it.fvaleri.clrs3e.algorithm.advanced.graph.mst;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import it.fvaleri.clrs3e.data.advanced.VMPQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Greedy strategy.
 * We can improve the asymptotic running time by using FibonacciHeap.
 * Runtime: O(E lg V)
 *
 * @author fvaleri
 */
public class PrimMST implements MSTProblem {
    @Override
    public List<Edge> execute(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        List<Edge> mst = new LinkedList<Edge>();
        List<Vertex> vertices = g.getVertices();
        Vertex root = vertices.get(0);
        search(g, root);
        for (Vertex v : vertices) {
            if (!v.equals(root)) {
                mst.add(new Edge(v, v.predecessor));
            }
        }
        return mst;
    }

    private void search(Graph g, Vertex root) {
        for (Vertex u : g.getVertices()) {
            u.key = Integer.MAX_VALUE;
            u.predecessor = null;
        }
        root.key = 0;
        VMPQueue q = new VMPQueue(g.getVertices());
        while (!q.isEmpty()) {
            Vertex u = q.extractMin(); // min weight
            for (Edge e : g.outEdges(u)) {
                Vertex v = e.destination;
                if (q.hasVertex(v) && e.weight < v.key) {
                    v.predecessor = u;
                    q.decreaseKey(v, e.weight);
                }
            }
        }
    }
}
