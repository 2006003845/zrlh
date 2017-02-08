package com.example.llkc_sdk_flash.beans;

import java.io.Serializable;

public class UserInfo implements Serializable {

	public UserInfo(String account, String pwd, String uid, String uname,
			String sex, String head, int score) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.uid = uid;
		this.uname = uname;
		this.sex = sex;
		this.head = head;
		this.score = score;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2404115090038256262L;

	private String account;// 账户
	private String pwd;// 密码
	private String uid;// 昵称
	private String uname;// 昵称
	private String sex;// 性别
	private String head;// 头像
	private int score;// 积分

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
