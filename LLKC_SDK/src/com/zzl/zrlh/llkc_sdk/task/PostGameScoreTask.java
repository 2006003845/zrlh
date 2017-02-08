package com.zzl.zrlh.llkc_sdk.task;

import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.zzl.zrlh.llkc_sdk.net.Community;
import com.zzl.zrlh.llkc_sdk.net.NoNetExpection;

public class PostGameScoreTask extends AsyncTask<Object, Integer, Result> {

	private ResultCallBack resultCallback;

	public void commit() {
		this.execute();
	}

	/**
	 * 游戏ID ，账户名 ，密码， 游戏成就
	 */
	private String gId;
	private String account;
	private String pwd;
	private int score;
	private Context context;

	public PostGameScoreTask(Context context, String gId, String account,
			String pwd, int score, ResultCallBack resultCallback) {
		super();
		this.context = context;
		this.resultCallback = resultCallback;
		this.gId = gId;
		this.account = account;
		this.pwd = pwd;
		this.score = score;
	}

	@Override
	protected Result doInBackground(Object... params) {
		try {
			return Community.getInstance(context).postGameScore(gId, account,
					pwd, score);
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
