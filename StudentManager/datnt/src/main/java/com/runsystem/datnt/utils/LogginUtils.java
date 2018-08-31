package com.runsystem.datnt.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.hibernate.query.Query;

public class LogginUtils {

	private Logger rootLogger;
	
	/*
	 * log appender for console
	 */
	private ConsoleAppender stdoutLogger;
	
	/*
	 * log appender for file.
	 */
	private RollingFileAppender fileLogger;
	
	/*
	 * log appdener for daily file.
	 */
	private DailyRollingFileAppender dailyLogger;
	
	private static LogginUtils instance = null;
	
	private LogginUtils() {
		
		rootLogger   = Logger.getRootLogger();
		stdoutLogger = new ConsoleAppender();
		fileLogger   = new RollingFileAppender();
		dailyLogger  = new DailyRollingFileAppender();
		
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("[%p] %d{yyyy/MM/dd HH:mm:ss} %c{2} - %m%n");
		
		stdoutLogger.setLayout(layout);
		stdoutLogger.setTarget("System.out");
		stdoutLogger.activateOptions();
		
		fileLogger.setFile("logs/program.log");
		fileLogger.setLayout(layout);
		fileLogger.activateOptions();
		
		dailyLogger.setFile("logs/Web");
		dailyLogger.setDatePattern("'_'yyyyMMdd'.log'");
		dailyLogger.setLayout(layout);
		dailyLogger.activateOptions();
		
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(stdoutLogger);
		rootLogger.addAppender(fileLogger);
		rootLogger.addAppender(dailyLogger);
	}
	
	public static LogginUtils getInstance() {
		if (instance == null) {
			instance = new LogginUtils();
		}
		return instance;
	}
	
	/*
	 * return a new logger object for class.
	 * 
	 * @param clazz
	 * 
	 * @return logger
	 */
	private Logger getLogger(Class<?> clazz) {
		return Logger.getLogger(clazz);
	}
	
	/*
	 * Log info when start of method.
	 * 
	 * @param class
	 * @param method's name
	 */
	public void logStart(Class<?> clazz, String method) {
		Logger logger = getLogger(clazz);
		logger.info("[METHOD] " + method + " - [START]");
	}
	
	/*
	 * Log info when a method is end
	 * 
	 * @param clazz
	 * @param method's name
	 */
	public void logEnd(Class<?> clazz, String method) {
		Logger logger = getLogger(clazz);
		logger.info("[METHOD] " + method + " - [END]");
	}
	
	/*
	 * Log info of query (query string and parameters in it)
	 * 
	 * @param clazz
	 * @param query object
	 */
	public void logQuery(Class<?> clazz, Query<?> query) {
		Logger logger = getLogger(clazz);
		logger.info("[EXECUTE QUERY]" + ParameterUtils.paramToString(query));		
	}
	
	/*
	 * Log info of query result.
	 * 
	 * @param clazz
	 * @param result object
	 */
	public void logResult(Class<?> clazz, Object result) {
		Logger logger = getLogger(clazz);
		String string = result == null ? "null" : result.toString();
		logger.info("[EXECUTE RESULT] " + string);
	}
	
	/*
	 * Log info of exception
	 * 
	 * @param clazz
	 * @param exception
	 */
	public void logError(Class<?> clazz, Exception exception) {
		Logger logger = getLogger(clazz);
		logger.error("[ERROR] " + exception);
	}
	
	/*
	 * Log info of content was got from view.
	 * 
	 * @param clazz
	 * @param msg
	 */
	public void logInfo(Class<?> clazz, String msg) {
		Logger logger = getLogger(clazz);
		logger.error("[CONTENT FROM VIEW] " + msg);
	}
}
