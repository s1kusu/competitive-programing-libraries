package com.s1kusu.algorithm.graph;

/**
 * 2SAT.
 */
class TwoSAT {

    private final int n;
    private final SCC scc;
    private int s, t;

    /**
     * N変数の2SATを生成する.<br>
     * 計算量：O(N)
     * @param n
     */
    public TwoSAT(int n) {
        this.n = n;
        this.scc = new SCC(n*2);
    }

    /**
     * (a = f) ∨ (b = g) というクローズを足す.<br>
     * 計算量：O(1)
     * @param a
     * @param f
     * @param b
     * @param g
     */
    public void addClause(int a, boolean f, int b, boolean g) {
        int a1 = a*2, a2 = a*2, b1 = b*2, b2 = b*2;
        if(f) a1++;
        else a2++;

        if(g) b2++;
        else b1++;

        scc.addEdge(a1, b1);
        scc.addEdge(b2, a2);
    }

    /**
     * 条件を満たす割当が存在するか判定する.<br>
     * 計算量：O(N+M) (Mは足したクローズの数)
     * @return 割当が存在するならばその割当を保持した配列、存在しなければnull.
     */
    public boolean[] build() {
        boolean[] ret = new boolean[n];
        scc.build();
        for (int i = 0; i < n; i++) {
            s = scc.getOrder(i*2); t = scc.getOrder(i*2 +1);
            if(s == t)
                return null;
            else if(t < s)
                ret[i] = true;
            else
                ret[i] = false;
        }
        return ret;
    }

}
