package com.runsystem.datnt.daos;

import java.io.IOException;

import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;

public interface UserDao {
	
	public void insert(UserDto user) throws InsertException, IOException;
	public void insertUserRole(int idUser, int idRole) throws InsertException, IOException;
	public void delete(int id) throws DeleteException, IOException;
	public void update(UserDto user) throws UpdateException, IOException;
	
	public Integer nextPK() throws IOException;
	
	/*
	 * Get user by username from database
	 */
	public UserDto selectByUsername(String username) throws IOException;
	
	public UserDto selectByStudentId(int id) throws IOException;
	
	public UserDto userLogin(UserDto user) throws IOException;
}
