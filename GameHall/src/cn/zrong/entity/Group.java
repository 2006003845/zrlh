package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import android.graphics.Bitmap;
import cn.zrong.connection.Protocol;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4985834596003993135L;

	public static final String LAG_GROUP = "group";

	private String gId;
	private String gNum;
	private String gName;
	private String gImgUrl;
	private String gNot;

	public String getgNot() {
		return gNot;
	}

	public void setgNot(String gNot) {
		this.gNot = gNot;
	}

	public String getgImgUrl() {
		return gImgUrl;
	}

	public void setgImgUrl(String gImgUrl) {
		this.gImgUrl = gImgUrl;
	}

	private String gExp;
	private String gAuth;
	private String gCap;
	private String gCreateAt;
	private String gSize;
	private String gAdId;
	private String gAdName;
	private List<Member> memberList;
	private boolean isSys;
	private boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isSys() {
		return isSys;
	}

	public void setSys(boolean isSys) {
		this.isSys = isSys;
	}

	public int haveJoin = -1;

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getgNum() {
		return gNum;
	}

	public void setgNum(String gNum) {
		this.gNum = gNum;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgExp() {
		return gExp;
	}

	public void setgExp(String gExp) {
		this.gExp = gExp;
	}

	public String getgAuth() {
		return gAuth;
	}

	public void setgAuth(String gAuth) {
		this.gAuth = gAuth;
	}

	public String getgCap() {
		return gCap;
	}

	public void setgCap(String gCap) {
		this.gCap = gCap;
	}

	public String getgCreateAt() {
		return gCreateAt;
	}

	public void setgCreateAt(String gCreateAt) {
		this.gCreateAt = gCreateAt;
	}

	public String getgSize() {
		return gSize;
	}

	public void setgSize(String gSize) {
		this.gSize = gSize;
	}

	public String getgAdId() {
		return gAdId;
	}

	public void setgAdId(String gAdId) {
		this.gAdId = gAdId;
	}

	public String getgAdName() {
		return gAdName;
	}

	public void setgAdName(String gAdName) {
		this.gAdName = gAdName;
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}

	public Group() {
	}

	public Group(JSONObject json) throws JSONException {
		if (json != null) {
			constructJson(json);
		}
	}

	private void constructJson(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_gId))
			gId = json.getString(Protocol.ProtocolKey.KEY_gId);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gNum))
			gNum = json.getString(Protocol.ProtocolKey.KEY_gNum);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gName))
			gName = json.getString(Protocol.ProtocolKey.KEY_gName);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gImg))
			gImgUrl = json.getString(Protocol.ProtocolKey.KEY_gImg);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gExp))
			gExp = json.getString(Protocol.ProtocolKey.KEY_gExp);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gNot))
			gNot = json.getString(Protocol.ProtocolKey.KEY_gNot);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gAuth))
			gAuth = json.getString(Protocol.ProtocolKey.KEY_gAuth);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gCap))
			gCap = json.getString(Protocol.ProtocolKey.KEY_gCap);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gTime))
			gCreateAt = json.getString(Protocol.ProtocolKey.KEY_gTime);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gSize))
			gSize = json.getString(Protocol.ProtocolKey.KEY_gSize);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gAdId))
			gAdId = json.getString(Protocol.ProtocolKey.KEY_gAdId);
		if (!json.isNull(Protocol.ProtocolKey.KEY_gAdName))
			gAdName = json.getString(Protocol.ProtocolKey.KEY_gAdName);
		if (!json.isNull(Protocol.ProtocolKey.KEY_list))
			memberList = Member.getMembers(json
					.getJSONArray(Protocol.ProtocolKey.KEY_list));
		if (!json.isNull("isSys")) {
			int in = Integer.parseInt(json.getString("isSys"));
			isSys = (in == 1);
		}
		if (!json.isNull("isAdmin")) {
			int in = Integer.parseInt(json.getString("isAdmin"));
			isAdmin = (in == 1);
		}
	}

	public static List<Group> getGroupList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<Group> groupList = new ArrayList<Group>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Group co = new Group(jsonO);
			groupList.add(co);
		}
		return groupList;
	}

	public static class Member implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5363151687941195374L;

		public Member(JSONObject json) throws JSONException {
			if (!json.isNull(Protocol.ProtocolKey.KEY_wbID))
				uID = json.getString(Protocol.ProtocolKey.KEY_wbID);
			if (!json.isNull(Protocol.ProtocolKey.KEY_wbName))
				uName = json.getString(Protocol.ProtocolKey.KEY_wbName);
			if (!json.isNull(Protocol.ProtocolKey.KEY_wbHead))
				uHeadUrl = json.getString(Protocol.ProtocolKey.KEY_wbHead);
			if (!json.isNull(Protocol.ProtocolKey.KEY_isAdmin))
				isAdmin = json.getString(Protocol.ProtocolKey.KEY_isAdmin);
			if (!json.isNull(Protocol.ProtocolKey.KEY_roleID))
				roleId = json.getString(Protocol.ProtocolKey.KEY_roleID);
			if (!json.isNull(Protocol.ProtocolKey.KEY_roleName))
				roleName = json.getString(Protocol.ProtocolKey.KEY_roleName);
			if (!json.isNull(Protocol.ProtocolKey.KEY_uSex))
				uSex = json.getString(Protocol.ProtocolKey.KEY_uSex);
		}

		public Member() {

		}

		public static final List<Member> getMembers(JSONArray array)
				throws JSONException {
			int size = array.length();
			List<Member> memberList = new ArrayList<Member>(size);
			for (int i = 0; i < size; i++) {
				JSONObject jsonO = array.getJSONObject(i);
				Member memb = new Member(jsonO);
				memberList.add(memb);
			}
			return memberList;
		}

		public static final String Adnim_N = "0";
		public static final String Adnim_Y = "1";
		public String uID;
		public String uName;
		public String uHeadUrl;
		public String isAdmin;
		public String roleId;
		public String roleName;
		public String uSex;

	}
}
