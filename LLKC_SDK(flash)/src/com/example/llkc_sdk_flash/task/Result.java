package com.example.llkc_sdk_flash.task;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author ZZL
 * 
 */
public class Result {

	/**
	 * stat : 0 成功
	 */
	public String stat;
	public String msg;
	

	public static final String STAT_NONET = "-3";
	public static final String STAT_JSONFORMFALSE = "-2";
	public static final String STAT_COMMITFALSE = "-1";

	public Result(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull("Stat"))
			stat = jsonO.getString("Stat");
		if (!jsonO.isNull("Msg"))
			msg = jsonO.getString("Msg");
	}

	public Result() {
	}

	public Result(String stat, String msg) {
		super();
		this.stat = stat;
		this.msg = msg;
	}

}
