package cn.zrong.handler;

import java.util.List;

import json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.AccountManagerActivity;
import cn.zrong.activity.CardActivity;
import cn.zrong.activity.CardEditActivity;
import cn.zrong.activity.GroupActivity;
import cn.zrong.activity.GroupCreateActivity;
import cn.zrong.activity.GroupInfoActivity;
import cn.zrong.activity.HomeActivity;
import cn.zrong.activity.WelActivity;
import cn.zrong.connection.Community;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.db.HallDB;
import cn.zrong.entity.GameItem;
import cn.zrong.entity.User;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.utils.Tools;
import cn.zrong.weibobinding.BindingAccount;

/**
 * 
 * <p>
 * Titile:MessageHandler
 * </p>
 * <p>
 * Description: use demo Message msg = Message.obtain(); msg.what =
 * MES_SHOWDIALOG; MessageHandler.sharedHandler.sendMessage(msg);
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author yz
 * @version 1.0
 * @date 2012-5-24
 */
public class MessageHandler extends Handler {

	private Context context;

	/**
	 * show dialog
	 */
	public static final int MES_SHOWDIALOG = 1;
	/**
	 * 快速注册成功
	 */
	public static final int MES_FastRegist_SUCCESS = 2;
	/**
	 * 注册成功
	 */
	public static final int MES_Regist_SUCCESS = 3;
	/**
	 * 登录成功
	 */
	public static final int MES_LOGIN_SUCCESS = 4;
	/**
	 * 插入用戶名密碼
	 */
	public static final int MES_INSERT_USER = 5;
	/**
	 * 更新用戶名密碼
	 */
	public static final int MES_UPDATE_USER = 6;
	/**
	 * 创建角色
	 */
	public static final int MES_CREATE_ROLE = 7;
	/**
	 * 更新角色
	 */
	public static final int MES_UPDATE_ROLE = 8;

	/**
	 * 更新WEibo User
	 */
	public static final int MES_UPDATE_WEIBOUSER = 18;
	/**
	 * 列出角色
	 */
	public static final int MES_List_ROLE = 9;

	/**
	 * 创建微群
	 */
	public static final int MES_CREATE_GROUP = 10;

	/**
	 * 加入微群
	 */
	public static final int MES_JOINT_GROUP = 11;

	/**
	 * 修改账号密码
	 */
	public static final int MES_CHANGE_AccountAndPwd = 12;
	/**
	 * 重置密码
	 */
	public static final int MES_SET_Pwd = 13;
	/**
	 * 登录角色
	 */
	public static final int MES_LOGIN_ROLE = 14;

	/**
	 * 发送私信成功
	 */
	public static final int MES_SENDMSG_SUCCESS = 15;

	/**
	 * 发送微博成功
	 */
	public static final int MES_sendWeibo_Success = 17;

	public static final int MES_removeGroup = 20;

	private static MessageHandler handler;

	public static MessageHandler sharedHandler() {
		return handler;
	}

	public MessageHandler(Looper looper, Context context) {
		super(looper);
		this.context = context;
		handler = this;
	}

