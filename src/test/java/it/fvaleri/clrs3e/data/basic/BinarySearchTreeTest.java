package it.fvaleri.clrs3e.data.basic;

import it.fvaleri.clrs3e.data.basic.DynamicSet.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class BinarySearchTreeTest {
    private BinarySearchTree cut;

    @Before
    public void setUp() {
        int[] keys = { 15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9 };
        this.cut = new BinarySearchTree(keys);
    }

    @Test
    public void search() {
        assertEquals(new Element(13), cut.search(13));
        assertEquals(cut.nil, cut.search(1000));
    }

    @Test
    public void insert() {
        cut.insert(new Element(11));
        assertEquals(new Element(11), cut.search(11));
        cut.insert(cut.nil);
        cut.insert(null);
    }

    @Test
    public void delete() {
        cut.delete(cut.search(18));
        assertEquals(cut.nil, cut.search(18));
        cut.delete(cut.nil);
        cut.delete(null);
    }

    @Test
    public void minimum() {
        assertEquals(new Element(2), cut.minimum());
    }

    @Test
    public void maximum() {
        assertEquals(new Element(20), cut.maximum());
    }

    @Test
    public void successor() {
        Element e = cut.search(15);
        assertEquals(new Element(17), cut.successor(e));
    }

    @Test
    public void predecessor() {
        Element e = cut.search(15);
        assertEquals(new Element(13), cut.predecessor(e));
    }

    @Test
    public void toOrderedString() {
        String e = "[2, 3, 4, 6, 7, 9, 13, 15, 17, 18, 20]";
        assertEquals(e, cut.toOrderedString());
    }
}
