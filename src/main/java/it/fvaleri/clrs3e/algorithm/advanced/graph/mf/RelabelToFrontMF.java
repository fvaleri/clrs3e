package it.fvaleri.clrs3e.algorithm.advanced.graph.mf;

import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Based on push-relabel method.
 * Preflow: capacity contraint and excess flow.
 * Termination: each internal vertex must have zero excess.
 * Edge.weight = capacity ; Edge.value = flow.
 * Vertex.value = excess ; Vertex.index = height.
 * Runtime: O(V^3)
 *
 * @author fvaleri
 */
public class RelabelToFrontMF extends MFProblem {
    private Graph g;
    private List<Vertex> l;
    private Map<String, List<Vertex>> n;

    @Override
    public int execute(Graph g, Vertex s, Vertex t) {
        if (g == null || g.vSize() < 2) {
            throw new IllegalArgumentException("Invalid input");
        }
        initializeState(g, s, t);
        initializePreflow(s);
        Vertex u = l.get(0);
        while (u != null) {
            int oldHeight = u.index;
            discharge(u);
            if (u.index > oldHeight) {
                // relabeled
                l.remove(u);
                l.add(0, u);
            }
            u = nextInternalVertex(u);
        }
        return maxFlowValue(g, t);
    }

    private int residualCapacity(Vertex u, Vertex v, boolean init) {
        int cf = 0;
        if (g.hasEdge(u, v)) {
            Edge uv = g.getEdge(u, v);
            cf = uv.weight - uv.value;
        } else {
            Edge vu = g.getEdge(v, u);
            if (vu != null) {
                cf = vu.value;
                if (cf == 0 && init) {
                    // may be a residual edge
                    cf = vu.weight;
                }
            }
        }
        return cf;
    }

    private Vertex nextInternalVertex(Vertex u) {
        if (u == null || (l.indexOf(u) + 1) >= l.size()) {
            return null;
        }
        return l.get(l.indexOf(u) + 1);
    }

    private Vertex getNeighborsVertex(Vertex u) {
        if (u == null || u.key >= n.get(u.id).size()) {
            return null;
        }
        return n.get(u.id).get(u.key);
    }

    private void initializeState(Graph g, Vertex s, Vertex t) {
        this.g = g;
        // internals vertices V-{s,t}
        this.l = new LinkedList<Vertex>();
        for (Vertex v : g.getVertices()) {
            if (!v.equals(s) && !v.equals(t)) {
                v.key = 0;
                l.add(v);
            }
        }
        // neighbors residual edges
        this.n = new HashMap<String, List<Vertex>>();
        for (Vertex u : g.getVertices()) {
            List<Vertex> ulist = new LinkedList<Vertex>();
            for (Vertex v : g.getVertices()) {
                if (residualCapacity(u, v, true) > 0) {
                    ulist.add(v);
                }
            }
            n.put(u.id, ulist);
        }
    }

    private void push(Vertex u, Vertex v, int cf) {
        if (cf <= 0 || u.index != v.index + 1) {
            throw new IllegalArgumentException("Inadmissible edge");
        }
        int df = Math.min(u.value, cf);
        if (g.hasEdge(u, v)) {
            Edge uv = g.getEdge(u, v);
            uv.value = uv.value + df;
        } else {
            Edge vu = g.getEdge(v, u);
            vu.value = vu.value - df;
        }
        u.value = u.value - df;
        v.value = v.value + df;
    }

    private void relabel(Vertex u) {
        if (u.value == 0) {
            throw new IllegalArgumentException("Not overflowing");
        }
        int min = Integer.MAX_VALUE;
        for (Vertex v : n.get(u.id)) {
            int cf = residualCapacity(u, v, false);
            if (cf > 0 && v.index < min) {
                min = v.index;
            }
        }
        u.index = min + 1;
    }

    private void initializePreflow(Vertex s) {
        for (Vertex v : g.getVertices()) {
            v.index = 0;
            v.value = 0;
        }
        for (Edge e : g.getEdges()) {
            e.value = 0;
        }
        s.index = g.vSize();
        for (Edge e : g.outEdges(s)) {
            e.value = e.weight;
            e.destination.value = e.weight;
            s.value = s.value - e.weight;
        }
    }

    private void discharge(Vertex u) {
        while (u.value > 0) {
            Vertex v = getNeighborsVertex(u);
            if (v == null) {
                // no more neighbors
                relabel(u);
                u.key = 0;
            } else {
                int cf = residualCapacity(u, v, false);
                if (cf > 0 && u.index == v.index + 1) {
                    // admissible edge
                    push(u, v, cf);
                } else {
                    // inadmissible edge
                    u.key++;
                }
            }
        }
    }
}
