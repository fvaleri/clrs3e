package it.fvaleri.clrs3e.data.advanced;

import it.fvaleri.clrs3e.data.advanced.FibonacciHeap.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class FibonacciHeapTest {
    private FibonacciHeap cut;

    @Before
    public void setUp() {
        this.cut = new FibonacciHeap();
        cut.insert(new Node(23));
        cut.insert(new Node(7));
        cut.insert(new Node(21));
        cut.insert(new Node(3));
        cut.insert(new Node(18));
        cut.insert(new Node(39));
        cut.insert(new Node(52));
        cut.insert(new Node(38));
    }

    @Test
    public void insert() {
        cut.insert(new Node(2));
        cut.insert(new Node(100));
        assertEquals(10, cut.size());
        assertEquals(new Node(2), cut.minimum());
    }

    @Test
    public void union() {
        FibonacciHeap h1 = new FibonacciHeap();
        h1.insert(new Node(23));
        h1.insert(new Node(100));
        h1.insert(new Node(12));
        FibonacciHeap h2 = new FibonacciHeap();
        h2.insert(new Node(2));
        h2.insert(new Node(5));
        cut = new FibonacciHeap(h1, h2);
        assertEquals(5, cut.size());
        assertEquals(new Node(2), cut.minimum());
    }

    @Test
    public void extractMin() {
        assertEquals(new Node(3), cut.extractMin());
        assertEquals(7, cut.size());
    }

    @Test
    public void decreaseKey() {
        Node n = new Node(10);
        cut.insert(n);
        cut.decreaseKey(n, 2);
        assertEquals(new Node(2), cut.extractMin());
    }

    @Test
    public void delete() {
        cut.delete(new Node(7));
        cut.delete(new Node(18));
        assertEquals(6, cut.size());
    }
}
