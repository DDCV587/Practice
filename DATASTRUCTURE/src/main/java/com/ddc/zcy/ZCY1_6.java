package com.ddc.zcy;

import org.omg.CORBA.INTERNAL;

import java.util.Stack;

enum Action {
    No,
    LTOM,
    MTOL,
    RTOM,
    MTOR
}

public class ZCY1_6 {

    public int hanno(int num) {
        Stack<Integer> lStack = new Stack<Integer>();
        Stack<Integer> mStack = new Stack<Integer>();
        Stack<Integer> rStack = new Stack<Integer>();

        lStack.push(Integer.MAX_VALUE);
        mStack.push(Integer.MAX_VALUE);
        rStack.push(Integer.MAX_VALUE);

        for(int i = num; i > 0; --i) {
            lStack.push(i);
        }

        Action[] record = {Action.No};
        int step = 0;

        while(rStack.size() != num + 1) {
            step += fStackTotStack(record, Action.LTOM, Action.MTOL, mStack, lStack, "m", "l");
            step += fStackTotStack(record, Action.MTOL, Action.LTOM, lStack, mStack, "l", "m");
            step += fStackTotStack(record, Action.MTOR, Action.RTOM, rStack, mStack, "r", "m");
            step += fStackTotStack(record, Action.RTOM, Action.MTOR, mStack, rStack, "m", "r");
        }

        return step;
    }

    public static int fStackTotStack(Action[] action, Action preNoAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {

        if(action[0] != preNoAct && fStack.peek() < tStack.peek()) {
            System.out.println("MOVE " + fStack.peek() + " from " + from + " to " + to);
            tStack.push(fStack.pop());

            action[0] = nowAct;

            return 1;
        }

        return 0;
    }
}
