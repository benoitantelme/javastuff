package com.java.stuff.leetcode.medium.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O {
    CyclicBarrier barrier;
    Semaphore hsem;
    Semaphore osem;

    public H2O() {
        barrier = new CyclicBarrier(3);
        hsem = new Semaphore(2);
        osem = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hsem.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        releaseHydrogen.run();
        hsem.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        osem.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        releaseOxygen.run();
        osem.release();
    }

    public static void main(String[] args) {
        H2O h2o = new H2O();

        Thread a = new Thread(() -> {
            try {
                h2o.hydrogen(() -> System.out.println("O"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                h2o.oxygen(() -> System.out.println("O"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                h2o.hydrogen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread d = new Thread(() -> {
            try {
                h2o.oxygen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread f = new Thread(() -> {
            try {
                h2o.oxygen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread g = new Thread(() -> {
            try {
                h2o.hydrogen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();

        c.start();
        d.start();
        f.start();
        g.start();
        b.start();
    }

}
