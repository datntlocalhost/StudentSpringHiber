package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Role;

public interface RoleDao {

	public void       insert(Role role);      
	public void       update(Role role);
	public void       delete(Integer roleId);
	public Role       selectById(int roleId);
	public Role       selectByName(String name);
	public List<Role> selectAll();
}
