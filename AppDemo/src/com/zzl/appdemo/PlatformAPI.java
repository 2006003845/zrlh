package com.zzl.appdemo;

import android.content.res.Resources;

/**
 * 
 * <p>
 * Titile:GameAPI
 * </p>
 * <p>
 * Description:defined Method or constant or varouble when can be used in this
 * project
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 * @date 2012-4-23
 */
public class PlatformAPI {
	public static String ver;// 本客户端 版本

	public static boolean haveNewVer = false;// 是否有版本更新

	public static String newVer;// 服务器最新版本

	public static String verDesc;// 更新版本描述

	public static String shareUrl = "";// 分享地址

	public static boolean Auth = false;

	public static String appDownUrl = "";// app下载更新地址

	public static String appKey = "";// 用极光推送

	public static String masterSecret = "";// 用于极光推送

	/**
	 * 平台：1.andriod 2 iso 3other
	 */
	public static String platform = "1";

	public static String iMobile = "";

	public static String requestUrl = "http://221.130.10.95/profession/Servlet";

	// // 开发地质
//	public static String BaseUrl = "http://192.168.0.19:9990/profession/discuz/";
//	public static String imgUrl = "http://192.168.0.19:9990/profession/discuz/";
	// public static String BaseUrl =
	// "http://218.247.140.193:9990/profession/discuz/";
//	 正式外网地址
	 public static String BaseUrl = "http://prof.51zhixun.com/interface/";
	 public static String imgUrl = "http://prof.51zhixun.com/interface/";

	public static char[] gameSwitch;

	public static final String JSONTAG = "JSON";

	public static final String SELF1TAG = "SELF1";

	public static final String PAIRTAG = "PAIR";

	public static String packageStructure = PAIRTAG;

	/** senceId */
	public static String adventure = "";

	public static String Main_School_Homepage_Url = "http://jj.zrong.cn/index.html";
	public static String Main_School_Bar_Url = "http://jj.zrong.cn/index.html";
	public static String Main_School_More_Url = "http://jj.zrong.cn/index.html";

	public static String Orgid = "default";

	public static String devievid = null;

	public static String alias = "";

	public static String DB = "cus";

	public static String Perosonal_WB_Url = null;

	public static void init(Resources resource) {

	}

	public static int screen_width = 0;
	public static int screen_height = 0;
	public static int statusBarHeight = 0;

}
