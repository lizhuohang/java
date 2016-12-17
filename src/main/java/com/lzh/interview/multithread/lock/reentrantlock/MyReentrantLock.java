package com.lzh.interview.multithread.lock.reentrantlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lizhuohang on 2016/12/17.
 * <p>
 * An implementation of reentrant spin lock
 */
public class MyReentrantLock {
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();
    private int count = 0;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count++;
            return;
        }
        while (!owner.compareAndSet(null, current)) {

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (--this.count == 0) {
                owner.compareAndSet(current , null);
            }
        }
    }
}
