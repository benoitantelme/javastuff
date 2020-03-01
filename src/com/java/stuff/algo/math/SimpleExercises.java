package com.java.stuff.algo.math;

import java.util.*;

public class SimpleExercises {

    public double pow(double x, int n){
        if(x == 0)
            return 0;
        if(n == 0)
            return 1;
        if(n == 1)
            return x;

        if(n < 0){
            n = -n;
            x = 1/x;
        }

        if(n % 2 == 0)
            return pow(x*x, n/2);
        else
            return pow(x*x, n/2) * x;


    }

    public List<Integer> randomOfInput(List<Integer> in){
        Random rdm = new Random();

        List<Integer> result = new ArrayList<>();
        result.add(in.get(0));
        for(int i = 1; i < in.size(); i++)
            result.add(in.get(rdm.nextInt(i)));

        return result;
    }

    public void medianValue(){
        Random rdm = new Random();

        List<Integer> inputs = new ArrayList<>();
        List<Integer> medians = new ArrayList<>();
        int total = 0;
        for(int i = 0; i < 500; i++) {
            int next = rdm.nextInt(1000000000);
            inputs.add(next);
            total += next;
            medians.add(total/inputs.size());
        }

        System.out.println("Inputs:");
        System.out.println(inputs);
        System.out.println("Medians:");
        System.out.println(medians);
    }

    private double getMedian(PriorityQueue<Integer> minh, PriorityQueue<Integer> maxh){
        if(minh.size() > maxh.size())
            return minh.peek();
        else if(minh.size() < maxh.size())
            return maxh.peek();
        else  if(minh.isEmpty() || maxh.isEmpty())
            return 0;
        return (minh.peek()+maxh.peek())/2;
    }
    public void medianElement(){
        Random rdm = new Random();

        List<Integer> inputs = new ArrayList<>();
        PriorityQueue<Integer> minh = new PriorityQueue<>();
        PriorityQueue<Integer> maxh = new PriorityQueue<>(Comparator.reverseOrder());
        List<Double> medians = new ArrayList<>();

        for(int i = 0; i < 500; i++) {
            int next = rdm.nextInt(1000000000);
            inputs.add(next);

            double median = getMedian(minh, maxh);
            if(next > median){
                minh.add(next);
            }else {
                minh.add(next);
            }

            int minsize = minh.size();
            int maxsize = maxh.size();
            if(Math.abs(minsize - maxsize) > 1){
                if(minsize > maxsize){
                    maxh.add(minh.poll());
                }else{
                    minh.add(maxh.poll());
                }
            }

            median = getMedian(minh, maxh);
            medians.add(median);
        }

        System.out.println("Inputs:");
        System.out.println(inputs);
        System.out.println("Medians:");
        System.out.println(medians);
    }

    /**
     * @param points
     * @return the y value of the horizontal line being the median for the points (less distance from each point)
     */
    public double calculateHorizontalMedian(List<int[]> points){
        int ys = 0;
        for(int[] point : points)
            ys += point[1];

        return (double) ys /  points.size();
    }

    /**
     * @param points
     * a = y2-y1/x2-x1 slope
     * b = y1 -ax1 because y1 = ax1 + b
     *
     * @return the x,y value of the median equation ax+b=y
     */
    public double[] calculate2PointsMedian(List<int[]> points){
        int[] one = points.get(0);
        int[] two = points.get(1);

        double a = (double) (two[1] - one[1])/(two[0] - one[0]);
        double b = one[1] - a * one[0];

        return new double[]{a, b};
    }

    public double stringToDouble(String str){
        // sign
        boolean minus = false;
        if(str.toCharArray()[0] == '+' || str.toCharArray()[0] == '-'){
            if(str.toCharArray()[0] == '-')
                minus = true;

            str = str.substring(1);
        }

        String[] splitted = str.split("\\.");

        // integer part
        double result = 0;
        for(char c : splitted[0].toCharArray())
            result = result * 10 + Character.getNumericValue(c);

        // decimal part
        double power = 1;
        if(splitted.length > 1) {
            double decimals = 0;
            for (char c : splitted[1].toCharArray()) {
                decimals = decimals * 10 + Character.getNumericValue(c);
                power *= 10;
            }
            result += decimals / power;
        }

        if(minus)
            result *= -1;

        return result;
    }

    public static void main(String[] args){
        SimpleExercises sp = new SimpleExercises();
        System.out.println(sp.pow(2, 4));
        System.out.println(sp.pow(2, -4));

        System.out.println(sp.randomOfInput(List.of(5, 8, 159, 753, 26, 48, 59, 14785, 6, 4, 2, 8, 9, 7, 1, 3, 598,
                8468, 24984, 1351, 897, 846, 6256, 84825, 636236, 84484, 2222,9595)));

        System.out.println("Median Value");
        sp.medianValue();

        System.out.println("Median Element");
        sp.medianElement();

        System.out.println(sp.calculateHorizontalMedian(
                List.of(new int[]{1, 1}, new int[]{-1, -1})));
        System.out.println(sp.calculateHorizontalMedian(
                List.of(new int[]{2, 2}, new int[]{-4, -4})));
        System.out.println(sp.calculateHorizontalMedian(
                List.of(new int[]{1, 1}, new int[]{2, 3}, new int[]{-1, -2})));

        System.out.println(Arrays.toString(sp.calculate2PointsMedian(List.of(new int[]{1, 1}, new int[]{-1, -1})))); // y = x
        System.out.println(Arrays.toString(sp.calculate2PointsMedian(List.of(new int[]{0, 0}, new int[]{2, 1})))); // y = x/2

        System.out.println(sp.stringToDouble("5"));
        System.out.println(sp.stringToDouble("-5"));
        System.out.println(sp.stringToDouble("5.23"));
        System.out.println(sp.stringToDouble("-5.22"));
    }


}
