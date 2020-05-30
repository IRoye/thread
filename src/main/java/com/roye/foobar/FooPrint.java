package com.roye.foobar;

public class FooPrint implements  Runnable {

    FooBar fb ;

    public FooPrint(FooBar fb) {
        this.fb = fb;
    }

    @Override
    public void run() {
        try {
            fb.foo(() -> System.out.print("foo"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
