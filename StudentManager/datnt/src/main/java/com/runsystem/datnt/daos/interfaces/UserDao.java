package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.User;

public interface UserDao {
	public void       insert(User user);      
	public void       update(User user);
	public void       delete(Integer userId);
	public User       selectById(int userId);
	public User       selectByName(String name);
	public List<User> selectAll();
}
