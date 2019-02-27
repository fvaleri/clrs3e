package it.fvaleri.clrs3e.data.basic;

/**
 * Nearly complete binary tree. 
 * Heapify: O(lg n) 
 * Build heap: O(n lg n)
 *
 * @author fvaleri
 */
public abstract class BinaryHeap {
    public int[] keys;
    public int size;

    public BinaryHeap(int[] keys) {
        this.keys = keys;
        this.size = keys.length;
        buildHeap();
    }

    protected abstract void heapify(int i);

    protected abstract void buildHeap();

    protected void swap(int[] a, int i, int j) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(keys[i] + ", ");
        }
        sb.replace(sb.lastIndexOf(", "), sb.length(), "]");
        return sb.toString();
    }
}
