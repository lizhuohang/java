package com.lzh.interview.multithread;

import java.util.HashMap;

/**
 * Created by lizhuohang on 2016/12/14.
 */
public class MessageQueue {

    public static void main(String[] args) {
        final MQ mq = new MQ(10);

        for (int p = 0; p < 5; p++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        mq.put((int) (Math.random() * 10000000) + "");
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "Producer" + p).start();
        }

        for (int c = 0; c < 5; c++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        mq.get();
                        try {
                            Thread.currentThread().sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "Consumer" + c).start();
        }
    }


    /**
     * inner class to simulate a message queue
     */
    private static class MQ {

        private String[] messages;// an array of string to storage the message
        private int opIndex; // the handle pointer

        public MQ(int size) {

            if (size <= 0) {

                throw new IllegalArgumentException("The minimum length of the message queue is 1!");
            }
            messages = new String[size];
            opIndex = 0;
        }

        public synchronized void put(String message) {

            while (opIndex == messages.length) {
                System.out.println("Producer " + Thread.currentThread().getName() + " the message queue is full......");
                // the message queue is full so the producer mast wait
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messages[opIndex] = message;
            opIndex++;
            System.out.println("Producer " + Thread.currentThread().getName() + " produce a message: " + message);
            // notify all of the thread(mainly for the consumers) which wait for the mq object after put message into the message queue
            notifyAll();
        }

        public synchronized String get() {

            while (opIndex == 0) {

                // Consumer mast wait because the message queue is empty
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String message = messages[opIndex - 1];
            opIndex--;
            System.out.println("Consumer " + Thread.currentThread().getName() + " spending message: " + message);
            // notify all of the thread(mainly for the producers) which wait for the mq object after get message from the message queue
            notifyAll();
            return message;
        }
    }
}
