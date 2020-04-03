package com.huawei.leetcode.string;

import java.util.Arrays;
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

    //139 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] memo = new boolean[n + 1];
        // memo[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }

    //516 最长回文子序列
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
