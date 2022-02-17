package com.kimi.bts;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * @param <E>
 */
public class BTS<E extends Comparable<E>>  {

    /**
     * 可增加深度,增加每个节点的子孙节点数,
     * 增加count来支持
     */
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node root;

    private int size;

    /**
     * 添加树节点dian
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
        size++;
    }

    /**
     * 向当前根节点为node的树,添加元素e 并返回新的根节点
     *
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node != null) {
            if (e.compareTo(node.e) < 0) {
                node.left = add(node.left, e);
            } else if (e.compareTo(node.e) > 0) {
                node.right = add(node.right, e);
            }
            return node;
        }
        Node newNode = new Node(e);
        return newNode;
    }

    /**
     * 获取树的最小值
     * @return
     */
    public E getMin() {
        if (size == 0) {
            throw new IllegalArgumentException("树为空");
        }
        return minNode(root).e;
    }

    /**
     * 获取树的最大值
     * @return
     */
    public E getMax() {
        if (size == 0) {
            throw new IllegalArgumentException("树为空");
        }
        return maxNode(root).e;
    }
    /**
     * 删除最小节点
     * @return
     */
    public E removeMin() {
        if (size == 0) {
            throw new IllegalArgumentException("树为空");
        }
        E min = getMin();
        root=removeMin(root);
        return min;
    }
    /**
     * 删除最大节点
     * @return
     */
    public E removeMax() {
        if (size == 0) {
            throw new IllegalArgumentException("树为空");
        }
        E max = getMax();
        root=removeMax(root);
        return max;
    }
    /**
     * 返回给定根节点树  移除最小元素后的新的根节点树
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left!=null) {
            node.left = removeMin(node.left);
            return node;
        }
        else if (node.right!=null){
            Node newRoot = node.right;
            size--;
            return newRoot;
        }else{
            size--;
            return null;
        }

    }
    /**
     * 返回给定根节点树  移除最大元素后的新的根节点树
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right!=null) {
            node.right = removeMax(node.right);
            return node;
        }
        else if (node.left!=null){
            Node newRoot = node.left;
            size--;
            return newRoot;
        }else{
            size--;
            return null;
        }
    }
    /**
     * 返回给定根节点树的最大值
     * @param node
     * @return
     */
    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    /**
     * 返回给定根节点树的最小值
     * @param node
     * @return
     */
    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    /**
     * 前序遍历
     */
    public void midVisit() {
        midVisit(root);
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    private void midVisit(Node node) {
        if (node != null) {
            midVisit(node.left);
            System.out.printf("%3d ", node.e);
            midVisit(node.right);
        }
    }

    /**
     * 包含
     * @param e
     * @return
     */
    public boolean contain(E e) {
        if (e == null) {
            throw new IllegalArgumentException("传入元素不能为空");
        }
        return  contain(root, e);
    }

    private boolean contain(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }else if(node.e.compareTo(e)>0){
            return contain(node.left, e);
        }else{
            return contain(node.right, e);
        }

    }


    /**
     * 非递归前序遍历
     */
    public void noRecursionVist() {
        if (root == null) {
            throw new IllegalArgumentException("树为空");
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelVisit() {
        if (root == null) {
            throw new IllegalArgumentException("树为空");
        }
        Queue<Node> queue =new  LinkedList <>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node pop = queue.remove();
            System.out.println(pop.e);
            if (pop.left != null) {
                queue.add(pop.left);
            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }
    }


    /**
     * 删除任意元素
     * @param e
     * @return
     */
    public void remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException("待移除元素为空");
        }
        root=remove(root, e);
    }

    /**
     * 给定根节点树,返回移除指定元素后的新根节点
     * @param node
     * @param e
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        //指定移除元素比当前根节点树元素大  那么需要在右子树中去移除  把移除后的新右子树重新挂载
        if (node.e.compareTo(e)<0) {
            node.right=remove(node.right,e);
            return node;
        }
        //指定移除元素比当前根节点树元素小  那么需要在左子树中去移除  把移除后的新左子树重新挂载
        else if (node.e.compareTo(e)>0){
            node.left=remove(node.left,e);
            return node;
        }
        //当前根节点是待移除元素
        else{
            size--;
            //左子树为空 返回右子树
            if (node.left == null) {
                Node right = node.right;
                node.right=null;
                return right;
            }
            //右子树为空 返回左子树
            else if (node.right==null){
                Node left = node.left;
                node.left = null;
                return left;
            }
            //左右子树都不为空  返回右子树的最小节点(也可返回左子树的最大节点)
            else{
                Node newRoot = removeMin(node.right);
                newRoot.right=node.right;
                newRoot.left=node.left;
                return newRoot;
            }

        }

    }

    public void rank() {

    }

    public void select() {

    }
}
