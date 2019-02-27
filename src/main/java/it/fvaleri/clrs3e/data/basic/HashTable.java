package it.fvaleri.clrs3e.data.basic;

/**
 * Chaining technique, multiplication hash.
 * We use a fixed size table for simplicity, but we could use a dynamic table.
 * Load factor (LF) = items stored / table size.
 * Expansion: a commont euristic is to double the size ot the table when full (LF = 1).
 * Contraction: to save space contract the table when LF becomes too small (ex. LF = 1/4).
 * Insert and delete: O(1)
 * Average search time with a good hash: O(1)
 *
 * @author fvaleri
 */
public class HashTable extends DynamicSet {
    private final int powerOf2 = 14;
    private LinkedList[] slots;

    public HashTable() {
        int size = (int) Math.pow(2, powerOf2);
        this.slots = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            slots[i] = new LinkedList();
        }
    }

    private int hash(int key) {
        long s = 2654435769L;
        int shiftAmount = 32 - powerOf2;
        long bitMask = slots.length - 1;
        long ks = key * s;
        int hash = (int) ((ks >> shiftAmount) & bitMask);
        return hash;
    }

    @Override
    public Element search(int key) {
        LinkedList slot = slots[hash(key)];
        return slot.search(key);
    }

    @Override
    public void insert(Element x) {
        if (x == null || x.equals(nil)) {
            return;
        }
        LinkedList slot = slots[hash(x.key)];
        slot.insert(x);
    }

    @Override
    public void delete(Element x) {
        if (x == null || x.equals(nil)) {
            return;
        }
        LinkedList slot = slots[hash(x.key)];
        slot.delete(x);
    }

    @Override
    public Element minimum() {
        Element res = nil;
        for (LinkedList slot : slots) {
            Element x = slot.minimum();
            int resKey = !res.equals(nil) ? res.key : Integer.MAX_VALUE;
            if (!x.equals(nil) && x.key < resKey) {
                res = x;
            }
        }
        return res;
    }

    @Override
    public Element maximum() {
        Element res = nil;
        for (LinkedList slot : slots) {
            Element x = slot.maximum();
            int resKey = !res.equals(nil) ? res.key : Integer.MIN_VALUE;
            if (!x.equals(nil) && x.key > resKey) {
                res = x;
            }
        }
        return res;
    }

    @Override
    public Element successor(Element x) {
        Element res = nil;
        for (LinkedList slot : slots) {
            Element y = slot.successor(x);
            Element z = slot.nil.next;
            int resKey = !res.equals(nil) ? res.key : Integer.MAX_VALUE;
            if (!y.equals(nil) && y.key < resKey) {
                res = y;
            } else if (!z.equals(nil) && z.key > x.key && z.key < resKey) {
                res = z;
            }
        }
        return res;
    }

    @Override
    public Element predecessor(Element x) {
        Element res = nil;
        for (LinkedList slot : slots) {
            Element y = slot.predecessor(x);
            Element z = slot.nil.next;
            int resKey = !res.equals(nil) ? res.key : Integer.MIN_VALUE;
            if (!y.equals(nil) && y.key > resKey) {
                res = y;
            } else if (!z.equals(nil) && z.key < x.key && z.key > resKey) {
                res = z;
            }
        }
        return res;
    }
}
