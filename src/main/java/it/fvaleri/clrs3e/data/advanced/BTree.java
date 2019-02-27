package it.fvaleri.clrs3e.data.advanced;

/**
 * Balanced search tree that minimize disk I/O.
 * Large min degree (t >= 2) yield B-trees with small height.
 * Typcally we choose t so that each node can contain a whole disk
 * page (ex. 2^12 bytes) to read/write as much information as possible.
 * Branching factor (2t) = max number of children at each node.
 * Worst-case height: h <= lg_t((n + 1) / 2)
 * Operations disk-access: O(h)
 * Operations cpu-time: O(th) = O(t lg_t n)
 *
 * @author fvaleri
 */
public class BTree {
    private static final int t = 2;
    private Node root;

    public BTree() {
        buildEmptyTree();
    }

    public BTree(int[] keys) {
        if (keys != null) {
            buildEmptyTree();
            for (int key : keys) {
                insert(key);
            }
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public Node getRoot() {
        return root;
    }

    private void buildEmptyTree() {
        // root can have fewer than t - 1 keys
        root = allocateNode();
        root.leaf = true;
        diskWrite(root);
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("__");
        }
        for (int i = 0; i < node.nKeys; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println();
        if (!node.leaf) {
            depth++;
            for (int i = 0; i <= node.nKeys; i++) {
                printTree(node.children[i], depth);
            }
        }
    }

    private void diskRead(Node x) {
        // disk access to read x
        // no-op if x is already in memory
    }

    private void diskWrite(Node x) {
        // disk access to write x
    }

    private Node allocateNode() {
        return new Node(t);
    }

    private void freeNode(Node x) {
        x = null;
    }

    public Node search(int k) {
        return searchKey(root, k);
    }

    private Node searchKey(Node x, int k) {
        int i = 0;
        while (i < x.nKeys && k > x.keys[i]) {
            i++;
        }
        if (i < x.nKeys && k == x.keys[i]) {
            x.kIndex = i;
            return x;
        } else if (x.leaf) {
            return null;
        } else {
            diskRead(x.children[i]);
            return searchKey(x.children[i], k);
        }
    }

    private void splitChild(Node x, int i) {
        Node z = allocateNode();
        Node y = x.children[i];
        z.leaf = y.leaf;
        z.nKeys = t - 1;
        for (int j = 0; j < (t - 1); j++) {
            z.keys[j] = y.keys[j + t];
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }
        y.nKeys = t - 1;
        for (int j = x.nKeys; j >= i; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;
        for (int j = x.nKeys - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        x.nKeys = x.nKeys + 1;
        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void insert(int k) {
        Node r = root;
        if (root.nKeys == (2 * t - 1)) {
            // root is full (height + 1)
            Node s = allocateNode();
            root = s;
            s.leaf = false;
            s.children[0] = r;
            splitChild(s, 0);
            insertNonFull(s, k);
        } else {
            insertNonFull(r, k);
        }
    }

    private void insertNonFull(Node x, int k) {
        int i = x.nKeys - 1;
        if (x.leaf) {
            // found the right leaf for key insert
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.nKeys = x.nKeys + 1;
            diskWrite(x);
        } else {
            // as we travel down we split every full node we find
            while (i >= 0 && k <= x.keys[i]) {
                i--;
            }
            i++;
            diskRead(x.children[i]);
            if (x.children[i].nKeys == (2 * t - 1)) {
                splitChild(x, i);
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    private int findPredecessorKey(Node x, int i, int k) {
        Node y = x.children[i];
        int j = 0;
        while (j < y.nKeys && k <= y.keys[j]) {
            j++;
        }
        if (j < y.nKeys && !y.leaf) {
            diskRead(y.children[j + 1]);
            return findPredecessorKey(y, j + 1, k);
        } else {
            return y.keys[j];
        }
    }

    private int findSuccessorKey(Node x, int i, int k) {
        Node y = x.children[i + 1];
        int j = x.nKeys - 1;
        while (j >= 0 && k >= y.keys[j]) {
            j--;
        }
        if (j >= 0 && !y.leaf) {
            diskRead(y.children[j]);
            return findSuccessorKey(y, j, k);
        } else {
            return y.keys[j];
        }
    }

    private void mergeChild(Node l, Node x, int i, Node r) {
        l.keys[l.nKeys] = x.keys[i];
        l.nKeys = l.nKeys + 1;
        for (int j = 0; j < r.nKeys; j++) {
            l.keys[l.nKeys + j] = r.keys[j];
        }
        if (!r.leaf) {
            for (int j = 0; j <= r.nKeys; j++) {
                l.children[l.nKeys + j] = r.children[j];
            }
        }
        l.nKeys = l.nKeys + r.nKeys;
        freeNode(r);
        diskWrite(l);
        for (int j = i; j < x.nKeys - 1; j++) {
            x.keys[j] = x.keys[j + 1];
        }
        if (!x.leaf) {
            for (int j = i + 1; j < x.nKeys; j++) {
                x.children[j] = x.children[j + 1];
            }
        }
        x.nKeys = x.nKeys - 1;
        if (x.equals(root) && x.nKeys == 0) {
            // root is empty (height - 1)
            root = x.children[0];
            freeNode(x);
        } else {
            diskWrite(x);
        }
    }

    public void delete(int k) {
        delete(root, k);
    }

    private void delete(Node x, int k) {
        if (root.nKeys == 0) {
            return;
        }
        int kIndex = -1;
        for (int i = 0; i < x.nKeys; i++) {
            if (k == x.keys[i]) {
                // k found in node x
                kIndex = i;
            }
        }
        if (x.leaf) {
            if (kIndex != -1) {
                // x is an leaf node that contains k
                for (int i = kIndex; i < x.nKeys - 1; i++) {
                    x.keys[i] = x.keys[i + 1];
                }
                x.nKeys = x.nKeys - 1;
                diskWrite(x);
            }
        } else {
            if (kIndex != -1) {
                // x is an internal node that contains k
                diskRead(x.children[kIndex]);
                Node y = x.children[kIndex];
                if (y != null && y.nKeys >= t) {
                    // prev child with at least t keys
                    int k1 = findPredecessorKey(x, kIndex, k);
                    delete(y, k1);
                    x.keys[kIndex] = k1;
                    diskWrite(x);
                } else {
                    diskRead(x.children[kIndex + 1]);
                    Node z = x.children[kIndex + 1];
                    if (z != null && z.nKeys >= t) {
                        // next child with at least t keys
                        int k1 = findSuccessorKey(x, kIndex, k);
                        delete(z, k1);
                        x.keys[kIndex] = k1;
                        diskWrite(x);
                    } else if (y != null && z != null && y.nKeys == t - 1 && z.nKeys == t - 1) {
                        // both y and z have only t - 1 keys
                        mergeChild(y, x, kIndex, z);
                        delete(y, k);
                    }
                }
            } else {
                // x is an internal node that doesn't contain k
                int i = x.nKeys - 1;
                while (i >= 0 && k <= x.keys[i]) {
                    i--;
                }
                i++;
                diskRead(x.children[i]);
                Node y = x.children[i];
                // if k is in the tree, y must contain it
                if (y.nKeys == t - 1) {
                    // backup procedure: each node must have at least t keys
                    // to delete in one downward pass without having to merge
                    Node l = null, r = null;
                    if (i - 1 >= 0) {
                        diskRead(x.children[i - 1]);
                        l = x.children[i - 1];
                    }
                    if (i + 1 <= x.nKeys) {
                        diskRead(x.children[i + 1]);
                        r = x.children[i + 1];
                    }
                    if (l != null && l.nKeys == t - 1) {
                        mergeChild(l, x, i - 1, y);
                        y = x;
                    } else if (r != null && r.nKeys == t - 1) {
                        mergeChild(y, x, i, r);
                    } else if (l != null && l.nKeys >= t) {
                        for (int j = y.nKeys - 1; j >= 0; j--) {
                            y.keys[j + 1] = y.keys[j];
                        }
                        y.keys[0] = x.keys[i - 1];
                        y.nKeys = y.nKeys + 1;
                        x.keys[i - 1] = l.keys[l.nKeys - 1];
                        y.children[y.nKeys] = l.children[l.nKeys - 1];
                        l.nKeys = l.nKeys - 1;
                        diskWrite(l);
                        diskWrite(x);
                    } else if (r != null && r.nKeys >= t) {
                        y.keys[y.nKeys - 1] = x.keys[i - 1];
                        y.nKeys = y.nKeys + 1;
                        x.keys[i - 1] = r.keys[0];
                        y.children[y.nKeys] = r.children[0];
                        r.nKeys = r.nKeys - 1;
                        diskWrite(r);
                        diskWrite(x);
                    }
                }
                delete(y, k);
            }
        }
    }

    public static class Node {
        public int nKeys;
        public boolean leaf;
        public int kIndex;
        public int[] keys;
        public Node[] children;

        public Node(int t) {
            this.nKeys = 0;
            this.leaf = false;
            this.kIndex = -1;
            if (t >= 2) {
                this.keys = new int[2 * t - 1];
                this.children = new Node[2 * t];
            }
        }
    }
}
