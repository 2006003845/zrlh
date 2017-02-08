package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.zrong.connection.Protocol;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class Friend extends RoleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -445626384983218040L;
	public int type;
	public int state;
	public String wbId;

	public int isFriend = -1;

	public static final int STATE_FRIEND_IS = 2;

	public static final int STATE_FRIEND_VERIFY = 1;

	public static final int STATE_FRIEND_Sending = 0;

	public static final int TYPE_FRIEND = 1;
	public static final int TYPE_BLACKLIST = 2;

	public static final int STATE_NO_ONLINE = 0;
	public static final int STATE_ONLINE = 1;

	public Friend() {
		super();
	}

	public Friend(JSONObject jsonO) throws JSONException {
		super(jsonO);
		if (!jsonO.isNull("type"))
			type = Integer.parseInt(jsonO.getString("type").trim());
		if (!jsonO.isNull("state"))
			state = Integer.parseInt(jsonO.getString("state").trim());
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_wbID))
			wbId = jsonO.getString(Protocol.ProtocolKey.KEY_wbID);
	}

	public static List<Friend> getFriendList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<Friend> users = new ArrayList<Friend>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Friend u = new Friend(jsonO);
			users.add(u);
		}
		return users;
	}

}
