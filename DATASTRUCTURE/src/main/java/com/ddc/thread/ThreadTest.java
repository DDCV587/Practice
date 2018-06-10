package com.ddc.thread;

public class ThreadTest extends Thread {
    private volatile int i = 0;

    public void test() {
//        Thread.yield();
        System.out.println("test");
        i++;
    }

    @Override
    public void run() {

        synchronized (this) {
            while(i == 0) {
                System.out.println("play");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int h = 0;
        ThreadTest t1 = new ThreadTest();
        t1.start();
        Thread.sleep(5000);
        t1.test();

    }
}
