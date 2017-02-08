package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class Classes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719197693691096179L;
	public String classId;
	public String className;
	public String depart;
	public int count;
	public String manager;
	public int identity;
	public String rname;
	/**
	 * 0:女 1:男
	 */
	public int sex;

	public Result result;
	public int stat = -2;

	public Classes(Result result, int stat) {
		this.result = result;
		this.stat = stat;
	}

	public Classes() {
	}

	public Classes(JSONObject json) throws JSONException {
		if (json == null) {
			return;
		}

		if (!json.isNull(Protocol.ProtocolKey.KEY_Tid)) {
			classId = json.getString(Protocol.ProtocolKey.KEY_Tid);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Tname)) {
			className = json.getString(Protocol.ProtocolKey.KEY_Tname);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Depart)) {
			depart = json.getString(Protocol.ProtocolKey.KEY_Depart);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Count)) {
			String c = json.getString(Protocol.ProtocolKey.KEY_Count);
			if (c != null && !c.equals("")) {
				count = Integer.parseInt(c);
			}
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Manager)) {
			manager = json.getString(Protocol.ProtocolKey.KEY_Manager);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Status)) {
			String c = json.getString(Protocol.ProtocolKey.KEY_Status);
			if (c != null && !c.equals(""))
				identity = Integer.parseInt(c);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Rname)) {
			rname = json.getString(Protocol.ProtocolKey.KEY_Rname);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Sex)) {
			String se = json.getString(Protocol.ProtocolKey.KEY_Sex);
			if (Tools.isNumeric(se))
				sex = Integer.parseInt(se.equals("") ? "0" : se);
			else
				sex = 0;
		}

	}

	public static List<Classes> getClassesList(JSONArray array)
			throws JSONException {
		List<Classes> list = new ArrayList<Classes>();
		if (array == null) {
			return list;
		}
		for (int i = 0, len = array.length(); i < len; i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj != null) {
				Classes p = new Classes(obj);
				list.add(p);
			}
		}
		return list;
	}

	/**
	 * 用户数据
	 * 
	 * @author zhouzhilong
	 * 
	 */
	public static class AuthTable {
		public static final String TAB_NAME = "auth";
		// 字段名
		public static final String _ID = "_id";
		public static final String Auth_Account = "_account";
		public static final String Auth_ClassId = "_cid";
		public static final String Auth_ClassNAME = "_cname";
		public static final String Auth_Depart = "_depart";
		public static final String Auth_Count = "_count";
		public static final String Auth_Manager = "_manager";
		public static final String Auth_Identity = "_identity";
		public static final String Auth_RName = "_rname";
		public static final String Auth_Sex = "_sex";

		// 列编号

		public static final int Auth_Account_Index = 1;
		public static final int Auth_ClassId_Index = 2;
		public static final int Auth_ClassNAME_Index = 3;
		public static final int Auth_Depart_Index = 4;
		public static final int Auth_Count_Index = 5;
		public static final int Auth_Manager_Index = 6;
		public static final int Auth_Identity_Index = 7;
		public static final int Auth_RName_Index = 8;
		public static final int Auth_Sex_Index = 9;

		public static Classes getAuth(Cursor cursor, boolean isList) {
			// cursor.moveToFirst();
			if (cursor.getCount() == 0) {
				if (!isList) {
					cursor.close();
				}
				cursor.close();
				return null;
			}
			Classes auth = new Classes();
			auth.classId = cursor.getString(Auth_ClassId_Index);
			auth.className = cursor.getString(Auth_ClassNAME_Index);
			auth.depart = cursor.getString(Auth_Depart_Index);
			auth.count = cursor.getInt(Auth_Count_Index);
			auth.manager = cursor.getString(Auth_Manager_Index);
			auth.identity = cursor.getInt(Auth_Identity_Index);
			auth.rname = cursor.getString(Auth_RName_Index);
			auth.sex = cursor.getInt(Auth_Sex_Index);
			if (!isList)
				cursor.close();
			return auth;
		}

		public static List<Classes> getAuthList(Cursor cursor) {
			cursor.moveToFirst();
			int count = cursor.getCount();
			ArrayList<Classes> list = new ArrayList<Classes>();
			do {
				if (count == 0) {
					break;
				}
				Classes Auth = getAuth(cursor, true);
				if (Auth != null) {
					list.add(Auth);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}

}
