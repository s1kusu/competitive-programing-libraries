package com.s1kusu.data_structure.segmentTree;

/**
 * Lazy Segment Tree - Range Sum Query.
 *
 */
class LazySegTree_RSQ {

    public int n;
    public int size;
    /**
     * 要素全体を含むノードのindex：0.
     * k番目(0-indexed)の要素のindex：k + size - 1
     * ノードkの親要素のindex：(k - 1) / 2.
     * ノードkの子要素のうち左側のindex：k*2+1.
     * ノードkの子要素のうち右側のindex：k*2+2.
     */
    public long[] data;
    public long[] lazy;

    /**
     * コンストラクタ.
     * @param n 要素数
     */
    public LazySegTree_RSQ(int n){
        this.n = n;
        size = 1;
        while(size < n) size *= 2;
        data = new long[size*2];
        lazy = new long[size*2];
    }

    /**
     * 指定したノードが遅延評価待ちの値を保持している場合、
     * 自ノードに反映し、子ノードの遅延評価待ちとして設定する。
     * @param k 自ノードのindex
     * @param l 自ノードの対象区間左側（含む）
     * @param r 自ノードの対象区間右側（含まない）
     */
    public void eval(int k, int l, int r){
        if(lazy[k] != 0){
            data[k] += lazy[k];
            if(1 < r - l){
                lazy[2*k+1]  += lazy[k] / 2;
                lazy[2*k+2]  += lazy[k] / 2;
            }
            lazy[k] = 0L;
        }
    }

    /**
     * 区間[a, b)にxを加算します.
     * 最初に呼ぶ際は、query(a, b, x, 0, 0, size)として呼ぶ.
     * @param a 加算する区間の下限（含む）
     * @param b 加算する区間上限（含まない）
     * @param x 加算する値
     * @param k 加算するノードのindex
     * @param l 加算するノードの下限（含む）
     * @param r 加算するノードの上限（含まない）
     */
    public void add(int a, int b, int x, int k, int l, int r){
        eval(k, l, r);

        // 区間外
        if(b <= l || r <= a) return;

        // 完全に含む
        if(a <= l && r <= b){
            lazy[k] += (long)(r - l) * x;
            eval(k, l, r);
            return;
        }

        // それ以外
        add(a, b, x, 2*k+1, l, (l+r)/2);
        add(a, b, x, 2*k+2, (l+r)/2, r);
        data[k] = data[2*k+1] + data[2*k+2];
    }

    /**
     * 区間[a, b)にxを加算します.
     * @param a 加算する区間の下限（含む）
     * @param b 加算する区間上限（含まない）
     * @param x 加算する値
     */
    public void add(int a, int b, int x){
        add(a, b, x, 0, 0, size);
    }

    /**
     * 区間[a, b)の和を求めます.
     * 最初に呼ぶ際は、query(a, b, 0, 0, size)として呼ぶ.
     * @param a 求める区間の下限（含む）
     * @param b 求める区間の上限（含まない）
     * @param k 確認するノードのindex
     * @param l 確認するノードの下限（含む）
     * @param r 確認するノードの上限（含まない）
     * @return 区間[a, b)の和
     */
    public long sum(int a, int b, int k, int l, int r){
        // 区間外
        if(b <= l || r <= a) return 0;
        eval(k, l, r);
        // 完全に含む
        if(a <= l && r <= b) return data[k];
        // それ以外
        long vl = sum(a, b, 2*k+1, l, (l+r)/2);
        long vr = sum(a, b, 2*k+2, (l+r)/2, r);
        return vl + vr;
    }

    /**
     * 区間[a, b)の和を求めます.
     * @param a 求める区間の下限（含む）
     * @param b 求める区間の上限（含まない）
     * @return 区間[a, b)の和
     */
    public long sum(int a, int b){
        return sum(a, b, 0, 0, size);
    }
}
