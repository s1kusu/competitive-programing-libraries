package com.s1kusu.algorithm.graph;

import java.util.*;

class LCA {

    public int n;
    public int root;
    public int[][] pars;
    public int[] depth;
    public int k;

    public LCA(int n, int root, int[][] graph){
        this.n = n;
        this.root = root;
        k = Integer.toBinaryString(n).length();
        depth = new int[n];
        pars = new int[n][k+1];
        Arrays.fill(depth, -1);
        dfs(root, -1, 0, graph);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                if(pars[j][i] < 0) pars[j][i+1] = -1;
                else pars[j][i+1] = pars[ pars[j][i] ][i];
            }
        }
    }

    private void dfs(int v, int p, int i, int[][] g) {
        pars[v][0] = p;
        depth[v] = i;
        for(int c : g[v]){
            if(depth[c] != -1) continue;
            dfs(c, v, i+1, g);
        }
    }

    public int lca(int a, int b){
        if(depth[a] < depth[b]){
            int t = b;
            b = a;
            a = t;
        }
        for (int i = 0; i <= k; i++) {
            if(((depth[a] - depth[b]) >> i & 1) == 1){
                a = pars[a][i];
            }
        }
        if(a == b) return a;
        for (int i = k; i >= 0; i--) {
            if(pars[a][i] != pars[b][i]){
                a = pars[a][i];
                b = pars[b][i];
            }
        }
        return pars[a][0];
    }
}
