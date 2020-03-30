package com.huawei.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
    //1 两数之和
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (m.containsKey(tmp)) {
                res[0] = i;
                res[1] = m.get(tmp);
                return res;
            }
            m.put(nums[i], i);
        }
        return res;
    }

    //4 寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i;
        for (i = 0; i < nums1.length; i++) {
            res[i] = nums1[i];
        }
        int j;
        for (i = nums1.length, j = 0; i < res.length; i++, j++) {
            res[i] = nums2[j];
        }
        Arrays.sort(res);
        int n = res.length;
        if (n % 2 == 0) {
            return (double)(res[(n - 1) / 2] + res[n / 2]) / 2 ;
        } else {
            return (res[n / 2]);
        }
    }

    //11 盛水最多的容器
    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0, j = height.length - 1; i < j ;) {
            int minHeight = Math.min(height[i], height[j]);
            res = Math.max(res, minHeight * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    //15 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) { //重复了
                continue;
            }
            int target = -nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]){
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }

    //137 只出现一次的束自Ⅱ
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;
            int count = 0;
            for (int elem : nums) {
                if ((elem & bit) != 0) {
                    count++;
                }
            }
            if ( count % 3 != 0) {
                res |= bit;
            }
        }
        return res;
    }
}
