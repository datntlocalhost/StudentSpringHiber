package com.runsystem.datnt.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;

@ControllerAdvice(basePackages = "com.runsystem.datnt.controllers")
public class ExceptionHandlerController {
	
	
	/*
	 * Process when controller throws any exception,
	 * get exception info and return to view an null object json.
	 * 
	 * @param exception
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody String processExceptionHandler(Exception ex) {
		LogginUtils.getInstance().logStart(this.getClass(), "processExceptionHandler");
		
		LogginUtils.getInstance().logError(this.getClass(), ex);
		
		//Create string json with null object
		String json = JsonUtils.objectToJson(null);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "processExceptionHandler");
		
		return json;
	}

}