package com.ddc.zcy;

import org.junit.Test;

public class TestZCY1_1 {

    @Test
    public void test() {
        ZCY1_1 class1 = new ZCY1_1();

        for(int i = 10; i >= 0; --i) {
            class1.push(i);
        }

        for(int j = 0; j < 10; ++j) {
            class1.push(0);
        }

        for(int k = 0; k < 20; ++k) {
            System.out.println(class1.getMin());
            class1.pop();
        }
    }
}
