package com.kimi.sort;

public class Sell implements Sort {
    @Override
    public void sort(int[] arr) {

        int val;
        int insertIndex;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i =gap; i < arr.length; i ++) {
                insertIndex = i;
                val = arr[i];
//                insertIndex = i - gap;
//                while (insertIndex > 0 && arr[insertIndex] > val) {
//                    arr[insertIndex + gap] = arr[insertIndex];
//                    insertIndex -= gap;
//                }
                while (insertIndex-gap >= 0 && val<arr[insertIndex-gap]) {
                    arr[insertIndex] = arr[insertIndex-gap];
                    insertIndex -= gap;
                }

                arr[insertIndex] = val;

            }


        }
    }
}
