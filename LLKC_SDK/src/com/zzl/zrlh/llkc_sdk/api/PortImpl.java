package com.zzl.zrlh.llkc_sdk.api;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.zzl.zrlh.llkc_sdk.beans.ShareData;
import com.zzl.zrlh.llkc_sdk.beans.UserInfo;
import com.zzl.zrlh.llkc_sdk.net.NoPlatformUserExpection;
import com.zzl.zrlh.llkc_sdk.task.GetRankListTask;
import com.zzl.zrlh.llkc_sdk.task.PostGameScoreTask;
import com.zzl.zrlh.llkc_sdk.task.PostUsedIntegralTask;
import com.zzl.zrlh.llkc_sdk.task.RankListCallBack;
import com.zzl.zrlh.llkc_sdk.task.ResultCallBack;

/**
 * 
 * @author ZZL
 * 
 */
public class PortImpl implements IPort {

	private static IPort iPort;

	private PortImpl() {
	}

	public static IPort getPort() {
		if (iPort == null)
			iPort = new PortImpl();
		return iPort;
	}

	@Override
	public UserInfo getUserInfo(Activity act) {
		return getUser(act);
	}

	@Override
	public void getGameRankList(Context context, String account, String pwd,
			String gId, RankListCallBack resultCallback)
			throws NoPlatformUserExpection {
		if (account == null || account.equals("") || pwd == null
				|| pwd.equals(""))
			throw new NoPlatformUserExpection("无易打工平台账户信息");
		else
			new GetRankListTask(context, gId, account, pwd, resultCallback)
					.commit();
	}

	@Override
	public void postGameScoreData(Context context, String account, String pwd,
			String gId, int score, ResultCallBack resultCallback)
			throws NoPlatformUserExpection {
		if (account == null || account.equals("") || pwd == null
				|| pwd.equals(""))
			throw new NoPlatformUserExpection("无易打工平台账户信息");
		else
			new PostGameScoreTask(context, gId, account, pwd, score,
					resultCallback).commit();
	}

	@Override
	public void postUsedIntegralData(Context context, String account,
			String pwd, String gId, int usedIntegral,
			ResultCallBack resultCallback) throws NoPlatformUserExpection {
		if (account == null || account.equals("") || pwd == null
				|| pwd.equals(""))
			throw new NoPlatformUserExpection("无易打工平台账户信息");
		else
			new PostUsedIntegralTask(context, gId, account, pwd, usedIntegral,
					resultCallback).commit();

	}

	@SuppressWarnings("deprecation")
	private UserInfo getUser(Activity activity) {
		Uri myUri = ShareData.User.CONTENT_URI;
		Cursor cur = activity.managedQuery(myUri, null, null, null, null);
		if (cur == null) {
			return null;
		}
		if (cur.moveToFirst()) {
			// do {
			// String id =
			// cur.getString(cur.getColumnIndex(ShareData.User._ID));
			String account = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_ACCOUNT));
			String pwd = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_PWD));
			String uId = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_ID));
			String userName = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_NAME));
			String sex = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_SEX));
			String head = cur.getString(cur
					.getColumnIndex(ShareData.User.USER_HEAD));
			int score = cur.getInt(cur
					.getColumnIndex(ShareData.User.USER_SCORE));
			return new UserInfo(account, pwd, uId, userName, sex, head, score);
		}
		return null;
	}
}
