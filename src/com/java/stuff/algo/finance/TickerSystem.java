package com.java.stuff.algo.finance;

import java.util.*;

public class TickerSystem {
    private int size;

    private PriorityQueue<Tick> queue;
    private Set<Tick> ticks;

    private Map<String, List<Vwap>> vwaps;
    private PriorityQueue<Vwap> deviations;
    private Set<Vwap> vwapsSets;

    public TickerSystem(int size) {
        this.size = size;

        queue = new PriorityQueue<>();
        ticks = new HashSet<>();

        deviations = new PriorityQueue<>();
        vwaps = new HashMap<>();
        vwapsSets = new HashSet<>();
    }

    void addOrUpdate(String stock, double price, double tradeValue, int volume) {
        Tick newTick = new Tick(stock, price);

        addToQueue(queue, ticks, newTick);

        Vwap vwap = calculateNewVwap(stock, price, tradeValue, volume);
        vwaps.computeIfAbsent(stock, k -> new ArrayList()).add(vwap);
        addToQueue(deviations, vwapsSets, vwap);
    }

    Vwap calculateNewVwap(String stock, double price, double tradeValue, int volume) {
        Vwap oldVwap;
        if (vwaps.containsKey(stock))
            oldVwap = vwaps.get(stock).get(vwaps.get(stock).size() - 1);
        else
            oldVwap = new Vwap(stock, 0.0, 0.1, price);

        return new Vwap(stock, oldVwap.cumulativePv + tradeValue * volume, oldVwap.cumulativeVolume + volume, price);
    }

    Tick[] topTicks() {
        return queue.toArray(new Tick[]{});
    }

    Vwap[] topDeviations() {
        return deviations.toArray(new Vwap[]{});
    }

    <T> void addToQueue(PriorityQueue<T> queue, Set<T> set, T newelement) {
        if (set.contains(newelement)) {
            queue.remove(newelement);
        } else {
            if (queue.size() >= size) {
                T removed = queue.remove();
                set.remove(removed);
            }
        }

        queue.add(newelement);
        set.add(newelement);
    }

    public static void main(String[] args) {
        TickerSystem ts = new TickerSystem(3);
        ts.addOrUpdate("SocGen", 8.0, 7.0, 40);
        ts.addOrUpdate("Alphabet", 12.0, 13.0, 50);
        ts.addOrUpdate("BNP", 6.0, 5.0, 30);
        ts.addOrUpdate("Facebook", 13.0, 11.0, 90);
        ts.addOrUpdate("Alphabet", 14.0, 13.5, 70);
        System.out.println(Arrays.toString(ts.topTicks()));
        System.out.println(Arrays.toString(ts.topDeviations()));

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

        @Override
        public String toString() {
            return "Tick{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    class Vwap implements Comparable<Vwap> {
        String symbol;
        double cumulativePv;
        double cumulativeVolume;
        double value;
        double deviation;

        public Vwap(String symbol, double cumulativePv, double cumulativeVolume, double price) {
            this.symbol = symbol;
            this.cumulativePv = cumulativePv;
            this.cumulativeVolume = cumulativeVolume;
            this.value = cumulativePv / cumulativeVolume;
            this.deviation = value / price;
        }

        @Override
        public int compareTo(Vwap o) {
            double comp = this.deviation - o.deviation;
            if (comp > 0)
                return 1;
            else if (comp < 0)
                return -1;
            else
                return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vwap vwap = (Vwap) o;
            return Objects.equals(symbol, vwap.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }

        @Override
        public String toString() {
            return "Vwap{" +
                    "symbol='" + symbol + '\'' +
                    ", value=" + value +
                    ", deviation=" + deviation +
                    '}';
        }
    }

}
