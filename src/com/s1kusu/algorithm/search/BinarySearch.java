package com.s1kusu.algorithm.search;

class BinarySearch {

    /**
     * 二分探索を行います。
     * 計算量：O(logn)
     * @param array ソート済み配列（昇順）
     * @param key 探索値
     * @return 探索値以上となる最小のindex
     */
    public static int binarySearch(int[] array, int key){
        int ok = array.length;
        int ng = -1;
        while(1 < Math.abs(ok - ng)){
            int mid = (ok + ng) / 2;
            if(key <= array[mid]) ok = mid;
            else ng = mid;
        }
        return ok;
    }
}
