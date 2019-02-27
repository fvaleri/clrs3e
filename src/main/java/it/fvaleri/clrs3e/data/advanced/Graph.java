package it.fvaleri.clrs3e.data.advanced;

import it.fvaleri.clrs3e.data.advanced.Graph.Vertex.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Directed/undirected graph G = (V, E).
 * Applications use DAGs to indicate precedence among events.
 * Sparse graph: E is much less than V^2
 * Dense graph: E is close to V^2
 *
 * @author fvaleri
 */
public abstract class Graph {
    private List<Vertex> vertices;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        vertices = new LinkedList<Vertex>();
    }

    public abstract int eSize();

    public abstract void addEdge(Vertex u, Vertex v);

    public abstract void addEdge(Vertex u, Vertex v, int weight);

    public abstract void removeEdge(Vertex u, Vertex v);

    public abstract boolean hasEdge(Vertex u, Vertex v);

    public abstract List<Edge> outEdges(Vertex v);

    public abstract List<Edge> inEdges(Vertex v);

    public abstract void transpose();

    public abstract void print();

    public boolean isDirected() {
        return directed;
    }

    public int vSize() {
        return vertices.size();
    }

    public Vertex getVertex(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        for (Vertex v : vertices) {
            if (v.id.equals(id)) {
                return v;
            }
        }
        return null;
    }

    public Edge getEdge(Vertex u, Vertex v) {
        if (u == null || v == null) {
            return null;
        }
        for (Edge e : outEdges(u)) {
            if (e.destination.equals(v)) {
                return e;
            }
        }
        return null;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new LinkedList<Edge>();
        for (Vertex v : vertices) {
            edges.addAll(outEdges(v));
        }
        return edges;
    }

    public void addVertex(Vertex v) {
        if (v == null) {
            return;
        }
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void resetState() {
        for (Vertex v : vertices) {
            v.distance = Integer.MAX_VALUE;
            v.color = Color.WHITE;
            v.predecessor = null;
            v.discovered = 0;
            v.finished = 0;
            v.key = Integer.MAX_VALUE;
            v.index = 0;
            v.value = 0;
        }
        for (Edge e : getEdges()) {
            e.value = 0;
        }
    }

    public String pathToString(Vertex u, Vertex v) {
        StringBuffer sb = new StringBuffer();
        if (vSize() == 0 || u == null || v == null) {
            sb.append("invalid input");
        }
        if (v.equals(u)) {
            sb.append(u);
        } else if (v.predecessor == null) {
            sb.append(u);
        } else {
            sb.append(pathToString(u, v.predecessor));
            sb.append(" -> " + v);
        }
        return sb.toString();
    }

    public static class Vertex {
        public String id;

        public int distance;
        public Vertex predecessor;
        public int discovered;
        public int finished;

        public int key;
        public int index;
        public int value;

        public Color color;

        public Vertex(String id) {
            this.id = id;
            this.distance = Integer.MAX_VALUE;
            this.color = Color.WHITE;
            this.key = Integer.MAX_VALUE;
        }

        @Override
        public String toString() {
            return id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!id.equals(((Vertex) obj).id)) {
                return false;
            }
            return true;
        }

        public enum Color {
            WHITE, GRAY, BLACK
        }
    }

    public static class Edge {
        public Vertex source;
        public Vertex destination;
        public int weight;
        public int value;

        public Edge(Vertex source, Vertex destination) {
            this.source = source;
            this.destination = destination;
        }

        public Edge(Vertex source, Vertex destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + source + ", " + destination + ", " + weight + ")";
        }

        @Override
        public int hashCode() {
            return source.id.hashCode() + destination.id.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!source.id.equals(((Edge) obj).source.id) || !destination.id.equals(((Edge) obj).destination.id)) {
                return false;
            }
            return true;
        }
    }
}
