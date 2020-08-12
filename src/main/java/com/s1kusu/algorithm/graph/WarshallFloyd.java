package com.s1kusu.algorithm.graph;

import java.util.Arrays;

class WarshallFloyd {

    /**
     * 全頂点間最短距離のコストを求める。(隣接リスト)
     * 計算量：O(|V|^3)
     * @param n 頂点数
     * @param edge 辺 {from, to, cost}
     * @return 全頂点間最短距離のコスト
     */
    public static long[][] warshallFloyd(int n, int[][] edge){
        long[][] d = new long[n][n];
        Arrays.fill(d, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) d[i][i] = 0;
        for (int i = 0; i < edge.length; i++) {
            d[edge[i][0]][edge[i][1]] = edge[i][2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        return d;
    }

    /**
     * 全頂点間最短距離のコストを求める。(隣接行列)
     * 計算量：O(|V|^3)
     * @param matrix 隣接行列
     * @return 全頂点間最短距離のコスト
     */
    public static int[][] warshallFloyd(int[][] matrix){
        int[][] d = matrix.clone();
        int n = matrix.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        return d;
    }
}
