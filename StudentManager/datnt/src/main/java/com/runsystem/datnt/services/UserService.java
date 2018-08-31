package com.runsystem.datnt.services;

import com.runsystem.datnt.dtos.UserDto;

public interface UserService {
	
	public UserDto getUserByUsername(String username);
	
	public UserDto userLogin(UserDto userInfo);
}
