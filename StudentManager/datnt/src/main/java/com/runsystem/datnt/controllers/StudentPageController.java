package com.runsystem.datnt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.interfaces.StudentService;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class StudentPageController {
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * Load page student info and add model attribute to render.
	 * 
	 * @param model
	 * 
	 * @return student info page
	 */
	@RequestMapping(value = "/student/info", method = RequestMethod.GET)
	public String loadPage(Model model) {
		//Get student username info from spring security
		String studentCode = getPrincipal();
		
		//Load student info from database by student's code
		Student student = studentService.selectByCode(studentCode);
		
		//create new student model and init
		StudentModel studentModel = new StudentModel();
		
		if (student != null) {
			studentModel.setStudentCode(student.getStudentCode());
			studentModel.setStudentName(student.getStudentName());
			studentModel.setSex(student.getRecord().getSex());
			studentModel.setBirthday(student.getRecord().getBirthday());
			studentModel.setSchool(student.getSchool().getSchoolCode());
			studentModel.setSchoolYear(student.getStartYear());
			studentModel.setPhone(student.getRecord().getPhone());
			studentModel.setEmail(student.getRecord().getEmail());
			studentModel.setAddress(student.getRecord().getAddress());
		}
		
		model.addAttribute("student", studentModel);
		
		return "studentrecord";
	}
	
	@RequestMapping(value = "/student/info", method = RequestMethod.POST)
	public String saveChanges(@ModelAttribute StudentModel studentInfo, BindingResult bindingResult, Model model) {		
		StudentValidator validator = new StudentValidator();
		Student student = studentService.selectByCode(getPrincipal());
		validator.validate(studentInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			studentInfo.setPhone(student.getRecord().getPhone());
			studentInfo.setEmail(student.getRecord().getEmail());
			studentInfo.setAddress(student.getRecord().getAddress());
			studentInfo.setStudentName(student.getStudentName());
			
			model.addAttribute("invalid", true);
			model.addAttribute("student", studentInfo);
			
			return "studentrecord";
		}
		
		if (student != null) {
			
			student.getRecord().setPhone(studentInfo.getPhone());
			student.getRecord().setEmail(studentInfo.getEmail());
			student.getRecord().setAddress(studentInfo.getAddress());
			
			StudentModel studentModel =  studentService.updateRecord(student);
			if (studentModel != null) {
				model.addAttribute("student", studentModel);
				model.addAttribute("success", true);
			} else {
				model.addAttribute("student", new StudentModel());
				model.addAttribute("error", true);
			}
		}
		
		return "studentrecord";
	}
	
	/*
	 * Get username of student
	 */
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}