package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

public class Reverse {
    public static void main(String[] args) {
        LinkNode nodes = LinkNode.linkNodes(1, 3, 5, 7,9,15,23);
        LinkNode res=reverse(nodes);
        System.out.println(res);
    }

    /**
     * 递归翻转链表. 返回反转后的头
     * @param nodes
     * @return
     */
    private static LinkNode reverse(LinkNode nodes) {
        if (nodes == null ||nodes.next() == null) {
            return nodes;
        }
        LinkNode next = nodes.next();
        LinkNode reverse = reverse(next);
        next.next(nodes);
        nodes.next(null);
        return reverse;
    }
}
