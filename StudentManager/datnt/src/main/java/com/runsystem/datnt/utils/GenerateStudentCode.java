package com.runsystem.datnt.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateStudentCode {
	
	/*
	 * Generate student code
	 * 
	 * @param index
	 * 
	 * @return code string
	 */
	public static String getCode(String code) {
		Calendar calendar = Calendar.getInstance();
		
		//Format date, just get 2 last number of year string
		SimpleDateFormat format = new SimpleDateFormat("yy");
		String year = format.format(calendar.getTime());
		
		//If create in this year
		if (!isNull(code) && code.startsWith(year)) {
			//return with value of current code + 1
			return String.valueOf(Long.parseLong(code) + 1);
		}
		
		return year + String.format("%06d", 0);
	}
	
	/*
	 * Check a string is null or empty
	 * 
	 * @param s
	 * 
	 * @return boolean true if string is null or empty, esle false
	 */
	private static boolean isNull(String s) {
		return s == null || s.isEmpty(); 
	}
}
