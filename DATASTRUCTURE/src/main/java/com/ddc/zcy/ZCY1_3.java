package com.ddc.zcy;

import java.util.Stack;

public class ZCY1_3<T> {
    Stack<T> stack = new Stack<T>();

    public T getAndRemoveLastElement(Stack<T> stack) {
        T result = stack.pop();
        if(stack.empty()) {
            return result;
        } else {
            T last = getAndRemoveLastElement(stack);
            stack.push(last);
            return last;
        }
    }
}