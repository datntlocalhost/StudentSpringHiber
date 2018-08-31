package com.runsystem.datnt.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.dtos.SchoolDto;
import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.services.SchoolService;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.LogginUtils;

@Controller
public class AdminController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * Set some info into model and return page to client.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * 
	 * @return admin list page.
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		LogginUtils.getInstance().logStart(this.getClass(), "loadPage");
		
		List<SchoolDto> schools = schoolService.getSchoolList();
		List<StudentInfoDto> students = studentService.getStudentList();
		
		model.addAttribute("studentList", students);
		model.addAttribute("schools", schools);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "loadPage");
		return "studentlist";
	}
}
