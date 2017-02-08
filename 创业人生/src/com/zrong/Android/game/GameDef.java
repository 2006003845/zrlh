package com.zrong.Android.game;

public class GameDef {
	
//	public static final short PUBLIC_VERSION=10301;//客户端发布的版本号
	
	
	public static short current_version=0;//
	
	public static String plat= "2";//平台,android 是2；
	/**
	 * 分区分线
	 */ 
	//public static String ServerSelecetURL="http://112.25.14.45/AreaLine/getGameLineServer?version=10100";
	
	/**
	 * 网关
	 */
	public static String  gateway; 
	
	/**
	 * 高地位
	 */
	public static  String agentCodeHi = "0";
	public static  String agentCodeLo = "0"; 
	
	/**
	 * 移动给的userId
	 */
	public static String userId="";
	
	/**
	 * 资源服务器地址 
	 */
	public static String resServer;
	
	public static String resSever="http://218.247.140.193:9999/GameSrc/AndroidSrc/";
	
	
	/**
	 * 用户平台
	 */
	public static String platform ;
	
	public static String userIDALL="";
	
	public static String reStrs[]=new String[4];
	public static String keyID="";//客户平台返回值
	public static String userID="";//移动伪码
	/**用户业务ID*/
	public static final String userApp = "10001"; 
	
	public static final String CMCC = "1500";
	
	public static final String dangle ="3500";
	
	public static  String channelId ="1500";
	
	//免费版
	public static String GATE_SERVER_PAGE = "http://gmpbeta.i139.cn/bizcontrol/LoginOnlineGame?sender=202&cpId=C00084&cpServiceId=160120003000&fid=1000";
	
	public static String CMCC_Address = "http://game.10086.cn/a";
    
	//---------------------------------------------
	//5元版本
//	public static String GATE_SERVER_PAGE = "http://gmp.i139.cn/bizcontrol/LoginOnlineGame?sender=202&cpId=C00084&cpServiceId=120122666000&channelId=10002000";
//	public static final String channelId = "1000";

	/** 测试线*/
//	public static String ServerSelecetURL = "http://218.247.140.193:9999/AreaLine/getGameLineServer?version="+current_version+"&channel="+channelId;
//	public static String serverseleceturl_1 = "http://218.247.140.193:9999/AreaLine/getGameLineServer?version=";
//	public static String serverseleceturl_2 = "&channel=";
	/** 正式线*/
	public static String ServerSelecetURL = "http://112.25.14.45/AreaLine/getGameLineServer?version="+current_version+"&channel="+channelId;
	public static String serverseleceturl_1 = "http://112.25.14.45/AreaLine/getGameLineServer?version=";
	public static String serverseleceturl_2 = "&channel=";
	//--------------------------------------验证移动信息--------------------------------------------
	 public static String checking_cmcc = "http://112.25.14.45/AreaLine/platformProxy";
}
