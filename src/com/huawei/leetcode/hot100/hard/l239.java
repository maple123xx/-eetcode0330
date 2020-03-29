package com.huawei.leetcode.hot100.hard;

import java.util.ArrayList;
import java.util.LinkedList;

public class l239 {
    public int[] maxSlidingWindow(int [] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return nums;
        }
        int[] ret = new int[nums.length - k + 1];
        ArrayList<Integer> res = new ArrayList<>();
        //双端队列，用来记录每个窗口的最大值下标
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
        }
        for (int i = k; i < nums.length; i++) {
            res.add(nums[q.peekFirst()]);
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            while (!q.isEmpty() && (i - q.peekFirst()) >= k) {
                q.pollFirst();
            }
            q.addLast(i);
        }
        res.add(nums[q.peekFirst()]);
        for (int i = 0; i < ret.length; i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
