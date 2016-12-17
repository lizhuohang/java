package com.lzh.interview.multithread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lizhuohang on 2016/12/17.
 */
public class CLHLock2 {

    AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock2() {
        tail = new AtomicReference<QNode>(new QNode());
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }

    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {

        }
    }

    public void unlock() {
        QNode qNode = myNode.get();
        qNode.locked = false;
        myNode.set(myPred.get());
    }

    public static class QNode {
        private boolean locked = false;
    }
}
