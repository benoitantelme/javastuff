package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd2 {
    private int n;
    private int cnt;
    private final Semaphore semZero;
    private final Semaphore semEven;
    private final Semaphore semOdd;
    private boolean isOdd;

    public ZeroEvenOdd2(int n) {
        this.n = n;
        cnt = 1;
        semZero = new Semaphore(1);
        semEven = new Semaphore(0);
        semOdd = new Semaphore(0);
        isOdd = true;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            if (cnt > n)
                return;

            semZero.acquire();
            if (cnt > n)
                return;

            printNumber.accept(0);
            if (isOdd) {
                semOdd.release();
            } else {
                semEven.release();
            }
            isOdd = !isOdd;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            if (cnt > n)
                return;

            if (cnt % 2 == 0) {
                semEven.acquire();

                printNumber.accept(cnt);
                cnt++;
                semZero.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            if (cnt > n)
                return;

            if (cnt % 2 != 0) {
                semOdd.acquire();

                printNumber.accept(cnt);
                cnt++;
                semZero.release();
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd2 zeo = new ZeroEvenOdd2(5);

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
