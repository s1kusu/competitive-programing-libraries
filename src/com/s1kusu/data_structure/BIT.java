package com.s1kusu.data_structure;

/**
 * BinaryIndexedTree
 *
 */
public class BIT {

    public int n;
    public long[] bit;

    public BIT(int n){
        this.n = n;
        bit = new long[n+1];
    }

    public long sum(int i){
        long s = 0L;
        while(0 < i){
            s += bit[i];
            i -= i & -i;
        }
        return s;
    }

    public void add(int i, int x){
        while(i <= n){
            bit[i] += x;
            i += i & -i;
        }
    }

    public int lowerBound(int k){
        if(k <= 0) return 0;
        int x = 0;
        int max = 1;
        while(max < n) max *= 2;
        for (int i = max; i > 0; i /= 2) {
            if(x + i <= n && bit[x + i] < k){
                k -= bit[x + i];
                x += i;
            }
        }
        return x + 1;
    }

}
