package com.lzh.interview.multithread.lock.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lizhuohang on 2016/12/16.
 *
 *  an instance of spin lock
 *
 * advantage : Realized the function of FIFO
 * disadvantage : every threads are reading and writing the same variable(serviceNum),
 *                witch cause a lot of synchronization in cache and increase memory flow
 *
 */
public class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();

    public int lock(){
        int myTicketNum = ticketNum.getAndIncrement();

        if (serviceNum.get() != myTicketNum){

        }

        return myTicketNum;
    }

    public void unlock(int myTicketNum){
        int next = myTicketNum + 1;
        serviceNum.compareAndSet(myTicketNum , next);
    }
}
