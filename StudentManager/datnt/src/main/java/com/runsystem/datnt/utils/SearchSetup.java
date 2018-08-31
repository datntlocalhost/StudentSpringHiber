package com.runsystem.datnt.utils;

import com.runsystem.datnt.models.SearchStudentModel;

public class SearchSetup {
	
	/*
	 * Setup search info before search from database.
	 * 
	 * @param searchinfo
	 * 
	 * @return searchinfo after setup
	 */
	public static SearchStudentModel setup (SearchStudentModel searchInfo) {
		
		searchInfo.setStudentCode("%" + searchInfo.getStudentCode() + "%");
		searchInfo.setStudentName("%" + searchInfo.getStudentName() + "%");
		searchInfo.setSex(searchInfo.getSex() + "%");
		searchInfo.setSchool("%" + searchInfo.getSchool() + "%");
		return searchInfo;
	}
}
