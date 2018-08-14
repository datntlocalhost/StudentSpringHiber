package com.runsystem.datnt.daos.implementations;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.RoleDao;
import com.runsystem.datnt.entities.Role;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

	public void insert(Role role) {
		persist(role);
	}

	public void update(Role role) {
		update(role);
	}

	public void delete(Integer roleId) {
		delete(roleId);
	}

	public Role selectById(int roleId) {
		return getByKey(roleId);
	}

	@SuppressWarnings("deprecation")
	public Role selectByName(String name) {
		return (Role) getSession().getNamedQuery("selectRoleByName").setString("roleName", name).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Role> selectAll() {
		return getSession().getNamedQuery("selectRoleAll").list();
	}
}