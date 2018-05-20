package com.ddc.zcy;

import java.util.LinkedList;

public class ZCY1_7 {

    public int[] getMaxWindow(int[] arr, int len) {
        if(arr == null || len < 1 || arr.length < len) {
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - len + 1];
        int index = 0;

        for(int i = 0; i < arr.length; ++i) {
            while(!qmax.isEmpty() && qmax.peekLast() <= arr[i]) {
                qmax.peekLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i - len) {
                qmax.pollFirst();
            }
            if(i >= len - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res;
    }
}
