package it.fvaleri.clrs3e.algorithm.advanced.graph.mf;

import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class RelabelToFrontMFTest {
    private RelabelToFrontMF cut;

    @Before
    public void setUp() {
        this.cut = new RelabelToFrontMF();
    }

    @Test
    public void test() {
        Vertex s = new Vertex("s");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");
        Vertex t = new Vertex("t");

        Graph graph = new ALGraph(true);
        graph.addEdge(s, x, 12);
        graph.addEdge(s, y, 14);
        graph.addEdge(x, y, 5);
        graph.addEdge(y, z, 8);
        graph.addEdge(z, t, 10);
        graph.addEdge(z, x, 7);
        graph.addEdge(x, t, 16);
        //graph.print();

        assertEquals(20, cut.execute(graph, s, t));
    }
}
