package it.fvaleri.clrs3e.data.basic;

/**
 * Circular doubly-linked list.
 * Insert and delete: O(1)
 * Search: O(n)
 *
 * @author fvaleri
 */
public class LinkedList extends DynamicSet {
    @Override
    public void insert(Element x) {
        if (x == null || x.equals(nil)) {
            return;
        }
        x.next = nil.next;
        nil.next.prev = x;
        nil.next = x;
        x.prev = nil;
    }

    @Override
    public Element search(int key) {
        Element x = nil.next;
        while (!x.equals(nil) && x.key != key) {
            x = x.next;
        }
        return x;
    }

    @Override
    public void delete(Element x) {
        if (x == null || x.equals(nil)) {
            return;
        }
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    @Override
    public Element minimum() {
        Element res = nil.next;
        Element x = nil.next;
        while (!x.equals(nil)) {
            if (x.key < res.key) {
                res = x;
            }
            x = x.next;
        }
        return res;
    }

    @Override
    public Element maximum() {
        Element res = nil.next;
        Element x = nil.next;
        while (!x.equals(nil)) {
            if (x.key > res.key) {
                res = x;
            }
            x = x.next;
        }
        return res;
    }

    @Override
    public Element successor(Element x) {
        if (nil.prev.equals(nil) && nil.next.equals(nil)) {
            return nil;
        }
        Element res = nil;
        Element y = x.next;
        while (y != x) {
            int resKey = !res.equals(nil) ? res.key : Integer.MAX_VALUE;
            if (!y.equals(nil) && y.key > x.key && y.key < resKey) {
                res = y;
            }
            y = y.next;
        }
        return res;
    }

    @Override
    public Element predecessor(Element x) {
        if (nil.prev.equals(nil) && nil.next.equals(nil)) {
            return nil;
        }
        Element res = nil;
        Element y = x.next;
        while (y != x) {
            int resKey = !res.equals(nil) ? res.key : Integer.MIN_VALUE;
            if (!y.equals(nil) && y.key < x.key && y.key > resKey) {
                res = y;
            }
            y = y.next;
        }
        return res;
    }
}
