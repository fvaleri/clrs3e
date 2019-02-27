package it.fvaleri.clrs3e.data.advanced;

import java.util.*;

/**
 * Adjacency-list graph (used for sparse graphs).
 * Memory required: O(V+E)
 * inEdge(dg), transpose: O(V+E)
 * removeEdge, hasEdge: O(deg(v))
 * addEdge, outEdges: O(1)
 *
 * @author fvaleri
 */
public class ALGraph extends Graph {
    private Map<String, List<Edge>> adjLists;

    public ALGraph() {
        this(false);
    }

    public ALGraph(boolean directed) {
        super(directed);
        this.adjLists = new HashMap<String, List<Edge>>();
    }

    @Override
    public int eSize() {
        int size = 0;
        for (String key : adjLists.keySet()) {
            size += adjLists.get(key).size();
        }
        if (!isDirected()) {
            size /= 2;
        }
        return size;
    }

    @Override
    public void addEdge(Vertex u, Vertex v) {
        addEdge(u, v, 0);
    }

    @Override
    public void addEdge(Vertex u, Vertex v, int weight) {
        if (u == null || v == null) {
            return;
        }
        addVertex(u);
        addVertex(v);
        List<Edge> adjU = adjLists.get(u.id);
        if (adjU == null) {
            adjU = new LinkedList<Edge>();
            adjLists.put(u.id, adjU);
        }
        Edge uv = new Edge(u, v, weight);
        if (!adjU.contains(uv)) {
            adjU.add(uv);
        }
        if (!isDirected()) {
            List<Edge> adjV = adjLists.get(v.id);
            if (adjV == null) {
                adjV = new LinkedList<Edge>();
                adjLists.put(v.id, adjV);
            }
            Edge vu = new Edge(v, u, weight);
            if (!adjV.contains(vu)) {
                adjV.add(vu);
            }
        }
    }

    @Override
    public void removeEdge(Vertex u, Vertex v) {
        if (u == null || v == null) {
            return;
        }
        List<Edge> adjU = adjLists.get(u.id);
        Edge uv = new Edge(u, v);
        if (isDirected() && adjU != null) {
            adjU.remove(uv);
        } else {
            List<Edge> adjV = adjLists.get(v.id);
            Edge vu = new Edge(v, u);
            if (!isDirected() && adjU != null && adjV != null) {
                adjU.remove(uv);
                adjV.remove(vu);
            }
        }
    }

    @Override
    public boolean hasEdge(Vertex u, Vertex v) {
        if (u == null || v == null) {
            return false;
        }
        List<Edge> adjU = adjLists.get(u.id);
        Edge uv = new Edge(u, v);
        if (isDirected() && adjU != null && adjU.contains(uv)) {
            return true;
        } else {
            List<Edge> adjV = adjLists.get(v.id);
            Edge vu = new Edge(v, u);
            if (!isDirected() && adjU != null && adjU.contains(uv) && adjV != null && adjV.contains(vu)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Edge> outEdges(Vertex v) {
        if (v == null || !adjLists.containsKey(v.id)) {
            return Collections.emptyList();
        }
        return adjLists.get(v.id);
    }

    @Override
    public List<Edge> inEdges(Vertex v) {
        if (v == null) {
            return Collections.emptyList();
        }
        if (!isDirected()) {
            return outEdges(v);
        } else {
            List<Edge> list = new LinkedList<Edge>();
            for (Vertex u : getVertices()) {
                List<Edge> adjU = adjLists.get(u.id);
                if (adjU != null) {
                    int idx = adjU.indexOf(new Edge(u, v));
                    if (idx != -1) {
                        list.add(adjU.get(idx));
                    }
                }
            }
            return list;
        }
    }

    @Override
    public void transpose() {
        if (isDirected()) {
            Map<String, List<Edge>> tmp = adjLists;
            adjLists = new HashMap<String, List<Edge>>();
            for (Vertex u : getVertices()) {
                List<Edge> adjU = tmp.get(u.id);
                if (adjU != null) {
                    for (Edge uv : adjU) {
                        addEdge(uv.destination, uv.source);
                    }
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.printf((!isDirected() ? "un" : "") + "directed" + " (%d vertices, %d edges)\n", vSize(), eSize());
        for (Vertex u : getVertices()) {
            System.out.print("[" + u.id + "]");
            List<Edge> adjU = adjLists.get(u.id);
            if (adjU != null) {
                for (Edge uv : adjU) {
                    System.out.printf(" -> %s(%d/%d)", uv.destination, uv.value, uv.weight);
                }
            }
            System.out.println();
        }
    }
}
