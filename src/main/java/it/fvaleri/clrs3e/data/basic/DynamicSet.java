package it.fvaleri.clrs3e.data.basic;

/**
 * Dictionary.
 * n! = factorial = number of distinct permutations (ordering of n distinct elements)
 * n!/(n!/(k!(n-k)!)) = binomial = n choose k = number of k-subsets of an n-element set
 *
 * @author fvaleri
 */
public abstract class DynamicSet {
    // non-static non-final NIL value that ca be used as sentinel
    public Element nil = new Element(0);

    public abstract Element search(int key);

    public abstract void insert(Element x);

    public abstract void delete(Element x);

    public abstract Element minimum();

    public abstract Element maximum();

    public abstract Element successor(Element x);

    public abstract Element predecessor(Element x);

    public String toOrderedString() {
        StringBuilder sb = new StringBuilder();
        Element x = minimum();
        while (!x.equals(nil)) {
            sb.append(x + ", ");
            x = successor(x);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return "[" + sb.toString().trim() + "]";
    }

    public static class Element {
        public int key;

        public Element prev;
        public Element next;

        public Element parent;
        public Element left;
        public Element right;

        public Color color;

        public Element(int key) {
            this.key = key;
            this.color = Color.BLACK;
            this.prev = this;
            this.next = this;
            this.right = this;
            this.left = this;
        }

        @Override
        public String toString() {
            return "" + key;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (key != ((Element) obj).key) {
                return false;
            }
            return true;
        }

        public enum Color {
            RED, BLACK
        }
    }
}
