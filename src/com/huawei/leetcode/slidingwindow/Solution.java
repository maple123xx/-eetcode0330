package com.huawei.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    //3 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> m = new HashMap<>();
        char[] ch = s.toCharArray();
        int l = 0;
        int r = 0;
        int res = 0;
        while (r < ch.length) {
            char c = ch[r];
            m.put(c, m.getOrDefault(c, 0) + 1);
            r++;
            while (m.get(c) > 1) {
                m.put(ch[l], m.get(ch[l]) - 1);
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    //438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> pmap = new HashMap<>();
        HashMap<Character, Integer> smap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pmap.put(c, pmap.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int len = p.length();
        int count = 0;
        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            smap.put(c, smap.getOrDefault(c, 0) + 1);
            if (pmap.containsKey(c) && smap.get(c) <= pmap.get(c)) {
                count++;
            }
            if (count == len) {
                res.add(left);
            }
            if (right - left + 1 >= len) {
                char leftChar = s.charAt(left);
                if (pmap.containsKey(leftChar) && smap.get(leftChar) <= pmap.get(leftChar)) {
                    count--;
                }
                smap.put(leftChar, smap.getOrDefault(leftChar, 1) - 1);
                left++;
            }
            right++;
        }
        return res;
    }
}
