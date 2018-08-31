package com.runsystem.datnt.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int recordId;
	private String sex;
	private String birthday;
	private String phone;
	private String email;
	private String address;
}