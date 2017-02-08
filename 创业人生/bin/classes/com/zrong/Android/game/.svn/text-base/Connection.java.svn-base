package com.zrong.Android.game;

 

 



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;

 

 

 

 

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.online.network.BaseConnection;
import com.zrong.Android.online.network.ConnectionHandler;
import com.zrong.Android.online.network.GameProtocol;



public class Connection implements ConnectionHandler{
	
	private static final String TAG = "Connection";
	
	private Context context;
	
	private GameGroupControl control;
	
	public  Connection(Context context,GameGroupControl control)
	{
		this.context = context;
		this.control = control;
		AnalysisData.context = context;
		AnalysisData.control = control;
	}
	
	/** Connection连接对象 */
	private static BaseConnection connection = null;

	/** 获取Connection对象 */
	public static BaseConnection getConnection() 
	{
		return connection;
	}
	/** 开启连接 */

	public static void createConnection(String server, Connection handler) 
	{
		connection = BaseConnection.CreateConnection(
				BaseConnection.C_CONNECTION_TYPE_TCP, server, handler,
				GameDef.userId);
		setKeepAliveStart(System.currentTimeMillis());
	}

	/** 关闭连接 */
	public static void close() 
	{
		if (connection != null) 
		{
			connection.close();
		}
	}
	
	
	/** 重连的次数 */
	private static int connectionTimeOut = 0;

	private static final int MAX_CONNECTION_TIMEOUT = 3;
	// +++++++++++++++++++++++++心跳包逻辑+++++++++++++++++++++++++++++++++++add
	private static long keepAliveStart;
	private static final long keepAliveInterval = 30000;

	public static void setKeepAliveStart(long n) 
	{
		keepAliveStart = n;
	}

	public static void clearConnectionIdle() 
	{
		keepAliveStart = System.currentTimeMillis();
	}
	
	public static boolean keepConnectionAlive() 
	{
		
		checkTimeOut();
		if (connection != null
				&& System.currentTimeMillis() - keepAliveStart > keepAliveInterval) 
		{
			Log.v(TAG, "============================================keepAlive");
			connection.keepAlive();
			clearConnectionIdle();
			return true;
		}
		return false;
	}
	
	public static void checkTimeOut()
	{
//		if (connectionTimeOut > MAX_CONNECTION_TIMEOUT)
//		{
//			close();
//			connectionTimeOut = 0;
//			Log.v(TAG, "网络重连超过 重连最大值5，清空重连次数,并且关闭连接");
//		}
	}
	//--------------------------------------------------
	public void ConnectionCancel() 
	{
		// TODO Auto-generated method stub
		
	}

	public void connectionBroken() 
	{
			 close();
			 SystemAPI.sleep(300);
			 Message message = Message.obtain();
			
			 message.what = GameDefinition.Message_show_dailog;
			
			 Bundle bundle = new Bundle();
			
			 bundle.putString("message", "网络中断");
			 
			 message.obj = bundle;
			
			 message.arg1 = 1;
			
			 GameData.mhandler.sendMessage(message);
	}
	
