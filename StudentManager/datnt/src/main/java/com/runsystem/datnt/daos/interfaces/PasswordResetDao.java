package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.PasswordReset;

public interface PasswordResetDao extends GenericDao<Integer, PasswordReset>{
	
	public List<PasswordReset> selectProcessed();
	public List<PasswordReset> selectUnprocess();
	public PasswordReset hasRequire(String username);
	public PasswordReset selectByUsername(String username);
	
}
