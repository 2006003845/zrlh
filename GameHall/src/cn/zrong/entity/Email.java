package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import cn.zrong.connection.Protocol;

public class Email extends Msg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015598289849636208L;
	public static final int GetType_MEG_ALL = 1;
	public static final int GetType_MEG_NeverRead = 2;
	public static final int GetType_MEG_HaveRead = 3;

	/**
	 * 普通
	 */
	public static final int TYPE_EMAIL_NORMAL = 1;
	/**
	 * 快递
	 */
	public static final int TYPE_EMAIL_EXPRESS = 2;
	/**
	 * 加密
	 */
	public static final int TYPE_EMAIL_ENCRYPT = 3;
	/**
	 * 未读
	 */
	public static final int STATE_EMAIL_NEVERREAD = 1;
	/**
	 * 已读
	 */
	public static final int STATE_EMAIL_HAVEREAD = 2;
	/**
	 * 垃圾
	 */
	public static final int STATE_EMAIL_RUBBISH = 3;

	public String index;
	public int type;
	public String sender;
	public String title;
	public String content;
	public String aff;
	public String pwd;
	public String exp;
	public String state;

	public Email(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX))
			index = json.getString(Protocol.ProtocolKey.KEY_INDEX);

		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX))
			type = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_type));

		if (!json.isNull(Protocol.ProtocolKey.KEY_sender))
			sender = json.getString(Protocol.ProtocolKey.KEY_sender);

		if (!json.isNull(Protocol.ProtocolKey.KEY_title))
			title = json.getString(Protocol.ProtocolKey.KEY_title);

		if (!json.isNull(Protocol.ProtocolKey.KEY_content))
			content = json.getString(Protocol.ProtocolKey.KEY_content);

		if (!json.isNull(Protocol.ProtocolKey.KEY_aff))
			aff = json.getString(Protocol.ProtocolKey.KEY_aff);

		if (!json.isNull(Protocol.ProtocolKey.KEY_Pwd))
			pwd = json.getString(Protocol.ProtocolKey.KEY_Pwd);

		if (!json.isNull(Protocol.ProtocolKey.KEY_exp))
			exp = json.getString(Protocol.ProtocolKey.KEY_exp);

		if (!json.isNull(Protocol.ProtocolKey.KEY_state))
			state = json.getString(Protocol.ProtocolKey.KEY_state);

	}

	public Email() {
	}

	public static List<Email> getMessageList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<Email> msgList = new ArrayList<Email>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			Email msg = new Email(jsonO);
			msgList.add(msg);
		}
		return msgList;
	}

}
