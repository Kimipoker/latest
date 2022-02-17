package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

/**
 * 判断链表中是否有环.
 * 给出环起点
 * https://labuladong.gitee.io/algo/2/17/16/
 */
public class NodeHasCircle {
    public static void main(String[] args) {
        LinkNode nodes = LinkNode.linkNodes(1, 3, 5, 7,9,15,23);
        LinkNode circleStart = new LinkNode(66);
        nodes.next(circleStart);
        circleStart.next(new LinkNode(2));
        circleStart.next().next(new LinkNode(3));
        circleStart.next().next().next(new LinkNode(4));
        circleStart.next().next().next(circleStart);
        boolean b = hasCircle(nodes);
        System.out.println(b);
        LinkNode linkNode = circlePosition(nodes);
        System.out.println("环的起点: "+linkNode.val()+"");
    }

    private static boolean hasCircle(LinkNode head) {
        LinkNode fast=head;
        LinkNode slow=head;
        while (fast!=null && fast.next()!=null){
            fast=fast.next().next();
            slow=slow.next();
            if (fast==slow){
                return true;
            }
        }
        return false;
    }

    private static LinkNode  circlePosition(LinkNode head) {
        LinkNode fast=head;
        LinkNode slow=head;
        while (fast!=null && fast.next()!=null){
            fast=fast.next().next();
            slow=slow.next();
            if (fast==slow){
                slow=head;
                while (fast!=slow){
                    fast=fast.next();
                    slow=slow.next();
                }
                return fast;
            }
        }
        return null;
    }
}
