package com.lzh.interview.multithread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by lizhuohang on 2016/12/17.
 */
public class CLHLock {

    public static class CLHNode {
        private boolean isLocked = true;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;

    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(CLHNode currentThreadCLHNode) {
        CLHNode preNode = UPDATER.getAndSet(this, currentThreadCLHNode);
        if (preNode != null) {
            while (preNode.isLocked) {

            }
        }
    }

    public void unlock(CLHNode currentThreadCLHNode) {
        if (!UPDATER.compareAndSet(this, currentThreadCLHNode, null)) {
            currentThreadCLHNode.isLocked = false;
        }
    }
}
