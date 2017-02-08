package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class F_Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3508068095344207398L;

	public String id;
	public String name;
	public int type;

	public static final int Type_Default = 0;
	public static final int Type_Define = 1;

	public F_Group(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public F_Group() {
		super();
	}

	public F_Group(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Gid))
			id = jsonO.getString(Protocol.ProtocolKey.KEY_Gid);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Gname))
			name = jsonO.getString(Protocol.ProtocolKey.KEY_Gname);
	}

	public static List<F_Group> getFGroupList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<F_Group> os = new ArrayList<F_Group>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			F_Group u = new F_Group(jsonO);
			os.add(u);
		}
		return os;
	}

	public static class FGroupTable {
		public static final String TAB_NAME = "fg";
		// 字段
		public static final String _ID = "_id";
		public static final String FGroup_ID = "_uid";
		public static final String FGroup_NAME = "_name";
		public static final String FGroup_Account = "_account";

		// 列编号
		public static final int FGroup_ID_INDEX = 1;
		public static final int FGroup_NAME_INDEX = 2;
		public static final int FGroup_Account_INDEX = 3;

		public static F_Group getF_Group(Cursor cursor) {
			if (cursor.getCount() == 0) {
				return null;
			}
			F_Group fg = new F_Group();
			fg.id = cursor.getString(FGroup_ID_INDEX);
			fg.name = cursor.getString(FGroup_NAME_INDEX);

			return fg;
		}

		public static List<F_Group> getF_GroupList(Cursor cursor) {
			cursor.moveToFirst();
			int count = cursor.getCount();
			ArrayList<F_Group> list = new ArrayList<F_Group>();
			do {
				if (count == 0) {
					break;
				}
				F_Group fg = getF_Group(cursor);
				if (fg != null) {
					list.add(fg);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}

}
