package com.s1kusu.algorithm.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

class Dijkstra {

    /**
     * ダイクストラ法により開始地点からの各頂点への最短距離を求める。
     * 負の辺を含む場合は無効。
     * 計算量：O(|E| log|V|)
     * @param n 頂点数
     * @param graph グラフ graph[i][j][k]：頂点ｉの次数がｊ－１で、graph[i][j]＝｛ 頂点, コスト ｝
     * @param s 開始地点
     * @return 開始地点sからの最短距離を保持した配列
     */
    public static long[] dijkstra(int n, int[][][] graph, int s) {
        // {sからの最短距離, 頂点}
        PriorityQueue<long[]> pq =
                new PriorityQueue<long[]>((x, y) -> Long.compare(x[0], y[0]));
        // sからの最短距離
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        d[s] = 0;
        pq.add(new long[]{0, s});
        while(!pq.isEmpty()){
            long[] a = pq.poll();
            int v = (int)a[1];
            if(d[v] < a[0]) continue;
            for (int[] g: graph[v]) {
                int to = g[0];
                int cost = g[1];
                if(d[to] > d[v] + cost){
                    d[to] = d[v] + cost;
                    pq.add(new long[]{d[to], to});
                }
            }
        }

        return d;
    }

}
