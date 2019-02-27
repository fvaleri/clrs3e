package it.fvaleri.clrs3e.data.basic;

/**
 * Can be used in an event-driven simulator.
 * Minimum: O(1)
 * Other operations: O(lg n)
 *
 * @author fvaleri
 */
public class MinPriorityQueue {
    private MinHeap minHeap;

    public MinPriorityQueue(int[] keys) {
        this.minHeap = new MinHeap(keys);
    }

    public boolean isEmpty() {
        return minHeap.size == 0;
    }

    public int minimum() {
        return minHeap.keys[0];
    }

    public int extractMin() {
        if (minHeap.size < 1) {
            throw new IllegalArgumentException("The queue is empty");
        }
        int min = minHeap.keys[0];
        minHeap.swap(minHeap.keys, 0, minHeap.size - 1);
        minHeap.size--;
        minHeap.heapify(0);
        return min;
    }

    public void decreaseKey(int i, int key) {
        if (key > minHeap.keys[i]) {
            throw new IllegalArgumentException("New key is greater than current key");
        }
        minHeap.keys[i] = key;
        while (i > 0 && minHeap.keys[minHeap.parent(i)] > minHeap.keys[i]) {
            minHeap.swap(minHeap.keys, i, minHeap.parent(i));
            i = minHeap.parent(i);
        }
    }

    public void insert(int key) {
        if (minHeap.size == minHeap.keys.length) {
            throw new IllegalArgumentException("The queue is full");
        }
        minHeap.size++;
        minHeap.keys[minHeap.size - 1] = Integer.MAX_VALUE;
        decreaseKey(minHeap.size - 1, key);
    }

    @Override
    public String toString() {
        return minHeap.toString();
    }
}
