package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    private AtomicInteger atom;

    public Foo() {
        atom = new AtomicInteger(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        while(true){
            int cnt = atom.get();
            if(cnt > 1)
                return;
            if(cnt == 1){
                printFirst.run();
                atom.incrementAndGet();
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(true){
            int cnt = atom.get();
            if(cnt > 2)
                return;
            if(cnt == 2){
                printSecond.run();
                atom.incrementAndGet();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(true){
            int cnt = atom.get();
            if(cnt > 3)
                return;
            if(cnt == 3){
                printThird.run();
                atom.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Foo fb = new Foo();

        Thread a = new Thread(() -> {
            try {
                fb.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                fb.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                fb.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
        c.start();
    }

}
