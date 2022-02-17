package com.kimi.common;

public class LinkNode<T> {
    T obj;
    LinkNode next;

    public LinkNode(T obj, LinkNode next) {
        this.obj = obj;
        this.next = next;
    }

    public static<T> LinkNode  linkNodes(T ...ts) {
        LinkNode first=new LinkNode();
        LinkNode tem=first;
        for (T t : ts) {
            tem.next(new LinkNode(t));
            tem = tem.next();
        }
        return first.next();
    }

//    private LinkNode<T> nodes(LinkNode<T> head, LinkNode<T> newNode) {
//
//    }

    public LinkNode next() {
        return next;
    }
    public void next(LinkNode node) {
         this.next=node;
    }
    public T val() {
        return obj;
    }
    public LinkNode(T obj) {
        this.obj = obj;
    }

    public LinkNode() {
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        if (this.obj != null) {
            sb.append(obj);
        }
        LinkNode next=this.next;
        while (next != null) {
            sb.append(",").append(next.val());
            next=next.next();
        }
        return sb.toString();
    }
}
