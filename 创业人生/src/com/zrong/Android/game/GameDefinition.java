package com.zrong.Android.game;

import android.graphics.Typeface;

/**
 * 
 *<p>Titile:GameDefinition</p>
 *<p>Description:��������������һ������Ҫ�������Ϸ����</p>
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
     * ��Ļ��
     */
	public static int screenWidth;
	/**
	 * ��Ļ��
	 */
	public static int screenHeight;
	
	/*��ʼ-> ��Ϸ�߼�״̬ */
	/**
	 * ������Ƶ
	 */
	public static final int Game_PowerVideo = 0;
	/**
	 * logo
	 */
	public static final int	Game_Logo		= 1;
	public static final int	Game_MainMenu	= 2;
	
	/**
	 * ��¼����
	 */
	public static final int Game_Login = 7;
	/**
	 * ��������
	 */
	public static final int Game_SelectSever = 8;
	/**
	 * loading
	 */
	public static final int Game_Loading = 9;
	
	/**
	 * ѡ���ɫ
	 */
	public static final int Game_CreateCharactor = 10;
	
	/**
	 * ��ʾ��Ϣ
	 */
	public static final int Game_TipBox = 11;
	
	/**
	 * �칫��
	 */	
	public static final int Game_Office = 12;
	
	/**
	 * ��ͼ
	 */
	public static final int Game_Map = 13;
	
 
	/**
	 * ��������
	 */
	public static final int Game_CreateBuilding =14;
	
 
	/**
	 * �鿴��������
	 */
	public static final int Game_ShopInfo = 15;
	
	
	/*����->��Ϸ�߼�״̬ */
	
	
	
	/*��ʼ View�����id*/
	/**
	 * ��Ƶ���Ž���
	 */
	public static final int VideoPlayerView = 0;
	/**
	 * ���˵�����
	 */
	public static final int MainMenuView = 1;
	/**
	 * ��¼����
	 */
	public static final int LoginView = 2;
	/**
	 * ��������
	 */
	public static final int SelectSever = 3;
	/**
	 * loading...
	 */
	public static final int LoadingView = 4;
	
	/**
	 * ��ʾ��Ϣ��
	 */
	public static final int TipBoxView = 5;
	/**
	 * ������ɫ
	 */
	public static final int CreateCharactorView = 6;
	/**
	 * �칫�ҽ���
	 */
	public static final int OfficeView = 7;
	
	/**
	 * ��ͼ����
	 */
	public static final int MapView = 8;
	
	/**
	 * ������������
	 */
	public static final int CreateBuildingView = 9;
	
	/**
	 * ��������
	 */
	public static final int ShopInfoView = 10;
	
	
	/*���� View�����id*/
	
	
	
	//��ʾ��һ����Ϣ(���ڶԻ�ģʽ)
	public static final int PAGEDOWN_KEYPRESS	=11;
	//ȷ��(���ڶԻ�ģʽ)
	public static final int OK_KEYPRESS		=12;
	//ȡ��(���ڶԻ�ģʽ)
	public static final int CANCEL_KEYPRESS	=13;
	
	
	//++++++++++++++++++++���ӵ�ַ+++++++++++++++++++++++++++
//	/**
//	 * ��������
//	 */ 
//	public static String ServerSelecetURL="http://112.25.14.45/AreaLine/getGameLineServer?version=10100";
//	/**
//	 * �ߵ�λ
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
	
	public static final int REQWRITE_MESSAGE = 3;//����д��Ϣ
	//=======================================
	
	
}

