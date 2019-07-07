package com.s1kusu.data_structure;

import java.util.Scanner;

class Graph {

    /**
     * 無向グラフ（隣接リスト）を生成する。
     * 計算量：O(m + n)
     * @param sc 標準入力を読み込むためのスキャナ
     * @param n 頂点の数
     * @param m 辺の数
     * @return 生成したグラフ
     */
    public static int[][] buildGraph(Scanner sc, int n, int m){
        int[][] graph = new int[n][];
        int[][] edges = new int[m][2];
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() -1;
            int b = sc.nextInt() -1;
            edges[i][0] = a;
            edges[i][1] = b;
            deg[a]++;
            deg[b]++;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = new int[deg[i]];
        }
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a][--deg[a]] = b;
            graph[b][--deg[b]] = a;
        }

        return graph;
    }

    /**
     * 有向グラフ（隣接リスト）を生成する。
     * 計算量：O(m + n)
     * @param sc 標準入力を読み込むためのスキャナ
     * @param n 頂点の数
     * @param m 辺の数
     * @return 生成したグラフ
     */
    public static int[][] buildDirectedGraph(Scanner sc, int n, int m){
        int[][] graph = new int[n][];
        int[][] edges = new int[m][2];
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() -1;
            int b = sc.nextInt() -1;
            edges[i][0] = a;
            edges[i][1] = b;
            deg[a]++;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = new int[deg[i]];
        }
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a][--deg[a]] = b;
        }

        return graph;
    }


    /**
     * 辺の情報から無向グラフ（隣接リスト）を生成する。
     * 計算量：O(m + n)
     * @param n 頂点の数
     * @param edges 辺の配列（｛ from, to ｝）
     * @return 辺の情報から生成したグラフ
     */
    public static int[][] buildGraph(int n, int[][] edges){
        int m = edges.length;
        int[][] graph = new int[n][];
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            deg[ edges[i][0] ]++;
            deg[ edges[i][1] ]++;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = new int[deg[i]];
        }
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a][--deg[a]] = b;
            graph[b][--deg[b]] = a;
        }

        return graph;
    }

    /**
     * 辺の情報から重み付き無向グラフ（隣接リスト）を生成する。
     * 計算量：O(m + n)
     * @param n 頂点の数
     * @param edges 辺の配列（｛ from, to, cost ｝）
     * @return 辺の情報から生成したグラフ(graph[i][j][k]：頂点ｉの次数がｊ－１で、graph[i][j]＝｛ vertex, cost ｝)
     */
    public static int[][][] buildWeightedGraph(int n, int[][] edges){
        int m = edges.length;
        int[][][] graph = new int[n][][];
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            deg[ edges[i][0] ]++;
            deg[ edges[i][1] ]++;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = new int[deg[i]][2];
        }
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int c = edges[i][2];
            graph[a][--deg[a]][0] = b;
            graph[a][deg[a]][1] = c;
            graph[b][--deg[b]][0] = a;
            graph[b][deg[b]][1] = c;
        }

        return graph;
    }

}
