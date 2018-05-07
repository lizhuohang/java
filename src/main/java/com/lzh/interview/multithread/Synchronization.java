package com.lzh.interview.multithread;

/**
 * Created by lizhuohang on 2016/12/14.
 */
public class Synchronization {
    private static String[] flag = {"t"};

    public static void main(String[] args) {
        NotifyThread notifyThread = new NotifyThread("n1");
        WaitThread waitThread1 = new WaitThread("w1");
        WaitThread waitThread2 = new WaitThread("w2");
        WaitThread waitThread3 = new WaitThread("w3");
        notifyThread.start();
        waitThread1.start();
        waitThread2.start();
        waitThread3.start();
    }

    static class NotifyThread extends Thread {
        public NotifyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                sleep(3000);//delay 3000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flag) {
                flag[0] = "f";
                flag.notifyAll();
            }
        }
    }

    static class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        public void run() {
            while (flag[0] != "f"){
                // while只是为了保证wait线程在notify线程notify后不再进入wait状态
                System.out.println(getName() + " begin waiting!!!");
                long waitTime = System.currentTimeMillis();
                try {
                    synchronized (flag) {
                        flag.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTime = System.currentTimeMillis() - waitTime;
                System.out.println("wait time :" + waitTime);
            }
            System.out.println(getName() + " end waiting!!!");
        }
    }

}
