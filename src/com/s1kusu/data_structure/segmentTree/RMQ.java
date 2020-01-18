package com.s1kusu.data_structure.segmentTree;

/**
 * Segment Tree - Range Minimum Query.
 *
 */
public class RMQ {

    public int n;
    public int size;
    public int[] data;

    /**
     * コンストラクタ.
     * @param n 要素数
     */
    public RMQ(int n){
        this.n = n;
        size = 1;
        while(size < n) size *= 2;
        data = new int[size*2];
        for (int i = 0; i < size*2; i++) data[i] = Integer.MAX_VALUE;
    }

    /**
     * k番目（0-indexed）の要素をaに更新します.
     * @param k 変更対象要素のindex（0-indexed）
     * @param a 変更後の値
     */
    public void update(int k, int a){
        k += size - 1;
        data[k] = a;
        while(0 < k){
            k = (k - 1) / 2;
            data[k] = Math.min(data[k * 2 + 1], data[k * 2 + 2]);
        }
    }

    /**
     * 区間[a, b)での最小値を求めます.
     * 最初に呼ぶ際は、query(a, b, 0, 0, size)として呼ぶ.
     * @param a 求める区間の下限（含む）
     * @param b 求める区間の上限（含まない）
     * @param k 確認するノードのindex
     * @param l 確認するノードの下限（含む）
     * @param r 確認するノードの上限（含まない）
     * @return 区間[a, b)での最小値
     */
    public int query(int a, int b, int k, int l, int r){
        if(r <= a || b <= l) return Integer.MAX_VALUE;

        if(a <= l && r <= b) return data[k];

        int vl = query(a, b, k*2+1, l, (l+r)/2);
        int vr = query(a, b, k*2+2, (l+r)/2, r);
        return Math.min(vl, vr);
    }
}
