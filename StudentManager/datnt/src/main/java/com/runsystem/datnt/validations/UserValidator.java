package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.entities.User;

public class UserValidator implements Validator {

	public boolean supports(Class<?> obj) {
		return User.class.equals(obj);
	}

	public void validate(Object target, Errors errors) {
		String usernameRegex = "^[a-zA-Z0-9]{4,26}$";
		String passwordRegex = "^[a-zA-Z0-9!@#$%^&*()]{4,32}$";
		
		User user = (User) target;
		
		if (!user.getUsername().matches(usernameRegex)) {
			errors.rejectValue("username", "user.invalid.username");
		}
		
		if (!user.getPassword().matches(passwordRegex)) {
			errors.rejectValue("password", "user.invalid.password");
		}
	}
}
