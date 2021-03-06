package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.utils.Validation;

public class SearchValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		SearchStudentModel search = (SearchStudentModel) target;
		
		if (!Validation.validCode(search.getStudentCode(), true)) {
			errors.rejectValue("studentCode", "student.invalid.code");
		}
		
		if (!Validation.validName(search.getStudentName(), true)) {
			errors.rejectValue("studentName", "student.invalid.name");
		}
		
		if (!Validation.validSex(search.getSex(), true)) {
			errors.rejectValue("sex", "student.invalid.sex");
		}
		
		if (!Validation.validDateRange(search.getDateFrom(), search.getDateTo())) {
			errors.rejectValue("dateFrom", "student.invalid.dateFrom");
			errors.rejectValue("dateTo", "student.invalid.dateTo");
		}
	}

}
