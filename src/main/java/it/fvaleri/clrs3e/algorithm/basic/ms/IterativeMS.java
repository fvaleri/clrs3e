package it.fvaleri.clrs3e.algorithm.basic.ms;

/**
 * Iterative (brute force).
 * Runtime: O(n^2)
 *
 * @author fvaleri
 */
public class IterativeMS extends MSProblem {
    @Override
    public int[] execute(int[] a) {
        int[] changes = buildChanges(a);
        int sum = 0;
        int leftIndex = -1;
        int rightIndex = -1;
        for (int j = 0; j < changes.length - 1; j++) {
            int tmpSum = changes[j];
            for (int z = j + 1; z < changes.length; z++) {
                tmpSum += changes[z];
                if (tmpSum > sum) {
                    sum = tmpSum;
                    leftIndex = j;
                    rightIndex = z;
                }
            }
        }
        if (leftIndex != -1 && rightIndex != -1) {
            return new int[] { leftIndex, rightIndex + 1 };
        } else {
            return new int[0];
        }
    }
}
