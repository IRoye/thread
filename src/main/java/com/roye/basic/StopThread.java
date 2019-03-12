package com.roye.basic;

public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Thread.yield();
                }
            }
        };
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
