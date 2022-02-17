package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个有序链表
 */
public class MergeKOderedLinkNode {

    public static LinkNode<Integer> merge(LinkNode<Integer> ...links) {

        int i=0;
        LinkNode<Integer> head = new LinkNode<>();
        LinkNode<Integer> tem=head;
        PriorityQueue<LinkNode<Integer>> priorityQueue= new PriorityQueue<>(Comparator.comparingInt(LinkNode::val));
        for (LinkNode<Integer> link : links) {
            priorityQueue.add(link);
        }
        while (!priorityQueue.isEmpty()){
            LinkNode<Integer> newNode = priorityQueue.poll();
            tem.next(newNode);
            tem=newNode;
            LinkNode next = newNode.next();
            if (next!= null) {
                priorityQueue.add(next);
            }
        }
        return head.next();
    }

    public static void main(String[] args) {
        LinkNode linkNode1 = LinkNode.linkNodes(1, 3, 5, 7,9);
        LinkNode linkNode2 = LinkNode.linkNodes(2,4 , 8, 10,14);
        LinkNode linkNode3 = LinkNode.linkNodes(6,9 , 10, 18,20);
        LinkNode merge = merge(linkNode1, linkNode2,linkNode3);
        System.out.println(merge.toString());
    }
}
