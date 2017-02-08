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
	
	/** Connection���Ӷ��� */
	private static BaseConnection connection = null;

	/** ��ȡConnection���� */
	public static BaseConnection getConnection() 
	{
		return connection;
	}
	/** �������� */

	public static void createConnection(String server, Connection handler) 
	{
		connection = BaseConnection.CreateConnection(
				BaseConnection.C_CONNECTION_TYPE_TCP, server, handler,
				GameDef.userId);
		setKeepAliveStart(System.currentTimeMillis());
	}

	/** �ر����� */
	public static void close() 
	{
		if (connection != null) 
		{
			connection.close();
		}
	}
	
	
	/** �����Ĵ��� */
	private static int connectionTimeOut = 0;

	private static final int MAX_CONNECTION_TIMEOUT = 3;
	// +++++++++++++++++++++++++�������߼�+++++++++++++++++++++++++++++++++++add
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
//			Log.v(TAG, "������������ �������ֵ5�������������,���ҹر�����");
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
			
			 bundle.putString("message", "�����ж�");
			 
			 message.obj = bundle;
			
			 message.arg1 = 1;
			
			 GameData.mhandler.sendMessage(message);
	}
	
	/** ���� */
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
	    case GameProtocol.CONNECTION_RESP_USEUPADAT:// �û����Ը��°�
	    	AnalysisData.exec_Resp_UseUpData(dis);
			break;
	    case GameProtocol.CONNECTION_RECONNECTION_INFO://��������
	    	AnalysisData.exec_Resp_ConnectionInfo(dis);
	    	break;
	    	//�û���¼���а���Ӧ
	    case GameProtocol.CONNECTION_SEND_AUTHLOGIN_RESP:
	    	AnalysisData.exec_Login_Resp(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_SYSTEMMESSAGE://ϵͳ��Ϣ
	    	AnalysisData.exec_Resp_SystemInfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_GAMEMAP_LIST_INFO://��ͼ��Ϣ�б��
	    	AnalysisData.exec_Resp_Map_LIST_INFO(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_CREATE_REG://��ɫ���������
	    	AnalysisData.exec_Resp_Accept_CreateReg(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_CPMSG:// ��˾���������
	    	AnalysisData.exec_Resp_Accept_CreateCp(dis);
	    break;
	    case GameProtocol.CONNECTION_ACCEPT_USERSINFORMATION://��ɫ������Ϣ��
	    	AnalysisData.exec_Resp_Accept_UsersInformation(dis);
	        break;
	    case GameProtocol.CONNECTION_ACCEPT_COMPINFOR://��˾������Ϣ��
	    	AnalysisData.exec_Resp_Accept_CompInformation(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_DEPT_ST_INFO: //�����б���Ϣ��
	    	AnalysisData.exec_Resp_Accept_DepStructureinfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_DEPARTMENT_UPDATE://�������Ը��°�
	    	AnalysisData.exec_Resp_DepartmentUpdata(dis);
	    case GameProtocol.CONNECTIOIN_ACCEPT_SHOP_LIST_BASEINFO:// �����б��
	    	AnalysisData.exec_Resp_Shop_List_BaseInfo(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_MULTIPLE_MESSAGE://��ý����Ϣ��
	    	AnalysisData.exec_Resp_Multiple_Message(dis);
	    	break;
	    case GameProtocol.CONNECTION_RESP_BugInfoList:// �ͷ��ʼ���Ϣ
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_DateTime_Info://���ڰ�
	    	AnalysisData.exec_Resp_Accept_DateTime_Info(dis);
	    	break;
	    case GameProtocol.CONNECTION_ACCEPT_SHOPTEMPLATE_INFO:// ���̻���ģ����Ϣ
	    	AnalysisData.exec_Resp_Accept_ShopTemplate_Info(dis);
			break;//
		case GameProtocol.CONNECTION_ACCEPT_BUILDINGTEMPLATE_INFO://��������ģ����Ϣ��
			AnalysisData.exec_Resp_Accept_BuildingTemplate_Info(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_PublicBuilding_Base_Info:// ���潨��������Ϣ
			AnalysisData.publicBuildingBaseInfo(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_Publicbuilding_Base_Infos:// ���潨����Ϣ�б�
			AnalysisData.PublicBuildingList(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_MAPAROUND_SHOP://��׼�����ܱߵĵ���
			AnalysisData.exec_Resp_Accept_MapAround_Shop(dis);
			break;
	    //1072��¼�����ûд
		case GameProtocol.CONNECTION_ACCEPT_SHOP_BASEINFO:
			AnalysisData.exec_Resp_Shop_BaseInfo(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_MAP_SHOP:// ��ӵ�ͼ�ϵĽ���
			AnalysisData.exec_Resp_Accept_Map_Shop_Add(dis);
			break;
		case GameProtocol.CONNECTION_RESP_PACKET_RESULT:// ���н����Ӧ��
			AnalysisData.exec_Resp_Packet_Result(dis);
			break;	
		case GameProtocol.CONNECTION_RESP_REQUESTFINISH:// �ͻ�����Ӧ�����
			AnalysisData.exec_Pakcet_finish(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TIPSMESSAGE:// tip��Ϣ
			AnalysisData.exec_Resp_TipMessage(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TWOSUREMSG:// ����ȷ��֪ͨ������
			AnalysisData.exec_Resp_TwoSureMsg(dis);
			break;
		case GameProtocol.MALL_LIST://�̳������б�
			AnalysisData.exec_Props_mall(dis);
		break;
		case GameProtocol.PROPS_LIST:// �����б�
			AnalysisData.exec_Props_list(dis);
			break;
		case GameProtocol.PROPS_UPDATE:// ���߸���
			AnalysisData.exec_Props_update(dis);
			break;
		case GameProtocol.CONNECTION_ACCEPT_STAFF_LIST_INFO://Ա���б�
			AnalysisData.exec_Resp_Staff_List_Info(dis);
			break;
		case GameProtocol.CONNECTION_SKILL_LIST_REQ://���ܸ��°�
			AnalysisData.exec_Resp_SkillUpdate(dis);
			break;
		case GameProtocol.CONNECTION_SKILL_LIST_RESP://�����б���Ӧ
			AnalysisData.exec_Resp_SkillList(dis);
			break;
		case GameProtocol.CONNECTION_RESP_EMPUPDATA:// Ա�����Ը��°�
			AnalysisData.exec_Resp_EmpUpData(dis);
		case GameProtocol.CONNECTION_Shop_Avai_Recruit_List_Resp:/** ����������Ϣ�б� */
			AnalysisData.shop_Recruit_List(dis);
			break;
		case GameProtocol.CONNECTION_RESP_SHOP_UPDATA:// �������Ը��°�
			AnalysisData.exec_Resp_ShopUpdata(dis);
			break;
		case GameProtocol.CONNECTION_RESP_TASKUPDATA:// ������°�
			AnalysisData.exec_Resp_TaskUpdata(dis);
			break;
		case GameProtocol.CONNECTION_RESP_DETAIL:// ��������
			AnalysisData.exec_Resp_TaskDetailUpdata(dis);
			break;	
		case GameProtocol.CONNECTION_RESP_RelationList_Resp:// ����
			AnalysisData.exec_RelationList_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_IsOnline_Resp:// �����Ƿ�������Ϣ
			AnalysisData.exec_IsOnline_Info(dis);
			break;
		case GameProtocol.CONNECTION_Relation_Update_Resp:// ���Ѹ���
			AnalysisData.exec_Relation_Updata_Info(dis);
			break;
		case GameProtocol.CONNECTION_MAIL_INFO_LIST_RESP:// �����ʼ�
			AnalysisData.exec_Resp_Mail_info_list(dis);
			break;
		case GameProtocol.CONNECTION_Search_User_List_Resp:
			AnalysisData.exec_Search_User_List(dis);
			break;

		
		case GameProtocol.CONNECTION_ACCEPT_PROMOTER_Req:
			AnalysisData.exec_Promoter_Friend_List(dis);
			break;

		case GameProtocol.Resp_SearchStaff://����Ա��Ӧ���
			AnalysisData.exec_searchStaff(dis);
			break;
		case GameProtocol.CONNECTION_ANIMATIONCONTROL://������ת��
			AnalysisData.exec_Resp_AnimationControl(dis);
			break;
//		case GameProtocol.Staff_Base_Info://Ա��������Ϣ
//			Staff_Base_Info(dis);
		case GameProtocol.CONNECTION_EXIT_GMAE:// ��Ϸ�Ƴ���
			AnalysisData.exec_Exit_Game(dis);
			break;
		case GameProtocol.CONNECTION_SEND_JointAdvocacy_Req:
			AnalysisData.exec_Resp_TwoSureMsg_Input(dis);
			break; 
		case GameProtocol.CONNECTION_RESP_COfC_Create_Info:// �����̻�������Ϣ��
			AnalysisData.exec_QOfC_Create_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Base_Info:// �̻������Ϣ
			AnalysisData.exec_QOfC_COfC_Base_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_List_Info:// �̻��б���Ϣ
			AnalysisData.exec_COfC_List_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_JointAdvocacyTemplates://�̻���������ģ����Ϣ��
			AnalysisData.exec_COfC_JointAdvocacyTemplates(dis);
			break;
		case GameProtocol.CONNECTION_RESP_JointAdvocacy_List://�̻����������б�
			AnalysisData.exec_COfC_JointAdvocacy_List(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Member_Info:// �̻��Ա������Ϣ
			AnalysisData.exec_QOfC_COfC_Member_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfCStatusUpdate_Info:// �̻����Ը��°�
			AnalysisData.exec_COfCStatusUpdate_Info(dis);
			break;
		case GameProtocol.CONNECTION_SEND_CofC_Request_Info:// �̻���������������
			AnalysisData.exec_CofC_Request_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_COfC_Member_Change_Info:// �̻��Ա���
			AnalysisData.exec_COfC_Member_Change_Info(dis);
			break;
		case GameProtocol.CONNECTION_RESP_CofcLogList:// �̻����������־
			AnalysisData.exec_COfC_CofcLogList(dis);
			break;
		case GameProtocol.CONNECTION_RESP_CofCLogAdd:// �̻������־׷��
			AnalysisData.exec_COfC_CofCLogAdd(dis);		
			break; 
		case GameProtocol.CONNECTION_RESP_LEVELUP_VIEW://������Ϣ��Ӧ
			AnalysisData.exec_Resp_levelupInfo(dis);
			break;
		case GameProtocol.CONNECTION_NEW_MAIL_COMING:
			AnalysisData.exec_Resp_New_Mail_Coming(dis);
			break;
		case GameProtocol.LOTTERY_TACKET_GET_LIST://��Ʊ�б���Ϣ
			AnalysisData.exec_LotteryTicket(dis);
			break;
		case GameProtocol.LOTTERY_TACKET_GET_INFO://��Ʊ�н���Ϣ
			AnalysisData.exec_LotteryTicket_info(dis);
			break;
		case GameProtocol.COUNTDOWNTIME:
			AnalysisData.exec_CountDownTime(dis);
			break;
		case GameProtocol.CONNECTION_RESP_QUESTION_INFO:
			AnalysisData.exec_Resp_QuestionInfo(dis);
			break;
		case GameProtocol.DOCTOR_BASE_INFO://��ʿ������Ϣ
			AnalysisData.exec_Doctor_Base_Info(dis);
			break;
		case GameProtocol.DOCTOR_STATE_UPDATE://��ʿ״̬���°�	
			AnalysisData.Staff_Doctor_updataInfo(dis);
			break;
		case GameProtocol.CONNECTION_RESP_DayTaskSummar:// ÿ���������
			AnalysisData.exec_Resp_DayTaskSummar(dis);
		case GameProtocol.CONNECTION_RESP_QUESTION_RESULT_INFO:// ������ȷ��
			AnalysisData.exec_QuestionResult_info_list(dis);
			break;
		case GameProtocol.CardList:// ��Ƭ�б�
			AnalysisData.cardlist(dis);
			break;
		case GameProtocol.Staff_Base_Info:
			AnalysisData.Staff_Base_Info(dis);
			break;
		case GameProtocol.PaperDollResp://ֽ����ϵͳ
			AnalysisData.Staff_PaperDollResp(dis);
			break;
		case GameProtocol.ExchangeList://����б�
			AnalysisData.Staff_ExchangeList(dis);
			break;
		case GameProtocol.JointPropaUpdata://���������б���°�
			AnalysisData.Staff_JointPropaUpdata(dis);
			break;
		case GameProtocol.BadgeList://�����б�
			AnalysisData.Staff_badgeList(dis);
			break;
		case GameProtocol.BadgeUpdate://���¸���
			AnalysisData.exec_Badge_update(dis);
			break;
		case GameProtocol.ElectionList://ѡ����Ϣ�б�
//			AnalysisData.exec_Election_List(dis);
			break;
		case GameProtocol.CandidateList://��ѡ����Ϣ�б�
			AnalysisData.exec_Candiadate_List(dis);
			break;
		case GameProtocol.AlloteFund://���侺ѡ����
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
