package com.kimi.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble implements Sort{
    public void sort(int[] arr) {
        int tem;

        for (int i = 0; i < arr.length-1; i++) {
            boolean isSorted=true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] >arr[j + 1]) {
                    tem = arr[j];
                    arr[j]= arr[j + 1];
                    arr[j + 1]=tem;
                    isSorted=false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }
}
