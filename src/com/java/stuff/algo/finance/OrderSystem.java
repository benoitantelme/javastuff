package com.java.stuff.algo.finance;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderSystem {
    ConcurrentHashMap<String, Integer> thresholds = new ConcurrentHashMap(10, (float) 0.75, 8);
    List<ExecutedOrder> executed = new CopyOnWriteArrayList<>();

    public void callOrder(String symbol, int price){
        if(price == thresholds.compute(symbol, (k, v) -> (v > price)? price : v)){
            Long t = System.nanoTime();
            System.out.println("Order for " + symbol + " for " + price + " at " + t);
            executed.add(new ExecutedOrder(symbol, price, t));
        }
    }


    public static void main(String[] args) throws InterruptedException {
        List<String> symbols = List.of("IBM", "BNP", "GOOGLE");
        OrderSystem os = new OrderSystem();
        os.thresholds.put("IBM", 5000);
        os.thresholds.put("BNP", 10000);
        os.thresholds.put("GOOGLE", 15000);
        ExecutorService pool = Executors.newFixedThreadPool(6);

        Random rdm = new Random();
        for(int i =0; i < 10000; i++){
            pool.execute(new OrderTask(symbols.get(rdm.nextInt(2)), rdm.nextInt(20000), os));
        }
        Thread.sleep(3000);
        pool.shutdown();

        Collections.sort(os.executed);
        System.out.println("Orders executed: ");
        System.out.println(os.executed);

    }

    public static class OrderTask implements Runnable {
        String symbol;
        int price;
        OrderSystem os;

        public OrderTask(String symbol, int price, OrderSystem os) {
            this.symbol = symbol;
            this.price = price;
            this.os = os;
        }

        public void run() {
            os.callOrder(symbol, price);
        }
    }

    public static class ExecutedOrder implements Comparable<ExecutedOrder>{
        String symbol;
        int price;
        Long time;

        public ExecutedOrder(String symbol, int price, Long time) {
            this.symbol = symbol;
            this.price = price;
            this.time = time;
        }

        @Override
        public int compareTo(ExecutedOrder o) {
            if(time.compareTo(o.time) > 0)
                return 1;
            else if(time.compareTo(o.time) < 0)
                return -1;

            return 0;
        }

        @Override
        public String toString() {
            return "ExecutedOrder{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    ", time=" + time +
                    '}';
        }
    }

}
