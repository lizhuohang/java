package com.lzh.interview.multithread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by lizhuohang on 2016/12/17.
 *
 * different from CLHLock :
 *      1.The code is more complex than CLHLock
 *      2.CLH is circle on the preview node , MCS is circle on it self
 *      3.CLH has a hidden linked list , MCS has real linked list on the memory
 *      4.Change attribute for it self in CLH when unlock , Change attribute for the next node in MCS when unlock
 */
public class MCSLock {
    public static class MCSNode{
        MCSNode next;
        boolean isLocked = true;
    }

    volatile MCSNode queue; //point to the latest mcsnode
    private static final AtomicReferenceFieldUpdater<MCSLock , MCSNode> UPDATE =
            AtomicReferenceFieldUpdater.newUpdater(MCSLock.class , MCSNode.class , "queue");

    public void lock(MCSNode currentThreadMcsNode){
        MCSNode predecessor = UPDATE.getAndSet(this , currentThreadMcsNode);
        if (predecessor != null){
            predecessor.next = currentThreadMcsNode;
            while (currentThreadMcsNode.isLocked){

            }
        }
    }

    public void unlock(MCSNode currentThreadMcsNode){
        if (UPDATE.get(this) == currentThreadMcsNode){
            if (currentThreadMcsNode.next == null){
                if (UPDATE.compareAndSet(this,currentThreadMcsNode , null)){
                    //if compareAndSet return true , there is no one behind us
                    return ;
                }else {
                    //there is someone joined us after we judgement the next node is null
                    while(currentThreadMcsNode.next == null){

                    }
                }
                currentThreadMcsNode.next.isLocked = false;
                currentThreadMcsNode.next = null;//for gc
            }
        }
    }

}