	@Override
	public void handleMessage(Message msg) {
		Intent intent = new Intent();
		switch (msg.what) {
		case MES_SHOWDIALOG:
			Log.v("DEBUG", "" + MES_SHOWDIALOG);
			break;
		case MES_FastRegist_SUCCESS:
			// 发送创建角色包
			Connection.sendMessage(
					Protocol.msgType_createRole,
					IOWriter.getRoleCreate(Protocol.msgType_createRole,
							ApplicationData.currentUser.getKeyID(),
							GameAPI.userApp, GameAPI.channel, GameAPI.platform,
							ApplicationData.currentUser.getU_name(), "0")
							.getBytes(), GameAPI.Port_Role);
			break;
		case MES_Regist_SUCCESS:
			HomeActivity.launch(context, intent);
			if (WelActivity.mIntance != null) {
				WelActivity.mIntance.finish();
				WelActivity.mIntance = null;
			}
			break;
		case MES_LOGIN_SUCCESS:
			String str = IOWriter.getBrowseRoleInfo(Protocol.msgType_loginRole,
					ApplicationData.currentUser.getKeyID(), "");
			// 发送登录角色包
			Connection.sendMessage(Protocol.msgType_loginRole, str.getBytes(),
					GameAPI.Port_Role);
			break;
		case MES_INSERT_USER:
			HallDB.getHallDBInstance(context).insertUser((User) msg.obj);
			break;
		case MES_UPDATE_USER:
			HallDB.getHallDBInstance(context).updateUser((User) msg.obj);
			break;
		case MES_CREATE_ROLE:
			// TODO
			HallDB.getHallDBInstance(context).updateUserInfo(
					ApplicationData.currentUser);
			if (WelActivity.mIntance != null) {
				showToast("登录成功");
				WelActivity.mIntance.dialog.dismiss();
				WelActivity.mIntance.finish();
				WelActivity.mIntance = null;
			}
			Bundle b = new Bundle();
			b.putBoolean(HomeActivity.LAG_IS_NEWCOMMER, true);
			intent.putExtras(b);
			HomeActivity.launch(context, intent);

			break;
		case MES_LOGIN_ROLE:
			HomeActivity.launch(context, intent);
			if (WelActivity.mIntance != null) {
				showToast("登录成功");

				WelActivity.mIntance.dialog.dismiss();
				WelActivity.mIntance.finish();
				WelActivity.mIntance = null;
			}
			// 获取绑定微博账号
			getBindingUserFromDB();
			// load 游戏列表
			new AsyncDataLoader(new AsyncDataLoader.Callback() {

				@Override
				public void onStart() {
					try {
						Community comm = Community.getInstance(context);
						if (comm != null) {
							List<GameItem> list = comm
									.getGameItemListOfClient();
							if (list != null) {
								ApplicationData.gameItemListOfClient
										.addAll(list);
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onPrepare() {
				}

				@Override
				public void onFinish() {
				}
			}).execute();
			break;
		case MES_UPDATE_ROLE:
			// TODO
			showToast("修改成功");
			if (CardEditActivity.mInstance != null) {
				if (CardEditActivity.mInstance.dialog != null
						&& CardEditActivity.mInstance.dialog.isShowing()) {
					CardEditActivity.mInstance.dialog.dismiss();
					CardEditActivity.mInstance
							.setProgressBarIndeterminateVisibility(false);
				}
				CardEditActivity.mInstance.getGameContext().finish();
				CardEditActivity.mInstance = null;
				if (CardActivity.mIntance != null) {
					CardActivity.mIntance.update();
				}
				// if(HomeActivity.mInstance!=null){
				// HomeActivity.mInstance.myName
				// }
			}

			break;
		case MES_UPDATE_WEIBOUSER:
			// TODO
			Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
			if (CardEditActivity.mInstance != null) {
				if (CardEditActivity.mInstance.dialog != null
						&& CardEditActivity.mInstance.dialog.isShowing()) {
					CardEditActivity.mInstance.dialog.dismiss();
					CardEditActivity.mInstance
							.setProgressBarIndeterminateVisibility(false);
				}
				CardEditActivity.mInstance.getGameContext().finish();
				CardEditActivity.mInstance = null;
				if (CardActivity.mIntance != null) {
					CardActivity.mIntance.update();
				}
			}

			break;
		case MES_List_ROLE:
			// TODO
			break;
		case MES_CHANGE_AccountAndPwd:
			if (AccountManagerActivity.mInstance != null) {
				if (AccountManagerActivity.mInstance.dialog != null
						&& AccountManagerActivity.mInstance.dialog.isShowing()) {
					AccountManagerActivity.mInstance.dialog.dismiss();
					AccountManagerActivity.mInstance
							.setProgressBarIndeterminateVisibility(false);
				}
			}
			showToast("修改成功");
			ApplicationData.currentUser
					.setU_name(ApplicationData.temporaryAccountName);
			ApplicationData.currentUser.setU_psd(ApplicationData.temporaryPwd);
			HallDB.getHallDBInstance(context).updateUser(
					ApplicationData.currentUser);

			// TODO
			break;
		case MES_SET_Pwd:
			// TODO
			if (AccountManagerActivity.mInstance != null) {
				if (AccountManagerActivity.mInstance.dialog != null
						&& AccountManagerActivity.mInstance.dialog.isShowing()) {
					AccountManagerActivity.mInstance.dialog.dismiss();
					AccountManagerActivity.mInstance
							.setProgressBarIndeterminateVisibility(false);
				}
			}
			if (msg.arg1 == 0) {
				showToast("修改成功");
				ApplicationData.currentUser
						.setU_psd(ApplicationData.temporaryPwd);
				HallDB.getHallDBInstance(context).updateUser(
						ApplicationData.currentUser);
			} else {

				showToast("修改失败");
			}

			break;
		case MES_SENDMSG_SUCCESS:
			// TODO
			showToast("发送成功");

			break;

		case MES_JOINT_GROUP:
			String text = (String) msg.obj;
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			break;
		case MES_sendWeibo_Success:
			if (GroupActivity.mInstance != null) {
				GroupActivity.mInstance.updateGroup();
			}
			break;

		case MES_CREATE_GROUP:
			if (GroupCreateActivity.mInstance != null) {
				if (GroupCreateActivity.mInstance.dialog != null
						&& GroupCreateActivity.mInstance.dialog.isShowing()) {
					GroupCreateActivity.mInstance.dialog.dismiss();
					GroupCreateActivity.mInstance
							.setProgressBarIndeterminateVisibility(false);
					HomeActivity.mInstance.updateMyGroups();
				}
				GroupCreateActivity.mInstance.getGameContext().finish();
				GroupCreateActivity.mInstance = null;
			}
			break;
		case MES_removeGroup:
			if (msg.arg1 == 200) {
				if (GroupInfoActivity.mInstance != null) {
					GroupInfoActivity.mInstance.finish();
					GroupInfoActivity.mInstance = null;
					if (GroupActivity.mInstance != null) {
						GroupActivity.mInstance.finish();
						GroupActivity.mInstance = null;
					}
					HomeActivity.mInstance.updateMyGroups();
				}
				showToast("解散成功");
			} else if (msg.arg1 == -1) {
				showToast("解散失败");
			}
			break;
		default:
			break;
		}
	}

	private void getBindingUserFromDB() {
		Cursor c = HallDB.getHallDBInstance(context).query(
				BindingAccount.BindingAccountTable.TAB_NAME,
				null,
				BindingAccount.BindingAccountTable.BINDINGUSER_USER_KEYID
						+ "=?",
				new String[] { ApplicationData.currentUser.getKeyID() }, null,
				null, null);
		c.moveToFirst();
		List<BindingAccount> list = BindingAccount.BindingAccountTable
				.getBindingUserList(c);
		if (list != null) {
			ApplicationData.bindingUserList.clear();
			ApplicationData.bindingUserList.addAll(list);
		}
	}

	private void showToast(String text) {
		Tools.showToast(context, text);
	}
}
