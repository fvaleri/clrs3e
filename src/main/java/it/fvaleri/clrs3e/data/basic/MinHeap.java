package it.fvaleri.clrs3e.data.basic;

/**
 * Binary min heap.
 *
 * @author fvaleri
 */
public class MinHeap extends BinaryHeap {
    public MinHeap(int[] keys) {
        super(keys);
    }

    @Override
    public void heapify(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);
        if (l < size && keys[l] < keys[smallest]) {
            smallest = l;
        }
        if (r < size && keys[r] < keys[smallest]) {
            smallest = r;
        }
        if (smallest != i) {
            swap(keys, i, smallest);
            heapify(smallest);
        }
    }

    @Override
    protected void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }
}
