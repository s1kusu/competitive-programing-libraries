package com.s1kusu.algorithm.math;
/**
 * フェルマーの小定理を用いて順列・組み合わせの数を算出するクラス.
 */
class Combination {

    /** factorial:階乗 */
    private long fac[];
    /** inverse:逆元（ここでは、fac[i]をMOD-2乗したものをMODで割った余り） */
    private long inv[];
    /** 除数 */
    private int mod;

    /**
     * コンストラクタ<br>
     * 計算量：O(n log n)
     * @param size 組み合わせの対象となる個数の最大値
     * @param mod 除数
     */
    public Combination(int size, int mod){
        this.fac = new long[size +1];
        this.inv = new long[size +1];
        this.mod = mod;

        this.fac[0] = 1;
        this.inv[0] = 1;

        for(int i = 1; i <= size; i++){
            fac[i] = fac[i-1] * i %mod;
            inv[i] = modPow(fac[i], mod - 2);
        }
    }

    /**
     * n個の中からr個選ぶ組み合わせの数（nCr）をmodで割った余りを算出する<br>
     * 引数は以下の制約を満たす必要があり、それ以外の場合の動作は保証されない。<br>
     * <ul>
     * <li>0 &lt;= n, r &lt;= size</li>
     * <li>r &lt;= n</li>
     * </ul>
     * 計算量：O(1)
     * @param n 元の集合の要素数
     * @param r 組み合わせる要素数
     * @return nCr % mod
     */
    public long comb(int n, int r){
        return fac[n] * inv[r] %mod * inv[n - r] %mod;
    }

    /**
     * n個の中からr個選んで並べる並べ方の数（nPr）をmodで割った余りを算出する<br>
     * 引数は以下の制約を満たす必要があり、それ以外の場合の動作は保証されない。<br>
     * <ul>
     * <li>0 &lt;= n, r &lt;= size</li>
     * <li>r &lt;= n</li>
     * </ul>
     * 計算量：O(1)
     * @param n 元の集合の要素数
     * @param r 並べる要素数
     * @return nPr % mod
     */
    public long perm(int n, int r){
        return fac[n] * inv[n - r] %mod;
    }

    /**
     * n種類の中から重複を許してr個選ぶ重複組み合わせの数（nHr）をmodで割った余りを算出する<br>
     * 引数は以下の制約を満たす必要があり、それ以外の場合の動作は保証されない。<br>
     * <ul>
     * <li>0 &lt;= n, r</li>
     * <li>0 &lt;= n-1, n+r-1 &lt;= size</li>
     * </ul>
     * 計算量：O(1)
     * @param n 元の集合の要素数
     * @param r 組み合わせる要素数
     * @return nCr % mod
     */
    public long hom(int n, int r){
        return fac[n + r - 1] * inv[r] %mod * inv[n - 1] %mod;
    }

    /**
     * base^exp をmodで割った余りを算出する.<br>
     * 計算量：O(log exq)
     * @param base 基数
     * @param exp 指数
     * @return base^exp % mod
     */
    private long modPow(long base, int exp) {
        long ret = 1;
        while(exp > 0){
            if((exp & 1) == 1){
                ret = ret * base %mod;
            }
            base = base * base %mod;
            exp >>= 1;
        }
        return ret;
    }

}