	/** 发包 */
	public static boolean sendMessage(short type, byte[] msg) {
		try {
			connection.sendMessage(type, msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return true;
	}

	public static boolean sendMessage(short type, byte[] msg, long wait) {
		try {
			connection.sendMessage(type, msg);
//			DrawLoading.initLoading(type, wait);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean sendMessage(short type, byte[] msg,
			boolean isNeedLoading) {
		try {
			connection.sendMessage(type, msg);
			if (isNeedLoading) {
//				DrawLoading.initLoading(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void connectionCreated() 
	{
		 
		setKeepAliveStart(System.currentTimeMillis());
	}

	public void connectionTimeout() 
	{
		 
		
	}
	
	public void connectionReCreated() {
		setKeepAliveStart(0);
	}
	
	 public void connectionConnected() 
	 {
		 
		 connectionTimeOut = 0;
		 
     }

	public boolean recvMessage(int type, int length, byte[] body) 
	{
		try {
			String str = new String(body,"UTF-8");
			Log.d(TAG, "type ="+Integer.toHexString(type)+"body ="+str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try{
//		ByteArrayInputStream bais = null;
		DataInputStream dis = null;
		if (body != null) {
			/*bais = new ByteArrayInputStream(body);
			dis = new DataInputStream(bais);*/
			dis = new DataInputStream(new ByteArrayInputStream(body));
		} 
	    switch(type)
	    {
	    case GameProtocol.HTTP_STANDARD:
	    	AnalysisData.exec_Http_Standard(dis);
	    	break;
	    case GameProtocol.HTTP_SERVER_RES:
	    	AnalysisData.exec_Http_ServerResp(dis);
	    	break;
	    case GameProtocol.HTTP_CMCC:
	    	AnalysisData.exec_Http_CMCC(dis);
			break;
	    case GameProtocol.CONNECTION_RESP_USEUPADAT:// 用户属性更新包
	    	AnalysisData.exec_Resp_UseUpData(dis);
			break;
	    case GameProtocol.CONNECTION_RECONNECTION_INFO://断网重连
	    	AnalysisData.exec_Resp_ConnectionInfo(dis);
	    	break;
	    	//用户登录下行包响应
	    case GameProtocol.CONNECTION_SEND_AUTHLOGIN_RESP:
	    	AnalysisData.exec_Login_Resp(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_SYSTEMMESSAGE://系统消息
	    	AnalysisData.exec_Resp_SystemInfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_GAMEMAP_LIST_INFO://地图信息列表包
	    	AnalysisData.exec_Resp_Map_LIST_INFO(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_CREATE_REG://角色创建结果包
	    	AnalysisData.exec_Resp_Accept_CreateReg(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_CPMSG:// 公司创建结果包
	    	AnalysisData.exec_Resp_Accept_CreateCp(dis);
	    break;
	    case GameProtocol.CONNECTION_ACCEPT_USERSINFORMATION://角色基本信息包
	    	AnalysisData.exec_Resp_Accept_UsersInformation(dis);
	        break;
	    case GameProtocol.CONNECTION_ACCEPT_COMPINFOR://公司基本信息包
	    	AnalysisData.exec_Resp_Accept_CompInformation(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_DEPT_ST_INFO: //部门列表信息包
	    	AnalysisData.exec_Resp_Accept_DepStructureinfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_DEPARTMENT_UPDATE://部门属性更新包
	    	AnalysisData.exec_Resp_DepartmentUpdata(dis);
	    case GameProtocol.CONNECTIOIN_ACCEPT_SHOP_LIST_BASEINFO:// 店铺列表包
	    	AnalysisData.exec_Resp_Shop_List_BaseInfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_MULTIPLE_MESSAGE://多媒体信息包
	    	AnalysisData.exec_Resp_Multiple_Message(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_BugInfoList:// 客服邮件信息
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_DateTime_Info://日期包
	    	AnalysisData.exec_Resp_Accept_DateTime_Info(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_SHOPTEMPLATE_INFO:// 店铺基本模板信息
	    	AnalysisData.exec_Resp_Accept_ShopTemplate_Info(dis);
			break;//
		case GameProtocol.CONNECTION_ACCEPT_BUILDINGTEMPLATE_INFO://公共建筑模板信息包
			AnalysisData.exec_Resp_Accept_BuildingTemplate_Info(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_PublicBuilding_Base_Info:// 公益建筑基本信息
			AnalysisData.publicBuildingBaseInfo(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_Publicbuilding_Base_Infos:// 公益建筑信息列表
			AnalysisData.PublicBuildingList(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_MAPAROUND_SHOP://基准个子周边的店铺
			AnalysisData.exec_Resp_Accept_MapAround_Shop(dis);
			break;
	    //1072登录结果包没写
		case GameProtocol.CONNECTION_ACCEPT_SHOP_BASEINFO:
			AnalysisData.exec_Resp_Shop_BaseInfo(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_MAP_SHOP:// 添加地图上的建筑
			AnalysisData.exec_Resp_Accept_Map_Shop_Add(dis);
			break;
		case GameProtocol.CONNECTION_RESP_PACKET_RESULT:// 上行结果响应包
			AnalysisData.exec_Resp_Packet_Result(dis);
			break;	
		case GameProtocol.CONNECTION_RESP_REQUESTFINISH:// 客户端响应结果包
			AnalysisData.exec_Pakcet_finish(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TIPSMESSAGE:// tip消息
			AnalysisData.exec_Resp_TipMessage(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TWOSUREMSG:// 二次确认通知包下行
			AnalysisData.exec_Resp_TwoSureMsg(dis);
			break;
		case GameProtocol.MALL_LIST://商场道具列表
			AnalysisData.exec_Props_mall(dis);
		break;
		case GameProtocol.PROPS_LIST:// 道具列表
			AnalysisData.exec_Props_list(dis);
			break;
		case GameProtocol.PROPS_UPDATE:// 道具更新
			AnalysisData.exec_Props_update(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_STAFF_LIST_INFO://员工列表
			AnalysisData.exec_Resp_Staff_List_Info(dis);
			break;
		case GameProtocol.CONNECTION_SKILL_LIST_REQ://技能更新包
			AnalysisData.exec_Resp_SkillUpdate(dis);
			break;
		case GameProtocol.CONNECTION_SKILL_LIST_RESP://技能列表响应
			AnalysisData.exec_Resp_SkillList(dis);
			break;
		case GameProtocol.CONNECTION_RESP_EMPUPDATA:// 员工属性更新包
			AnalysisData.exec_Resp_EmpUpData(dis);
		case GameProtocol.CONNECTION_Shop_Avai_Recruit_List_Resp:/** 招揽店铺信息列表 */
			AnalysisData.shop_Recruit_List(dis);
			break;
		case GameProtocol.CONNECTION_RESP_SHOP_UPDATA:// 店铺属性更新包
			AnalysisData.exec_Resp_ShopUpdata(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TASKUPDATA:// 任务更新包
			AnalysisData.exec_Resp_TaskUpdata(dis);
			break;
		case GameProtocol.CONNECTION_RESP_DETAIL:// 任务详情
			AnalysisData.exec_Resp_TaskDetailUpdata(dis);
			break;	
		case GameProtocol.CONNECTION_RESP_RelationList_Resp:// 好友
			AnalysisData.exec_RelationList_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_IsOnline_Resp:// 好友是否在线信息
			AnalysisData.exec_IsOnline_Info(dis);
			break;
		case GameProtocol.CONNECTION_Relation_Update_Resp:// 好友更新
			AnalysisData.exec_Relation_Updata_Info(dis);
			break;
		case GameProtocol.CONNECTION_MAIL_INFO_LIST_RESP:// 加载邮件
			AnalysisData.exec_Resp_Mail_info_list(dis);
			break;
		case GameProtocol.CONNECTION_Search_User_List_Resp:
			AnalysisData.exec_Search_User_List(dis);
			break;

		
		case GameProtocol.CONNECTION_ACCEPT_PROMOTER_Req:
			AnalysisData.exec_Promoter_Friend_List(dis);
			break;

		case GameProtocol.Resp_SearchStaff://搜索员工应答包
			AnalysisData.exec_searchStaff(dis);
			break;
		case GameProtocol.CONNECTION_ANIMATIONCONTROL://进货大转盘
			AnalysisData.exec_Resp_AnimationControl(dis);
			break;
//		case GameProtocol.Staff_Base_Info://员工基本信息
//			Staff_Base_Info(dis);
		case GameProtocol.CONNECTION_EXIT_GMAE:// 游戏推出包
			AnalysisData.exec_Exit_Game(dis);
			break;
		case GameProtocol.CONNECTION_SEND_JointAdvocacy_Req:
			AnalysisData.exec_Resp_TwoSureMsg_Input(dis);
			break; 
		case GameProtocol.CONNECTION_RESP_COfC_Create_Info:// 创建商会条件信息包
			AnalysisData.exec_QOfC_Create_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Base_Info:// 商会基本信息
			AnalysisData.exec_QOfC_COfC_Base_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_List_Info:// 商会列表信息
			AnalysisData.exec_COfC_List_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_JointAdvocacyTemplates://商会联合宣传模板信息包
			AnalysisData.exec_COfC_JointAdvocacyTemplates(dis);
			break;
		case GameProtocol.CONNECTION_RESP_JointAdvocacy_List://商会联合宣传列表
			AnalysisData.exec_COfC_JointAdvocacy_List(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Member_Info:// 商会会员基本信息
			AnalysisData.exec_QOfC_COfC_Member_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfCStatusUpdate_Info:// 商会属性更新包
			AnalysisData.exec_COfCStatusUpdate_Info(dis);
			break;
		case GameProtocol.CONNECTION_SEND_CofC_Request_Info:// 商会邀请或申请的请求
			AnalysisData.exec_CofC_Request_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Member_Change_Info:// 商会成员变更
			AnalysisData.exec_COfC_Member_Change_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_CofcLogList:// 商会最近操作日志
			AnalysisData.exec_COfC_CofcLogList(dis);
			break;
		case GameProtocol.CONNECTION_RESP_CofCLogAdd:// 商会操作日志追加
			AnalysisData.exec_COfC_CofCLogAdd(dis);		
			break; 
		case GameProtocol.CONNECTION_RESP_LEVELUP_VIEW://升级信息响应
			AnalysisData.exec_Resp_levelupInfo(dis);
			break;
		case GameProtocol.CONNECTION_NEW_MAIL_COMING:
			AnalysisData.exec_Resp_New_Mail_Coming(dis);
			break;
		case GameProtocol.LOTTERY_TACKET_GET_LIST://彩票列表信息
			AnalysisData.exec_LotteryTicket(dis);
			break;
		case GameProtocol.LOTTERY_TACKET_GET_INFO://彩票中奖信息
			AnalysisData.exec_LotteryTicket_info(dis);
			break;
		case GameProtocol.COUNTDOWNTIME:
			AnalysisData.exec_CountDownTime(dis);
			break;
		case GameProtocol.CONNECTION_RESP_QUESTION_INFO:
			AnalysisData.exec_Resp_QuestionInfo(dis);
			break;
		case GameProtocol.DOCTOR_BASE_INFO://博士基本信息
			AnalysisData.exec_Doctor_Base_Info(dis);
			break;
		case GameProtocol.DOCTOR_STATE_UPDATE://博士状态更新包	
			AnalysisData.Staff_Doctor_updataInfo(dis);
			break;
		case GameProtocol.CONNECTION_RESP_DayTaskSummar:// 每日任务汇总
			AnalysisData.exec_Resp_DayTaskSummar(dis);
		case GameProtocol.CONNECTION_RESP_QUESTION_RESULT_INFO:// 问题正确答案
			AnalysisData.exec_QuestionResult_info_list(dis);
			break;
		case GameProtocol.CardList:// 卡片列表
			AnalysisData.cardlist(dis);
			break;
		case GameProtocol.Staff_Base_Info:
			AnalysisData.Staff_Base_Info(dis);
			break;
		case GameProtocol.PaperDollResp://纸娃娃系统
			AnalysisData.Staff_PaperDollResp(dis);
			break;
		case GameProtocol.ExchangeList://礼包列表
			AnalysisData.Staff_ExchangeList(dis);
			break;
		case GameProtocol.JointPropaUpdata://联合宣传列表更新包
			AnalysisData.Staff_JointPropaUpdata(dis);
			break;
		case GameProtocol.BadgeList://徽章列表
			AnalysisData.Staff_badgeList(dis);
			break;
		case GameProtocol.BadgeUpdate://徽章更新
			AnalysisData.exec_Badge_update(dis);
			break;
		case GameProtocol.ElectionList://选区信息列表
//			AnalysisData.exec_Election_List(dis);
			break;
		case GameProtocol.CandidateList://候选人信息列表
			AnalysisData.exec_Candiadate_List(dis);
			break;
		case GameProtocol.AlloteFund://分配竞选基金
			AnalysisData.exec_AlloteFund(dis);
			break;
	    default:
	    	break;
	    
	    }
		return false;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	 

	
}
