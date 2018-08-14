package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.UserRolesDao;
import com.runsystem.datnt.entities.UserRoles;

@Repository
@Transactional
public class UserRolesDaoImpl extends AbstractDao<Integer, UserRoles> implements UserRolesDao {

	public List<UserRoles> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
