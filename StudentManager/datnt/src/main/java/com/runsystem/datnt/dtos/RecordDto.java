package com.runsystem.datnt.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecordDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int recordId;
	private String sex;
	private String birthday;
	private String phone;
	private String email;
	private String address;
	
	public RecordDto() {}

	public RecordDto(int recordId, String sex, String birthday, String phone, String email, String address) {
		super();
		this.recordId = recordId;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
}
