package com.kimi.segmentTree;

import java.util.Arrays;
import java.util.Random;

/**
 * 线段树
 * @param <E>
 */
public class SegmentTree<E> {
     //保存线段区间值得树节点
     private E[] nodes;
     //数据
     private E[] data;
     //合并函数
     private Merger<E> merger;

    /**
     * 构造线段树,同时指导合并规则
     * @param arr
     * @param merger
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger=merger;
        int dataSize = arr.length;
        data = (E[]) new Object[dataSize];
        System.arraycopy(arr, 0, data, 0, dataSize);
        nodes= (E[]) new Object[dataSize *4];
        buildSegmentTree(0, 0, dataSize - 1);
    }


    /**
     * 创建树根节点序号为treeRootIndex,值表示区间为left到right的树.并返回根节点的值.创建的关键是给树节点赋值
     * @param treeRootIndex
     * @param left
     * @param right
     * @return
     */
    private E buildSegmentTree(int treeRootIndex, int left, int right) {
        E value;
        if (left == right) {
            value=data[left];
        }else {
            int leftChildIndex = leftChildIndex(treeRootIndex);
            int rightChildIndex = rightChildIndex(treeRootIndex);
            int mid = left + (right - left) / 2;
            E leftValue = buildSegmentTree(rightChildIndex, mid+1, right);
            E rightValue = buildSegmentTree(leftChildIndex, left, mid);
            value=merger.meger(leftValue, rightValue);
        }
        nodes[treeRootIndex] = value;
        return value;
    }

    /**
     * 区间线段查询
     * @param start
     * @param end
     * @return
     */
    public E segmentQuery(int start, int end) {
        if (start < 0 || end > data.length - 1) {
            throw new IllegalArgumentException("参数不正确");
        }
        return search(0, 0, data.length - 1, start, end);
    }

    /**
     * 在以treeIndex为根节点(区间为left,right)的树中查询start,end 区间的值.返回查到的值
     * @param treeIndex
     * @param left
     * @param right
     * @param start
     * @param end
     * @return
     */
    private E search(int treeIndex, int left, int right, int start, int end) {
        //到根节点
        if (left==right){
            return data[left];
        }
        //查询的区间重合
        if (left == start && right == end) {
            return nodes[treeIndex];
        }
        int mid = left + (right - left) / 2;
        //查询的范围在右子树
        if (start > mid) {
            int rightChildIndex = rightChildIndex(treeIndex);
            return search(rightChildIndex, mid + 1, right, start, end);
        }
        //查询的范围在左子树
        else if (end < mid + 1) {
            int  leftChildIndex= leftChildIndex(treeIndex);
            return search(leftChildIndex, left, mid, start, end);
        }else {
            int rightChildIndex = rightChildIndex(treeIndex);
            //在右子树中查询mid + 1, right的值
            E e1 = search(rightChildIndex, mid + 1, right, start, end);
            //在左子树中查询left,mid的值
            int  leftChildIndex= leftChildIndex(treeIndex);
            //合并左右子树查出的值
            E e2 = search(leftChildIndex, left, mid, start, end);
            return merger.meger(e1, e2);
        }
    }

    /**
     * 左子树的根节点
     * @param index
     * @return
     */
    public int leftChildIndex(int index) {
        return index*2+1;
    }

    /**
     * 右子树的根节点
     * @param index
     * @return
     */
    public int rightChildIndex(int index) {
        return index*2+2;
    }


    @Override
    public String toString() {
        return "SegmentTree{" +
                "nodes=" + Arrays.toString(nodes) +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public static void main(String[] args) {
        int size=5;
        Integer[] datas = new Integer[size];
        for (int i = 0; i < size; i++) {
            datas[i] = new Random().nextInt(30);
        }
        SegmentTree<Integer> segmentTree = new SegmentTree<>(datas,((e1, e2) -> e1+e2));
        System.out.println(segmentTree);
        Integer integer = segmentTree.segmentQuery(0, 4);
        System.out.println(integer);
    }
}


