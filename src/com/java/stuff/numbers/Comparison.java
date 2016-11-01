package com.java.stuff.numbers;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Comparison {

	private static long times = 99999999l;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		testBigDecimal();
		startTime = printAndResetTime(startTime);

		testBigDecimalWithoutDecimals();
		startTime = printAndResetTime(startTime);

		testObjectDouble();
		startTime = printAndResetTime(startTime);

		testObjectLong();
		startTime = printAndResetTime(startTime);

		testPrimitiveDouble();
		startTime = printAndResetTime(startTime);

		testDoublePrimitiveWithoutDecimals();
		startTime = printAndResetTime(startTime);

		testLongWithoutDecimals();
		startTime = printAndResetTime(startTime);

		testDoubleStream();
		startTime = printAndResetTime(startTime);

		testLongStream();
		startTime = printAndResetTime(startTime);

		testParallelDoubleStream();
		startTime = printAndResetTime(startTime);

		testParallelLongStream();
		startTime = printAndResetTime(startTime);

	}

	private static void testPrimitiveDouble() {
		double primitive = 0d;

		for (long i = 0; i < times; i++)
			primitive += 0.2;

		System.out.println("For primitive double value is " + primitive);
	}

	private static void testObjectDouble() {
		Double obj = new Double(0);

		for (long i = 0; i < times; i++)
			obj += 0.2d;

		System.out.println("For object Double value is " + obj);
	}

	private static void testBigDecimal() {
		BigDecimal bd = new BigDecimal("0.2");
		BigDecimal counter = new BigDecimal("0");

		for (long i = 0; i < times; i++)
			counter = counter.add(bd);

		System.out.println("For big decimal value is " + counter.toString());
	}

	private static void testLongWithoutDecimals() {
		long lgprimitive = 0l;

		for (long i = 0; i < times; i++)
			lgprimitive += 2l;

		System.out.println("For long primitive value without decimals is " + lgprimitive);
	}

	private static void testObjectLong() {
		Long obj = new Long(0);

		for (long i = 0; i < times; i++)
			obj += 2l;

		System.out.println("For object Long value is " + obj);
	}

	private static void testDoublePrimitiveWithoutDecimals() {
		double primitive = 0d;

		for (long i = 0; i < times; i++)
			primitive += 2d;

		System.out.println("For double primitive value without decimals is " + primitive);
	}

	private static void testBigDecimalWithoutDecimals() {
		BigDecimal bd = new BigDecimal("0.2");
		BigDecimal counter = new BigDecimal("0");

		counter = new BigDecimal("0");
		bd = new BigDecimal("2");
		for (long i = 0; i < times; i++)
			counter = counter.add(bd);

		System.out.println("For big decimal value without decimals is " + counter.toString());
	}

	private static void testDoubleStream() {
		DoubleStream ds = IntStream.range(0, (int) times).mapToDouble(i -> 0.2);
		OptionalDouble res = ds.reduce((x, y) -> x + y);

		System.out.println("For double stream the value is " + res.getAsDouble());
	}

	private static void testLongStream() {
		LongStream ds = IntStream.range(0, (int) times).mapToLong(i -> 2l);
		OptionalLong res = ds.reduce((x, y) -> x + y);

		System.out.println("For long stream without decimals the value is " + res.getAsLong());
	}

	private static void testParallelDoubleStream() {
		DoubleStream ds = IntStream.range(0, (int) times).mapToDouble(i -> 2d);
		OptionalDouble res = ds.parallel().reduce((x, y) -> x + y);

		System.out.println("For parallel double stream without decimals the value is " + res.getAsDouble());
	}

	private static void testParallelLongStream() {
		LongStream ls = IntStream.range(0, (int) times).mapToLong(i -> 2l);
		OptionalLong res = ls.parallel().reduce((x, y) -> x + y);

		System.out.println("For parallel long stream without decimals the value is " + res.getAsLong());
	}

	private static long printAndResetTime(long startTime) {
		System.out.println("Total time = " + (System.currentTimeMillis() - startTime) + " ms");
		return System.currentTimeMillis();
	}

}
