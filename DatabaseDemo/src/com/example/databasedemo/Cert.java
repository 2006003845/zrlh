package com.example.databasedemo;

import java.io.Serializable;

public class Cert extends Obj implements Serializable {

	public Cert(String id, String name) {
		super(id, name);
	}

	public Cert(String name) {
		super(name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5116467745009498565L;

}
