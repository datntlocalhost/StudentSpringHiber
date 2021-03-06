package com.runsystem.datnt.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.exceptions.AuthException;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InputInvalidException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.ResponePackage;
import com.runsystem.datnt.utils.HeaderPackage;
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
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public @ResponseBody String processExceptionHandler(Exception ex) {
		LogginUtils.getInstance().logStart(this.getClass(), "processExceptionHandler");
		
		LogginUtils.getInstance().logError(this.getClass(), ex);
		ResponePackage responePackage = null;
		
		if (ex instanceof InputInvalidException) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
			responePackage = new ResponePackage(HeaderPackage.INPUT_INVALID);
		} else if (ex instanceof InsertException) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
			responePackage = new ResponePackage(HeaderPackage.CREATE_ERROR);
		} else if (ex instanceof UpdateException) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
			responePackage = new ResponePackage(HeaderPackage.UPDATE_ERROR);
		} else if (ex instanceof DeleteException) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
			responePackage = new ResponePackage(HeaderPackage.DELETE_ERROR);
		} else if (ex instanceof AuthException) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
			responePackage = new ResponePackage(HeaderPackage.AUTH_REQUIREMENT);
		}
		
		//Create string json with null object
		String json = JsonUtils.objectToJson(responePackage);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "processExceptionHandler");
		
		return json;
	}

}
