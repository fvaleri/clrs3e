package it.fvaleri.clrs3e.algorithm.basic;

/**
 * Naive recursive approach.
 * Recursive algorithms are best candidates for multithreading.
 * Runtime: O(phi^n)
 *
 * @author fvaleri
 */
public class FibonacciNumber {
    public int execute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (n <= 1) {
            return n;
        } else {
            int x = /* spawn */ execute(n - 1);
            int y = execute(n - 2);
            /* sync */
            return x + y;
        }
    }
}
