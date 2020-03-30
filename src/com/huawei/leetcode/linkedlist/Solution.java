package com.huawei.leetcode.linkedlist;

public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //2 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode cur = dummy;
        int carry = 0;
        while (p1 != null || p2 != null) {
            int a = (p1 != null) ? p1.val : 0;
            int b = (p2 != null) ? p2.val : 0;
            cur.next = new ListNode((a +  b + carry) % 10);
            cur = cur.next;
            carry = (a +  b + carry) / 10;

            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }
        cur.next = carry != 0 ? new ListNode(carry) : null;
        return dummy.next;
    }

    //141 环形链表
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return false;
    }

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode meetNode = meet(head);
        if (meetNode == null) {
            return null;
        }
        int num = 1;
        ListNode p = meetNode;
        while (p.next != meetNode) {
            p = p.next;
            num++;
        }
        p = head;
        for (int i = 0; i < num; i++) {
            p = p.next;
        }
        ListNode p2 = head;
        while (p != p2) {
            p = p.next;
            p2 = p2.next;
        }
        return p;
    }

    private ListNode meet(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }
}
