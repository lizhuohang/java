package com.lzh.interview.multithread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lizhuohang on 2016/12/17.
 *
 * The ReentrantLock and synchronized are re-entrant lock in java
 *
 * you can rerun more times to observe the result
 *
 */
public class JDKReenTrantLock implements Runnable {
    public synchronized void get(){
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getId());
    }

    ReentrantLock lock = new ReentrantLock();
    public void get_reentrant(){
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set_reentrant();
        lock.unlock();
    }

    public void set_reentrant(){
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }


    public void run() {
//        System.out.println("-------------------  result of synchronized  -------------------");
//        get();

        System.out.println("-------------------  result of reentrantlock  -------------------");
        get_reentrant();
    }

    public static void main(String[] args) {
        JDKReenTrantLock jdkReenTrantLock = new JDKReenTrantLock();
        new Thread(jdkReenTrantLock).start();
        new Thread(jdkReenTrantLock).start();
        new Thread(jdkReenTrantLock).start();
    }
}
