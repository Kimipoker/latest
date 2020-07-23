package com.kimi.sort;

import java.util.Arrays;
import java.util.Random;

public class SortMain {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 6, 0, -3, 9};
        testSort(new Sell(),getArr(8000000));
        testSort(new MergeSort(),getArr(8000000));
    }

    public static int[] getArr(int n) {
        Random random = new Random();
        int[] arr=new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(80000);
        }
        return arr;
    }

    public static void testSort(Sort sort,int[] arr) {
        long be4= System.currentTimeMillis();
        sort.sort(arr);
      //  System.out.println(Arrays.toString(arr));
        long after= System.currentTimeMillis();
        long l = (after - be4)  ;
        System.out.printf("花费%dm秒",l);
    }
}
