package com.roye.foobar;

public class BarPrint implements Runnable {

    FooBar fb;

    public BarPrint(FooBar fb) {
       this.fb = fb;
    }

    @Override
    public void run() {
        try {
            fb.bar(() -> System.out.print("bar"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
