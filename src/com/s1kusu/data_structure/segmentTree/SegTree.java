package com.s1kusu.data_structure.segmentTree;

/**
 * Segment Tree Template.
 *
 */
public class SegTree {

    public int n;
    public int size;
    /**
     * 要素全体を含むノードのindex：0.
     * k番目(0-indexed)の要素のindex：k + size - 1
     * ノードkの親要素のindex：(k - 1) / 2.
     * ノードkの子要素のうち左側のindex：k*2+1.
     * ノードkの子要素のうち右側のindex：k*2+2.
     */
    public int[] data;

    /**
     * コンストラクタ.
     * @param n 要素数
     */
    public SegTree(int n){
        this.n = n;
        size = 1;
        while(size < n) size *= 2;
        data = new int[size*2];
        // TODO 初期化処理
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
            // TODO 更新処理
        }
    }

    /**
     * 区間[a, b)での○○を求めます.
     * 最初に呼ぶ際は、query(a, b, 0, 0, size)として呼ぶ.
     * @param a 求める区間の下限（含む）
     * @param b 求める区間の上限（含まない）
     * @param k 確認するノードのindex
     * @param l 確認するノードの下限（含む）
     * @param r 確認するノードの上限（含まない）
     * @return 区間[a, b)での○○
     */
    public int query(int a, int b, int k, int l, int r){
        // TODO クエリ
        return 0;
    }
}
