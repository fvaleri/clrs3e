package it.fvaleri.clrs3e.algorithm.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * KnuthMorrisPratt string matching.
 * Runtime: O(m+n)
 *
 * @author fvaleri
 */
public class KMPSearch {
    public List<Integer> execute(String t, String p) {
        if (t == null || t.isEmpty() || p == null || p.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        List<Integer> shifts = new ArrayList<Integer>();
        int n = t.length();
        int m = p.length();
        int[] pi = computePrefixFunction(p);
        int q = 0; // number of charachter matched
        for (int i = 0; i < n; i++) { // scan the text from left to right
            while (q > 0 && p.charAt(q) != t.charAt(i)) {
                q = pi[q]; // next character does not match
            }
            if (p.charAt(q) == t.charAt(i)) {
                q = q + 1; // next character matches
            }
            if (q == m) { // is all of P matched?
                shifts.add(i - m + 1);
                q = pi[q]; // look for the next match
            }
        }
        return shifts;
    }

    private int[] computePrefixFunction(String p) {
        int m = p.length();
        int[] pi = new int[m + 1];
        pi[0] = 0;
        //pi[1] = 0;
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && p.charAt(k) != p.charAt(q)) {
                k = pi[k];
            }
            if (p.charAt(k) == p.charAt(q)) {
                k = k + 1;
            }
            pi[q + 1] = k;
        }
        return pi;
    }
}
