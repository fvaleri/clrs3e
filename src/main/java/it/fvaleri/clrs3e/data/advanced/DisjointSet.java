package it.fvaleri.clrs3e.data.advanced;

import java.util.LinkedList;
import java.util.List;

/**
 * Linked-list implementation.
 * A sequence of m operations (with n makeSet) takes O(m+n lg n).
 *
 * @param <E>
 * @author fvaleri
 */
public class DisjointSet<E> {
    private List<List<E>> sets;

    public int size() {
        if (sets == null) {
            return 0;
        }
        return sets.size();
    }

    public List<List<E>> getSets() {
        return sets;
    }

    public void makeSet(E v) {
        if (v == null) {
            return;
        }
        if (sets == null) {
            sets = new LinkedList<List<E>>();
        }
        List<E> set = new LinkedList<E>();
        set.add(v);
        sets.add(set);
    }

    public E findSet(E v) {
        if (v == null) {
            return null;
        }
        for (List<E> set : sets) {
            if (set.contains(v)) {
                // representative
                return set.get(0);
            }
        }
        return null;
    }

    public void union(E u, E v) {
        if (u == null || v == null) {
            return;
        }
        List<E> uSet = null;
        for (List<E> set : sets) {
            if (set.contains(u)) {
                uSet = set;
                break;
            }
        }
        List<E> vSet = null;
        for (List<E> set : sets) {
            if (set.contains(v)) {
                vSet = set;
                break;
            }
        }
        if (uSet != null && vSet != null) {
            // weighted-union heuristic
            if (uSet.size() < vSet.size()) {
                vSet.addAll(uSet);
                sets.remove(uSet);
            } else {
                uSet.addAll(vSet);
                sets.remove(vSet);
            }
        }
    }
}
