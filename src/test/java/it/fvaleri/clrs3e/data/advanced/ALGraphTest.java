package it.fvaleri.clrs3e.data.advanced;

import it.fvaleri.clrs3e.data.advanced.Graph.Edge;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex.Color;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author fvaleri
 */
public class ALGraphTest {
    @Test
    public void buildDirectedGraph() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");

        Graph graph = new ALGraph(true);
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v4);
        graph.addEdge(v2, v5);
        graph.addEdge(v3, v5);
        graph.addEdge(v3, v6);
        graph.addEdge(v4, v2);
        graph.addEdge(v5, v4);
        graph.addEdge(v6, v6);
        //graph.print();

        assertEquals(6, graph.vSize());
        assertEquals(8, graph.eSize());
        assertEquals(Arrays.asList(new Edge(v1, v2), new Edge(v1, v4)), graph.outEdges(v1));
    }

    @Test
    public void buildUndirectedGraph() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");

        Graph graph = new ALGraph();
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v5);
        graph.addEdge(v2, v5);
        graph.addEdge(v2, v4);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v4);
        graph.addEdge(v4, v5);
        //graph.print();

        assertEquals(5, graph.vSize());
        assertEquals(7, graph.eSize());
        assertEquals(Arrays.asList(new Edge(v2, v1), new Edge(v2, v5), new Edge(v2, v4), new Edge(v2, v3)),
                graph.outEdges(v2));
    }

    @Test
    public void buildWeightedUndGraph() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");

        Graph graph = new ALGraph();
        graph.addEdge(v1, v2, 2);
        graph.addEdge(v1, v3, 3);
        graph.addEdge(v3, v2, 5);
        //graph.print();

        assertEquals(3, graph.vSize());
        assertEquals(3, graph.eSize());
        assertEquals(Arrays.asList(new Edge(v1, v2), new Edge(v1, v3)), graph.outEdges(v1));
    }

    @Test
    public void removeEdge() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Graph graph1 = new ALGraph();
        graph1.addEdge(v1, v2);
        assertTrue(graph1.hasEdge(v1, v2));
        assertTrue(graph1.hasEdge(v2, v1));
        graph1.removeEdge(v1, v2);
        assertFalse(graph1.hasEdge(v1, v2));
        assertFalse(graph1.hasEdge(v2, v1));

        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Graph graph2 = new ALGraph(true);
        graph2.addEdge(v3, v4);
        assertTrue(graph2.hasEdge(v3, v4));
        assertFalse(graph2.hasEdge(v4, v3));
        graph2.removeEdge(v3, v4);
        assertFalse(graph2.hasEdge(v3, v4));
        assertFalse(graph2.hasEdge(v4, v3));
    }

    @Test
    public void hasEdge() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Graph graph1 = new ALGraph();
        graph1.addEdge(v1, v2);
        assertTrue(graph1.hasEdge(v1, v2));
        assertTrue(graph1.hasEdge(v2, v1));

        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Graph graph2 = new ALGraph(true);
        graph2.addEdge(v3, v4);
        assertTrue(graph2.hasEdge(v3, v4));
        assertFalse(graph2.hasEdge(v4, v3));
    }

    @Test
    public void resetState() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Graph graph = new ALGraph();
        graph.addEdge(v1, v2);
        v1.color = Color.GRAY;
        v2.predecessor = v1;
        v2.color = Color.GRAY;
        v2.distance = 1;
        v2.key = 23;
        v2.value = 12;
        graph.getEdges().get(0).value = 5;
        graph.resetState();
        assertEquals(v1.color, Color.WHITE);
        assertEquals(null, v2.predecessor);
        assertEquals(v2.color, Color.WHITE);
        assertEquals(v2.distance, Integer.MAX_VALUE);
        assertEquals(v2.key, Integer.MAX_VALUE);
        assertEquals(v2.value, 0);
        assertEquals(0, graph.getEdges().get(0).value);
    }

    @Test
    public void inOutEdges() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Graph graph1 = new ALGraph(true);
        graph1.addVertex(v1);
        graph1.addEdge(v2, v3);
        assertEquals(Arrays.asList(new Edge(v2, v3)), graph1.outEdges(v2));
        assertEquals(Arrays.asList(new Edge(v2, v3)), graph1.inEdges(v3));
        assertEquals(Collections.emptyList(), graph1.outEdges(v3));

        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Graph graph2 = new ALGraph();
        graph2.addEdge(v4, v5);
        graph2.addEdge(v4, v6);
        assertEquals(Arrays.asList(new Edge(v4, v5), new Edge(v4, v6)), graph2.outEdges(v4));
        assertEquals(Arrays.asList(new Edge(v4, v5), new Edge(v4, v6)), graph2.inEdges(v4));
        assertEquals(Arrays.asList(new Edge(v5, v4)), graph2.inEdges(v5));
        assertEquals(Arrays.asList(new Edge(v5, v4)), graph2.outEdges(v5));
    }

    @Test
    public void transpose() {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Graph graph = new ALGraph(true);
        graph.addVertex(v1);
        graph.addEdge(v2, v3);
        //graph.print();
        assertEquals(Arrays.asList(new Edge(v2, v3)), graph.outEdges(v2));
        graph.transpose();
        //graph.print();
        assertEquals(Arrays.asList(new Edge(v3, v2)), graph.outEdges(v3));
    }
}
