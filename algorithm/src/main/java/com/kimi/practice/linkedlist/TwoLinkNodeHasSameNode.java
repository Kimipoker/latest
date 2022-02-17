package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

/**
 * 2个链表存在相交,返回相交点
 *
 * 也可以首位相连,找环的起点
 */
public class TwoLinkNodeHasSameNode {
    public static void main(String[] args) {
        LinkNode commonNode = LinkNode.linkNodes(4, 9,14);
        LinkNode node1 = LinkNode.linkNodes(1, 3, 5,7 );
        node1.next(commonNode);
        LinkNode node2 = LinkNode.linkNodes(1, 15,23);
        node2.next(commonNode);
        LinkNode res=findCommonNode(node1, node2);
        System.out.println(res);
    }

    private static LinkNode findCommonNode(LinkNode node1, LinkNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        LinkNode node1Point=node1;
        LinkNode node2Point=node2;
//        boolean hasLink=false;
//        while (node1Point!=null) {
//            if (node1Point==node2Point){
//                return  node1Point;
//            }
//            node1Point=node1Point.next();
//            node2Point=node2Point.next();
//            //node1 遍历完  接着遍历node2.
//            if (node1Point == null && !hasLink) {
//                hasLink=true;
//                node1Point=node2;
//            }
//            if (node2Point == null) {
//                node2Point=node1;
//            }
//        }

        while (node1Point!=node2Point) {
            //node1 遍历完  接着遍历node2.
            if (node1Point == null ) {
                node1Point=node2;
            }else{
                node1Point=node1Point.next();
            }
            if (node2Point == null) {
                node2Point=node1;
            }else{
                node2Point=node2Point.next();
            }
        }
        return node1Point;
    }

    /**
     * 计算长度. 让长的先走
     * @param headA
     * @param headB
     * @return
     */
    public LinkNode getIntersectionNode(LinkNode headA, LinkNode headB) {
        int lenA = 0, lenB = 0;
        // 计算两条链表的长度
        for (LinkNode p1 = headA; p1 != null; p1 = p1.next()) {
            lenA++;
        }
        for (LinkNode p2 = headB; p2 != null; p2 = p2.next()) {
            lenB++;
        }
        // 让 p1 和 p2 到达尾部的距离相同
        LinkNode p1 = headA, p2 = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                p1 = p1.next();
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                p2 = p2.next();
            }
        }
        // 看两个指针是否会相同，p1 == p2 时有两种情况：
        // 1、要么是两条链表不相交，他俩同时走到尾部空指针
        // 2、要么是两条链表相交，他俩走到两条链表的相交点
        while (p1 != p2) {
            p1 = p1.next();
            p2 = p2.next();
        }
        return p1;
    }
}
