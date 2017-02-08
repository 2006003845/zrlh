package com.example.databasedemo;

import java.io.Serializable;

public class City extends Obj implements Serializable {

	public String id;
	public String name;
	public City(String id, String name) {
		super(id, name);
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5693974410177708740L;
	


}
