/**
 * 
 */
 
package com.zrong.Android.game;

 

import java.util.Vector;

import res.BuildingSprite;
import res.Draw;
import res.Map;

import android.os.Message;
import android.util.Log;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.GameAPI;
import com.zrong.Android.Util.SystemAPI;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;

import com.zrong.Android.View.FreshManLead;

import com.zrong.Android.element.Branch;
import com.zrong.Android.element.Chat;
import com.zrong.Android.element.Corporation;
import com.zrong.Android.element.Department;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Member;
import com.zrong.Android.element.MemberEmployee;
 
import com.zrong.Android.element.Card;
import com.zrong.Android.element.Player;
import com.zrong.Android.element.Promoter;
import com.zrong.Android.element.Props;
import com.zrong.Android.element.PublicBuilding;
import com.zrong.Android.element.Quality;
import com.zrong.Android.element.Shop;
import com.zrong.Android.element.Skill;
import com.zrong.Android.element.Social;
import com.zrong.Android.element.Task;
import com.zrong.Android.element.Propaganda;


 

/**
 *<p>Titile:GameData</p>
 *<p>Description:游戏数据类</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public class GameData {
	
	public static boolean isEnterMap = false;
	
	public static byte Months=0,Date=0,Hour=0,Minute=0;
	
	public static MessageHandler mhandler;
	
	public static boolean isEnterMainMenu = false;
	
	/** 倒计时时间*/
	
	public static int lastTime;
	public static long curReciveTime;
	
	/**彩票系统*/
	public static final String LOTTERY_TICKET = MainActivity.resources.getString(R.string.gamedata_lottery_ticket);
	public static final String LOTTERY_TICKET_INFO = MainActivity.resources.getString(R.string.gamedata_lottery_ticket_info);
	public static final String LOTTERY_TICKET_FONT = MainActivity.resources.getString(R.string.gamedata_lottery_ticket_font);
	public static String lottery_tickey_info[];
	
	public static boolean isExitRequest=false;
	public static int key1;
	public static int key2;
	
	public static Map map;
	
	public static Shop recruitShop[];
	public static long recruitShopId;//发起招揽店铺id
	
	public static boolean  isLogined = false;
	 
	public static boolean isGuest;
	
	public static String mebName = "";//商会性名
	
	/**二次确认----输入框信息*///jiangxujin add
	public static int TwoSureMsg_Input_id ;
	public static byte TwoSureMsg_Input_controlSize ;
	public static byte TwoSureMsg_Input_controlType[];
	public static String TwoSureMsg_Input_key[];
	public static String TwoSureMsg_Input_label[];
	
	
	
	/***用户创建接受数据的变量**/
	public static byte usersStatus = -1 ;//创建结果 0成功 -1失败 -2需要
	public static long userId  = 0;//用户id（status为0时需要）
	public static String userMsg  = "";//提示信息

	/***性别***/
	public static final String[] Sex ={MainActivity.resources.getString(R.string.gamedata_woman), MainActivity.resources.getString(R.string.gamedata_man),};
	public static int selctIndex ;//选择人物头像
	public static int colorMsg = 0xff00ff;
	/***公司创建接受数据的变量**/
	public static byte cpStatus = 0 ;//创建结果 0成功 -1失败 -2需要
	public static String cpMsg  = "";//提示信息
	public static String cpName = "";
	
	public static final short ORIGINAL_MAP_X=544;
	public static final short ORIGINAL_MAP_Y=352;
	
	
	/**屏幕左上角的X坐标*/ 
	public static int mapX=10000;  
	public static int mapY=10000;
	
	/**光标所在位置的xy坐标 */
	public static int focusX;
	public static int focusY;
	/**移动前光标所在位置 */
	public static int original_focusX;
	public static int original_focusY;
	/**中心街道的位置 */
	public static int centerX;
	public static int centerY;
	
	public static boolean isNeedNewMapData()
	{
		if(Math.abs(original_focusX-focusX) > 200 || Math.abs(original_focusY-focusY) > 100)
		{
			return true;
		}
		return false;
	}
	
	public static void frushOriginalFocus()
	{
		original_focusX = focusX;
		original_focusY = focusY;
	}
	
	/**
	 * 升级信息
	 */
	public static Vector<Vector> levelUpInfo = null;
	
	
	/**以光标为中心，一定范围内的个子的xy坐标，用来给建筑分配位置和触摸屏判断位置，目前是以光标所在位置为中心点的7x7的范围 */
	public static final int ARRAY_LENTH=11;
	public static int xyArray[][]=new int[11*11][2];
	
	public static int smallxyArray[][] = {{0,22},{-22,11},{-44,0},{22,11},{0,0},{-22,-11},{44,0},{22,-11},{0,-22}};
	
	/**光标的所在的街道值 */
	public static short cityX=0;
	public static short cityY=0;
	
	
	public static short series_X[]={0,7,14,21,4,11,18,1,8,15,22,5,12,19,2,
									 9,16,23,6,13,20,3,10,17};
	
	public static final short[][] SmallGRID_OffXY={{/*1,39*/ 0,36},{/*-23,20*/-24,24},{-48,12},
		{24,24},{0,12},{-24,0},
		{48,12},{24,0},{0,-13}
	};
	
	/**
	 * 当前地图上的建筑
	 */
	public static BuildingSprite[] build;
	
	/**盖建筑的街道 */
	public static short buildCityX;
	public static short buildCityY;
	
	
	
	
	//建筑模板信息
	public static byte community_num;//add by zzx   记录可以创建工艺建筑的个数
	public static byte buildingId[];
	public static byte buildingScale[];
	public static int buildingResId[];
	public static String buildingName[];
	public static short buildingStaffNum[];
	public static long buildingFees[];
	public static long weeklyFees[];
	public static short flow_gain[];//	可选	short	2	公益建筑的人流增益
	public static byte staff_lv[];//	可选	byte	1	员工等级要求
	public static short staff_ability[];//	可选	short	2	员工能力要求
	public static short staff_exp[];//	可选	short	2	员工经验要求
	public static byte quality_id[];//	可选	byte	1	素质id
	public static byte quality_lv[];//	可选	byte	1	素质等级
	public static byte eff_area[];	//可选	byte	1	影响范围（单位街区）
	public static byte accessLevel[];//访问级别(当角色的访问级别大于等于此级别是可以创建此公益建筑物)
	/**公益建筑描述信息*/
	public static String build_description[];	
	/**占地面积（大块）*/
	public static byte build_area[];	

	/**
	 * 获取可以创建的建筑的类型的索引
	 */
	public static int[] getIndex_accessBuilding()
	{
		Vector v = new Vector();
		
		if(accessLevel != null)
		{
			for(int i = 0 ; i < accessLevel.length;i++)
			{
				if(GameData.player.accessLevel >= accessLevel[i])
				{
					v.addElement(i);
				}
			}
		}
		
		int index[] = new int[v.size()];
		
		for(int i =0 ; i < index.length; i++)
		{
			index[i] = Integer.parseInt(String.valueOf(v.elementAt(i)));
		}
		
		
		return index;
	}
	
	//商会联合宣传模板信息包
