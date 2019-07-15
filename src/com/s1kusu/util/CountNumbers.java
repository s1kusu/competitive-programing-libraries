package com.s1kusu.util;

public class CountNumbers {

    public static int[][] countNumbers(int[] array){
        int n = array.length;
        int[][] ret = new int[n][];
        int nums = 0;
        for (int i = 0; i < n; i++) {
            if(i == 0 || array[i] != array[i-1]) ret[nums++] = new int[]{array[i], 0};
            ret[nums-1][1]++;
        }
        return ret;
    }

}
