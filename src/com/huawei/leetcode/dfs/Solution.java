package com.huawei.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    //17. 电话号码的字母组合
    private HashMap<Character, String> m = new HashMap<Character, String>(){
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };
    List<String> res = new ArrayList<>();
    StringBuilder str = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<String>();
        }
        dfs(digits, 0);
        return res;
    }

    private void dfs(String digits, int k) {
        if (digits.length() == str.length()) {
            res.add(str.toString());
            return;
        }
        String tmp = m.get(digits.charAt(k));
        for (int i = 0; i < tmp.length(); i++) {
            str.append(tmp.charAt(i));
            dfs(digits, k + 1);
            str.replace(str.length() - 1, str.length(), "");
        }
    }

    //131 分割回文串
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        dfs(s, res, new ArrayList<>(), 0);
        return res;
    }
    private void dfs(String s, List<List<String>> res, List<String> tmp, int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(tmp));
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindroom(s, i, j)) {
                tmp.add(s.substring(i, j + 1));
                dfs(s, res, tmp, j + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
    private boolean isPalindroom(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //216. 组合总和 III
    List<List<Integer>> res1 = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, new ArrayList<>(), 1, 0);
        return res1;
    }

    private void dfs(int k, int n, List<Integer> tmp, int num, int i) {
        if (i == k) {
            if (n == 0) {
                res1.add(new ArrayList<>(tmp));
            }
            return;
        }
        for (int j = num; j < 10; j++) {
            tmp.add(j);
            dfs(k, n - j, tmp, j + 1, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

}
