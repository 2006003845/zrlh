package com.example.databasedemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mate extends UserInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957637212647944597L;

	public int identity;

	public Mate() {
		super();
	}

	public Mate(JSONObject json) throws JSONException {
		super(json);
		if (!json.isNull(Protocol.ProtocolKey.KEY_Status)) {
			identity = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_Status));
		}
	}

	public static List<Mate> getMateList(JSONArray array) throws JSONException {
		int size = array.length();
		List<Mate> users = new ArrayList<Mate>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Mate u = new Mate(jsonO);
			users.add(u);
		}
		return users;
	}

}
