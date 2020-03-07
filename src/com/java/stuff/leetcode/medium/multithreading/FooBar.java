package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FooBar {
    private int n;
    private AtomicInteger cnt;
    private AtomicBoolean workStarted;

    public FooBar(int n) {
        this.n = n;
        cnt = new AtomicInteger(0);
        workStarted = new AtomicBoolean(false);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        while(true){
            if(cnt.get() == n)
                return;

            if(workStarted.get() == false){
                printFoo.run();
                workStarted.compareAndSet(false, true);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        while(true){
            if(cnt.get() == n)
                return;

            if(workStarted.get() == true){
                printBar.run();
                cnt.incrementAndGet();
                workStarted.compareAndSet(true, false);
            }
        }
    }

    public static void main(String[] args) {
        FooBar foobar = new FooBar(2);

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
