package com.runsystem.datnt.daos;

import java.io.IOException;
import java.util.List;

import com.runsystem.datnt.dtos.RoleDto;

public interface RoleDao {

	public List<RoleDto> getUserRole(int userId) throws IOException;
}
