package cn.zrong.entity;

import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;

import cn.zrong.connection.Protocol;

public class GameType {

	public String iconUrl;
	public String index;
	public String name;
	public String exp;
	public int size;

	public GameType() {
	}

	public GameType(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX)) {
			index = json.getString(Protocol.ProtocolKey.KEY_INDEX);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_name)) {
			name = json.getString(Protocol.ProtocolKey.KEY_name);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_exp)) {
			exp = json.getString(Protocol.ProtocolKey.KEY_exp);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_size)) {
			size = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_size));
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_img)) {
			iconUrl = json.getString(Protocol.ProtocolKey.KEY_img);
		}
	}

	public static List<GameType> getGameTypeList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<GameType> gameTypeList = new ArrayList<GameType>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			GameType gi = new GameType(jsonO);
			gameTypeList.add(gi);
		}
		return gameTypeList;
	}
}
