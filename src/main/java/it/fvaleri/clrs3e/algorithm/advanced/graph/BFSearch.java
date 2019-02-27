package it.fvaleri.clrs3e.algorithm.advanced.graph;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex.Color;
import it.fvaleri.clrs3e.data.basic.Queue;

import java.util.LinkedList;
import java.util.List;

/**
 * Breadth First Search (greedy).
 * Compute the shortest path from source to each vertex.
 * Runtime: O(V+E)
 *
 * @author fvaleri
 */
public class BFSearch {
    private Graph g;

    public BFSearch(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.g = g;
    }

    public Graph getGraph() {
        return g;
    }

    public void execute(Vertex s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        g.resetState();
        s.color = Color.GRAY;
        s.distance = 0;
        Queue<Vertex> q = new Queue<Vertex>(g.vSize());
        q.enqueue(s);
        while (!q.isEmpty()) {
            Vertex u = q.dequeue();
            for (Edge uv : g.outEdges(u)) {
                Vertex v = uv.destination;
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.distance = u.distance + 1;
                    v.predecessor = u;
                    q.enqueue(v);
                }
            }
            u.color = Color.BLACK;
        }
    }

    public List<Edge> shortestPath(Vertex u, Vertex v) {
        execute(u);
        return shortestPath(u, v, new LinkedList<Edge>());
    }

    private List<Edge> shortestPath(Vertex u, Vertex v, List<Edge> p) {
        if (v.equals(u) || v.predecessor == null) {
            return p;
        } else {
            Vertex z = v.predecessor;
            for (Edge e : g.outEdges(z)) {
                if (e.destination.equals(v)) {
                    p.add(0, e);
                }
            }
            return shortestPath(u, z, p);
        }
    }
}
