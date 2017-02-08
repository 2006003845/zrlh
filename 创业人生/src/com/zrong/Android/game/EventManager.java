package com.zrong.Android.game;

import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.zrong.Android.Util.Preferences;
import com.zrong.Android.View.Event;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.MapInfo;
import com.zrong.Android.entity.VentureSchoolData;
import com.zrong.Android.logic.Loading;
import com.zrong.Android.online.network.GameProtocol;
import com.zrong.Android.online.network.HttpConnection;

public class EventManager {

	private Context context;

	private GameGroupControl control;
	/**
	 * ����ע��ƽ̨
	 */
	public static final int ACTION_FASTREGIST = 1;

	/**
	 * ��������
	 */
	public static final int ACTION_SEVER_LIST = 2;

	/**
	 * ��¼��Ϸ������
	 */
	public static final int ACTION_LOGIN_SEVER = 3;

	/**
	 * ��¼ƽ̨
	 */
	public static final int ACTION_LOGIN = 4;

	/**
	 * ��ת�ĵ�¼����
	 */
//	public static final int ACTION_GO_LOGINVIEW = 5;

	/**
	 * ��ת�����������б�
	 */
	public static final int ACTION_GO_SEVERLIST = 6;
	/**
	 * ע���ɫ
	 */
	public static final int ACTION_REG_CHARACTOR = 7;
	/**
	 * ע�ṫ˾
	 */
	public static final int ACTION_REG_CONMANY = 8;
	/**
	 * ע���˺�
	 */
	public static final int ACTION_REGIST = 9;
	
	
	

	public EventManager(Context context, GameGroupControl control) {
		this.context = context;
		this.control = control;
	}

	private Vector manager = new Vector(5, 5);

	public EventManager addEvent(Event e) {
		manager.addElement(e);
		return this;
	}

	public void removeAllEvent() {
		manager.removeAllElements();
	}

	public Event getEvent() {
		if (manager.size() > 0) {
			return (Event) manager.elementAt(0);
		}
		return null;
	}

	public Event getEvent(int action) {
		if (manager.size() > 0) {
			Event e = null;
			for (int i = 0; i < manager.size(); i++) {
				e = (Event) manager.elementAt(i);

				if (e == null)
					continue;
				if (e.getAction() == action) {
					return e;
				}
			}
		}

		return null;
	}

	// poll���¼�
	public Event pollEvent() {
		if (manager.size() > 0) {
			Event e = (Event) manager.elementAt(0);
			manager.removeElement(e);
			return e;
		}
		return null;
	}

