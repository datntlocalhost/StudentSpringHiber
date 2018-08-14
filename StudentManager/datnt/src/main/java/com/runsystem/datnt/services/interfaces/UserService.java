package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.User;

public interface UserService {
	
	public boolean    insert(User user);      
	public boolean    update(User user);
	public boolean    delete(Integer userId);
	public User       selectById(int userId);
	public User       selectByName(String name);
	public List<User> selectAll();
}
