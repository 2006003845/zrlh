package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5990482400104245101L;

	public UserInfo() {
		super();
	}
	
	private int id;
	

	public UserInfo(String uId, String uName, String head) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.head = head;
	}

	public UserInfo(JSONObject json) throws JSONException {
		if (json == null) {
			return;
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Uid)) {
			uId = json.getString(Protocol.ProtocolKey.KEY_Uid);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Uname)) {
			uName = json.getString(Protocol.ProtocolKey.KEY_Uname);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Sex)) {
			String se = json.getString(Protocol.ProtocolKey.KEY_Sex);
			if (se == null)
				sex = 0;
			else
				sex = Integer.parseInt(se.equals("") ? "0" : se);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Email)) {
			email = json.getString(Protocol.ProtocolKey.KEY_Email);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Local)) {
			addr = json.getString(Protocol.ProtocolKey.KEY_Local);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Phone)) {
			phone = json.getString(Protocol.ProtocolKey.KEY_Phone);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Head)) {
			head = json.getString(Protocol.ProtocolKey.KEY_Head);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Birth)) {
			birth = json.getString(Protocol.ProtocolKey.KEY_Birth);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Back)) {
			back = json.getString(Protocol.ProtocolKey.KEY_Back);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Prof)) {
			String p = json.getString(Protocol.ProtocolKey.KEY_Prof);
			if (p != null)
				professions = p.split("\\|");
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Cert)) {
			String p = json.getString(Protocol.ProtocolKey.KEY_Cert);
			if (p != null)
				certs = p.split("\\|");
		}
		

		if (!json.isNull(Protocol.ProtocolKey.KEY_Rname)) {
			rName = json.getString(Protocol.ProtocolKey.KEY_Rname);
		}
	}

	public UserInfo(String uId, String uHead, String uName, int uSex, int uAge,
			String uAddr, String uIntro) {
		super();
		this.uId = uId;
		this.head = uHead;
		this.uName = uName;
		this.sex = uSex;
		this.age = uAge;
		this.addr = uAddr;
		this.sign = uIntro;
	}

	public static List<UserInfo> getUserList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<UserInfo> users = new ArrayList<UserInfo>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			UserInfo u = new UserInfo(jsonO);
			users.add(u);
		}
		return users;
	}

	public boolean isFriend = false;

	private String uId = "";
	private String head = "";
	private String uName = "";
	/**
	 * 0:女 1:男
	 */
	private int sex;
	private int age = 0;
	private String addr;
	private String phone = "";
	private String email = "";
	private String sign = "";
	private String birth = "";
	private String back = "";
	/**
	 * 职业
	 */
	private String[] professions;
	/**
	 * 证书
	 */
	private String[] certs;
	/**
	 * 组id
	 */
	private String gId;

	/**
	 * 真实姓名
	 */
	private String rName;

	private int identity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String[] getProfessions() {
		return professions;
	}

	public void setProfessions(String[] professions) {
		this.professions = professions;
	}

	public String[] getCerts() {
		return certs;
	}

	public void setCerts(String[] certs) {
		this.certs = certs;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}
	
	
	
}
