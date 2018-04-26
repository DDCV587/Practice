package com.ddc.zcy;

import org.junit.Test;

import java.util.Stack;

public class TestZCY1_5 {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(2);
        stack.push(5);
        stack.push(1);

        Stack<Integer> stack1 = ZCY1_5.sortStackByStack(stack);

        for(Integer temp : stack1) {
            System.out.println(temp);
        }
    }
}
