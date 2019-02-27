package it.fvaleri.clrs3e.algorithm.advanced.graph.mst;

import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class PrimMSTTest {
    private PrimMST cut;

    @Before
    public void setUp() {
        this.cut = new PrimMST();
    }

    @Test
    public void test() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        Vertex i = new Vertex("i");

        Graph graph = new ALGraph();
        graph.addEdge(a, b, 4);
        graph.addEdge(a, h, 8);
        graph.addEdge(b, h, 11);
        graph.addEdge(b, c, 8);
        graph.addEdge(h, i, 7);
        graph.addEdge(h, g, 1);
        graph.addEdge(i, c, 2);
        graph.addEdge(i, g, 6);
        graph.addEdge(c, d, 7);
        graph.addEdge(c, f, 4);
        graph.addEdge(g, f, 2);
        graph.addEdge(d, e, 9);
        graph.addEdge(d, f, 14);
        graph.addEdge(e, f, 10);
        // graph.print();

        assertEquals("[(b, a, 0), (h, a, 0), (c, f, 0), (i, c, 0), (g, h, 0), (d, c, 0), (f, g, 0), (e, d, 0)]",
                cut.execute(graph).toString());
    }
}
