package it.fvaleri.clrs3e.data.advanced;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author fvaleri
 */
public class BTreeTest {
    private BTree cut;

    @Before
    public void setUp() {
        int[] keys = { 2, 1, 3, 8, 10, 5, 12, 11 };
        this.cut = new BTree(keys);
    }

    @Test
    public void search() {
        assertEquals(1, cut.search(11).kIndex);
        assertEquals(null, cut.search(100));
    }

    @Test
    public void insert() {
        cut.insert(4);
        cut.insert(5);
        cut.insert(22);
        //cut.printTree();
        assertTrue(cut.getRoot().nKeys == 1);
        assertEquals(4, cut.getRoot().keys[0]);
        assertEquals(22, cut.getRoot().children[1].children[2].keys[1]);
    }

    @Test
    public void delete() {
        cut.delete(11);
        cut.delete(12);
        cut.delete(3);
        cut.delete(5);
        cut.delete(1);
        cut.delete(10);
        //cut.printTree();
        assertTrue(cut.getRoot().nKeys == 2);
        assertEquals(2, cut.getRoot().keys[0]);
        assertEquals(8, cut.getRoot().keys[1]);
    }
}
