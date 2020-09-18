package com.s1kusu.data_structure.segmentTree;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * Lazy Segment Tree.<br>
 * (0-indexed)
 */
class LazySegTree<T, S> {

    /** 要素数 */
    private final int N;
    /** Nより大きい最小の2の累乗数 */
    private final int SIZE;
    /** 2 ^ LOG = SIZE */
    private final int LOG;
    /**
     * データ配列.
     * 要素全体を含むノードのindex：1.
     * k番目(0-indexed)の要素のindex：k + size
     * ノードkの親要素のindex：k/2.
     * ノードkの子要素のうち左側のindex：k*2.
     * ノードkの子要素のうち右側のindex：k*2+1.
     */
    private final T[] DATA;
    /** 遅延評価配列 */
    private final S[] LAZY;
    /** データの単位元供給関数 */
    private final Supplier<T> ET;
    /** クエリ結果を求める関数 */
    private final BinaryOperator<T> OP;
    /** 遅延評価の単位元供給関数 */
    private final Supplier<S> ES;
    /** 遅延分をデータに適用する関数 */
    private final BiFunction<T, S, T> MAPPING;
    /** 遅延分を合成する関数 */
    private final BinaryOperator<S> COMPOSITION;

    /**
     * 全てのノードを単位元で初期化するコンストラクタ.<br>
     * 計算量：O(N)
     * @param n
     * @param et
     * @param op
     * @param es
     * @param mapping
     * @param composition
     */
    @SuppressWarnings("unchecked")
    public LazySegTree(int n, Supplier<T> et, BinaryOperator<T> op, Supplier<S> es,
            BiFunction<T, S, T> mapping, BinaryOperator<S> composition){
        int tmpSize = 1, tmpLog = 0;
        while(tmpSize < n) {
            tmpLog++;
            tmpSize *= 2;
        }
        this.N = n;
        this.SIZE = tmpSize;
        this.LOG = tmpLog;
        this.DATA = (T[]) new Object[SIZE*2];
        this.LAZY = (S[]) new Object[SIZE*2];
        this.ET = et;
        this.OP = op;
        this.ES = es;
        this.MAPPING = mapping;
        this.COMPOSITION = composition;
        Arrays.fill(DATA, ET.get());
        Arrays.fill(LAZY, ES.get());
    }

    /**
     * 引数で指定された配列で初期化するコンストラクタ.<br>
     * 計算量：O(N)
     * @param a
     * @param et
     * @param op
     * @param es
     * @param mapping
     * @param composition
     */
    @SuppressWarnings("unchecked")
    public LazySegTree(T[] a, Supplier<T> et, BinaryOperator<T> op, Supplier<S> es,
            BiFunction<T, S, T> mapping, BinaryOperator<S> composition){
        int tmpSize = 1, tmpLog = 0;
        while(tmpSize < a.length) {
            tmpLog++;
            tmpSize *= 2;
        }
        this.N = a.length;
        this.SIZE = tmpSize;
        this.LOG = tmpLog;
        this.DATA = (T[]) new Object[SIZE*2];
        this.LAZY = (S[]) new Object[SIZE*2];
        this.ET = et;
        this.OP = op;
        this.ES = es;
        this.MAPPING = mapping;
        this.COMPOSITION = composition;
        for (int i = 0; i < N; i++) DATA[i + SIZE] = a[i];
        for(int i = N; i < SIZE; i++) DATA[i+SIZE] = ET.get();
        for (int i = SIZE-1; i > 0; i--) update(i);
        Arrays.fill(LAZY, ES.get());
    }

    /**
     * 指定したノードに遅延分を反映し、子ノードの遅延評価待ちを設定する。<br>
     * 計算量：O(1)
     * @param k 自ノードのindex
     */
    public void eval(int k){
        DATA[k] = MAPPING.apply(DATA[k], LAZY[k]);
        if(k < SIZE-1) {
            LAZY[2*k] = COMPOSITION.apply(LAZY[k], LAZY[2*k]);
            LAZY[2*k+1] = COMPOSITION.apply(LAZY[k], LAZY[2*k+1]);
        }
        LAZY[k] = ES.get();
    }

