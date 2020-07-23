package com.kimi.linkedList;

import com.kimi.domain.Hero;

/**
 * 头节点是虚拟节点
 * @param <T>
 */
public class CircleList<T> {
    Node head =new Node();
    Node tail = head;
    int size;
    public boolean isEmpty() {
        return tail==head;
    }

    public void add(T t) {
        Node add = new Node(t);
        tail.next=add;
        add.next=head;
        tail=add;
        size++;
    }

    public int getSize() {
        return size;
    }

    /**
     * 移动环中头节点位置index步 并移除那个位置的结点
     * @param index
     * @return
     */
    public T headMarginRepalce(int index) {
        if (index<0 || index >size){
            throw new IllegalArgumentException("参数不合法");
        }

        if (isEmpty()) {
            throw new IllegalStateException("队列为空");
        }
        if (size == 1) {
            return null;
        }
        //找到节点的前一位
        Node cur=head;
        int temIndex=1;
        while (cur.next != null && temIndex<index) {
            cur=cur.next;
            temIndex++;
        }
        Node remove;
        remove=cur.next;
        //移除的是第一个节点
        if (cur == head) {
            head.next=cur.next.next;
        }
        //移除的节点是尾节点
        else if( cur.next==tail){
            cur.next.next=null;
            cur.next=head;
            tail=cur;
        }
        else{
            tail.next=head.next;
            tail=cur;
            head.next=cur.next.next;
            cur.next=head;
        }
        size--;
        return (T)remove.obj;
    }

    /**
     * 头结点移动index个位置
     * @param index
     * @return
     */
    public Node headMargin(int index) {
        if (index<0 || index >size){
            throw new IllegalArgumentException("参数不合法");
        }
        //size==index   head插入到尾巴结点  不需要操作
        if (isEmpty() || size == 1 || size==index || index==0) {
           return head;
        }
        //找到新的头结点位置的前一节点
        Node cur=head.next;
        int temIndex=1;
        while (cur!= null && temIndex<index) {
            cur=cur.next;
            temIndex++;
        }
        Node prefirstNoed=head.next;
        //如果cur是尾巴结点那么也不需要移动  cur.next  就是head  不需要移动   不然  就是 head.next=head; 清空结点了
        head.next=cur.next;
        cur.next=head;
        //原来的末结点要只向移动前的head的下已结点
        tail.next=prefirstNoed;
        tail=cur;
        return head;
    }


    public Node removeTail() {
        if (isEmpty()) {
            return null;
        }
        Node cur=head;
        while (cur.next != tail) {
            cur=cur.next;
        }
        Node remove=cur.next;
        cur.next=tail.next;
        tail=cur;
        size--;
        return remove;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (tail == null) {
            return sb.append("]").toString();
        }else{
            Node cur=head.next;
            boolean first=true;
            while (cur != head) {
                if (first){
                    sb.append(cur);
                    first=!first;
                }else{
                    sb.append("->").append(cur);
                }
                cur=cur.next;
            }
            return sb.append("]").toString();
        }
    }

    public static void main(String[] args) {
        CircleList<Hero> circleList = new CircleList<>();
        circleList.add(new Hero("郑一",1));
        circleList.add(new Hero("陈二",2));
        circleList.add(new Hero("张三",3));
        circleList.add(new Hero("李四",4));
        circleList.add(new Hero("王五",5));
//        circleList.add(new Hero("周六",6));
//        circleList.add(new Hero("迟七",7));
//        circleList.add(new Hero("董八",8));
//        circleList.add(new Hero("朱九",9));
       // circleList.headMarginRepalce(3);
        //约瑟夫问题  1-9个 小朋友  从第k个起 每数到第 m（小于小朋友数）个号出队。  问出队顺序
       // k=2,m=4;
        int k=1;
        int m=3;
        circleList.headMargin(k);
        while (!circleList.isEmpty()) {
            int n=circleList.getSize();
            if (m<=n){
                n=n+1;
            }
            circleList.headMargin(m % n);
            Node node = circleList.removeTail();
            System.out.println(node);
        }

    }



}


