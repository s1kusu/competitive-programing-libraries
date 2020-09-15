package com.s1kusu.data_structure;

/**
 * BinaryIndexedTree.<br>
 * 長さnの配列に対し以下の操作をO(logN)で行うデータ構造.
 * <ul>
 * <li>add ：要素の1点変更</li>
 * <li>sum：区間の要素の総和</li>
 * </ul>
 * 要素のIndexは1-indexed.
 */
class BIT {

    private int n;
    private long[] bit;

    /**
     * コンストラクタ.<br>
     * 長さ n+1、初期値 0の配列を生成する.<br>
     * 計算量：O(N)
     * @param n
     */
    public BIT(int n){
        this.n = n;
        this.bit = new long[n+1];
    }

    /**
     * i番目の要素にxを加算する.<br>
     * 計算量：O(logN)
     * @param i
     * @param x
     */
    public void add(int i, int x){
        while(i <= n){
            bit[i] += x;
            i += i & -i;
        }
    }

    /**
     * 0～i番目までの要素の総和.<br>
     * 計算量：O(logN)
     * @param i
     */
    public long sum(int i){
        long s = 0L;
        while(0 < i){
            s += bit[i];
            i -= i & -i;
        }
        return s;
    }

    /**
     * l～r番目までの要素の総和.<br>
     * 計算量：O(logN)
     * @param l
     * @param r
     */
    public long sum(int l, int r) {
        return sum(r) - sum(l-1);
    }

    /**
     * k ≦ sum(x) となる最小のxを求める.<br>
     * 計算量：O(logN)
     * @param k
     * @return
     */
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

