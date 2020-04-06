package com.huawei.leetcode.string;

import com.sun.javaws.IconUtil;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class Solution {
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

    //8 字符串转整数
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int index = 0;
        while (index < n && chars[index] == ' ') {
            index++; //去空格
        }
        if (index == n) {
            return 0;
        }
        boolean minus = false;
        if (chars[index] == '-') {
            minus = true;
            index++;
        } else if (chars[index] == '+') {
            index++;
        } else if (!Character.isDigit(chars[index])) {
            return 0;
        }
        int res = 0;
        while (index < n && Character.isDigit(chars[index])) {
            int digit = chars[index] - '0';
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return minus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = 10 * res + digit;
            index++;
        }
        return minus ? -res : res;
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

    //67 二进制求和
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int x;
        int y;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            x = i >= 0 ? a.charAt(i) - '0' : 0;
            y = j >= 0 ? b.charAt(j) - '0' : 0;
            res.append((x + y + carry) % 2);
            carry = (x + y + carry) / 2;
        }
        if (carry > 0) {
            res.append('1');
        }
        return res.reverse().toString();
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

    //243最短单词距离
    public int shortestDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE;
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
                if (p2 > -1) {
                    min = Math.min(min, Math.abs(p1 - p2));
                }
            } else if (words[i].equals(word2)) {
                p2 = i;
                if (p1 > -1) {
                    min = Math.min(min, Math.abs(p1 - p2));
                }
            }
        }
        return min;
    }

    //246 中心对称数
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<Character, Character>(){
            {
                put('0', '0');
                put('1', '1');
                put('6', '9');
                put('8', '8');
                put('9', '6');
            }
        };
        char[] chars = num.toCharArray();
        for (int i = 0, j = chars.length - 1; i <= j;) {
            char l = chars[i];
            char r = chars[j];
            if (!map.containsKey(l) || map.get(l) != r) {
                return false;
            }
        }
        return true;
    }

    //438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character,Integer> smap = new HashMap<>(); //记录s的每个字符和出现的次数
        Map<Character,Integer> pmap = new HashMap<>(); //记录p的每个字符和出现的次数
        for(char ch : p.toCharArray()){
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        List<Integer> res = new ArrayList<>();
        int count = 0; //候选字符的个数
        int len = p.length();
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            smap.put(ch,smap.getOrDefault(ch,0) + 1);
            //如果 p 中包含当前字符，且s的窗口中该字符出现次数不足，则该字符可以作为候选字符，count加一
            if(pmap.containsKey(ch) && smap.get(ch) <= pmap.get(ch)){
                count++;
            }
            //当候选字符个数等于p长度，此时left为起始索引
            if(count == len){
                res.add(left);
            }
            //当窗口大小等于p长度时，窗口左边需要收缩一个字符
            if(right - left + 1 >= len){
                char leftChar = s.charAt(left);
                //判断收缩的这个字符是否是候选字符，是则count减一
                if(pmap.containsKey(leftChar) && smap.get(leftChar) <= pmap.get(leftChar)){
                    count--;
                }
                //窗口收缩一个字符
                smap.put(leftChar,smap.getOrDefault(leftChar,1) - 1);
                left++;
            }
            right++;
        }
        return res;
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

    //820 压缩编码
    //大概思路是这样的，我们的大概思路是删除，Hashset有个特点remove的元素不在set里面的话，是删除不了什么东西的。
    // 例如题目中的样例，time me bell，删除ime 的话是什么都不会发生的, map也是同样的道理。利用这一点，我们可以把每个string元素从第
    // 一位开始从set中删除
    public int minimumLengthEncoding(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        for (String s : words) {
            for (int i = 1; i < s.length(); i++) {
                set.remove(s.substring(i));
            }
        }
        int res = 0;
        for (String s : set) {
            res += s.length() + 1;
        }
        return res;
    }
    //法二
    //把每个字符串都倒序，然后排序，只需要比较相邻的字符串即可
    public int minimumLengthEncoding2(String[] words) {
        int res = 0, len = words.length;
        StringBuffer[] sb = new StringBuffer[len];
        for (int i = 0; i < len; i++) {
            sb[i] = new StringBuffer(words[i]);
            sb[i].reverse();
        }

        Arrays.sort(sb, (o1, o2) -> o1.toString().compareTo(o2.toString()));//可以不用比较器，默认就是字典序
        for (int i = 0; i < len - 1; i++) {
            if (!sb[i + 1].toString().startsWith(sb[i].toString()))
                res += sb[i].length() + 1;
        }
        return res + sb[len - 1].length() + 1;
    }

    public static void main(String[] args) {
        String  str = "+";
    }
}
