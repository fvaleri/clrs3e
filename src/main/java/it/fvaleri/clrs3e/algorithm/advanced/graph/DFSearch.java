package it.fvaleri.clrs3e.algorithm.advanced.graph;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Depth First Search (greedy).
 * The predecessor subgraph form a forest of trees.
 * A depth-first search on a DAG yields no "back" edges.
 * A strongly connected components graph is a DAG.
 * Each component is a DFS tree.
 * Runtime: O(V+E)
 *
 * @author fvaleri
 */
public class DFSearch {
    private int time;

    public void execute(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        search(g, null, null);
    }

    public List<Vertex> search(Graph g, List<Vertex> ts, List<List<Vertex>> scc) {
        if (g == null || g.vSize() == 0) {
            return Collections.emptyList();
        }
        g.resetState();
        time = 0;
        List<Vertex> vertices = g.getVertices();
        if (scc != null) {
            vertices = ts;
        }
        for (Vertex u : vertices) {
            if (u.color == Color.WHITE) {
                // vertex u becomes the root of a new tree in the forest
                if (scc != null) {
                    ts = new LinkedList<Vertex>();
                }
                visit(g, u, ts);
                if (scc != null) {
                    scc.add(ts);
                }
            }
        }
        return ts;
    }

    private void visit(Graph g, Vertex u, List<Vertex> ts) {
        u.discovered = ++time; // white vertex u has just been discovered
        u.color = Color.GRAY;
        for (Edge uv : g.outEdges(u)) { // explore edge(u, v)
            Vertex v = uv.destination;
            if (v.color == Color.WHITE) {
                v.predecessor = u;
                visit(g, v, ts);
            }
        }
        u.color = Color.BLACK; // blacken u; it's finished
        u.finished = ++time;
        if (ts != null) {
            ts.add(0, u);
        }
    }

    public List<Vertex> topologicalSort(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (g.isDirected()) {
            List<Vertex> ts = new LinkedList<Vertex>();
            search(g, ts, null);
            return ts;
        } else {
            return Collections.emptyList();
        }
    }

    public List<List<Vertex>> stronglyConnectedComps(Graph g) {
        if (g == null || g.vSize() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (g.isDirected()) {
            List<Vertex> ts = topologicalSort(g);
            g.transpose();
            List<List<Vertex>> scc = new LinkedList<List<Vertex>>();
            search(g, ts, scc);
            return scc;
        } else {
            return Collections.emptyList();
        }
    }
}
