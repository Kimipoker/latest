package com.kimi.array;



public class SparseArray<T> {

    /**
     * 转稀疏数组
     * @param arr
     * @return
     */
    public static   int[][]  toSparseArray(int[][] arr) {
        int rowSize = arr.length;
        int colSize=0;
        int sum=0;
        for (int i = 0; i < rowSize; i++) {
            colSize = arr[i].length;
            for (int j = 0; j < colSize; j++) {
                if (arr[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] spare = new int[sum+1][3];
        spare[0][0] = rowSize;
        spare[0][1] = colSize;
        spare[0][2] = sum;
        int tem=1;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (arr[i][j] != 0) {
                    spare[tem][0]=i;
                    spare[tem][1]=j;
                    spare[tem][2]=arr[i][j];
                    tem++;
                }
            }
        }
        return spare;
    }

    /**
     * 数组还原
     * @param sparseArr
     * @return
     */
    public static int[][] sparseArrReback(int[][] sparseArr) {
        int [][] arr=null;
        if (sparseArr == null) {
            return arr;
        }
        int length = sparseArr[0].length;
        if (length != 3) {
            throw new RuntimeException("数据格式错误");
        }

        int rowSize = sparseArr[0][0];
        int colSize = sparseArr[0][1];
        arr = new int[rowSize][colSize];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        return arr;

    }

    /**
     * 数组tostring
     * @param arr
     * @return
     */
    public static String arrToStr(int[][] arr) {
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[][] arr = new int[3][6];
        arr[1][2]=3;
        arr[2][1]=4;
        int[][] ints = SparseArray.toSparseArray(arr);
        System.out.println(SparseArray.arrToStr(ints));
        int[][] ints1 = SparseArray.sparseArrReback(ints);
        System.out.println(SparseArray.arrToStr(ints1));
    }

}