//	public static byte size;
	public static byte jointId[];
	public static String jointName[];
	public static int jointResID[];
	public static short jointIncome[];
	public static short jointDuration[];
	public static short joinNum[];
	public static short joinMorale[];
	public static long  joinMoney[];
	public static String joinDesc[];
	//商会联合宣传列表
	public static Propaganda pro[] =null ;
	
	//商会日志
	/**最大条数*/
	public static final int mebMaxsize = 20;
	/**商会*/
	public static Vector mebstr = new Vector(); 
	
	public static long RecruitedShopId;
	
	public static int RecruitedShopFlowGain;
	
	/**从第几个开始 */
    public static int RecruitedShopstart = 0;
	
	/**
	 * 
	 * @param array1 
	 * @param array2
	 * @param value
	 * @return
	 */
	public static byte getByteValue(byte[] array1,byte[] array2,byte value)
	{
		if(array1==null||array2==null)return -1;
		int idx=SystemAPI.getByteArrayIndex(array1, value);
		if(idx>=0&&idx<array2.length)
		{
			return array2[idx];
		}
		return -1;
	}
	/**
	 * 
	 * @param array1
	 * @param array2
	 * @param value
	 * @return
	 */
	public static long getLongtValue(byte[] array1,long[] array2,byte value)
	{
		if(array1==null||array2==null)return -1;
		int idx=SystemAPI.getByteArrayIndex(array1, value);
		if(idx>=0&&idx<array2.length)
		{
			return array2[idx];
		}
		return -1;
	}
	/**
	 * 
	 * @param array1
	 * @param array2
	 * @param value
	 * @return
	 */
	public static short getShorttValue(byte[] array1,short[] array2,byte value)
	{
		if(array1==null||array2==null)return -1;
		int idx=SystemAPI.getByteArrayIndex(array1, value);
		if(idx>=0&&idx<array2.length)
		{
			return array2[idx];
		}
		return -1;
	}
	/**
	 * 
	 * @param array1
	 * @param array2
	 * @param value
	 * @return
	 */
	public static String getStringValue(byte[] array1,String[] array2,byte value)
	{
		if(array1==null||array2==null)return "";
		int idx=SystemAPI.getByteArrayIndex(array1, value);
		if(idx>=0&&idx<array2.length)
		{
			return array2[idx];
		}
		return "";
	}


	public static Social seachPlayer []= null;
	public static Promoter friendList []=null;
	//商会列表信息

	//区域地图店铺模板信息
	
	public static byte professionId[];
	public static byte shop_scale_size;
	public static byte[] shopScale;
	

	
	public static int shopRes[];
	
	
	
	public static String shopName[];
	public static short staff_Num[];
	public static long createShop_Fees[];
	public static int shop_sprite[];

	/**add by zzx*/
	public static long shopTemplate_id[];//店铺模板id
	public static int shopTemplate_addition[];//店铺模板加成
	public static int shopTemplate_hiddenresid[];//隐藏店铺图片资源id
	public static byte shopTemplate_hidded[];//店铺模板是否隐藏
	public static short shopTemplate_point[];//店铺模板点数
	public static String shopTemplate_info[];//店铺模板信息
	public static byte shopTemplate_level[];//店铺模板级别
	public static String shopTemplate_condition[];//购买隐藏店铺需要的条件描述
	public static int shopLittleRes[];//店铺模板的小图片

	
	public static int[][] hiddedShopId;//隐藏店铺的id
	public static int seleftShopId = 0;
	public static int[] hiddenresid;
	
	//策划部 策略id 策略对象
	public static byte planningId = 0;
	public static long planningPeo = 0;
	public static byte planningNum[][] = new byte[6][2];
	public static long planning_costmoney[][] = new long[6][1];
	public static long planning_costpoint[][] = new long[6][1];
	
	/**博士信息
	 * 博士体力
	 * 博士点数
	 * 博士加成
	 * */
	public static int doctorLevel=0;
	public static int doctorPower = 0;
	public static int doctorMaxPower = 0;
	public static long doctorPoint = 0;
	public static short doctorGain ;
	
	/**
	 * 托管id 
	 * 托管的名称
	 * 托管的状态
	 * 托管的对象
	 * */
	public static int trustId[] ;
	public static byte[] trustlevel;
	public static String trustName[] ;
	public static byte[]  trustType ;
	public static byte trustTarget[] ;

	
	
	/**
	 * 店铺列表中选中的店铺ID
	 * */
	public static long corporation_shopID;
	/**
	 * 员工列表中选中的员工ID
	 * */
	public static long selectedEmployeesId[];
	
	
	//地图信息列表
	
	public static short mapIdIndexBack=0;//地图id的索引的缓存
	public static short mapIdIndex=0;//地图id的索引
	public static short[] mapIds;//地图id;
	public static String[] mapNames;//地图名字
	public static int[] mapTraffic;//地图客流
	public static int[] mapRent;//地图租金
	public static int [] mapTaxRate;//地图税率
	public static byte [][] canBuildType;//地图上允许建造的
	public static short [] mapWidth;
	public static short [] mapHeight;
	
	
	//选择员工的数组
	public static long [] empMeet;
	//开会的类型
	public static byte MeetType;
   //二次确认收到的ID
	public static int MeetID;
	public static int tpyekk = 0;
	
	public static boolean isResetMap=false;
	
	public static Skill skills[];
	public static Quality qualities[];
	/**
	 * add by zzx;
	 * 道具列表
	 * 商场列表
	 */
	public static Props props[] ;
	
	public static Props propsMall[];
	public static int choseID[] = {
			0,0,0,0,
			0,0,0,0
	};
	
	/**公司基本信息*/
	public static Corporation corporation;
	/**别人公司基本信息*/
	public static Corporation otherCorporation;
	/**角色基本信息*/
	public static Player player;
	public static Card[] card;
	public static long add_player_money;
	/**推广好友列表*/
	public static Promoter promoter;
	/**临时保存一下店铺ID*/
	public static long shopId;
	
	/**商会标示是否退出游戏后*/
	public static byte isExitMember = -1;//false:不存在;1:存在
	/**商会*/
	public static Member member;
	public static Member mber[] = null;
	/**商会成员*/
	
	/**保存描述 第一维数组表示：0主线1店铺2员工3商会4社会5答题(注：1-5为每日任务)*/
	public static boolean isUpdatTask;
	public static Task task[][]=new Task[13][];
	public static byte typeTask;//0:表示主线 1：表示日常
	
	/**
	 * 新手引导标识
	 */
	public static boolean isFreshMan = false;

	
	public static byte typeTaskMessage = 0;//目前 就这一个表示 1为创业测评
	
    public static byte taskGroup[];//任务组
    public static byte finish[];//任务组完成总数
    public static byte giveward[];//任务组奖励总加成
    public static byte maxGiveward[];//任务组奖励最大值
    public static byte taskSize[];//任务组任务数

	public static boolean isCheckMapTask;//
	
	public static int mainIndex[];
	public static int subIndex[];
	/**0好友3黑名单2陌生人1竞争者 */
	//public static Social social[][] = new Social[4][];
	 public static Vector friend= new Vector(10,5);
	 public static Vector competitor= new Vector(10,5);
	 public static Vector stranger= new Vector(10,5);
	 public static Vector blacklist= new Vector(10,5);
	 /**
	  * 
	  * <p>Description:通过索引取得相应的社交集合 如果超过集合索引的范围，将返回好友集合以防止空指针 </p>
	  * @author YangZheng
	  * @param index
	  * @return
	  */
	 public static Vector getSocial(int index)
	 {
		 switch(index)
		 {
		 case 0:
			 return friend;
		 case 1:
			 return competitor;
		 case 2:
			 return stranger;
		 case 3:
			 return blacklist;
		 }
		 return friend;
	 }
	 
	 
	 public static Vector inMailbox=new Vector(10,5);
	 public static Vector outMailbox=new Vector(10,5);
	 public static Vector eventListbox=new Vector(10,5);
	 
	
	 
	 public static Vector chatInfo[] = new Vector[5]; 
	 
	 public static Vector diglist = new Vector(10,10);
	 
	 public static final int MAXCHATCOUNT = 100;
	 public static void addChat(int channel,Chat chat)
	 {
		 if(chatInfo[channel] == null)
		 {
			 chatInfo[channel] = new Vector(10,10);
		 }
		 if(chatInfo[channel].size() >= MAXCHATCOUNT)
		 {
			 chatInfo[channel].removeElement(0);
		 }
		 chatInfo[channel].add(chat);

		Log.i("zzx", "频道:" + chat.channel+chat.content);
	 }
	 /*public static void addChat(int channel,Chat chat)
	 {
		 if(channel == 0)
		 {
			 if(allChatbox.size() >= MAXCHATCOUNT)
			 {
				 allChatbox.removeElementAt(0);
			 }
			 allChatbox.addElement(chat);
		 }
		 else if(channel == 1)
		 {
			 if(worldChatbox.size() >= MAXCHATCOUNT)
			 {
				 worldChatbox.removeElementAt(0);
			 }
			 worldChatbox.addElement(chat);
		 }
		 else if(channel == 2)
		 {
			 if(privateChatbox.size() >= MAXCHATCOUNT)
			 {
				 privateChatbox.removeElementAt(0);
			 }
			 privateChatbox.addElement(chat);
		 }
		 else if(channel == 3)
		 {
			 if(systemChatbox.size() >= MAXCHATCOUNT)
			 {
				 systemChatbox.removeElementAt(0);
			 }
			 systemChatbox.addElement(chat);
		 }
	 }*/
	 
	
	//抽奖widget字符串定义
	/**
	 * lotteryInfo 抽奖反馈
	 */
	public static String lotteryInfo = MainActivity.resources.getString(R.string.gamedata_lotteryinfo);
	/**
	 * lotteryManaging 常务奖励
	 */
	public static String lotteryManaging = MainActivity.resources.getString(R.string.gamedata_lotterymanaging);
	public static String companyArchsStr = MainActivity.resources.getString(R.string.gamedata_companyarchsstr);
	public static String tabCompanyArchsStr = MainActivity.resources.getString(R.string.gamedata_tabcompanyarchsstr);
	public static int lotteryNums = 8;
	
	/**
	 * @author 统计任务完成优先级
	 * @param 0 表示可接受
	 * @param 1 表示奖励（优先）
	 */
	public static byte getTaskStatus()
	{
		byte status = 0;
		
		if(GameData.task[1] != null)
		{
			
			if(GameData.task[1].length > 0)
			{
				for(int i = 0;i < GameData.task[1].length;i++)
				{
					if(GameData.task[1][i]!=null)
					{
						if(GameData.task[1][i].status == 1)
						{
	//						return 1;
							status = 1;
						}
						else if(GameData.task[1][i].status == 0 )
						{
							status = 0;
						}else{
							status = -1;
						}
					}
					else
					{
						status = -1;
					}
				}
			}else
			{
				status = -1;
			}
		}
		else
		{
			status = -1;
		}
		return status;
	}
	/**
	 * @author 获取地图区域的英文缩写
	 */
	public static String getMapRegional()
	{
		String onestr=null;
		if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_1)))
		{
			onestr = "A";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_2)))
		{
			onestr = "B";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_3)))
		{
			onestr = "C";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_4)))
		{
			onestr = "D";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_5)))
		{
			onestr = "E";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_6)))
		{
			onestr = "F";
		}else if(GameData.mapNames[GameData.mapIdIndex].equals(MainActivity.resources.getString(R.string.gamedata_mapnames_7)))
		{
			onestr = "G";
		}
		return onestr;
	}
	
	
	/**
	 * 当前已经有了街道位置，通过偏移值来计算新的街道位置
	 * @param cityX 当前街道
	 * @param cityY
	 * @param moveX 移动偏移街道值
	 * @param moveY
	 * @return offXY 偏移的xy坐标 高位时x,地位是y
	 */ 
	public static int[] moveCityXY(short cityX,short cityY,int moveX,int moveY)
	{
		
		//xy方向上偏移的坐标
		int offx=0;
		int offy=0;
		//xy移动的次数
		int frequencyX=Math.abs(moveX);
		int frequencyY=Math.abs(moveY);
		//xy移动的方向
		byte signUnitsX=0;
		byte signUnitsY=0;
		 if(frequencyX!=0)
		 {
			 signUnitsX=(byte)(moveX/frequencyX);
		 }
		 if(frequencyY!=0)
		 {
			 signUnitsY=(byte)(moveY/frequencyY);
		 }
		
		for(int i=0;i<frequencyX;i++)
		{
			int idx=getBlockIndex(cityX,cityY);
			offx=offx+Script.location_Offset_X[idx*4+(signUnitsX>0?0:1)];
			offy=offy+Script.location_Offset_Y[idx*4+(signUnitsX>0?0:1)];
			cityX+=signUnitsX;
		}
		for(int j=0;j<frequencyY;j++)
		{
			int idx=getBlockIndex(cityX,cityY);
			offx=offx+Script.location_Offset_X[idx*4+(signUnitsY>0?2:3)];
			offy=offy+Script.location_Offset_Y[idx*4+(signUnitsY>0?2:3)];
			cityY+=signUnitsY;
		}
		
		return new int[]{offx,offy};
	}
	
