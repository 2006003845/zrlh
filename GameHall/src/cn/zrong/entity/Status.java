package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import cn.zrong.connection.Community;
import cn.zrong.connection.Protocol;

public class Status implements Serializable {


	/**
	 * index wbID wbName content time source
	 */

	private String index;
	private String wbID;
	private String wbName;
	private String wbHead;
	private String hdType;
	private String content;
	private String picImgUrl;
	private Date createdAt;
	private String zhuanfaCount;
	private String commentCount;
	private String hitCount;

	public String getPicImgUrl() {
		return picImgUrl;
	}

	public void setPicImgUrl(String picImgUrl) {
		this.picImgUrl = picImgUrl;
	}

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public String getSubPicUrl() {
		return subPicUrl;
	}

	public void setSubPicUrl(String subPicUrl) {
		this.subPicUrl = subPicUrl;
	}

	private String subContentId;
	private String subContent;
	private String subPicUrl;
	private String subCommentCount;
	private String subZhuanfaCount;
	private String subHitCount;

	public String getWbHead() {
		return wbHead;
	}

	public void setWbHead(String wbHead) {
		this.wbHead = wbHead;
	}

	public String getHdType() {
		return hdType;
	}

	public void setHdType(String hdType) {
		this.hdType = hdType;
	}

	public String getSubZhuanfaCount() {
		return subZhuanfaCount;
	}

	public void setSubZhuanfaCount(String subZhuanfaCount) {
		this.subZhuanfaCount = subZhuanfaCount;
	}

	public String getSubContentId() {
		return subContentId;
	}

	public void setSubContentId(String subContentId) {
		this.subContentId = subContentId;
	}

	public String getSubCommentCount() {
		return subCommentCount;
	}

	public void setSubCommentCount(String subCommentCount) {
		this.subCommentCount = subCommentCount;
	}

	public String getSubHitCount() {
		return subHitCount;
	}

	public void setSubHitCount(String subHitCount) {
		this.subHitCount = subHitCount;
	}

	private Status retweeted_status;// 转发

	public Status getRetweeted_status() {
		return retweeted_status;
	}

	public void setRetweeted_status(Status retweeted_status) {
		this.retweeted_status = retweeted_status;
	}

	public String getZhuanfaCount() {
		return zhuanfaCount;
	}

