package com.java.stuff.algo.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void median(){
        Random rdm = new Random();

        List<Integer> inputs = new ArrayList<>();
        List<Integer> medians = new ArrayList<>();
        int total = 0;
        for(int i = 0; i < 500; i++) {
            int next = rdm.nextInt(100);
            inputs.add(next);
            total += next;
            medians.add(total/inputs.size());
        }

        System.out.println("Inputs:");
        System.out.println(inputs);
        System.out.println("Medians:");
        System.out.println(medians);
    }

    public static void main(String[] args){
        SimpleExercises sp = new SimpleExercises();
        System.out.println(sp.pow(2, 4));
        System.out.println(sp.pow(2, -4));

        System.out.println(sp.randomOfInput(List.of(5, 8, 159, 753, 26, 48, 59, 14785, 6, 4, 2, 8, 9, 7, 1, 3, 598,
                8468, 24984, 1351, 897, 846, 6256, 84825, 636236, 84484, 2222,9595)));


        sp.median();
    }


}
