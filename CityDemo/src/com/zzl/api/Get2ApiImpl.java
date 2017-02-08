package com.zzl.api;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.zzl.city.City;

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
	public List<City> getCityList(int from, String fileName) throws WSError {
		String responseStr = doGet(from, fileName);
		return XMLParseUtil.readStringXmlToCityList(responseStr);
	}

}
