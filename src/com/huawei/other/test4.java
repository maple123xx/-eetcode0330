package com.huawei.other;

import java.util.*;

public class test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int myMax = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Map.Entry<Integer,Integer>> res = sortByValueFloatDesc(map);
        System.out.println(res.get(0).getKey());
        System.out.println(res.get(0).getValue());
    }
    private static List<Map.Entry<Integer,Integer>> sortByValueFloatDesc(Map<Integer, Integer> nowPartTwoData){
        //这里将map.entrySet()转换成list
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(nowPartTwoData.entrySet());
        //然后通过比较器来实现排序
        //降序排序
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list;
    }

}