	public void nextAction() {
		Event e = pollEvent();// ��ȡ��ǰ(Vector)manger�еĵ�һ��event

		if (e == null)
			return;

		Vector v = new Vector(5, 5);
		switch (e.getAction()) {
		case ACTION_SEVER_LIST:

			HttpConnection severlist = new HttpConnection(
					GameProtocol.HTTP_SERVER_RES, control.connection);
			severlist.startURL(GameDef.ServerSelecetURL);// ����Ŀ��Ϊָ��url��HTTP����

			v.addElement(String.valueOf(1000));// Loading�ı�ʶ
			control.setGameStatus(GameDefinition.Game_Loading, v);// ʵ�ֶ���
			break;
		case ACTION_FASTREGIST:
			GameData.usersStatus = -1;
			HttpConnection httpFastRegist = new HttpConnection(
					GameProtocol.HTTP_STANDARD, control.connection);
			httpFastRegist.startURLPost(
					GameDef.platform,
					ConstructData.getXMLRequest(new String[] { "msgType",
							"userApp", "channel", "linename", "platform",
							"iMobile" }, new String[] { "FastRegist", "10001",
							GameDef.channelId, "AC1", GameDef.plat,
							GameDef.userIDALL }));
			v.addElement(String.valueOf(1001));
			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
		case ACTION_LOGIN:
			String uname = String.valueOf(e.getParam().get("name"));
			String upassword = String.valueOf(e.getParam().get("password"));
			HttpConnection httplogin = new HttpConnection(
					GameProtocol.HTTP_STANDARD, control.connection);
			httplogin.startURLPost(GameDef.platform, ConstructData
					.getXMLRequest(new String[] { "msgType", "uName", "uPwd","userApp",
							"channel", "platform", "iMobile" },
							new String[] { "LoginUnitive", uname, upassword,GameDef.userApp,
									GameDef.channelId, GameDef.plat,
									GameDef.userIDALL }));
			v.addElement(String.valueOf(1001));
			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
		case ACTION_LOGIN_SEVER:

			String username = String.valueOf(e.getParam().get("name"));
			String password = String.valueOf(e.getParam().get("password"));

			Connection.createConnection(GameDef.gateway, control.connection);
			Connection.sendMessage(
					(short) GameProtocol.CONNECTION_SEND_AUTHLOGIN,
					ConstructData.getAuthLogin(GameDef.keyID, username,
							password, GameDef.current_version/* (short)10100 */,
							GameDef.userId, GameDef.plat), 60000);
			v.addElement(String.valueOf(GameProtocol.CONNECTION_SEND_AUTHLOGIN));
			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
//		case ACTION_GO_LOGINVIEW:
//
//			String uuname = control.preferences.getString(Preferences.USERNAME);
//			//
//			String uupassword = control.preferences
//					.getString(Preferences.PASSWORD);
//
//			v.addElement(uuname);
//			v.addElement(uupassword);
//
//			Message message = Message.obtain();
//
//			message.what = GameDefinition.Message_SetGameStatus;
//			message.arg1 = GameDefinition.Game_Login;
//			message.obj = v;
//
//			// ͨ��Handler����Я���������������Ϣ
//
//			GameData.mhandler.sendMessage(message);
//			// control.setGameStatus(GameDefinition.Game_Login,v);
//			break;
		case ACTION_GO_SEVERLIST:

			// control.setGameStatus(GameDefinition.Game_SelectSever,
			// (Vector)e.getParam().get("element"));

			Message msg = Message.obtain();
			msg.what = GameDefinition.Message_SetGameStatus;
			msg.arg1 = GameDefinition.Game_SelectSever;
			msg.obj = (Vector) e.getParam().get("element");
			GameData.mhandler.sendMessage(msg);
			// ���ٵȴ�����
			if (control.logic.get("Loading") != null) {
				((Loading) control.logic.get("Loading")).destroy();
			}
			break;
		case ACTION_REG_CHARACTOR:// ע���ɫ
			String pname = String.valueOf(e.getParam().get("pname"));
			byte sex = Byte.parseByte(String.valueOf(e.getParam().get("sex")));
			int resId = Integer.parseInt(String.valueOf(e.getParam().get(
					"resId")));
			long pid = Long.parseLong(String.valueOf(e.getParam().get("pid")));

			Connection.sendMessage(GameProtocol.CONNECTION_SEND_CREATE_REG,
					ConstructData.getCreatRoleTest(pname, "", sex, resId, "",
							pid));
			v.addElement(GameProtocol.CONNECTION_SEND_CREATE_REG);
			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
		case ACTION_REG_CONMANY:// ע�ṫ˾
			String cName = String.valueOf(e.getParam().get("cname"));
			Connection.sendMessage(
					(short) GameProtocol.CONNECTION_SEND_CPINFOR,
					ConstructData.getCpName(cName));
//			v.addElement(GameProtocol.CONNECTION_SEND_CPINFOR);
//			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
		case ACTION_REGIST:// ע���˺�
			GameData.usersStatus = -1;
			String regname = String.valueOf(e.getParam().get("name"));
			String regpass = String.valueOf(e.getParam().get("password"));

			HttpConnection httpregist = new HttpConnection(
					GameProtocol.HTTP_STANDARD, control.connection);
			httpregist.startURLPost(
					GameDef.platform,
					ConstructData.getXMLRequest(new String[] { "msgType",
							"uName", "uPwd", "userApp", "channel", "linename",
							"platform", "iMobile" }, new String[] { "Reg",
							regname, regpass, "10001", GameDef.channelId,
							"AET", GameDef.plat, GameDef.userIDALL }));
			control.setGameStatus(GameDefinition.Game_Loading, v);
			break;
		}
	}

	

}
