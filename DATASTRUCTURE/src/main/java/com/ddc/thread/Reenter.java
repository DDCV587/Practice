package com.ddc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

class ReenterLockCallable implements Callable<Void> {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public ReenterLockCallable(int lock) {
        this.lock = lock;
    }

    @Override
    public Void call() throws Exception {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+ " 线程退出");
        }


        return null;
    }
}

public class Reenter {
    public static void main(String[] args) {
        ReenterLockCallable lock1 = new ReenterLockCallable(1);
        ReenterLockCallable lock2 = new ReenterLockCallable(2);

        FutureTask task1 = new FutureTask(lock1);
        FutureTask task2 = new FutureTask(lock2);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();
    }
}
