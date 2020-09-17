package com.s1kusu.data_structure.segmentTree;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

/**
 * Segment Tree.<br>
 * (0-indexed.)
 */
class SegTree {

    /** SegTreeで扱う要素数 */
    private final int N;
    /** nより大きい最小の2の累乗数 */
    private final int SIZE;
    /** 単位元 */
    private final int E;
    /** 演算 */
    private final IntBinaryOperator OP;
    /**
     * 要素全体を含むノードのindex：0.
     * k番目(0-indexed)の要素のindex：k + size - 1
     * ノードkの親要素のindex：(k - 1) / 2.
     * ノードkの子要素のうち左側のindex：k*2+1.
     * ノードkの子要素のうち右側のindex：k*2+2.
     */
    private final int[] DATA;

    /**
     * 全てのノードを単位元で初期化するコンストラクタ.<br>
     * 計算量：O(N)
     * @param n 要素数
     * @param e 単位元
     * @param op 演算
     */
    public SegTree(int n, int e, IntBinaryOperator op){
        int tmpSize = 1;
        while(tmpSize < n) tmpSize *= 2;
        this.N = n;
        this.E = e;
        this.OP = op;
        this.SIZE = tmpSize;
        this.DATA = new int[SIZE*2];
        Arrays.fill(DATA, e);
    }

    /**
     * 引数で指定された配列で初期化するコンストラクタ.<br>
     * 計算量：O(N)
     * @param a 初期配列
     * @param e 単位元
     * @param op 演算
     */
    public SegTree(int[] a, int e, IntBinaryOperator op){
        int tmpSize = 1;
        while(tmpSize < a.length) tmpSize *= 2;
        this.N = a.length;
        this.E = e;
        this.OP = op;
        this.SIZE = tmpSize;
        this.DATA = new int[SIZE*2];
        Arrays.fill(DATA, e);
        for (int i = 0; i < N; i++) DATA[i + SIZE - 1] = a[i];
        for (int i = SIZE-2; i >= 0; i--) update(i);
    }

    /**
     * k番目の要素を取得する.
     * 計算量：O(1)
     * @param k
     * @return k番目の要素の値
     */
    public int get(int k) {
        return DATA[k + SIZE - 1];
    }

    /**
     * k番目（0-indexed）の要素をaに更新する.<br>
     * 計算量：O(logN)
     * @param k 変更対象要素のindex（0-indexed）
     * @param v 変更後の値
     */
    public void set(int k, int v){
        k += SIZE - 1;
        DATA[k] = v;
        while(0 < k){
            k = (k - 1) / 2;
            update(k);
        }
    }

    /**
     * ノードkに演算を適用する.<br>
     * 計算量：O(1)
     * @param k
     */
    private void update(int k) {
        DATA[k] = OP.applyAsInt(DATA[k * 2 + 1], DATA[k * 2 + 2]);
    }

    /**
     * 区間[a, b)の結果を取得する.<br>
     * 計算量：O(logN)
     * @param l 求める区間の下限（含む）
     * @param r 求める区間の上限（含まない）
     * @return 区間[a, b)の結果
     */
    public int query(int l, int r){
        return query(l, r, 0, 0, SIZE);
    }

    /**
     * 区間[a, b)の結果を取得する.<br>
     * @param l 求める区間の下限（含む）
     * @param r 求める区間の上限（含まない）
     * @param k 確認するノードのindex
     * @param a 確認するノードの下限（含む）
     * @param b 確認するノードの上限（含まない）
     * @return 区間[a, b)の結果
     */
    private int query(int l, int r, int k, int a, int b){
        if(b <= l || r <= a) return E;

        if(l <= a && b <= r) return DATA[k];

        int vl = query(l, r, k*2+1, a, (a+b)/2);
        int vr = query(l, r, k*2+2, (a+b)/2, b);
        return OP.applyAsInt(vl, vr);
    }

    /**
     * pr(op(l, l+1, ... , x-1)) = true かつ
     * pr(op(l, l+1, ... , x)) = false
     * を満たす最大のxを求める.<br>
     * @param l 対象範囲下限
     * @param pr 判定条件
     * @return 条件を満たす最大のIndex
     */
    public int maxRight(int l, IntPredicate pr) {
        return maxRight(l, N, pr);
    }

    /**
     * [l, r)の範囲において
     * pr(op(l, l+1, ... , x-1)) = true かつ
     * pr(op(l, l+1, ... , x)) = false
     * を満たす最大のxを求める.<br>
     * @param l 対象範囲下限（含む）
     * @param r 対象範囲上限（含まない）
     * @param pr 判定条件
     * @return 条件を満たす最大のIndex
     */
    public int maxRight(int l, int r, IntPredicate pr) {
        int idx = l + SIZE - 1;
        int li = l, ri = l, width = 1;
        int applied = E;
        while(ri < SIZE) {
            int tmp = OP.applyAsInt(applied, DATA[idx]);
            if(ri < r && pr.test(tmp)) {
                applied = tmp;
                if((idx&1) == 1) {
                    idx++;
                }else {
                    idx = (idx-1)/2 +1;
                    width *= 2;
                }
                li = ri + 1;
                ri = li + width -1;
            }else {
                if(SIZE - 1 <= idx) {
                    return li;
                }
                idx = idx*2+1;
                width /= 2;
                ri -= width;
            }
        }
        return r;
    }

    // TODO minLeft()
}
