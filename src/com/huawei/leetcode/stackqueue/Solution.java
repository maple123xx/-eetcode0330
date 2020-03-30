package com.huawei.leetcode.stackqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    //127 单词接龙
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        boolean[] visit = new boolean[wordList.size() + 1];
        int layer = 1;
        while (!queue.isEmpty()) {
            layer++;
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < wordList.size(); i++) {
                    if (visit[i]) {
                        continue;
                    }
                    String dic = wordList.get(i);
                    if (canChange(dic, cur)) {
                        if (dic.equals(endWord)) {
                            return layer;
                        }
                        queue.add(dic);
                        visit[i] = true;
                    }
                }
            }
        }
        return 0;
    }
    private boolean canChange(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}
