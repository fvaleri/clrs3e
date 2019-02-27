package it.fvaleri.clrs3e.data.advanced;

/**
 * Van Emde Boas tree (u=universe size).
 * Faster implementation of dynamic set operations, but
 * each element must be in the range 0 to u-1 with no duplicates.
 * Empty-tree creation: O(u)
 * Operations worst-case: O(lg lg u)
 *
 * @author fvaleri
 */
public class VEBTree {
    private Structure v;

    public VEBTree(int u) {
        v = buildEmptyTree(u);
    }

    private Structure buildEmptyTree(int u) {
        if (u == 2) {
            return new Structure(u);
        } else {
            int uSqrt = Structure.upperRoot(u);
            int lSqrt = Structure.lowerRoot(u);
            Structure summary = buildEmptyTree(uSqrt);
            Structure[] cluster = new Structure[uSqrt];
            for (int i = 0; i < cluster.length; i++) {
                cluster[i] = buildEmptyTree(lSqrt);
            }
            return new Structure(u, summary, cluster);
        }
    }

    public void printTree() {
        printTree(v, 0);
    }

    private void printTree(Structure v, int depth) {
        if (v.u < 2) {
            return;
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("__");
        }
        System.out.println("u: " + v.u + ", min: " + v.min + ", max: " + v.max);
        if (v.u > 2) {
            depth++;
            printTree(v.summary, depth);
            for (int i = 0; i < v.cluster.length; i++) {
                printTree(v.cluster[i], depth);
            }
        }
    }

    public int minimum() {
        return minimum(v);
    }

    private int minimum(Structure v) {
        return v.min;
    }

    public int maximum() {
        return maximum(v);
    }

    private int maximum(Structure v) {
        return v.max;
    }

    public boolean member(int x) {
        return member(v, x);
    }

    private boolean member(Structure v, int x) {
        if (x == v.min || x == v.max) {
            return true;
        } else if (v.u == 2) {
            return false;
        } else {
            return member(v.cluster[v.high(x)], v.low(x));
        }
    }

    public int successor(int x) {
        return successor(v, x);
    }

    private int successor(Structure v, int x) {
        if (v.u == 2) {
            if (x == 0 && v.max == 1) {
                return 1;
            } else {
                return -1;
            }
        } else if (v.min != -1 && x < v.min) {
            return v.min;
        } else {
            int maxLow = maximum(v.cluster[v.high(x)]);
            if (maxLow != -1 && v.low(x) < maxLow) {
                int offset = successor(v.cluster[v.high(x)], v.low(x));
                return v.index(v.high(x), offset);
            } else {
                int succCluster = successor(v.summary, v.high(x));
                if (succCluster == -1) {
                    return -1;
                } else {
                    int offset = minimum(v.cluster[succCluster]);
                    return v.index(succCluster, offset);
                }
            }
        }
    }

    public int predecessor(int x) {
        return predecessor(v, x);
    }

    private int predecessor(Structure v, int x) {
        if (v.u == 2) {
            if (x == 1 && v.min == 0) {
                return 0;
            } else {
                return -1;
            }
        } else if (v.max != -1 && x > v.max) {
            return v.max;
        } else {
            int minLow = minimum(v.cluster[v.high(x)]);
            if (minLow != -1 && v.low(x) > minLow) {
                int offset = predecessor(v.cluster[v.high(x)], v.low(x));
                return v.index(v.high(x), offset);
            } else {
                int predCluster = predecessor(v.summary, v.high(x));
                if (predCluster == -1) {
                    if (v.min != -1 && x > v.min) {
                        return v.min;
                    } else {
                        return -1;
                    }
                } else {
                    int offset = maximum(v.cluster[predCluster]);
                    return v.index(predCluster, offset);
                }
            }
        }
    }

    public void insert(int x) {
        if (x < 0 && x >= v.u) {
            return;
        }
        insert(v, x);
    }

    private void emptyTreeInsert(Structure v, int x) {
        v.min = x;
        v.max = x;
    }

    private void insert(Structure v, int x) {
        if (v.min == -1) {
            emptyTreeInsert(v, x);
        } else {
            if (x < v.min) {
                int z = v.min;
                v.min = x;
                x = z;
            }
            if (v.u > 2) {
                if (minimum(v.cluster[v.high(x)]) == -1) {
                    insert(v.summary, v.high(x));
                    emptyTreeInsert(v.cluster[v.high(x)], v.low(x));
                } else {
                    insert(v.cluster[v.high(x)], v.low(x));
                }
            }
            if (x > v.max) {
                v.max = x;
            }
        }
    }

    public void delete(int x) {
        if (x < 0 && x >= v.u) {
            return;
        }
        delete(v, x);
    }

    private void delete(Structure v, int x) {
        if (v.min == v.max) {
            v.min = -1;
            v.max = -1;
        } else if (v.u == 2) {
            if (x == 0) {
                v.min = 1;
            } else {
                v.min = 0;
            }
            v.max = v.min;
        } else {
            if (x == v.min) {
                int firstCluster = minimum(v.summary);
                x = v.index(firstCluster, minimum(v.cluster[firstCluster]));
                v.min = x;
            }
            delete(v.cluster[v.high(x)], v.low(x));
            if (minimum(v.cluster[v.high(x)]) == -1) {
                delete(v.summary, v.high(x));
                if (x == v.max) {
                    int summaryMax = maximum(v.summary);
                    if (summaryMax == -1) {
                        v.max = v.min;
                    } else {
                        v.max = v.index(summaryMax, maximum(v.cluster[summaryMax]));
                    }
                }
            } else if (x == v.max) {
                v.max = v.index(v.high(x), maximum(v.cluster[v.high(x)]));
            }
        }
    }

    public static class Structure {
        public int u; // universe size
        public int min; // min element
        public int max; // max element
        public Structure summary;
        public Structure[] cluster;

        public Structure(int u) {
            this.u = u;
            this.min = -1;
            this.max = -1;
        }

        public Structure(int u, Structure summary, Structure[] cluster) {
            this(u);
            this.summary = summary;
            this.cluster = cluster;
        }

        public static int upperRoot(int n) {
            double result = 1;
            while (result < Math.sqrt(n)) {
                result *= 2;
            }
            return (int) result;
        }

        public static int lowerRoot(int n) {
            double result = 1;
            while (result * 2 <= Math.sqrt(n)) {
                result *= 2;
            }
            return (int) result;
        }

        public int high(int x) {
            // the number of x's cluster
            return x / lowerRoot(u);
        }

        public int low(int x) {
            // x's position within its cluster
            return x % lowerRoot(u);
        }

        public int index(int x, int y) {
            // builds the element from x and y
            return x * lowerRoot(u) + y;
        }
    }
}
