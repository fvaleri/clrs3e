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
public class DFSearchTest {
    private DFSearch cut;

    @Before
    public void setUp() {
        this.cut = new DFSearch();
    }

    @Test
    public void directedGraphDFS() {
        Vertex u = new Vertex("u");
        Vertex v = new Vertex("v");
        Vertex w = new Vertex("w");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");

        Graph graph = new ALGraph(true);
        graph.addEdge(u, v);
        graph.addEdge(u, x);
        graph.addEdge(v, y);
        graph.addEdge(x, v);
        graph.addEdge(y, x);
        graph.addEdge(w, y);
        graph.addEdge(w, z);
        graph.addEdge(z, z);
        //graph.print();

        cut.execute(graph);
        assertEquals(2, v.discovered);
        assertEquals(7, v.finished);
    }

    @Test
    public void topologicalSort() {
        Vertex undershorts = new Vertex("undershorts");
        Vertex pants = new Vertex("pants");
        Vertex belt = new Vertex("belt");
        Vertex shirt = new Vertex("shirt");
        Vertex tie = new Vertex("tie");
        Vertex jacket = new Vertex("jacket");
        Vertex socks = new Vertex("socks");
        Vertex shoes = new Vertex("shoes");
        Vertex watch = new Vertex("watch");

        Graph graph = new ALGraph(true);
        graph.addEdge(undershorts, pants);
        graph.addEdge(pants, belt);
        graph.addEdge(belt, jacket);
        graph.addEdge(shirt, belt);
        graph.addEdge(shirt, tie);
        graph.addEdge(tie, jacket);
        graph.addEdge(socks, shoes);
        graph.addEdge(pants, shoes);
        graph.addEdge(undershorts, shoes);
        graph.addVertex(watch);
        //graph.print();

        assertEquals("[watch, socks, shirt, tie, undershorts, pants, shoes, belt, jacket]",
                cut.topologicalSort(graph).toString());
    }

    @Test
    public void stronglyConnectedComps() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");

        Graph graph = new ALGraph(true);
        graph.addEdge(a, b);
        graph.addEdge(b, e);
        graph.addEdge(e, a);
        graph.addEdge(b, f);
        graph.addEdge(e, f);
        graph.addEdge(f, g);
        graph.addEdge(g, f);
        graph.addEdge(b, c);
        graph.addEdge(c, d);
        graph.addEdge(d, c);
        graph.addEdge(c, g);
        graph.addEdge(d, h);
        graph.addEdge(g, h);
        graph.addEdge(h, h);
        //graph.print();

        assertEquals("[[a, e, b], [c, d], [f, g], [h]]", cut.stronglyConnectedComps(graph).toString());
    }
}
