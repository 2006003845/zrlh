package com.zzl.zrlh.llkc_sdk.api;

import android.app.Activity;
import android.content.Context;

import com.zzl.zrlh.llkc_sdk.beans.UserInfo;
import com.zzl.zrlh.llkc_sdk.net.NoPlatformUserExpection;
import com.zzl.zrlh.llkc_sdk.task.RankListCallBack;
import com.zzl.zrlh.llkc_sdk.task.ResultCallBack;

/**
 * 
 * @author ZZL
 * 
 */
public interface IPort {

	UserInfo getUserInfo(Activity act);

	/**
	 * 获取好友游戏排名
	 * 
	 * @param account
	 * @param pwd
	 * @param gId
	 *            游戏Id
	 * @return
	 */
	void getGameRankList(Context context, String account, String pwd,
			String gId, RankListCallBack resultCallback)
			throws NoPlatformUserExpection;

	/**
	 * 提交游戏成绩
	 * 
	 * @param account
	 *            易打工账户
	 * @param pwd
	 *            密码
	 * @param gId
	 *            游戏Id
	 * @param score
	 *            得分
	 * @return
	 */
	void postGameScoreData(Context context, String account, String pwd,
			String gId, int score, ResultCallBack resultCallback)
			throws NoPlatformUserExpection;

	/**
	 * 提交平台消耗积分
	 * 
	 * @param context
	 * @param account
	 * @param pwd
	 * @param gId
	 * @param usedIntegral
	 *            消耗积分
	 * @param resultCallback
	 * @throws NoPlatformUserExpection
	 */
	void postUsedIntegralData(Context context, String account, String pwd,
			String gId, int usedIntegral, ResultCallBack resultCallback)
			throws NoPlatformUserExpection;

}
