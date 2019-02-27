package it.fvaleri.clrs3e.algorithm.advanced.greedy;

import it.fvaleri.clrs3e.data.basic.MinPriorityQueue;

import java.util.Arrays;

/**
 * The resulting binary tree represents the coding scheme (charset).
 * Codeword = simple path from the root to that character.
 * We consider only prefix-codes.
 * Runtime: O(n lg n)
 *
 * @author fvaleri
 */
public class HuffmanCode {
    private Character[] chars;
    private MinPriorityQueue freqs;

    public HuffmanCode(Character[] c) {
        this.chars = c;
    }

    public Character execute() {
        int n = chars.length;
        freqs = new MinPriorityQueue(getKeyset());
        for (int i = 0; i < n - 1; i++) {
            // merging the two least-frequent nodes
            Character z = new Character();
            Character x = getCharacter(freqs.extractMin());
            Character y = getCharacter(freqs.extractMin());
            z.left = x;
            z.right = y;
            z.freq = x.freq + y.freq;
            freqs.insert(z.freq);
            chars = Arrays.copyOf(chars, chars.length + 1);
            chars[chars.length - 1] = z;
        }
        return getCharacter(freqs.extractMin());
    }

    private int[] getKeyset() {
        int[] a = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            a[i] = chars[i].freq;
        }
        return a;
    }

    private Character getCharacter(int key) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i].freq == key) {
                return chars[i];
            }
        }
        return null;
    }

    public static class Character {
        public char value;
        public int freq;
        public Character left;
        public Character right;

        public Character() {
        }

        public Character(char value, int freq) {
            this.value = value;
            this.freq = freq;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Char [value=").append(value).append(", freq=").append(freq).append(", left=").append(left)
                    .append(", right=").append(right).append("]");
            return builder.toString();
        }
    }
}
