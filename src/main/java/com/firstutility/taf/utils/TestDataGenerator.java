package com.firstutility.taf.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestDataGenerator {

    public final static String EMPTY = "EMPTY" ;

	public static <T extends Enum<?>> T generateEnumValue(Class<T> clazz) {
		Random random = new Random();
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	public static String incrementInputDate(String inputDate, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = format.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return format.format(c.getTime());
	}
	
	public static String generateCurrentDate(String template) {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.MINUTE, 10);
		DateFormat format = new SimpleDateFormat(template);
		return format.format(c.getTime());
	}

    public static String parsingInputDate(String inputDate, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
		try {
			date = format.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return format.format(c.getTime());
    }

}
