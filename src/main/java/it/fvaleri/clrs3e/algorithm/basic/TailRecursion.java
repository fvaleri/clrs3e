package it.fvaleri.clrs3e.algorithm.basic;

import java.math.BigInteger;

/**
 * Unitl Java 1.8 there is no support for tail call 
 * optimization and this code will blow the stack.
 * 
 * @author fvaleri
 */
public class TailRecursion {
    public BigInteger factorial(int n) {
        return fact(BigInteger.ONE, BigInteger.valueOf(n));
    }

    private BigInteger fact(BigInteger acc, BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return acc;
        }
        return fact(acc.multiply(n), n.subtract(BigInteger.ONE));
    }
    
}
