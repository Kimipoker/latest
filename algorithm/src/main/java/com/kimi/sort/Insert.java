package com.kimi.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insert implements Sort{
    public  void sort(int[] arr) {
        int nowInt;
        int insetIndex;
        for (int i = 1; i < arr.length; i++) {
            nowInt=arr[i];
            insetIndex=i-1;
            //如果小于的话  以后插入新的值 后面的值都要往后挪动的.
            while (insetIndex > 0 && arr[insetIndex] > nowInt) {
                arr[insetIndex+1] = arr[insetIndex];
                insetIndex--;
            }

            //需要插入的位置
            if (!(insetIndex+1==i)){
                arr[insetIndex+1]=nowInt;
            }
        }
    }

}
