package com.zzl.zl_app.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Obj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6880521423930974578L;

	private String id;
	private String name;

	public Obj(JSONObject json) throws JSONException {

	}

	public Obj() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
