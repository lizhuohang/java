package com.lzh.interview.multithread;

/**
 * Created by lizhuohang on 2016/12/14.
 *
 * load and use the latest value when you use a volatile variable in a thread
 * even the variable has been loaded into the stack of the thread
 */
public class Volatile {
    // you can run main method with the volatile and without volatile
    private static volatile boolean bChanged;
//    private static boolean bChanged;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            @Override
            public void run() {
                for (;;) {
                    if (bChanged == !bChanged) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread() {

            @Override
            public void run() {
                for (;;) {
                    bChanged = !bChanged;
                }
            }
        }.start();
    }
}
