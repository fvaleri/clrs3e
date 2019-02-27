package it.fvaleri.clrs3e.data.basic;

/**
 * FIFO policy.
 * Runtime: O(1)
 *
 * @param <E>
 * @author fvaleri
 */
public class Queue<E> {
    private E[] values;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public Queue(int size) {
        this.values = (E[]) new Object[size];
        this.head = 0;
        this.tail = 0;
    }

    public boolean isEmpty() {
        return (head == tail) ? true : false;
    }

    private int nextHead() {
        return (head == values.length - 1) ? 0 : head + 1;
    }

    private int nextTail() {
        return (tail == values.length - 1) ? 0 : tail + 1;
    }

    public void enqueue(E x) {
        if (head == nextTail()) {
            throw new IllegalArgumentException("Queue overflow");
        } else {
            values[tail] = x;
            tail = nextTail();
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue underflow");
        } else {
            E x = values[head];
            head = nextHead();
            return x;
        }
    }
}
