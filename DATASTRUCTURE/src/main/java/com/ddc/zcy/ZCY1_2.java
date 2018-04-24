package com.ddc.zcy;

import java.util.Stack;

public class ZCY1_2 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void add(int i) {
        if (stack2.empty()) {
            stack1.push(i);
        } else {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(i);
        }
    }

    public int poll() {
        if(!stack2.empty()) {
            return stack2.pop();
        }
        if (stack1.empty()) {
            throw new RuntimeException("无数据");
        } else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }

            return stack2.pop();
        }
    }

    public int peek() {
        if(!stack2.empty()) {
            return stack2.peek();
        }
        if (stack1.empty()) {
            throw new RuntimeException("无数据");
        } else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }

            return stack2.peek();
        }
    }
}
