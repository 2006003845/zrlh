package com.zzl.zl_app.connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Community {

	private static Community weibo = null;
	private static Context mContext;

	private static Handler handler;

	public static void initHandler() {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				String cmsg = (String) msg.obj;
//				MyToast.getToast().showToast(mContext, cmsg);
				// BaseActivity.showMsgDialog(msg.what == 10,
				// msg.obj.toString(),
				// mContext);
			};
		};
	}

	public static Community getInstance(Context context) {
		mContext = context;
		if (weibo == null) {
			weibo = new Community();
		}
		if (checkNetWorkStatus(context)) {
			return weibo;
		} else {
			handler.sendEmptyMessage(1);
		}
		return weibo;
	}

	private static boolean checkNetWorkStatus(Context context) {
		boolean result;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}



	public JSONArray getJSONArrayFromByte(byte[] data) throws JSONException {
		if (data == null) {
			return null;
		}
		String json = new String(data);
		json = json.trim();
		Log.v("IO", json);
		if (!json.substring(0, 1).equals("{")) {
			return null;
		}
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
		if (!jsonObj.isNull(Protocol.ProtocolKey.KEY_List))
			return jsonObj.getJSONArray(Protocol.ProtocolKey.KEY_List);
		return null;
	}

	private JSONObject getJSONObjectFromByte(byte[] data) throws JSONException {
		if (data == null) {
			return null;
		}
		String json = new String(data);
		json = json.trim();
		Log.v("IO", json);
		if (!json.substring(0, 1).equals("{")) {
			return null;
		}

		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
		if (!jsonObj.isNull("Stat")) {
			String stat = jsonObj.getString("Stat");
			if (stat.equals("-99")) {
				Message msg = handler.obtainMessage();
				msg.obj = "连接超时";
				handler.sendMessage(msg);
				return null;
			}
		}
		return jsonObj;
	}

	public JSONObject getJSONObjectFromString(String resp) throws JSONException {
		resp = resp.trim();
		Log.v("IO", resp);
		if (!resp.substring(0, 1).equals("{")) {
			return null;
		}
		JSONTokener jsonParser = new JSONTokener(resp);
		JSONObject obj = (JSONObject) jsonParser.nextValue();
		if (!obj.isNull("Stat")) {
			String stat = obj.getString("Stat");
			if (stat.equals("-99")) {
				Message msg = handler.obtainMessage();
				msg.obj = "连接超时";
				handler.sendMessage(msg);
			}
		}
		return obj;
	}

	private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();

	public static Date parseDate(String str, String format) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			formatMap.put(format, sdf);
		}
		try {
			synchronized (sdf) {
				// SimpleDateFormat is not thread safe
				return sdf.parse(str);
			}
		} catch (ParseException e) {
			Log.e("error", e.getMessage());
		}
		return null;
	}

}
