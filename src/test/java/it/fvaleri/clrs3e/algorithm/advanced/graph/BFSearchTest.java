package it.fvaleri.clrs3e.algorithm.advanced.graph;

import it.fvaleri.clrs3e.data.advanced.ALGraph;
import it.fvaleri.clrs3e.data.advanced.Graph;
import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class BFSearchTest {
    private BFSearch cut;

    @Before
    public void setUp() {
        Vertex r = new Vertex("r");
        Vertex s = new Vertex("s");
        Vertex t = new Vertex("t");
        Vertex u = new Vertex("u");
        Vertex v = new Vertex("v");
        Vertex w = new Vertex("w");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");

        Graph graph = new ALGraph();
        graph.addEdge(r, s);
        graph.addEdge(r, v);
        graph.addEdge(s, w);
        graph.addEdge(w, t);
        graph.addEdge(w, x);
        graph.addEdge(t, x);
        graph.addEdge(t, u);
        graph.addEdge(x, u);
        graph.addEdge(x, y);
        graph.addEdge(u, y);
        this.cut = new BFSearch(graph);
        //graph.print();
    }

    @Test
    public void execute() {
        Graph g = cut.getGraph();
        Vertex s = g.getVertex("s");
        Vertex y = g.getVertex("y");
        cut.execute(s);
        assertEquals("s -> w -> x -> y", g.pathToString(s, y));
    }

    @Test
    public void shortestPath() {
        Graph g = cut.getGraph();
        Vertex s = g.getVertex("s");
        Vertex y = g.getVertex("y");
        assertEquals("[(s, w, 0), (w, x, 0), (x, y, 0)]", cut.shortestPath(s, y).toString());

        g.resetState();
        Vertex z = new Vertex("z");
        g.addVertex(z);
        assertEquals("[]", cut.shortestPath(s, z).toString());
    }

    @Test
    public void noPath() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");

        Graph graph = new ALGraph(true);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(d, b);
        graph.addEdge(d, c);
        //graph.print();

        this.cut = new BFSearch(graph);
        assertEquals("[]", cut.shortestPath(a, d).toString());
    }
}
