package it.fvaleri.clrs3e.data.basic;

import it.fvaleri.clrs3e.data.basic.DynamicSet.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author fvaleri
 */
public class HashTableTest {
    private HashTable cut;

    @Before
    public void setUp() {
        this.cut = new HashTable();
        cut.insert(new Element(15));
        cut.insert(new Element(4));
        cut.insert(new Element(20));
        cut.insert(new Element(8));
        cut.insert(new Element(13));
    }

    @Test
    public void insert() {
        cut.insert(new Element(11));
        assertEquals(new Element(11), cut.search(11));
        cut.insert(cut.nil);
        cut.insert(null);
    }

    @Test
    public void search() {
        assertEquals(new Element(13), cut.search(13));
        assertEquals(cut.nil, cut.search(1000));
        cut = new HashTable();
        assertEquals(cut.nil, cut.search(13));
    }

    @Test
    public void delete() {
        assertEquals(new Element(15), cut.search(15));
        cut.delete(cut.search(15));
        assertEquals(cut.nil, cut.search(15));
        cut.delete(null);
    }

    @Test
    public void minimum() {
        assertEquals(new Element(4), cut.minimum());
        cut = new HashTable();
        assertEquals(cut.nil, cut.minimum());
    }

    @Test
    public void maximum() {
        assertEquals(new Element(20), cut.maximum());
        cut = new HashTable();
        assertEquals(cut.nil, cut.maximum());
    }

    @Test
    public void successor() {
        Element x = cut.search(8);
        assertEquals(new Element(13), cut.successor(x));
        cut = new HashTable();
        assertEquals(cut.nil, cut.successor(x));
    }

    @Test
    public void predecessor() {
        Element x = cut.search(8);
        assertEquals(new Element(4), cut.predecessor(x));
        cut = new HashTable();
        assertEquals(cut.nil, cut.predecessor(x));
    }

    @Test
    public void toOrderedString() {
        String e = "[4, 8, 13, 15, 20]";
        assertEquals(e, cut.toOrderedString());
        cut = new HashTable();
        assertEquals("[]", cut.toOrderedString());
    }
}
