package com.java.stuff.algo.finance;

import java.util.*;

public class TickerSystem {
    private int size;
    private PriorityQueue<Tick> queue;
    private Set<Tick> ticks;

    public TickerSystem(int size) {
        this.size = size;
        queue = new PriorityQueue<>();
        ticks = new HashSet<>();
    }

    void addOrUpdate(String stock, double price) {
        Tick newTick = new Tick(stock, price);

        if (ticks.contains(newTick)) {
            queue.remove(newTick);
        } else {
            if (queue.size() >= size) {
                Tick removed = queue.remove();
                ticks.remove(removed);
            }
        }

        queue.add(newTick);
        ticks.add(newTick);
    }

    Tick[] top() {
        return queue.toArray(new Tick[]{});
    }


    public static void main(String[] args) {
        TickerSystem ts = new TickerSystem(3);
        ts.addOrUpdate("SocGen", 8.0);
        ts.addOrUpdate("Alphabet", 12.0);
        ts.addOrUpdate("BNP", 6.0);
        ts.addOrUpdate("Facebook", 13.0);
        ts.addOrUpdate("Alphabet", 14.0);
        System.out.println(Arrays.toString(ts.top()));

    }

    class Tick implements Comparable<Tick> {
        String symbol;
        double price;


        public Tick(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        @Override
        public int compareTo(Tick o) {
            double comp = this.price - o.price;
            if (comp > 0)
                return 1;
            else if (comp < 0)
                return -1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "Tick{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tick tick = (Tick) o;
            return Objects.equals(symbol, tick.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }
    }

}
