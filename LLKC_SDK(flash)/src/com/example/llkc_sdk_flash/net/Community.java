package com.example.llkc_sdk_flash.net;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.example.llkc_sdk_flash.beans.RankItem;
import com.example.llkc_sdk_flash.task.Result;
import com.zzl.zl_app.utils.Tools;

public class Community {

	private static Community comm = null;

	public static Community getInstance(Context context) {
		mContext = context;
		if (comm == null) {
			comm = new Community();
		}
		return comm;
	}

	private static Context mContext;

	private boolean checkNetWorkStatus(Context context) {
		if (context == null)
			return false;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();

		if (netinfo != null) {
			return netinfo.isAvailable();
		}
		return false;
	}

	/**
	 * 
	 * @param Optype
	 * @return List<RankItem>
	 * @throws JSONException
	 * @throws NoNetExpection
	 * @throws WSError
	 */
	public List<RankItem> getRankItemList(String gId, String account, String pwd)
			throws JSONException, NoNetExpection {
		if (!checkNetWorkStatus(mContext))
			throw new NoNetExpection("no net");
		String url = PlatformAPI.BaseUrl + "Ranking_Score_Req.jsp?Orgid="
				+ PlatformAPI.Orgid + "&Name=" + account + "&Pwd=" + pwd
				+ "&GameId=" + gId;
		String reponse = ConnectionCaller.doGet(url);
		JSONArray array = getJSONArrayFromString(reponse);
		if (array != null) {
			return RankItem.getList(array);
		}
		return null;
	}

	/**
	 * 提交游戏成绩
	 * 
	 * @param gId
	 * @param account
	 * @param pwd
	 * @param score
	 * @throws WSError
	 * @return Result
	 * @throws NoNetExpection
	 */
	public Result postGameScore(String gId, String account, String pwd,
			int score) throws JSONException, NoNetExpection {
		if (!checkNetWorkStatus(mContext))
			throw new NoNetExpection("no net");
		String request = PlatformAPI.BaseUrl + "Score_Update_Req.jsp?Orgid="
				+ PlatformAPI.Orgid + "&Name=" + account + "&Pwd=" + pwd
				+ "&GameId=" + gId + "&Score=" + score;
		String reponse = ConnectionCaller.doGet(request);
		if (reponse != null) {
			JSONObject obj = getJSONObjectFromString(reponse);
			if (obj != null) {
				return new Result(obj);
			}
		}
		return new Result("-1", "提交失败");

	}

	/**
	 * 提交消耗易打工积分
	 * 
	 * @param gId
	 * @param account
	 * @param pwd
	 * @param Optype
	 *            操作类型 （1消耗积分，2追加积分）
	 * @param integral
	 * @return
	 * @throws JSONException
	 * @throws NoNetExpection
	 * @throws WSError
	 */
	public Result postUsedIntegral(String gId, String account, String pwd,
			String Optype, int integral) throws JSONException, NoNetExpection {
		if (!checkNetWorkStatus(mContext))
			throw new NoNetExpection("no net");
		String request = PlatformAPI.BaseUrl + "Integral_Update_Req.jsp?Orgid="
				+ PlatformAPI.Orgid + "&Name=" + account + "&Pwd=" + pwd
				+ "&GameId" + gId + "&Optype=" + Optype + "&Integral="
				+ integral;
		String reponse = ConnectionCaller.doGet(request);
		if (reponse != null) {
			JSONObject obj = getJSONObjectFromString(reponse);
			if (obj != null) {
				return new Result(obj);
			}
		}
		return new Result("-1", "提交失败");
	}

	public JSONArray getJSONArrayFromByte(byte[] data) throws JSONException {
		if (data == null) {
			return null;
		}
		String json = new String(data);
		json = json.trim();

		if (!json.substring(0, 1).equals("{")) {
			return null;
		}
		Tools.log("IO", "Result : " + json);
		JSONTokener jsonParser = new JSONTokener(json);
		Object obj = null;
		do {
			obj = jsonParser.nextValue();
		} while (obj instanceof String && jsonParser.more());
		if (obj instanceof JSONObject) {
			JSONObject jsonObj = (JSONObject) obj;
			// JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
			if (!jsonObj.isNull("List"))
				return jsonObj.getJSONArray("List");
		}
		return null;
	}

	public JSONArray getJSONArrayFromString(String resp) throws JSONException {
		if (resp == null)
			return null;
		resp = resp.trim();

		if (!resp.substring(0, 1).equals("{")) {
			return null;
		}
		Tools.log("IO", "Result : " + resp);
		JSONTokener jsonParser = new JSONTokener(resp);
		Object obj = null;
		do {
			obj = jsonParser.nextValue();
		} while (obj instanceof String && jsonParser.more());
		if (obj instanceof JSONObject) {
			JSONObject jsonObj = (JSONObject) obj;
			// JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
			if (!jsonObj.isNull("List"))
				return jsonObj.getJSONArray("List");
		}
		return null;
	}

	public JSONObject getJSONObjectFromString(String resp) throws JSONException {
		if (resp == null)
			return null;
		resp = resp.trim();

		if (!resp.substring(0, 1).equals("{")) {
			return null;
		}
		Tools.log("IO", "Result : " + resp);
		JSONTokener jsonParser = new JSONTokener(resp);

		Object obj = null;
		do {
			obj = jsonParser.nextValue();
		} while (obj instanceof String && jsonParser.more());
		if (obj instanceof JSONObject) {
			JSONObject jsonObj = (JSONObject) obj;
			return jsonObj;
		}
		return null;
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
			e.printStackTrace();
		}
		return null;
	}

}
