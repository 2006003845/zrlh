package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9151284818965386360L;

	public Msg() {

	}

	public Msg(String id, String type, String uName, String head, String time,
			String content, String uId, int sendType, int state) {
		super();
		this.id = id;
		this.type = type;
		this.uName = uName;
		this.head = head;
		this.time = time;
		this.content = content;
		this.uId = uId;
		this.sendType = sendType;
		this.state = state;
	}

	public Msg(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Mid))
			id = jsonO.getString(Protocol.ProtocolKey.KEY_Mid);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Uid))
			uId = jsonO.getString(Protocol.ProtocolKey.KEY_Uid);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Uname))
			uName = jsonO.getString(Protocol.ProtocolKey.KEY_Uname);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Content))
			content = jsonO.getString(Protocol.ProtocolKey.KEY_Content);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Time))
			time = jsonO.getString(Protocol.ProtocolKey.KEY_Time);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Head))
			head = jsonO.getString(Protocol.ProtocolKey.KEY_Head);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Type))
			type = jsonO.getString(Protocol.ProtocolKey.KEY_Type);
	}

	public String id;
	public String type;
	public String uName;
	public String head;
	public String time;
	public String content;
	public String uId;

	public int newitems;

	public static List<Msg> getMsgList(JSONArray array) throws JSONException {
		int size = array.length();
		List<Msg> os = new ArrayList<Msg>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Msg u = new Msg(jsonO);
			os.add(u);
		}
		return os;
	}

	public int sendType;
	public static final int TYPE_MAIL_IN = 1;
	public static final int TYPE_MAIL_OUT = 2;

	public int state = STATE_SEND_SUCCESS;// 发送状态
	public static final int STATE_SENDING = 0;
	public static final int STATE_SEND_SUCCESS = 1;
	public static final int STATE_SEND_FAILED = 2;
	public static final int STATE_SEND_NO = 3;

	public static final String TYPE_SYSTEM = "3";
	public static final String TYPE_UNSYSTEM = "2";

	public static class MsgTable {
		public static final String TAB_NAME = "msg";
		// 字段
		public static final String _ID = "_id";

		public static final String Msg_ID = "id";
		public static final String Msg_UNAME = "name";
		public static final String Msg_Type = "type";
		public static final String Msg_Head = "head";
		public static final String Msg_Time = "time";
		public static final String Msg_Content = "content";
		public static final String Msg_Uid = "uid";
		public static final String Msg_SendType = "sendtype";
		public static final String Msg_Account = "account";

		// 列编号
		public static final int Msg_ID_Index = 1;
		public static final int Msg_UNAME_Index = 2;
		public static final int Msg_Type_Index = 3;
		public static final int Msg_Head_Index = 4;
		public static final int Msg_Time_Index = 5;
		public static final int Msg_Content_Index = 6;
		public static final int Msg_Uid_Index = 7;
		public static final int Msg_SendType_Index = 8;
		public static final int Msg_Account_Index = 9;

		public static Msg getMsg(Cursor cursor) {
			if (cursor.getCount() == 0) {
				return null;
			}
			Msg m = new Msg();
			m.id = cursor.getString(Msg_ID_Index);
			m.uName = cursor.getString(Msg_UNAME_Index);
			m.type = cursor.getString(Msg_Type_Index);
			m.head = cursor.getString(Msg_Head_Index);
			m.time = cursor.getString(Msg_Time_Index);
			m.content = cursor.getString(Msg_Content_Index);
			m.uId = cursor.getString(Msg_Uid_Index);
			m.sendType = cursor.getInt(Msg_SendType_Index);
			return m;
		}

		public synchronized static List<Msg> getMsgList(Cursor cursor) {
			if (cursor == null) {
				return null;
			}
			cursor.moveToFirst();
			int count = cursor.getCount();
			if (count == 0) {
				cursor.close();
				return null;
			}

			ArrayList<Msg> list = new ArrayList<Msg>();
			do {
				if (count == 0) {
					break;
				}
				Msg item = getMsg(cursor);
				if (item != null) {
					list.add(item);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}

	public static class MsgUserTable {
		public static final String TAB_NAME = "msg_user";
		// 字段
		public static final String _ID = "_id";

		public static final String Msg_ID = "id";
		public static final String Msg_UNAME = "name";
		public static final String Msg_Type = "type";
		public static final String Msg_Head = "head";
		public static final String Msg_Time = "time";
		public static final String Msg_Content = "content";
		public static final String Msg_Uid = "uid";
		public static final String Msg_SendType = "sendtype";
		public static final String Msg_NewItem = "newitems";
		public static final String Msg_Account = "account";

		// 列编号
		public static final int Msg_ID_Index = 1;
		public static final int Msg_UNAME_Index = 2;
		public static final int Msg_Type_Index = 3;
		public static final int Msg_Head_Index = 4;
		public static final int Msg_Time_Index = 5;
		public static final int Msg_Content_Index = 6;
		public static final int Msg_Uid_Index = 7;
		public static final int Msg_SendType_Index = 8;
		public static final int Msg_NewItem_Index = 9;
		public static final int Msg_Account_Index = 10;

		public static Msg getMsg(Cursor cursor) {
			if (cursor.getCount() == 0) {
				return null;
			}
			Msg m = new Msg();
			m.id = cursor.getString(Msg_ID_Index);
			m.uName = cursor.getString(Msg_UNAME_Index);
			m.type = cursor.getString(Msg_Type_Index);
			m.head = cursor.getString(Msg_Head_Index);
			m.time = cursor.getString(Msg_Time_Index);
			m.content = cursor.getString(Msg_Content_Index);
			m.uId = cursor.getString(Msg_Uid_Index);
			m.sendType = cursor.getInt(Msg_SendType_Index);
			m.newitems = cursor.getInt(Msg_NewItem_Index);
			return m;
		}

		public synchronized static List<Msg> getMsgList(Cursor cursor) {
			if (cursor == null) {
				return null;
			}
			cursor.moveToFirst();
			int count = cursor.getCount();
			ArrayList<Msg> list = new ArrayList<Msg>();
			do {
				if (count == 0) {
					break;
				}
				Msg item = getMsg(cursor);
				if (item != null) {
					list.add(item);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}

}
