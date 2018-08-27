package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.utils.Validation;

@Controller
public class DeleteController {

	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public @ResponseBody boolean deleteStudent(@ModelAttribute StudentModel deleteInfo, 
												HttpServletRequest request, HttpServletResponse response) {
		if (!Validation.validCode(deleteInfo.getStudentCode(), false)) {
			return false;
		}
		return studentDao.delete(deleteInfo.getStudentCode());
	}
}
