package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

/**
 * 单链表的中点
 */
public class FindMidleNode {
    public static void main(String[] args) {
        LinkNode nodes = LinkNode.linkNodes(1, 3, 5, 7,9,15,23);
        LinkNode linkNode = mid(nodes);
        System.out.println(linkNode.val());
    }

    private static LinkNode mid(LinkNode head) {
        LinkNode fast=head;
        LinkNode slow=head;
        while (fast!=null && fast.next()!=null){
            fast=fast.next().next();
            slow=slow.next();
        }
        return slow;
    }
}
