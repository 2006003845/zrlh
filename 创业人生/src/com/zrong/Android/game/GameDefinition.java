package com.zrong.Android.game;

import android.graphics.Typeface;

/**
 * 
 *<p>Titile:GameDefinition</p>
 *<p>Description:这个类用来定义第一所有需要定义的游戏常量</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 * @date Jul 8, 2011
 */
public class GameDefinition
{
	
//	public static Typeface face;
    /**
     * 屏幕宽
     */
	public static int screenWidth;
	/**
	 * 屏幕高
	 */
	public static int screenHeight;
	
	/*开始-> 游戏逻辑状态 */
	/**
	 * 播放视频
	 */
	public static final int Game_PowerVideo = 0;
	/**
	 * logo
	 */
	public static final int	Game_Logo		= 1;
	public static final int	Game_MainMenu	= 2;
	
	/**
	 * 登录界面
	 */
	public static final int Game_Login = 7;
	/**
	 * 分区分线
	 */
	public static final int Game_SelectSever = 8;
	/**
	 * loading
	 */
	public static final int Game_Loading = 9;
	
	/**
	 * 选择角色
	 */
	public static final int Game_CreateCharactor = 10;
	
	/**
	 * 提示信息
	 */
	public static final int Game_TipBox = 11;
	
	/**
	 * 办公室
	 */	
	public static final int Game_Office = 12;
	
	/**
	 * 地图
	 */
	public static final int Game_Map = 13;
	
 
	/**
	 * 创建建筑
	 */
	public static final int Game_CreateBuilding =14;
	
 
	/**
	 * 查看店铺详情
	 */
	public static final int Game_ShopInfo = 15;
	
	
	/*结束->游戏逻辑状态 */
	
	
	
	/*开始 View界面的id*/
	/**
	 * 视频播放界面
	 */
	public static final int VideoPlayerView = 0;
	/**
	 * 主菜单界面
	 */
	public static final int MainMenuView = 1;
	/**
	 * 登录界面
	 */
	public static final int LoginView = 2;
	/**
	 * 分区分线
	 */
	public static final int SelectSever = 3;
	/**
	 * loading...
	 */
	public static final int LoadingView = 4;
	
	/**
	 * 提示信息框
	 */
	public static final int TipBoxView = 5;
	/**
	 * 创建角色
	 */
	public static final int CreateCharactorView = 6;
	/**
	 * 办公室界面
	 */
	public static final int OfficeView = 7;
	
	/**
	 * 地图界面
	 */
	public static final int MapView = 8;
	
	/**
	 * 创建建筑界面
	 */
	public static final int CreateBuildingView = 9;
	
	/**
	 * 店铺详情
	 */
	public static final int ShopInfoView = 10;
	
	
	/*结束 View界面的id*/
	
	
	
	//显示下一条信息(用于对话模式)
	public static final int PAGEDOWN_KEYPRESS	=11;
	//确认(用于对话模式)
	public static final int OK_KEYPRESS		=12;
	//取消(用于对话模式)
	public static final int CANCEL_KEYPRESS	=13;
	
	
	//++++++++++++++++++++连接地址+++++++++++++++++++++++++++
//	/**
//	 * 分区分线
//	 */ 
//	public static String ServerSelecetURL="http://112.25.14.45/AreaLine/getGameLineServer?version=10100";
//	/**
//	 * 高地位
//	 */
//	public static  String agentCodeHi = "0";
//	public static  String agentCodeLo = "0"; 
//	
//	public static String userId="";
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++
	
	public static final int Message_SetGameStatus = 1;
	
	public static final int Message_removeView = 2;
	
	public static final int Message_changeActivity = 3;
	
	public static final int Message_showToast = 4;
	
	public static final int Message_changeData = 5;
	
	public static final int Message_task_detail = 6;
	
	public static final int Message_show_dailog = 7;
	
	public static final int Message_update_progress = 8;
	
	//====================================
	public static final int RESULT_OK = 0;
	
	public static final int RESULT_CANCEL = 1;
	
	//=======================================
	
	public static final int REQUEST_BUYGOOD = 0;
	
	public static final int REQTASK_DETAILS = 1;
	
	public static final int REQWRITE_MAIL = 2;
	
	public static final int REQWRITE_MESSAGE = 3;//请求写消息
	//=======================================
	
	
}

