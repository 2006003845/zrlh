package com.example.databasedemo;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend extends UserInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3018228319189314452L;
	
	public String gId;

	public Friend() {
	}

	public Friend(JSONObject json) throws JSONException {
		super(json);
	}

	public Friend(String uId, String uHead, String uName, int uSex, int uAge,
			String uAddr, String uIntro) {
		super(uId, uHead, uName, uSex, uAge, uAddr, uIntro);
	}

}
