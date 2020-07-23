package com.kimi.linkedList;

import com.kimi.domain.Hero;


public class LinkedList<T extends Comparable> {
    private Node head=new Node();
    private Node tail=head;
    private long size;
    /**
     * 插入是否有序  根据compareto 结果排序
     */
    private boolean isPriority;

    public LinkedList(boolean isPriority) {
        this.isPriority=isPriority;
    }

    /**
     * 默认为不排序
     */
    public LinkedList() {
        this.isPriority=false;
    }

    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        Node node = new Node(t);
        //在最后一个元素前是否找到插入的位置
        boolean find=false;
        if (isPriority){
            Node cur=head;
            //找到要插入位置的前一节点   考察的下一个节点不为null
            while (cur.next != null) {
                //如果下一个节点大于当前等于节点  则应该在当前节点后插入要加的元素
                if (t.compareTo(cur.next.obj) <= 0) {
                    find=true;
                    break;
                }
                cur=cur.next;
            }
            //要插入的节点位置在cur之后
            node.next=cur.next;
            cur.next=node;
            //如果是插入在队尾那么更新尾指针
            if (!find) {
                tail=node;
            }
        }else{
            tail.next=node;
            this.tail=node;
        }
        this.size++;
    }



    public boolean delete(T t) {
        if (head == tail || t==null) {
            return false;
        }

        Node cur=head;
        while (cur.next != null) {
            //t放在前边避免空指针
            boolean equals = t.equals(cur.next.obj);
            if (equals){
                cur.next=cur.next.next;
                return true;
            }
            cur=cur.next;
        }

        return false;
    }

    public long size() {
        return size;
    }

    public String toString(){
        if (head  ==  tail) {
            return "";
        }
        Node next=head.next;
        StringBuilder sb = new StringBuilder();
        while (next != null) {
            sb.append(next).append("\t");
            next=next.next;
        }
        return sb.toString();
    }

    public void reverse() {
        if (head == tail) {
            return;
        }
        //新建头节点  遍历原链表  插入新的头节点后
        Node cur=head.next;
        Node newHead = new Node();
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next=newHead.next;
            newHead.next=cur;
            cur=next;
        }
        head=newHead;
    }

    public static void main(String[] args) {
        LinkedList<Hero> heros=new LinkedList<>(true);
        Hero zs=new Hero( "张三", 1);
        heros.add(zs);
        Hero ls = new Hero("李四", 2);
        heros.add(ls);
        Hero ww =  new Hero("王五", 1);
        heros.add(ww);
        System.out.println(heros.toString());
       // heros.delete(zs);
        heros.reverse();
        System.out.println(heros.toString());
    }

}


class Node<T>{
    T obj;
    Node next;

    public Node(T obj, Node next) {
        this.obj = obj;
        this.next = next;
    }

    public Node(T obj) {
        this.obj = obj;
    }

    public Node() {
    }

    @Override
    public String toString() {
        if (obj != null) {
            return obj.toString();
        }
        return null;

    }
}
