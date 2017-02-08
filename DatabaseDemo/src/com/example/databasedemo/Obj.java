package com.example.databasedemo;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Obj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4961113952214955693L;
	public String id;
	public String name;

	public Obj(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Obj(String name) {
		super();
		this.name = name;
	}

	public Obj(JSONObject json) throws JSONException {
		
	}

}