	public void setZhuanfaCount(String zhuanfaCount) {
		this.zhuanfaCount = zhuanfaCount;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getHitCount() {
		return hitCount;
	}

	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * 所有
	 */
	public static final int TYPE_ALL = 0;
	/**
	 * 我自己
	 */
	public static final int TYPE_SELF = 1;
	/**
	 * 我关注
	 */
	public static final int TYPE_MYATTENTION = 2;
	/**
	 * 话题
	 */
	public static final int TYPE_HUATI = 3;
	/**
	 * 标签
	 */
	public static final int TYPE_LABEL = 4;
	/**
	 * 群消息
	 */
	public static final int TYPE_GROUP = 5;
	/**
	 * 详情
	 */
	public static final int TYPE_DETAIL = 9;
	/**
	 * 指定人微博
	 */
	public static final int TYPE_NOMINATOR = 10;

	private static final long serialVersionUID = 1608000492860584608L;

	public Status() {
	}

	private void constructJson(JSONObject json) throws JSONException {

		if (!json.isNull("focus")) {
			JSONArray array2 = json.getJSONArray("focus");
			int len = array2.length();
			for (int i = 0; i < len; i++) {
				JSONObject obj = array2.getJSONObject(i);
				Focus focus = new Focus();
				if (!json.isNull("index"))
					focus.index = obj.getString("index");
				if (!json.isNull("wbID"))
					focus.wbID = obj.getString("wbID");
				if (!json.isNull("Name"))
					focus.fName = obj.getString("Name");
				if (!json.isNull("type"))
					focus.fName = obj.getString("type");
				focusList.add(focus);
			}
		}

		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX)) {
			index = json.getString(Protocol.ProtocolKey.KEY_INDEX);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_wbID)) {
			wbID = json.getString(Protocol.ProtocolKey.KEY_wbID);
		}

		if (!json.isNull(Protocol.ProtocolKey.KEY_roleID))
			roleID = json.getString(Protocol.ProtocolKey.KEY_roleID);
		if (!json.isNull(Protocol.ProtocolKey.KEY_wbName)) {
			wbName = json.getString(Protocol.ProtocolKey.KEY_wbName);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_content)) {
			content = json.getString(Protocol.ProtocolKey.KEY_content);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_img)) {
			picImgUrl = json.getString(Protocol.ProtocolKey.KEY_img);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_wbHead)) {
			wbHead = json.getString(Protocol.ProtocolKey.KEY_wbHead);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_createAt)) {
			createdAt = Community.parseDate(
					json.getString(Protocol.ProtocolKey.KEY_createAt),
					"yyyy-MM-dd HH:mm:ss");
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_for)) {
			zhuanfaCount = json.getString(Protocol.ProtocolKey.KEY_for);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_comm)) {
			commentCount = json.getString(Protocol.ProtocolKey.KEY_comm);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_hit)) {
			hitCount = json.getString(Protocol.ProtocolKey.KEY_hit);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_source)) {

			JSONObject sourceJson = json
					.getJSONObject(Protocol.ProtocolKey.KEY_source);
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_contID)) {
				subContentId = sourceJson
						.getString(Protocol.ProtocolKey.KEY_contID);
			}
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_contMsg)) {
				subContent = sourceJson
						.getString(Protocol.ProtocolKey.KEY_contMsg);
			}
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_contUrl)) {
				subPicUrl = sourceJson
						.getString(Protocol.ProtocolKey.KEY_contUrl);
			}
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_comm)) {
				subCommentCount = sourceJson
						.getString(Protocol.ProtocolKey.KEY_comm);
			}
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_for)) {
				subZhuanfaCount = sourceJson
						.getString(Protocol.ProtocolKey.KEY_for);
			}
			if (!sourceJson.isNull(Protocol.ProtocolKey.KEY_hit)) {
				subHitCount = sourceJson
						.getString(Protocol.ProtocolKey.KEY_hit);
			}
		}

		// String geo = json.getString("");
		// if (geo != null && !"".equals(geo) && !"null".equals(geo)) {
		// getGeoInfo(geo);
		// }
	}

	public static List<Status> getStatusList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<Status> statuslist = new ArrayList<Status>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Status st = new Status(jsonO);
			statuslist.add(st);
		}
		return statuslist;
	}

	// private void getGeoInfo(String geo) {
	// StringBuffer value = new StringBuffer();
	// for (char c : geo.toCharArray()) {
	// if (c > 45 && c < 58) {
	// value.append(c);
	// }
	// if (c == 44) {
	// if (value.length() > 0) {
	// latitude = Double.parseDouble(value.toString());
	// value.delete(0, value.length());
	// }
	// }
	// }
	// longitude = Double.parseDouble(value.toString());
	// }

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getWbID() {
		return wbID;
	}

	public void setWbID(String wbID) {
		this.wbID = wbID;
	}

	public String getWbName() {
		return wbName;
	}

	public void setWbName(String wbName) {
		this.wbName = wbName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUser(GameUser user) {
		this.user = user;
	}

	public Status(JSONObject json) throws JSONException {
		constructJson(json);
	}

	public Status(String str) throws JSONException {
		// StatusStream uses this constructor
		super();
		JSONObject json = new JSONObject(str);
		constructJson(json);
	}

	/**
	 * Return the created_at
	 * 
	 * @return created_at
	 * @since Weibo4J 1.1.0
	 */

	public Date getCreatedAt() {
		return this.createdAt;
	}

	private GameUser user = null;

	/**
	 * Return the user
	 * 
	 * @return the user
	 */
	public GameUser getUser() {
		return user;
	}

	private String roleID;

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public List<Focus> focusList = new ArrayList<Status.Focus>();

	public static class Focus implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8320536411909895111L;
		public String index;
		public String wbID;
		public String type;// 1@用户 2#话题
		public String fName;
	}

}