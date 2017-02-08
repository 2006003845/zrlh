package com.example.llkc_sdk_flash.task;

import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.llkc_sdk_flash.net.Community;
import com.example.llkc_sdk_flash.net.NoNetExpection;

public class PostUsedIntegralTask extends AsyncTask<Object, Integer, Result> {

	private ResultCallBack resultCallback;

	public void commit() {
		this.execute();
	}

	/**
	 * 游戏ID ，账户名 ，密码， 消耗积分
	 */
	private String gId;
	private String account;
	private String pwd;
	private int usedIntegral;
	private Context context;

	public PostUsedIntegralTask(Context context, String gId, String account,
			String pwd, int usedIntegral, ResultCallBack resultCallback) {
		super();
		this.context = context;
		this.resultCallback = resultCallback;
		this.gId = gId;
		this.account = account;
		this.pwd = pwd;
		this.usedIntegral = usedIntegral;

	}

	@Override
	protected Result doInBackground(Object... params) {
		try {
			return Community.getInstance(context).postUsedIntegral(gId,
					account, pwd, "1", usedIntegral);
		} catch (NoNetExpection e) {
			Log.e("error", "NoNetExpection : no net");
			return new Result(Result.STAT_NONET, "no net");
		} catch (JSONException e) {
			Log.e("error", "JSONException : postUsedIntegral");
			return new Result(Result.STAT_JSONFORMFALSE, "data form false");
		} catch (Exception e) {
			return new Result(Result.STAT_COMMITFALSE, "提交失败");
		}
	}

	@Override
	protected void onPostExecute(Result result) {
		resultCallback.onResult(result);
		super.onPostExecute(result);
	}

}
