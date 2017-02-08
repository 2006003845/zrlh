package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;

import cn.zrong.connection.Protocol;

public class City implements Serializable {
	private static final long serialVersionUID = 5583121902056391760L;
	public String index;
	public String name;

	public City() {
	}

	public City(String index) {
		this.index = index;
	}

	public City(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_INDEX))
			index = json.getString(Protocol.ProtocolKey.KEY_INDEX);
		if (!json.isNull(Protocol.ProtocolKey.KEY_name))
			name = json.getString(Protocol.ProtocolKey.KEY_name);
	}

	public static List<City> getCityList(JSONArray array) throws JSONException {
		int size = array.length();
		List<City> cityList = new ArrayList<City>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			City city = new City(jsonO);
			cityList.add(city);
		}
		return cityList;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
