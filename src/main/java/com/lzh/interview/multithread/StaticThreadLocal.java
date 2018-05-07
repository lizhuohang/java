package com.lzh.interview.multithread;

/**
 * Created by lizhuohang on 2016/12/20.
 * <p>
 * ThreadLocal with static : variables dependent on thread
 * <p>
 * static variables is quoted by every instances in one thread,separated within different threads
 */
public class StaticThreadLocal {

    /**
     * 1
     *
     * @param args
     */
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };

//    private int seqNum = 0;

    public int getNexNum() {
        seqNum.set((seqNum.get()) + 1);
        return seqNum.get();
//        return ++seqNum;
    }


    /**
     * 2
     *
     * @param args
     */
//    private static int a = 0;
//    public synchronized int getNexNum(){
//        return ++a;
//    }
    public static void main(String[] args) {
        StaticThreadLocal st = new StaticThreadLocal();
        TestClient t1 = new TestClient(st);
        TestClient t2 = new TestClient(st);
        TestClient t3 = new TestClient(st);

        t1.start();
        t2.start();
        t3.start();

        t1.print();
        t2.print();
        t3.print();
    }

    private static class TestClient extends Thread {
        private StaticThreadLocal st;

        public TestClient(StaticThreadLocal st) {
            this.st = st;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + st.getNexNum());
            }
        }

        public void print() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + st.getNexNum());
            }
        }
    }
}
