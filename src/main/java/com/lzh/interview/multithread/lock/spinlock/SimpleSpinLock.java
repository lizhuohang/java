package com.lzh.interview.multithread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lizhuohang on 2016/12/16.
 *
 * spin lock : Cycle view whether lock has been released , gain lock once it is released
 * upgrade version : give up after appointed times retry
 *
 * advantage : can reduce expensive of the thread_switch if the lock is held for a short time
 * disadvantage : may increase the burden of CPU if the held of the lock is a little bit longer;
 *                deadlock may occur when you try to use lock in a recursion function
 */
public class SimpleSpinLock {

    public static void main(String[] args) {
        final SL spinLock = new SL();
        for (int i = 0; i < 100; i++) {
            AddThread addThread = new AddThread(spinLock);
            Thread t = new Thread(addThread);
            t.start();
        }
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(AddThread.sum);
    }

    private static class SL {
        private AtomicReference<Thread> owner = new AtomicReference<Thread>();

        public void lock() {
            Thread current = Thread.currentThread();
            System.out.println("Thread : "+ current.getName() + ",require lock");
            while (!owner.compareAndSet(null, current)) {

            }
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            if (!owner.compareAndSet(current, null)) {
                throw new RuntimeException("Please unlock after you locked!");
            }
        }
    }

    private static class AddThread implements Runnable{

        static int sum;
        private SL lock;

        public AddThread(SL lock) {
            this.lock = lock;
        }
        public void run() {
            this.lock.lock();
            sum++;
            this.lock.unlock();
        }
    }
}
