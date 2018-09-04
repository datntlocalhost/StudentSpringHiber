package com.runsystem.datnt.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.runsystem.datnt.utils.HeaderPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponePackage<T> implements Serializable {
	
	private static final long serialVersionUID = 8133486797902934222L;
	private HeaderPackage headerPackage;
	private List<T> data = new ArrayList<T>();
	
	public ResponePackage(HeaderPackage headerPackage) {
		this.headerPackage = headerPackage;
	}

}
