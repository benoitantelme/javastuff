package com.java.stuff.numbers;

import java.math.BigDecimal;

public class Comparison {

	public static void main(String[] args) {
		long times = 99999999l;
		double primitive = 0d;
		Double obj = new Double(0);
		BigDecimal bd = new BigDecimal("0.2");
		BigDecimal counter = new BigDecimal("0");
		long lgprimitive = 0l;

		long startTime = System.currentTimeMillis();

		for (long i = 0; i < times; i++)
			primitive += 0.2;

		System.out.println("For primitive value is " + primitive);
		startTime = printAndResetTime(startTime);

		for (long i = 0; i < times; i++)
			obj += 0.2d;

		System.out.println("For object value is " + obj);
		startTime = printAndResetTime(startTime);
		
		for (long i = 0; i < times; i++)
			counter = counter.add(bd);
		
		System.out.println("For big decimal value is " + counter.toString());
		startTime = printAndResetTime(startTime);
		
		for (long i = 0; i < times; i++)
			lgprimitive += 2l;
		
		System.out.println("For long primitive value without decimals is " + lgprimitive);
		startTime = printAndResetTime(startTime);
		
		primitive = 0d;
		for (long i = 0; i < times; i++)
			primitive += 2d;
		
		System.out.println("For double primitive value without decimals is " + primitive);
		startTime = printAndResetTime(startTime);
		
		counter = new BigDecimal("0");
		bd = new BigDecimal("2");
		for (long i = 0; i < times; i++)
			counter = counter.add(bd);
		
		System.out.println("For big decimal value without decimals is " + counter.toString());
		startTime = printAndResetTime(startTime);
	}
	
	private static long printAndResetTime(long startTime){
		System.out.println("Total time = " + (System.currentTimeMillis() - startTime) + " ms");
		return System.currentTimeMillis();
	}

}
