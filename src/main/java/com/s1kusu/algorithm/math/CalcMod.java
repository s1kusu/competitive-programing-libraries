package com.s1kusu.algorithm.math;

class CalcMod {
    private static int MOD = 1000000007;

    static public void setMod(int mod) {
        MOD = mod;
    }
    static public void setMod1000000007() {
        MOD = 1000000007;
    }
    static public void setMod998244353() {
        MOD = 998244353;
    }

    // TODO 負の数に対応したメソッド

    static public long add(long x, long y) {
        return (x + y) % MOD;
    }

    static public long sub(long x, long y) {
        return (x - y + MOD) % MOD;
    }

    static public long mul(long x, long y) {
        return x * y % MOD;
    }

    static public long div(long x, long y) {
        return mul(x, inv(y));
    }

    static public long inv(long x) {
        return pow(x, MOD - 2);
    }

    static public int pow(long base, int exp) {
        long ret = 1;
        while(exp > 0){
            if((exp & 1) == 1){
                ret = ret * base %MOD;
            }
            base = base * base %MOD;
            exp >>= 1;
        }
        return (int)ret;
    }

}
