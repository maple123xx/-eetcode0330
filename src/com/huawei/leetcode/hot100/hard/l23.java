package com.huawei.leetcode.hot100.hard;

public class l23 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int mid = (i + j) / 2;
        ListNode left = mergeKLists(lists, i, mid);
        ListNode right = mergeKLists(lists, mid + 1, j);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode r = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                r.next = l1;
                l1 = l1.next;
            } else {
                r.next = l2;
                l2 = l2.next;
            }
            r = r.next;
        }
        if (l1 != null) {
            r.next = l1;
        }
        if (l2 != null) {
            r.next = l2;
        }
        return dummy.next;
    }
}
