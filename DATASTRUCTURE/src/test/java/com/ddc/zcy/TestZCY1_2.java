package com.ddc.zcy;

import org.junit.Test;

public class TestZCY1_2 {

    @Test
    public void test() {
        ZCY1_2 class1 = new ZCY1_2();
        for(int i = 0; i< 10; ++i) {
            class1.add(i);
        }

        System.out.println(class1.poll());
        System.out.println(class1.peek());
    }
}
