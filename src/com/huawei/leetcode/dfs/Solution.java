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
}
