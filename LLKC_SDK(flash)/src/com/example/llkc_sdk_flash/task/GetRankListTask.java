package com.example.llkc_sdk_flash.task;

import java.util.List;

import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.llkc_sdk_flash.beans.RankItem;
import com.example.llkc_sdk_flash.net.Community;
import com.example.llkc_sdk_flash.net.NoNetExpection;

public class GetRankListTask extends AsyncTask<Object, Integer, List<RankItem>> {

	private RankListCallBack resultCallback;

	public void commit() {
		this.execute();
	}

	/**
	 * 游戏ID ，账户名 ，密码， 消耗积分 ， 游戏成就
	 */
	private String gId;
	private String account;
	private String pwd;
	private Context context;

	private Result result;

	public GetRankListTask(Context context, String gId, String account,
			String pwd, RankListCallBack resultCallback) {
		super();
		this.context = context;
		this.resultCallback = resultCallback;
		this.gId = gId;
		this.account = account;
		this.pwd = pwd;
	}

	@Override
	protected List<RankItem> doInBackground(Object... params) {
		try {
			List<RankItem> list = Community.getInstance(context)
					.getRankItemList(gId, account, pwd);
			if (list == null)
				result = new Result("-1", "");
			else
				result = new Result("0", "");
			return list;
		} catch (NoNetExpection e) {
			Log.e("error", "NoNetExpection : no net");
			result = new Result(Result.STAT_NONET, "no net");
		} catch (JSONException e) {
			Log.e("error", "JSONException : postUsedIntegral");
			result = new Result(Result.STAT_JSONFORMFALSE, "data form false");
		} catch (Exception e) {
			result = new Result("-1", "");
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<RankItem> list) {
		resultCallback.onResult(result, list);
		super.onPostExecute(list);
	}

}
