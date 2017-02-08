package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import json.JSONObject;
import android.graphics.Bitmap;

import cn.zrong.ApplicationData;
import cn.zrong.connection.Protocol;

public class RoleInfo implements Serializable {

	private static final long serialVersionUID = 4127749874873391246L;

	public RoleInfo() {
	}

	public RoleInfo(String index, String nickName, Gender gender, City city,
			String intro, String userSysPortrait, String selfDefPortrait,
			String usedPortrait) {
		super();
		this.index = index;
		this.nickName = nickName;
		this.gender = gender;
		this.city = city;
		this.intro = intro;
		this.userSysPortrait = userSysPortrait;
		this.selfDefPortrait = selfDefPortrait;
		this.usedPortrait = usedPortrait;

	}

	public RoleInfo(String name, Gender gender) {
		this.nickName = name;
		this.gender = gender;
	}

	public RoleInfo(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_INDEX))
			index = jsonO.getString(Protocol.ProtocolKey.KEY_INDEX);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Name))
			nickName = jsonO.getString(Protocol.ProtocolKey.KEY_Name);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_def))
			headerUrl = jsonO.getString(Protocol.ProtocolKey.KEY_def);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_sex))
			gender = Gender.getGender(jsonO
					.getString(Protocol.ProtocolKey.KEY_sex));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_city))
			city = new City(jsonO.getString(Protocol.ProtocolKey.KEY_city));
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_intro))
			intro = jsonO.getString(Protocol.ProtocolKey.KEY_intro);

		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_sys))
			userSysPortrait = jsonO.getString(Protocol.ProtocolKey.KEY_sys);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_def))
			selfDefPortrait = jsonO.getString(Protocol.ProtocolKey.KEY_def);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_appid))
			appid = jsonO.getString(Protocol.ProtocolKey.KEY_appid);
	}

	public String userId;
	public String index;
	public String nickName;
	public String headerUrl;
	public Gender gender;
	// public int age;
	// public String blood;
	// public Date birthDay;
	// public String constellation;
	public City city;

	public String intro;
	/**
	 * 用户系统头像索引
	 */
	public String userSysPortrait;
	/**
	 * 用户自定义头像索引
	 */
	public String selfDefPortrait;
	/**
	 * 产品代码
	 */
	public String appid;

	/**
	 * 用户使用的头像
	 */
	public String usedPortrait;// 0/1

	public Bitmap form_icon;

	/**
	 * 性别
	 * 
	 * @author lenovo
	 * 
	 */
	public enum Gender implements Serializable {
		男, 女;
		public String toString() {
			return this.equals(男) ? "男" : "女";
		};

		public static Gender getGender(String gend) {
			if ("0".equals(gend)) {
				return 男;
			} else if ("1".equals(gend)) {
				return 女;
			} else {
				return null;
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nickName + gender + city.name;
	}

}
