package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ap;

import it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss.BellmanFordSP;
import it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss.DijkstraSP;
import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.List;

/**
 * Uses Bellman-Ford and Dijkstra as subroutines (adj-list impl).
 * Can be faster if we use FibonacciHeap instead of BinaryMinHeap.
 * Runtime: O(VE log V)
 *
 * @author fvaleri
 */
public class JohnsonSP extends AllPairsSP {
    @Override
    public int[][] execute(Graph g) {
        Vertex s = new Vertex("0");
        Graph g1 = extendInputGraph(g, s);
        reweightInputGraph(g, s, g1);
        return computeShortestPaths(g);
    }

    private Graph extendInputGraph(Graph g, Vertex s) {
        Graph g1 = new ALGraph(g.isDirected());
        for (Edge e : g.getEdges()) {
            g1.addEdge(e.source, e.destination, e.weight);
        }
        for (Vertex v : g.getVertices()) {
            g1.addEdge(s, v, 0);
        }
        return g1;
    }

    private void reweightInputGraph(Graph g, Vertex s, Graph g1) {
        if (new BellmanFordSP().execute(g1, s) == false) {
            throw new IllegalArgumentException("The input graph contains a negative-weight cycle");
        } else {
            for (Vertex v : g.getVertices()) {
                v.value = pathToLength(g1, s, v, 0);
            }
            for (Edge e : g.getEdges()) {
                Vertex u = e.source;
                Vertex v = e.destination;
                e.weight = e.weight + u.value - v.value;
            }
        }
    }

    private int pathToLength(Graph g, Vertex u, Vertex v, int len) {
        if (v.equals(u) || v.predecessor == null) {
            return len;
        } else {
            Vertex z = v.predecessor;
            for (Edge e : g.outEdges(z)) {
                if (e.destination.equals(v)) {
                    len += e.weight;
                }
            }
            return pathToLength(g, u, z, len);
        }
    }

    private int[][] computeShortestPaths(Graph g) {
        int n = g.vSize();
        int[][] d = new int[n][n];
        List<Vertex> vertices = g.getVertices();
        for (int i = 0; i < n; i++) {
            Vertex u = vertices.get(i);
            new DijkstraSP().execute(g, u);
            for (int j = 0; j < n; j++) {
                Vertex v = vertices.get(j);
                d[i][j] = pathToLength(g, u, v, 0) + v.value - u.value;
            }
        }
        return d;
    }
}
