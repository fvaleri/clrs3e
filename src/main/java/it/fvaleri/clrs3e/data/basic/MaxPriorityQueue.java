package it.fvaleri.clrs3e.data.basic;

/**
 * Can be used to schedule jobs on a shared computer.
 * Maximum: O(1)
 * Other operations: O(lg n)
 *
 * @author fvaleri
 */
public class MaxPriorityQueue {
    private MaxHeap maxHeap;

    public MaxPriorityQueue(int[] keys) {
        this.maxHeap = new MaxHeap(keys);
    }

    public boolean isEmpty() {
        return maxHeap.size == 0;
    }

    public int maximum() {
        return maxHeap.keys[0];
    }

    public int extractMax() {
        if (maxHeap.size < 1) {
            throw new IllegalArgumentException("The queue is empty");
        }
        int max = maxHeap.keys[0];
        maxHeap.swap(maxHeap.keys, 0, maxHeap.size - 1);
        maxHeap.size--;
        maxHeap.heapify(0);
        return max;
    }

    public void increaseKey(int i, int key) {
        if (key < maxHeap.keys[i]) {
            throw new IllegalArgumentException("New key is smaller than current key");
        }
        maxHeap.keys[i] = key;
        while (i > 0 && maxHeap.keys[maxHeap.parent(i)] < maxHeap.keys[i]) {
            maxHeap.swap(maxHeap.keys, i, maxHeap.parent(i));
            i = maxHeap.parent(i);
        }
    }

    public void insert(int key) {
        if (maxHeap.size == maxHeap.keys.length) {
            throw new IllegalArgumentException("The queue is full");
        }
        maxHeap.size++;
        maxHeap.keys[maxHeap.size - 1] = Integer.MIN_VALUE;
        increaseKey(maxHeap.size - 1, key);
    }

    @Override
    public String toString() {
        return maxHeap.toString();
    }
}
