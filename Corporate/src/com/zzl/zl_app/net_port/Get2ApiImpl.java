package com.zzl.zl_app.net_port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

import com.example.corporate.City;
import com.example.corporate.Obj;
import com.example.corporate.Type;
import com.zzl.zl_app.connection.Protocol;

public class Get2ApiImpl implements IGet2Api {

	private Context context;

	public Get2ApiImpl(Context context) {
		this.context = context;

	}

	private String doGet(int from, String query) throws WSError {
		if (from == From_Assert)
			return ConnectionCaller.doGet(query, context);
		else if (from == From_Net)
			return ConnectionCaller.doGet(query);
		else
			return null;
	}

	public byte[] doGetImgRes(String urlStr) {
		return ConnectionCaller.doGetImgRes(urlStr);
	}

	public static byte[] getXMLRequest(String[] key, String[] value) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
			if (key != null) {
				for (int i = 0; i < key.length; i++) {
					buffer.append("<").append(key[i]).append(">")
							.append(value[i]).append("</").append(key[i])
							.append(">");
				}
			}
			buffer.append("</request>");
			Log.i("Log", buffer + "");
			String s = buffer.toString();
			buffer.delete(0, buffer.length());
			byte[] data = s.getBytes("UTF-8");
			return data;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<City> getCityList(int from, String fileName) throws WSError,
			JSONException {
		String responseStr = doGet(from, fileName);
		if (responseStr == null || responseStr.equals("")) {
			return null;
		}
		JSONArray array = getJSONArrayFromStr(responseStr);
		return City.getList(array);
	}

	@Override
	public ArrayList<HashMap<Obj, ArrayList<Obj>>> getTypeMapList(int from,
			String fileName) throws WSError, JSONException {
		String responseStr = doGet(from, fileName);
		if (responseStr == null || responseStr.equals("")) {
			return null;
		}
		JSONArray array = getJSONArrayFromStr(responseStr);
		ArrayList<HashMap<Obj, ArrayList<Obj>>> jobs = new ArrayList<HashMap<Obj, ArrayList<Obj>>>();
		for (int i = 0, len = array.length(); i < len; i++) {
			JSONObject obj = array.getJSONObject(i);
			if (obj != null) {
				Type p = new Type(obj);
				HashMap<Obj, ArrayList<Obj>> map = new HashMap<Obj, ArrayList<Obj>>();
				ArrayList<Obj> list = null;
				if (!obj.isNull("List")) {
					JSONArray array2 = obj.getJSONArray("List");
					list = Type.getList(array2);
				}
				map.put(p, list);
				jobs.add(map);
			}
		}
		return jobs;
	}

	public JSONArray getJSONArrayFromStr(String json) throws JSONException {
		json = json.trim();
		Log.v("IO", json);
		// if (!json.startsWith("{")) {
		// return null;
		// }
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
		if (!jsonObj.isNull(Protocol.ProtocolKey.KEY_List))
			return jsonObj.getJSONArray(Protocol.ProtocolKey.KEY_List);
		return null;
	}

}
