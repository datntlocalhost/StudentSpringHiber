package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.entities.User;

public class UserValidator implements Validator {

	public boolean supports(Class<?> obj) {
		return User.class.equals(obj);
	}

	public void validate(Object target, Errors errors) {
		String usernameRegex = "";
		String passwordRegex = "";
		
		User user = (User) target;
		
		if (!user.getUsername().matches(usernameRegex)) {
			
		}
		
		if (!user.getPassword().matches(passwordRegex)) {
			
		}
	}
}
