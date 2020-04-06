package com.huawei.leetcode.stackqueue;

import java.util.*;

class MaxStack {

    /** initialize your data structure here. */
    /** initialize your data structure here. */
    List<Integer> stack;
    public MaxStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.add(x);
    }

    public int pop() {
        int tmp = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return tmp;
    }

    public int top() {
        return stack.get(stack.size() - 1);
    }

    public int peekMax() {
        return Collections.max(stack);
    }

    public int popMax() {
        int index = stack.lastIndexOf(peekMax());
        int res = peekMax();
        stack.remove(index);
        return res;
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.top());
        System.out.println(stack.peekMax());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }
}
