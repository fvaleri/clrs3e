package it.fvaleri.clrs3e.algorithm.basic.ms;

/**
 * Recursive (divide and conquer).
 * Runtime: O(n lg n)
 *
 * @author fvaleri
 */
public class RecursiveMS extends MSProblem {
    @Override
    public int[] execute(int[] a) {
        int[] changes = buildChanges(a);
        int[] result = findMaximumSubarray(changes, 0, changes.length - 1);
        if (result[0] != -1 && result[1] != -1) {
            return new int[] { result[0], result[1] + 1 };
        } else {
            return new int[0];
        }
    }

    private int[] findMaximumSubarray(int[] a, int low, int high) {
        if (high == low) {
            // base case with only one element
            return new int[] { low, high, a[low] };
        } else {
            // divide into disjoint subproblems
            int mid = (low + high) / 2;
            // conquer/solve subproblems recursively
            int[] leftSubarray = findMaximumSubarray(a, low, mid);
            int[] rightSubarray = findMaximumSubarray(a, mid + 1, high);
            // merge subproblem solutions
            int[] crossingSubarray = findMaxCrossingSubarray(a, low, mid, high);
            if (leftSubarray[2] >= rightSubarray[2] && leftSubarray[2] >= crossingSubarray[2]) {
                return leftSubarray;
            } else if (rightSubarray[2] >= leftSubarray[2] && rightSubarray[2] >= crossingSubarray[2]) {
                return rightSubarray;
            } else {
                return crossingSubarray;
            }
        }
    }

    private int[] findMaxCrossingSubarray(int[] a, int low, int mid, int high) {
        int leftIndex = -1;
        int rightIndex = -1;
        int leftSum = 0;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += a[i];
            if (sum > leftSum) {
                leftSum = sum;
                leftIndex = i;
            }
        }
        int rightSum = 0;
        sum = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum += a[j];
            if (sum > rightSum) {
                rightSum = sum;
                rightIndex = j;
            }
        }
        return new int[] { leftIndex, rightIndex, leftSum + rightSum };
    }
}
