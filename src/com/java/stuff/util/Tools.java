package com.java.stuff.util;

public class Tools {
	
	public static long printAndResetTime(long startTime) {
		System.out.println("Total time = " + (System.nanoTime() - startTime) + " ns");
		return System.nanoTime();
	}

}
