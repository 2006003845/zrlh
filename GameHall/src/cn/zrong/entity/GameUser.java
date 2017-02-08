package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import cn.zrong.connection.Protocol;
import cn.zrong.entity.RoleInfo.Gender;

public class GameUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7285507715090521840L;

	public GameUser() {

	}

	public GameUser(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_wbID))
			wbID = jsonO.getString(Protocol.ProtocolKey.KEY_wbID);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_wbName))
			wbName = jsonO.getString(Protocol.ProtocolKey.KEY_wbName);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_wbHead))
			wbHead = jsonO.getString(Protocol.ProtocolKey.KEY_wbHead);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Name))
			wbName = jsonO.getString(Protocol.ProtocolKey.KEY_Name);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_sex))
			gender = Gender.getGender(jsonO
					.getString(Protocol.ProtocolKey.KEY_sex));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_city))
			city = new City(jsonO.getString(Protocol.ProtocolKey.KEY_city));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_type))
			type = Integer.parseInt(jsonO
					.getString(Protocol.ProtocolKey.KEY_type));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_sys))
			userSysPortrait = jsonO.getString(Protocol.ProtocolKey.KEY_sys);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_def))
			selfDefPortrait = jsonO.getString(Protocol.ProtocolKey.KEY_def);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_use))
			usedPortrait = jsonO.getString(Protocol.ProtocolKey.KEY_use);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_att))
			attentionNum = Integer.parseInt(jsonO
					.getString(Protocol.ProtocolKey.KEY_att));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_fans))
			fansNum = Integer.parseInt(jsonO
					.getString(Protocol.ProtocolKey.KEY_fans));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_speech))
			speechsNum = Integer.parseInt(jsonO
					.getString(Protocol.ProtocolKey.KEY_speech));
		// if (!jsonO.isNull(Protocol.ProtocolKey.KEY_fans))
		// fansNum = Integer.parseInt(jsonO
		// .getString(Protocol.ProtocolKey.KEY_fans));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_atten))
			isAtten = Integer.parseInt(jsonO
					.getString(Protocol.ProtocolKey.KEY_atten));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_intro))
			intro = jsonO.getString(Protocol.ProtocolKey.KEY_intro);
	}

	public String wbID;
	public String wbName;
	public String wbHead;
	public Gender gender;
	public City city;
	public String intro;
	public int type;
	public int isAtten;
	public int isFans;

	/**
	 * 不是关注的
	 */
	public static final int TYPE_ATTEN_NO = 0;
	/**
	 * 是关注的
	 */
	public static final int TYPE_ATTEN_YES = 1;
	/**
	 * 不是粉丝
	 */
	public static final int TYPE_FANS_NO = 0;
	/**
	 * 是粉丝
	 */
	public static final int TYPE_FANS_YES = 1;

	/**
	 * 用户系统头像索引
	 */
	public String userSysPortrait;
	/**
	 * 用户自定义头像索引
	 */
	public String selfDefPortrait;
	/**
	 * 用户使用的头像
	 */
	public String usedPortrait;// 0/1
	/**
	 * 关注用户数量
	 */
	public int attentionNum;
	/**
	 * fans 数量
	 */
	public int fansNum;
	/**
	 * 发言数量
	 */
	public int speechsNum;

	public static final int CLASSIFY_NO = 0;
	public static final int CLASSIFY_SCHOOLMATE = 1;
	public static final int CLASSIFY_COLLEAGUE = 2;
	public static final int CLASSIFY_FRIEND = 3;
	public static final int CLASSIFY_STARE = 4;
	public static final int CLASSIFY_Special = 5;

	public static List<GameUser> getWeibUserList(JSONArray array)
			throws JSONException {

		int size = array.length();
		List<GameUser> users = new ArrayList<GameUser>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			GameUser u = new GameUser(jsonO);

			users.add(u);
		}
		return users;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return wbName;
	}

}
