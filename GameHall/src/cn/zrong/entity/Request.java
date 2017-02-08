package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import cn.zrong.connection.Protocol;

public class Request extends Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8684252415563096041L;

	public String index;
	public String id;
	public String name;
	public String head;
	public String hdType;
	public String msg;
	public int requsest_type;
	public int type;

	public static final int REQUEST_TYPE_FRIEND = 1;
	public static final int REQUEST_TYPE_GROUP = 2;

	public Request() {

	}

	public Request(JSONObject json) throws JSONException {
		type = 2;
		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX))
			index = json.getString(Protocol.ProtocolKey.KEY_INDEX);

		if (!json.isNull(Protocol.ProtocolKey.KEY_wbID))
			id = json.getString(Protocol.ProtocolKey.KEY_wbID);
		if (!json.isNull(Protocol.ProtocolKey.KEY_id))
			id = json.getString(Protocol.ProtocolKey.KEY_id);

		if (!json.isNull(Protocol.ProtocolKey.KEY_wbName))
			name = json.getString(Protocol.ProtocolKey.KEY_wbName);
		if (!json.isNull(Protocol.ProtocolKey.KEY_Name))
			name = json.getString(Protocol.ProtocolKey.KEY_Name);

		if (!json.isNull(Protocol.ProtocolKey.KEY_wbHead))
			head = json.getString(Protocol.ProtocolKey.KEY_wbHead);

		if (!json.isNull(Protocol.ProtocolKey.KEY_hdType))
			hdType = json.getString(Protocol.ProtocolKey.KEY_hdType);

		if (!json.isNull(Protocol.ProtocolKey.KEY_msg))
			msg = json.getString(Protocol.ProtocolKey.KEY_msg);
	}

	public static List<Request> getRequestList(JSONArray array, int type)
			throws JSONException {
		int size = array.length();
		List<Request> msgList = new ArrayList<Request>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Request msg = new Request(jsonO);
			msg.requsest_type = type;
			msgList.add(msg);
		}
		return msgList;
	}
}
