package com.java.stuff.leetcode.medium.multithreading;

import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;
    private volatile int cnt;
    private volatile boolean zeroed;

    public ZeroEvenOdd(int n) {
        this.n = n;
        cnt = 1;
        zeroed = false;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(true){
            synchronized (this) {
                if (cnt > n)
                    return;

                if (!zeroed) {
                    printNumber.accept(0);
                    zeroed = true;
                }
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(true){
            synchronized (this) {
                if (cnt > n)
                    return;

                if (zeroed) {
                    if (cnt % 2 == 0) {
                        printNumber.accept(cnt);
                        cnt ++;
                        zeroed = false;
                    }
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(true){
            synchronized (this) {
                if (cnt > n)
                    return;

                if (zeroed) {
                    if (cnt % 2 != 0) {
                        printNumber.accept(cnt);
                        cnt ++;
                        zeroed = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeo = new ZeroEvenOdd(2);

        Thread a = new Thread(() -> {
            try {
                zeo.zero(value -> System.out.println(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                zeo.even(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                zeo.odd(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
        c.start();
    }
}
