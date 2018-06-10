package com.ddc.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterTryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public ReenterTryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " my job done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " my job done");
                                return ;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ReenterTryLock tryLock1 = new ReenterTryLock(1);
        ReenterTryLock tryLock2 = new ReenterTryLock(2);

        Thread thread1 = new Thread(tryLock1);
        Thread thread2 = new Thread(tryLock2);

        thread1.start();
        thread2.start();
    }
}
