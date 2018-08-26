package com.runsystem.datnt.aops;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LoggingAOP {
	
	private Logger logger;
	
	public void onLogController(JoinPoint joinPoint) {
		logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info("call '" + joinPoint.getSignature().getName() + "' method.");
	}
	
	public void onLogService(JoinPoint joinPoint) {
		logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info("call '" + joinPoint.getSignature().getName() + "' method.");
	}
	
}
