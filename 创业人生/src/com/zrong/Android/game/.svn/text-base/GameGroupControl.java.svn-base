package com.zrong.Android.game;

import java.util.Vector;

import org.dom4j.Text;

import res.ResManager;

import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.Preferences;
import com.zrong.Android.Util.SystemAPI;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.MoreGame;
import com.zrong.Android.activity.R; 
import com.zrong.Android.activity.SystemSettingActivity;

import com.zrong.Android.logic.Loading;
import com.zrong.Android.logic.LogicObject;
import com.zrong.Android.logic.LogicObjectQueue;
import com.zrong.Android.logic.MainMenu;

import com.zrong.Android.logic.VideoPlayer;
import com.zrong.Android.online.network.GameProtocol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Canvas;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 *<p>
 * Titile:GameGroupControl
 * </p>
 *<p>
 * Description:
 * </p>
 *<p>
 * Copyright:Copyright(c)2010
 * </p>
 *<p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 * @date Aug 14, 2011
 */

public class GameGroupControl extends RelativeLayout implements Runnable {

	public static final String TAG = "GameGruopControl";

	/**
	 * 游戏状态
	 */
	public int mIGameStatus = -1;

	/**
	 * 是否开启线程
	 */
	public boolean mBLoop = false;

	/**
	 * 逻辑队列
	 */
	public LogicObjectQueue logic;

	public static GameGroupControl gameGroupControl;

	public static Connection connection;

	public static EventManager eventManger;

	public static Preferences preferences;
	


	/**
	 * 保存游戏执行状态,先进先出
	 */
	public Vector gamestatueQueue;

	public GameGroupControl(Context context) {
		super(context);
		gameGroupControl = this;
	}

	/**
	 * 初始化游戏
	 */
	public void initGame() {
		mBLoop = true;

		connection = new Connection(this.getContext(), this);// 连接
		eventManger = new EventManager(this.getContext(), this);// 事件管理器
		preferences = new Preferences(this.getContext(), this);// 首选项

		logic = new LogicObjectQueue(this.getContext(), this);

		Script.initScript();
//      正常开始
		setGameStatus(GameDefinition.Game_Logo, null);// 设置游戏状态
//		进入主菜单
//		GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
//		MainActivity.mContext.setContentView(GameGroupControl.gameGroupControl);

	/*	MainMenu.seting[0] = preferences.getBoolean(
				preferences.MUSICOPEN, false);
		MainMenu.seting[1] = preferences.getBoolean(
				preferences.FRIENDACCEPT, true);
		MainMenu.seting[2] = preferences.getBoolean(
				preferences.WORLDCHATACCEPT, true);*/
		SystemSettingActivity.state[0] = preferences.getBoolean(
				preferences.MUSICOPEN, false);
		SystemSettingActivity.state[1] = preferences.getBoolean(
				preferences.FRIENDACCEPT, true);
		SystemSettingActivity.state[2] = preferences.getBoolean(
				preferences.WORLDCHATACCEPT, true);
		SystemSettingActivity.state[3] = preferences.getBoolean(
				preferences.PRIVATECHATACCEPT, true);

		byte[] b = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Vector v = new Vector();
		v.addElement(b);

		Thread t = new Thread(this);
		t.start();
	}

	

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		//zhouzhilong add
		if (gameGroupControl.logic.get("Loading") != null) {
			((Loading) gameGroupControl.logic.get("Loading")).destroy();
		}

