package com.s1kusu.algorithm.math;
/**
 * ユークリッドの互除法により最大公約数と最小公倍数を求める
 */
class GcdLcm {

    /**
     * aとbの最大公約数を求める
     * 計算量：O(log n)
     * @param a
     * @param b
     * @return aとbの最大公約数
     */
    public static int gcd(int a, int b){
        if(a < b) return gcd(b, a);
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    /**
     * aとbの最小公倍数を求める
     * 計算量：O(log n)
     * @param a
     * @param b
     * @return aとbの最小公倍数
     */
    public static int lcm(int a, int b){
        return a * b / gcd(a, b);
    }

    /**
     * (拡張ユークリッドの互除法)aとbの最大公約数を求める.
     * xとyには [ax + by = gcd(a, b)]を満たす(x、y)が格納される.
     * 計算量：O(min(a,b))
     * @param a
     * @param b
     * @param x
     * @param y
     * @return aとbの最大公約数
     */
    public static int gcdEx(int a, int b, Integer x, Integer y){
        if(b == 0){
            x = 1;
            y = 0;
            return a;
        }
        int d = gcdEx(b, a%b, y, x);
        y -= a/b * x;
        return d;
    }
}
