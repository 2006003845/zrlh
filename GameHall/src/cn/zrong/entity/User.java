package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.zrong.entity.RoleInfo.Gender;

import json.JSONArray;
import json.JSONException;
import android.database.Cursor;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4395553819588032201L;

	public User() {

	}

	public User(String keyID, String u_name, String u_psd) {
		super();
		this.keyID = keyID;
		this.u_name = u_name;
		this.u_psd = u_psd;
	}

	private String keyID = "";
	private String u_name;
	private String u_psd;

	private RoleInfo userInfo;

	public RoleInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(RoleInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_psd() {
		return u_psd;
	}

	public void setU_psd(String u_psd) {
		this.u_psd = u_psd;
	}

	/**
	 * 用户数据表
	 * 
	 * @author zhouzhilong
	 * 
	 */
	public static class UserTable {
		public static final String TAB_NAME = "user";
		// 字段名
		public static final String _ID = "_id";
		public static final String USER_ID = "user_keyid";
		public static final String USER_NAME = "user_name";
		public static final String USER_PSD = "user_psd";
		public static final String USER_NICKNAME = "user_nickname";
		public static final String USER_GENDER = "user_gender";
		public static final String USER_HEADURL = "user_headurl";

		// 列编号
		public static final int USER_ID_INDEX = 1;
		public static final int USER_NAME_INDEX = 2;
		public static final int USER_PSD_INDEX = 3;
		public static final int USER_NICKNAME_INDEX = 4;
		public static final int USER_GENDER_INDEX = 5;
		public static final int USER_HEADURL_INDEX = 6;

		public static User getUser(Cursor cursor) {
			cursor.moveToFirst();
			if (cursor.getCount() == 0) {
				cursor.close();
				return null;
			}
			User user = new User();
			user.keyID = cursor.getString(USER_ID_INDEX);
			user.u_name = cursor.getString(USER_NAME_INDEX);
			user.u_psd = cursor.getString(USER_PSD_INDEX);
			user.userInfo = new RoleInfo(cursor.getString(USER_NICKNAME_INDEX),
					Gender.getGender(cursor.getString(USER_GENDER_INDEX)));
			cursor.close();
			return user;
		}
	}
}
