package com.ddc.zcy;

import java.util.Stack;

public class ZCY1_5 {

    public static Stack<Integer> sortStackByStack(Stack<Integer> stack) {
        if(stack.empty()) {
            throw new RuntimeException("栈长度为0");
        }
        Stack<Integer> stack1 = new Stack<Integer>();

        while(!stack.empty()) {
            int temp = stack.pop();
            if(stack1.empty() || (stack1.peek() >= temp)) {
                stack1.push(temp);
            }else {
                while(!stack1.empty() && stack1.peek() < temp) {
                    stack.push(stack1.pop());
                }
                stack1.push(temp);
            }
        }

        return stack1;
    }
}