package it.fvaleri.clrs3e.data.advanced;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Adjaceny-matrix graph (used for dense graphs).
 * Memory required: O(V^2)
 * addEdge, removeEdge, hasEdge: O(1)
 * outEdge, inEdge: O(n)
 *
 * @author fvaleri
 */
public class AMGraph extends Graph {
    public IntMatrix adjMatrix;

    public AMGraph() {
        this(false);
    }

    public AMGraph(boolean directed) {
        super(directed);
        adjMatrix = new IntMatrix(2, 2);
    }

    public int[][] getRawMatrix() {
        int n = vSize();
        return adjMatrix.getRawMatrix(n, n);
    }

    @Override
    public int eSize() {
        int size = 0;
        int vSize = vSize();
        for (int i = 0; i < vSize; i++) {
            for (int j = 0; j < vSize; j++) {
                if (adjMatrix.get(i, j) > 0) {
                    size++;
                }
            }
        }
        if (!isDirected()) {
            size /= 2;
        }
        return size;
    }

    @Override
    public void addEdge(Vertex u, Vertex v) {
        addEdge(u, v, 1);
    }

    @Override
    public void addEdge(Vertex u, Vertex v, int weight) {
        if (u == null || v == null) {
            return;
        }
        addVertex(u);
        addVertex(v);
        int n = vSize();
        if (adjMatrix.rows < n) {
            adjMatrix.resize(n, n);
        }
        List<Vertex> vertices = getVertices();
        int i = vertices.indexOf(u);
        int j = vertices.indexOf(v);
        if (isDirected()) {
            adjMatrix.set(i, j, weight);
        } else {
            adjMatrix.set(i, j, weight);
            adjMatrix.set(j, i, weight);
        }
    }

    @Override
    public void removeEdge(Vertex u, Vertex v) {
        List<Vertex> vertices = getVertices();
        if (u == null || v == null || !vertices.contains(u) || !vertices.contains(v)) {
            return;
        }
        int i = vertices.indexOf(u);
        int j = vertices.indexOf(v);
        if (isDirected()) {
            adjMatrix.set(i, j, 0);
        } else {
            adjMatrix.set(i, j, 0);
            adjMatrix.set(j, i, 0);
        }
    }

    @Override
    public boolean hasEdge(Vertex u, Vertex v) {
        List<Vertex> vertices = getVertices();
        if (u == null || v == null || !vertices.contains(u) || !vertices.contains(v)) {
            return false;
        }
        int i = vertices.indexOf(u);
        int j = vertices.indexOf(v);
        if (isDirected() && adjMatrix.get(i, j) > 0) {
            return true;
        } else if (!isDirected() && adjMatrix.get(i, j) > 0 && adjMatrix.get(i, j) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Edge> outEdges(Vertex v) {
        List<Vertex> vertices = getVertices();
        int i = vertices.indexOf(v);
        if (v == null || i == -1) {
            return Collections.emptyList();
        }
        List<Edge> edges = new LinkedList<Edge>();
        for (int j = 0; j < vSize(); j++) {
            if (adjMatrix.get(i, j) > 0) {
                edges.add(new Edge(vertices.get(i), vertices.get(j), adjMatrix.get(i, j)));
            }
        }
        return edges;
    }

    @Override
    public List<Edge> inEdges(Vertex v) {
        List<Vertex> vertices = getVertices();
        int i = vertices.indexOf(v);
        if (v == null || i == -1) {
            return Collections.emptyList();
        }
        if (!isDirected()) {
            return outEdges(v);
        } else {
            List<Edge> edges = new LinkedList<Edge>();
            for (int j = 0; j < vSize(); j++) {
                if (adjMatrix.get(j, i) > 0) {
                    edges.add(new Edge(vertices.get(j), vertices.get(i), adjMatrix.get(j, i)));
                }
            }
            return edges;
        }
    }

    @Override
    public void transpose() {
        if (isDirected()) {
            int vSize = vSize();
            for (int i = 0; i < vSize; i++) {
                for (int j = i + 1; j < vSize; j++) {
                    int tmp = adjMatrix.get(i, j);
                    adjMatrix.set(i, j, adjMatrix.get(j, i));
                    adjMatrix.set(j, i, tmp);
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.printf((!isDirected() ? "un" : "") + "directed" + " (%d vertices, %d edges)\n", vSize(), eSize());
        System.out.println("keys: " + getVertices().toString());
        System.out.println(adjMatrix.toString());
    }

    public static class IntMatrix {
        public int rows;
        public int cols;
        private int[] data; // linear storage

        public IntMatrix(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.data = new int[rows * cols];
        }

        private static int getIndex(int row, int col, int width) {
            return row * width + col;
        }

        public int[][] getRawMatrix(int rows, int cols) {
            if (rows > this.rows || cols > this.cols) {
                return null;
            }
            int[][] w = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == j) {
                        w[i][j] = 0;
                    } else {
                        int wij = get(i, j);
                        w[i][j] = (wij == 0) ? Integer.MAX_VALUE : wij;
                    }
                }
            }
            return w;
        }

        public int get(int row, int col) {
            return data[getIndex(row, col, cols)];
        }

        public void set(int row, int col, int value) {
            data[getIndex(row, col, cols)] = value;
        }

        public void resize(int rows, int cols) {
            // values placed at the top-left corner
            int[] newData = new int[cols * rows];
            int colsToCopy = Math.min(cols, this.cols);
            int rowsToCopy = Math.min(rows, this.rows);
            for (int i = 0; i < rowsToCopy; ++i) {
                int oldRowStart = getIndex(i, 0, this.cols);
                int newRowStart = getIndex(i, 0, cols);
                System.arraycopy(data, oldRowStart, newData, newRowStart, colsToCopy);
            }
            this.data = newData;
            this.rows = rows;
            this.cols = cols;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sb.append(get(i, j) + "\t");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
