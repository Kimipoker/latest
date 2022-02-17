package com.kimi.practice.linkedlist;

import com.kimi.common.LinkNode;

/**
 * 合并2个有序链表
 */
public class MergeTwoOderedLinkNode {

    public static LinkNode<Integer> merge(LinkNode<Integer> link1,LinkNode<Integer> link2) {
        if (link1 == null) {
            return  link2;
        }
        if (link2 == null) {
            return  link1;
        }
        LinkNode<Integer> newHead = new LinkNode<>();
        LinkNode<Integer> tem = newHead;
        LinkNode<Integer> positionLink1=link1;
        LinkNode<Integer> positionLink2=link2;
        while (positionLink1 != null && positionLink2 != null) {
            Integer val1 = positionLink1.val();
            Integer val2 = positionLink2.val();
            if (val1 <= val2) {
//                LinkNode next = positionLink1.next();
//                positionLink1.next(null);
//                tem.next(positionLink1);
//                positionLink1=next;
                tem.next(positionLink1);
                positionLink1=positionLink1.next();;
            }else {
//                LinkNode next = positionLink2.next();
//                positionLink2.next(null);
//                tem.next(positionLink2);
//                positionLink2=next;

                tem.next(positionLink2);
                positionLink2=positionLink2.next();;
            }
            tem=tem.next();
        }
        if (positionLink1 != null) {
            tem.next(positionLink1);
        }
        if (positionLink2 != null) {
            tem.next(positionLink2);
        }
        return newHead.next();
    }

    public static void main(String[] args) {
        LinkNode linkNode1 = LinkNode.linkNodes(1, 3, 5, 7,9);
        LinkNode linkNode2 = LinkNode.linkNodes(2,4 , 8, 10,14);
        LinkNode merge = merge(linkNode1, linkNode2);
        System.out.println(merge.toString());
    }
}
