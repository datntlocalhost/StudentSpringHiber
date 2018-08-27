package com.runsystem.datnt.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.models.StudentListModel;

@Controller
public class AdminController {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SchoolDao schoolDao;

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<StudentListModel> studentList = studentDao.list();
		List<School> schools = schoolDao.list();
		
		model.addAttribute("studentList", studentList);
		model.addAttribute("schools", schools);
		
		return "studentlist";
	}
}
