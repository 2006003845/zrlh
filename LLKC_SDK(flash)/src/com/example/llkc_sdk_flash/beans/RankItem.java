package com.example.llkc_sdk_flash.beans;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RankItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3383925953127907091L;

	public RankItem() {
		super();
	}

	public RankItem(String gid, String uid, String uname, String head, int score) {
		super();
		this.gid = gid;
		this.uid = uid;
		this.uname = uname;
		this.head = head;
		this.score = score;
	}

	public static ArrayList<RankItem> getList(JSONArray array)
			throws JSONException {
		int size = array.length();
		ArrayList<RankItem> os = new ArrayList<RankItem>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			RankItem u = new RankItem(jsonO);
			os.add(u);
		}
		return os;
	}

	public RankItem(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull("gameId"))
			gid = jsonO.getString("gameId");
		if (!jsonO.isNull("userId"))
			uid = jsonO.getString("userId");
		if (!jsonO.isNull("uName"))
			uname = jsonO.getString("uName");
		if (!jsonO.isNull("uSex"))
			sex = jsonO.getString("uSex");
		if (!jsonO.isNull("uHead"))
			head = jsonO.getString("uHead");
		if (!jsonO.isNull("score")) {
			String s = jsonO.getString("score");
			if (s != null && !s.equals(""))
				try {
					score = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					Log.e("error", "NumberFormatException: try change " + s
							+ "to integer");
				}
		}
		if (!jsonO.isNull("ranking")) {
			String s = jsonO.getString("ranking");
			if (s != null && !s.equals(""))
				try {
					ranking = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					Log.e("error", "NumberFormatException: try change " + s
							+ "to integer");
				}
		}
	}

	private String gid;// 游戏id
	private String uid;// 用户id
	private String uname;// 用户昵称
	private String sex;// 性别
	private String head;// 用户头像
	private int score;// 用户得分
	private int ranking;// 排名
	private int integral;// 积分

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "gid:" + gid + "\n name:" + uname + "\n head:" + head
				+ "\n sex:" + sex + "\n score:" + score;
	}

}
