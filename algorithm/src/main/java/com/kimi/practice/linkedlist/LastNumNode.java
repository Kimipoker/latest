package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

/**
 * 单链表的倒数第 k 个节点
 */
public class LastNumNode {
    /**
     * 移除单链表倒数第num个节点
     * @param head
     * @param num
     * @return
     */
    public static LinkNode removeLastNode(LinkNode head ,int num) {
        //这里要用 虚拟节点 不然移除头节点的时候会报错
        LinkNode dummy = new LinkNode(0, head);
        LinkNode linkNode = lastNode(dummy, num + 1);
        linkNode.next(linkNode.next().next());
        return dummy.next();
    }

    public static LinkNode lastNode(LinkNode head ,int num) {
        LinkNode fast=head;
        LinkNode slow=head;
        for (int i = 0; i <num ; i++) {
            if (fast == null) {
                throw new RuntimeException("num超过节点数");
            }
            fast=fast.next();
        }
        while (fast != null) {
            fast=fast.next();
            slow=slow.next();
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkNode nodes = LinkNode.linkNodes(1, 3, 5, 7,9);
        LinkNode linkNode = lastNode(nodes, 2);
        System.out.println(linkNode.val());

        LinkNode newNodes = removeLastNode(nodes, 5);
        System.out.println(newNodes);
    }
}