//	public static void ChangeCityXY(int moveX,int moveY)
//	{
//		if(!isBeyondBorder(cityX+moveX,cityY+moveY))
//		{
//			int xy[]=moveCityXY(cityX,cityY,moveX,moveY);
//			cityX=(short)(cityX+moveX);
//			cityY=(short)(cityY+moveY);
//			setMapXY(focusX+xy[0],focusY+xy[1]);
//		}
//		else
//		{
//			//Game.instance.initClewBox("系统","前方修路过不去啦，咱还是回到市区吧。", true);
//		}
//	}
//	/**
//	 * 如果当前没有保留街道的位置，或者需要重新设置街道位置
//	 * @param x
//	 * @param y
//	 */
//	public static void setCityXY(short x,short y)
//	{
//		if(isBeyondBorder(x,y))
//		{
//			//Game.instance.initClewBox("系统","前方修路过不去啦，咱还是回到市区吧。", true);
//		}
//		else
//		{
//			cityX=x;
//			cityY=y;
//			
//			short[] xy=transformCITY_TO_MAP(x,y);
//			setMapXY(xy[0]+Map.mapInfo_W*10,xy[1]+Map.mapInfo_H*10);
//		}
//	}
	
	
	public static void setCityXY(short x,short y,boolean refresh)
	{
		if(isBeyondBorder(x,y))
		{
			//Game.instance.initClewBox("系统","前方修路过不去啦，咱还是回到市区吧。", true);
//			 Message message = Message.obtain();
//			 message.what = GameDefinition.Message_showToast;
//			 
//			 Vector v = new Vector();
//			 v.addElement(MainActivity.resources.getString(R.string.gamedata_setcity_info));
//			 message.obj = v;
//             
//			 GameData.mhandler.sendMessage(message);
			 GameAPI.showToast(MainActivity.resources.getString(R.string.gamedata_setcity_info));
			 GameData.build = null;
			
		}
		else
		{
			cityX=x;
			cityY=y;
			
			if(refresh)
			{
				short[] xy=transformCITY_TO_MAP(x,y);
				setMapXY(xy[0]+Map.mapInfo_W*10,xy[1]+Map.mapInfo_H*10);
				centerX = focusX;
				centerY = focusY;
			}
			else
			{
			 
				boolean change = false;
				if(focusX < 2000)
				{
					setMapXY(focusX+544*10,focusY);
					change = true;
				}
				else if(focusX>20000)
				{
					setMapXY(focusX-544*10,focusY);
					change = true;
				}
				
				if(focusY<2000)
				{
					setMapXY(focusX,focusY+352*10);
					change = true;
				}
				else if(focusY>20000)
				{
					setMapXY(focusX,focusY-352*10);
					change = true;
				}
				
				short[] xy=transformCITY_TO_MAP(x,y);
				
//				centerX = focusX;
//				
//				centerY = focusY;
				
				centerX = focusX +(xy[0] - (focusX%Map.mapInfo_W));
				
				centerY = focusY + (xy[1] - (focusY%Map.mapInfo_H));
			}
		}
	}
	
	/**
	 * 判定是否超越了边界
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isBeyondBorder(int x,int y)
	{
		if(x<0||y<0||x>=SystemAPI.getShortArrayValue(mapWidth, mapIdIndex)||y >= SystemAPI.getShortArrayValue(mapHeight, mapIdIndex))
		{
			return true;
		}
		return false;
	}
	
	public static int getBlockIndex(int x,int y)
	{
		if(isBeyondBorder((short)x,(short)y))
		{
			while(x<0)
			{
				x+=24;
			}
			while(y<0)
			{
				y+=24;
			}
			while(x>SystemAPI.getShortArrayValue(mapWidth, mapIdIndex))
			{
				x-=24*8;
			}
			while(y>SystemAPI.getShortArrayValue(mapHeight, mapIdIndex))
			{
				y-=24*8;
			}
		}
		int idx=(short)(x%24);
		idx=(series_X[idx]+y%24)%24;
		return idx;
	}
	
	public static short[] transformCITY_TO_MAP(int x,int y)
	{
		int idx=getBlockIndex(x,y);
		short tempx=Script.locationX[idx];
		short tempy=Script.locationY[idx];
		return new short[]{tempx,tempy};
	}
	/**
	 * 设置当前焦点坐标和地图左上角坐标
	 * @param x
	 * @param y
	 */
	public static void setMapXY(int x,int y)
	{
		focusX=x;
		focusY=y;
		GameData.mapX=(short)(x-(Draw.showWidh>>1));
		GameData.mapY=(short)(y-(Draw.showHeight>>1));
	}
	/**商会员工操作  0新增；1修改；2删除*/
	public static void clearMemberEmployee()
	{
		member.employee = new MemberEmployee[0];
		if(member.employee != null)
		{
			for(int i = 0; i < member.employee.length; i++)
			{
				if(member.employee[i] != null)
				{
					member.employee = new MemberEmployee[0];
				}
			}
		}
	}
	 /**商会员工添加操作*/
	public static void addMemberEmployee(MemberEmployee e)
	{
		if(member.employee!=null)
		{
			member.employee = addmebemployee(member.employee,e);
		}
	}
   /**商会员工删除操作*/
	public static void deleteMebEmployee(long id)
	{
		if(member.employee!=null)
		{
			member.employee = deleteMebEmployee(member.employee,id);
		}
	}
	public static MemberEmployee[] addmebemployee(MemberEmployee[] src,MemberEmployee e)
	{
		if(src==null)
		{
			src=new MemberEmployee[1];
			src[0]=e;
		}
		else
		{
			boolean add=false;
			for(int i=0;i<src.length;i++)
			{
				if(src[i].memId==e.memId)
				{
					src[i]=e;
					add=true;
				}
			}
			if(!add)
			{
				MemberEmployee temp[]=new MemberEmployee[src.length];
				System.arraycopy(src, 0, temp, 0, temp.length);
				src=new MemberEmployee[temp.length+1];
				System.arraycopy(temp, 0, src, 0, temp.length);
				src[src.length-1]=e;
			}
		}
		return src;
	}
	public static MemberEmployee[] deleteMebEmployee(MemberEmployee[] src,long id)
	{
		Vector v = new Vector(src.length);
		
		if(src!=null&&src.length>0)
		{
			for(int i=0;i<src.length;i++)
			{
				if(src[i].memId!=id)
				{
					v.addElement(src[i]);
				}
			}
		}
		
		src = new MemberEmployee[v.size()];
		
		for(int i = 0;i < src.length;i++)
		{
			src[i] = (MemberEmployee)v.elementAt(i);
		}
		
		return src;
	}
	/**商会员工操作  0新增；1修改；2删除*/
	//--------------------------------------------------------------------------------
	public static void clearEmployee()
	{
		corporation.employee=new Employee[0];
		if(corporation.shop!=null)
		{
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(corporation.shop[i]!=null)
				{
					corporation.shop[i].employees=new Employee[0];
				}
			}
		}
	}
	
	public static void addEmployee(Employee e, int type)
	{
		// 添加到公司
		corporation.employee = addemployee(corporation.employee, e);
		corporation.employeeCount = (short)corporation.employee.length;
		// 添加到部门
		if(e.type != 6 && e.type != 5)
		{
			if(GameData.corporation.department != null)
			{
				for(int i = 0;i < GameData.corporation.department.length;i++)
				{ 
					{
						if(GameData.corporation.department[i].type == e.type)
						{
							GameData.corporation.department[i].employees = addemployee(GameData.corporation.department[i].employees, e);
							GameData.corporation.department[i].staffNum = (short)GameData.corporation.department[i].employees.length;
							break;
						}
					}
					 
				}
			}
			// 添加到店铺
			if(GameData.corporation.shop != null)
			{
				for(int i = 0;i < GameData.corporation.shop.length;i++)
				{
					if(GameData.corporation.shop[i].id == e.department)// 找到对应的店铺
					{
							GameData.corporation.shop[i].employees = addemployee(GameData.corporation.shop[i].employees, e);
							GameData.corporation.shop[i].staffNum = (short)GameData.corporation.shop[i].employees.length;	

					}
				}
			}
		}
		else
		{
			if(GameData.corporation.department != null)
			{ 
				for(int i =0 ;i < GameData.corporation.department.length; i++)
				{
					if(GameData.corporation.department[i] != null)
					{
						if(GameData.corporation.department[i].employees != null)
						{
							for(int j = 0; j < GameData.corporation.department[i].employees.length;j++)
							{
								if(GameData.corporation.department[i].employees[j] != null)
								{
									if(GameData.corporation.department[i].employees[j].id == e.id)
									{
										GameData.corporation.department[i].employees = deleteEmployee(GameData.corporation.department[i].employees, e.id);
										GameData.corporation.department[i].staffNum = (short)GameData.corporation.department[i].employees.length;
										if(type == 2)
										{
											if(GameData.corporation.department[i].manager_twoId == e.id)//员工任职的是哪个职位
											{
												GameData.corporation.department[i].manager_twoId = 0;
//												GameData.corporation.employeesMaxNum -= 50;
											}
											if(GameData.corporation.department[i].manager_oneId == e.id)
											{
												GameData.corporation.department[i].manager_oneId = 0;
											}
											if(GameData.corporation.department[i].manager_threeId == e.id)
											{
												GameData.corporation.department[i].manager_threeId = 0;
//												GameData.corporation.employeesMaxNum -= 100;
											}
											if(GameData.corporation.department[i].manager_fourId == e.id)
											{
												GameData.corporation.department[i].manager_fourId = 0;
//												GameData.corporation.employeesMaxNum -= 150;
											}
											if(GameData.corporation.department[i].manager_five == e.id)
											{
												GameData.corporation.department[i].manager_five = 0;
//												GameData.corporation.employeesMaxNum -= 200;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			if(GameData.corporation.shop != null)
			{
				for(int i =0 ;i < GameData.corporation.shop.length; i++)
				{
					if(GameData.corporation.shop[i] != null)
					{
						if(GameData.corporation.shop[i].employees != null)
						{
							for(int j = 0; j < GameData.corporation.shop[i].employees.length;j++)
							{
								if(GameData.corporation.shop[i].employees[j] != null)
								{
									if(GameData.corporation.shop[i].employees[j].id == e.id)
									{
										GameData.corporation.shop[i].employees = deleteEmployee(GameData.corporation.shop[i].employees, e.id);
										GameData.corporation.shop[i].staffNum = (short)GameData.corporation.shop[i].employees.length;
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
	
	public static void deleteEmployee(long id)
	{
		if(corporation.employee!=null)//对公司的操作
		{
			corporation.employee=deleteEmployee(corporation.employee,id);
			corporation.employeeCount=(short)corporation.employee.length;
		}
		if(corporation.department!=null)
		{
			for(int i=0;i<corporation.department.length;i++)
			{
				if(corporation.department[i]!=null&&corporation.department[i].employees != null)
				{
					corporation.department[i].employees=deleteEmployee(corporation.department[i].employees,id);
					corporation.department[i].staffNum=(short)corporation.department[i].employees.length;
				}
			}
		}
		if(corporation.shop!=null)
		{
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(corporation.shop[i]!=null && corporation.shop[i].employees != null)
				{
					corporation.shop[i].employees=deleteEmployee(corporation.shop[i].employees,id);
					corporation.shop[i].staffNum=(short)corporation.shop[i].employees.length;
				}
			}
		}
	}
	
	public static Employee[] addemployee(Employee[] src,Employee e)
	{
		if(src==null)
		{
			src=new Employee[1];
			src[0]=e;
		}
		else
		{
			boolean add=false;
			for(int i=0;i<src.length;i++)
			{
				if(src[i].id==e.id)
				{
					src[i].copy(e);
					add=true;
				}
			}
			if(!add)
			{
				Employee temp[]=new Employee[src.length];
				System.arraycopy(src, 0, temp, 0, temp.length);
				src=new Employee[temp.length+1];
				System.arraycopy(temp, 0, src, 0, temp.length);
				src[src.length-1]=e;
			}
		}
		return src;
	}
	
	
	
	public static Employee[] deleteEmployee(Employee[] src,long id)
	{
		if(src == null) return null;
		Vector v = new Vector(src.length);
		
		if(src!=null&&src.length>0)
		{
			for(int i=0;i<src.length;i++)
			{
				if(src[i].id!=id)
				{
					v.addElement(src[i]);
				}
			}
		}
		
		src = new Employee[v.size()];
		
		for(int i = 0;i < src.length;i++)
		{
			src[i] = (Employee)v.elementAt(i);
		}
		
		return src;
	}
	
	public static void addPublicBuilding(PublicBuilding pb)
	{
		boolean add=false;
		for(int i=0;i<corporation.build.length;i++)
		{
			if(add)break;
			if(corporation.build[i]==null)
			{
				corporation.build[i]=pb;
				 
				add=true;
			}
			else if(corporation.build[i].id==pb.id)
			{
				corporation.build[i]=pb;
				add=true;
			}
		}
		if(!add)
		{
			PublicBuilding[] temp=new PublicBuilding[corporation.build.length];
			System.arraycopy(corporation.build, 0, temp, 0, corporation.build.length);
			corporation.build=new PublicBuilding[temp.length+1];
			System.arraycopy(temp, 0, corporation.build, 0, temp.length);
			corporation.build[corporation.build.length-1]=pb;
		}
	}
	/**
	 * 
	 * @param shop
	 */
	public static void addShop(Shop shop)
	{
		
		if(GameData.corporation.employee != null)
		{
			for(int i =0; i<GameData.corporation.employee.length; i++)
			{
				if(corporation.employee[i].department == shop.id)
				{
					shop.employees = addemployee(shop.employees, corporation.employee[i]);
				}
			}
		}
		
		
		if(corporation.shop==null)
		{
			corporation.shop=new Shop[1];
			corporation.shop[0]=shop;
			corporation.shopCount=1;
		}
		else
		{
			boolean add=false;
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(add)break;
				if(corporation.shop[i]==null)
				{
					corporation.shop[i]=shop;
					corporation.shopCount=(short)(corporation.shopCount+1);
					add=true;
				}
				else if(corporation.shop[i].id==shop.id)
				{
					corporation.shop[i]=shop;
					add=true;
				}
			}
			if(!add)
			{
				Shop[] temp=new Shop[corporation.shop.length];
				System.arraycopy(corporation.shop, 0, temp, 0, corporation.shop.length);
				corporation.shop=new Shop[temp.length+1];
				System.arraycopy(temp, 0, corporation.shop, 0, temp.length);
				corporation.shop[corporation.shop.length-1]=shop;
				corporation.shopCount=(short)(corporation.shopCount+1);
			}
		}
		Log.i("Log", "addShop--success==?"+(corporation.shop!=null)+corporation.shop.length);
	}
	public static Shop[] getShopByTradeAndMapId(byte trade,short mapId)
	{
		Vector v = new Vector();
		
		if(corporation.shop != null)
		{
			 for(int i = 0; i < corporation.shop.length; i++)
			 {
				 if(corporation.shop[i].trade_id == trade && corporation.shop[i].map_id == mapId)
				 {
					 v.addElement(corporation.shop[i]);
				 }
			 }
		}
		
		Shop shop[] = new Shop[v.size()];
		
		for(int i = 0; i < shop.length; i++)
		{
			shop[i] = (Shop)v.elementAt(i);
		}
		return shop;
	}
	
	/**
	 * 获取指定id的店铺，如果找不到返回null
	 * @param id
	 * @return
	 */
	public static Shop getShop(long id)
	{
		if(corporation.shop!=null)
		{
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(corporation.shop[i]!=null)
				{
					if(id==corporation.shop[i].id)
					{
						return corporation.shop[i];
					}
				}
			}
		}
		return null;
	}
	
	public static Shop getShopByBuildingID(long id)
	{
		if(corporation.shop!=null)
		{
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(corporation.shop[i]!=null)
				{
					if(id==corporation.shop[i].buildingId)
					{
						return corporation.shop[i];
					}
				}
			}
		}
		return null;
	}
	
	public static PublicBuilding getPublicBuildingByBuildingID(long id)
	{
		if(corporation.build!=null)
		{
			for(int i = 0; i < corporation.build.length;i++)
			{
				if(id == corporation.build[i].build_id)
				{
					return corporation.build[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取指定id的部门
	 * @param id
	 * @return
	 */
	public static Department getDepartment(long id)
	{
		if(corporation.department!=null)
		{
			for(int i=0;i<corporation.department.length;i++)
			{
				if(corporation.department[i]!=null)
				{
					if(id==corporation.department[i].id)
					{
						return corporation.department[i];
					}
				}
			}
		}
		return null;
	}
	
	public static PublicBuilding getBuilding(long id)
	{
		if(corporation.build != null)
		{
			for(int i =0; i< corporation.build.length;i++)
			{
				if(id == corporation.build[i].id)
				{
					return corporation.build[i];
				}
			}
		}
		return null;
	}
	/**
	 * 获取指定类型的店铺
	 * @param type
	 * @return
	 */
	public static Department getDepartment(byte type)
	{
		if(corporation.department!=null)
		{
			for(int i=0;i<corporation.department.length;i++)
			{
				if(corporation.department[i]!=null)
				{
					if(type==corporation.department[i].type)
					{
						return corporation.department[i];
					}
				}
			}
		}
		return null;
	}
	
	public static Branch getBranch(long id)
	{
		Branch b = null;
		
		if((b =getDepartment(id)) != null)
		{
			return b;
		}
		
		if((b = getShop(id)) != null)
		{
			return b;
		}
		
		if((b = getBuilding(id)) !=null)
		{
			return b;
		}
		return b;
	}
	
	/**
	 * 得到指定部门的员工
	 */
	public static Employee[] getBranchEmployee(long id)
	{
		Vector v =new  Vector();
		
		Employee e[] = new Employee[0];
		
		if(GameData.corporation.employee != null)
		{
			for(int i = 0 ; i < GameData.corporation.employee.length;i++)
			{
				if(GameData.corporation.employee[i] != null)
				{
					if(GameData.corporation.employee[i].department == id)//员工属于指定id的部门
					{
						v.addElement(GameData.corporation.employee[i]);
					}
				}
			}
			
			if(v.size() > 0)
			{
				e = new Employee[v.size()];
				
				for(int i = 0 ; i < v.size() ; i++)
				{
					e[i] = (Employee)v.elementAt(i);
				}
			}
		}
		
		return e;
	}
//	/**
//	 * 获取店铺中的员工
//	 * @param branchId
//	 * @return
//	 */
//	public static Employee[] getShopEmployee(long shopId)
//	{
//		Shop shop=getShop(shopId);
//		if(shop!=null)
//		{
//			return shop.employees;
//		}
//		return null;
//	}
//	/**
//	 * 获取部门中的员工
//	 * @param shopId
//	 * @return
//	 */
//	public static Employee[] getDepartmentEmployee(long shopId)
//	{
//		
//	}
	/**
	 * 查找某个员工
	 */
	public static Employee getEmployeeById(Department department,int type)
	{
		int pplen = corporation.department.length ;
		for(int i = 0;i<pplen ;i++)
		{
			if(department.id == corporation.department[i].id)
			{
				int plen = corporation.department[i].employees.length ;
				for(int j=0;j<plen;j++)
				{
					if(corporation.department[i].employees[j].duty == type)
					{
						return corporation.department[i].employees[j] ;
					}
				}
				return null;
			}
		}
		return null ;
	}
	public static Employee getEmployeeById1(long id)
	{
		if(corporation.employee==null)return null;
		for(int i=0;i<corporation.employee.length;i++)
		{
			if(corporation.employee[i]!=null)
			{
				if(corporation.employee[i].id==id)
				{
					return corporation.employee[i];
				}
			}
		}
		return null;
	}
	
	public static String getEmployeeNameById(long id,String defaultname)
	{
		String name =defaultname;
		
		Employee e = getEmployeeById1(id);
		
		if(e != null)
		{
			name = e.name;
		}
		return name;
	}
	/**
	 * 根据职责和id  查找名称
	 * */
	public static String getEmployeeNameByDuty(int duty,long departmentId,String defaultname)
	{
		String name = defaultname;
		Employee e = getEmployeeByDuty(duty,departmentId);
		
		if(e != null)
		{
			name = e.name;
		}
		return name;
	}
	/**
	 * 根据职责和部门id 获得员工
	 * */
	public static Employee getEmployeeByDuty(int duty,long departmentId)
	{
	 
			for(int i = 0 ; i < GameData.corporation.employee.length ; i++)
			{
				if(GameData.corporation.employee[i] != null&& GameData.corporation.employee[i].duty == duty&&GameData.corporation.employee[i].department == departmentId)
				{
					return GameData.corporation.employee[i];
				}
			}
		 
		return null;
	}
	
	
	
	
	public static boolean isMyShop(long buildingId)
	{
		if(corporation.shop!=null)
		{
			for(int i=0;i<corporation.shop.length;i++)
			{
				if(corporation.shop[i]!=null)
				{
					if(corporation.shop[i].buildingId==buildingId)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 获取指定的行业资源(在UI中显示的资源)
	 * @param tradeId
	 * @return
	 */
	public static int getUIResId(byte tradeId)
	{
		//先查是不是店铺
		if(professionId!=null)
		{
			for(int i=0;i<professionId.length;i++)
			{
				if(tradeId==professionId[i])
				{
					return shopRes[i*shop_scale_size];
				}
			}
		}
		
		if(buildingId!=null)
		{
			for(int i=0;i<buildingId.length;i++)
			{
				if(tradeId==buildingId[i])
				{
					return buildingResId[i];
				}
			}
		}
		return 0;
	}
	/**
	 * 获取指定的行业资源 
	 * @param tradeId
	 * @return
	 */
	public static int getUISpriteId(byte tradeId)
	{
		//先查是不是店铺
		if(professionId!=null)
		{
			for(int i=0;i<professionId.length;i++)
			{
				if(tradeId==professionId[i])
				{
					return shop_sprite[i*shop_scale_size];
				}
			}
		}
		
		return 0;
	}
	
	
	 
	
	/**
	 * 获取指定的行业名称
	 * @param tradeId 行业id
	 * @param scale规模
	 * @return
	 */
	public static String getProfessionName(String name,byte tradeId,byte scale)
	{
		
		if(professionId!=null)
		{
			for(int i=0;i<professionId.length;i++)
			{
				if(tradeId==professionId[i])
				{
					return name+shopName[i*shop_scale_size+scale-1];
				}
			}
		}
		
		if(buildingId!=null)
		{
			for(int i=0;i<buildingId.length;i++)
			{
				if(tradeId==buildingId[i])
				{
					return name+buildingName[i];
				}
			}
		}
		return name;
	}
	/**
	 * 在地图上能否建造指定规模的建筑
	 * @param mapIdx
	 * @param type
	 * @return
	 */
	public static boolean canBuild(int mapIdx,int type)
	{
		for(int i=0;i<canBuildType[mapIdx].length;i++)
		{
			if(canBuildType[mapIdx][i]==type)
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * add by zzx;
	 * 更新道具
	 */
	public static void updateProps(byte opType,long id,short count,short icon,String name,String desc,byte subsize,byte[] targetTypeld){
		int propsID = 0;
		switch(opType){
		case 1://增加
			addPro(id,count,icon,name,desc,subsize,targetTypeld);
			break;
		case 2://删除
			 propsID = lookProps(id);
			delPro(propsID);
			break;
		case 3://更新
			 propsID = lookProps(id);
			updatePro(propsID,/*id,*/count/*,icon,name,desc,subsize,targetTypeld*/);
			break;
		}
	}
	
	public static int lookProps(long id){
		for(int i = 0;i<props.length;i++){
			if(props[i]==null)
				continue;
			if(props[i].id == id){
				return i;
			}
		}
		return -1;
	} 
	/**
	 * 添加道具
	 */
	public static void addPro(long id,short count ,short icon,String name,String desc,byte subsize,byte[] targetTypeld)
	{
		if(props/*[0]*/==null){
			props = new Props[1];
			props[0] = new Props();
			props[0].id = id;
			props[0].count = count;
			props[0].icon = icon;
			props[0].name = name;
			props[0].desc = desc;
			props[0].subsize = subsize;
			props[0].targetTypeld = targetTypeld;
		}else{
			Props temp[] = new Props[props.length + 1];
			
			System.arraycopy(props, 0, temp, 0, props.length);

			temp[temp.length-1] = new Props();
			temp[temp.length-1].id = id;
			temp[temp.length-1].count = count;
			temp[temp.length-1].icon = icon;
			temp[temp.length-1].name = name;
			temp[temp.length-1].desc = desc;
			temp[temp.length-1].subsize = subsize;
			temp[temp.length-1].targetTypeld = targetTypeld;

			props = null;
			props = temp;
		}
	}
	/**
	 * 删除道具
	 */
	public static void delPro(int id)
	{ 
		Vector v = new Vector();
	   
	   for(int i = 0; i < props.length; i++)
	   {
		   if(i != id)
		   {
			   v.addElement(props[i]);
		   }
	   }
	   
	   props = new Props[v.size()];
	   
	   for(int i = 0 ; i < props.length; i++)
	   {
		   props[i] = (Props)v.elementAt(i);
	   }
	    
	   
	}
	/**
	 * 更新指定道具
	 * @param index 道具
	 * @param id
	 * @param count
	 * @param icon
	 * @param name
	 * @param desc
	 * @param subsize
	 * @param targetTypeld
	 */
	public static void updatePro(int index,short count){
		props[index].count = count;
	}
	/**
	 * 更新道具数组
	 * 为写完
	 */
	public void updateProps(){
		int temp_index = 0;
		Props[] temp_props = new Props[props.length];
		for(int i=0;i<props.length;i++){
//			if(props[i].id != 0){
//				temp_props[temp_index++] = props[i]; 
//			}
		}
		System.arraycopy(temp_props, 0, props, 0, temp_index);
		temp_props = null;
	}
	 
	/**
	 * 
	 * @param cityX
	 * @param cityY
	 * @return
	 */
	public static PublicBuilding getMySelectBuilding(short cityX,short cityY)
	{
		for(int i=0;i<corporation.build.length;i++)
		{
			if(corporation.build[i]!=null)
			{
				if(corporation.build[i].address_x == cityX && corporation.build[i].address_y == cityY)
				{
					return corporation.build[i];
				}
			}
		}
		return null;
	}
	
	public static Vector getSelectBuildingSprite(short cityX,short cityY)
	{
		Vector v=new Vector();
//		if(Game.instance.gameRun.build!=null)
//		{
//			 for(int i=0;i<Game.instance.gameRun.build.length;i++)
//			 {
//				 if(Game.instance.gameRun.build[i]!=null)
//				 {
//					 if(Game.instance.gameRun.build[i].mb.cityX==GameData.cityX&&Game.instance.gameRun.build[i].mb.cityY==GameData.cityY)
//					 {
//						 v.addElement(Game.instance.gameRun.build[i]);
//						 for(int j=0;j<Game.instance.gameRun.build[i].mb.cellCount;j++)
//						 {
//							 int x=(Game.instance.gameRun.build[i].mb.startGrid+j)/3;
//							 int y=(Game.instance.gameRun.build[i].mb.startGrid+j)%3;
//							 
//						 }			 
//					 }
//				 }
//			 }
//		}
		return v;
	}
	/**
	 * 判定是否还有空余的地方盖建筑
	 * @param cityX
	 * @param cityY
	 * @return
	 */
	public static boolean canBuild(short cityX,short cityY)
	{
		Vector v=getSelectBuildingSprite(cityX,cityY);
		int cellCount=0;
		for(int i=0;i<v.size();i++)
		{
//			BuildingSprite bs=(BuildingSprite)v.elementAt(i);
//			cellCount+=bs.mb.cellCount;
		}
		if(cellCount<9)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static long Ranking_level [] = {0,0};
	public static String Ranking_name [] = {"",""};
	public static long Ranking_info [] = {0,0};

	
	//九宫格描述问题子
	public static final int MEETING = 0;
	public static final int COMMNUCATION = 1;
	public static final int VOLUNTEER = 2;
	public static final int PR =3;
	public static final int TRAINING = 7;
	public static final int ROLEMANAGING = 9;
	
	public static final String str[] ={
		
		MainActivity.resources.getString(R.string.gamedata_canbuild_item1),//开会 0
		MainActivity.resources.getString(R.string.gamedata_canbuild_item2),//沟通 1
		MainActivity.resources.getString(R.string.gamedata_canbuild_item3),//义工 2
		MainActivity.resources.getString(R.string.gamedata_canbuild_item4),//公关 3
		MainActivity.resources.getString(R.string.gamedata_canbuild_item5),//4
		MainActivity.resources.getString(R.string.gamedata_canbuild_item6),//5
		MainActivity.resources.getString(R.string.gamedata_canbuild_item7),//6
		MainActivity.resources.getString(R.string.gamedata_canbuild_item8),//培训 7
//		"进修：花费金钱让员工参加进修，提升员工等级，成功率与员工的技能和能力值有关。",//ft注释
		MainActivity.resources.getString(R.string.gamedata_canbuild_item9),//ft修改
		MainActivity.resources.getString(R.string.gamedata_canbuild_item10),//常务//开会 9
		MainActivity.resources.getString(R.string.gamedata_canbuild_item11),//沟通 10
		MainActivity.resources.getString(R.string.gamedata_canbuild_item12),//店铺巡视 11
		MainActivity.resources.getString(R.string.gamedata_canbuild_item13),//员工招聘 12
		MainActivity.resources.getString(R.string.gamedata_canbuild_item14),//员工续约 13
		MainActivity.resources.getString(R.string.gamedata_canbuild_item15),//员工培训 14
		MainActivity.resources.getString(R.string.gamedata_canbuild_item16),//义务劳动 15
		MainActivity.resources.getString(R.string.gamedata_canbuild_item17),//店铺公关 16
		MainActivity.resources.getString(R.string.gamedata_canbuild_item18),//创业学堂 17
		MainActivity.resources.getString(R.string.gamedata_canbuild_item19),//调薪 18
		MainActivity.resources.getString(R.string.gamedata_canbuild_item20),//解雇 19
		MainActivity.resources.getString(R.string.gamedata_canbuild_item21),//交租 20
		MainActivity.resources.getString(R.string.gamedata_canbuild_item22),//纳税 21
		MainActivity.resources.getString(R.string.gamedata_canbuild_item23),//发薪 22
		
	};
	
	/**
	 * 清除公司和店铺的数据
	 */ 
	public static void clearData()
	{
		GameData.player=null;
		GameData.promoter=null;
		GameData.corporation=null;
		mainIndex = null;
		subIndex = null;
		isCheckMapTask = false;
	    taskGroup= null;//任务组
	    finish=null;//任务组完成总数
	    giveward=null;//任务组奖励总加成
	    maxGiveward=null;//任务组奖励最大值
	    taskSize=null;//任务组任务数
	    typeTask=0;
	    task=new Task[13][];
	    isUpdatTask = false;
	    member = null;
	    isExitMember = -1;
	    shopId =0;
	    props = null;
	    skills = null;
	    qualities = null;
	    isResetMap = false;
	    selctIndex = 0;
	    clearChatNotes();
	    lastTime = 0;
	    curReciveTime = 0;
	    
	    clearFreshManLead();

//	    Game.chat.clear();
//	    GameRun.chatchannel.clear();
//	    Game.fw.clear();
//	    Game.mailBox.clear();
//	   Game.customerMailbox.clear();
	}
	
	/**
	 * 清楚聊天记录
	 */
	public static void clearChatNotes(){
		for(int i=0;i<GameData.chatInfo.length;i++)
		{
			if(GameData.chatInfo[i]!=null)
			GameData.chatInfo[i].clear();
		}
	}
	/**
	 * 对新手引导进行清理
	 */
	public static void clearFreshManLead(){
		FreshManLead.caseId = 0;
	    isFreshMan = false;
	}
	
	
	
	
	public static boolean isRecruiting()
	{
		if(GameData.corporation.shop != null)
		{
			for(int i = 0 ; i < GameData.corporation.shop.length ; i++)
			{
				if(GameData.corporation.shop[i] != null)
				{
					if(GameData.corporation.shop[i].isRecruited())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static String getShopScale(int type,String defaulttype)
	{
		String n= defaulttype;
		
		if(type == 1)
		{
			n = MainActivity.resources.getString(R.string.gamedata_getshopscale_small);
		}
		else if(type == 2)
		{
			n = MainActivity.resources.getString(R.string.gamedata_getshopscale_medium);
		}
		else if(type == 3)
		{
			n = MainActivity.resources.getString(R.string.gamedata_getshopscale_large);
		}
		return n;
	}
	
//	/**
//	 * 删除店铺
//	 * */
//	public static void delShop(long shopId)
//	{
//		int delBuildId = 0;
//		int delIndex = 0;
//		/**遍历出店铺ID*/
//		for(int i = 0;i < corporation.shop.length;i++)
//		{
//			if(corporation.shop[i].id == shopId)
//			{
//				delIndex = i; 
//				break;
//			}
//		}
//		/**遍历出建筑ID*/
//		for(int i = 0;i < GameRun.instance.build.length;i++)
//		{
//			if(GameRun.instance.build[i].mb.id == corporation.shop[delIndex].buildingId)
//			{
//				delBuildId = i;
//			}
//		}
//		/**删除数据*/
//		GameRun.instance.build[delBuildId] = null;
//		corporation.shop[delIndex] = null;
//		/**排序数组*/
//		Shop[] temp = new Shop[corporation.shop.length-1];
//		System.arraycopy(corporation.shop, 0, temp, 0, delIndex);
//		System.arraycopy(corporation.shop, delIndex+1, temp, delIndex, (corporation.shop.length-1-delIndex));
//		
//		corporation.shop = temp;
//		corporation.shopCount=(short)(temp.length);
//		
//		
//	}
}
