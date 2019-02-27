package it.fvaleri.clrs3e.data.advanced;

/**
 * Collection of rooted trees that are min-heap ordered.
 * Faster than an ordinary binary heaps.
 * Operations amortized time: O(1)
 * ExtractMin and delete: O(lg n)
 * <p>
 * Credits to Nathan L. Fiedler.
 *
 * @author fvaleri
 */
public class FibonacciHeap {
    private Node min; // minimum node
    private int n; // total number of nodes

    public FibonacciHeap() {
    }

    public FibonacciHeap(FibonacciHeap h1, FibonacciHeap h2) {
        union(h1, h2);
    }

    public Node minimum() {
        return min;
    }

    public int size() {
        return n;
    }

    public void insert(Node x) {
        if (x == null) {
            return;
        }
        if (min != null) {
            x.right = min;
            x.left = min.left;
            min.left = x;
            x.left.right = x;
            if (x.key < min.key) {
                min = x;
            }
        } else {
            min = x;
        }
        n++;
    }

    private void union(FibonacciHeap h1, FibonacciHeap h2) {
        if (h1 != null && h2 != null) {
            min = h1.min;
            if (min != null) {
                if (h2.min != null) {
                    min.right.left = h2.min.left;
                    h2.min.left.right = min.right;
                    min.right = h2.min;
                    h2.min.left = min;
                    if (h2.min.key < h1.min.key) {
                        min = h2.min;
                    }
                }
            } else {
                min = h2.min;
            }
            n = h1.n + h2.n;
        }
    }

    public Node extractMin() {
        Node z = min;
        if (z == null) {
            return null;
        }
        if (z.child != null) {
            z.child.parent = null;
            for (Node x = z.child.right; x != z.child; x = x.right) {
                x.parent = null;
            }
            // merge the children into root list
            Node minLeft = min.left;
            Node zChildLeft = z.child.left;
            min.left = zChildLeft;
            zChildLeft.right = min;
            z.child.left = minLeft;
            minLeft.right = z.child;
        }
        // remove z from root list of heap
        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }
        n--;
        return z;
    }

    private void consolidate() {
        // max degree equals to log base phi of Integer.MAX_VALUE
        Node[] a = new Node[45];
        // for each root list node look for others of the same degree
        Node start = min;
        Node w = min;
        do {
            Node x = w;
            // because x might be moved, save its sibling now
            Node nextW = w.right;
            int d = x.degree;
            while (a[d] != null) {
                // make one of the nodes a child of the other
                Node y = a[d];
                if (x.key > y.key) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    // because removeMin() arbitrarily assigned the min
                    // reference, we have to ensure we do not miss the
                    // end of the root node list
                    start = start.right;
                }
                if (y == nextW) {
                    // if we wrapped around we need to check for this case
                    nextW = nextW.right;
                }
                // node y disappears from root list
                y.link(x);
                // we've handled this degree, go to next one
                a[d] = null;
                d++;
            }
            // save this node for later when we might encounter another
            // of the same degree
            a[d] = x;
            // move forward through list
            w = nextW;
        } while (w != start);

        // the node considered to be min may have been changed above
        min = start;
        // find the minimum key again
        for (Node n : a) {
            if (n != null && n.key < min.key) {
                min = n;
            }
        }
    }

    public void decreaseKey(Node x, int k) {
        decreaseKey(x, k, false);
    }

    private void decreaseKey(Node x, int k, boolean delete) {
        if (!delete && k > x.key) {
            throw new IllegalArgumentException("New key is greater than current key");
        }
        x.key = k;
        Node y = x.parent;
        if (y != null && (delete || k < y.key)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }
        if (delete || k < min.key) {
            min = x;
        }
    }

    public void delete(Node x) {
        decreaseKey(x, Integer.MIN_VALUE, true);
        extractMin();
    }

    public static class Node {
        public int key;

        public Node parent;
        public Node left;
        public Node right;

        public Node child;
        public int degree;
        public boolean mark;

        public Node(int key) {
            this.key = key;
            this.right = this;
            this.left = this;
        }

        @Override
        public String toString() {
            return "" + key;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (key != ((Node) obj).key) {
                return false;
            }
            return true;
        }

        public void link(Node parent) {
            left.right = right;
            right.left = left;
            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }
            parent.degree++;
            mark = false;
        }

        public void cascadingCut(Node min) {
            Node z = parent;
            if (z != null) {
                if (mark) {
                    z.cut(this, min);
                    z.cascadingCut(min);
                } else {
                    mark = true;
                }
            }
        }

        public void cut(Node x, Node min) {
            x.left.right = x.right;
            x.right.left = x.left;
            degree--;
            if (degree == 0) {
                child = null;
            } else if (child == x) {
                child = x.right;
            }
            x.right = min;
            x.left = min.left;
            min.left = x;
            x.left.right = x;
            x.parent = null;
            x.mark = false;
        }
    }
}
