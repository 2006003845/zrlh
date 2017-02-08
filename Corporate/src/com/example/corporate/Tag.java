package com.example.corporate;

import org.json.JSONException;
import org.json.JSONObject;

public class Tag extends Obj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319507981231842328L;

	public Tag(JSONObject json) throws JSONException {
		super(json);
	}

	public Tag(String id, String name) {
		super(id, name);
	}

	public Tag(String name) {
		super(name);
	}

}
