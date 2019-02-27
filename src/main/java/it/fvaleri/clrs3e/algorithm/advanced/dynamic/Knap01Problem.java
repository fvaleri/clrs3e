package it.fvaleri.clrs3e.algorithm.advanced.dynamic;

/**
 * 0-1 Knapsack Problem (bottom-up).
 * Assuming input in monotonically increasing order.
 * We can't solve this with a greedy algorithm.
 * Runtime: O(n*m)
 *
 * @author fvaleri
 */
public class Knap01Problem {
    public int execute(int[] v, int[] w, int m) {
        int n = w.length;
        // subproblem values: items are rows and weight (smaller knapsacks) are columns
        int[][] values = new int[n + 1][m + 1];

        // base case1: knapsack has 0 capacity (all zeroes in first column)
        // base case2: there are no items (all zeroes in first row)
        // fill in other values row by row
        for (int item = 1; item <= n; item++) {
            for (int weight = 1; weight <= m; weight++) {
                if (w[item - 1] <= weight) {
                    // take the max value for the same weight without this item
                    int maxValWithoutThisItem = values[item - 1][weight];
                    // take the value of the current item + value that we could accumulate with the remaining weight
                    int currValPlusRemaining = v[item - 1] + values[item - 1][weight - w[item - 1]];
                    values[item][weight] = Math.max(maxValWithoutThisItem, currValPlusRemaining);
                } else {
                    // current item's weight is greater than running weight
                    values[item][weight] = values[item - 1][weight];
                }
            }
        }

        //System.out.println(Arrays.toString(values));
        return values[n][m];
    }
}
