package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.datnt.daos.interfaces.RoleDao;
import com.runsystem.datnt.entities.Role;
import com.runsystem.datnt.services.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	public void insert(Role role) {
		
	}

	public void update(Role role) {
		
	}

	public void delete(Integer roleId) {
		
	}

	public Role selectById(int roleId) {
		return roleDao.selectById(roleId);
	}

	public Role selectByName(String name) {
		return roleDao.selectByName(name);
	}

	public List<Role> selectAll() {
		return roleDao.selectAll();
	}

}