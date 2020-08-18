package com.s1kusu.algorithm.math;
/**
 * ユークリッドの互除法により最大公約数と最小公倍数を求める
 */
class GcdLcm {

    /**
     * 最大公約数を求める.<br>
     * 計算量：O(log n)<br>
     * gcd(a, 0) または gcd(0, a) の場合 |a| が返却される。
     * gcd(0, 0) の場合 0 が返却される。
     * 引数が双方とも-2^31の場合は適切な値を返却できないため例外がスローされる。
     * @param a
     * @param b
     * @return 最大公約数
     * @throws IllegalArgumentException 引数が双方とも-2^31の場合にスローされる
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
     * aとbの最小公倍数(正の倍数のうち最小のもの)を求める.<br>
     * 計算量：O(log n)<br>
     * 引数が1つ以上０である場合0が返却される。
     * @param a
     * @param b
     * @return 最小公倍数
     */
    public static long lcm(int a, int b){
        if(a == b) return a;
        return Math.abs((long)a * b / gcd(a, b));
    }

    /**
     * (拡張ユークリッドの互除法)aとbの最大公約数を求める.<br>
     * x と y には ax + by = gcd(a, b) を満たす(x, y)となる。
     * 引数に負の数が含まれる場合の動作は保証されない。
     * 計算量：O(min(a,b))
     * @param a
     * @param b
     * @return {gcd(a, b), x, y}
     */
    public static int[] gcdEx(int a, int b){
        if(b == 0){
            return new int[] {a, 1, 0};
        }
        int[] gcdYX = gcdEx(b, a%b);
        int gcd = gcdYX[0];
        int y = gcdYX[1];
        int x = gcdYX[2];
        y -= a/b * x;
        return new int[] {gcd, x, y};
    }
}
