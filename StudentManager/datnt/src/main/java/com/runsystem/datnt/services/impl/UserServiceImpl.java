package com.runsystem.datnt.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.RoleDao;
import com.runsystem.datnt.daos.UserDao;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.dtos.RoleDto;
import com.runsystem.datnt.services.UserService;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.Sha256Hash;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Transactional
	public UserDto getUserByUsername(String username) {
		LogginUtils.getInstance().logStart(this.getClass(), "getUserByUsername");
		
		UserDto user = null;
		List<RoleDto> roles = null;
		
		try {
			user = userDao.selectByUsername(username);
			
			if (user != null) {
				roles = roleDao.getUserRole(user.getUserId());
			}
			
			if (roles != null) {
				user.setRoles(roles);
			}
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getUserByUsername");
		
		return user;
	}

	/*
	 * Select user info by username and password for login.
	 * 
	 * @param userInfo
	 * 
	 * @return UserDto
	 */
	@Transactional
	public UserDto userLogin(UserDto userInfo) {
		LogginUtils.getInstance().logStart(this.getClass(), "userLogin");
		
		UserDto user = null;
		
		//Hash password before check login.
		userInfo.setPassword(Sha256Hash.hash(userInfo.getPassword()));
		
		try {
			user = userDao.userLogin(userInfo);
			
			List<RoleDto> roles = roleDao.getUserRole(user.getUserId());
			
			user.setRoles(roles);
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		} catch (NullPointerException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "userLogin");
		return user;
	}

}
