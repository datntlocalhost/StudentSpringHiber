package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.PasswordResetDao;
import com.runsystem.datnt.entities.PasswordReset;

@Repository
public class PasswordResetDaoImpl extends AbstractDao<Integer, PasswordReset> implements PasswordResetDao {

	public List<PasswordReset> selectProcessed() {
		return getSession().getNamedQuery("selectProcessed").getResultList();
	}

	public List<PasswordReset> selectUnprocess() {
		return getSession().getNamedQuery("selectUnprocess").getResultList();
	}

	public PasswordReset hasRequire(String username) {
		return (PasswordReset) getSession().getNamedQuery("hasRequireReset").setParameter("username", username).uniqueResult();
	}

	public PasswordReset selectByUsername(String username) {
		return null;
	}

}
