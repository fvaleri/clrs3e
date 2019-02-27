package it.fvaleri.clrs3e.data.basic;

/**
 * Binary max heap.
 *
 * @author fvaleri
 */
public class MaxHeap extends BinaryHeap {
    public MaxHeap(int[] keys) {
        super(keys);
    }

    @Override
    public void heapify(int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);
        if (l < size && keys[l] > keys[i]) {
            largest = l;
        }
        if (r < size && keys[r] > keys[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(keys, i, largest);
            heapify(largest);
        }
    }

    @Override
    protected void buildHeap() {
        // (n/2)..n are all leaves
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }
}
