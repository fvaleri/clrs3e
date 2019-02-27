package it.fvaleri.clrs3e.data.basic;

import it.fvaleri.clrs3e.algorithm.basic.RandomGenerator;

/**
 * Much faster than linear search that takes O(n).
 * For any node x, the keys in the left subtree are at most x.key,
 * and the keys in the right subtree are at least x.key.
 * Basic operations take time proportional to the height.
 * Average-case: O(lg n)
 * Runtime: O(n)
 *
 * @author fvaleri
 */
public class BinarySearchTree extends DynamicSet {
    protected Element root;

    public BinarySearchTree() {
        this.root = nil;
    }

    public BinarySearchTree(int[] keys) {
        this.root = nil;
        if (keys != null && keys.length > 0) {
            // randomize input to have a balanced tree
            RandomGenerator.randomize(keys);
            for (int key : keys) {
                Element x = new Element(key);
                x.parent = nil;
                x.left = nil;
                x.right = nil;
                insert(x);
            }
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    @Override
    public Element search(int key) {
        if (root.equals(nil)) {
            return null;
        }
        Element x = root;
        while (!x.equals(nil) && key != x.key) {
            if (key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    @Override
    public void insert(Element z) {
        if (z == null || z.equals(nil)) {
            return;
        }
        Element y = nil, x = root;
        while (!x.equals(nil)) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y.equals(nil)) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    @Override
    public void delete(Element z) {
        if (z == null || z.equals(nil)) {
            return;
        }
        if (z.left.equals(nil)) {
            transplant(z, z.right);
        } else if (z.right.equals(nil)) {
            transplant(z, z.left);
        } else {
            Element y = minimum(z.right);
            if (!y.parent.equals(z)) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    protected void transplant(Element u, Element v) {
        if (u.parent.equals(nil)) {
            root = v;
        } else if (u.equals(u.parent.left)) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != nil) {
            v.parent = u.parent;
        }
    }

    @Override
    public Element minimum() {
        return minimum(root);
    }

    protected Element minimum(Element z) {
        if (z == null || z.equals(nil)) {
            return nil;
        }
        while (!z.left.equals(nil)) {
            z = z.left;
        }
        return z;
    }

    @Override
    public Element maximum() {
        return maximum(root);
    }

    protected Element maximum(Element z) {
        if (z == null || z.equals(nil)) {
            return nil;
        }
        while (!z.right.equals(nil)) {
            z = z.right;
        }
        return z;
    }

    @Override
    public Element successor(Element z) {
        if (z == null || z.equals(nil)) {
            return nil;
        }
        if (!z.right.equals(nil)) {
            return minimum(z.right);
        }
        Element x = z.parent;
        while (!x.equals(nil) && z.equals(x.right)) {
            z = x;
            x = x.parent;
        }
        return x;
    }

    @Override
    public Element predecessor(Element z) {
        if (z == null || z.equals(nil)) {
            return nil;
        }
        if (!z.left.equals(nil)) {
            return maximum(z.left);
        }
        Element x = z.parent;
        while (!x.equals(nil) && z.equals(x.left)) {
            z = x;
            x = x.parent;
        }
        return x;
    }
}
