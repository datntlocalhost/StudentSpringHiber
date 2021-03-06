package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.dtos.UserDto;

public class UserValidator implements Validator {

	public boolean supports(Class<?> obj) {
		return UserDto.class.equals(obj);
	}

	public void validate(Object target, Errors errors) {
		String usernameRegex = "^[a-zA-Z0-9]{4,26}$";
		String passwordRegex = "^[a-zA-Z0-9!@#$%^&*()]{4,32}$";
		
		UserDto user = (UserDto) target;
		
		if (!user.getUsername().matches(usernameRegex)) {
			errors.rejectValue("username", "user.invalid.username");
		}
		
		if (!user.getPassword().matches(passwordRegex)) {
			errors.rejectValue("password", "user.invalid.password");
		}
	}
}
