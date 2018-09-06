package com.runsystem.datnt.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.runsystem.datnt.utils.HeaderPackage;

import lombok.Data;

@Data
public class ResponePackage<T> implements Serializable {
	
	private static final long serialVersionUID = 8133486797902934222L;
	private HeaderPackage headerPackage;
	private List<T> data = new ArrayList<T>();
	
	public ResponePackage() {}
	
	public ResponePackage(HeaderPackage headerPackage) {
		this.headerPackage = headerPackage;
	}

	public ResponePackage(HeaderPackage headerPackage, List<T> data) {
		super();
		this.headerPackage = headerPackage;
		this.data = data;
	}

}
