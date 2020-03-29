package com.huawei.other;

public class test1 {
    public static int solution(int n, String[] fruits) {
        int count = 0;
        for (int i = 0; i < fruits.length; i++) {
            String[] s = fruits[i].trim().split(" ");
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals("apple")) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
