package com.huawei;

import java.util.*;

public class util {

    //252 会议室
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? (o1[1] - o2[1]) : o1[0] - o2[0]);
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                return false;
            }
            end = intervals[i][1];
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.toString(5, 2)); //十进制转二进制
//        System.out.println(Integer.toBinaryString(5));//十进制转二进制的另一种方法
//        System.out.println(Integer.parseInt("101", 2)); //二进制转十进制
//        System.out.println(0b100);  //二进制数以0b开头
//        System.out.println(0100);   //八进制以0开头
//        System.out.println(0x100);  //十六进制以0x开头
        //map的put是放在末尾，也就是遍历的时候，先放进去的会先遍历到
//        Iterator<HashMap.Entry<Integer, Integer>> it = map.entrySet().iterator();
//        it.next();  //删除第一个元素
//        it.remove();
        //另外声明一下，计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有
        // 效防止了 left 和 right 太大直接相加导致溢出。
        //System.out.println(String.format("%s,,%s", "huang", "xixi"));
        String[] ss = {"abc", "dfaf", "bac", "abd"};
        //先按长度排序，再按字典序
//        Arrays.sort(ss, (o1, o2) -> o1.length() == o2.length() ? (o1.compareTo(o2)) : (o1.length() - o2.length()));
//        for (String s : ss) {
//            System.out.println(s);
//        }
        int[][] arr = {{7,10},{2,4}};
        System.out.println(new util().canAttendMeetings(arr));
    }
}
