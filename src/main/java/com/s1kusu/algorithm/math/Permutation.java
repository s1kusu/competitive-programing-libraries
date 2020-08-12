package com.s1kusu.algorithm.math;
/**
 * フェルマーの小定理を用いて順列の数を算出するクラス
 */
class Permutation {

    /** factorial:階乗 */
    private long fac[];
    /** inverse:逆元（ここでは、fac[i]をMOD-2乗したものをMODで割った余り） */
    private long inv[];
    /** 除数 */
    private int mod;

    /**
     * コンストラクタ
     * @param size 順列の対象となる個数
     * @param mod 除数
     */
    public Permutation(int size, int mod){
        this.fac = new long[size +1];
        this.inv = new long[size +1];
        this.mod = mod;

        this.fac[0] = 1;
        this.inv[0] = 1;

        for(int i = 1; i <= size; i++){
            fac[i] = (fac[i -1] * i) %mod;
            inv[i] = modPow(fac[i], (int)mod -2) %mod;
        }
    }

    /**
     * n個の中からr個選んで並べる並べ方の数（nPr）をmodで割った余りを算出する
     * @param n 元の集合の要素数
     * @param r 並べる要素数
     * @return nPr % mod
     */
    public long perm(int n, int r){
        return fac[n] * inv[n - r] %mod;
    }

    /**
     * base^exp をmodで割った余りを算出する
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
