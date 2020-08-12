package com.s1kusu.algorithm.graph;

import java.util.Arrays;

/**
 * クラスカル法による最小全域木。
 */
class Kruskal {

    /**
     * 与えられた辺からなる重み付きグラフの最小全域木の辺のコストの総和を求める。(クラスカル法)
     * 計算量：O(|E| log|V|)
     * @param n 頂点数
     * @param edge 重み付きグラフを成す辺 {from, to, cost}
     * @return 最小全域木の辺のコストの総和
     */
    public static long kruskal(int n, int[][] edge){
        Arrays.sort(edge, (x, y) -> x[2] - y[2]);
        DJSet ds = new DJSet(n);

        long ret = 0;
        for (int i = 0; i < edge.length; i++) {
            if(!ds.same(edge[i][0], edge[i][1])){
                ds.union(edge[i][0], edge[i][1]);
                ret += (long)edge[i][2];
            }
        }

        return ret;
    }
}

class DJSet {
    /**
     * 各要素の親を保持する配列。
     * 自信が根である要素は負の数を持つ。
     * （負の数の場合、絶対値がその集合の要素数）
     */
    public int[] upper;

    /**
     * コンストラクタ。
     * @param n 対象となる要素数
     */
    public DJSet(int n) {
        upper = new int[n];
        Arrays.fill(upper, -1);
    }

    /**
     * 要素xが含まれる集合のrootを求める。
     * rootを求める過程で経路圧縮しrootが親になるようにする。
     * @param x 要素x
     * @return 要素xが含まれる集合のroot
     */
    public int root(int x) {
        return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
    }

    /**
     * 要素xと要素yが同じ集合か判定する。
     * @param x 要素x
     * @param y 要素y
     * @return 要素xと要素yが同じ集合であればtrue、そうでなければfalseを返す。
     */
    public boolean same(int x, int y) {
        return root(x) == root(y);
    }

    /**
     * 要素xと要素yを含むそれぞれの集合を合併する。
     * 要素数の多い集合のrootを残し、少ないほうのrootをその下に合併する。
     * @param x 要素x
     * @param y 要素y
     * @return 要素xと要素yを含むそれぞれの集合を合併できたらtrue、もともと同じ集合で合併しなかったらfalseを返す。
     */
    public boolean union(int x, int y) {
        x = root(x);
        y = root(y);
        if (x != y) {
            if (upper[y] < upper[x]) {
                int t = x;
                x = y;
                y = t;
            }
            upper[x] += upper[y];
            upper[y] = x;
        }
        return x != y;
    }

    /**
     * 集合の数を数える。
     * @return 集合の数
     */
    public int countSet() {
        int c = 0;
        for (int u : upper) {
            if (u < 0)
                c++;
        }
        return c;
    }

    /**
     * 要素ｘが含まれる集合の要素数を数える。
     * @param x 要素ｘ
     * @return 要素ｘが含まれる集合の要素数
     */
    public int countElement(int x) {
        return root(x) * -1;
    }
}
