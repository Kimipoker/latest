package com.kimi.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Select implements Sort{
    /**
     * 第i次循环找到第i小的元素放到第i个位置
     * @param arr
     */
    public  void sort(int[] arr) {
        int minIndex;
        int tem;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i+1; j < arr.length - 1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex=j;
                }
            }

            if (i != minIndex) {
                tem = arr[i];
                arr[i]= arr[minIndex];
                arr[minIndex]=tem;
            }

        }
    }


}
