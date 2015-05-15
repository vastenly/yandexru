package com.firstutility.taf.utils.random;

import java.util.Arrays;
import java.util.Random;

public class RandomNumeric {
	
	private final static int ONE = 1;
	private final static int TEN = 10;
	private final static Random random = new Random();

	public static int getRandomInt() {
		return random.nextInt();
	}

	public static int getRandomInt(int limit) {
		return random.nextInt(limit);
	}
	
	public static int getRandomInt(int min, int max) {
		return random.nextInt(max - min) + min;	
	}
	
	public static int getRandomInt(int min, int max, int[] exclude) {
		Arrays.sort(exclude);
		int rand = 0;
		do {
			rand = getRandomInt(min, max);
		} while (Arrays.binarySearch(exclude, rand) >= 0);
		return rand;		
	}

	public static double getRandomDouble() {
		return random.nextDouble();
	}

	public static double getRandomDouble(double limit) {
		int degree = 0;
		double limitValue = limit;
		double value = 0;
		while (limitValue > ONE) {
			limitValue /= TEN;
			degree++;
		}
		while (true) {
			value = random.nextDouble();
			if (value <= limitValue) {
				break;
			}
		}
		return value * Math.pow(TEN, degree);
	}

	public static float getRandomFloat() {
		return random.nextFloat();
	}

	public static float getRandomFloat(float limit) {
		int degree = 0;
		float limitValue = limit;
		float value = 0;
		while( limitValue > ONE ) {
			limitValue /= TEN;
			degree++;
		}
		while (true) {
			value = random.nextFloat();
			if (value <= limitValue) {
				break;
			}
		}
		return (float) (value * Math.pow(TEN, degree));
	}

	public static long getRandomLong() {
		return random.nextLong();
	}

	public static long getRandomLong(long limit) {
		long value = 0;
		while( true ) {
			value = random.nextLong();
			if( value <= limit ) {
				return value;
			}
		}
	}
}
