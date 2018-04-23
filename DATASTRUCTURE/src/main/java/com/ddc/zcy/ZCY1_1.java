package com.ddc.zcy;

import java.util.Stack;

public class ZCY1_1 {

    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();


    public int getMin() {
        if (stack2.empty()) {
            throw new RuntimeException("无数据");
        } else {
            return stack2.peek();
        }
    }

    public void push(Integer data) {
        stack1.push(data);
        try {
            int min = (data < getMin()) ? data : getMin();

            if (min > data) {
                while (!stack2.empty()) {
                    stack2.pop();
                }
            }
            stack2.push(min);
        }catch(RuntimeException e) {
            if(stack2.empty()) {
                stack2.push(data);
            }
        }

    }

    public int pop() {
        if (stack1.empty()) {
            throw new RuntimeException("无数据");
        } else {
            int value = stack1.pop();
            if (value == stack2.peek()) {
                stack2.pop();
            }
            return value;
        }

    }
}
