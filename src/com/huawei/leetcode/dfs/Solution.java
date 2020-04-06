package com.huawei.leetcode.dfs;

import com.sun.corba.se.spi.ior.IdentifiableContainerBase;

import java.text.CollationElementIterator;
import java.util.*;

public class Solution {
    //17. 电话号码的字母组合
    private HashMap<Character, String> map = new HashMap<Character, String>(){
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
    private List<String> res = new ArrayList<>();
    private StringBuilder str = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }
        dfs(digits, 0);
        return res;
    }

    private void dfs(String digits, int k) {
        if (str.length() == digits.length()) {
            res.add(str.toString());
            return;
        }
        String tmp = map.get(digits.charAt(k));
        for (int j = 0; j < tmp.length(); j++) {
            str.append(tmp.charAt(j));
            dfs(digits, k + 1);
            str.replace(str.length() - 1, str.length(), "");//删除最后一个字符
        }
    }

    //22括号生成
    private List<String> res2 = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return res2;
        }
        dfs("", 0, 0, n);
        return res2;
    }

    private void dfs(String str, int left, int right, int n) {
        if (left > n || right > n || right > left) {
            return;
        }
        if (left == n && right == n) {
            res2.add(str);
            return;
        }
        dfs(str + "(", left + 1, right, n);
        dfs(str + ")", left, right + 1, n);
    }

    //39 组合总和
    private List<List<Integer>> res3 = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) {
            return res3;
        }
        dfs(candidates, new ArrayList<>(), target, 0, 0);
        return res3;
    }

    private void dfs(int[] candidates, List<Integer> tmp, int target, int sum, int start) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            //在这里面不能给tmp排序，相当于重排了tmp，打乱了顺序
            res3.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = start; j < candidates.length; j++) {
            tmp.add(candidates[j]);
            dfs(candidates, tmp, target, sum + candidates[j], j);
            tmp.remove(tmp.size() - 1);
        }
    }

    //40 组合总和Ⅱ
    private List<List<Integer>> res4 = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null) {
            return res4;
        }
        Arrays.sort(candidates);
        dfs2(candidates, new ArrayList<>(), target, 0, 0);
        return res4;
    }

    private void dfs2(int[] candidates, List<Integer> tmp, int target, int sum, int start) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
//            System.out.println(tmp);
//            List<Integer> tmp2 = new ArrayList<>(tmp);
//            Collections.sort(tmp2);
//            if (!res4.contains(tmp2)) {
//                res4.add(new ArrayList<>(tmp2));
//            }
            res4.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = start; j < candidates.length; j++) {
            tmp.add(candidates[j]);
            dfs2(candidates, tmp, target, sum + candidates[j], j + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    //46 全排列
    //另一种求全排列的方法
    private List<List<Integer>> res5 = new ArrayList<>();
    private void back(int[] nums, List<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res5.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            tmp.add(nums[i]);
            back(nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
    //求全排列的第三个方法
    List<List<Integer>> res8 = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res8;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res8.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    //51 N皇后
    public static List<List<String>> output;
    public List<List<String>> solveNQueens(int n) {
        output = new ArrayList<>();
        // 声明一个长度为n的数组用来代表 第n行棋子是在第result[n]列
        int[] result = new int [n];
        calnQueens(0, n, result);
        return output;
    }

    // n 皇后问题 row代表计算到了到第row行
    private static void calnQueens(int row, int n, int[] result){
        if (row == n){
            // 到达第n行代表已经得到一个将解决方案 直接返回即可
            // 根据result数组将结果加入到output列表中
            getPrint(result);
            return;
        }
        // 若不是第n行 则说明需要继续判断该行棋子应该在那一列
        for (int column = 0; column < n; column++){
            // 判断第row行 放置在column列的棋子是否满足要求
            if (isOK(row, column, result)){
                result[row] = column;
                // 递归判断下一行的情况
                calnQueens(row + 1, n, result);
            }
            // 不满足要求 回溯下一列 对应操作column++
        }
    }

    // row代表行数 column代表列数 result代表满足规则的棋子在第n行中的位置
    private static boolean isOK(int row, int column, int[] result){
        // 判断棋子的位置是否正确 不正确返回false
        for (int i = 0; i < row; i++){
            // 第一个条件排除的是相同列的问题
            // 第二个条件排除的是对角线列的左下角
            // 第三个条件排除的是对角线列的右下角
            if (column == result[i] || column == result[i] - row + i || column == result[i] + row - i){
                return false;
            }
        }
        return true;
    }

    private static void getPrint(int[] result){
        List<String> one = new ArrayList<>();
        for (int row = 0; row < result.length; row++){
            // 一行一个StringBuilder
            StringBuilder str = new StringBuilder();
            for (int column = 0; column < result.length; column++){
                if (column == result[row]){
                    str.append("Q");
                }else{
                    str.append(".");
                }
            }
            one.add(str.toString());
        }
        output.add(one);
    }

    //60 第k个排列
    private int count = 0;
    private List<List<Integer>> res6 = new ArrayList<>();
    private void back(int[] nums, List<Integer> tmp, int[] visited, int k) {
        if (tmp.size() == nums.length) {
            count++;
            if (count == k) {
                res6.add(new ArrayList<>(tmp));
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            tmp.add(nums[i]);
            back(nums, tmp, visited, k);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    public String getPermutation(int n, int k) {
        int[] visited = new int[n];
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        back(nums, new ArrayList<>(), visited, k);
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>(res6.get(0));
        for (int elem : list) {
            sb.append(elem);
        }
        return sb.toString();
    }

    public List<List<Integer>> Permutation(int[] nums) {
        int[] visited = new int[nums.length];
        back(nums, new ArrayList<>(), visited);
        return res5;
    }

    //77 组合
    private List<List<Integer>> res7 = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new ArrayList<>());
        return res7;
    }

    private void dfs(int n, int k, int start, List<Integer> tmp) {
        if (tmp.size() == k) {
            res7.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = start; j <= n; j++) {
            tmp.add(j);
            dfs(n, k, j + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    //78子集
    private List<List<Integer>> res10 = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>());
        return res10;
    }
    private void dfs(int[] nums, int i, List<Integer> tmp) {
        res10.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            dfs(nums, j + 1, tmp);
            tmp.remove(tmp.size() - 1);
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
    private List<List<Integer>> res1 = new ArrayList<>();
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

    //494 目标和
    private int count2 = 0;
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        dfs(nums, S, 0, sum);
        return count2;
    }

    private void dfs(int[] nums, int S, int i, int sum) {
        if (i == nums.length) {
            if (S == sum) {
                count2++;
            }
            return;
        }
        dfs(nums, S, i + 1, sum + nums[i]);
        dfs(nums, S, i + 1, sum - nums[i]);
    }

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        List<List<Integer>> res = new Solution().combinationSum2(candidates, 5);
        System.out.println(res);
    }

}
