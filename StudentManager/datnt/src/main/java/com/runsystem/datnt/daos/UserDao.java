package com.runsystem.datnt.daos;

import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.exceptions.UpdateException;

public interface UserDao {
	
	public void insert(UserDto user) throws InsertException;
	public void insertUserRole(int idUser, int idRole) throws InsertException;
	public void delete(int id) throws DeleteException;
	public void update(UserDto user) throws UpdateException;
	
	public Integer nextPK() throws SelectNullException;
	/*
	 * Get user by username from database
	 */
	public UserDto selectByUsername(String username) throws SelectNullException;
	
	public UserDto selectByStudentId(int id) throws SelectNullException;
	
	public UserDto userLogin(UserDto user) throws SelectNullException;
}
