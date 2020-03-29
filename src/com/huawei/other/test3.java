package com.huawei.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        ArrayList<String> res = Permutation(s);
        System.out.print("[");
        for (int i = 0; i < res.size(); i++) {
            if (i != res.size() - 1) {
                System.out.print("'" + res.get(i) + "', ");
            } else {
                System.out.print("'" + res.get(i) + "'");
            }
        }
        System.out.print("]");
    }

    public static ArrayList<String> Permutation(String str) {
        if (str  == null) {
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>();
        fun(str.toCharArray(), list, 0);
        Collections.sort(list);

        return list;

    }
    public static void fun(char[] charArray, ArrayList<String> list, int i) {
        if (i == charArray.length - 1) {
            if (!list.contains(String.valueOf(charArray))) {
                list.add(String.valueOf(charArray));
                return;
            }
        } else {
            for (int j = i; j < charArray.length; j++) {
                swap(charArray, i, j);
                fun(charArray, list, i+1);
                swap(charArray, i, j);
            }
        }
    }
    public static void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
