package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import cn.zrong.connection.Protocol;

public class Mail extends Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015598289849636208L;
	public String id;
	public String wbName;
	public String headUrl;
	public String cont;
	public int type;
	public String time;
	public int state;// 发送状态
	public static final int STATE_SENDING = 0;
	public static final int STATE_SEND_SUCCESS = 1;
	public static final int STATE_SEND_FAILED = 2;

	public Mail(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_wbID))
			id = json.getString(Protocol.ProtocolKey.KEY_wbID);

		if (!json.isNull(Protocol.ProtocolKey.KEY_type))
			type = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_type));

		if (!json.isNull(Protocol.ProtocolKey.KEY_wbName))
			wbName = json.getString(Protocol.ProtocolKey.KEY_wbName);

		if (!json.isNull(Protocol.ProtocolKey.KEY_wbHead))
			headUrl = json.getString(Protocol.ProtocolKey.KEY_wbHead);

		if (!json.isNull(Protocol.ProtocolKey.KEY_cont))
			cont = json.getString(Protocol.ProtocolKey.KEY_cont);

		if (!json.isNull(Protocol.ProtocolKey.KEY_time))
			time = json.getString(Protocol.ProtocolKey.KEY_time);

	}

	public Mail() {
	}
	
	public boolean isSelf;

	public static List<Mail> getMailList(JSONArray array) throws JSONException {
		int size = array.length();
		List<Mail> msgList = new ArrayList<Mail>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Mail msg = new Mail(jsonO);
			msgList.add(msg);
		}
		return msgList;
	}

}
