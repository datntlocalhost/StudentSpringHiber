package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.models.ChangePwdModel;

public class ChangePwdValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		
		ChangePwdModel changePwdModel = (ChangePwdModel) target;
		
		if (changePwdModel != null) {
			if (!changePwdModel.getNewPassword().equals(changePwdModel.getConfirmPassword())) {
				errors.rejectValue("confirmIncorrect", "confirm.password.incorrect");
			}
		}
	}

}
