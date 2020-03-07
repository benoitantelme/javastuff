package com.java.stuff.leetcode.medium;

import java.util.function.IntConsumer;


public class FizzBuzz {
    private int n;
    private volatile int cnt = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true)
            synchronized (this) {
                if (cnt > n)
                    return;
                if (cnt % 3 == 0 && cnt % 5 != 0) {
                    printFizz.run();
                    cnt++;
                }
            }
    }


    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true)
            synchronized (this) {
                if (cnt > n)
                    return;
                if (cnt % 3 != 0 && cnt % 5 == 0) {
                    printBuzz.run();
                    cnt++;
                }
            }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true)
            synchronized (this) {
                if (cnt > n)
                    return;
                if (cnt % 3 == 0 && cnt % 5 == 0) {
                    printFizzBuzz.run();
                    cnt++;
                }
            }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true)
            synchronized (this) {
                if (cnt > n)
                    return;
                if (cnt % 3 != 0 && cnt % 5 != 0) {
                    printNumber.accept(cnt);
                    cnt++;
                }
            }
    }


    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fb = new FizzBuzz(15);

        Thread a = new Thread(() -> {
            try {
                fb.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                fb.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread d = new Thread(() -> {
            try {
                fb.number(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();

    }

}
