package com.runsystem.datnt.daos.interfaces;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.Role;

public interface RoleDao extends GenericDao<Integer, Role> {

	public Role selectRoleByName(String roleName);
}
