package it.fvaleri.clrs3e.data.basic;

import it.fvaleri.clrs3e.data.basic.DynamicSet.Element.Color;

/**
 * Balanced binary search tree.
 * Ensure that the heigh is small for faster operations.
 * Runtime: O(lg n)
 *
 * @author fvaleri
 */
public class RedBlackTree extends BinarySearchTree {
    public RedBlackTree() {
        super();
    }

    public RedBlackTree(int[] keys) {
        super(keys);
    }

    @Override
    public void insert(Element z) {
        if (z == null || z.equals(nil)) {
            return;
        }
        Element y = nil;
        Element x = root;
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
        z.left = nil;
        z.right = nil;
        z.color = Color.RED;
        insertFixup(z);
    }

    private void insertFixup(Element z) {
        while (z.parent.color == Color.RED) {
            if (z.parent.equals(z.parent.parent.left)) {
                Element y = z.parent.parent.right;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z.equals(z.parent.right)) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Element y = z.parent.parent.left;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z.equals(z.parent.left)) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void leftRotate(Element x) {
        Element y = x.right;
        x.right = y.left;
        if (!y.left.equals(nil)) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent.equals(nil)) {
            root = y;
        } else if (x.equals(x.parent.left)) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Element y) {
        Element x = y.left;
        y.left = x.right;
        if (!x.right.equals(nil)) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent.equals(nil)) {
            root = x;
        } else if (y.equals(y.parent.left)) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    @Override
    public void delete(Element z) {
        if (z == null || z.equals(nil)) {
            return;
        }
        Element y = z, x = nil;
        Color yOriginalColor = y.color;
        if (z.left.equals(nil)) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right.equals(nil)) {
            x = x.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent.equals(z)) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
            if (yOriginalColor == Color.BLACK) {
                deleteFixup(x);
            }
        }
    }

    @Override
    protected void transplant(Element u, Element v) {
        if (u.parent.equals(nil)) {
            root = v;
        } else if (u.equals(u.parent.left)) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteFixup(Element x) {
        while (!x.equals(root) && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Element w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Element w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }
}
