package com.ddc.zcy;

import org.junit.Test;

public class TestZCY1_4 {

    @Test
    public void test() throws InterruptedException {
        Pet cat = new Cat("xiao mao");
        Pet dog = new Dog("xiao gou");

        ZCY1_4<Pet> queue = new ZCY1_4<Pet>();

        queue.add(cat);
        Thread.sleep(100);
        queue.add(dog);

        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
     }
}
