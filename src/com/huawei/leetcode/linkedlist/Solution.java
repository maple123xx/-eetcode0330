package com.huawei.leetcode.linkedlist;

public class Solution {
    //2 两数相加
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

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
}
