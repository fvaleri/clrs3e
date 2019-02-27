package it.fvaleri.clrs3e.algorithm.advanced.graph.sp.ss;

import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class DagLinearSPTest {
    private DagLinearSP cut;

    @Before
    public void setUp() {
        this.cut = new DagLinearSP();
    }

    @Test
    public void test() {
        Vertex r = new Vertex("r");
        Vertex s = new Vertex("s");
        Vertex t = new Vertex("t");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");

        Graph graph = new ALGraph(true);
        graph.addEdge(r, s, 5);
        graph.addEdge(s, t, 2);
        graph.addEdge(t, x, 7);
        graph.addEdge(r, s, 5);
        graph.addEdge(x, y, -1);
        graph.addEdge(y, z, -2);
        graph.addEdge(r, t, 3);
        graph.addEdge(s, x, 6);
        graph.addEdge(t, y, 4);
        graph.addEdge(t, z, 2);
        graph.addEdge(x, z, 1);
        //graph.print();

        cut.execute(graph, s);
        assertEquals("s -> x -> y", graph.pathToString(s, y));
    }
}
