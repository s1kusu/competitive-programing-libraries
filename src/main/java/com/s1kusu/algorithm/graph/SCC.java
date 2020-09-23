package com.s1kusu.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 強連結成分 - Strongly Connected Components.
 */
class SCC {

    private final int n;
    private final ArrayList<Integer>[] G;
    private final ArrayList<Integer>[] rG;
    private final List<Integer> rv;
    private final boolean[] used;
    private final int[] cmp;
    private final List<List<Integer>> sccList;

    @SuppressWarnings("unchecked")
    public SCC(int n) {
        this.n = n;
        this.G = new ArrayList[n];
        this.rG = new ArrayList[n];
        this.rv = new ArrayList<>(n);
        this.used = new boolean[n];
        this.cmp = new int[n];
        this.sccList = new ArrayList<>(n);
    }

    public void addEdge(int from, int to) {
        if(G[from] == null) G[from] = new ArrayList<Integer>();
        G[from].add(to);
        if(rG[to] == null) rG[to] = new ArrayList<Integer>();
        rG[to].add(from);
    }

    private void dfs(int v) {
        used[v] = true;
        if(G[v] != null){
            for(int nv : G[v]) {
                if(used[nv]) continue;
                dfs(nv);
            }
        }
        rv.add(v);
    }

    private void rdfs(int v, int k, List<Integer> list) {
        used[v] = true;
        cmp[v] = k;
        list.add(v);
        if(rG[v] == null) return;
        for(int nv : rG[v]) {
            if(used[nv]) continue;
            rdfs(nv, k, list);
        }
    }

    public List<List<Integer>> scc(){
        Arrays.fill(used, false);
        rv.clear();
        for (int i = 0; i < n; i++) {
            if(used[i]) continue;
            dfs(i);
        }
        Arrays.fill(used, false);
        int k = 0;
        for (int i = n-1; i >= 0; i--) {
            if(used[rv.get(i)]) continue;
            List<Integer> list = new ArrayList<>();
            rdfs(rv.get(i), k++, list);
            sccList.add(list);
        }
        return sccList;
    }

}
