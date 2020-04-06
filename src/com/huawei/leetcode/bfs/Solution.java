package com.huawei.leetcode.bfs;

import java.util.ArrayDeque;
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

    //1162 地图分析
    private final int[] DX = {-1, 1, 0, 0};
    private final int[] DY = {0, 0, -1, 1};
    public int maxDistance(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        //所有陆地入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int[] point = null;
        boolean hasOcean = false;
        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + DX[i];
                int newY = y + DY[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                // 这里直接修改原数组，因此就不需要额外的数组来标志是否访问
                grid[newX][newY] = grid[x][y] + 1;
                hasOcean = true;
                queue.add(new int[]{newX, newY});
            }
        }
        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        // 返回最后一次遍历到的海洋的距离。
        return grid[point[0]][point[1]] - 1;
    }
}
