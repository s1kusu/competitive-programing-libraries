package com.s1kusu.algorithm.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * 素数に関する処理を提供するクラス。
 *
 */
class Prime {

    /**
     * 素数判定をする.<br>
     * 計算量：O(√n)
     * @param n 判定対象の数
     * @return 判定対象が素数であればtrue、そうでなければfalseを返す。
     */
    public static boolean isPrime(int n){
        if(n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return n != 1;
    }

    /**
     * 約数を列挙する.<br>
     * 計算量：O(√n)
     * @param n 約数を列挙する対象の数
     * @return 対象の約数が昇順に格納された配列。
     */
    public static int[] getDivisor(int n){
        if(n < 1) return new int[0];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0){
                list.add(i);
                if(i != n / i) list.add(n / i);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ret[i] = list.get(i);
        Arrays.sort(ret);
        return ret;
    }

    /**
     * 素因数分解する.<br>
     * 計算量：O(√n)
     * @param n 素因数分解する対象の数
     * @return (素因数, 素因数の指数)となるマップ
     */
    public static TreeMap<Integer, Integer> getPrimeFactors(int n){
        TreeMap<Integer, Integer> ret = new TreeMap<>();
        if(n < 2) return ret;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while(n % i == 0){
                ret.merge(i, 1, Integer::sum);
                n /= i;
            }
        }
        if(n != 1) ret.put(n, 1);
        return ret;
    }

    /**
     * 配列に含まれる数をすべて掛け合わせた数を素因数分解する.<br>
     * 配列に2より小さい数が含まれる場合、その数はスキップされる。<br>
     * 計算量：O(|a|√x)
     * @param a 掛け合わせる数が格納された配列
     * @return (素因数, 素因数の指数)となるマップ
     */
    public static TreeMap<Integer, Integer> getPrimeFactors(int[] a){
        TreeMap<Integer, Integer> ret = new TreeMap<>();
        for(int x : a){
            if(x < 2) continue;
            for (int i = 2; i <= Math.sqrt(x); i++) {
                while(x % i == 0){
                    ret.merge(i, 1, Integer::sum);
                    x /= i;
                }
            }
            if(x != 1) ret.merge(x, 1, Integer::sum);
        }
        return ret;
    }

    /**
     * 自然数n以下の素数を求める。(2 ≦ n)
     * 計算量：O(nloglogn)
     * @param n 素数を求める範囲の最大値(この値を含む)
     * @return 自然数n以下の素数が昇順に格納された配列
     */
    public static int[] getPrimeNumbers(int n){
        return getPrimeNumbers(2, n);
    }
    /**
     * from-toの範囲の素数を求める。
     * 計算量：O(nloglogn)
     * @param from 素数を求める範囲の最小値(この値を含む)
     * @param to 素数を求める範囲の最大値(この値を含む)
     * @return from-toの範囲の素数が昇順に格納された配列
     */
    public static int[] getPrimeNumbers(int from, int to){
        if(to < from || to < 2) return new int[0];
        int[] ret = new int[to+1];
        boolean[] prime = new boolean[to+1];
        Arrays.fill(prime, true);
        prime[0] = false; prime[1] = false;
        int cnt = 0;
        for (int i = 2; i <= to; i++) {
            if(prime[i]){
                if(i >= from){
                    ret[cnt] = i;
                    cnt++;
                }
                for(int j = 2 * i; j <= to; j += i) prime[j] = false;
            }
        }
        return Arrays.copyOfRange(ret, 0, cnt);
    }

}
