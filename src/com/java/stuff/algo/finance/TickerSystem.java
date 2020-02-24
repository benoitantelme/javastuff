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

    void addOrUpdate(String stock, double price){
        Tick newTick = new Tick(stock, price);

        if(ticks.contains(newTick)){
            queue.remove(newTick);
        }else{
            if(queue.size() >= size)
                queue.remove();
        }

        queue.add(newTick);
    }

    Tick[] top() {
        return queue.toArray(new Tick[]{});
    }


    public static void main(String[] args){
        TickerSystem ts = new TickerSystem(3);
        ts.addOrUpdate("SocGen", 8.0);
        ts.addOrUpdate("Alphabet", 12.0);
        ts.addOrUpdate("BNP", 6.0);
        ts.addOrUpdate("Facebook", 13.0);
        ts.addOrUpdate("Alphabet", 14.0);
        System.out.println(Arrays.toString(ts.top()));

    }

    class Tick implements Comparable<Tick>{
        String symbol;
        double value;


        public Tick(String symbol, double value) {
            this.symbol = symbol;
            this.value = value;
        }

        @Override
        public int compareTo(Tick o) {
            double comp = this.value - o.value;
            if(comp > 0)
                return 1;
            else if(comp < 0)
                return -1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "Tick{" +
                    "symbol='" + symbol + '\'' +
                    ", value=" + value +
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
