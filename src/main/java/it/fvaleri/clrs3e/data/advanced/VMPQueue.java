package it.fvaleri.clrs3e.data.advanced;

import it.fvaleri.clrs3e.data.advanced.Graph.Vertex;

import java.util.List;

/**
 * Vertex min priority queue.
 * Vertices and corresponding heap elements maintain handles to each other.
 * Extract min and decrease key: O(lg n)
 *
 * @author fvaleri
 */
public class VMPQueue {
    private Vertex[] array;
    private int heapSize;

    public VMPQueue(List<Vertex> vertices) {
        this.array = new Vertex[vertices.size()];
        for (int i = 0; i < array.length; i++) {
            Vertex v = vertices.get(i);
            this.array[i] = v;
            v.index = i;
        }
        this.heapSize = this.array.length;
        buildMinHeap();
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public Vertex extractMin() {
        if (heapSize < 1) {
            throw new IllegalArgumentException("the queue is empty");
        }
        Vertex min = array[0];
        swap(array, 0, heapSize - 1);
        heapSize--;
        heapify(0);
        return min;
    }

    public void decreaseKey(Vertex v, int key) {
        int i = v.index;
        if (key > array[i].key) {
            throw new IllegalArgumentException("New key is greater than current key");
        }
        array[i].key = key;
        while (i > 0 && array[parent(i)].key > array[i].key) {
            swap(array, i, parent(i));
            i = parent(i);
        }
    }

    public boolean hasVertex(Vertex v) {
        for (int i = 0; i < heapSize; i++) {
            if (array[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void swap(Vertex[] array, int i, int j) {
        Vertex vi = array[i];
        Vertex vj = array[j];
        array[i] = vj;
        array[j] = vi;
        vi.index = j;
        vj.index = i;
    }

    private void heapify(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);
        if (l < heapSize && array[l].key < array[smallest].key) {
            smallest = l;
        }
        if (r < heapSize && array[r].key < array[smallest].key) {
            smallest = r;
        }
        if (smallest != i) {
            swap(array, i, smallest);
            heapify(smallest);
        }
    }

    private void buildMinHeap() {
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < heapSize; i++) {
            Vertex v = array[i];
            sb.append(v + "(" + v.index + ", " + v.key + "), ");
        }
        sb.replace(sb.lastIndexOf(", "), sb.length(), "]");
        return sb.toString();
    }
}
