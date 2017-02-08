package com.zrong.Android.game;














import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;



import res.BuildingSprite;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zrong.Android.Util.GameAPI;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.Preferences;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.Util.xmlPhaser.XElem;
import com.zrong.Android.Util.xmlPhaser.XParser;
import com.zrong.Android.View.Event;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.activity.AnswersQuesActivity; 
import com.zrong.Android.activity.ChatWindowActivity;
import com.zrong.Android.activity.CommerceDiaryActivity;
import com.zrong.Android.activity.CommerceInfoActivity;
import com.zrong.Android.activity.CommerceListActivity;
import com.zrong.Android.activity.CommerceStaffActivity;
import com.zrong.Android.activity.CompanyLevelInfoActivity;
import com.zrong.Android.activity.CompanyTabActivity;
import com.zrong.Android.activity.CreateCommerceActivity;
import com.zrong.Android.activity.DepartmentActivity;
import com.zrong.Android.activity.DepartmentLevelInfoActivity;
import com.zrong.Android.activity.DiglistActivity;
import com.zrong.Android.activity.DoctorCustodyActivity;
import com.zrong.Android.activity.DoctorInfoActivity;
import com.zrong.Android.activity.DoctorShopActivity;
import com.zrong.Android.activity.DoctorShopinfoActivity;
import com.zrong.Android.activity.DoctorTaskActivity;
import com.zrong.Android.activity.Lottery_Station_Activity;
import com.zrong.Android.activity.MailDetailActivity;
import com.zrong.Android.activity.MailboxActivity;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.PlanningDeptActivity;
import com.zrong.Android.activity.PlanningDeptShoplistActivity;
import com.zrong.Android.activity.PromotersActivity;
import com.zrong.Android.activity.PropagandaListActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.RecruitActivity;
import com.zrong.Android.activity.ShopInfoActivity;
import com.zrong.Android.activity.ShopListActivity;
import com.zrong.Android.activity.SimpleShopListActivity;
import com.zrong.Android.activity.SocialActivity;
import com.zrong.Android.activity.StoreActivity;
import com.zrong.Android.activity.TaskActivity;
import com.zrong.Android.activity.TaskAnswerResultActivity;
import com.zrong.Android.activity.TaskDetailActivity;
import com.zrong.Android.activity.TaskListActivity;
import com.zrong.Android.activity.VentureEvalutionActivity;
import com.zrong.Android.activity.shopInfo2Activity;
import com.zrong.Android.api.XMLParseUtil;
import com.zrong.Android.element.Branch;
import com.zrong.Android.element.Card;
import com.zrong.Android.element.Chat;
import com.zrong.Android.element.Corporation;
import com.zrong.Android.element.Department;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Mail;
import com.zrong.Android.element.Member;
import com.zrong.Android.element.MemberEmployee;
import com.zrong.Android.element.Player;
import com.zrong.Android.element.Promoter;
import com.zrong.Android.element.Propaganda;
import com.zrong.Android.element.Props;
import com.zrong.Android.element.PublicBuilding;
import com.zrong.Android.element.Quality;
import com.zrong.Android.element.Shop;
import com.zrong.Android.element.Skill;
import com.zrong.Android.element.Social;
import com.zrong.Android.element.Strategy;
import com.zrong.Android.element.Task;
import com.zrong.Android.logic.Loading;
import com.zrong.Android.logic.LogicObject;
import com.zrong.Android.logic.MainMenu;
import com.zrong.Android.logic.SelectSever;
import com.zrong.Android.online.network.GameProtocol;
import com.zrong.Android.online.network.HttpConnection;

public class AnalysisData {

	public static final String TAG = "AnalysisData";

	public static Context context;

	public static GameGroupControl control;

	private static LayoutInflater factory = null;
	
	public static int staff_NUM;

	/**
	 * 解析移动用户平台数据
	 */
	public static void exec_Http_CMCC(DataInputStream dis) {
		parseUserId(dis);
	}

