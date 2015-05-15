package com.firstutility.taf.utils.random;

import java.util.Random;
import java.util.Vector;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Generate random String with or without (optionally) special symbols
 */
public class RandomString extends RandomStringUtils {
	
	/** Default length of string with special symbols */
	private static final int DEFAULT_LENGTH = 5;

	/** Special symbols intervals in ASCII codes table */
	private static final Vector<int[]> SPEC_SYMBOLS_INTERVALS = new Vector<int[]>();

	static {
		/** ! " # $ % & ' ( ) * + - . / */
		SPEC_SYMBOLS_INTERVALS.add( new int[] {32, 47} );
		/** : ; < = > ? */
		SPEC_SYMBOLS_INTERVALS.add( new int[] {58, 64} );
		/** [ \ ] ^ _ ` */
		SPEC_SYMBOLS_INTERVALS.add( new int[] {91, 96} );
		/** { | } ~ */
		SPEC_SYMBOLS_INTERVALS.add( new int[] {123, 126} );
	}

	private static boolean isSpecSymbol(int value) {
		for( int i = 0; i < SPEC_SYMBOLS_INTERVALS.size(); i++ ) {
			int[] interval = SPEC_SYMBOLS_INTERVALS.get( i );
			/** Interval must have start and end indexes */
			if( interval.length < 2 ) {
				continue;
			}
			if( value >= interval[0] && value <= interval[1] ) {
				return true;
			}
		}
		return false;
	}

	public static char getRandomSpecSymbol() {
		int value = new Random().nextInt( 127 );
		return (char) value;
	}

	/**
	 * Generate string with special symbols only.
	 * @param length
	 * @return
	 */
	public static String getRandomSpecSymbolsString(int length) {
		StringBuilder builder = new StringBuilder();
		int symbolsCount = length;
		while (symbolsCount > 0) {
			char randValue = getRandomSpecSymbol();
			if (isSpecSymbol(randValue)) {
				builder.append(randValue);
				symbolsCount--;
			}
		}
		return builder.toString().trim();
	}

	/**
	 * Generate string with special symbols only. Symbols will be used from <code>validSpecSymbols</code> 
	 * @param length
	 * @param validSpecSymbols
	 * @return
	 */
  	public static String getRandomSpecSymbolsString(int length, char[] validSpecSymbols) {
  		StringBuilder builder = new StringBuilder();
  		int symbolsCount = length;
  		while (symbolsCount > 0) {
  			char randValue = getRandomSpecSymbol();
  			for( int i = 0; i < validSpecSymbols.length; i++ ) {
  				if( randValue == validSpecSymbols[i] ) {
  					builder.append(randValue);
  					symbolsCount--;
  				}
  			}
  		}
  		return builder.toString().trim();
  	}

  	/**
  	 * Generate string with only special symbols with {@link RandomString.DEFAULT_LENGTH} 
  	 * @return
  	 */
  	public static String getRandomSpecSymbolsString() {
  		return getRandomSpecSymbolsString(DEFAULT_LENGTH);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAscii()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH}
  	 * 
  	 * @param length
  	 * @return
  	 */
  	public static String getRandomSpecAsciiString(int length) {
  		return randomAscii(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
  	}
  	
  	public static String getRandomAlphabetic(int length) {
  		return randomAlphabetic(length);
  	}
  	
  	public static String getRandomNumeric(int length) {
  		return randomNumeric(length);
  	}
  	
  	public static String getRandomAlphanumeric(int length) {
  		return randomAlphanumeric(length);
  	}
  	
	public static String getRandomHexNumeric() {
		int number = RandomNumeric.getRandomInt(16);
		if ( number == 10 )
			return "A";
		if ( number == 11 )
			return "B";
		if ( number == 12 )
			return "C";
		if ( number == 13 )
			return "D";
		if ( number == 14 )
			return "E";
		if ( number == 15 )
			return "F";
		return String.valueOf(number);
	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAlphabetic()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH}
  	 * @param length
  	 * @return
  	 */
  	public static String getRandomSpecAlphabetic(int length) {
  		return randomAlphabetic(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAlphanumeric()</code> with special symbols. Special symbols
  	 * string length is {@link RandomString.DEFAULT_LENGTH} 
  	 * @param length
  	 * @return
  	 */
  	public static final String getRandomSpecAlphanumeric(int length) {
  		return randomAlphanumeric(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomNumeric()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH} 
  	 * @param length
  	 * @return
  	 */
  	public static final String getRandomSpecNumeric(int length) {
  		return randomNumeric(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
  	}

  	/**
  	 * Generate string like <code>randomSpecSymbolsString()</code> with special symbols. Special symbols string length is
  	 * {@link RandomString.DEFAULT_LENGTH} 
  	 * @param validSpecSymbols - symbols which accessible to generate
  	 * @return
  	 */
  	public static String getRandomSpecSymbolsString(char[] validSpecSymbols) {
  		return getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAscii()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH}
  	 * @param length
  	 * @param validSpecSymbols - symbols which accessible to generate
  	 * @return
  	 */
  	public static String getRandomSpecAsciiString(int length, char[] validSpecSymbols) {
  		return randomAscii(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAlphabetic()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH}
  	 * @param length
  	 * @param validSpecSymbols - symbols which accessible to generate
  	 * @return
  	 */
  	public static String getRandomSpecAlphabetic(int length, char[] validSpecSymbols) {
  		return randomAlphabetic(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomAlphanumeric()</code> with special symbols. Special symbols
  	 * string length is {@link RandomString.DEFAULT_LENGTH}
  	 * @param length
  	 * @param validSpecSymbols - symbols which accessible to generate
  	 * @return
  	 */
  	public static final String getRandomSpecAlphanumeric(int length, char[] validSpecSymbols) {
  		return randomAlphanumeric(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
  	}

  	/**
  	 * Generate string like <code>RandomStringUtils.randomNumeric()</code> with special symbols. Special symbols string
  	 * length is {@link RandomString.DEFAULT_LENGTH}
  	 * @param length
  	 * @param validSpecSymbols - symbols which accessible to generate
  	 * @return
  	 */
  	public static final String getRandomSpecNumeric(int length, char[] validSpecSymbols) {
  		return randomNumeric(length-DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
  	}
}