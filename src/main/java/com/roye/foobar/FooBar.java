package com.roye.foobar;

/**
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 第一种方式，使用synchronized   + wait() + notify()
 */
public class FooBar {
    private int n;
    private Object obj = new Object();
    private int count = 0;

    public FooBar(int n) {
        this.n = n;
        System.out.println("总数");
        System.out.println(n);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                if (count % 2 == 1) {
                    obj.wait();
                }
                printFoo.run();
                count++;
                obj.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
//            System.out.printf("i: %d", i);
            synchronized (obj) {
                if (count % 2 == 0) {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    obj.wait();
                }
                // 下次从这里继续开始
                printBar.run();
                count++;
                obj.notify();

            }
        }
    }

    public static void main(String[] args) {
        FooBar fb = new FooBar(5);
        Thread t1 = new Thread(new FooPrint(fb));
        // 一个线程 参数是实现了Runnable的对象
        Thread t2 = new Thread(new BarPrint(fb));
        t1.start();  // 注意不能是run()
        t2.start();  // 注意不能是run()
    }
}

