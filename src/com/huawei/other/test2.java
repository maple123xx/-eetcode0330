package com.huawei.other;

import java.util.*;

public class test2 {
    public static void solution(int x, int y, int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < x && j < y) {
            if (nums1[i] < nums2[j]) {
                res.add(nums1[i]);
                i++;
            } else if (nums1[i] > nums2[j]) {
                res.add(nums2[j]);
                j++;
            } else {
                i++;
                j++;
            }
        }
        while (i < x) {
            res.add(nums1[i]);
            i++;
        }
        while (j < y) {
            res.add(nums2[j]);
            j++;
        }

        List<Integer> list = new ArrayList<>();
        for (int m : res) {
            if (!list.contains(m)) {
                list.add(m);
            }
        }
        for (int k : list) {
            System.out.print(k + " ");
        }
    }
    public static void main(String[] args) {
        int x = 4;
        int y = 3;
        Integer[] nums1 = {1, 1, 2, 3, 4};
        Integer[] nums2 = {7, 11, 15};
        //solution(x, y, nums1, nums2);
        Integer[] a = new Integer[3];
        ArrayList<Integer> res = new ArrayList<>();
        res.add(3);
        res.add(4);
        res.add(5);
        res.toArray(a);
        for (Integer i :a){
            System.out.println(i);
        }

        List<Integer> cc = new ArrayList<Integer>(){
            {
                add(0);
                add(1);
                add(2);
            }
        };
        //Integer[] dd = (Integer[]) cc.toArray();
        Integer[] ee = cc.toArray(new Integer[0]);
        System.out.println(cc);
    }
}
