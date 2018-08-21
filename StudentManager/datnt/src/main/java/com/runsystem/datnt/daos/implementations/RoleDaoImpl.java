package com.runsystem.datnt.daos.implementations;

import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.RoleDao;
import com.runsystem.datnt.entities.Role;

@Repository
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

	public Role selectRoleByName(String roleName) {
		return buildQuery("selectRoleByName").setParameter("roleName", roleName).uniqueResult();
	}

}
