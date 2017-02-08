package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.List;

import res.ResManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.MapInfo;
import com.zrong.Android.entity.VentureSchoolData;
import com.zrong.Android.game.EventManager;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.game.MessageHandler;

/**
 * 
 *<p>
 * Titile:GameActivity
 * </p>
 *<p>
 * Description:
 * </p>
 * 游戏的启动类，没什么好说的，还需要再整理一下
 *<p>
 * Copyright:Copyright(c)2010
 * </p>
 *<p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 */
public class MainActivity extends GameActivity {
	public static MainActivity mContext = null;
	public static Resources resources = null;
	public static GameGroupControl gameGroupControl;
	private boolean bIsReleased = false;
	private boolean bIsPaused = false;
	private boolean bIsPlaying = false;
	public static EventManager eventManger;

	/* 创建 */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//yangzheng add 获取游戏版本号
		GameDef.current_version = (short)getVersionCode();
		//zhouzhilong add
		new VentureSchoolTask().execute((Void)null);//执行下载创业学堂中国地图的信息
		
		Display dis = getWindowManager().getDefaultDisplay();
		GameDefinition.screenWidth = dis.getWidth();// 获取屏幕宽度
		GameDefinition.screenHeight = dis.getHeight();// 获取屏幕高度
	
		mContext = this;
		 
		resources = mContext.getResources();
		GameDef.channelId = resources.getString(R.string.channel);

		GameDef.ServerSelecetURL = GameDef.serverseleceturl_1+GameDef.current_version+GameDef.serverseleceturl_2+GameDef.channelId;

		ResManager.init(null, 0);
		gameGroupControl = new GameGroupControl(mContext);
		gameGroupControl.initGame();

//		eventManger = new EventManager(mContext, null);// 事件管理器
//		MainActivity.mContext.Activitychange(GameLogoActivity.class, null);

		Looper looper = Looper.myLooper();

		GameData.mhandler = new MessageHandler(looper, this, gameGroupControl);
	}

	/* 创建菜单 */
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	/* 销毁 */
	protected void onDestroy() {
		super.onDestroy();
	}

	/* 按键按下 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		GameGroupControl.gameGroupControl.onKeyDown(keyCode, event);
		return false;
	}

	/* 按键重复按下 */
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	/* 按键释放 */
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.FLAG_CANCELED_LONG_PRESS:// 退出键
			break;
		case KeyEvent.KEYCODE_MENU:// 菜单键
			break;
		}

		return super.onKeyUp(keyCode, event);
	}

	/* 菜单事件 */
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return super.onMenuItemSelected(featureId, item);
	}

	

	
	/* 重新开始 */
	protected void onRestart() {
		super.onRestart();
	}


	/* 触笔事件 */
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	/* 设置标题 */
	// 使用MVC框架让资源文件通过R.java文件读取
	public void setTitle(int titleId) 
	{
		super.setTitle(titleId);
	}
	
	public void Activitychange(Class activityClass, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}
		
		intent.setClass(MainActivity.this, activityClass);
		this.startActivity(intent);//启动指定的Activity
	}

	public static Activity getTopActivty() 
	{
		ActivityManager activityManager = (ActivityManager) mContext
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> forGroundActivity = activityManager
				.getRunningTasks(1);
		RunningTaskInfo currentActivity;
		currentActivity = forGroundActivity.get(0);
		String activityname = currentActivity.topActivity.getClassName();
		if (activityname.equals("com.zrong.Android.activity.ComfirmActivity")) {
			return ComfirmActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.MainActivity")) {
			return MainActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.CompanyTabActivity")) {
			return CompanyTabActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.MapmainMenuActivity")) {
			return MapmainMenuActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.ShopInfoActivity")) {
			return ShopInfoActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.StoreActivity")) {
			return StoreActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.EmployeeInfoActivity")) {
			return EmployeeInfoActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.DepartmentActivity")) {
			return DepartmentActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.ShopListActivity")) {
			return ShopListActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.EmployeeListActivity")) {
			return EmployeeListActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.RecruitActivity")) {
			return RecruitActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.TaskActivity")) {
			return TaskActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.TaskDetailActivity")) {
			return TaskDetailActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.SocialActivity")) {
			return SocialActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.SocialDetail")) {
			return SocialActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.MailboxActivity")) {
			return MailboxActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.MailDetailActivity")) {
			return MailDetailActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.ChatWindowActivity")) {
			return ChatWindowActivity.context;
		} else if (activityname
				.equals("com.zrong.Android.activity.WriteMailActivity")) {
			return WriteMailActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.ChatActivity")) {
			return ChatActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.CreateShopActivity")) {
			return CreateShopActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.checkBuildingActivity")) {
			return CheckBuildingActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.CreateBuildingActivity")) {
			return CreateBuildingActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.SeacherFriendActivity")) {
			return SeacherFriendActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.DiglistActivity")) {
			return DiglistActivity.mContext;
		} else if (activityname
				.equals("com.zrong.Android.activity.SearchconditionActivity")) 
		{
			return SearchconditionActivity.mContext;
		}

		return null;
	}

	@Override
	public void finish() 
	{
		if(gameGroupControl!= null)
		{
			gameGroupControl.mBLoop = false;
			//mContext = null;
		}
		super.finish();
	}
	
	
	
	private class VentureSchoolTask extends
			AsyncTask<Void, WSError, ArrayList<Area>> {

		@Override
		protected ArrayList<Area> doInBackground(Void... params) {
			IGet2Api server = new Get2ApiImpl();
			ArrayList<Area> mapInfo = null;
			try {
				mapInfo = server.getMapInfo();
				Log.i("Log", "MainAtivity--mapinfo" + (mapInfo == null));
				// 获取政策消息列表
			} catch (WSError e) {
				e.printStackTrace();
			}
			return mapInfo;
		}

		@Override
		protected void onPostExecute(ArrayList<Area> result) {
			VentureSchoolData.mapInfo = new MapInfo(result);
			super.onPostExecute(result);
		}

	}


	@Override
	public GameActivity getGameContext() 
	{
		return this;
	}
	
	/**
	 * 获取游戏的versioncode
	 */
	public int getVersionCode()
	{
		PackageManager pm = getPackageManager();
		
		try {
			  PackageInfo pinfo;
			  pinfo = pm.getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
			  
			  return pinfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	

}