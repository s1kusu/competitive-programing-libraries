package com.s1kusu.algorithm.math;
/**
 * ユークリッドの互除法により最大公約数と最小公倍数を求める
 */
class GcdLcm {

    /**
     * 最大公約数を求める.<br>
     * 計算量：O(log n)<br>
     * gcd(a, 0) または gcd(0, a) の場合 |a| が返却されます。
     * gcd(0, 0) の場合 0 が返却されます。
     * 引数が双方とも-2^31の場合は例外がスローされます。
     * @param a
     * @param b
     * @return 最大公約数
     * @throws IllegalArgumentException 引数が双方とも-2^31の場合にスローされます
     */
    public static int gcd(int a, int b){
        // 引数が-2^31の場合、正の数に変換できないため調整する
        if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }else if(a == Integer.MIN_VALUE) {
            a /= 2;
        }else if(b == Integer.MIN_VALUE) {
            b /= 2;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        if(a < b) {
            int t = a;
            a = b;
            b = t;
        }
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
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
