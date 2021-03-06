package com.runsystem.datnt.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.utils.Validation;

public class StudentValidator implements Validator {
	
	private boolean hasPassword = false;
	
	public StudentValidator() {}
	
	public StudentValidator(boolean hasPassword) {
		this.hasPassword = hasPassword;
	}

	public boolean supports(Class<?> arg0) {
		return StudentModel.class.equals(arg0);
	}

	public void validate(Object target, Errors errors) {
		
		StudentModel student = (StudentModel) target;
		
		if (student != null) {
			if (!Validation.validName(student.getStudentName(), false)) {
				errors.rejectValue("studentName", "student.invalid.name");
			}
			
			if (!Validation.validSchoolYear(student.getSchoolYear())) {
				errors.rejectValue("schoolYear", "student.invalid.startyear");
			}
			
			if (!Validation.validSex(student.getSex(), false)) {
				errors.rejectValue("sex", "student.invalid.sex");
			}
			
			if (!Validation.validDate(student.getBirthday())) {
				errors.rejectValue("birthday", "student.invalid.birthday");
			}
			
			if (!Validation.validPhone(student.getPhone(), true)) {
				errors.rejectValue("phone", "student.invalid.phone");
			}
			
			if (!Validation.validEmail(student.getEmail(), true)) {
				errors.rejectValue("email", "student.invalid.email");
			}
			
			if (!Validation.validAddress(student.getAddress(), true)) {
				errors.rejectValue("address", "student.invalid.address");
			}
			
			if (hasPassword) {
				if (!Validation.validPassword(student.getPassword())) {
					errors.rejectValue("password", "user.invalid.password");
				}
			}
			
		} else {
			errors.rejectValue("invalidStudent", "invalid.student");
		}
		
	}

}
