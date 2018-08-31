package com.runsystem.datnt.daos;

import java.util.List;

import com.runsystem.datnt.dtos.*;
import com.runsystem.datnt.exceptions.SelectNullException;

public interface RoleDao {

	public List<RoleDto> getUserRole(int userId) throws SelectNullException;
}
