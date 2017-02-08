package com.example.databasedemo;

import java.io.Serializable;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387735030523893568L;

	public int state;
	public String msg;
	public Result(int state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}
	
	

}
