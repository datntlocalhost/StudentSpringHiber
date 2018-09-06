package com.runsystem.datnt.dtos;
import java.io.Serializable;

import lombok.Data;

@Data
public class SchoolDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int schoolId;
	private String schoolCode;
	private String schoolName;
	
	public SchoolDto() {}

	public SchoolDto(int schoolId, String schoolCode, String schoolName) {
		super();
		this.schoolId = schoolId;
		this.schoolCode = schoolCode;
		this.schoolName = schoolName;
	}
}