    /**
     * k番目の要素を取得する.<br>
     * 計算量：O(logN)
     * @param k
     * @return k番目の要素の値
     */
    public T get(int k) {
        k += SIZE;
        for(int i = LOG; i >= 0; i--) eval(k >> i);
        return DATA[k];
    }

    /**
     * k番目（0-indexed）の要素をaに更新する.<br>
     * 計算量：O(logN)
     * @param k 変更対象要素のindex（0-indexed）
     * @param v 変更後の値
     */
    public void set(int k, T v){
        k += SIZE;
        for(int i = LOG; i >= 0; i--) eval(k >> i);
        DATA[k] = v;
        for(int i = 1; i <= LOG; i++) update(k >> i);
    }

    /**
     * ノードkに演算を適用する.<br>
     * 計算量：O(1)
     * @param k
     */
    private void update(int k) {
        DATA[k] = OP.apply(DATA[k*2], DATA[k*2+1]);
    }

    /**
     * k番目（0-indexed）の要素に s を適用する.<br>
     * 計算量：O(logN)
     * @param k 変更対象要素のindex（0-indexed）
     * @param s 更新処理
     */
    public void apply(int k, S s) {
        k += SIZE;
        for(int i = LOG; i >= 0; i--) eval(k >> i);
        DATA[k] = MAPPING.apply(DATA[k], s);
        for(int i = 1; i <= LOG; i++) update(k >> i);
    }


    /**
     * 区間[a, b)に s を適用する.<br>
     * 計算量：O(logN)
     * @param l 最終的に適用する区間の下限（含む）
     * @param r 最終的に適用する区間上限（含まない）
     * @param s 更新処理
     */
    public void apply(int l, int r, S s) {
        apply(l, r, 1, 0, SIZE, s);
    }

    /**
     * 区間[a, b)に s を適用する.<br>
     * 計算量：O(logN)
     * @param l 最終的に適用する区間の下限（含む）
     * @param r 最終的に適用する区間上限（含まない）
     * @param k 今見ているノードのindex
     * @param a 今見ているノードの下限（含む）
     * @param b 今見ているノードの上限（含まない）
     * @param s 更新処理
     */
    private void apply(int l, int r, int k, int a, int b, S s){
        eval(k);

        // 区間外
        if(r <= a || b <= l) return;

        // 完全に含む
        if(l <= a && b <= r){
            LAZY[k] = COMPOSITION.apply(LAZY[k], s);
            eval(k);
            return;
        }

        // それ以外
        apply(l, r, 2*k, a, (a+b)/2, s);
        apply(l, r, 2*k+1, (a+b)/2, b, s);
        update(k);
    }

    /**
     * 区間[l, r)の結果を求める.<br>
     * 計算量：O(logN)
     * @param l 求める区間の下限（含む）
     * @param r 求める区間の上限（含まない）
     * @return 区間[l, r)の結果
     */
    public T query(int l, int r){
        return query(l, r, 1, 0, SIZE);
    }

    /**
     * 区間[l, r)の結果を求める.<br>
     * 計算量：O(logN)
     * @param l 求める区間の下限（含む）
     * @param r 求める区間の上限（含まない）
     * @param k 確認するノードのindex
     * @param a 確認するノードの下限（含む）
     * @param b 確認するノードの上限（含まない）
     * @return 区間[l, r)の結果
     */
    private T query(int l, int r, int k, int a, int b){
        // 区間外
        if(r <= a || b <= l) return ET.get();
        eval(k);
        // 完全に含む
        if(l <= a && b <= r) return DATA[k];
        // それ以外
        T vl = query(l, r, 2*k, a, (a+b)/2);
        T vr = query(l, r, 2*k+1, (a+b)/2, b);
        return OP.apply(vl, vr);
    }
}
