package cn.zrong.weibobinding;

import java.io.StringReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.wifi.WifiConfiguration.Protocol;
import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class JsonUtils {
	public static BindingAccount userQQ = null;
	public static BindingAccount userSina = null;
	public String[] strQQInfo = { "birth_day", "birth_month", "birth_year",
			"city_code", "comp", "country_code", "edu", "email", "fansnum",
			"favnum", "head", "homecity_code", "homecountry_code", "homepage",
			"homeprovince_code", "hometown_code", "idolnum", "industry_code",
			"introduction", "isent", "ismyblack", "ismyfans", "ismyidol",
			"isrealname", "isvip", "location", "mutual_fans_num", "name",
			"nick", "openid", "province_code", "regtime", "send_private_flag",
			"sex", "tag", "tweetinfo", "tweetnum", "verifyinfo" };

	public void parseJsonOfUserQQ(String jsonData) {
		if (userQQ == null) {
			userQQ = new BindingAccount();
		}
		try {
			userQQ.name = parseOfjsonRead(jsonData);
			// parseOfjsonReadComplex(jsonData);
		} catch (Exception e) {
		}
		// return user;
	}

	public String parseJsonOfSinaUserUID(String jsonData) {
		String result = "";
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				String tagName = reader.nextName();
				if (tagName.equals("uid")) {
					result = reader.nextString();
				}
			}
			reader.endObject();
		} catch (Exception e) {
		}
		return result;
	}

	public ShareWeiboResult parseJsonSinaUploadPicResult(String jsonData) {
		ShareWeiboResult rlt = new ShareWeiboResult();
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				String tagName = reader.nextName();
				if (tagName.equals("error_code") || tagName.equals("error")
						|| tagName.equals("request")) {
					if (tagName.equals("error_code")) {
						rlt.error_code = reader.nextString();
					}
					if (tagName.equals("request")) {
						rlt.request = reader.nextString();
					}
					// String tagNameValue = reader.nextString();
					// if(tagNameValue.equals("20006")){
					// rlt = 2;//图片太大
					// }else if(tagNameValue.equals("20019")){//提交相同的信息
					// rlt = 0;
					// }else{
					// rlt = 1;
					// }

				} else {
				}
			}
			reader.endObject();
		} catch (Exception e) {

		}
		return rlt;
	}

	private String[] strSinaStatus = { "created_at", "id", "text", "source",
			"favorited", "truncated", "in_reply_to_status_id",
			"in_reply_to_user_id", "in_reply_to_screen_name", "geo", "mid",
			"reposts_count", "comments_count", "annotations", "user", };

	private String[] strSinaUser = { "id", "screen_name", "name", "province",
			"city", "location", "description", "url", "profile_image_url",
			"domain", "gender", "followers_count", "friends_count",
			"statuses_count", "favourites_count", "created_at", "following",
			"allow_all_act_msg", "remark", "geo_enabled", "verified", "status",
			"allow_all_comment", "avatar_large", "verified_reason",
			"follow_me", "online_status", "bi_followers_count" };

	public void parseJsonOfSina(String jsonData) {
		if (userSina == null) {
			userSina = new BindingAccount();
		}
		// parseJson_public_timeline_Sina(jsonData);
		parseJson_usersShow_Sina(jsonData);
	}

	public void parseJson_usersShow_Sina(String jsonData) {
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				for (int i = 0; i < strSinaUser.length; i++) {
					String tagName = reader.nextName();
					if (tagName.endsWith("screen_name")) {
						userSina.screen_name = reader.nextString();
						break;
					} else {
						reader.skipValue();
					}
				}
				/** 以下方法也可以，但是担心陷入死循环 */
				// while(true){
				// String tagName = reader.nextName();
				// if(tagName.endsWith("screen_name")){
				// userSina.screen_name = reader.nextString();
				// break;
				// }else{
				// reader.skipValue();
				// }
				// }
			}
			reader.endObject();
		} catch (Exception e) {

		}
	}

	private void parseJson_public_timeline_Sina(String jsonData) {
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				String tagName = reader.nextName();
				if (tagName.equals("statuses")) {
					parseStatusesOfSina(reader);
				} else if (tagName.equals("previous_cursor")) {
					reader.skipValue();
				} else if (tagName.equals("next_cursor")) {
					reader.skipValue();
				} else if (tagName.equals("total_number")) {
					reader.skipValue();
				}
			}
			reader.endObject();
		} catch (Exception e) {
		}
	}

	private void parseStatusesOfSina(JsonReader reader) {
		try {
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
			} else {
				reader.beginArray();
				while (reader.hasNext()) {
					reader.beginObject();
					for (int i = 0; i < strSinaUser.length; i++) {
						String tagName = reader.nextName();
						if (tagName.equals("user")) {
							parseUserOfSina(reader);
							break;
						} else {
							reader.skipValue();
						}
					}
					reader.endObject();
				}
				reader.endArray();
			}
		} catch (Exception e) {
		}
	}

	private void parseUserOfSina(JsonReader reader) {
		try {
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
			} else {
				reader.beginObject();
				while (reader.hasNext()) {
					for (int i = 0; i < strSinaUser.length; i++) {
						String tagName = reader.nextName();
						if (tagName.equals("id")) {
							userSina.ID = reader.nextInt();
						} else if (tagName.equals("screen_name")) {
							userSina.screen_name = reader.nextString();
						} else if (tagName.equals("name")) {
							userSina.name = reader.nextString();
						} else {
							reader.skipValue();
						}
					}

				}
				reader.endObject();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 本方法只解析想取的字符串；
	 * 
	 * @param jsonData
	 * @return
	 */
	private String parseOfjsonRead(String jsonData) {
		String strUserName = "";
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				String labelName = reader.nextName();
				Log.i("ftftft", "labelName=" + labelName);
				if (labelName.equals("data")) {
					reader.beginObject();
					while (reader.hasNext()) {
						for (int i = 0; i < strQQInfo.length; i++) {
							String tagName = reader.nextName();
							if (!tagName.equals("nick")) {
								reader.skipValue();
							} else {
								strUserName = reader.nextString();
								break;
							}
						}
					}
					reader.endObject();
				}
				reader.endArray();
			}
		} catch (Exception e) {
		}
		return strUserName;
	}

	public static ShareWeiboResult parseJsonOfqqSendReturn(String jsonData) {
		String qqSendReturn[] = { "data", "errcode", "imgurl", "msg", "ret" };
		ShareWeiboResult result = new ShareWeiboResult();
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginObject();
			while (reader.hasNext()) {
				for (int i = 0; i < qqSendReturn.length; i++) {
					String tagName = reader.nextName();
					if (tagName.endsWith("ret")) {
						result.ret = reader.nextInt();
					} else if (tagName.endsWith("errcode")) {
						result.errcode = reader.nextInt();
					} else {
						reader.skipValue();
					}
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	// private void parseOfjsonReadComplex(String jsonData){
	// try{
	// JsonReader reader = new JsonReader(new StringReader(jsonData));
	// reader.beginObject();
	// while(reader.hasNext()){
	// String labelName = reader.nextName();
	// Log.i("ftftft", "labelName="+labelName);
	// if(labelName.equals("data")){
	// reader.beginObject();
	// while(reader.hasNext()){
	// String tagName = reader.nextName();
	// Log.i("ftftft", "tagName="+tagName);
	// if(tagName.equals(strQQInfo[0])){
	// userQQ.birth_day = reader.nextInt();
	// System.out.println("birth_day--->" + userQQ.birth_day);
	// }else if(tagName.equals(strQQInfo[1])){
	// userQQ.birth_month = reader.nextInt();
	// System.out.println("birth_month--->" + userQQ.birth_month);
	// }else if(tagName.equals(strQQInfo[2])){
	// userQQ.birth_year = reader.nextInt();
	// System.out.println("birth_year--->" + userQQ.birth_year);
	// }else if(tagName.equals(strQQInfo[3])){
	// userQQ.city_code = reader.nextString();
	// System.out.println("city_code--->" + userQQ.city_code);
	// }else if(tagName.equals(strQQInfo[4])){
	// compDataRead(reader);
	// }else if(tagName.equals(strQQInfo[5])){
	// userQQ.country_code = reader.nextString();
	// System.out.println("country_code--->" + userQQ.country_code);
	// }else if(tagName.equals(strQQInfo[0])){
	// userQQ.birth_day = reader.nextInt();
	// System.out.println("birth_day--->" + userQQ.birth_day);
	// }else if(tagName.equals(strQQInfo[6])){
	// eduDataRead(reader);
	// }else if(tagName.equals(strQQInfo[7])){
	// userQQ.email = reader.nextString();
	// System.out.println("email--->" + userQQ.email);
	// }else if(tagName.equals(strQQInfo[8])){
	// userQQ.fansnum = reader.nextInt();
	// System.out.println("fansnum--->" + userQQ.fansnum);
	// }else if(tagName.equals(strQQInfo[9])){
	// userQQ.favnum = reader.nextInt();
	// System.out.println("favnum--->" + userQQ.favnum);
	// }else if(tagName.equals(strQQInfo[10])){
	// userQQ.head = reader.nextString();
	// System.out.println("head--->" + userQQ.head);
	// }else if(tagName.equals(strQQInfo[11])){
	// userQQ.homecity_code = reader.nextString();
	// System.out.println("homecity_code--->" + userQQ.homecity_code);
	// }else if(tagName.equals(strQQInfo[12])){
	// userQQ.homecountry_code = reader.nextString();
	// System.out.println("homecountry_code--->" + userQQ.homecountry_code);
	// }else if(tagName.equals(strQQInfo[13])){
	// userQQ.homepage = reader.nextString();
	// System.out.println("homepage--->" + userQQ.homepage);
	// }else if(tagName.equals(strQQInfo[14])){
	// userQQ.homeprovince_code = reader.nextString();
	// System.out.println("homeprovince_code--->" + userQQ.homeprovince_code);
	// }else if(tagName.equals(strQQInfo[15])){
	// userQQ.hometown_code = reader.nextString();
	// System.out.println("hometown_code--->" + userQQ.hometown_code);
	// }else if(tagName.equals(strQQInfo[16])){
	// userQQ.idolnum = reader.nextInt();
	// System.out.println("idolnum--->" + userQQ.idolnum);
	// }else if(tagName.equals(strQQInfo[17])){
	// userQQ.industry_code = reader.nextInt();
	// System.out.println("industry_code--->" + userQQ.industry_code);
	// }else if(tagName.equals(strQQInfo[18])){
	// userQQ.introduction = reader.nextString();
	// System.out.println("introduction--->" + userQQ.introduction);
	// }else if(tagName.equals(strQQInfo[19])){
	// userQQ.isent = reader.nextInt();
	// System.out.println("isent--->" + userQQ.isent);
	// }else if(tagName.equals(strQQInfo[20])){
	// userQQ.ismyblack = reader.nextInt();
	// System.out.println("ismyblack--->" + userQQ.ismyblack);
	// }else if(tagName.equals(strQQInfo[21])){
	// userQQ.ismyfans = reader.nextInt();
	// System.out.println("ismyfans--->" + userQQ.ismyfans);
	// }else if(tagName.equals(strQQInfo[22])){
	// userQQ.ismyidol = reader.nextInt();
	// System.out.println("ismyidol--->" + userQQ.ismyidol);
	// }else if(tagName.equals(strQQInfo[23])){
	// userQQ.isrealname = reader.nextInt();
	// System.out.println("isrealname--->" + userQQ.isrealname);
	// }else if(tagName.equals(strQQInfo[24])){
	// userQQ.isvip = reader.nextInt();
	// System.out.println("isvip--->" + userQQ.isvip);
	// }else if(tagName.equals(strQQInfo[25])){
	// userQQ.location = reader.nextString();
	// System.out.println("location--->" + userQQ.location);
	// }else if(tagName.equals(strQQInfo[26])){
	// userQQ.mutual_fans_num = reader.nextInt();
	// System.out.println("mutual_fans_num--->" + userQQ.mutual_fans_num);
	// }else if(tagName.equals(strQQInfo[27])){
	// userQQ.name = reader.nextString();
	// System.out.println("name--->" + userQQ.name);
	// }else if(tagName.equals(strQQInfo[28])){
	// userQQ.nick = reader.nextString();
	// System.out.println("nick--->" + userQQ.nick);
	// }else if(tagName.equals(strQQInfo[29])){
	// userQQ.openid = reader.nextString();
	// System.out.println("openid--->" + userQQ.openid);
	// }else if(tagName.equals(strQQInfo[30])){
	// userQQ.province_code = reader.nextString();
	// System.out.println("province_code--->" + userQQ.province_code);
	// }else if(tagName.equals(strQQInfo[31])){
	// userQQ.regtime = reader.nextInt();
	// System.out.println("regtime--->" + userQQ.regtime);
	// }else if(tagName.equals(strQQInfo[32])){
	// userQQ.send_private_flag = reader.nextInt();
	// System.out.println("send_private_flag--->" + userQQ.send_private_flag);
	// }else if(tagName.equals(strQQInfo[33])){
	// userQQ.sex = reader.nextInt();
	// System.out.println("sex--->" + userQQ.sex);
	// }else if(tagName.equals(strQQInfo[34])){
	// tagDataRead(reader);
	// }else if(tagName.equals(strQQInfo[35])){
	// // userInfo.tweetinfo = reader.nextInt();
	// // System.out.println("tweetinfo--->" + userInfo.tweetinfo);
	// break;
	// }else if(tagName.equals(strQQInfo[36])){
	// userQQ.tweetnum = reader.nextInt();
	// System.out.println("tweetnum--->" + userQQ.tweetnum);
	// }else if(tagName.equals(strQQInfo[36])){
	// userQQ.verifyinfo = reader.nextString();
	// System.out.println("verifyinfo--->" + userQQ.verifyinfo);
	// }
	// }
	// reader.endObject();
	// }
	// if(labelName.equals("ret")){
	// int mResult = reader.nextInt();
	// }
	// }
	// reader.endObject();
	// }catch(Exception e){}
	// }

	

	
	public static QQResult parseQQResultJson(String jsonData) {
		JSONTokener jsonParser = new JSONTokener(jsonData);

		try {
			JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
			if (jsonObj != null) {
				return new QQResult(jsonObj);
			} else {
				return null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SinaResult parseSinaResultJson(String jsonData) {
		JSONTokener jsonParser = new JSONTokener(jsonData);

		try {
			JSONObject jsonObj = (JSONObject) jsonParser.nextValue();
			if (jsonObj != null) {
				return new SinaResult(jsonObj);
			} else {
				return null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 此方法未调通，有待验证 */
	private void parseJsonOfObject(String jsonData) {
		try {
			// 如果需要解析JSON数据，首先要生成一个JsonReader对象
			// ft_6.6解法1（不成功）
			// Log.i("ftftft", "jsondata="+jsonData);
			// JSONObject job = new JSONObject(jsonData).getJSONObject("data");
			// userInfo.birth_day = job.getInt(strQQInfo[0]);
			// Log.i("ftftft", "bir="+userInfo.birth_day);
			// userInfo.birth_month = job.getInt(strQQInfo[1]);
			// Log.i("ftftft", "birM="+userInfo.birth_month);
			// userInfo.birth_year = job.getInt(strQQInfo[2]);
			// Log.i("ftftft", "birth_year="+userInfo.birth_year);
			// userInfo.city_code = job.getString(strQQInfo[3]);
			// userInfo.comp = job.getJSONObject(strQQInfo[4]);
			// userInfo.country_code = job.getString(strQQInfo[5]);
			// userInfo.edu = job.getJSONObject(strQQInfo[6]);
			// userInfo.email = job.getString(strQQInfo[7]);
			// userInfo.fansnum = job.getInt(strQQInfo[8]);
			// userInfo.favnum = job.getInt(strQQInfo[9]);
			// userInfo.head = job.getString(strQQInfo[10]);
			// userInfo.homecity_code = job.getString(strQQInfo[11]);
			// userInfo.homecountry_code = job.getString(strQQInfo[12]);
			// userInfo.homepage = job.getString(strQQInfo[13]);
			// userInfo.homeprovince_code = job.getString(strQQInfo[14]);
			// userInfo.hometown_code = job.getString(strQQInfo[15]);
			// userInfo.idolnum = job.getInt(strQQInfo[16]);
			// userInfo.industry_code = job.getInt(strQQInfo[17]);
			// userInfo.introduction = job.getString(strQQInfo[18]);
			// userInfo.isent = job.getInt(strQQInfo[19]);
			// userInfo.ismyblack = job.getInt(strQQInfo[20]);
			// userInfo.ismyfans = job.getInt(strQQInfo[21]);
			// userInfo.ismyidol = job.getInt(strQQInfo[22]);
			// userInfo.isrealname = job.getInt(strQQInfo[23]);
			// userInfo.isvip = job.getInt(strQQInfo[24]);
			// userInfo.location = job.getString(strQQInfo[25]);
			// userInfo.mutual_fans_num = job.getInt(strQQInfo[26]);
			// userInfo.name = job.getString(strQQInfo[27]);
			// userInfo.nick = job.getString(strQQInfo[28]);
			// userInfo.openid = job.getString(strQQInfo[29]);
			// userInfo.province_code = job.getString(strQQInfo[30]);
			// userInfo.regtime = job.getInt(strQQInfo[31]);
			// userInfo.send_private_flag = job.getInt(strQQInfo[32]);
			// userInfo.sex = job.getInt(strQQInfo[33]);
			// userInfo.tag = job.getJSONObject(strQQInfo[34]);
			// userInfo.tweetinfo = job.getJSONObject(strQQInfo[35]);
			// userInfo.tweetnum = job.getInt(strQQInfo[36]);
			// userInfo.verifyinfo = job.getString(strQQInfo[37]);
			// ft_6.6解法1（不成功）
		} catch (Exception e) {
		}
	}
}
