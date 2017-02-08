package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2681580186187911867L;

	public String id;
	public String name;
	public String pwd;
	public UserInfo userInfo;
//	/**
//	 * 身份
//	 */
//	public int identity = 0;

	public Result result;
	public int stat = -2;

	public boolean isMemb = false;

	public Account(Result result, int stat) {
		this.result = result;
		this.stat = stat;
	}

	public Account() {
	}

	public Account(JSONObject json) throws JSONException {
		if (json == null) {
			return;
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Aid)) {
			id = json.getString(Protocol.ProtocolKey.KEY_Aid);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Name)) {
			name = json.getString(Protocol.ProtocolKey.KEY_Name);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Pwd)) {
			pwd = json.getString(Protocol.ProtocolKey.KEY_Pwd);
		}
		userInfo = new UserInfo(json);
	}

	public Account(JSONObject json, int stat) throws JSONException {
		if (json == null) {
			return;
		}
		this.stat = stat;
		if (!json.isNull(Protocol.ProtocolKey.KEY_Name)) {
			name = json.getString(Protocol.ProtocolKey.KEY_Name);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Pwd)) {
			pwd = json.getString(Protocol.ProtocolKey.KEY_Pwd);
		}
		userInfo = new UserInfo(json);
	}

	public static List<Account> getAccountList(JSONArray array)
			throws JSONException {
		if (array == null) {
			return null;
		}
		List<Account> list = new ArrayList<Account>();
		for (int i = 0, len = array.length(); i < len; i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj != null) {
				Account p = new Account(obj);
				list.add(p);
			}
		}
		return list;
	}

}
