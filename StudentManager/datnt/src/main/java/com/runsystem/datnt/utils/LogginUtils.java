package com.runsystem.datnt.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

public class LogginUtils {

	private Logger logger = LogManager.getLogger(); 
	
	private static LogginUtils instance = null;
	
	private LogginUtils() {}
	
	public static LogginUtils getInstance() {
		if (instance == null) {
			instance = new LogginUtils();
		}
		return instance;
	}
	
	/*
	 * Log info when start of method.
	 * 
	 * @param class
	 * @param method's name
	 */
	public void logStart(Class<?> clazz, String method) {
		logger.info(clazz.getSimpleName() + " - [METHOD] " + method + " - [START]");
	}
	
	/*
	 * Log info when a method is end
	 * 
	 * @param clazz
	 * @param method's name
	 */
	public void logEnd(Class<?> clazz, String method) {
		logger.info(clazz.getSimpleName() + " - [METHOD] " + method + " - [END]");
	}
	
	/*
	 * Log info of query (query string and parameters in it)
	 * 
	 * @param clazz
	 * @param query object
	 */
	public void logQuery(Class<?> clazz, Query<?> query) {
		logger.info(clazz.getSimpleName() + " - [CONTENT]" + ParameterUtils.paramToString(query));		
	}
	
	/*
	 * Log info of query result.
	 * 
	 * @param clazz
	 * @param result object
	 */
	public void logResult(Class<?> clazz, Object result) {
		String string = result == null ? "null" : result.toString();
		logger.info(clazz.getSimpleName() + " - [CONTENT]\n\n" + "\tQUERY RESULT: " + string + "\n");
	}
	
	/*
	 * Log info of exception
	 * 
	 * @param clazz
	 * @param exception
	 */
	public void logError(Class<?> clazz, Exception exception) {
		logger.error(clazz.getSimpleName() + " - [ERROR] ", exception);
	}
	
	/*
	 * Log info of content was got from view.
	 * 
	 * @param clazz
	 * @param msg
	 */
	public void logInputFromView(Class<?> clazz, HttpServletRequest request, String msg) {
		logger.info(clazz.getSimpleName() + " - [CONTENT]\n\n" + "\tINPUT FROM "+ request.getLocalAddr() + ": " + msg + "\n");
	}
	
	public void logContent(Class<?> clazz, String msg) {
		logger.info(clazz.getSimpleName() + " - [CONTENT] " + msg);
	}
}
