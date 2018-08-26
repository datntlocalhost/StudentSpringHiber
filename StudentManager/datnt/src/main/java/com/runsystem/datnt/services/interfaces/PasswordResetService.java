package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.PasswordReset;

public interface PasswordResetService {

	public boolean insert(PasswordReset info);
	public List<PasswordReset> selectProcessed();
	public List<PasswordReset> selectUnprocess();
	public PasswordReset acceptReset(String studentCode);
}