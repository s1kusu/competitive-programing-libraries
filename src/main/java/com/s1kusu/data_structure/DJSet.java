package com.s1kusu.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Disjoint Set (UnionFindTree).<br>
 * 互いに素な集合を保持するクラス.
 */
public class DJSet {
    /**
     * 各要素の親を保持する配列.<br>
     * 自信が根である要素は負の数を持つ.
     * （負の数の場合、絶対値がその集合の要素数）
     */
    private int[] upper;
    /** 要素数 */
    private int N;

    /**
     * コンストラクタ.
     * @param n 対象となる要素数
     */
    public DJSet(int n){
        this.N = n;
        this.upper = new int[n];
        Arrays.fill(upper, -1);
    }

    /**
     * 要素xが含まれる集合のrootを求める.<br>
     * rootを求める過程で経路圧縮しrootが親になるようにする.
     * @param x 要素x
     * @return 要素xが含まれる集合のroot
     */
    public int root(int x){
        return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
    }

    /**
     * 要素xと要素yが同じ集合か判定する.
     * @param x 要素x
     * @param y 要素y
     * @return 要素xと要素yが同じ集合であればtrue、そうでなければfalseを返す。
     */
    public boolean same(int x, int y){
        return root(x) == root(y);
    }

    /**
     * 要素xと要素yを含むそれぞれの集合を合併する.
     * 要素数の多い集合のrootを残し、少ないほうのrootをその下に合併する。
     * @param x 要素x
     * @param y 要素y
     * @return 要素xと要素yを含むそれぞれの集合を合併できたらtrue、もともと同じ集合で合併しなかったらfalseを返す。
     */
    public boolean union(int x, int y){
        x = root(x);
        y = root(y);
        if(x != y){
            if(upper[y] < upper[x]){
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
     * 集合の数を数える.
     * @return 集合の数
     */
    public int countSet(){
        int c = 0;
        for(int u :upper){
            if(u < 0) c++;
        }
        return c;
    }

    /**
     * 要素ｘが含まれる集合の要素数を数える.
     * @param x 要素ｘ
     * @return 要素ｘが含まれる集合の要素数
     */
    public int countElement(int x){
        return upper[root(x)] * -1;
    }

    /**
     * 連結成分ごとの頂点リストのリストを取得する.
     * @return 連結成分ごとの頂点リストのリスト
     */
    public List<List<Integer>> groups(){
        List<List<Integer>> groups = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Integer root = upper[i] < 0 ? i : upper[i];
            if(!map.containsKey(root)) {
                List<Integer> list = new ArrayList<>();
                map.put(root, list);
                groups.add(list);
            }
            map.get(root).add(i);
        }
        return groups;
    }
}