		// KeyEvent.KEYCODE_BACK:键盘上的[back]按键
		// 捕获按键：按下[back]键并且没有重复
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{	
			if(!GameData.isEnterMap)
			{
				if(MainMenu.mainmenu!=null&&MainMenu.mainmenu.showDialog)
				{
					MainMenu.mainmenu.closeMiddleDialog(-1);
				}else
				showDialog1();
			} 
			else
			{
				final AlertDialog dlg2 = new AlertDialog.Builder(MainActivity.mContext).create();
				dlg2.show();					
				dlg2.getWindow().setContentView(R.layout.dialog_new);
				Button confirm =(Button)dlg2.findViewById(R.id.button_confirm);
				Button cancel =(Button)dlg2.findViewById(R.id.button_cancel);
				TextView tv = (TextView)dlg2.findViewById(R.id.notice_message);
				tv.setTextSize(16);
				tv.setText(MainActivity.resources.getString(R.string.gamegroupcontrol_setmessage));
				confirm.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub

						if (GameData.player != null
								&& Connection.getConnection() != null) 
						{
							GameData.isExitRequest = true;
 
							logic.clear();
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_SEND_CHRARCTER_LOGOUT,
											ConstructData
													.getLogout(GameData.player.id));

							GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
							GameData.isEnterMap = false;
						}
					dlg2.dismiss();
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg2.dismiss();
					}
				});
				
				
			/*	
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.mContext);
				builder.setMessage(MainActivity.resources.getString(R.string.gamegroupcontrol_setmessage))
						// 设置对话框内容
						.setTitle(MainActivity.resources.getString(R.string.gamegroupcontrol_settitle));
				
				builder.setPositiveButton(MainActivity.resources.getString(R.string.gamegroupcontrol_ok), new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which) 
					
					
					{
						if (GameData.player != null
								&& Connection.getConnection() != null) 
						{
							GameData.isExitRequest = true;
 
							logic.clear();
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_SEND_CHRARCTER_LOGOUT,
											ConstructData
													.getLogout(GameData.player.id));

							GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
							GameData.isEnterMap = false;
						}
					}
				}
				);
				
				builder.setNegativeButton(MainActivity.resources.getString(R.string.gamegroupcontrol_cancel), new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which) 
					{
						
						
					}
					
				});
				 
				builder.create().show();
				*/
			}
			 
			
		}
		return false;
	}

	public void showDialog1() {
		final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
		dlg.show();					
		dlg.getWindow().setContentView(R.layout.dialog_new);
		Button confirm =(Button)dlg.findViewById(R.id.button_confirm);
		Button cancel =(Button)dlg.findViewById(R.id.button_cancel);
		confirm.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dlg.dismiss();// 关闭对话框
				Music.getInstance(MainActivity.mContext).stop();
				try {
					if (GameData.player != null
							&& Connection.getConnection() != null) {
						GameData.isExitRequest = true;
						Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_CHRARCTER_LOGOUT,
										ConstructData
												.getLogout(GameData.player.id));
						ResManager.close();
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
				final AlertDialog dlg1 = new AlertDialog.Builder(MainActivity.mContext).create();
				dlg1.show();					
				dlg1.getWindow().setContentView(R.layout.dialog_new);
				Button confirm =(Button)dlg1.findViewById(R.id.button_confirm);
				Button cancel =(Button)dlg1.findViewById(R.id.button_cancel);
				TextView tv = (TextView)dlg1.findViewById(R.id.notice_message);
				if (GameDef.channelId.equals(GameDef.dangle)) {
					tv.setText(R.string.dangle_exit_message);
					tv.setTextSize(15);
				} else {
					tv.setText(R.string.cmcc_exit_message);
					tv.setTextSize(15);
				}
				confirm.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();

						Bundle bundle = new Bundle();

						if (GameDef.channelId
								.equals(GameDef.dangle)) {
							bundle.putString(
									"url",
									MainActivity.resources
											.getString(R.string.dangle_exit_url));
						} 
						else 
						{
							bundle.putString(
									"url",
									MainActivity.resources
											.getString(R.string.cmcc_exit_url));
						}
						
						intent.putExtras(bundle);

						MainActivity.mContext
								.Activitychange(
										MoreGame.class,
										intent);
						MainActivity.mContext.finish();
						
					dlg1.dismiss();
					
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainActivity.mContext.finish();
						dlg1.dismiss();
					}
				});
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
	}

	public boolean onTouchEvent(MotionEvent event) {
		int iAction = event.getAction();

		Log.v(TAG, "pointerCount=" + event.getPointerCount());

		// 根据获得的不同的事件进行处
		if (iAction == MotionEvent.ACTION_CANCEL) {
			Log.v(TAG, "ACTION_CANCEL");
		} else if (iAction == MotionEvent.ACTION_DOWN) {
			Log.v(TAG, "ACTION_DOWN");
		} else if (iAction == MotionEvent.ACTION_MOVE) {
			Log.v(TAG, "ACTION_DOWN");
		}
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (mIGameStatus) {
		case GameDefinition.Game_PowerVideo:
			VideoPlayer vp = (VideoPlayer) logic.get("VideoPlayer");
			vp.onTouch(null, event);
			break;
		case GameDefinition.Game_Logo:
			// 处理logo状态的触笔事件
			break;
		case GameDefinition.Game_MainMenu:
			// 处理主菜单状态的触笔事件
			break;
		default:
			break;
		}
		return true;
	}

	public void run() 
	{
		while (mBLoop)
		{
			SystemAPI.sleep(10);

			update();

			Connection.keepConnectionAlive();

			postInvalidate(); // 刷新屏幕
		}
	}

	/**
	 * 游戏逻辑处理方法
	 */
	public void update() {
		switch (mIGameStatus) {
		case GameDefinition.Game_PowerVideo:
			break;
		case GameDefinition.Game_Logo:
			break;
		case GameDefinition.Game_MainMenu:
			break;
		case GameDefinition.Game_Loading:
			break;
		case GameDefinition.Game_TipBox:
			break;
		case GameDefinition.Game_Map:
			break;
		}
	}

	
	
	/**
	 *设置游戏状态（中转站）
	 * 
	 * @param status
	 */
	public void setGameStatus(int status, Vector vec) {
		Log.v(TAG, "setGameStatus =" + status);
		LogicObject logicobj = null;
		mIGameStatus = status;

		switch (status) {
		case GameDefinition.Game_PowerVideo:
			break;
		case GameDefinition.Game_Logo:
			logicobj = logic.get("Logo", status);

			return;

		case GameDefinition.Game_MainMenu:
			logicobj = logic.get("MainMenu", status);
			break;
		case GameDefinition.Game_Login:
			logicobj = logic.get("Login", status);
			logicobj.loadProperties(vec);
			break;
		case GameDefinition.Game_SelectSever:
			logicobj = logic.get("SelectSever", status);
			logicobj.loadProperties(vec);
			break;
		case GameDefinition.Game_Loading:
			logicobj = logic.get("Loading", status);
			logicobj.loadProperties(vec);
			break;
		case GameDefinition.Game_CreateCharactor://角色创建
			logicobj = logic.get("CreateCharactor", status);
			logicobj.loadProperties(vec);
			break;
		case GameDefinition.Game_TipBox:
			logicobj = logic.get("TipBox", status);
			break;
//		case GameDefinition.Game_Office:
//			logic.clear();
//			logicobj = logic.get("Office", status);
//			logicobj.loadProperties(vec);
//			break;
		case GameDefinition.Game_Map:
			logic.clear();
			logicobj = logic.get("Map", status);
			break;

//		case GameDefinition.Game_CreateBuilding:
//			logicobj = logic.get("CreateBuilding", status);
//			logicobj.loadProperties(vec);
//			break;
//		case GameDefinition.Game_ShopInfo:
//			logicobj = logic.get("ShopInfo", status);
//			logicobj.loadProperties(vec);
//			break;
		}
		logicobj.refreshView();
	}
	
	

	/**
	 * 初始化某个逻辑,和setGameStatus的区别在于这个不改变当前游戏的状态
	 * 
	 * @param logic
	 * @param vec
	 */
	public void initLogic(int logicId, Vector vec) {
		LogicObject logicobj = null;
		switch (logicId) {
		case GameDefinition.Game_Loading:
			logicobj = logic.get("Loading", logicId);
			logicobj.loadProperties(vec);
			break;
		}
		logicobj.refreshView();
	}

	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
	}

	// public boolean onTouch(View view, MotionEvent event) {
	// // Bitmap bitmap =view.getDrawingCache();
	// //
	// //
	// // int count = event.getPointerCount();
	// // Log.v(TAG, "count =" + count);
	// //
	// // for(int i = 0 ; i < count ; i++)
	// // {
	// // event.getPointerId(i);
	// // event.getX(i);
	// // event.getX(i);
	// // }
	// return false;
	// }

}
