package com.runsystem.datnt.models;

import java.io.Serializable;
import java.util.List;

import com.runsystem.datnt.entities.PasswordReset;

public class ResetPwdModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PasswordReset> unprocess;
	private List<PasswordReset> processed;
	
	public ResetPwdModel() {}
	
	public ResetPwdModel(List<PasswordReset> unprocess, List<PasswordReset> processed) {
		super();
		this.unprocess = unprocess;
		this.processed = processed;
	}

	public List<PasswordReset> getUnprocess() {
		return unprocess;
	}

	public void setUnprocess(List<PasswordReset> unprocess) {
		this.unprocess = unprocess;
	}

	public List<PasswordReset> getProcessed() {
		return processed;
	}

	public void setProcessed(List<PasswordReset> processed) {
		this.processed = processed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
