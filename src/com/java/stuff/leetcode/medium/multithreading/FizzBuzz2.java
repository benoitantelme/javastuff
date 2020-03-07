package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;


public class FizzBuzz2 {
    private int n;
    private AtomicInteger cnt = new AtomicInteger(1);

    public FizzBuzz2(int n) {
        this.n = n;
    }


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            int tmp = cnt.get();
            if (tmp > n)
                return;
            if (tmp % 3 == 0 && tmp % 5 != 0) {
                printFizz.run();
                cnt.incrementAndGet();
            }
        }
    }


    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            int tmp = cnt.get();
            if (tmp > n)
                return;
            if (tmp % 3 != 0 && tmp % 5 == 0) {
                printBuzz.run();
                cnt.incrementAndGet();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            int tmp = cnt.get();
            if (tmp > n)
                return;
            if (tmp % 3 == 0 && tmp % 5 == 0) {
                printFizzBuzz.run();
                cnt.incrementAndGet();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            int tmp = cnt.get();
            if (tmp > n)
                return;
            if (tmp % 3 != 0 && tmp % 5 != 0) {
                printNumber.accept(tmp);
                cnt.incrementAndGet();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        FizzBuzz2 fb = new FizzBuzz2(15);

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
