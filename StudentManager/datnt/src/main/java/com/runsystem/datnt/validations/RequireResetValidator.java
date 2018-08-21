package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.entities.PasswordReset;
import com.runsystem.datnt.utils.Validation;

public class RequireResetValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		PasswordReset require = (PasswordReset) target;
		
		if (!Validation.validUsername(require.getUsername())){
			errors.rejectValue("username", "user.invalid.username");
		}
		
		if (!Validation.validEmail(require.getEmail(), false)) {
			errors.rejectValue("email", "student.invalid.email");
		}
		
		if (!Validation.validPassword(require.getNewPassword())) {
			errors.rejectValue("newPassword", "user.invalid.password");
		}
	}

}
