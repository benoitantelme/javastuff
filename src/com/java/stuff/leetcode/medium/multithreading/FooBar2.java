package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.Semaphore;

public class FooBar2 {
    private int n;
    private Semaphore semFoo;
    private Semaphore semBar;

    public FooBar2(int n) {
        this.n = n;
        this.semFoo = new Semaphore(1);
        this.semBar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            this.semFoo.acquire();

            printFoo.run();
            this.semBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            this.semBar.acquire();
            printBar.run();
            this.semFoo.release();
        }
    }

    public static void main(String[] args) {
        FooBar2 foobar = new FooBar2(2);

        Thread a = new Thread(() -> {
            try {
                foobar.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                foobar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
    }

}
