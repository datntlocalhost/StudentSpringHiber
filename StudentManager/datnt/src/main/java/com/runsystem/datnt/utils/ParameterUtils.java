package com.runsystem.datnt.utils;

import org.hibernate.query.Query;

public class ParameterUtils {
	
	public static String paramToString(Query<?> query) {
		StringBuilder string = new StringBuilder("\n\n");
		
		@SuppressWarnings("deprecation")
		String param[] = query.getNamedParameters();
		
		string.append("\tString query: " + query.getQueryString() + "\n\n");
		
		for (String s : param) {
			
			string.append("\tParameter [" + s + "]\t: " + query.getParameterValue(s) + "\n");
		}
		
		return string.toString();
	}

}
