package it.fvaleri.clrs3e.data.basic;

/**
 * LIFO policy.
 * Runtime: O(1)
 * Multi-pop: min(k, size)
 * Amortized cost for n operations: O(n)
 *
 * @author fvaleri
 */
public class Stack {
    private int[] values;
    private int top;

    public Stack(int size) {
        this.values = new int[size];
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1) ? true : false;
    }

    public void push(int x) {
        if (top == values.length - 1) {
            throw new IllegalArgumentException("Stack overflow");
        } else {
            values[++top] = x;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack underflow");
        } else {
            return values[top--];
        }
    }

    public void multiPop(int k) {
        while (!isEmpty() && k > 0) {
            pop();
            k--;
        }
    }
}
