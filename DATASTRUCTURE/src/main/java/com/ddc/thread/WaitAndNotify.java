package com.ddc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class WaitAndNotify {
    public static Object u = new Object();

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectCallable changeCallable = new ChangeObjectCallable();
        ReadObjectCallable readCallable = new ReadObjectCallable();
        FutureTask task1 = new FutureTask(changeCallable);
        FutureTask task2 = new FutureTask(readCallable);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        Thread.sleep(1000);
        changeCallable.setSuspendMe();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        changeCallable.resumeMe();
    }

    static class ChangeObjectCallable implements Callable {
        volatile boolean suspendme = false;

        public void setSuspendMe() {
            suspendme = true;
        }

        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public Object call() throws Exception {
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        wait();
                    }
                }

                synchronized (u) {
                    System.out.println("in ChangeObjectThread");
                }

                Thread.yield();
            }

        }
    }

    static class ReadObjectCallable implements Callable {

        @Override
        public Object call() throws Exception {
            while (true) {
                synchronized (u) {
                    System.out.println("in ReadObjectThread");
                }

                Thread.yield();
            }
        }
    }
}
