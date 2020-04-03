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

    //454. 四数相加 II
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                map1.put(A[i] + B[j], map1.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (map1.containsKey(-(C[i] + D[j]))) {
                    res += map1.get(-(C[i] + D[j]));
                }
            }
        }
        return res;
    }

    //461 汉明距离
    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int count = 0;
        while (a > 0) {
            a &= (a - 1);
            count++;
        }
        return count;
    }

    //560 和为k的子数组
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // 扫描一遍数组, 使用map记录出现的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    //581. 最短无序连续子数组
    public int findUnsortedSubarray(int[] nums) {
        int m = nums[0];
        int n = nums[nums.length - 1];
        int r = -2, l = -1; //防止初始即有序
        for (int i = 1, j = nums.length - 2; i < nums.length && j >= 0; i++, j--) {
            m = Math.max(m, nums[i]);
            n = Math.min(n, nums[j]);
            if (m != nums[i]) {
                r = i;
            }
            if (n != nums[j]) {
                l = j;
            }
        }
        return r - l + 1;
    }

    //621 任务调度器
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            count[tasks[i] - 'A']++;
        }
        Arrays.sort(count);
        int maxCount = 0;
        for (int i = 25; i >= 0; i--) {
            if (count[i] != count[25]) {
                break;
            }
            maxCount++;
        }
        return Math.max((count[25] - 1) * (n + 1) + maxCount, tasks.length);
    }
}