	private static void parseUserId(DataInputStream dis) 
	{
		try {
			for (int i = 0; i < GameDef.reStrs.length; i++) 
			{
				GameDef.reStrs[i] = "";

				int ch = dis.read();
				while (ch != '=' && ch != -1) {
					ch = dis.read();
				}
				ch = dis.read();
				while (ch != '\r' && ch != -1) {
					GameDef.reStrs[i] += (char) ch;
					ch = dis.read();
				}
			}
			GameDef.userIDALL = GameDef.reStrs[0] + "," + GameDef.reStrs[1]
					+ "," + GameDef.reStrs[2] + "," + GameDef.reStrs[3];
			GameDef.userID = GameDef.reStrs[2];
			 show_check_cmcc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void show_check_cmcc() {
		GameDef.userID = GameDef.reStrs[2];
		HttpConnection httplogin = new HttpConnection(
				GameProtocol.HTTP_STANDARD, control.connection);
		httplogin.startURLPost(GameDef./* STANDARD_URL */checking_cmcc,
				ConstructData.getXMLRequest(new String[] { "msgType", "hRet",
						"status", "userId", "key", "region", "channel" },
						new String[] { "MobileCheck", GameDef.reStrs[0],
								GameDef.reStrs[1], GameDef.reStrs[2], "" + 0,
								"", GameDef.channelId }));
	}

	public static void exec_Http_ServerResp(DataInputStream dis) {
		try {

			XParser uiXML = XParser.make(dis);

			Log.v(TAG, "depth =" + uiXML.depth());

			XElem uiElem = uiXML.parse();

			Event e = control.eventManger.getEvent();

			if (e != null) {
				int savedId = control.preferences
						.getInt(Preferences.SEVERLINE_ID);

				int selectIndex = -1;

				XElem line[] = uiElem.elems();

				if (line != null) {
					for (int i = 0; i < line.length; i++) {
						if (line[i] != null) {
							String idstr = line[i].elem("id").text().string();
							String def = line[i].elem("default").text()
									.string();

							if (def != null && def.equals("1"))// 如果是默认的那条线
							{
								selectIndex = i;
							}

							if (idstr != null) {
								try {
									int id = Integer.parseInt(idstr);
									if (id == savedId) {
										selectIndex = i;
										break;
									}
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						}
					}
				}
				if (selectIndex < 0) {
					selectIndex = 0;
				}
				GameDef.gateway = line[selectIndex].elem("gateway").text()
						.string();
				GameDef.agentCodeHi = line[selectIndex].elem("agentCodeHi")
						.text().string();
				GameDef.agentCodeLo = line[selectIndex].elem("agentCodeLo")
						.text().string();
				GameDef.resServer = line[selectIndex].elem("resServer").text()
						.string();
				GameDef.platform = line[selectIndex].elem("platform").text()
						.string();

				if (e.getAction() == EventManager.ACTION_FASTREGIST) {
					LogicObject logicobj = control.logic.get("SelectSever",
							GameDefinition.Game_SelectSever);

					((SelectSever) logicobj).setElem(uiElem);
				} else if (e.getAction() == EventManager.ACTION_LOGIN) {

				} else {
					Hashtable hash = new Hashtable();
					Vector v = new Vector();
					v.addElement(uiElem);
					hash.put("element", v);
					e.setParam(hash);
				}
				control.eventManger.nextAction();
			} 
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void exec_Http_Standard(DataInputStream dis) {
		XParser uiXML;
		try {
			uiXML = XParser.make(dis);

			Log.v(TAG, "depth =" + uiXML.depth());

			XElem uiElem = uiXML.parse();

			String msgType = uiElem.elem("msgType").text().string();

			if (msgType.equals("FastRegist")) {
				String s = uiElem.elem("status").text().string();
				if (s.equals("0"))// 成功
				{
					String username = uiElem.elem("Uname").text().string();
					String password = uiElem.elem("Upwd").text().string();

					GameDef.keyID = uiElem.elem("keyID").text().string();

					control.preferences.set(Preferences.USERNAME, username);

					control.preferences.set(Preferences.PASSWORD, password);

					Hashtable hash = new Hashtable();

					hash.put("name", username);

					hash.put("password", password);

					control.eventManger.getEvent(
							EventManager.ACTION_LOGIN_SEVER).setParam(hash);

					control.eventManger.nextAction();
				} else {
					if (control.logic.get("Loading") != null) {
						((Loading) control.logic.get("Loading")).destroy();
					}
//					Message message = Message.obtain();
//					message.what = GameDefinition.Message_showToast;
//
//					Vector v = new Vector();
//					v.addElement(showErrorInfo(s));
//					message.obj = v;
//
//					GameData.mhandler.sendMessage(message);
					GameAPI.showToast(s);
				}
			} else if (msgType.equals("LoginUnitive")) {
				String s = uiElem.elem("status").text().string();
				if (s.equals("0"))// 成功
				{
					GameDef.keyID = uiElem.elem("keyID").text().string();

					Hashtable hash = new Hashtable();

					LogicObject logic = control.logic.get("Login");

					if (logic != null) {
						hash.put(
								"name",
								((EditText) logic.getView().findViewById(
										R.id.lottery_bet_edit_number)).getText());
						hash.put(
								"password",
								((EditText) logic.getView().findViewById(
										R.id.lottery_bet_edit_buy_number)).getText());

						control.preferences.set(
								Preferences.USERNAME,
								((EditText) logic.getView().findViewById(
										R.id.lottery_bet_edit_number)).getText()
										.toString());
						control.preferences.set(
								Preferences.PASSWORD,
								((EditText) logic.getView().findViewById(
										R.id.lottery_bet_edit_buy_number)).getText()
										.toString());
					} else {
						hash.put("name", control.preferences
								.getString(Preferences.USERNAME));
						hash.put("password", control.preferences
								.getString(Preferences.PASSWORD));
					}
//					Event e1 = new Event(EventManager.ACTION_LOGIN_SEVER,hash);
//					
//					GameGroupControl.eventManger.addEvent(e1);
					control.eventManger.getEvent(EventManager.ACTION_LOGIN_SEVER).setParam(hash);

					control.eventManger.nextAction();
				} else {
					if (control.logic.get("Loading") != null) {
						((Loading) control.logic.get("Loading")).destroy();
					}

//					Message message = Message.obtain();
//					message.what = GameDefinition.Message_showToast;
//
//					Vector v = new Vector();
//					v.addElement(showErrorInfo(s));
//					message.obj = v;
//
//					GameData.mhandler.sendMessage(message);
					GameAPI.showToast(s);
				}
			} else if (msgType.equals("Reg")) {
				String s = uiElem.elem("status").text().string();
				if (s.equals("0"))// 成功
				{
					GameDef.keyID = uiElem.elem("keyID").text().string();

					Event e = control.eventManger.getEvent();
					control.preferences.set(Preferences.USERNAME,
							String.valueOf(e.getParam().get("name")));
					control.preferences.set(Preferences.PASSWORD,
							String.valueOf(e.getParam().get("password")));
					control.eventManger.nextAction();

				} else {
					if (control.logic.get("Loading") != null) {
						((Loading) control.logic.get("Loading")).destroy();
					}
//					Message message = Message.obtain();
//					message.what = GameDefinition.Message_showToast;
//
//					Vector v = new Vector();
//					v.addElement(showErrorInfo(s));
//					message.obj = v;
//
//					GameData.mhandler.sendMessage(message);
					GameAPI.showToast(s);
				}
			} else if (msgType.endsWith("MobileCheck"))// 移动认证消息
			{
				final XElem elem = uiElem;
				Thread thread = new Thread(new Runnable() {
					public void run() {
						while (!GameData.isEnterMainMenu) {
							SystemAPI.sleep(200);
						}

						try {
							String s = elem.elem("status").text().string();
							if (s.equals("0")) {
								String isAlert = elem.elem("isAlert").text()
										.string();
								String AlertInfo = elem.elem("AlertInfo")
										.text().string();
								if (isAlert != null) {
									if (isAlert.equals("1")) {

									} else if (isAlert.equals("2")) {

									} else if (isAlert.equals("3")) {

									} else if (isAlert.equals("4")) {

									} else if (isAlert.equals("5")) {

									}
								}
							} else {
								// throw new Exception();
//								Message message = Message.obtain();
//
//								message.what = GameDefinition.Message_showToast;
//
//								Vector v = new Vector();
//
//								v.addElement(s);
//
//								message.obj = v;
//
//								GameData.mhandler.sendMessage(message);
							}
						} catch (Exception e) {
							// GameDef.cmcc_Loading_over = true;
							// Script.saveAccount();
							// DrawLoading.destroy();
						}
					}

				});
				thread.start();

				// zhouzhilong add---parse
			}
			/*
			 * else if ("showSchoolInfo".equals(msgType)) { // 解析消息 String
			 * infoListStr = XMLParseUtil.convertStreamToString(dis);
			 * 
			 * } else if ("mapInfo".equals(msgType)) { // 解析地图信息 String
			 * mapInfoStr = XMLParseUtil.convertStreamToString(dis);
			 */

			// }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 断网重连包
	 * 
	 * @param dis
	 */
	public static void exec_Resp_ConnectionInfo(DataInputStream dis) {
		try {
			GameData.key1 = dis.readInt();
			GameData.key2 = dis.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exec_Login_Resp(DataInputStream dis) {
		try {
			byte status = dis.readByte();
			String message = dis.readUTF();
			if (status == 0)// 成功
			{
				GameData.isLogined = true;// 登陆标识
			} else// 失败
			{
				if (control.logic.get("Loading") != null) {
					((Loading) control.logic.get("Loading")).destroy();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void exec_Resp_SystemInfo(DataInputStream dis) {
		try {
			Chat chat = new Chat();
			chat.channel = dis.readByte();// 频道0世界1私聊 2商会3系统
			chat.level = dis.readByte();// 显示级别 0 正常信息 1
			chat.content = dis.readUTF();// 系统消息内容
			chat.content = SystemAPI.getText(chat.content);
			chat.id = dis.readLong();// 发言者ID(-1为系统发言)
			chat.name = dis.readUTF();// 发言者名称
			if(chat.level == 1)
			{
				dis.readByte();
			}

			int channel = chat.channel;

			/*if (chat.channel == 0) {
				channel = 1;
			} else if (chat.channel == 1) {
				channel = 2;
			} else if (chat.channel == 2) {
				channel = -1;
			} else if (chat.channel == 3) {
				channel = 3;
				Log.i("Log", "系统:" + chat.content);
			}*/
			GameData.addChat(0, chat);// 向全部里添加聊天信息
			GameData.addChat(channel+1, chat);// 向频道里添加

			/*if (ChatBoxActivity.mContext != null ) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 6;
				message.arg2 = channel;
				message.obj = chat;

				GameData.mhandler.sendMessage(message);
			}*/
			if (ChatWindowActivity.context != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 6;
				message.arg2 = channel;
				message.obj = chat;

				GameData.mhandler.sendMessage(message);
			}
			if (chat.channel == 3)// 系统消息
			{
//				Message message = Message.obtain();
//				message.what = GameDefinition.Message_showToast;
//
//				Vector v = new Vector();
//				v.addElement(chat.content);
//				message.obj = v;
//
//				GameData.mhandler.sendMessage(message);
				GameAPI.showToast(chat.content);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Game.instance.chat.addMessage(5, channel, true, name, id,
		// false, "对我", s);// 向“全部”频道添加聊天信息
		// Game.instance.chat.addMessage(0, channel, true, name, id,
		// false, "对我", s);// 向“全部”频道添加聊天信息
		// Game.instance.chat.addMessage(channel + 1, channel, false,
		// name, id, false, "对我", s);// zhangxiaoqing false
		// if (channel == 3) {
		// if (!Tutorial.isInTutorial()) {
		// Game.instance.initClewBox("系统消息", s, true);
		// }
		// }
	}

	public static void exec_Resp_Accept_CreateReg(DataInputStream dis) {

		// GameData.usersStatus = dis.readByte();
		// GameData.userMsg = dis.readUTF();

		byte usersStatus = 0;
		String userMsg = null;
		try {
			usersStatus = dis.readByte();
			userMsg = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.d("用户创建结果", "status=" + usersStatus + ",msg=" + userMsg);

		if (usersStatus == 0)// 成功
		{
			Hashtable hash = new Hashtable();

			/*EditText et = (EditText) (control.logic.get("CreateCharactor")
					.getView().findViewById(R.id.createcharactor_cname));

			hash.put("cname", et.getText().toString().trim());*/
			
			hash.put("cname", "abc");

			Event e = new Event(EventManager.ACTION_REG_CONMANY, hash);

			control.eventManger.addEvent(e);

			control.eventManger.nextAction();

		} else if (usersStatus == -1)// 失败
		{
//			Message message = Message.obtain();
//			
//			message.what = GameDefinition.Message_showToast;
//			Vector v = new Vector();
//			v.addElement(userMsg);
//			message.obj = v;
//			Log.i("Log", "响应结果:" + v.toString());
//			GameData.mhandler.sendMessage(message);
			GameAPI.showToast(userMsg);
		} else if (usersStatus == -2)// 需要创建角色
		{
			if (control.logic.get("Loading") != null) {
				((Loading) control.logic.get("Loading")).destroy();
			}
			// 通过handle进入创建角色的acitvity
			Message message = Message.obtain();
			message.what = GameDefinition.Message_SetGameStatus;
			message.arg1 = GameDefinition.Game_CreateCharactor;
			Vector v = new Vector(5, 5);
			v.addElement(control.preferences.getString(Preferences.USERNAME));
			message.obj = v;
			GameData.mhandler.sendMessage(message);
			
		}
	}

	/**
	 * 接受公司创建结果
	 */
	public static void exec_Resp_Accept_CreateCp(DataInputStream dis)
			throws Exception {
		GameData.cpStatus = dis.readByte();

		GameData.cpMsg = dis.readUTF();

		if (GameData.cpStatus == 0) {
	
		} else if (GameData.cpStatus == -2 || GameData.cpStatus == -1) {
			if (control.logic.get("Loading") != null) {
				((Loading) control.logic.get("Loading")).destroy();
			}
			// 通过handle进入创建角色的acitvity
			Message message = Message.obtain();
			message.what = GameDefinition.Message_SetGameStatus;
			message.arg1 = GameDefinition.Game_CreateCharactor;
			Vector v = new Vector(5, 5);
			v.addElement(control.preferences.getString(Preferences.USERNAME));
			message.obj = v;
			GameData.mhandler.sendMessage(message);
		}

	}

	public static void exec_Resp_Map_LIST_INFO(DataInputStream dis) {
		try {
			byte size = dis.readByte();
			GameData.mapIds = new short[size];
			GameData.mapNames = new String[size];
			GameData.mapTraffic = new int[size];
			GameData.mapRent = new int[size];
			GameData.mapTaxRate = new int[size];
			GameData.canBuildType = new byte[size][];
			GameData.mapWidth = new short[size];
			GameData.mapHeight = new short[size];
			for (int i = 0; i < size; i++) {
				GameData.mapIds[i] = dis.readShort();
				GameData.mapNames[i] = dis.readUTF();
				GameData.mapTraffic[i] = dis.readInt();
				GameData.mapRent[i] = dis.readInt();
				GameData.mapTaxRate[i] = dis.readInt();
				byte subsize = dis.readByte();
				GameData.canBuildType[i] = new byte[subsize];
				for (int j = 0; j < subsize; j++) {
					GameData.canBuildType[i][j] = dis.readByte();
				}
				GameData.mapWidth[i] = dis.readShort();
				GameData.mapHeight[i] = dis.readShort();

			}
			Script.getLocationOffset();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 角色基本信息
	 */
	public static void exec_Resp_Accept_UsersInformation(DataInputStream dis)
			throws Exception {
		//zhouzhilong add
		GameData.usersStatus = 0; 

		
		if(GameData.player == null)
			GameData.player = new Player();
			GameData.player.id = dis.readLong();//角色ID     1
			GameData.player.name = dis.readUTF();//角色名 2
			GameData.player.address = dis.readUTF();//角色地址 3
			GameData.player.sex = dis.readByte();//性别 4
			GameData.player.headResIdx = dis.readInt();//角色头像 5
			GameData.player.level = dis.readShort();//等级 6
			GameData.player.phone = dis.readUTF();//电话 7
			GameData.player.assets= dis.readLong();//资产 8
			GameData.player.capacityId = dis.readShort();//官职 9
			GameData.player.businessId = dis.readLong();//商会ID 10
			GameData.player.businessName = dis.readUTF();//商会名称 11
			GameData.player.money = dis.readLong();//现金  
			GameData.player.voteNum = dis.readInt();//选票数量 
			GameData.player.loan = dis.readLong();//债务 14
			GameData.player.incomeToday = dis.readLong();//今日收入 15
			GameData.player.incomeYesterday = dis.readLong();//昨日收入 16
			GameData.player.strategy = dis.readInt();//策略 17
			GameData.player.maxStrategy = dis.readInt();//策略最大 18
			GameData.player.leadership = dis.readInt();//领导 19
			GameData.player.maxLeadership = dis.readInt();//领导最大 20
			GameData.player.execution = dis.readInt();//执行 21
			GameData.player.maxExecution = dis.readInt();//执行 22
			GameData.player.strength = dis.readInt();//体力 23
			GameData.player.maxStrength = dis.readInt();//体力最大 24 
			GameData.player.friendship = dis.readLong();//好友值 25
			GameData.player.allFriendship = dis.readLong();//累积好友值 26
			GameData.player.badgeId = dis.readLong();//点亮的徽章ID 27
			String temp = dis.readUTF();
			if(!temp.equals("")){
			String[] info = SystemAPI.split(temp, "$");
			GameData.player.imgId = Integer.parseInt(info[1]);//点亮的徽章资源ID 28
			}
			GameData.player.badgenumber = dis.readInt();//获得的徽章数 28
			GameData.player.badgeMAXnumber = dis.readInt();//最大徽章数 29

			GameData.player.assets_rank = dis.readInt();//资产排名 30
			GameData.player.companyName = dis.readUTF();//公司名 31
			GameData.player.shopCount = dis.readShort();//店铺数量 32
			GameData.player.employeeCount = dis.readShort();//员工数量 33
			GameData.player.accessLevel = dis.readByte();//访问级别 34
			GameData.player.advocacy = dis.readByte();//联合宣传状态：-1(无);0(等待);1(正在进行) 35
			GameData.player.recommendCode = dis.readUTF() ;//我的推荐码 36
			GameData.player.referrerName = dis.readUTF() ;//推荐人名称 37
	}

	/**
	 * 公司基本信息
	 */
	public static void exec_Resp_Accept_CompInformation(DataInputStream dis)
			throws Exception {
		if (GameData.corporation == null)
			GameData.corporation = new Corporation();

		GameData.corporation.companyId = dis.readLong();
		GameData.corporation.name = dis.readUTF();
		GameData.corporation.level = dis.readByte();
		GameData.corporation.wealth = dis.readLong();
		GameData.corporation.awareness = dis.readInt();
		GameData.corporation.prestige = dis.readInt();
		GameData.corporation.shopCount = dis.readShort();
		GameData.corporation.shopMaxNum = dis.readShort();
		GameData.corporation.employeeCount = dis.readShort();

		GameData.corporation.employeesMaxNum = dis.readShort();

		// Message message = Message.obtain();
		// message.what = GameDefinition.Message_SetGameStatus;
		// message.arg1 = GameDefinition.Game_Office;
		//
		//
		// GameData.mhandler.sendMessage(message);

		if (CompanyTabActivity.mContext != null) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 0;// 公司数据修改

			GameData.mhandler.sendMessage(message);
		} else {
			GameData.isResetMap = true;
			GameData.mapIdIndexBack = 0;
			Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
					ConstructData.getMapAroundShop((byte) 1, (short) -1,
							(short) -1, (byte) 1,
							(byte) (GameData.ARRAY_LENTH / 2)));
			/*Vector v = new Vector(5, 5);
			v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
			control.setGameStatus(GameDefinition.Game_Loading, v);*/

			Music.getInstance(MainActivity.mContext).start(R.raw.m2, true);
		}

	}

	public static void exec_Resp_Accept_DateTime_Info(DataInputStream dis)
			throws Exception 
			{

		GameData.Months = dis.readByte();
		GameData.Date = dis.readByte();
		GameData.Hour = dis.readByte();
		GameData.Minute = dis.readByte();
		// Draw.updataDay(month, day);
	}

	public static void exec_Resp_Accept_MapAround_Shop(DataInputStream dis)
			throws Exception 
			{
		SystemAPI.sleep(1000);
		Log.v("yz", "MapAround_Shop ="+System.currentTimeMillis());
		short focus_x = dis.readShort();
		short focus_y = dis.readShort();
		if (control.logic.get("Loading") != null) 
		{
			((Loading) control.logic.get("Loading")).destroy();
		}

		if (GameData.isResetMap) {

			GameData.isResetMap = false;

			GameData.mapIdIndex = GameData.mapIdIndexBack;

			GameData.setCityXY((short) focus_x, (short) focus_y, true);

			Message message = new Message();

			message.what = GameDefinition.Message_SetGameStatus;

			message.arg1 = GameDefinition.Game_Map;

			GameData.mhandler.sendMessage(message);
			MainMenu.mainmenu.closeMiddleDialog(-1);

		} else {
			GameData.setCityXY((short) focus_x, (short) focus_y, false);
		}

		GameData.frushOriginalFocus();

		for (int i = -GameData.ARRAY_LENTH / 2; i <= GameData.ARRAY_LENTH / 2; i++) {
			for (int j = -GameData.ARRAY_LENTH / 2; j <= GameData.ARRAY_LENTH / 2; j++) {
				int xy[] = GameData.moveCityXY(focus_x, focus_y, j, i);
				GameData.xyArray[(i + GameData.ARRAY_LENTH / 2)
						* GameData.ARRAY_LENTH + j + GameData.ARRAY_LENTH / 2][0] = GameData.centerX
						+ xy[0];
				GameData.xyArray[(i + GameData.ARRAY_LENTH / 2)
						* GameData.ARRAY_LENTH + j + GameData.ARRAY_LENTH / 2][1] = GameData.centerY
						+ xy[1];
			}
		}
		int focus_startX = focus_x - GameData.ARRAY_LENTH / 2;
		int focus_startY = focus_y - GameData.ARRAY_LENTH / 2;
		int curx;
		int cury;
		short size = dis.readShort();
		GameData.build = new BuildingSprite[size];
		for (int i = 0; i < size; i++) {
			GameData.build[i] = BuildingSprite.readBuildSprite(dis);
			curx = GameData.build[i].mb.cityX - focus_startX;
			cury = GameData.build[i].mb.cityY - focus_startY;
			if (curx + cury * GameData.ARRAY_LENTH >= 0
					&& curx + cury * GameData.ARRAY_LENTH < GameData.ARRAY_LENTH
							* GameData.ARRAY_LENTH) {
				GameData.build[i].setMapXY((short) GameData.xyArray[curx + cury
						* GameData.ARRAY_LENTH][0],
						(short) GameData.xyArray[curx + cury
								* GameData.ARRAY_LENTH][1]);
			}
		}

	}

	/**
	 * 
	 * @param 公益建筑模版包
	 * @throws Exception
	 */
	public static void exec_Resp_Accept_BuildingTemplate_Info(
			DataInputStream dis) throws Exception {
		byte size = dis.readByte();
		if (size < 0)
			return;
		GameData.community_num = 0;
		GameData.buildingId = new byte[size];// 建筑模板id
		GameData.buildingScale = new byte[size];// 建筑规模：1小，2中，3大，4超大，5巨型(建筑一般为4或5)
		GameData.buildingResId = new int[size];// 建筑图片资源id
		GameData.buildingName = new String[size];// 建筑模板名字
		GameData.buildingStaffNum = new short[size];// 建筑最大员工数
		GameData.buildingFees = new long[size];// 建筑费用
		GameData.weeklyFees = new long[size];// 建筑周维护费用
		GameData.flow_gain = new short[size];// 公益建筑的人流增益
		GameData.staff_lv = new byte[size];// 员工等级要求
		GameData.staff_ability = new short[size];// 员工能力要求
		GameData.staff_exp = new short[size];// 员工经验要求
		GameData.eff_area = new byte[size];// 影响范围（单位街区）
		GameData.accessLevel = new byte[size];// 访问级别(当角色的访问级别大于等于此级别是可以创建此公益建筑物)
		GameData.build_description = new String[size];
		GameData.build_area = new byte[size];// 占地面积（大块）
		for (int i = 0; i < size; i++) {
			GameData.buildingId[i] = dis.readByte();
			// #ifdef Debug
			// # System.out.println("建筑模板id ==" + i + "==" +
			// GameData.buildingId[i]);
			// #endif
			GameData.buildingScale[i] = dis.readByte();
			GameData.buildingResId[i] = dis.readInt();
			if (GameData.buildingResId[i] == 1448) {
				GameData.buildingResId[i] = 1388;
			}
			GameData.buildingName[i] = dis.readUTF();
			GameData.buildingStaffNum[i] = dis.readShort();
			GameData.buildingFees[i] = dis.readLong();
			GameData.weeklyFees[i] = dis.readLong();

			GameData.flow_gain[i] = dis.readShort();
			if (GameData.flow_gain[i] > 0)// add by zzx 判断当能增加利益的建筑材可以 创建
			{
				GameData.community_num++;
			}
			GameData.staff_lv[i] = dis.readByte();
			GameData.staff_ability[i] = dis.readShort();
			GameData.staff_exp[i] = dis.readShort();
			GameData.eff_area[i] = dis.readByte();
			byte size1 = dis.readByte();
			GameData.quality_id = new byte[size1];
			GameData.quality_lv = new byte[size1];
			for (int j = 0; j < size1; j++) {
				GameData.quality_id[j] = dis.readByte();
				GameData.quality_lv[j] = dis.readByte();
			}
			GameData.accessLevel[i] = dis.readByte();
			String s = dis.readUTF();
			GameData.build_description[i] = s;
			GameData.build_area[i] = dis.readByte();

			Log.i("Log", "buildingId" + GameData.buildingId[i]);
			Log.i("Log", "buildingName" + GameData.buildingName[i]);
		}

	}
	/**
	 * @author 公益建筑信息列表
	 * @param 公益建筑数目，根据size大小循环读取后面的公益建筑基本信息
	 * @throws Exception
	 */
	public static void  PublicBuildingList(DataInputStream dis) throws Exception
	{
		byte size = dis.readByte();
		GameData.corporation.build = new PublicBuilding[size];
		for(int i = 0; i< size;i++)
		{
			GameData.corporation.build[i] = readPublicBuild(dis);
		}
		//#ifdef Debug
		System.out.println("公益建筑信息列表 个数 = "+size);
		//#endif
	}
	
	public static void publicBuildingBaseInfo(DataInputStream dis)
	{
		/*try {
			PublicBuilding  pb =readPublicBuild(dis);
			GameData.addPublicBuilding(pb);
			Widget w = GameRun.getCurWidget()Game.instance.stackManager.currentWidget();
			if(w != null)
			{
				if( w instanceof CheckCommunity)
				{
					((CheckCommunity)w).upData(pb.trade_id,pb);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
    /**
     * @author 公益建筑基本信息
     * @param 
     * @param dis
     * @return
     * @throws Exception
     */
	private static PublicBuilding readPublicBuild(DataInputStream dis)throws Exception
	{
		PublicBuilding build=new PublicBuilding();
		
		build.id =dis.readLong();
		build.trade_id = dis.readByte();
		build.name = dis.readUTF();
		build.manager_id = dis.readLong();
		build.staffNum = dis.readShort();
		build.maxStaffNum = dis.readShort();
		build.flowGain = dis.readShort();
		build.scale = dis.readByte();
		build.map_id = dis.readShort();
		build.address_x = dis.readShort();
		build.address_y = dis.readShort();
		build.address_i = dis.readByte();
		build.build_id = dis.readLong();

		//#ifdef Debug
		Log.d("bug", "x==" + build.address_x+";  y="+build.address_y+";  id="+build.map_id);
		//#endif
		return build;
	}
	/**
	 * 店铺模板基本信息
	 * 
	 * @param dis
	 * @throws Exception
	 */
	public static void exec_Resp_Accept_ShopTemplate_Info(DataInputStream dis)
			throws Exception {

		 int[] temp = null;
		int hiddedNum = 0;

		byte professionNum = dis.readByte();
		GameData.professionId = new byte[professionNum];
		for (int i = 0; i < professionNum; i++) {
			GameData.professionId[i] = dis.readByte();
			GameData.shop_scale_size = dis.readByte();
			if (i == 0) {
				short num=(byte)(professionNum*GameData.shop_scale_size);
				GameData.shopRes=new int[num];
				GameData.shopName=new String[num];
				GameData.staff_Num=new short[num];
				GameData.createShop_Fees=new long[num];
				GameData.shopScale=new byte[num];
				GameData.shop_sprite = new int[num];
				
				GameData.shopTemplate_id = new long[num];
				GameData.shopTemplate_addition = new int[num];
				GameData.shopTemplate_hiddenresid = new int[num];
				GameData.shopTemplate_hidded = new byte[num];
				GameData.shopTemplate_point = new short[num];
				GameData.shopTemplate_info = new String[num];
				GameData.shopTemplate_condition = new String[num];
				GameData.shopTemplate_level = new byte[num];
				GameData.shopLittleRes = new int[num];
				

				temp = new int[num];
			}
			for (int j = 0; j < GameData.shop_scale_size; j++) {
				GameData.shopTemplate_id[i*GameData.shop_scale_size+j] = dis.readLong();
				
				GameData.shopScale[i*GameData.shop_scale_size+j]=dis.readByte();
				GameData.shopLittleRes [i*GameData.shop_scale_size+j] = dis.readInt();
				GameData.shopRes[i*GameData.shop_scale_size+j]=dis.readInt();
				GameData.shopName[i*GameData.shop_scale_size+j]=dis.readUTF();
				GameData.staff_Num[i*GameData.shop_scale_size+j]=dis.readShort();
				GameData.createShop_Fees[i*GameData.shop_scale_size+j]=dis.readLong();
				GameData.shop_sprite[i*GameData.shop_scale_size+j] = dis.readInt();

				GameData.shopTemplate_addition[i*GameData.shop_scale_size+j]=dis.readInt();
				GameData.shopTemplate_hiddenresid[i*GameData.shop_scale_size+j]=dis.readInt();
				GameData.shopTemplate_hidded[i*GameData.shop_scale_size+j]=dis.readByte();
				GameData.shopTemplate_point[i*GameData.shop_scale_size+j]=dis.readShort();
				GameData.shopTemplate_level[i*GameData.shop_scale_size+j]=dis.readByte();
				GameData.shopTemplate_condition[i*GameData.shop_scale_size+j]=dis.readUTF();
				GameData.shopTemplate_info[i*GameData.shop_scale_size+j]=dis.readUTF();
				
				System.out.println("状态是 ："+GameData.shopTemplate_info[i*GameData.shop_scale_size+j]);
				if(GameData.shopTemplate_hidded[i*GameData.shop_scale_size+j] == 1)
				{
					temp[hiddedNum++] = i*GameData.shop_scale_size+j; 
					//GameData.hiddenresid[hiddedNum++] = GameData.shopTemplate_hiddenresid[i*GameData.shop_scale_size+j];
					//Log.i("pealo", "shopTemplate_hiddenresid--------------------------->:"+GameData.shopTemplate_hiddenresid[i*GameData.shop_scale_size+j]);
				}
			}
		}
	/*	GameData.hiddedShopId = new long [hiddedNum];
		System.arraycopy(temp, 0, GameData.hiddedShopId, 0, hiddedNum);
		
		System.out.println(GameData.hiddedShopId[0]);
*/
		GameData.hiddedShopId = new int [hiddedNum][2];
	//	GameData.hiddenresid = new int [hiddedNum];
	//	Log.i("pealo", "GameData.hiddedShopId --------------------------->:"+GameData.hiddedShopId .length);	
		for(int i=0;i<hiddedNum;i++)
		{
			GameData.hiddedShopId[i][0] = temp[i];
			GameData.hiddedShopId[i][1] = 1;
		//	GameData.hiddenresid[i] =  temp[i];
		//	Log.i("pealo", "hiddenresid------->"+GameData.hiddenresid[GameData.hiddenresid[i]]);
		} 
	}

	/**
	 * 店铺基本信息
	 * 
	 * @param dis
	 */
	public static void exec_Resp_Shop_BaseInfo(DataInputStream dis) {

		if (GameData.corporation == null)
			GameData.corporation = new Corporation();
		Shop shop = Shop.readShop(dis);
		// for(int i=0;i<GameData.corporation.shop.length;i++)
		// {
		// if(GameData.corporation.shop[i].id==shop.id)
		// {
		// isNewCreateS = false ;
		// break ;
		// }
		// else if(GameData.corporation.shop[i]==null)
		// {
		// isNewCreateS = false;
		// break;
		// }
		// }
		GameData.addShop(shop);
		Log.i("Log", "AnalysisData---addShop");

		// if (GameData.corporation.shopCount == 2 && isNewCreateS &&
		// GameData.corporation.level == 2)
		// {
		// final ConformDialogEx cd = new ConformDialogEx("提示",
		// "你已经达到公司升级条件，可以去升级公司", ConformDialogEx.TWO_BUTTON);
		// cd.addActionListener(new ActionListener() {
		// public void actionPerformed(Object sender) {
		// if (sender == cd.buttonLeft) {
		// Game.instance.gameRun.initTabInfo(0);
		// cd.deactive();
		// } else if (sender == cd.buttonRight) {
		// cd.deactive();
		// }
		//
		// }
		//
		// });
		// cd.setBounds(25, (SysDef.SCREEN_H - 180) >> 1,
		// SysDef.SCREEN_W - 50, 180);
		// cd.active();
		// Game.instance.gameRun.frame.addWidget(cd);
		// }
		// else
		// {
		// if (!Tutorial.isInTutorial() && GameRun.getCurWidget() == null &&
		// isNewCreateS == true) {
		// newCreaateshop = shop;
		// Connection.shopId = shop.id;
		// final ConformDialogEx cd = new ConformDialogEx("提示",
		// "店铺创建完成，是否为店铺招聘员工", ConformDialogEx.TWO_BUTTON);
		// cd.addActionListener(new ActionListener() {
		// public void actionPerformed(Object sender) {
		// if (sender == cd.buttonLeft) {
		// Connection
		// .sendMessage(
		// GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
		// Connection
		// .getEmployStaffBatch(
		// (byte) 2,
		// new long[] { Connection.shopId }));
		// Connection.shopId = -1;
		// Connection.isNewCreateShop = true;
		// cd.deactive();
		// } else if (sender == cd.buttonRight) {
		// cd.deactive();
		// }
		//
		// }
		//
		// });
		// cd.setBounds(25, (SysDef.SCREEN_H - 180) >> 1,
		// SysDef.SCREEN_W - 50, 180);
		// cd.active();
		// Game.instance.gameRun.frame.addWidget(cd);
		// }
		//
		// }
		// if(GameRun.getCurWidget() instanceof TabWidget)
		// {
		// TabWidget tab= (TabWidget)GameRun.getCurWidget();
		// game.ui.Widget widget=tab.getTab(2);
		// if(widget instanceof CompanyShop)
		// {
		// ((CompanyShop)widget).updataCpShop(GameData.corporation);
		// }
		// }
		// if (GameRun.getCurWidget() != null
		// && (GameRun.getCurWidget() instanceof CompanyShop)) {
		// ((CompanyShop) (GameRun.getCurWidget()))
		// .updataCpShop(GameData.corporation);
		// }
		// if (GameRun.getCurWidget() != null
		// && (GameRun.getCurWidget() instanceof ShopList)) {
		// ((ShopList) (GameRun.getCurWidget()))
		// .updataShopList(GameData.corporation);
		// }
		// if (GameRun.getCurWidget() != null
		// && (GameRun.getCurWidget() instanceof ShopUI)) {
		// ((ShopUI) (GameRun.getCurWidget())).upDataShopUI(shop);
		// }

	}

	/**
	 * 添加地图建筑
	 * 
	 * @param dis
	 * @throws Exception
	 */
	public static void exec_Resp_Accept_Map_Shop_Add(DataInputStream dis)
			throws Exception {

		short focus_x = GameData.buildCityX;
		short focus_y = GameData.buildCityY;

		if (GameData.build == null) {
			GameData.build = new BuildingSprite[1];
		} else {
			BuildingSprite[] temp = GameData.build;
			GameData.build = new BuildingSprite[1 + temp.length];
			System.arraycopy(temp, 0, GameData.build, 0, temp.length);
		}

		int start = GameData.build.length - 1;
		GameData.build[start] = BuildingSprite.readBuildSprite(dis);
		int xy[] = GameData.moveCityXY(GameData.cityX, GameData.cityY,
				GameData.build[start].mb.cityX - GameData.cityX,
				GameData.build[start].mb.cityY - GameData.cityY);
		GameData.build[start].setMapXY((short) (GameData.centerX + xy[0]),
				(short) (GameData.centerY + (short) xy[1]));
	}

	/**
	 * 上行结果响应包
	 * 
	 * @param dis
	 */
	public static void exec_Resp_Packet_Result(DataInputStream dis) {
		try {

			short opCode = dis.readShort();
			byte opStatus = dis.readByte();// 0成功，1失败
			String str = dis.readUTF();
			if (str != null && !str.trim().equals("")) {
//				Message message = Message.obtain();
//				message.what = GameDefinition.Message_showToast;
//
//				Vector v = new Vector();
//				v.addElement(str);
//				message.obj = v;
//				Log.i("Log", "响应结果:" + v.toString());
//				GameData.mhandler.sendMessage(message);
				GameAPI.showToast(str);
			}

			// switch (opCode) {
			// case GameProtocol.Op_Create_Shop:
			//
			// Game.instance.initClewBox("系统消息", message,
			// opStatus == 0 ? false : true);
			// if (Tutorial.tutorialId == Tutorial.CREATESHOPWAIT) {// aa
			// // 教程处理
			// if (opStatus == 1) {
			// Game.instance.turorial.clearOption();
			// Tutorial.tutorialId = Tutorial.CREATESHOP;
			// // Tutorial.teach_index = 9;
			// //
			// Game.instance.turorial.addOption(Option.getOptionTip(UIManager.TEACH[Tutorial.teach_index],"第10"+UIManager.TEACH_STEP,DrawTipInfo.AIRCRAFT,546),Option.option_key_no);//飞机
			// Game.instance.turorial.addOption(Option
			// .getOptionArrow(
			// new int[] { UIManager.KEY_FIRE },
			// new int[] { UIManager.KEY_UP,
			// UIManager.KEY_DOWN,
			// UIManager.KEY_RIGHT,
			// UIManager.KEY_LEFT },
			// SysDef.SCREEN_W >> 1,
			// SysDef.SCREEN_H >> 1, 2),
			// Option.option_key_all);
			// }
			// }
			// break;
			// case GameProtocol.Purchase_Goods_Batch:
			// case GameProtocol.cofc_create:
			// // case GameProtocol.cofc_duty:
			// case GameProtocol.ACTIVE_MEET:
			// case GameProtocol.ACTIVE_PUBLICITY:
			// case GameProtocol.ACTIVE_TRAIN:
			// case GameProtocol.ACTIVE_VOLUNTEER:
			// case GameProtocol.STAFF_CONTINUE_CONTTACT:
			// case GameProtocol.STAFF_OP:
			// case GameProtocol.STAFF_SKILLLIST:
			// case GameProtocol.STAFF_EMPLOYMENT:
			// if (!Tutorial.isInTutorial()) {
			// if (message == null || !message.equals("")) {
			// Game.instance.initClewBox("系统消息", message, true);
			// }
			// }
			// break;
			// case GameProtocol.Company_Change_Req:
			// if(opStatus == 0){
			// if (message == null || !message.equals("")) {
			// Game.instance.initClewBox("系统消息", message, true);
			// }
			// }else{
			//
			// final ConformDialogEx cfmenu = new ConformDialogEx("系统提示",
			// message, ConformDialogEx.TWO_BUTTON);
			// cfmenu.buttonLeft.caption = "是";
			// cfmenu.buttonRight.caption = "否";
			// cfmenu.addActionListener(new ActionListener() {
			//
			// public void actionPerformed(Object cfmenuSender) {
			// if(cfmenuSender == cfmenu.buttonLeft)
			// {
			// changeCorporationName();
			// cfmenu.deactive();
			// }
			// else if(cfmenuSender == cfmenu.buttonRight)
			// {
			// cfmenu.deactive();
			// }
			// }
			// });
			// cfmenu.setBounds(25, (SysDef.SCREEN_H - 150)>>1, SysDef.SCREEN_W
			// - 50, 160);
			// cfmenu.cover();
			// GameRun.addWidget(cfmenu, true);
			// }
			// break;
			// }
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 客户端请求处理结束包
	 * 
	 * @param dis
	 */
	public static void exec_Pakcet_finish(DataInputStream dis) {
		try {
			short pid = dis.readShort();
			if (pid != GameProtocol.CONNECTION_SEND_AUTHLOGIN)// 用户登录包
			{
				// DrawLoading.destroy(pid);
				if (control.logic.get("Loading") != null) {
					((Loading) control.logic.get("Loading")).destroy(pid);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tip消息
	 * 
	 * @param dis
	 */
	public static void exec_Resp_TipMessage(DataInputStream dis) {

		try {
			String str = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// // if (!Tutorial.isInTutorial()) {
		// // String str = dis.readUTF();
		// // // Game.instance.initTip(str, 0, 0, SysDef.SCREEN_W,
		// // // SysDef.SCREEN_H,1,2);
		// // }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }// 文字内容
	}

	/**
	 * 二次确认包 文字框
	 * 
	 * @param dis
	 */
	public static void exec_Resp_TwoSureMsg(DataInputStream dis) {
		try {
			final int eventId = dis.readInt();
			String title = dis.readUTF();

			byte size = dis.readByte();
			String str = "";

			for (int i = 0; i < size; i++) {
				str += dis.readUTF() + "\n";
			}

			Connection.sendMessage(GameProtocol.CONNECTION_SEND_TWOSURE,
					ConstructData.getTwoSureMsg(eventId, (byte) 1));
		} catch (IOException e) {
			e.printStackTrace();
		}// 文字
	}

	/**
	 * 部门列表信息
	 */
	public static void exec_Resp_Accept_DepStructureinfo(DataInputStream dis)
			throws Exception {

		if (GameData.corporation == null)
			GameData.corporation = new Corporation();
		byte size = dis.readByte();
		if (GameData.corporation.department == null) {
			GameData.corporation.department = new Department[size];
		}
		for (int i = 0; i < size; i++) {
			if (GameData.corporation.department[i] == null) {
				GameData.corporation.department[i] = new Department();
			}
			GameData.corporation.department[i].id = dis.readLong();
			GameData.corporation.department[i].type = dis.readByte();

			GameData.corporation.department[i].name = dis.readUTF();

			GameData.corporation.department[i].level = dis.readByte();
			GameData.corporation.department[i].staffNum = dis.readShort();
			GameData.corporation.department[i].maxStaffNum = dis.readShort();
			GameData.corporation.department[i].manager_oneId = dis.readLong();// 部门主管id
			GameData.corporation.department[i].manager_twoId = dis.readLong();
			GameData.corporation.department[i].manager_threeId = dis.readLong();
			GameData.corporation.department[i].manager_fourId = dis.readLong();
			GameData.corporation.department[i].manager_five = dis.readLong();// 部门首席id
			GameData.corporation.department[i].morale = dis.readShort();
			GameData.corporation.department[i].maxMorale = dis.readShort();
			GameData.corporation.department[i].ability = dis.readShort();
			GameData.corporation.department[i].maxAbility = dis.readShort();
			GameData.corporation.department[i].faith = dis.readShort();
			GameData.corporation.department[i].maxFaith = dis.readShort();
			GameData.corporation.department[i].exp = dis.readShort();
			GameData.corporation.department[i].maxExp = dis.readShort();
			if(GameData.corporation.department[i].type==2)
			{
				byte num = dis.readByte();
				for(int j=0;j<num;j++)
				{
					GameData.planningNum[j][0]= dis.readByte();
					GameData.planningNum[j][1]= dis.readByte();
					GameData.planning_costmoney[j][0] = dis.readLong();
					GameData.planning_costpoint[j][0] = dis.readLong();
					Log.i("jiang", "金钱：--------"+GameData.planning_costmoney[j][0]+"\n 点数：-------"+GameData.planning_costpoint[j][0]);
				}
				
			}
			//zhouzhilong add
		/*	if(GameData.corporation.department[i].type == 2){
				byte actionSize = dis.readByte();
				GameData.corporation.department[i].strategties = new Strategy[actionSize];
				for(int j = 0; j<actionSize; j++){
					GameData.corporation.department[i].strategties[i].id =(byte) j;
					GameData.corporation.department[i].strategties[i].actionCount = dis.readByte();		
					GameData.corporation.department[i].strategties[i].maxActionCount = dis.readByte();
				}
			}*/
			

		}

		// if (GameRun.getCurWidget() instanceof TabWidget) {
		// TabWidget tab = (TabWidget) GameRun.getCurWidget();
		// game.ui.Widget widget = tab.getTab(1);
		// if (widget instanceof CompanyArchs) {
		// ((CompanyArchs) widget).updataArch(GameData.corporation,
		// GameData.player);
		//
		// }
		// }

		// GameLogin.time[3][1] = System.currentTimeMillis();
		// GameLogin.time[3][2] = GameLogin.time[3][1] -GameLogin.time[3][0] ;

	}
	
	/**
	 * 部门属性更新包
	 * @param dis
	 */
	public static void exec_Resp_DepartmentUpdata(DataInputStream dis)
	{
		try {
			long id=dis.readLong();
			byte size=dis.readByte();
			byte type;
			int value=0;
			long valuemid = 0;
			Department dep=GameData.getDepartment(id);
			for(int i=0;i<size;i++)
			{
				type=dis.readByte();
				 
				if(type == 1)//士气
				{
					dep.morale=(short)dis.readInt();
				}
				if(type == 2)//主管
				{
					 
					dep.manager_oneId = dis.readLong();
					int plen = dep.employees.length ;
				}
				else if(type == 3)//助理
				{
					dep.manager_twoId = dis.readLong();
				}
				else if(type == 4)//经理
				{
					dep.manager_threeId = dis.readLong();
				}
				else if(type == 5)//总监
				{
					dep.manager_fourId = dis.readLong();
				}
				else if(type == 6)//首席
				{
					dep.manager_five = dis.readLong();
				}
				byte num = dis.readByte();
				for(int j = 0;j<num;j++)
				{
					byte planningId = dis.readByte();
					GameData.planningNum[planningId-1][0] = dis.readByte();
					GameData.planningNum[planningId-1][1] = dis.readByte();
					GameData.planning_costmoney[planningId-1][0] = dis.readLong();
					GameData.planning_costpoint[planningId-1][0] = dis.readLong();
				}
				 
			}
			if (PlanningDeptActivity.mContext != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 13;// 小博士数据修改
				//message.arg2 = 1;

				GameData.mhandler.sendMessage(message);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/*	for(int i = 0 ; i < GameRun.widgetControl.size(); i++)
		{
			if(GameRun.widgetControl.elementAt(i) instanceof TabWidget)
			{
				TabWidget tab=	(TabWidget)GameRun.widgetControl.elementAt(i);
				game.ui.Widget widget=tab.getTab(1);
				if(widget instanceof CompanyArchs)
				{
					((CompanyArchs)widget).updataArch(GameData.corporation,GameData.player);
				
				}
			}
		}*/
		 
	}

	public static void exec_Resp_Shop_List_BaseInfo(DataInputStream dis) {
		
//		try {
//			/**add by zzx */
//			byte type = dis.readByte();
//			if(type == 0)
//			{
//				if (GameData.corporation == null)
//					GameData.corporation = new Corporation();
//			}
//			/**by zzx */
//			byte size = dis.readByte();			
//			GameData.corporation.shop = new Shop[size];
//			
//			for (int i = 0; i < size; i++) 
//			{
//				GameData.corporation.shop[i] = Shop.readShop(dis);
//				Log.i("LOG", GameData.corporation.shop[i].simpleName);
//			}
//			/**add by zzx */
//			if(type == 1)
////			if (PlanningDeptShoplistActivity.mContext == null)
//			{
//				Message message = Message.obtain();
//
//				message.what = GameDefinition.Message_changeActivity;
//				Vector v = new Vector();
//				v.addElement(PlanningDeptShoplistActivity.class);
//				message.obj = v;
//				message.arg1 = 11;
//				GameData.mhandler.sendMessage(message);
//			}
//			/**by zzx */
//			} catch (IOException e) {
//		 
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
		
		
		/**add by yz*/
		byte type =-1;
		try
		{
			type = dis.readByte();
			if(type == 0)
			{
				if (GameData.corporation == null)
				{
					GameData.corporation = new Corporation();
				}
				byte size = dis.readByte();	
				
				GameData.corporation.shop = new Shop[size];
				
				for (int i = 0; i < size; i++) 
				{
					GameData.corporation.shop[i] = Shop.readShop(dis);
					Log.i("LOG", GameData.corporation.shop[i].simpleName);
				}
			}
			else if(type == 1 || type == 2) 
			{
				GameData.otherCorporation = null;
				
				GameData.otherCorporation = new Corporation();
				
				byte size = dis.readByte();	
				
				GameData.otherCorporation.shop = new Shop[size];
				
				for(int i =0; i < size; i++)
				{
					GameData.otherCorporation.shop[i] = Shop.readShop(dis);
				}
				
				if(type == 1)
				{
					
				}
				else if(type == 2)
				{
					Message message = Message.obtain();
					
					message.what = GameDefinition.Message_changeActivity;
					Vector v = new Vector();
					v.addElement(SimpleShopListActivity.class);
					message.obj = v;
					message.arg1 = 22;
					Bundle b = new Bundle();
					b.putInt("type", SimpleShopListActivity.TYPE_SOCIAL);
					GameData.mhandler.sendMessage(message);
				}
			}
		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 加载商场物品列表
	 * 
	 * @param dis
	 */
	public synchronized static void exec_Props_mall(DataInputStream dis) {
		try {
			int props_size = dis.readShort();
			GameData.propsMall = new Props[props_size];
			for (int i = 0; i < props_size; i++) {
				GameData.propsMall[i] = new Props();
				GameData.propsMall[i].id = dis.readLong();
				GameData.propsMall[i].price = dis.readInt();
				GameData.propsMall[i].discount = dis.readByte();
				GameData.propsMall[i].icon = dis.readShort();
				GameData.propsMall[i].name = dis.readUTF();
				GameData.propsMall[i].desc = dis.readUTF();
			}
			if (StoreActivity.mContext == null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeActivity;
				Vector v = new Vector();
				v.addElement(StoreActivity.class);
				message.obj = v;
				message.arg1 = 0;
				GameData.mhandler.sendMessage(message);
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 加载道具列表
	 * 
	 * @param dis
	 */
	public static void exec_Props_list(DataInputStream dis) {
		try {
			int props_size = dis.readShort();
			GameData.props = new Props[props_size];
			for (int i = 0; i < props_size; i++) {
				GameData.props[i] = new Props();
				GameData.props[i].id = dis.readLong();
				// zhouzhilong add
				GameData.props[i].templateId = dis.readLong();
				GameData.props[i].type = dis.readByte();
				GameData.props[i].count = dis.readShort();
				GameData.props[i].icon = dis.readShort();
				GameData.props[i].name = dis.readUTF();
				GameData.props[i].desc = dis.readUTF();
				GameData.props[i].subsize = dis.readByte();
				
				Log.i("juj", "props[i].icon ="+GameData.props[i].icon );
				
				GameData.props[i].targetTypeld = new byte[GameData.props[i].subsize];

				for (int j = 0; j < GameData.props[i].subsize; j++) {

					GameData.props[i].targetTypeld[j] = dis.readByte();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		if (MainActivity.mContext.getTopActivty() == StoreActivity.mContext) 
		{
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 2;// 公司数据修改

			GameData.mhandler.sendMessage(message);
		}
	}

	/**
	 * 加载道具更新
	 * 
	 * @param dis
	 */
	public static void exec_Props_update(DataInputStream dis) {
		try {
			long templateid = 0;
			byte type = 0;
			short count = 0;
			short icon = 0;
			String name = "";
			String desc = "";
			byte subsize = 0;
			byte[] targetTypeld = null;

			byte opType = dis.readByte();
			long id = dis.readLong();

			switch (opType) {
			case 1:
				templateid = dis.readLong();
				type = dis.readByte();
				count = dis.readShort();
				Log.i("ppqq", "analysis.count======"+count);
				icon = dis.readShort();
				name = dis.readUTF();
				desc = dis.readUTF();
				subsize = dis.readByte();
				targetTypeld = new byte[subsize];
				for (int i = 0; i < subsize; i++) {
					targetTypeld[i] = dis.readByte();
				}
				break;
			case 2:
				break;
			case 3:
				count = dis.readShort();
				break;
			}
			GameData.updateProps(opType, id, count, icon, name, desc, subsize,
					targetTypeld);
		} catch (Exception e) {
			e.printStackTrace();

		}

		if (MainActivity.mContext.getTopActivty() == StoreActivity.mContext) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 2;// 公司数据修改

			GameData.mhandler.sendMessage(message);
		}
	}

	/**
	 * 使用道具
	 */
	public static byte[] useProps(byte ontype, long iteml, byte targetTypeId,
			long targetld, short count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {

			dos = new DataOutputStream(baos);
			switch (ontype) {
			case 1:
				dos.writeByte(ontype);
				dos.writeLong(iteml);
				dos.writeByte(targetTypeId);
				dos.writeLong(targetld);
				break;
			case 2:
				dos.writeByte(ontype);
				dos.writeLong(iteml);
				dos.writeShort(count);
				break;
			case 3:
				dos.writeByte(ontype);
				break;
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 员工列表信息
	 * 
	 * @param dis
	 */
	public static void exec_Resp_Staff_List_Info(DataInputStream dis) {
		try {
			// 0刷新1新增2变更3删除
			byte type = dis.readByte();
			short size = dis.readShort();

			if (type == 0)// 刷新
			{
				GameData.clearEmployee();
			}

			for (int i = 0; i < size; i++) {

				Employee emp = Employee.readEmployee(dis);
				if (type == 3) {
					GameData.deleteEmployee(emp.id);
				} else {
					GameData.addEmployee(emp, type);

				}
			}

			// 更新数据
			// if (GameRun.getCurWidget() instanceof ShopUI) {
			// ShopUI UI = (ShopUI) GameRun.getCurWidget();
			//
			// if (UI.shop != null) {
			// for (int i = 0; i < GameData.corporation.shop.length; i++) {
			// if (UI.shop.id == GameData.corporation.shop[i].id) {
			// UI.upDataShopUI(GameData.corporation.shop[i]);
			// }
			// }
			// }
			// }
			//
			// if (GameRun.recEmp != null) {
			// GameRun.recEmp.updateEmployeeList();
			// }
			//
			// if (GameRun.getCurWidget() != null
			// && (GameRun.getCurWidget() instanceof RecruitmentEmp)) {
			// ((RecruitmentEmp) (GameRun.getCurWidget()))
			// .updateEmployeeList();
			// }

			// 0人事部;1公关部;2策划部;3财务部;

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Widget widget = GameRun.getCurWidget();
		//
		// if (widget != null && widget instanceof AppointEmployeesWindow) {
		// AppointEmployeesWindow aew = (AppointEmployeesWindow) widget;
		// aew.clearItemsInPage(0);
		// aew.clearItemsInPage(1);
		//
		// for (int i = 0; i < GameData.corporation.employee.length; i++) {
		// Employee emp = GameData.corporation.employee[i];
		//
		// if (emp.type == 6)// 空闲
		// {
		// aew
		// .addItemInPage(0, new AppointmentEmployeeItem(null,
		// emp));
		// } else if (emp.type <= 4)// 店铺
		// {
		// if (aew.shop == null) {
		// if (emp.departName.equals(aew.department.name)) {
		// aew.addItemInPage(1, new AppointmentEmployeeItem(
		// null, emp));
		// }
		// } else {
		// if (emp.departName.equals(aew.shop.simpleName)) {
		// aew.addItemInPage(1, new AppointmentEmployeeItem(
		// null, emp));
		// }
		// }
		// }
		// }
		// }
		// if (Connection.isNewCreateShop) {
		// Connection.isNewCreateShop = false;
		// final ConformDialogEx cd = new ConformDialogEx("提示",
		// "恭喜您招聘到所需员工，给您新开张的店铺进货吧。", ConformDialogEx.TWO_BUTTON);
		// cd.addActionListener(new ActionListener() {
		// public void actionPerformed(Object sender) {
		// if (sender == cd.buttonLeft) {
		// PurcherCargo pc = new PurcherCargo(PurcherCargo.SINGLE);
		// pc.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
		// pc.updataData(newCreaateshop);
		// GameRun.addWidget(pc, true);
		// newCreaateshop = null;
		// cd.deactive();
		// } else if (sender == cd.buttonRight) {
		// cd.deactive();
		// }
		//
		// }
		//
		// });
		// cd.setBounds(25, (SysDef.SCREEN_H - 180) >> 1,
		// SysDef.SCREEN_W - 50, 180);
		// cd.active();
		// Game.instance.gameRun.frame.addWidget(cd);
		// }

		if (GameData.corporation.listener != null) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 0;// 公司数据修改

			GameData.mhandler.sendMessage(message);

		}
	}

	public static void exec_Resp_SkillUpdate(DataInputStream dis) {
		try {
			long employeeId = dis.readLong();
			byte opType = dis.readByte();
			byte skillType = dis.readByte();
			short skillId = dis.readShort();

			byte skillLevel = 0;
			String skillName = "";
			String skillDesp = "";

			if (opType != 2)// 不是删除
			{
				skillLevel = dis.readByte();
				skillName = dis.readUTF();
				skillDesp = dis.readUTF();
			}

			Employee e = null;

			e = GameData.getEmployeeById1(employeeId);

			if (e != null) {
				if (skillType == 1) // 是技能
				{
					Skill sk = null;

					for (int i = 0; i < e.skill.length; i++) {
						if (e.skill[i].id == skillId) {
							sk = e.skill[i];
						}
					}

					switch (opType) {
					case 1:// 增加

						Skill ss[] = new Skill[e.skill.length + 1];
						int i = 0;
						for (i = 0; i < e.skill.length; i++) {
							ss[i] = e.skill[i];
						}

						ss[i] = new Skill();

						ss[i].id = skillId;
						ss[i].level = skillLevel;
						ss[i].name = skillName;
						ss[i].desp = skillDesp;

						e.skill = ss;

						break;
					case 2:// 删除

						if (sk != null) {
							if (e.skill.length > 0) {
								Skill[] s = new Skill[e.skill.length - 1];

								int tmp = 0;
								for (i = 0; i < e.skill.length; i++) {
									if (e.skill[i] != sk) {
										s[tmp++] = e.skill[i];
									}
								}

								e.skill = s;
							} else {
								// #ifdef Debug
								// # System.out.println("删除技能失败！没有可供删除的技能");
								// #endif
							}
						} else {
							// #ifdef Debug
							// # System.out.println("删除技能失败！无法找到该技能:" +
							// skillId);
							// #endif
						}
						break;
					case 3:// 更新

						if (sk != null) {
							sk.name = skillName;
							sk.level = skillLevel;
						} else {

						}
						break;
					}
				} else if (skillType == 2)// 是素质
				{
					Quality q = null;

					for (int i = 0; i < e.quality.length; i++) {
						if (e.quality[i].id == skillId) {
							q = e.quality[i];
						}
					}

					switch (opType) {
					case 1:// 增加

						Quality qq[] = new Quality[e.quality.length + 1];
						int i = 0;
						for (i = 0; i < e.quality.length; i++) {
							qq[i] = e.quality[i];
						}

						qq[i] = new Quality();

						qq[i].id = skillId;
						qq[i].level = skillLevel;
						qq[i].name = skillName;
						qq[i].desp = skillDesp;

						e.quality = qq;

						break;
					case 2:// 删除

						if (q != null) {
							if (e.quality.length > 0) {
								Quality[] s = new Quality[e.quality.length - 1];

								int tmp = 0;
								for (i = 0; i < e.quality.length; i++) {
									if (e.quality[i] != q) {
										s[tmp++] = e.quality[i];
									}
								}

								e.quality = s;
							} else {
								// #ifdef Debug
								// # System.out.println("删除技能失败！没有可供删除的技能");
								// #endif
							}
						} else {
							// #ifdef Debug
							// # System.out.println("删除技能失败！无法找到该技能:" +
							// skillId);
							// #endif
						}
						break;
					case 3:// 更新

						if (q != null) {
							q.name = skillName;
							q.level = skillLevel;
						} else {
							// #ifdef Debug
							// # System.out.println("更新技能失败！无法找到该技能:" +
							// skillId);
							// #endif
						}
						break;
					}
				}// ----------------------------------------------------------------------------

				if (e.listener != null) {
					Message message = Message.obtain();

					message.what = GameDefinition.Message_changeData;

					message.arg1 = 1;// 公司数据修改

					message.obj = e;

					GameData.mhandler.sendMessage(message);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public static void exec_Resp_SkillList(DataInputStream dis) {
		try {
			long type = dis.readByte();
			byte size = dis.readByte();

			if (type == 1) {
				GameData.skills = new Skill[size];

				for (int i = 0; i < size; i++) {
					GameData.skills[i] = new Skill();
					GameData.skills[i].id = dis.readShort();
					GameData.skills[i].name = dis.readUTF();
					GameData.skills[i].desp = dis.readUTF();
					// zhouzhilong add
					GameData.skills[i].itemTemplateId = dis.readLong();
				}
			} else {
				GameData.qualities = new Quality[size];

				for (int i = 0; i < size; i++) {
					GameData.qualities[i] = new Quality();
					GameData.qualities[i].id = dis.readShort();
					GameData.qualities[i].name = dis.readUTF();
					GameData.qualities[i].desp = dis.readUTF();
					// zhouzhilong add
					GameData.qualities[i].itemTemplateId = dis.readLong();
					Log.i("Log3", "itemTemplateId:=="
							+ GameData.qualities[i].itemTemplateId);
					Log.v(TAG, "quality id =" + GameData.qualities[i].id
							+ "name=" + GameData.qualities[i].name);
				}
			}

			// System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public static void exec_Resp_SkillList(DataInputStream dis) {
			try {
				long type = dis.readByte();
				byte size = dis.readByte();

				if (type == 1) {
					GameData.skills = new Skill[size];

					for (int i = 0; i < size; i++) {
						GameData.skills[i] = new Skill();
						GameData.skills[i].id = dis.readShort();
						GameData.skills[i].name = dis.readUTF();
						GameData.skills[i].desp = dis.readUTF();
						// zhouzhilong add
						GameData.skills[i].itemTemplateId = dis.readLong();
					}
				} else {
					GameData.qualities = new Quality[size];

					for (int i = 0; i < size; i++) {
						GameData.qualities[i] = new Quality();
						GameData.qualities[i].id = dis.readShort();
						GameData.qualities[i].name = dis.readUTF();
						GameData.qualities[i].desp = dis.readUTF();
						// zhouzhilong add
						GameData.qualities[i].itemTemplateId = dis.readLong();
						Log.i("Log3", "itemTemplateId:=="
								+ GameData.qualities[i].itemTemplateId);
						Log.v(TAG, "quality id =" + GameData.qualities[i].id
								+ "name=" + GameData.qualities[i].name);
					}
				}

				// System.out.println("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	/**
	 * 新手提示触发包
	 * 
	 * @param dis
	 */
	public static void exec_Resp_Multiple_Message(DataInputStream dis) {
		try {
			// Log.i("Log", "新手触发bao----");
			int id = dis.readByte();// 触发新手提示的id

			// Log.i("Log", "新手触发bao----id:--"+id);

			Log.i("Log", "新手触发bao----id:--" + id);
			// 新手触发
			//jiangxujin(delete)
		/*	if (1 == id) {
				Log.i("Log", "新手线程启动----");
				GameData.isFreshMan = true;
				FreshManLead.isBank = true;
				// 启动新手引导线程
				new Thread(new FreshManLead()).start();
			}*/

			String str = dis.readUTF();// 文字内容
			GameAPI.showToast(str);
		//	jiangxujin(delete)  yangzheng add 
//			Message message = Message.obtain();
//			message.what = GameDefinition.Message_showToast;
//
//			Vector v = new Vector();
//			v.addElement(str);
//			message.obj = v;
//
//			GameData.mhandler.sendMessage(message);

			// if (!Tutorial.isInTutorial())
			// {
			// if (id == 1)// 新手引导
			// {
			// Game.instance.turorial.tutorialId=Tutorial.GETTASK;
			// Tutorial.teach_index = 0;
			// Game.instance.turorial.addOption(Option.getOptionTip(UIManager.TEACH[Tutorial.teach_index],
			// "第1" + UIManager.TEACH_STEP, DrawTipInfo.FLASE,
			// 562),Option.option_key_no);
			//
			// Tutorial.teach_index = 1;
			// Game.instance.turorial.addOption(Option.getOptionTip(UIManager.TEACH[Tutorial.teach_index],
			// "第2" + UIManager.TEACH_STEP, 1, 1),Option.option_key_no);
			// Game.instance.turorial.addOption(Option.getOptionArrow(new
			// int[]{UIManager.KEY_SOFTKEY1}, null, 35, SysDef.SCREEN_H - 40,
			// 2),Option.option_key_all_Left);
			//
			// /*Vector v = new Vector();
			//
			// Answer answer6 = new Answer(null);
			// answer6.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer6.setInt("评价你的创业潜力",
			// "究竟您是否适合创业呢？请跟着我们做个小测试，由此来正确客观的认识自己吧。",UseResListNew.IMAGE_1037);
			// v.addElement(answer6);
			//
			// Answer answer1 = new Answer(null);
			// answer1.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer1.setInt(0, "评价你的创业潜力", "你认为人生最重要的", new String[] {
			// "挫折少", "经历多" }, (byte) 0);
			// v.addElement(answer1);
			//
			// Answer answer2 = new Answer(null);
			// answer2.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer2.setInt(0, "评价你的创业潜力", "你认为哪一种情况更可怕", new String[] {
			// "不知道明天怎么样", "每天都一样" }, (byte) 1);
			// v.addElement(answer2);
			// Game.instance.turorial.addOption(Option.getAnswer(v));
			//
			// Answer answer3 = new Answer(null);
			// answer3.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer3.setInt(0, "评价你的创业潜力", "你认为哪种情况更容易", new String[] {
			// "遵守别人的规则", "自己定规则" }, (byte) 2);
			// v.addElement(answer3);
			// Game.instance.turorial.addOption(Option.getAnswer(v));
			//
			// Answer answer4 = new Answer(null);
			// answer4.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer4.setInt(0, "评价你的创业潜力", "你遇到问题时，更倾向于说", new String[] {
			// "这不是我的责任", "我承担全部责任" }, (byte) 3);
			// v.addElement(answer4);
			// Game.instance.turorial.addOption(Option.getAnswer(v));
			//
			// Answer answer5 = new Answer(null);
			// answer5.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// answer5.setInt(0, "评价你的创业潜力", "你更喜欢哪一个职位", new String[] {
			// "大公司高层", "小公司老板" }, (byte) 4);
			// v.addElement(answer5);
			// Game.instance.turorial.addOption(Option.getAnswer(v));
			// */
			//
			// } else if (id == 2)// 休息
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.RIBBON, 710),
			// Option.option_key_no);
			// } else if (id == 3)// 培训
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.RIBBON, 698),
			// Option.option_key_no);
			// } else if (id == 4)// 进货
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.PURCHASE, 713),
			// Option.option_key_no);
			// } else if (id == 5)// 公司升级
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.UPDATA, 702, 765, 717),
			// Option.option_key_no);
			// } else if (id == 6)// 商会升级
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.UPDATA, 708, 766, 717),
			// Option.option_key_no);
			// } else if (id == 7)// 招聘员工
			// {
			// Game.instance.turorial.addOption(Option.getOptionTip(str,
			// null, DrawTipInfo.RIBBON, 569),
			// Option.option_key_no);
			// }
			// } else// 新手提示状态
			// {
			// if (id == 4)// 进货
			// {
			// if (Tutorial.tutorialId == Tutorial.PURERCARGO) {
			// Tutorial.teach_index = 16;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第17"
			// + UIManager.TEACH_STEP,
			// DrawTipInfo.PURCHASE, 713),
			// Option.option_key_no);
			// Game.instance.turorial.addOption(Option.getOptionArrow(
			// new int[] { UIManager.KEY_SOFTKEY1 }, null, 35,
			// SysDef.SCREEN_H - 40, 2),
			// Option.option_key_all_Left);
			// Tutorial.tutorialId = Tutorial.RECRUITMENT;
			// }
			// } else if (id == 7)// 招聘员工
			// {
			// if (Tutorial.tutorialId == Tutorial.RECRUITMENT) {
			// Tutorial.teach_index = 17;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// str + UIManager.TEACH[Tutorial.teach_index],
			// "第18" + UIManager.TEACH_STEP,
			// DrawTipInfo.RIBBON, 569), Option.option_key_no);
			// Game.instance.turorial.addOption(Option.getOptionArrow(
			// new int[] { UIManager.KEY_SOFTKEY2 },
			// new int[] { UIManager.KEY_UP,
			// UIManager.KEY_DOWN },
			// SysDef.SCREEN_W - 35, SysDef.SCREEN_H - 40, 2),
			// Option.option_key_Right);
			// Tutorial.teach_index = 18;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第19"
			// + UIManager.TEACH_STEP, 1, 1),
			// Option.option_key_no);
			// Tutorial.can_next_index = true;
			// Game.instance.turorial.addOption(Option.getOptionArrow(
			// new int[] { UIManager.KEY_SOFTKEY1 },
			// new int[] { UIManager.KEY_UP,
			// UIManager.KEY_DOWN }, 35,
			// SysDef.SCREEN_H - 40, 2),
			// Option.option_key_Left);
			// Tutorial.tutorialId = Tutorial.FINISHTASK;
			// }
			// }
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新员工属性
	 * 
	 * @param dis
	 */
	public static void exec_Resp_EmpUpData(DataInputStream dis) {
		try {
			long id = dis.readLong();
			byte size = dis.readByte();

			int employeeId = -1;

			for (int i = 0; i < GameData.corporation.employee.length; i++) {
				if (GameData.corporation.employee[i].id == id) {
					employeeId = i;
					break;
				}
			}

			if (employeeId == -1)
				return;
			for (int i = 0; i < size; i++) {
				byte attribute_type = dis.readByte();
				if (attribute_type == 10) {
					long attribute_value = dis.readLong();
					GameData.corporation.employee[employeeId].compact = attribute_value;// 合同到期时间
				} else if (attribute_type == 13)// 更新员工的部门
				{

					long attribute_value = dis.readLong();

					long oldDepartment = GameData.corporation.employee[employeeId].department;

					// System.out.println("attribute_value 新部门==" +
					// attribute_value);

					GameData.corporation.employee[employeeId].department = attribute_value;// 员工所在部门

					// 更新部门的员工
					Branch b = GameData
							.getBranch(GameData.corporation.employee[employeeId].department);
					if (b != null) {

						if (b instanceof PublicBuilding) {
							((PublicBuilding) b).manager_id = GameData.corporation.employee[i].id;
						} else {
							b.employees = GameData
									.getBranchEmployee(GameData.corporation.employee[employeeId].department);
							b.staffNum = (short) b.employees.length;
						}
					}

					// 更新老部门的员工
					b = GameData.getBranch(oldDepartment);
					if (b != null) {
						if (b instanceof PublicBuilding) {
							((PublicBuilding) b).manager_id = 0;
						} else {
							b.employees = GameData
									.getBranchEmployee(oldDepartment);
							b.staffNum = (short) b.employees.length;
						}
					}

					// 更新部门名称
					for (int j = 0; j < GameData.corporation.department.length; j++) {
						if (GameData.corporation.department[j].id == GameData.corporation.employee[employeeId].department) {
							GameData.corporation.employee[employeeId].departName = GameData.corporation.department[j].name;
							break;
						}
					}

					for (int j = 0; j < GameData.corporation.build.length; j++) {
						if (GameData.corporation.build[j].id == GameData.corporation.employee[employeeId].department) {
							GameData.corporation.employee[employeeId].departName = GameData.corporation.build[j].name;
						}
					}

					for (int j = 0; j < GameData.corporation.shop.length; j++) {
						if (GameData.corporation.shop[j].id == GameData.corporation.employee[employeeId].department) {
							GameData.corporation.employee[employeeId].departName = GameData.corporation.shop[j].name;
						}
					}

					if (GameData.corporation.employee[employeeId].department == 0) {
						GameData.corporation.employee[employeeId].departName = "空闲";
					}
				} else {
					int attribute_value = dis.readInt();
					if (GameData.corporation.employee[employeeId] != null) {
						switch (attribute_type) {
						case 1:
							GameData.corporation.employee[employeeId].level = (short) attribute_value;
							break;
						case 2:
							GameData.corporation.employee[employeeId].experence = attribute_value;
							break;
						case 3:
							GameData.corporation.employee[employeeId].strength = attribute_value;
							break;
						case 4:
							GameData.corporation.employee[employeeId].loyalty = attribute_value;
							break;
						case 5:
							GameData.corporation.employee[employeeId].morale = attribute_value;
							break;
						case 6:
							GameData.corporation.employee[employeeId].ability = attribute_value;
							break;
						case 7:// 人品
							GameData.corporation.employee[employeeId].makeUp = attribute_value;
							break;
						case 8:
							GameData.corporation.employee[employeeId].duty = (short) attribute_value;
							for (int pi = 0; pi < GameData.corporation.department.length; pi++) {
								if (GameData.corporation.department[pi].id == GameData.corporation.employee[employeeId].department) {
									for (int pii = 0; pii < GameData.corporation.department[pi].employees.length; pii++) {
										if (GameData.corporation.department[pi].employees[pii].id == GameData.corporation.employee[employeeId].id) {
											GameData.corporation.department[pi].employees[pii].duty = (short) attribute_value;
											break;
										}
									}
								}
							}
							// if
							// (GameRun.getWidget(GameData.tabCompanyArchsStr)
							// != null) {
							// ((CompanyArchs) ((TabWidget) (GameRun
							// .getWidget(GameData.tabCompanyArchsStr)))
							// .getTab(1)).updataArch(
							// GameData.corporation, GameData.player);
							// }
							break;
						case 9:// 周薪
							GameData.corporation.employee[employeeId].salary = attribute_value;
							break;
						case 11:// 员工体力上限
							GameData.corporation.employee[employeeId].maxstrength = attribute_value;
							break;
						case 12:// 员工类型
							GameData.corporation.employee[employeeId].type = attribute_value;
							break;

						}
					}
				}
			}

			if (GameData.corporation.listener != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 0;// 公司数据修改

				GameData.mhandler.sendMessage(message);
			}
			if(DepartmentActivity.mContext != null)
			{
				Message message = Message.obtain();
				
				message.what = GameDefinition.Message_changeData;
				message.arg1 = 21;//部门数据更新
				GameData.mhandler.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void shop_Recruit_List(DataInputStream dis) throws Exception {
		byte state = dis.readByte();
		// 0为店铺进入//1为好友2为竞争者界面进入;
		byte size = dis.readByte();
		if (size <= 0) {
			String str = null;
			if (state == 0) {

				str = MainActivity.resources
						.getString(R.string.analysisdata_shop_recruit_list_element1);
			} 
			else 
			{
				str = MainActivity.resources
						.getString(R.string.analysisdata_shop_recruit_list_element2);
			}
			GameAPI.showToast(str);
			return;
		}
		GameData.recruitShop = new Shop[size];
		for (int i = 0; i < GameData.recruitShop.length; i++) {
			GameData.recruitShop[i] = new Shop();
			GameData.recruitShop[i].buildingId = dis.readLong();
			GameData.recruitShop[i].simpleName = dis.readUTF();
			GameData.recruitShop[i].associationName = dis.readUTF();
			GameData.recruitShop[i].flowGain = dis.readInt();
			GameData.recruitShop[i].cityX = dis.readShort();
			GameData.recruitShop[i].cityY = dis.readShort();
			GameData.recruitShop[i].shopKeeper = dis.readUTF();
			GameData.recruitShop[i].competitor = dis.readByte();
			GameData.recruitShop[i].competitorNum = dis.readInt();
			GameData.recruitShop[i].level = dis.readByte();
			GameData.recruitShop[i].trade_id = dis.readByte();
			GameData.recruitShop[i].map_id = dis.readShort();
			GameData.recruitShop[i].scale = dis.readByte();
		}
		
		if(ShopListActivity.mContext != null)
		{
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeActivity;

			Vector v = new Vector();

			v.addElement(RecruitActivity.class);

			Bundle bundle = new Bundle();

			bundle.putLong("shopId", GameData.RecruitedShopId);

			bundle.putByte("type", (byte) 0);
			v.addElement(bundle);
			message.obj = v;
			message.setData(bundle);
			message.arg1 = 21;
			GameData.mhandler.sendMessage(message);
		}
		if(shopInfo2Activity.mContext != null)
		{
//			shopInfo2Activity.mContext.showDialog_zhaolan(null);
			
			Message message = Message.obtain();
			message.what = GameDefinition.Message_show_dailog;
			Vector v = new Vector();
			v.addElement(shopInfo2Activity.class);
			Bundle bundle = new Bundle();
			bundle.putLong("shopId", GameData.RecruitedShopId);

			bundle.putByte("type", (byte) 0);
			v.addElement(bundle);
			message.obj = v;
			message.setData(bundle);
			
			message.arg1 = 5;
			GameData.mhandler.sendMessage(message);
			
			
		}
		if (ShopInfoActivity.mContext != null) 
		{
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeActivity;

			Vector v = new Vector();

			v.addElement(RecruitActivity.class);

			Bundle bundle = new Bundle();

			bundle.putLong("shopId", GameData.RecruitedShopId);

			bundle.putByte("type", (byte) 0);
			v.addElement(bundle);
			message.obj = v;
			message.setData(bundle);
			message.arg1 = 1;
			GameData.mhandler.sendMessage(message);
		}
		if (SocialActivity.mContext != null) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeActivity;

			Vector v = new Vector();

			v.addElement(RecruitActivity.class);

			Bundle bundle = new Bundle();

			bundle.putLong("shopId", GameData.RecruitedShopId);
			bundle.putByte("type", (byte) 1);
			v.addElement(bundle);
			message.obj = v;
			message.setData(bundle);
			message.arg1 = 3;
			GameData.mhandler.sendMessage(message);
		}
	}

	/**
	 * @店铺属性更新包
	 * @param dis
	 *            1等级;2士气;3知名度;4品质;5店铺加成;6 店铺招揽;7 店长ID;8招揽剩余时间（分钟）;9店铺剩余货物数量
	 *            12招揽量13被招揽量
	 */
	public static void exec_Resp_ShopUpdata(DataInputStream dis) {
		try {
			long id = dis.readLong();
			// System.out.println("shopId ="+id);
			byte size = dis.readByte();
			// #ifdef Debug
			System.out.println(id + "====size===================" + size);
			// #endif
			byte type;
			int value;
			Shop shop = GameData.getShop(id);
			for (int i = 0; i < size; i++) {
				type = dis.readByte();

				// #ifdef Debug
				// System.out.println(id + "====更新类型是=" + type);
				// #endif
				if (shop == null)
					break;
				if (type == 1) {
					value = dis.readInt();
					// #ifdef Debug
					// System.out.println("等级" + value);
					// #endif
					shop.level = (byte) value;
				} else if (type == 2) {
					value = dis.readInt();
					// #ifdef Debug
					// System.out.println("士气" + value);
					// #endif
					shop.morale = value;
				} else if (type == 3) {
					value = dis.readInt();
					// #ifdef Debug
					// System.out.println("知名" + value);
					// #endif
					shop.popularity = value;
				} else if (type == 4) {
					value = dis.readInt();
					// #ifdef Debug
					// System.out.println("品质" + value);
					// #endif
					shop.quality = value;
				} else if (type == 5) {
					value = dis.readInt();
					// #ifdef Debug
					// System.out.println("加成" + value);
					// #endif
					shop.gain = value;
				} else if (type == 6) {
					shop.recruitStat = (byte) dis.readInt();
					// #ifdef Debug
					// System.out.println("店铺招揽" + shop.recruitStat);
					// #endif
				} else if (type == 7) {
					shop.managerId = dis.readLong();
					// #ifdef Debug
					// System.out.println("店长" + shop.managerId);
					// #endif
				} else if (type == 8) {
					shop.lastTime = dis.readInt();
					shop.curTime = System.currentTimeMillis();
					// #ifdef Debug
					// System.out.println("招揽剩余时间" + shop.lastTime);
					// #endif
				} else if (type == 9) {
					shop.goodsNum = dis.readInt();
					// #ifdef Debug
					// System.out.println("库存数量::" + shop.goodsNum);
					// #endif
				} else if (type == 10) {
					dis.readInt();
					// GameData.delShop(id);
				} else if (type == 11) {
					shop.simpleName = dis.readUTF();
					shop.name = GameData.getProfessionName(shop.simpleName,
							shop.trade_id, shop.scale);

					// #ifdef Debug
					// System.out.println("店铺名字是::" + shop.name);
					// #endif
					// for(int index =
					// 0;index<GameRun.instance.build.length;index++)
					// {
					// if(GameRun.instance.build[index].mb.id ==
					// shop.buildingId)
					// {
					// GameRun.instance.build[index].mb.simpleName =
					// shop.simpleName;
					// GameRun.instance.build[index].mb.name = shop.name;
					// }
					// }
				} else if (type == 12) {
					shop.flowGain_canvass = dis.readInt();
					// #ifdef Debug
					// System.out.println("招揽人流量::" + shop.flowGain_canvass);
					// #endif
				} else if (type == 13) {
					shop.flowGain_canvassed = dis.readInt();
					// #ifdef Debug
					// System.out.println("被招揽人流量::" + shop.flowGain_canvassed);
					// #endif
				}

			}

			// ft_8.11好友招揽****
			// if(shop.flowGain_canvass > 0 || shop.flowGain_canvassed > 0){
			// int index = GameData.getShopIndex(id);
			// if(index != -1){
			// GameData.corporation.shop[index] = shop;
			// }
			// }
			//
			// if(GameRun.getCurWidget() != null){
			// if(GameRun.getWidget("shopUiInfo") != null){
			// ShopUI UI = (ShopUI) GameRun.getWidget("shopUiInfo");
			//
			// if (UI.shop != null) {
			// for (int i = 0; i < GameData.corporation.shop.length; i++) {
			// if (UI.shop.id == GameData.corporation.shop[i].id) {
			// UI.upDataShopUI(GameData.corporation.shop[i]);
			// }
			// }
			// }
			// }
			// }
			// if (GameRun.getCurWidget() != null
			// && (GameRun.getCurWidget() instanceof ShopUI)) {
			// ShopUI UI = (ShopUI) GameRun.getCurWidget();
			//
			// if (UI.shop != null) {
			// for (int i = 0; i < GameData.corporation.shop.length; i++) {
			// if (UI.shop.id == GameData.corporation.shop[i].id) {
			// UI.upDataShopUI(GameData.corporation.shop[i]);
			// }
			// }
			// }
			// }
			// if (GameRun.getCurWidget() != null
			// && (GameRun.getCurWidget() instanceof ShopList)) {
			// ((ShopList) (GameRun.getCurWidget()))
			// .updataShopList(GameData.corporation);
			// }

			if (GameData.corporation.listener != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 0;// 公司数据修改

				GameData.mhandler.sendMessage(message);

			}
			if(ShopInfoActivity.mContext !=null){
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 15;// 公司数据修改

				GameData.mhandler.sendMessage(message);
			}
			if(shopInfo2Activity.mContext != null){
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 22;// 公司数据修改

				GameData.mhandler.sendMessage(message);
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}// 文字
	}

	/**
	 * @任务更新包
	 * @param dis
	 */
	public static void exec_Resp_TaskUpdata(DataInputStream dis) {
		try {

			byte size = dis.readByte();
			// 1主线2支线5店铺6员工7商会8答题9交互10创业学堂11小博士任务12小博士问答(注：5-9为每日任务)
			byte group = dis.readByte();

			//#ifdef Debug
			 Log.i("zzx", ("任务更" + size+"组数据----------------------------------"));
			//#endif
			GameData.task[group] = new Task[size];
			int[] s = new int[] { 7, 0, 1 };
			int[] s_1 = { 7, 1, 2, 0, 3 };

			for (int i = 0; i < size; i++) {
				Task t = new Task();
				t.id = dis.readInt();
				t.status = dis.readByte();
				t.type = dis.readByte();
				t.description = dis.readUTF();
				//#ifdef Debug
				 Log.i("zzx",("第"+i+"组数据====任务id："+t.id+"任务状态："+t.status));
				//#endif

				if (group == 1) {
					// 完成排序顺序
					// 可领奖 7
					// 已完成 1
					// 未完成 2(已接受)
					// 可接受 0
					// 不可接受 3
					// 其他
					for (int j = 0; j < GameData.task[group].length; j++) {
						if (GameData.task[group][j] != null) {
							Log.i("TASK", "main");
							int tIndex = SystemAPI.getIntArrayIndex(s_1,
									t.status);
							int curIndex = SystemAPI.getIntArrayIndex(s_1,
									GameData.task[group][j].status);
							if (tIndex >= curIndex && curIndex != -1
									|| tIndex < 0) {
								continue;
							} else {
								for (int k = GameData.task[group].length - 2; k >= j; k--) {
									GameData.task[group][k + 1] = GameData.task[group][k];
								}
								GameData.task[group][j] = t;
								break;
							}
						} else {
							GameData.task[group][i] = t;
							break;
						}
					}
				} else if (group == 2) {
					// 排序顺序
					// 可领奖 7
					// 可接受 0
					// 已完成 1
					// 其他
					for (int j = 0; j < GameData.task[group].length; j++) {
						if (GameData.task[group][j] != null) {
							int tIndex = SystemAPI
									.getIntArrayIndex(s, t.status);
							int curIndex = SystemAPI.getIntArrayIndex(s,
									GameData.task[group][j].status);
							if (tIndex >= curIndex && curIndex != -1
									|| tIndex < 0) {
								continue;
							} else {
								for (int k = GameData.task[group].length - 2; k >= j; k--) {
									GameData.task[group][k + 1] = GameData.task[group][k];
								}
								GameData.task[group][j] = t;
								break;
							}
						} else {
							GameData.task[group][i] = t;
							break;
						}
					}

				} else {
					GameData.task[group][i] = t;
					if(group == 12){
						Log.i("Log2", i+"==="+t.description);
						Log.i("Log2", "size:"+size);
					}
				}
			}

			// GameData.taskId[group] = new int[size];
			// GameData.taskStatus[group] = new byte[size];
			// GameData.taskType[group] = new byte[size];
			// GameData.taskDescription[group] = new String[size];
			//
			// for(int j = 0; j < size; j ++ )
			// {
			// GameData.taskId[group][j] = dis.readInt();//id
			// GameData.taskStatus[group][j] = dis.readByte();//任务状态
			// GameData.taskType[group][j] = dis.readByte();//任务所属类别
			// GameData.taskDescription[group][j]=dis.readUTF();//标题
			// }

			// 表示任务是否更新
			GameData.isUpdatTask = true;
			// if(Tutorial.tutorialId==Tutorial.GETTASK)
			// {
			// Tutorial.tutorialId=Tutorial.CREATESHOP;
			// //
			// Game.instance.turorial.addOption(Option.getOptionTip("/红色店铺/黑色是公司的基础组成部分，是公司产生收益的/蓝色重要来源/黑色，马上去开设您的第一间店铺吧！",null,1,1));
			// //
			// Game.instance.turorial.addOption(Option.getOptionTip("请选择菜单栏中的“地图”选项。",1,1));
			// // Game.instance.turorial.addOption(Option.getOptionArrow(new
			// int[]{UIManager.KEY_SOFTKEY1},null, 35, SysDef.SCREEN_H-40,2));
			// GameRun.removeAllWidget();
			// }
			// if (Tutorial.tutorialId == Tutorial.FINISHTASK)
			// {
			// //
			// Game.instance.turorial.addOption(Option.getOptionTip(UIManager.TEACH[18],null,DrawTipInfo.RIBBON,569));
			// if(Tutorial.teach_index<19)
			// {
			// if (Tutorial.can_next_index) {
			// Tutorial.teach_index = 19;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第20"
			// + UIManager.TEACH_STEP, 1, 1),
			// Option.option_key_no);
			// Tutorial.teach_index = 20;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第21"
			// + UIManager.TEACH_STEP, 1, 1),
			// Option.option_key_no);
			//
			// Game.instance.turorial.tutorialId = Tutorial.GETTASK;
			// Tutorial.teach_index = 21;
			// Game.instance.turorial.addOption(Option.getOptionTip(UIManager.TEACH[Tutorial.teach_index],
			// "第22" + UIManager.TEACH_STEP, 1, 1),Option.option_key_no);
			// Game.instance.turorial.addOption(Option.getOptionArrow(new
			// int[]{UIManager.KEY_SOFTKEY1}, null, 35, SysDef.SCREEN_H - 40,
			// 2),Option.option_key_all_Left);
			// //-----------------------------------------
			// // Tutorial.tutorialId = -1;
			// // Tutorial.teach_index = 22;
			// // Game.instance.turorial.addOption(Option.getOptionTip(
			// // UIManager.TEACH[Tutorial.teach_index], "第22"
			// // + UIManager.TEACH_STEP,
			// // DrawTipInfo.PURCHASE, 526), Option.option_key_no);
			//
			// }
			// GameRun.removeAllWidget();
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TaskActivity.mContext != null||DoctorTaskActivity.mContext !=null||TaskListActivity.mContext!=null) {
			Message message = Message.obtain();
			message.what = GameDefinition.Message_changeData;
			message.arg1 = 3;
			GameData.mhandler.sendMessage(message);
		}

	}

	/**
	 * @任务详情
	 * @param dis
	 */
	public static void exec_Resp_TaskDetailUpdata(DataInputStream dis) {
		try {

			int id = dis.readInt();
			// 任务状态:-1任务不可显示0任务未接受1任务已完成2任务已接受3任务不能被接受4任务可以提交5任务不能提交6可以回答问题7可以领取奖励8奖励已领取9任务已关闭
			byte state = dis.readByte();// 任务状态:0未接受1已接受2完成4已领取奖励6可以回答问题
			Log.i("Log", "Analysis---state:--" + state);
			byte group = dis.readByte();// 0主线1店铺2员工3商会4社会5答题6交互
			String title = dis.readUTF();// 标题
			String target = dis.readUTF();// 目标
			String guide = dis.readUTF();// 指引
			String award = dis.readUTF();// 奖励



			if (TaskActivity.mContext != null||DoctorTaskActivity.mContext != null || VentureEvalutionActivity.mContext!=null) 
			{
				


				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeActivity;

				Vector v = new Vector();

				v.addElement(TaskDetailActivity.class);

				Bundle bundle = new Bundle();

				bundle.putInt("id", id);

				bundle.putByte("state", state);

				bundle.putByte("group", group);

				bundle.putString("title", title);

				bundle.putString("target", target);

				bundle.putString("guide", guide);

				bundle.putString("award", award);

				v.addElement(bundle);

				message.obj = v;

				message.setData(bundle);

				message.arg1 = 2;
				// zhouzhilong add
				message.arg2 = state;

				GameData.mhandler.sendMessage(message);
			}

			// TaskDescription dep = new TaskDescription(null);
			// dep.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// dep.setInt(title, target, guide, award);
			// dep.init(GameData.typeTask, state, id, group);//
			// GameData.typeTask
			// // ：0主线1日常
			// // 第二个参数表示：任务当前状态
			// GameRun.addWidget(dep, true);
			// if (Tutorial.tutorialId == Tutorial.GETTASK)// 如果是在新手提示-获取任务阶段
			// {
			// Game.instance.turorial.addOption(Option.getOptionArrow(
			// new int[] { UIManager.KEY_SOFTKEY1, }, new int[] {
			// UIManager.KEY_UP, UIManager.KEY_DOWN }, 35,
			// SysDef.SCREEN_H - 40, 2), Option.option_key_all_Left);
			// } else if (Tutorial.tutorialId == Tutorial.FINISHTASK) {
			// Game.instance.turorial.addOption(Option.getOptionArrow(
			// new int[] { UIManager.KEY_SOFTKEY1 }, new int[] {
			// UIManager.KEY_UP, UIManager.KEY_DOWN }, 35,
			// SysDef.SCREEN_H - 40, 2), Option.option_key_all_Left);
			// if(Tutorial.teach_index>=21)
			// {
			// GameData.isCheckMapTask = true;
			// Tutorial.tutorialId = Tutorial.GETTASK2;
			// Tutorial.teach_index = 22;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第23"
			// + UIManager.TEACH_STEP, 1, 1),
			// Option.option_key_no);
			//
			// Tutorial.teach_index = 23;
			// Game.instance.turorial.addOption(Option.getOptionTip(
			// UIManager.TEACH[Tutorial.teach_index], "第24"
			// + UIManager.TEACH_STEP,
			// DrawTipInfo.PURCHASE, 526), Option.option_key_no);
			//
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();

			// Game.instance.initClewBox("系统消息", "网络又断啦，请先休息一下，稍后再试吧。", true);
		}
	}

	/**
	 * @author 任务列表
	 * @param dis
	 */
	public static void exec_Task_List_Info(DataInputStream dis) {
		try {

			byte size = dis.readByte();
			int id[] = new int[size];
			byte state[] = new byte[size];
			String name[] = new String[size];
			for (int i = 0; i < size; i++) {
				id[i] = dis.readInt();
				state[i] = dis.readByte();
				name[i] = dis.readUTF();
			}

			// TaskList tasklist = new TaskList(null);
			// tasklist.setBounds(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
			// tasklist.init(id, state, name);
			// GameRun.addWidget(tasklist, false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param dis
	 * @author 每日任务汇总
	 * @param taskGroup
	 *            任务组
	 * @param finish
	 *            任务组完成总数
	 * @param giveward
	 *            任务组奖励总加成
	 * @param maxGiveward
	 *            任务组奖励最大值
	 * @param taskSize
	 *            任务组任务数
	 * 
	 */
	public static void exec_Resp_DayTaskSummar(DataInputStream dis) {
		try {

			byte size = dis.readByte();
			GameData.taskGroup = new byte[size];
			GameData.finish = new byte[size];
			GameData.giveward = new byte[size];
			GameData.maxGiveward = new byte[size];
			GameData.taskSize = new byte[size];
			for (int i = 0; i < size; i++) {
				GameData.taskGroup[i] = dis.readByte();
				GameData.finish[i] = dis.readByte();
				GameData.giveward[i] = dis.readByte();
				GameData.maxGiveward[i] = dis.readByte();
				GameData.taskSize[i] = dis.readByte();
			}
			Log.i("juj", "maxGiveward------------------>"+GameData.giveward);
			Log.i("juj", "giveward------------------>"+GameData.giveward);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (TaskActivity.mContext != null||DoctorTaskActivity.mContext !=null) {
			Message message = Message.obtain();
			message.what = GameDefinition.Message_changeData;
			message.arg1 = 14;
			GameData.mhandler.sendMessage(message);
		}
	}

	/**
	 * 好友是否在线信息 zhangxiaoqing
	 * 
	 * @param dis
	 */
	public static void exec_IsOnline_Info(DataInputStream dis) {
		try {
			long targetId = dis.readLong();
			byte type = dis.readByte();
			byte isOnline = dis.readByte();
			String name = dis.readUTF();

			if (type == 1)// 好友
			{
				Social f = null;
				for (int i = 0; i < GameData.friend.size(); i++) {
					f = (Social) GameData.friend.elementAt(i);
					if (f.id == targetId) {
						f.status = isOnline;
						f.name = name;
					}

				}
			} else if (type == 2)// 黑名单
			{
				Social f = null;
				for (int i = 0; i < GameData.blacklist.size(); i++) {
					f = (Social) GameData.blacklist.elementAt(i);
					if (f.id == targetId) {
						f.status = isOnline;
						f.name = name;
					}

				}
			} else if (type == 3)// 陌生者
			{
				Social f = null;
				for (int i = 0; i < GameData.stranger.size(); i++) {
					f = (Social) GameData.stranger.elementAt(i);
					if (f.id == targetId) {
						f.status = isOnline;
						f.name = name;
					}

				}
			} else if (type == 4)// 竞争者
			{
				Social f = null;
				for (int i = 0; i < GameData.competitor.size(); i++) {
					f = (Social) GameData.competitor.elementAt(i);
					if (f.id == targetId) {
						f.status = isOnline;
						f.name = name;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exec_RelationList_Info(DataInputStream dis) {
		try {
			byte type = dis.readByte();
			int size = dis.readByte() & 0xff;

			if (type == 1)// 好友
			{
				GameData.friend.removeAllElements();
			} else if (type == 2)// 黑名单

			{
				GameData.blacklist.removeAllElements();

			} else if (type == 3)// 陌生者
			{
				GameData.stranger.removeAllElements();
			} else if (type == 4)// 竞争者
			{
				GameData.competitor.removeAllElements();
			}
			Social s = null;
			for (int i = 0; i < size; i++) {
				s = new Social();
				s.relation = type;
				s.id = dis.readLong();
				if(type == 1)
				{
					s.isUnrecruit = dis.readByte();
				}
				s.level = dis.readByte();
				s.status = dis.readByte();
				s.name = dis.readUTF();
				s.headId = dis.readShort();
				Log.v("yz", "headid="+s.headId);
				s.sex = dis.readByte();
				s.companyName = dis.readUTF();
				s.companyLevel = dis.readByte();
				s.companyAsset = dis.readLong();
				s.currShopCount = dis.readShort();
				s.maxShopCount = dis.readShort();
				s.currEmployeeCount = dis.readShort();
				s.maxEmployeeCount = dis.readShort();
				s.compitierNum = dis.readInt();
				// 对方招揽你的店铺的次数//ft_8.11好友招揽
				if (type == 1)// 好友
				{
					GameData.friend.addElement(s);
				} else if (type == 2)// 黑名单
				{
					GameData.blacklist.addElement(s);

				} else if (type == 3)// 陌生者
				{
					GameData.stranger.addElement(s);
				} else if (type == 4)// 竞争者
				{
					GameData.competitor.addElement(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exec_Relation_Updata_Info(DataInputStream dis) {
		try {
			
			Social s = new Social();

			byte type = dis.readByte();
			s.relation = dis.readByte();
			s.id = dis.readLong();
			 
			if (type == 1) {
				s.level = dis.readByte();
				s.status = dis.readByte();
				if(s.relation == 1)
				{
					s.isUnrecruit = dis.readByte();
				}
				s.name = dis.readUTF();
				s.headId = dis.readShort();
				s.sex = dis.readByte();
				s.companyName = dis.readUTF();
				s.companyLevel = dis.readByte();
				s.companyAsset = dis.readLong();
				s.currShopCount = dis.readShort();
				s.maxShopCount = dis.readShort();
				s.currEmployeeCount = dis.readShort();
				s.maxEmployeeCount = dis.readShort();
				s.compitierNum = dis.readInt();
			}
			if (type == 1)// add
			{
				if (s.relation == 1)// 好友
				{
					GameData.friend.addElement(s);
				} else if (s.relation == 2)// 黑名单
				{
					GameData.blacklist.addElement(s);
				} else if (s.relation == 3)// 陌生者
				{
					GameData.stranger.addElement(s);
				} else if (s.relation == 4)// 竞争者
				{
					GameData.competitor.addElement(s);

				}
			} else if (type == 2)// delete
			{
				if (s.relation == 1)// 好友
				{
					Social f = null;
					for (int i = 0; i < GameData.friend.size(); i++) {
						f = (Social) GameData.friend.elementAt(i);
						if (f.id == s.id) {
							GameData.friend.removeElementAt(i);
						}
					}

				} else if (s.relation == 2)// 黑名单
				{
					Social b = null;
					for (int i = 0; i < GameData.blacklist.size(); i++) {
						b = (Social) GameData.blacklist.elementAt(i);
						if (b.id == s.id) {
							GameData.blacklist.removeElementAt(i);
						}
					}

				} else if (s.relation == 3)// 陌生者
				{
					Social st = null;
					for (int i = 0; i < GameData.stranger.size(); i++) {
						st = (Social) GameData.stranger.elementAt(i);
						if (st.id == s.id) {
							GameData.stranger.removeElementAt(i);
						}
					}
				} 
				else if (s.relation == 4)// 竞争者
				{
					Social c = null;

					for (int i = 0; i < GameData.competitor.size(); i++) {
						c = (Social) GameData.competitor.elementAt(i);
						if (c.id == s.id) {
							GameData.competitor.removeElementAt(i);
						}
					}

				}
			}

			if (SocialActivity.mContext != null) {
				Message msg = Message.obtain();

				msg.what = GameDefinition.Message_changeData;

				msg.arg1 = 4;

				GameData.mhandler.sendMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// zhouzhilong add --synchronized--
	public synchronized static void exec_Resp_Mail_info_list(DataInputStream dis)
			throws Exception {
		byte type = dis.readByte();
		short maxNum = dis.readShort();
		short size = dis.readShort();

		if (type == MailboxActivity.IN_MAIL_BOX) {

			GameData.inMailbox.removeAllElements();

		} else if (type == MailboxActivity.OUT_MAIL_BOX) {
			GameData.outMailbox.removeAllElements();
		} else if (type == MailboxActivity.EVENT_LIST_BOX) {
			GameData.eventListbox.removeAllElements();
		}

		for (int i = 0; i < size; i++) {
			Mail mail = new Mail();
			mail.id = dis.readLong();
			mail.sender = dis.readUTF();
			mail.receiver = dis.readUTF();
			mail.title = dis.readUTF();
			mail.content = dis.readUTF();
			mail.money = dis.readLong();

			short propsSize = dis.readShort();
			mail.good = new Props[propsSize];

			for (int j = 0; j < propsSize; j++) {
				mail.good[j] = new Props();
				mail.good[j].id = dis.readLong();
				mail.good[j].count = dis.readShort();
				mail.good[j].icon = dis.readShort();
				mail.good[j].name = dis.readUTF();
				mail.good[j].desc = dis.readUTF();
				mail.good[j].subsize = dis.readByte();
				mail.good[j].targetTypeld = new byte[mail.good[j].subsize];
				for (int h = 0; h < mail.good[j].subsize; h++) {
					mail.good[j].targetTypeld[h] = dis.readByte();
				}

			}

			mail.isExist = dis.readByte();
			mail.type = dis.readByte();
			mail.time = dis.readUTF();
			mail.isRead = dis.readByte();// 0未读 1 已读

			if (type == MailboxActivity.IN_MAIL_BOX) {
				GameData.inMailbox.addElement(mail);

			} else if (type == MailboxActivity.OUT_MAIL_BOX) {
				GameData.outMailbox.addElement(mail);
			} else if (type == MailboxActivity.EVENT_LIST_BOX) {
				GameData.eventListbox.addElement(mail);
			}
		}

		if (MailboxActivity.isShow && MailboxActivity.mContext == null)// 要打开这个界面
		{
			MailboxActivity.isShow = false;
			Message message = Message.obtain();
			message.what = GameDefinition.Message_changeActivity;
			message.arg1 = 0;
			Vector v = new Vector();

			v.addElement(MailboxActivity.class);
			message.obj = v;
			GameData.mhandler.sendMessage(message);
			return;
		}

		if (MailboxActivity.mContext != null) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 5;

			message.arg2 = type + 1;
			GameData.mhandler.sendMessage(message);
		}

		if (MailDetailActivity.mContext != null) {
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeData;

			message.arg1 = 7;

			message.arg2 = type;

			GameData.mhandler.sendMessage(message);
		}
	}

	public static String showErrorInfo(String error) {
		String str = "";
		if (error != null) {
			if (error.equals("s1011")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info1);
			} else if (error.equals("s1012")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info2);
			} else if (error.equals("s1013")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info3);
			} else if (error.equals("s1014")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info4);
			} else if (error.equals("s1015")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info5);
			} else if (error.equals("s1016")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info6);
			} else if (error.equals("s1017")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info7);
			} else if (error.equals("s1018")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info8);
			} else if (error.equals("s1100")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info9);
			} else if (error.equals("s9999")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info10);
			} else if (error.equals("s5000")) {
				str = MainActivity.resources
						.getString(R.string.analysisdata_showerrorinfo_info11);
			} else {
				str = error;
			}
		}
		return str;
	}

	public static void exec_Search_User_List(DataInputStream dis) {
		try {
			byte opType = dis.readByte();
			int size = dis.readByte();

			if (size > 0) {
				GameData.seachPlayer = new Social[size];
				for (int i = 0; i < size; i++) {
					GameData.seachPlayer[i] = new Social();
					GameData.seachPlayer[i].id = dis.readLong();
					GameData.seachPlayer[i].name = dis.readUTF();
					GameData.seachPlayer[i].level = dis.readByte();
					GameData.seachPlayer[i].companyName = dis.readUTF();
					GameData.seachPlayer[i].companyLevel = dis.readByte();
					GameData.seachPlayer[i].headId = dis.readShort();
					/*添加禁言时间字段*/
					GameData.seachPlayer[i].time = dis.readInt();
					GameData.seachPlayer[i].sex = (byte) (GameData.seachPlayer[i].headId % 2);
				}
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 8;// 公司数据修改

				GameData.mhandler.sendMessage(message);
			} else {
				Message message = Message.obtain();
//				message.what = GameDefinition.Message_showToast;
//
//				Vector v = new Vector();
//				v.addElement(showErrorInfo(MainActivity.resources
//						.getString(R.string.analysisdata_user_list_info)));
//				message.obj = v;
//
//				GameData.mhandler.sendMessage(message);
				GameAPI.showToast(showErrorInfo(MainActivity.resources.getString(R.string.analysisdata_user_list_info)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exec_Promoter_Friend_List(DataInputStream dis) {
		try {
			if (GameData.promoter == null)
				GameData.promoter = new Promoter();

			GameData.promoter.pageIndex = dis.readByte();
			GameData.promoter.totalPage = dis.readByte();
			short loopCount = dis.readShort();
			
			if (loopCount > 0) {
				GameData.friendList = new Promoter[loopCount];
				for (int i = 0; i < loopCount; i++) {
					GameData.friendList[i] = new Promoter();

					GameData.friendList[i].userId = dis.readLong();
					GameData.friendList[i].userLvl = dis.readByte();
					GameData.friendList[i].userName = dis.readUTF();
					GameData.friendList[i].comId = dis.readLong();
					GameData.friendList[i].comLvl = dis.readByte();
					GameData.friendList[i].comName = dis.readUTF();
					GameData.friendList[i].friendShip = dis.readLong();//好友值

				}
				// PromoterActivity.mContext.Activitychange(PromoterActivity.class,null);
				Message message = Message.obtain();
				Vector v = new Vector();
				message.obj = v;
				message.what = GameDefinition.Message_changeActivity;
				v.addElement(PromotersActivity.class);

				message.arg1 = 4;

				GameData.mhandler.sendMessage(message);
			}
			/*
			 * else{ Message message = Message.obtain(); message.what =
			 * GameDefinition.Message_showToast;
			 * 
			 * Vector v = new Vector();
			 * v.addElement(showErrorInfo("附近好像没有该玩家！")); message.obj = v;
			 * 
			 * GameData.mhandler.sendMessage(message);
			 * 
			 * 
			 * }
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exec_searchStaff(DataInputStream dis) {
		try {
			// zhouzhilong Add
			GameData.diglist.clear();
			byte opType = dis.readByte();// 1人才市场 2：全部3:特定人物
			int size = dis.readInt();
			staff_NUM = size;
			Employee emp = null;
			// if(opType == 1)
			// {
			// TalentMarket talentMarket = new TalentMarket(null, "人才市场",1);
			for (int i = 0; i < size; i++) {
				emp = new Employee();
				if (opType == 1) {
					emp.employerName = dis.readUTF();// 员工雇主名
					emp.price = dis.readInt();// 出售价格
				}
				emp.id = dis.readLong();
				emp.imageId = dis.readInt();
				emp.sex = dis.readByte();
				emp.type = dis.readByte();
				emp.name = dis.readUTF();
				emp.empCompany = dis.readUTF();
				emp.duty = dis.readShort();
				emp.level = dis.readShort();
				emp.department = dis.readLong();
				emp.departName = dis.readUTF();
				emp.hobby = dis.readShort();
				emp.salary = dis.readInt();
				emp.compact = dis.readLong();
				emp.ability = dis.readInt();
				emp.maxAbility = dis.readInt();
				emp.loyalty = dis.readInt();
				emp.maxLoyalty = dis.readInt();
				emp.experence = dis.readInt();
				emp.maxExperence = dis.readInt();
				emp.morale = dis.readInt();
				emp.makeUp = dis.readInt();
				emp.strength = dis.readInt();
				emp.maxstrength = dis.readInt();

				byte skillNum = dis.readByte();

				if (skillNum >= 0) {
					emp.skill = new Skill[skillNum];
					for (int j = 0; j < skillNum; j++) {
						Skill sk = new Skill();
						sk.id = dis.readShort();
						sk.level = dis.readByte();
						sk.name = dis.readUTF();
						emp.skill[j] = sk;
					}
				}

				byte qualityNum = dis.readByte();
				if (qualityNum >= 0) {
					emp.quality = new Quality[qualityNum];

					for (int j = 0; j < qualityNum; j++) {
						Quality q = new Quality();
						q.id = dis.readShort();
						q.level = dis.readByte();
						q.name = dis.readUTF();
						emp.quality[j] = q;
						q = null;
					}
				}
				// talentMarket.addRecruimentEmp(emp);
				GameData.diglist.addElement(emp);
			}
			if (DiglistActivity.mContext != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 9;// 公司数据修改

				GameData.mhandler.sendMessage(message);
			}else if(SocialActivity.mContext != null){
				
				Message message = Message.obtain();
				Vector v = new Vector();
				message.obj = v;
				message.what = GameDefinition.Message_changeActivity;
				v.addElement(DiglistActivity.class);

				message.arg1 = 12;

				GameData.mhandler.sendMessage(message);
			}

			// talentMarket.setBounds(0, 0, UIManager.SCREEN_WIDTH,
			// UIManager.SCREEN_HEIGHT);
			// talentMarket.initEmp();
			// talentMarket.updateEmployeeList();
			// GameRun.addWidget(talentMarket, true);

			// }
			// else if(opType == 2)
			// {
			//
			// TalentMarket talentMarket = new TalentMarket(null, "猎头中心",2);
			// for(int i=0;i<size;i++)
			// {
			// Employee emp = new Employee();
			// // emp.employerName = dis.readUTF() ;//员工雇主名
			// // emp.price = dis.readInt() ;//出售价格
			// emp.id=dis.readLong();
			// emp.imageId=dis.readInt();
			// emp.sex=dis.readByte();
			// emp.type=dis.readByte();
			// emp.name=dis.readUTF();
			// emp.empCompany = dis.readUTF();
			// emp.duty=dis.readShort();
			// emp.level=dis.readShort();
			// emp.department=dis.readLong();
			// emp.departName=dis.readUTF();
			// emp.hobby=dis.readShort();
			// emp.salary=dis.readInt();
			// emp.compact=dis.readLong();
			// emp.ability=dis.readInt();
			// emp.maxAbility=dis.readInt();
			// emp.loyalty=dis.readInt();
			// emp.maxLoyalty=dis.readInt();
			// emp.experence=dis.readInt();
			// emp.maxExperence=dis.readInt();
			// emp.morale=dis.readInt();
			// emp.makeUp=dis.readInt();
			// emp.strength=dis.readInt();
			// emp.maxstrength=dis.readInt();
			//
			// byte skillNum=dis.readByte();
			//
			// if(skillNum>=0)
			// {
			// emp.skill=new Skill[skillNum];
			// for(int j=0;j<skillNum;j++)
			// {
			// Skill sk=new Skill();
			// sk.id=dis.readShort();
			// sk.level=dis.readByte();
			// sk.name=dis.readUTF();
			// emp.skill[j]=sk;
			// }
			// }
			//
			// byte qualityNum=dis.readByte();
			// if(qualityNum>=0)
			// {
			// emp.quality=new Quality[qualityNum];
			//
			// for(int j=0;j<qualityNum;j++)
			// {
			// Quality q=new Quality();
			// q.id=dis.readShort();
			// q.level=dis.readByte();
			// q.name=dis.readUTF();
			// emp.quality[j]=q;
			// q = null;
			// }
			// }
			// talentMarket.addRecruimentEmp(emp);
			// }
			// talentMarket.setBounds(0, 0, UIManager.SCREEN_WIDTH,
			// UIManager.SCREEN_HEIGHT);
			// talentMarket.initEmp();
			// talentMarket.updateEmployeeList();
			// GameRun.addWidget(talentMarket, true);
			// }
			// else if(opType == 3)
			// {
			//
			//
			// TalentMarket talentMarket = new TalentMarket(null, "好友员工列表",3);
			// for(int i=0;i<size;i++)
			// {
			// Employee emp = new Employee();
			// // emp.employerName = dis.readUTF() ;//员工雇主名
			// // emp.price = dis.readInt() ;//出售价格
			// emp.id=dis.readLong();
			// emp.imageId=dis.readInt();
			// emp.sex=dis.readByte();
			// emp.type=dis.readByte();
			// emp.name=dis.readUTF();
			// emp.empCompany = dis.readUTF();
			// emp.duty=dis.readShort();
			// emp.level=dis.readShort();
			// emp.department=dis.readLong();
			// emp.departName=dis.readUTF();
			// emp.hobby=dis.readShort();
			// emp.salary=dis.readInt();
			// emp.compact=dis.readLong();
			// emp.ability=dis.readInt();
			// emp.maxAbility=dis.readInt();
			// emp.loyalty=dis.readInt();
			// emp.maxLoyalty=dis.readInt();
			// emp.experence=dis.readInt();
			// emp.maxExperence=dis.readInt();
			// emp.morale=dis.readInt();
			// emp.makeUp=dis.readInt();
			// emp.strength=dis.readInt();
			// emp.maxstrength=dis.readInt();
			//
			// byte skillNum=dis.readByte();
			//
			// if(skillNum>=0)
			// {
			// emp.skill=new Skill[skillNum];
			// for(int j=0;j<skillNum;j++)
			// {
			// Skill sk=new Skill();
			// sk.id=dis.readShort();
			// sk.level=dis.readByte();
			// sk.name=dis.readUTF();
			// emp.skill[j]=sk;
			// }
			// }
			//
			// byte qualityNum=dis.readByte();
			// if(qualityNum>=0)
			// {
			// emp.quality=new Quality[qualityNum];
			//
			// for(int j=0;j<qualityNum;j++)
			// {
			// Quality q=new Quality();
			// q.id=dis.readShort();
			// q.level=dis.readByte();
			// q.name=dis.readUTF();
			// emp.quality[j]=q;
			// q = null;
			// }
			// }
			// talentMarket.addRecruimentEmp(emp);
			// }
			// talentMarket.setBounds(0, 0, UIManager.SCREEN_WIDTH,
			// UIManager.SCREEN_HEIGHT);
			// talentMarket.initEmp();
			// talentMarket.updateEmployeeList();
			// GameRun.addWidget(talentMarket, true);
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exec_Resp_AnimationControl(DataInputStream dis)
			throws Exception {
		byte status = dis.readByte();
		int resId = dis.readInt();
		String description = dis.readUTF();

//		Message message = Message.obtain();
//		message.what = GameDefinition.Message_showToast;
//
//		Vector v = new Vector();
//		v.addElement(description);
//		message.obj = v;
//
//		GameData.mhandler.sendMessage(message);
		GameAPI.showToast(description);
	}

	/**
	 * 退出游戏包
	 * 
	 * @param dis
	 */
	public static void exec_Exit_Game(DataInputStream dis) {
		try {
			byte flag = dis.readByte();
			String s = dis.readUTF();

			if (flag == 3)// 游戏爆满
			{
//				Message message = Message.obtain();
//				message.what = GameDefinition.Message_showToast;
//
//				Vector v = new Vector();
//				v.addElement(s);
//				message.obj = v;
//
//				GameData.mhandler.sendMessage(message);
				GameAPI.showToast(s);
			} else if (flag == 2)// 版本更新
			{
				String url = dis.readUTF();

				Message message = Message.obtain();

				message.what = GameDefinition.Message_show_dailog;

				Bundle bundle = new Bundle();

				bundle.putString("message", s);

				bundle.putString("url", url);

				message.obj = bundle;

				message.arg1 = 0;

				GameData.mhandler.sendMessage(message);
			} else {
				try {
					if (GameData.isExitRequest)// 正常退出
					{
						GameData.isExitRequest = false;
						Connection.close();
					} else {
						if (control.connection != null) {
							control.connection.connectionBroken();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (control.logic.get("Loading") != null) {
			((Loading) control.logic.get("Loading")).destroy();
		}
	}

	public static void exec_Resp_TwoSureMsg_Input(DataInputStream dis) {

		try {
			final int eventId = dis.readInt();

			String title = dis.readUTF();
			final byte controlSize = dis.readByte();

			byte[] controlType;
			String[] inputKey;
			String[] inputLabel;

			controlType = new byte[controlSize];
			inputKey = new String[controlSize];
			inputLabel = new String[controlSize];

			for (int i = 0; i < controlSize; i++) {
				controlType[i] = dis.readByte();
				inputKey[i] = dis.readUTF();
				inputLabel[i] = dis.readUTF();
			}

			Message message = Message.obtain();
			Bundle bundle = new Bundle();

			bundle.putInt("TwoSureMsg_Input_id", eventId);
			bundle.putByte("TwoSureMsg_Input_controlSize", controlSize);

			bundle.putStringArray("TwoSureMsg_Input_key", inputKey);

			message.obj = bundle;

			message.what = GameDefinition.Message_show_dailog;
			message.arg1 = 2;

			// Vector v = (Vector)message.obj;
			GameData.mhandler.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户属性
	 * 
	 * @param dis
	 */
	public static void exec_Resp_UseUpData(DataInputStream dis) {
		try {
			long id = dis.readLong();
			byte size = dis.readByte();
			byte type;
			for (int i = 0; i < size; i++) {
				type = dis.readByte();

				if (type == 1) {
					GameData.player.strength = dis.readInt();
				} else if (type == 2) {
					GameData.player.strategy = dis.readInt();
				} else if (type == 3) {
					GameData.player.leadership = dis.readInt();
				} else if (type == 4) {
					GameData.player.execution = dis.readInt();
				} else if (type == 5) {
					long save_money = GameData.player.money;
					GameData.player.money = dis.readLong();
					GameData.add_player_money = GameData.player.money
							- save_money;
					// if(GameData.add_player_money>0)
					// {
					// Game.add_addMoney(GameData.add_player_money);
					// }
					// #ifdef Debug
					// # System.out.println("GameData.player.money =现金= "
					// # + GameData.player.money);
					// #endif
				} else if (type == 6) {
					GameData.player.level = dis.readShort();
				} else if (type == 7) {
					GameData.player.businessId = dis.readLong();
				} else if (type == 8) {
					GameData.player.businessName = dis.readUTF();
				} else if (type == 9) {
					GameData.player.capacityId = dis.readShort();
				} else if (type == 10) {
					// #ifdef Debug
					// # System.out.println("GameData.player =对像= " +
					// GameData.player);
					// #endif
					GameData.player.assets = dis.readLong();
				} else if (type == 11) {
					// #ifdef Debug
					// # System.out.println("商会联合宣传状态");
					// #endif
				}else if(type ==14)//更新角色的好友值
	           	 {
	           		 GameData.player.friendship = dis.readLong();
	           	 }
	           	 else if( type ==15 )//更新点亮的徽章
	           	 {
	           		 GameData.player.badgeId = dis.readLong();
	           	 } 
	           	 else if( type == 16 )//更新获得徽章的数量
	           	 {
	           		 GameData.player.badgenumber = dis.readInt();
	           	 }
	           	 else if(type ==17)//更新角色的彩票数量
	           	 {
	           		 GameData.player.voteNum = dis.readInt();
	           	 }else if(type ==18)//累计好友值
	           	 {
	           		 dis.readLong();
	           	 }else if(type ==19)//人物点亮徽章id 
	           	 {
	           		 dis.readUTF();
	           	 }
			}
			// #ifdef Debug
			// # System.out.println("id == " + id);
			// #endif

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 文字内容

	/**
	 * 
	 * @param dis
	 *            商会会员基本信息
	 * @return
	 * @throws IOException
	 */
	// jiangxujin add
	public static MemberEmployee readMemberemployee(DataInputStream dis)
			throws IOException {
		MemberEmployee emp = new MemberEmployee();
		emp.memId = dis.readLong();
		emp.name = dis.readUTF();
		emp.gender = dis.readByte();
		emp.duty = dis.readByte();
		emp.level = dis.readShort();
		emp.companyLevel = dis.readShort();

		return emp;
	}

	/**
	 * 升级信息响应
	 * 
	 * @param dis
	 */
	public static void exec_Resp_levelupInfo(DataInputStream dis) {
		try {
			Vector<Vector> v = new Vector<Vector>();
			byte type = dis.readByte();// 0公司 1部门 2 店铺  3小博士
			long id = dis.readLong();
			// v0：符合条件信息
			byte size0 = dis.readByte();
			Vector v0 = new Vector();
			for (int i = 0; i < size0; i++) {
				v0.addElement(dis.readUTF());
			}
			v.addElement(v0);
			// v1：不符合条件信息
			byte size1 = dis.readByte();
			Vector v1 = new Vector();
			for (int i = 0; i < size1; i++) {
				v1.addElement(dis.readUTF());
			}
			v.addElement(v1);
			// v2：升级效果信息
			byte size2 = dis.readByte();
			Vector v2 = new Vector();
			for (int i = 0; i < size2; i++) {
				v2.addElement(dis.readUTF());
			}
			v.addElement(v2);

			GameData.levelUpInfo = v;

			Message msg = Message.obtain();
			msg.what = GameDefinition.Message_changeActivity;
			Vector vv = new Vector();
			if (type == 0) {// 公司升级
				vv.addElement(CompanyLevelInfoActivity.class);

			} else if (type == 1) {// 部门升级
				vv.addElement(DepartmentLevelInfoActivity.class);
			} else if (type == 2) {// 店铺升级
				vv.addElement(shopInfo2Activity.class);
			}else if(type == 3){//博士
				vv.addElement(DepartmentLevelInfoActivity.class);
			}
			vv.addElement(id);

			msg.obj = vv;
			msg.arg1 = 9;
			msg.arg2 = type;
			GameData.mhandler.sendMessage(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author 创建商会条件信息包
	 * @param dis
	 * @param valid
	 *            是否符合条件：0符合；1不符合
	 * @param size
	 *            条件个数
	 * @param msg
	 *            商会创建的条件信息
	 */
	// jiangxujin add (0x1045)
	public static void exec_QOfC_Create_Info(DataInputStream dis) {
		try {
			byte valid = dis.readByte();
			byte size = dis.readByte();
			String msg[] = new String[size];
			// int id = dis.readInt();
			for (int i = 0; i < size; i++) {
				msg[i] = dis.readUTF();
			}
			Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(CreateCommerceActivity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeActivity;
			Bundle bundle = new Bundle();
			bundle.putStringArray("msg", msg);
			message.setData(bundle);

			message.arg1 = 5;

			GameData.mhandler.sendMessage(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会基本信息
	 * @param dis
	 * @param id
	 *            商会id
	 * @param resId
	 *            商会图标
	 * @param name
	 *            商会名字
	 * @param level
	 *            商会等级
	 * @param affiche
	 *            商会公告
	 * @param createTime
	 *            商会成立时间
	 * @param chairManId
	 *            会长ID
	 * @param chairManName
	 *            会长名字
	 * @param rank
	 *            排名
	 * @param manageCost
	 *            商会管理费
	 * @param dues
	 *            商会会费
	 * @param donation
	 *            我的捐款
	 * @param money
	 *            商会基金
	 * @param assets
	 *            商会资产
	 * @param maxMemberNum
	 *            商会人数上限
	 * @param memberNum
	 *            商会id
	 * 
	 */
	// jiangxujin add (0x1046)
	public static void exec_QOfC_COfC_Base_Info(DataInputStream dis) {
		try {

			if (GameData.member == null)
				GameData.member = new Member();
			GameData.member.id = dis.readLong();
			GameData.member.resId = dis.readInt();
			GameData.member.name = dis.readUTF();
			Log.i("oop", "mingcheng===="+GameData.member.name);
			GameData.member.level = dis.readShort();
			GameData.member.affiche = dis.readUTF();
			GameData.member.createTime = dis.readUTF();
			GameData.member.chairManId = dis.readLong();
			GameData.member.chairManName = dis.readUTF();
			GameData.member.rank = dis.readShort();
			GameData.member.manageCost = dis.readLong();
			GameData.member.dues = dis.readLong();
			GameData.member.donation = dis.readLong();
			GameData.member.money = dis.readLong();
			GameData.member.assets = dis.readLong();
			GameData.member.maxMemberNum = dis.readShort();
			GameData.member.memberNum = dis.readShort();
			MemberEmployee employe[] = new MemberEmployee[GameData.member.memberNum];

			for (int i = 0; i < GameData.member.memberNum; i++) {
				employe[i] = readMemberemployee(dis);
			}
			GameData.member.employee = new MemberEmployee[GameData.member.memberNum];
			for (int i = 0; i < GameData.member.memberNum; i++) {
				for (int j = i + 1; j < GameData.member.memberNum; j++) {
					if (employe[i].duty < employe[j].duty) {
						MemberEmployee temp;
						temp = employe[i];
						employe[i] = employe[j];
						employe[j] = temp;
					}
				}

				GameData.member.employee[i] = employe[i];
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会会员基本信息
	 * @param dis
	 * @param memId
	 *            会员id
	 * @param name
	 *            会员名字
	 * @param gender
	 *            会员姓名
	 * @param duty
	 *            会员职务
	 * @param level
	 *            会员等级
	 * @param companyLevel
	 *            会员公司等级
	 * 
	 */
	// jiangxujin add (0x1048)
	public static void exec_QOfC_COfC_Member_Info(DataInputStream dis) {
		try {
			long memId = dis.readLong();
			String name = dis.readUTF();
			Log.i("oop", "name=========="+name);
			byte gender = dis.readByte();
			byte duty = dis.readByte();
			short level = dis.readShort();
			short companyLevel = dis.readShort();
			if (CommerceStaffActivity.mContext != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 =20 ;// 小博士数据修改				
				GameData.mhandler.sendMessage(message);
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会属性更新包
	 * @param dis
	 * @param id
	 * @param size
	 * @param statusId
	 */
	// jiangxujin add (0x1049)
	public static void exec_COfCStatusUpdate_Info(DataInputStream dis) {
		try {
			long id = dis.readLong();

			byte size = dis.readByte();

			for (int i = 0; i < size; i++) {
				byte statusId = dis.readByte();
				if (statusId == 1)// 商会公告
				{
					GameData.member.affiche = dis.readUTF();

					Message message = Message.obtain();
					Vector v = new Vector();
					v.addElement(CommerceInfoActivity.class);
					message.obj = v;
					message.what = GameDefinition.Message_changeData;
					Bundle bundle = new Bundle();
					bundle.putString("msg", GameData.member.affiche);
					message.setData(bundle);

					message.arg1 = 10;

					GameData.mhandler.sendMessage(message);

				} else if (statusId == 2)// 商会资产
				{
					GameData.member.assets = dis.readLong();
				} else if (statusId == 3)// 商会基金
				{
					GameData.member.money = dis.readLong();
				} else if (statusId == 4)// 商会等级
				{
					GameData.member.level = dis.readShort();
				} else if (statusId == 5)// 商会捐款
				{
					GameData.member.donation = dis.readLong();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会列表信息
	 * @param picID
	 *            商会图标
	 */
	// jiangxujin add (0x104a)
	public static void exec_COfC_List_Info(DataInputStream dis) {
		try {
			short size = dis.readShort();

			// Member mber[] = new Member[size];
			GameData.mber = new Member[size];
			Member member = null;
			for (int i = 0; i < size; i++) {
				member = new Member();
				member.id = dis.readLong();
				member.resId = dis.readInt();
				member.name = dis.readUTF();
				member.level = dis.readShort();
				member.maxMemberNum = dis.readShort();
				member.memberNum = dis.readShort();

				GameData.mber[i] = member;
				// mber[i] = new Member();
				// mber[i].id = member.id;
				// mber[i].resId = member.resId;
				// mber[i].name = member.name;
				// mber[i].level = member.level;
				// mber[i].maxMemberNum = member.maxMemberNum;
				// mber[i].memberNum = member.memberNum;

			}
			if(CommerceInfoActivity.mContext == null){
			Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(CommerceListActivity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeActivity;
			message.arg1 = 6;
			GameData.mhandler.sendMessage(message);
			}else{
	/*			Message message = Message.obtain();
				Vector v = new Vector();
				v.addElement(CommerceInfoActivity.class);
				message.obj = v;
				message.what = GameDefinition.Message_changeActivity;
				message.arg1 = 18;
				GameData.mhandler.sendMessage(message);	*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会邀请或申请的请求
	 * @param dis
	 * @param id
	 *            请求的id
	 * @param cofcId
	 *            商会id
	 * @param reqMsg
	 *            文字信息
	 */
	// jiangxujin add (0x1053)
	public static void exec_CofC_Request_Info(DataInputStream dis) {
		try {
			Log.v("yz", "收到申请商会");
			final long id = dis.readLong();
			final long cofcId = dis.readLong();
			String reqMsg = dis.readUTF();
			
			Message message = Message.obtain();

			message.what = GameDefinition.Message_show_dailog;

			Bundle bundle = new Bundle();
			
			bundle.putLong("id", id);
			bundle.putLong("cofcId", cofcId);
			bundle.putString("reqMsg", reqMsg);
			
			message.obj = bundle;

			message.arg1 = 4;

			GameData.mhandler.sendMessage(message);

//			Builder builde = new AlertDialog.Builder(context);
//
//			builde.setMessage(
//					MainActivity.resources
//							.getString(R.string.analysisdata_request_info_message))
//					.setPositiveButton(
//							MainActivity.resources
//									.getString(R.string.analysisdata_request_info_ok),
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// TODO Auto-generated method stub
//									byte agree = 0;// 同意
//									Connection
//											.sendMessage(
//													GameProtocol.CONNECTION_SEND_CofC_Request_Resp,
//													ConstructData
//															.getCofC_Request_Resp(
//																	id, cofcId,
//																	agree));
//									dialog.dismiss();
//								}
//							})
//
//					.setNegativeButton(
//							MainActivity.resources
//									.getString(R.string.analysisdata_request_info_no),
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// TODO Auto-generated method stub
//									byte agree = 1;// 不同意
//									Connection
//											.sendMessage(
//													GameProtocol.CONNECTION_SEND_CofC_Request_Resp,
//													ConstructData
//															.getCofC_Request_Resp(
//																	id, cofcId,
//																	agree));
//									dialog.dismiss();
//								}
//							});
//			builde.create().show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会成员变更
	 * @param relation
	 *            0新增；1修改；2删除
	 * @author 商会会员基本信息
	 * @param dis
	 * @param memId
	 *            会员id
	 * @param name
	 *            会员名字
	 * @param gender
	 *            会员姓名
	 * @param duty
	 *            会员职务
	 * @param level
	 *            会员等级
	 * @param companyLevel
	 *            会员公司等级
	 */
	// jiangxujin add (0x1057)
	public static void exec_COfC_Member_Change_Info(DataInputStream dis) {
		try {
			byte type = dis.readByte();
			MemberEmployee emp = readMemberemployee(dis);

			if (type == 0 || type == 1) {
				GameData.addMemberEmployee(emp);

			} else if (type == 2) {
				GameData.deleteMebEmployee(emp.memId);

			}
			Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(CommerceStaffActivity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeData;
			Bundle bundle = new Bundle();
			bundle.putString("msg", emp.toString());
			message.setData(bundle);

			message.arg1 = 11;

			GameData.mhandler.sendMessage(message);
			/*
			 * Widget w = GameRun.getCurWidget(); if (w != null && w instanceof
			 * MemberManagement) { MemberManagement tabw = (MemberManagement) w;
			 * MemberSystemMeb mm = (MemberSystemMeb) tabw.getWidget(2);
			 * mm.updateList();
			 * 
			 * }
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会最近操作日志
	 * @param String
	 *            info
	 * @param String
	 *            opTime
	 */
	// jiangxujin add(0x1070)
	public static void exec_COfC_CofcLogList(DataInputStream dis) {
		try {
			byte size = dis.readByte();

			for (int i = 0; i < size; i++) {
				String str[] = new String[2];
				str[0] = SystemAPI.getText(dis.readUTF());
				str[1] = SystemAPI.getText(dis.readUTF());
				if (GameData.mebstr.size() < GameData.mebMaxsize) {
					GameData.mebstr.addElement(str);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会操作日志追加
	 * @param String
	 *            info
	 * @param String
	 *            opTime
	 */
	// jiangxujin add (0x1071)
	public static void exec_COfC_CofCLogAdd(DataInputStream dis) {
		try {
			String str[] = new String[2];
			str[0] = SystemAPI.getText(dis.readUTF());

			str[1] = SystemAPI.getText(dis.readUTF());

			if (GameData.mebstr.size() > GameData.mebMaxsize) {
				GameData.mebstr.removeElementAt(GameData.mebstr.size() - 1);
				// GameData.mebstr.removeElementAt(0);
			}
			GameData.mebstr.insertElementAt(str, 0);

		/*	Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(CommerceDiaryActivity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeActivity;
			message.arg1 = 7;
			GameData.mhandler.sendMessage(message);*/

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会联合宣传模板信息包
	 * @param size
	 *            模板数量
	 * @param id
	 *            模板id
	 * @param name
	 *            模板名字
	 * @param resid
	 *            模板图标
	 * @param addIncome
	 *            收入提高百分比
	 * @param duration
	 *            持续时间：单位小时
	 * @param joinNum
	 *            参加人数上限.
	 * @param morale
	 *            士气提升
	 * @param money
	 *            花费金钱
	 * @param desc
	 *            模板描述
	 */

	// jiangxujin add (0x1073)
	public static void exec_COfC_JointAdvocacyTemplates(DataInputStream dis) {
		try {
			byte size = dis.readByte();
			GameData.jointId = new byte[size];
			GameData.jointName = new String[size];
			GameData.jointResID = new int[size];
			GameData.jointIncome = new short[size];
			GameData.jointDuration = new short[size];
			GameData.joinNum = new short[size];
			GameData.joinMorale = new short[size];
			GameData.joinMoney = new long[size];
			GameData.joinDesc = new String[size];
			for (int i = 0; i < size; i++) {
				GameData.jointId[i] = dis.readByte();
				GameData.jointName[i] = dis.readUTF();
				GameData.jointResID[i] = dis.readInt();
				GameData.jointIncome[i] = dis.readShort();
				GameData.jointDuration[i] = dis.readShort();
				GameData.joinNum[i] = dis.readShort();
				GameData.joinMorale[i] = dis.readShort();
				GameData.joinMoney[i] = dis.readLong();
				GameData.joinDesc[i] = dis.readUTF();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author 商会联合宣传列表
	 * @param size
	 *            列表大小
	 * @param id
	 *            宣传id
	 * @param name
	 *            宣传名字
	 * @param resid
	 *            图标
	 * @param addincome
	 *            收入提高百分比
	 * @param duration
	 *            持续时间：单位小时
	 * @param morale
	 *            士气提升
	 * @param money
	 *            花费金钱
	 * @param joinNumMax
	 *            参加人数上限
	 * @param joinNum
	 *            参加人数
	 * @param desc
	 *            描述
	 */
	// jiangxujin add (1074)
	public static void exec_COfC_JointAdvocacy_List(DataInputStream dis) {
		try {
			byte size = dis.readByte();
			GameData.pro = new Propaganda[size];
			Log.i("ppq"," "+GameData.pro.length );
			Propaganda propa = null;
			for (int i = 0; i < GameData.pro.length; i++) {
				propa = new Propaganda();
				Log.i("ppq"," "+GameData.pro.length );
				//GameData.pro[i].id = dis.readLong();
				propa.id = dis.readLong();
				Log.i("ppq","----- "+propa.id );
				propa.name = dis.readUTF();
				propa.resid = dis.readInt();
				propa.addIncome = dis.readShort();
				propa.duration = dis.readShort();
				propa.morale = dis.readShort();
				propa.money = dis.readLong();
				propa.joinNumMax = dis.readShort();
				propa.joinNum = dis.readShort();
				propa.desc = dis.readUTF();
				GameData.pro[i] = propa;
			}
			if(CommerceInfoActivity.mContext == null){
			Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(PropagandaListActivity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeActivity;
			message.arg1 = 8;
			GameData.mhandler.sendMessage(message);
			Log.i("ppq", "zhixingdifang ");
			Log.i("ppq", "analysis");
			}else {
				/*Message message = Message.obtain();
				Vector v = new Vector();
				v.addElement(CommerceInfoActivity.class);
				message.obj = v;
				message.what = GameDefinition.Message_changeActivity;
				message.arg1 = 17;
				GameData.mhandler.sendMessage(message);*/
			}
			/*
			 * JointPropaList list = new JointPropaList(null); list.setBounds(0,
			 * 0, SysDef.SCREEN_W, SysDef.SCREEN_H); list.init();
			 * list.updata(desc, resid, id, money); GameRun.addWidget(list,
			 * true);
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** jiangxujn add (0x1040) */
	public static void exec_Resp_New_Mail_Coming(DataInputStream dis) {
		if (dis != null) {
			try {
				byte id = dis.readByte();
				if (id == 0) {
					// GameRun.isNewMailComing = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 彩票列表信息
	 * */
	public static void exec_LotteryTicket(DataInputStream dis) {
		try {
			int index = 0;
			byte size = dis.readByte();
			GameData.lottery_tickey_info = new String[size * 3];
			for (int i = 0; i < size; i++) {
				String name = dis.readUTF();
				String num = dis.readUTF();
				byte type = dis.readByte();
				GameData.lottery_tickey_info[3 * i] = name;
				GameData.lottery_tickey_info[3 * i + 1] = num;
				GameData.lottery_tickey_info[3 * i + 2] = String.valueOf(type);

			}
			Message message = Message.obtain();
			Vector v = new Vector();
			v.addElement(Lottery_Station_Activity.class);
			message.obj = v;
			message.what = GameDefinition.Message_changeActivity;
			message.arg1 = 10;
			GameData.mhandler.sendMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("错误-------------加载彩票列表");
		}
	}

	public static void exec_LotteryTicket_info(DataInputStream dis) {
		try {
			String name = dis.readUTF();
			/*
			 * if (!Tutorial.isInTutorial()) { final ConformDialogEx cd = new
			 * ConformDialogEx("彩票中奖信息", name); cd.addActionListener(new
			 * ActionListener() { public void actionPerformed(Object sender) {
			 * if (sender == cd.buttonLeft) {
			 * Connection.sendMessage(GameProtocol.LOTTERY_TACKET_ASK_LIST,
			 * Connection.getLotteryTicketAskList((byte)2)); cd.deactive(); }
			 * else if (sender == cd.buttonRight) { cd.deactive(); } } });
			 * cd.setBounds(25, (SysDef.SCREEN_H - 180) >> 1, SysDef.SCREEN_W -
			 * 50, 180); cd.cover(); GameRun.addWidget(cd, true); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("错误-------------加载彩票中奖信息");
		}
	}

	/**
	 * 
	 * @param dis
	 */
	public static void exec_CountDownTime(DataInputStream dis) throws Exception {
		byte type = dis.readByte();
		GameData.lastTime = dis.readInt();
		GameData.curReciveTime = System.currentTimeMillis();
	}

	public static void exec_Resp_QuestionInfo(DataInputStream dis) {
		Log.i("Log", "QuestionInfo");
		try {
			int taskId = dis.readInt();// 任务ID
			String title = dis.readUTF();// 标题
			byte pass = dis.readByte();// 已答题数
			byte right = dis.readByte();// 答对题数
			byte totalSize = dis.readByte();// 总题数
			String question = dis.readUTF();// 问题
			byte size = dis.readByte();// 选项个数
			String[] options = new String[size];
			for (int i = 0; i < options.length; i++) {
				options[i] = dis.readUTF();
				Log.i("pealo", "第"+i+"个选项："+options[i]);
			}
			Log.i("pealo " ,"总题数：——————"+totalSize);
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeActivity;

			Vector v = new Vector();

			v.addElement(AnswersQuesActivity.class);

			Bundle bundle = new Bundle();
			bundle.putInt("taskId", taskId);
			bundle.putString("title", title);
			bundle.putByte("pass", pass);
			bundle.putByte("right", right);
			bundle.putByte("totalSize", totalSize);
			bundle.putString("question", question);
			bundle.putByte("size", size);
			bundle.putStringArray("options", options);
			v.addElement(bundle);

			message.obj = v;
			message.setData(bundle);
			message.arg1 = 20;
			GameData.mhandler.sendMessage(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 小博士基本信息
	 * @param is
	 */
	public static void exec_Doctor_Base_Info(DataInputStream dis){
		/**
		 * 博士基本信息
		 * */
		
		
			try {
				GameData.doctorLevel = dis.readInt();
				GameData.doctorPower = dis.readInt();
				GameData.doctorMaxPower = dis.readInt();
				GameData.doctorPoint = dis.readLong();
				GameData.doctorGain = dis.readShort();
//				System.out.println("级别："+GameData.doctorLevel+"力量："+GameData.doctorPower+"最大力量："+GameData.doctorMaxPower+"点数："+GameData.doctorPoint+"附加值："+GameData.doctorGain);
				short trustSize = dis.readShort();
				GameData.trustId = new int[trustSize]; 
				GameData.trustlevel = new byte [trustSize];
				GameData.trustName = new String[trustSize];
				GameData.trustType = new byte[trustSize]; 
				GameData.trustTarget = new byte[trustSize];
				for(int i=0;i<trustSize;i++)
				{
					GameData.trustId[i] = dis.readInt();
					GameData.trustlevel [i]= dis.readByte();
					GameData.trustName[i] = dis.readUTF();
					GameData.trustType[i] = dis.readByte();
					GameData.trustTarget[i] = dis.readByte();
					System.out.println("托管id："+GameData.trustId[i]+"名称："+GameData.trustName[i]+"类型："+GameData.trustType[i]+"针对对象"+GameData.trustTarget[i]);
				} 
				int size = dis.readShort();
				for(int i=0;i<size;i++)
				{ 
					updataHiddedShopID(dis.readLong());
				} 
				/*GameData.hiddedShopId = null; 
				GameData.hiddedShopId = new long[size];
				for(int i=0;i<size;i++)
				{
					GameData.hiddedShopId[i] = dis.readLong();
				}*/
				/*Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("d_level", GameData.doctorLevel );
				intent.putExtra("doctor", bundle);*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	/**
	 * 更新小博士隐藏店铺ID
	 * @param value
	 */
	public static void updataHiddedShopID(long value)
	{
		for(int i=0;i<GameData.hiddedShopId.length;i++)
		{
			if(value == GameData.shopTemplate_id[GameData.hiddedShopId[i][0]])
			{
				GameData.hiddedShopId[i][1] = 0;
				break;
			}
		}
	}

	/**
	 * 博士信息更新
	 * */
	public static void Staff_Doctor_updataInfo(DataInputStream dis)
	{
		try {
			int size = dis.readByte();
			for(int i=0;i<size;i++)
			{
				int type = dis.readByte();
				switch(type)
				{
				case 1://等级
					GameData.doctorLevel = dis.readInt();
					break;
				case 2://体力
					GameData.doctorPower = dis.readInt();
					break;
				case 7://最大体力
					GameData.doctorMaxPower = dis.readInt();
					break;
				case 3://点数
					GameData.doctorPoint = dis.readLong();
					break;
				case 4://加成
					GameData.doctorGain = (short)dis.readShort();
					break;
				case 5://任务托管
					int trustId = dis.readInt();
					GameData.trustType [trustId-1] = dis.readByte();
					System.out.println("修改托管"+trustId+",状态为 ="+GameData.trustType [trustId-1]);
					break;
		/*		case 6://激活店铺个数
					int num = dis.readInt();
					GameData.shopTemplate_id = null; 
					GameData.shopTemplate_id = new long[num];
					for(int j=0;j<num;j++)
					{
						GameData.shopTemplate_id[j] = dis.readLong();
					}
					break; */
				case 6://激活店铺个数
					int num = dis.readShort();
					for(int j=0;j<num;j++)
					{ 
						updataHiddedShopID(dis.readLong());
					} 
					break;
				}
			}
			if (DoctorCustodyActivity.mContext != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 12;// 小博士数据修改
				message.arg2 = 1;
				GameData.mhandler.sendMessage(message);
				}
			if (DoctorInfoActivity.mContext != null) {
				Message message = Message.obtain();

				message.what = GameDefinition.Message_changeData;

				message.arg1 = 12;// 小博士数据修改
				message.arg2 = 0;
				GameData.mhandler.sendMessage(message);
				}
		if (DoctorShopActivity.mContext != null) {
				Message message = Message.obtain();
				
				message.what = GameDefinition.Message_changeData;
				
				message.arg1 = 12;// 小博士数据修改
				message.arg2 = 3;
				GameData.mhandler.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @author 问题正确答案
	 * @param dis
	 * @param  title 主标题
	 * @param  pass  已回答完的题数
	 * @param right 答对的题数
	 * @param totalSize 总题目数
	 * @param  question 问题描述
	 * @param  answer 问题答案
	 * @param hasNext是否还有未回答的问题(1有,0无)
	 * @param taskId 任务ID
	 */
	public static void exec_QuestionResult_info_list(DataInputStream dis)
	{
		try
		{
			String title = dis.readUTF();
			byte pass = dis.readByte();
//			byte right = dis.readByte();
//			byte totalSize = dis.readByte();
			String question = dis.readUTF();
			String answer = dis.readUTF();
			byte hasNext = dis.readByte();
			int taskId = dis.readInt();
			Message message = Message.obtain();

			message.what = GameDefinition.Message_changeActivity;

			Vector v = new Vector();

			v.addElement(TaskAnswerResultActivity.class);

			Bundle bundle = new Bundle();
			bundle.putInt("taskId", taskId);
			bundle.putString("title", title);
			bundle.putByte("pass", pass);
			bundle.putString("question", question);
			bundle.putString("answer", answer);
			bundle.putByte("hasNext",hasNext);
			v.addElement(bundle);

			message.obj = v;
			message.setData(bundle);
			message.arg1 = 19;
			GameData.mhandler.sendMessage(message);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void cardlist(DataInputStream dis)
	{
		try{
			 short size = dis.readShort();
			 GameData.card = new Card[size];
			 Card crd = null;
			 for(int i=0;i<size;i++){
				  crd = new Card();
				/* GameData.card[i].cardId = dis.readLong();
				 GameData.card[i].cardIcon = dis.readShort();
				 GameData.card[i].cardSmallicon = dis.readShort();
				 GameData.card[i].cardName = dis.readUTF();
				 GameData.card[i].cardDesc = dis.readUTF();
				 GameData.card[i].cardCollective = dis.readByte();
				 */
				  crd.cardId = dis.readLong();
				  crd.cardidx = dis.readShort();
				  crd.cardIcon = dis.readShort();
				  crd.cardSmallicon =  dis.readShort();
				  crd.cardName =  dis.readUTF();
				  crd.cardDesc = dis.readUTF();
				  crd.cardCollective = dis.readByte();
				  GameData.card[i] =crd;
				  Log.i("juj","id="+crd.cardId+ "icon="+crd.cardIcon+"small="
						  +crd.cardSmallicon+"name="+crd.cardName
						  +"desc="+crd.cardDesc+"collective="+crd.cardCollective  );
			 }
			
		    }catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	public static void Staff_Base_Info(DataInputStream dis)
	{
		try {
			Employee e =  Employee.readEmployee(dis);
			GameData.addEmployee(e, -1);
			Message message = Message.obtain();
			message.what = GameDefinition.Message_changeData;
			message.arg1 = 0;
			GameData.mhandler.sendMessage(message);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 联合宣传列表更新包
	 * 
	 * */
	public static void Staff_JointPropaUpdata(DataInputStream dis)
	{
		try { 
			
			byte type = dis.readByte();
			long id = dis.readLong();
			String text ="";
			byte tempId = 0; 
			
			if(type == 0)//修改
			{
				text = dis.readUTF();
				dis.readShort();
			}else if(type == 1)//删除
			{
				
			}else if(type ==2)//增加
			{
				tempId = dis.readByte();
				text = dis.readUTF();  
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 纸娃娃系统
	 * */
	public static void Staff_PaperDollResp(DataInputStream dis)
	{
		try { 
//			GameData.paperDollRespRes = new short[3];  
			for(int i=0;i<3;i++)
			{ 
				/*GameData.paperDollRespRes[i] =*/ dis.readShort(); 
			}
//			GameData.canDrawPaperDoll = true;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 礼包列表
	 * */
	public static void Staff_ExchangeList(DataInputStream dis)
	{
		try {  
			short size = dis.readShort();
//			GameData.propsFriendShip = new Props[size]; 
			
			for(int i=0;i<size;i++)
			{ 
				/*GameData.propsFriendShip[i] =*/ new Props();
				/*GameData.propsFriendShip[i].id =*/ dis.readLong(); 
				/*GameData.propsFriendShip[i].name =*/ dis.readUTF(); 
				/*GameData.propsFriendShip[i].desc =*/ dis.readUTF();
				/*GameData.propsFriendShip[i].icon =*/ dis.readShort(); 
				/*GameData.propsFriendShip[i].friendShipNum =*/ dis.readLong(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 徽章列表
	 * 
	 * */
	public static void Staff_badgeList(DataInputStream dis)
	{
		try {  
			byte type = dis.readByte();
			
			if(type == 0)
			{
				short size = dis.readShort();
				/*GameData.badgeList = new badgeItem[size];*/
				for(int i=0;i<size;i++)
				{
					/*GameData.badgeList[i] = new badgeItem();
					GameData.badgeList[i].id = */dis.readLong(); 
					/*GameData.badgeList[i].name =*/ dis.readUTF();
					/*GameData.badgeList[i].isGet =*/ dis.readByte();
					/*GameData.badgeList[i].isLight =*/ dis.readByte(); 
					/*GameData.badgeList[i].status =*/ dis.readByte();
					/*GameData.badgeList[i].icon =*/ dis.readShort();
					/*GameData.badgeList[i].desc =*/ dis.readUTF(); 
													dis.readShort();
					/*if(GameData.badgeList[i].isGet == 1)
					{ 
							GameData.addMyBadge(GameData.badgeList[i]); 
					}*/
				} 
			}else
			{
				short size = dis.readShort();
				/*GameData.FirendsBadge = null;
				GameData.FirendsBadge = new badgeItem[size];*/
				for(int i=0;i<size;i++)
				{
					/*GameData.FirendsBadge[i] = new badgeItem();
					GameData.FirendsBadge[i].id = */dis.readLong(); 
					/*GameData.FirendsBadge[i].name =*/ dis.readUTF(); 
					/*GameData.FirendsBadge[i].status =*/ dis.readByte();
					/*GameData.FirendsBadge[i].icon =*/ dis.readShort();
					/*GameData.FirendsBadge[i].desc =*/ dis.readUTF(); 
				} 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		/*if( GameData.myBadge == null ){
//			GameData.myBadge = new badgeItem[1];
//		}*/
//		if( GameData.myBadge == null || GameData.myBadge.length < UIManager.drawGridMaxNumber ){
//			Badge.maxPage = UIManager.drawGridYNumber;
//		} else {
//			/*int temp = ( (GameData.myBadge.length % UIManager.drawGridXNumber)>0 ? 1:0 );*/
//			Badge.maxPage = GameData.myBadge.length / UIManager.drawGridXNumber /*+ temp*/;
//		}
//		if( GameData.badgeList == null || GameData.badgeList.length < UIManager.drawGridMaxNumber ){
//			AchievementBadge.maxPage = UIManager.drawGridYNumber;;
//		} else {
//			/*int temp = ((GameData.badgeList.length % UIManager.drawGridXNumber)>0 ? 1:0);*/
//			AchievementBadge.maxPage = GameData.badgeList.length / UIManager.drawGridXNumber /*+ temp*/ ;
//		}
//		if( GameData.FirendsBadge == null || GameData.FirendsBadge.length < UIManager.drawGridMaxNumber ){
//			FirendsBadge.maxPage = UIManager.drawGridYNumber;
//		} else { 
//			FirendsBadge.maxPage = GameData.FirendsBadge.length / UIManager.drawGridXNumber /*+ temp*/ ;
//		}
	}
	/**
	 * 徽章更新
	 * @param dis
	 */
	public static void exec_Badge_update(DataInputStream dis){
		try {
			
			byte isGet = 0;
			byte status = 0;
			short icon =0;
			int index =0; 
			/*badgeItem item = null;*/
			byte opType  = dis.readByte();
			long id = dis.readLong();
			
			switch( opType )
			{
			case 1://更新获取状态(获得徽章)
				isGet = dis.readByte();//是否得到徽章
				icon = dis.readShort();//获取图片
				
				/*index = GameData.lookBadge(GameData.badgeList, id);
				GameData.badgeList[index].isGet = isGet;
				GameData.badgeList[index].icon = icon;
				item = GameData.badgeList[index]; 
				*//**原判定 是否可点亮才放到自己的徽章列表里，修改 获得的徽章就可以放到自己的徽章里*//*
				if(item.isLight==1 item.isGet == 1 )
				{
					GameData.addMyBadge(item);
				}else
				{
					return;
				}*/
				break;
			case 2://删除
				isGet = dis.readByte();//,0未获取 1 获取
				
				/*index = GameData.lookBadge(GameData.badgeList, id);
				item = GameData.badgeList[index];
				item.isGet = isGet;
				GameData.delMyBadge(id);*/
				
				break;
			case 3://更新徽章点亮状态
				 status = dis.readByte();//0非点亮状态,1点亮状态
				 icon = dis.readShort();//获取图片
				 
				/*index = GameData.lookBadge(GameData.myBadge, id);
				item = GameData.myBadge[index];
				item.status = status;
				item.icon = icon;*/
			
				break;
			}
		} catch(Exception e) {
			System.out.println("更新徽章失败"+e);
		}

		/*if( GameData.myBadge == null || GameData.myBadge.length < UIManager.drawGridMaxNumber ){
			Badge.maxPage = UIManager.drawGridYNumber;
		} else {
			Badge.maxPage = GameData.myBadge.length / UIManager.drawGridXNumber ;
		}
		if( GameData.badgeList == null || GameData.badgeList.length < UIManager.drawGridMaxNumber ){
			AchievementBadge.maxPage = UIManager.drawGridYNumber;;
		} else {
			AchievementBadge.maxPage = GameData.badgeList.length / UIManager.drawGridXNumber ;
		}*/
	}
	
	/**
	 * 选区列表
	 * @param dis
	 */
	public static void exec_Election_List(DataInputStream dis){
		try {
			
			byte type = dis.readByte(); 
			short size = dis.readShort();
			switch(type)//0刷新，1新增，2变更,3删除
			{
				case 0:// 0刷新 
					break;
				case 1:// 1新增 
					for(int i=0;i<size;i++)
					{
						/*ElectionItem item =*/ getItem(dis);
//						GameData.addElection(item);
					}
					break;
				case 2:// 2变更  
				case 3:// 3删除
					/*ElectionItem item =*/ getItem(dis); 
//					GameData.delElection(item,type==3);
					
					break; 
			}
			
		} catch(Exception e) {
			System.out.println("选区列表失败"+e);
			e.printStackTrace();
		}
	}
	public static Object getItem(DataInputStream dis)
	{
//		ElectionItem item = new ElectionItem();
		try
		{
			/**  选区ID **/   
			/*item.ID =*/ dis.readInt();
			/**  弹劾者用户Id **/ 
			/*item.Delater =*/ dis.readLong();
			/**  当选者用户ID **/ 
			/*item.Electioner =*/ dis.readLong();
			/**  当选者头像ID**/
			/*item.ElectionerRes_id =*/ dis.readInt();
			/**  弹劾保证金 **/ 
			/*item.DelateFee =*/ dis.readLong();
			/**  竞选保证金 **/ 
			/*item.ElectionFee =*/ dis.readLong();
			/**  地图ID **/ 
			/*item.Map_id =*/ dis.readLong();
			/** 选举状态   1：参选报名  2：选举投票  3当选中 4：弹劾中   */ 
			/*item.Stat =*/ dis.readByte();
			/**  地图名称  **/ 
			/*item.Map_name =*/ dis.readUTF();
			/**  当选者名称 **/  
			/*item.ElectionerName =*/ dis.readUTF();
			/**商会名字 **/ 
			/*item.GuildName =*/ dis.readUTF(); 
			/**卸任倒计时**/ 
			/*item.ElectionRemainTime = */dis.readInt();
			/**支持票数**/ 
			/*item.AgreeCount =*/ dis.readInt();
			/**反对票数**/
			/*item.OpposeCount =*/ dis.readInt();
			 
			return null;
			
		}catch(Exception e)
		{ 
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 候选人列表
	 * @param dis
	 */
	public static void exec_Candiadate_List(DataInputStream dis){
		try {
			int currentElectionID = 0;
			byte type = dis.readByte(); 
			short size = dis.readShort();
			switch(type)//0刷新，1新增，2变更,3删除
			{
				case 0:// 0刷新 
					break;
				case 1:// 1新增 
					for(int i=0;i<size;i++)
					{
						/*CandidateItem item =*/ getCandiadateItem(dis);
//						GameData.addCandidate(item);
					}
					break;
				case 2:// 2变更
					/*CandidateItem.AgreeCount =*/ dis.readInt();
					break;
				case 3:// 3删除
					/*CandidateItem item =*/ getCandiadateItem(dis);
//					currentElectionID = item.Electionid;
//					GameData.delCandidate(item,type==3);
//					
//					Widget cand = Game.stackManager.currentWidget();
//					if(cand != null)
//						if(cand instanceof ElectionUI)
//						{
//							
//						}
					break; 
			}
//			if(type == 2||type == 3)
//			{
//				Widget cand = Game.stackManager.currentWidget();
//				if(cand != null)
//					if(cand instanceof CandidateList)
//					{
//						((CandidateList)cand).initList(currentElectionID);
//					}
//			}
		} catch(Exception e) {
			System.out.println("选区列表失败"+e);
			e.printStackTrace();
		}
	}
	public static Object getCandiadateItem(DataInputStream dis)
	{
//		CandidateItem item = new CandidateItem(null);
		try
		{
			/*item.ID =*/ dis.readLong();
			/*item.Userid =*/ dis.readLong();
			/*item.Electionid =*/ dis.readByte();
			/*item.CandidateName =*/ dis.readUTF();
			/*item.GuildName =*/ dis.readUTF();
			/*item.Fund =*/ dis.readLong(); 
			/*item.AgreeCount =*/ dis.readInt();
			/*item.OpposeCount =*/ dis.readInt();
			 
			return null;
			
		}catch(Exception e)
		{ 
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 分配竞选基金
	 * @param dis
	 */
	public static void exec_AlloteFund(DataInputStream dis){
		try {
			
			final long id = dis.readLong();
			final String info = dis.readUTF();
//			GameData.allotFundInfo = info;
//			final ConformDialogEx cd =   new ConformDialogEx("提示", info,ConformDialogEx.TWO_BUTTON);
//			cd.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(Object sender) {
//					if(sender == cd.buttonLeft)
//					{			
//						doItem(id);
//						cd.deactive() ;
//					}
//					else if(sender == cd.buttonRight)
//					{
//						cd.deactive() ;
//					}
//					
//				}
//				
//			}) ;
//			cd.setBounds(25, (SysDef.SCREEN_H - 180)>>1, SysDef.SCREEN_W - 50, 180);
//			cd.active() ;
//			cd.cover();//by  zzx 12.20
//			cd.setPriority(QueueManager.ConformDiaPriority);
//			Game.instance.queueManager.addWidget(cd, true);
			
		} catch(Exception e) {
			System.out.println("分配竞选基金");
			e.printStackTrace();
		}
	}
}
