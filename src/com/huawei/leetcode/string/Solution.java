package com.huawei.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;

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

    //5 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int start = 0; //最长子串的起点和终点
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = centerExpand(s, i, i);
            int len2 = centerExpand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                start = i - (len - 1) / 2;
                maxLen = len;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int centerExpand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1; //r-l+1-2
    }

    //9 回文数
    public boolean isPalindrome(int x) {
        int numSrc = x;
        long numDst = 0;
        if (x < 0) {
            return false;
        }
        while (numSrc != 0) {
            numDst = numDst * 10 + numSrc % 10;
            numSrc /= 10;
        }
        return numDst == x;
    }

    //10 正则表达式匹配
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sINdex = 0;
        int pIndex = 0;
        return matchCore(s.toCharArray(), 0, p.toCharArray(), 0);
    }

    private boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
}
