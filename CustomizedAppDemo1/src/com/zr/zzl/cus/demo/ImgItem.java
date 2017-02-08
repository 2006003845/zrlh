package com.zr.zzl.cus.demo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class ImgItem {

	public ImgItem(int id, String name, String url, String icon,String text) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.text = text;
	}

	public ImgItem() {
	}

	public int id;
	public String name;
	public String url;
	public String icon;
	public int type;
	public String text;

	public ImgItem(JSONObject json) throws JSONException {
//		if (!json.isNull(Protocol.ProtocolKey.KEY_id))
//			id = json.getInt(Protocol.ProtocolKey.KEY_id);
//		if (!json.isNull(Protocol.ProtocolKey.KEY_name)) {
//			name = json.getString(Protocol.ProtocolKey.KEY_name);
//		}
//		if (!json.isNull(Protocol.ProtocolKey.KEY_icon)) {
//			icon = json.getString(Protocol.ProtocolKey.KEY_icon);
//		}
//		if (!json.isNull(Protocol.ProtocolKey.KEY_url))
//			url = json.getString(Protocol.ProtocolKey.KEY_url);
//		if (!json.isNull(Protocol.ProtocolKey.KEY_bgColor)) {
//			bgColor = json.getInt(Protocol.ProtocolKey.KEY_bgColor);
//		}
//		if (!json.isNull(Protocol.ProtocolKey.KEY_type)) {
//			type = json.getInt(Protocol.ProtocolKey.KEY_type);
//		}
	}
	
	public static List<ImgItem> getItemList(JSONArray array)
			throws JSONException {
		List<ImgItem> list = new ArrayList<ImgItem>();
		for (int i = 0, len = array.length(); i < len; i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj != null) {
				ImgItem p = new ImgItem(obj);
				list.add(p);
			}
		}
		return list;
	}

	public static class ItemTable {
		public static final String TAB_NAME = "imgitem";
		// 字段
		public static final String _ID = "_id";

		public static final String Item_ID = "id";
		public static final String Item_NAME = "name";
		public static final String Item_ICON = "icon";
		public static final String Item_URL = "url";
		public static final String Item_text = "text";
		public static final String Item_Type = "type";

		// 列编�?
		public static final int Item_ID_INDEX = 1;
		public static final int Item_NAME_INDEX = 2;
		public static final int Item_ICON_INDEX = 3;
		public static final int Item_URL_INDEX = 4;
		public static final int Item_TEXT_INDEX = 5;
		public static final int Item_Type_Index = 6;

		public static ImgItem getItem(Cursor cursor) {
			if (cursor.getCount() == 0) {
				return null;
			}
			ImgItem item = new ImgItem();
			item.id = cursor.getInt(Item_ID_INDEX);
			item.name = cursor.getString(Item_NAME_INDEX);
			item.icon = cursor.getString(Item_ICON_INDEX);
			item.url = cursor.getString(Item_URL_INDEX);
			item.text = cursor.getString(Item_TEXT_INDEX);
			item.type = cursor.getInt(Item_Type_Index);
			return item;
		}

		public static List<ImgItem> getItemList(Cursor cursor) {
			if (cursor == null) {
				return null;
			}
			cursor.moveToFirst();
			int count = cursor.getCount();
			ArrayList<ImgItem> list = new ArrayList<ImgItem>();
			do {
				if (count == 0) {
					break;
				}
				ImgItem item = getItem(cursor);
				if (item != null) {
					list.add(item);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}
}
