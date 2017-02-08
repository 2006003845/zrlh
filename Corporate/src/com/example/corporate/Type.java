package com.example.corporate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Type extends Obj {

	private static final long serialVersionUID = -4070669018591493814L;

	public Type(JSONObject json) throws JSONException {
		super(json);

	}

	public Type(String id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public Type(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<Obj> getList(JSONArray array) throws JSONException {
		if (array == null) {
			return null;
		}
		ArrayList<Obj> list = new ArrayList<Obj>();
		for (int i = 0, len = array.length(); i < len; i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj != null) {
				Type p = new Type(obj);
				list.add(p);
			}
		}
		return list;
	}

}
