package com.zzl.zl_app.connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.corporate.Jobs.JobDetail;
import com.example.corporate.PlatformAPI;
import com.example.corporate.base.MyToast;

public class Community {

	private static Community weibo = null;
	private static Context mContext;

	private static Handler handler;

	public static void initHandler() {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				String cmsg = (String) msg.obj;
				MyToast.getToast().showToast(mContext, cmsg);
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

	/**
	 * 企业认证
	 * 
	 * @param accountName
	 * @param pwd
	 * @param companyId
	 * @param name
	 * @param phone
	 * @param addr
	 * @param intro
	 * @return
	 * @throws JSONException
	 */
	public boolean companyIdent(String accountName, String pwd, String name,
			String phone, String addr, String intro) throws JSONException {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Orgid,
				PlatformAPI.Orgid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Name,
				accountName));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Pwd, pwd));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_name, name));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_phone, phone));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_addr, addr));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_intro, intro));

		byte[] data = Connection.getDataSync(null, pairs,
				Protocol.Request_Url_Company_Ident);
		if (data != null) {
			JSONObject obj = getJSONObjectFromByte(data);
			if (obj != null) {
				String state = obj.getString(Protocol.ProtocolKey.KEY_Stat);
				String m = obj.getString(Protocol.ProtocolKey.KEY_Msg);
				if (state.equals("0")) {
					return true;
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = m;
					handler.sendMessage(msg);
				}
			}
		}
		return false;
	}

	/**
	 * 上传企业LOGO/营业执照
	 * 
	 * @param context
	 * @param accountName
	 * @param pwd
	 * @param type
	 * @param imgext
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public String companyImgOp(Context context, String accountName, String pwd,
			String type, Bitmap img) throws Exception {
		String resp = Utility.openUrl(context, PlatformAPI.imgUrl
				+ Protocol.Request_Url_Company_ImgOp + "?Orgid="
				+ PlatformAPI.Orgid + "&Name=" + accountName + "&Pwd=" + pwd
				+ "&Type=" + type + "&Imgext=jpg", "POST", img);
		Log.i("IO", resp);
		if (resp != null) {
			JSONObject obj = getJSONObjectFromString(resp);
			if (obj != null) {
				String state = obj.getString(Protocol.ProtocolKey.KEY_Stat);
				String m = obj.getString(Protocol.ProtocolKey.KEY_Msg);
				if (state.equals("0")) {
					if (!obj.isNull(Protocol.ProtocolKey.KEY_Url)) {
						String url = obj
								.getString(Protocol.ProtocolKey.KEY_Url);
						if (url != null && !url.equals("")) {
							return url;
						}
						// TODO
					}
					return null;
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = m;
					handler.sendMessage(msg);
				}
			}
		}
		return null;
	}

	/**
	 * 编辑/发布岗位信息
	 * 
	 * @param accountName
	 * @param pwd
	 * @param type
	 *            操作类型（1：添加 2：修改）
	 * @param jobCity
	 *            岗位所在城市
	 * @param rid
	 *            岗位id
	 * @param name
	 *            岗位名称
	 * @param people
	 *            招聘人数
	 * @param salary_min
	 *            薪水下限
	 * @param salary_max
	 *            薪水上限
	 * @param contact_name
	 *            联系人
	 * @param tel
	 *            联系电话
	 * @param Class_ot
	 *            工作类别
	 * @param jobDescription
	 *            岗位描述
	 * @param description
	 *            岗位要求
	 * @param outtime
	 *            有效期限
	 * @param tag
	 *            标签
	 * @param address
	 *            工作地址
	 * @return
	 * @throws JSONException
	 */
	public boolean recruitOp(String accountName, String pwd, String type,
			String jobCity, String rid, String name, String people,
			String salary_min, String salary_max, String contact_name,
			String tel, String Class_ot, String jobDescription,
			String description, String outtime, String tag, String address)
			throws JSONException {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Orgid,
				PlatformAPI.Orgid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Name,
				accountName));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Pwd, pwd));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_type, type));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_job_city,
				jobCity));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_rid, rid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_name, name));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_people,
				people));

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_salary_min,
				salary_min));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_salary_max,
				salary_max));
		pairs.add(new BasicNameValuePair(
				Protocol.ProtocolKey.KEY_contacts_name, contact_name));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_tel, tel));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_class_ot,
				Class_ot));
		pairs.add(new BasicNameValuePair(
				Protocol.ProtocolKey.KEY_jobdescription, jobDescription));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_description,
				description));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_outtime,
				outtime));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_tag, tag));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_address,
				address));

		byte[] data = Connection.getDataSync(null, pairs,
				Protocol.Request_Url_Recruit_Op);
		if (data != null) {
			JSONObject obj = getJSONObjectFromByte(data);
			if (obj != null) {
				String state = obj.getString(Protocol.ProtocolKey.KEY_Stat);
				String m = obj.getString(Protocol.ProtocolKey.KEY_Msg);
				if (state.equals("0")) {
					return true;
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = m;
					handler.sendMessage(msg);
				}
			}
		}
		return false;
	}

	/**
	 * 删除岗位
	 * 
	 * @param accountName
	 * @param pwd
	 * @param city
	 * @param rid
	 * @return
	 * @throws JSONException
	 */
	public boolean recruitDelete(String accountName, String pwd, String city,
			String rid) throws JSONException {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Orgid,
				PlatformAPI.Orgid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Name,
				accountName));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Pwd, pwd));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_city, city));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_rid, rid));

		byte[] data = Connection.getDataSync(null, pairs,
				Protocol.Request_Url_Recruit_Delete);
		if (data != null) {
			JSONObject obj = getJSONObjectFromByte(data);
			if (obj != null) {
				String state = obj.getString(Protocol.ProtocolKey.KEY_Stat);
				String m = obj.getString(Protocol.ProtocolKey.KEY_Msg);
				if (state.equals("0")) {
					return true;
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = m;
					handler.sendMessage(msg);
				}
			}
		}
		return false;
	}

	/**
	 * 查询岗位详情信息请求
	 * 
	 * @param accountName
	 * @param pwd
	 * @param city
	 * @param rid
	 * @return
	 * @throws JSONException
	 */
	public JobDetail recruitInfo(String accountName, String pwd, String city,
			String rid) throws JSONException {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Orgid,
				PlatformAPI.Orgid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Name,
				accountName));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Pwd, pwd));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_city, city));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_rid, rid));

		byte[] data = Connection.getDataSync(null, pairs,
				Protocol.Request_Url_Recruit_Delete);
		if (data != null) {
			JSONObject obj = getJSONObjectFromByte(data);
			if (obj != null) {
				String state = obj.getString(Protocol.ProtocolKey.KEY_Stat);
				if (state.equals("0")) {
					return new JobDetail(obj);
				} else {
					if (!obj.isNull(Protocol.ProtocolKey.KEY_Msg)) {
						String m = obj.getString(Protocol.ProtocolKey.KEY_Msg);
						Message msg = handler.obtainMessage();
						msg.obj = m;
						handler.sendMessage(msg);
					}
				}
			}
		}
		return null;
	}

	public List<JobDetail> recruitInfoList(String accountName, String pwd)
			throws JSONException {
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Orgid,
				PlatformAPI.Orgid));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Name,
				accountName));
		pairs.add(new BasicNameValuePair(Protocol.ProtocolKey.KEY_Pwd, pwd));

		byte[] data = Connection.getDataSync(null, pairs,
				Protocol.Request_Url_Recruit_InfoList);
		if (data != null) {
			JSONArray array = getJSONArrayFromByte(data);
			return JobDetail.getList(array);
		}
		return null;
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
