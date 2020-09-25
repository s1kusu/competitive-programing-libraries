package com.s1kusu.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大流 - MaxFlow
 */
class MaxFlow {

    class Edge{
        private final int from;
        private final int to;
        private long cap;
        private final int rev;
        Edge(int from, int to, long cap, int rev){
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
        public int from() { return from; }
        public int to() { return to; }
        public long cap() { return cap; }
        public long getFlow() { return g[to][rev].cap; }
    }

    private final int n;
    private final List<Edge> es;
    private final int[] cnt;
    private final int[] level;
    private final int[] iter;
    private final Edge[][] g;

    /**
     * コンストラクタ.<br>
     * N頂点0辺のグラフを生成する.<br>
     * 計算量：O(N)
     * @param n
     */
    MaxFlow(int n){
        this.n = n;
        this.es = new ArrayList<>();
        this.cnt = new int[n];
        this.level = new int[n];
        this.iter = new int[n];
        this.g = new Edge[n][];
    }

    /**
     * fromからtoへ 容量cap 流量0 の辺を追加する.<br>
     * 計算量：O(1)
     * @param from
     * @param to
     * @param cap
     * @return 何番目に追加された辺かを返却（0-indexed）
     */
    public int addEdge(int from, int to, long cap) {
        es.add(new Edge(from, to, cap, cnt[to]++));
        cnt[from]++;
        return es.size()-1;
    }

    /**
     * k番目に追加された辺の容量・流量を newCap・newFlow に変更する.<br>
     * 計算量：O(1)
     * @param k
     * @param newCap
     * @param newFlow
     */
    public void changeEdge(int k, long newCap, long newFlow) {
        Edge e = es.get(k);
        e.cap = newCap - newFlow;
        g[e.to][e.rev].cap = newFlow;
    }

    /**
     * k番目に追加された辺の情報を取得する.<br>
     * 計算量：O(1)
     * @param k
     * @return k番目に追加された辺
     */
    public Edge getEdge(int k) {
        return es.get(k);
    }

    /**
     * 追加済みの辺のリストを取得する.<br>
     * 計算量：O(1)
     * @return 追加済みの辺のリスト
     */
    public List<Edge> getEdges(){
        return es;
    }

    private void build() {
        for (int i = 0; i < n; i++) {
            g[i] = new Edge[cnt[i]];
        }
        int[] idx = new int[n];
        for(Edge e : es) {
            g[e.from][idx[e.from]] = e;
            Edge er = new Edge(e.to, e.from, 0L, idx[e.from]++);
            g[e.to][idx[e.to]++] = er;
        }
    }

    private void bfs(int s) {
        Arrays.fill(level, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        level[s] = 0;
        q.add(s);
        while(!q.isEmpty()) {
            int v = q.poll();
            for(Edge e : g[v]) {
                if(0 < e.cap && level[e.to] < 0) {
                    level[e.to] = level[v] + 1;
                    q.addLast(e.to);
                }
            }
        }
    }

    private long dfs(int s, int t, long f) {
        if(s == t) return f;
        for (int i = iter[s]; i < cnt[s]; i++) {
            Edge e = g[s][i];
            if(0 < e.cap && level[s] < level[e.to]) {
                long d = dfs(e.to, t, Math.min(f, e.cap));
                if(0 < d) {
                    e.cap -= d;
                    g[e.to][e.rev].cap += d;
                    return d;
                }
            }
        }
        return 0L;
    }

    /**
     * 頂点sから頂点tへ流せるだけ流し、その流量を返す.<br>
     * 計算量：O(MN^2) （Mは追加した辺の数）
     * @param s
     * @param t
     * @return s-t間の最大流量
     */
    public long flow(int s, int t) {
        return flow(s, t, Long.MAX_VALUE);
    }

    /**
     * 流量がflowLimitに達するまで頂点sから頂点tへ流せるだけ流し、その流量を返す.<br>
     * 計算量：O(MN^2) （Mは追加した辺の数）
     * @param s
     * @param t
     * @return flowLimitの範囲内でのs-t間の最大流量
     */
    public long flow(int s, int t, long flowLimit) {
        long flow = 0L;
        build();
        while(true) {
            bfs(s);
            if(level[t] < 0) break;
            Arrays.fill(iter, 0);
            long f = 0L;
            do {
                f = dfs(s, t, flowLimit - flow);
                flow += f;
            }while(0 < f);
        }
        return flow;
    }

    /**
     * 残余グラフで頂点sから到達可能な頂点を返す.<br>
     * 事前にflow(s, t)を一度実行した後の結果がs-t間のmincutに対応する.<br>
     * 計算量：O(N+M) （Mは追加した辺の数）
     * @param s
     * @return 残余グラフで頂点sから到達可能な頂点はtrue、そうでない場合はfalseが設定された配列
     */
    public boolean[] minCut(int s) {
        boolean[] ret = new boolean[n];
        ret[s] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(s);
        while(!q.isEmpty()) {
            int v = q.poll();
            for(Edge e : g[v]) {
                int nv = e.to;
                if(ret[nv] || e.cap <= 0) continue;
                ret[nv] = true;
                q.add(nv);
            }
        }
        return ret;
    }

}
