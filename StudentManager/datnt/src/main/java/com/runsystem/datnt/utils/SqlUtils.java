package com.runsystem.datnt.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SqlUtils {
	
	public static final String SCHOOL_LIST              = "sql/school_list.sql";
	public static final String SCHOOL_ID_BY_CODE        = "sql/school_id_by_code.sql";
	
	public static final String STUDENT_LIST			    = "sql/student_list.sql";
	public static final String STUDENT_INSERT			= "sql/student_insert.sql";
	public static final String STUDENT_DELETE			= "sql/student_delete.sql";
	public static final String STUDENT_UPDATE			= "sql/student_update.sql";
	public static final String STUDENT_SEARCH           = "sql/student_search.sql";
	public static final String STUDENT_SEARCH_WITH_DATE = "sql/student_search_with_date.sql";
	public static final String STUDENT_SELECT_BY_CODE   = "sql/student_select_by_code.sql";
	public static final String STUDENT_ID_BY_CODE		= "sql/student_id_by_code.sql";
	public static final String STUDENT_MAX_ID			= "sql/student_select_max_id.sql";
	public static final String STUDENT_MAX_CODE			= "sql/student_select_max_code.sql";
	
	public static final String RECORDS_INSERT           = "sql/records_insert.sql";
	public static final String RECORDS_DELETE		    = "sql/records_delete.sql";
	public static final String RECORDS_UPDATE			= "sql/records_update.sql";
	
	public static final String TOKEN_INSERT				= "sql/token_insert.sql";
	public static final String TOKEN_DELETE				= "sql/token_delete.sql";
	public static final String TOKEN_LAST_TOKEN			= "sql/token_select_last_token.sql";
	
	public static final String USER_INSERT				= "sql/user_insert.sql";
	public static final String USER_INSERT_ROLES		= "sql/user_insert_roles.sql";
	public static final String USER_UPDATE				= "sql/user_update.sql";
	public static final String USER_DELETE				= "sql/user_delete.sql";
	public static final String USER_MAX_ID				= "sql/user_select_max_id.sql";
	public static final String USER_LOGIN				= "sql/user_login.sql";
	public static final String USER_SELECT_BY_USERNAME  = "sql/user_select_by_username.sql";
	public static final String USER_SELECT_BY_STUDENTID = "sql/user_select_by_studentid.sql";
	
	public static final String ROLE_SELECT_BY_USERID	= "sql/role_select_by_userid.sql";
	
	
	/*
	 * Read query string from the file .sql and return a query string.
	 * 
	 * @param path
	 * 		  path of the .sql file
	 * 
	 * @return string
	 * 
	 * @throws IOException
	 */
	public static String getSQL(String path) throws IOException {
		
		ClassLoader classLoader = SqlUtils.class.getClassLoader();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(classLoader.getResource(path).getFile()));
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String string;
		
		while ( (string = bufferedReader.readLine()) != null ) {
			stringBuilder.append(string);
		}
		
		bufferedReader.close();
		return stringBuilder.toString();
	}
}
