package com.huawei.leetcode.zhenti;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

import java.util.*;

class LogSystem {
    private Map<Integer, String> cache;
    public LogSystem() {
        cache = new HashMap<>();
    }

    public void put(int id, String timestamp) {
        cache.put(id, timestamp);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> list = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            int index = map.get(gra);
            if (entry.getValue().substring(0, index).compareTo(s.substring(0, index)) >= 0
                    && e.substring(0, index).compareTo(entry.getValue().substring(0, index)) >= 0) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LogSystem log = new LogSystem();
        log.put(1, "2017:01:01:23:59:59");
        log.put(2,"2017:01:02:23:59:59");
        //log.put(3,"2016:01:01:00:00:00");
        //List<Integer> res = log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
        List<Integer> res = log.retrieve("2017:01:01:23:59:58","2017:01:02:23:59:58","Second");
        System.out.println(res);

    }
}