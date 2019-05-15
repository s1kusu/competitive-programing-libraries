package com.s1kusu.algorithm.graph;

import java.util.Arrays;

class BellmanFord {

    /**
     * ベルマンフォード法により開始地点からの各頂点への最短距離を求める。
     * 負の辺を含む場合も有効。
     * 負の閉路がある場合は無効。
     * 計算量：O(|V||E|)
     * @param n 頂点数
     * @param edge {from, to, cost}
     * @param s 始点
     * @return 始点sからの最短距離を保持した配列(負の閉路を含む場合null)
     */
    public static long[] bellmanFord(int n, int[][] edge, int s){
        long d[] = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        d[s] = 0L;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < edge.length; j++){
                int[] e = edge[j];
                if(d[e[0]] != Long.MAX_VALUE && d[e[1]] > d[e[0]] + e[2]){
                    d[e[1]] = d[e[0]] + e[2];
                    if(i == n - 1){
                        d = null;
                        break;
                    }
                }
            }
        }
        return d;
    }
}
