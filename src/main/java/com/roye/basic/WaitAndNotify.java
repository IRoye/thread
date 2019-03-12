package com.roye.basic;

/***
 * java 高并发程序设计 例子
 */
public class WaitAndNotify {

    final static Object object = new Object();

    // 静态nested class
    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("t1 wait for the object");
                    // 线程停止继续执行，转为等待状态， 直到其他线程调用了object.notify
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1 end");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("t2 start");
                // 唤醒其他线程之前，也是需要获取锁的， notify之后，释放对象的锁。
                object.notify();
                System.out.println("t2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();

        t1.start();
        t2.start();
    }
}
