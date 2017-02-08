/**
 * 
 */
package com.zrong.Android.online.network;

/**
 * <p>
 * Titile:GameProtocol
 * </p>
 * <p>
 * Description:数据包类型类，这里只存数据类型，不要在这里写函数
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 */
public class GameProtocol {
	static public final int CONNECTION_CREATED = 0x00000ff0;
	static public final int CONNECTION_BROKEN = 0x00000ff1;
	static public final int CONNECTION_MESSAGE = 0x00000ff2;

	static public final int CONNECTION_RECONNECTION_INFO = 0x1002;

	static public final int CONNECTION_EXIT_GMAE = 0x1004;
	/**
	 * 用户平台
	 */
	static public final short HTTP_STANDARD = 0x0001;
	static public final short HTTP_CMCC = 0x0002;// 登陆移动平台
	static public final short HTTP_RES = 0x0003;// 连接资源服务器
	/**
	 * 获取服务器分区分线
	 */
	static public final short HTTP_SERVER_RES = 0x0005;//
	static public final short HTTP_UPDATASESSION = 0x0004;// 更新session
	/** 上行包 **/
	static public final short CONNECTION_SEND_AUTHLOGIN = 0x1001;// 用户权限注册包
	static public final short CONNECTION_SEND_AUTHLOGIN_RESP = 0x1072;// 用户登录下行包
	static public final short CONNECTION_SEND_CREATE_REG = 0x1005;// 创建角色
	static public final short CONNECTION_ACCEPT_SYSTEMMESSAGE = 0x1003;// 系统消息

	/** 接受用户创建结果下行包 **/
	static public final short CONNECTION_ACCEPT_CREATE_REG = 0x1007; // 接受用户创建结果
	/** 用户基本信息下行包 **/
	static public final short CONNECTION_ACCEPT_USERSINFORMATION = 0x1009;// 用户基本信息

	/** 日期包 */
	static public final short CONNECTION_ACCEPT_DateTime_Info = 0x1066;// 日期包
	/** 公司基本信息下行包 **/
	static public final short CONNECTION_ACCEPT_COMPINFOR = 0x100a;// 公司基本信息
	/** 公司架构信息下行包 **/
	// static public final short CONNECTION_ACCEPT_STRUCTURE_INFO = 0x100e;//下行包
	/** 客户端数据请求 */
	static public final short CONNECTION_SEND_ClientDatas_Req = 0x101d;// 客户端数据请求

	/** 部门列表信息 */
	static public final short CONNECTION_ACCEPT_DEPT_ST_INFO = 0x1011;// 部门列表信息
	/** 公司创建结果上行包 **/
	static public final short CONNECTION_SEND_CPINFOR = 0x1008;// 公司基本信息
	/** 公司创建结果下行包 **/
	static public final short CONNECTION_ACCEPT_CPMSG = 0x1012;// 下行包

	/** 获取基准个子周边店铺 上行包 */
	static public final short CONNECTION_SEND_MAPAROUND_SHOP = 0x1015;
	/** 基准个子周边的店铺 下行包 */
	static public final short CONNECTION_ACCEPT_MAPAROUND_SHOP = 0x1019;
	/** 地图上的建筑 */
	static public final short CONNECTION_ACCEPT_MAP_SHOP = 0x101f;
	/** 地图店铺模板包 */
	static public final short CONNECTION_ACCEPT_SHOPTEMPLATE_INFO = 0x101d;
	/** 地图信息列表 */
	static public final short CONNECTION_ACCEPT_GAMEMAP_LIST_INFO = 0x101e;
	/** 建筑模板信息 */
	static public final short CONNECTION_ACCEPT_BUILDINGTEMPLATE_INFO = 0x1031;
	/** 公益建筑创建 */
	static public final short CONNECTION_SEND_CREATE_PUBLICBUIDING = 0x1060;
	/** 公益建筑信息列表 */
	static public final short CONNECTION_ACCEPT_Publicbuilding_Base_Infos = 0x1067;
	/** 公共建筑经理任命请求 */
	static public final short CONNECTION_SEND_PublicBuildStaffChangeReq = 0x1102;
	// /**公益建筑基本信息*/
	static public final short CONNECTION_ACCEPT_PublicBuilding_Base_Info = 0x1032;
	/** 创建店铺 */
	static public final short CONNECTION_SEND_SHOP_CREATE = 0x100a;
	/** 店铺基本信息 */
	static public final short CONNECTION_ACCEPT_SHOP_BASEINFO = 0x100b;
	/** 店铺列表基本信息 */
	static public final short CONNECTIOIN_ACCEPT_SHOP_LIST_BASEINFO = 0x100c;
	/** 创建店铺地址请求 */
	static public final short CONNECTION_REQ_CREATEADDRESS = 0x101c;
	/** 店铺地址下行包 */
	static public final short CONNECTION_ACCEPT_CREATEADDRESS = 0x101c;

	/** 批量招聘员工 */
	static public final short CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH = 0x101a;
	/** 员工信息列表 */
	static public final short CONNECTION_ACCEPT_STAFF_LIST_INFO = 0x100f;
	/** 采购货物请求 */
	static public final short CONNECTION_REQ_PURCHASE_GOODS = 0x1019;
	/** 升级信息请求包 */
	static public final short CONNECTION_REQ_LEVELUP_VIEW = 0x100d;
	/** 公司店铺的升级操作请求 */
	static public final short CONNECTION_REQ_LEVELUP = 0x1040;
	/** 升级信息响应包 */
	static public final short CONNECTION_RESP_LEVELUP_VIEW = 0x1010;
	/** 收入信息 */
	static public final short CONNECTION_RESP_INCOME_INFO = 0x1020;
	/** 货物信息 */
	static public final short CONNECTION_RESP_GOODS_INFO = 0x1021;
	/** 心跳包 */
	static public final short CONNECTION_SEND_KEEPALIVE = 0x1000;
	/** 退出包 */
	static public final short CONNECTION_SEND_CHRARCTER_LOGOUT = 0x101e;
	/** 多媒体信息 */
	static public final short CONNECTION_RESP_MULTIPLE_MESSAGE = 0x1022;
	/** tip消息 */
	static public final short CONNECTION_RESP_TIPSMESSAGE = 0x1023;
	/** 查看员工招聘信息 */
	static public final short CONNECTION_SEND_EMPLOYMENT_STAFF_LIST = 0x1016;
	/** 可雇佣员工信息列表 */
	static public final short CONNECTION_RESP_EMPLOYMENT_STAFF_LIST = 0x101a;
	/** 员工操作请求 */
	static public final short CONNECTION_SEND_STAFF_OP_REQ = 0x1018;
	/** 员工职务任命请求 */
	static public final short CONNECTION_SEND_Staff_DUTY_CHANGE_REQ = 0x1020;
	/** 开会请求上行 */
	static public final short CONNECTION_SEND_STAFF_MEET = 0x1021;

	/** 招聘员工请求 */
	static public final short CONNECTION_SEND_EMPLOYEEMENT_STAFF_REQ = 0x1017;

	/** 开会请求 */
	static public final short CONNECTION_SEND_EMPLOYEEMENT_MEET = 0x1021;
	/** 员工属性更新包 */
	static public final short CONNECTION_RESP_EMPUPDATA = 0x1027;
	/** 用户属性更新包 */
	static public final short CONNECTION_RESP_USEUPADAT = 0x1024;
	/** 二次确认响应包上行 */
	static public final short CONNECTION_SEND_TWOSURE = 0x1024;
	/** 二次确认通知包下行 */
	static public final short CONNECTION_RESP_TWOSUREMSG = 0x1028;

	public static final short CONNECTION_COMMUNICATION_REQ = 0x1022;

	/** 巡视请求 */
	static public final short CONNECTION_SEND_TOUR_REQ = 0x1023;
	/** 店铺公关活动 */
	static public final short CONNECTION_SEND_SHOPPR = 0x1043;
	/** 店铺属性更新包 */
	static public final short CONNECTION_RESP_SHOP_UPDATA = 0x1026;
	/** 部门属性更新包 */
	static public final short CONNECTION_RESP_DEPARTMENT_UPDATE = 0x1044;

	/** 义工 */
	public static final short CONNECTION_COMMONWELL_REQ = 0x1025;

	public static final short CONNECTION_SKILL_TRAINING_REQ = 0x1027;

	/** 宣传 */
	public static final short CONNECTION_PROPAGANDA_REQ = 0x1026;

	/** 技能学习，素质学习 */
	public static final short CONNECTION_STAFF_SKILL_LEARN = 0x1028;
	/** 技能列表请求 */
	public static final short CONNECTION_SKILL_LIST_REQ = 0x1029;

	public static final short CONNECTION_SKILL_LIST_RESP = 0x1030;

	/** 技能更新相应包 */
	public static final short CONNECTION_SKILL_UPDATE_RESP = 0x1029;

	/** 员工续约请求 */
	static public final short CONNECTION_SEND_STAFF_CONTINUE = 0x1042;
	/** 员工调薪请求 */
	static public final short CONNECTION_SEND_WEEKLY_CHANGE = 0x1041;

	static public final short CONNECTION_SEND_CHAT_REQ = 0x1050;

	/** 财务请求操作包 */
	static public final short CONNECTION_SEND_FINANCE = 0x1044;
	/** 任务详情获取请求 */
	static public final short CONNECTION_SEND_TASK_DETAIL = 0x1061;//
	/** 任务操作请求 */
	static public final short CONNECTION_SEND_TASK_OPREQ = 0x1062;
	/** 任务列表刷新请求包 */
	static public final short CONNECTION_SEND_REFRESH_OPREQ = 0x1063;
	/** 任务更新包 */
	static public final short CONNECTION_RESP_TASKUPDATA = 0x1033;// 任务更新包
	/** 任务详情 */
	static public final short CONNECTION_RESP_DETAIL = 0x1034;// 任务详情
	/** 每日任务汇总 */
	static public final short CONNECTION_RESP_DayTaskSummar = 0x1069;// 每日任务汇总

	/** 客户端上行包响应结果 */
	static public final short CONNECTION_RESP_PACKET_RESULT = 0x1042;
	/** 批量升级 */
	static public final short CONNECTION_SEND_LEVELUP_BATCH = 0x1079;
	/** 批量采购 */
	static public final short CONNECTION_SEND_GOODS_BATCH = 0x1078;

	/** 挖人请求 **/
	public static final short REQ_FINDEMPLOY = 0x1105;
	/** 人才市场出售、取消出售、购买员工请求 */
	public static final short REQ_TradeStaff_Req = 0x1106;
	/** 查询员工请求 */
	public static final short REQSearchStaff_Req = 0x1107;
	/** 搜索员工应答包 */
	public static final short Resp_SearchStaff = 0x1090;
	/** 员工基本信息下发包 */
	public static final short Staff_Base_Info = 0x101b;

	// ---------------------------------------------------------------
	/** 部门职务需求信息 */
	static public final short CONNECTION_SEND_DEPARTMENT_OFFICE_INFO = 0x100e;
	public static final short CONNECTION_SEND_MAIL_REQ = 0x1030;
	public static final short CONNECTION_SHOW_MAIL_LIST_REQ = 0x1031;// 显示邮件请求上行包
	public static final short CONNECTION_MAIL_INFO_LIST_RESP = 0x1041;//邮件列表信息响应包
	public static final short CONNECTION_NEW_MAIL_COMING = 0x1040;
	public static final short CONNECTION_MAIL_OPTION_REQ = 0x1032;
	/** 全局统一答题响应请求 */
	public static final short CONNECTION_SEND_GlobalQuestionReq = 0x1082;
	/** 全局统一答题广播 */
	public static final short CONNECTION_RESP_GlobalQuestion = 0x1058;
	/** 任务列表请求 */
	public static final short CONNECTION_SEND_TASKLISTREQ = 0x1071;
	/** 任务列表 */
	public static final short CONNECTION_RESP_TASKLIST_INFO = 0x1047;
	/** 开始答题 */
	public static final short CONNECTION_SEND_QUESTIONSTART = 0x1077;
	/** 回答问题 0x1074 */
	public static final short CONNECTION_SEND_QuestionAnswer = 0x1074;
	/** 显示下一道问题 */
	public static final short CONNECTION_SEND_NEXTQUESTION = 0x1075;
	/** 问题描述 QuestionInfo [0x1050]: */
	public static final short CONNECTION_RESP_QUESTION_INFO = 0x1050;
	/** 问题正确答案 QuestionResult [0x1051]: */
	public static final short CONNECTION_RESP_QUESTION_RESULT_INFO = 0x1051;
	/** 问题得分 QuestionScore [0x1052]: */
	public static final short CONNECTION_RESP_QUESTION_SCORE_INFO = 0x1052;
	/** 商会创建条件信息 */
	public static final short CONNECTION_SEND_COfC_Create_Info_Req = 0x1070;
	/** 商会创建条件请求 */
	public static final short CONNECTION_SEND_COfC_Create_Do_Req = 0x1072;
	/** 商会修改公告请求 */
	public static final short CONNECTION_SEND_COfC_Change_Affiche_Req = 0x1073;
	/** 商会列表信息请求（商会搜索请求） */
	public static final short CONNECTION_SEND_COfC_List_Info_Req = 0x1076;
	/** 创建商会条件信息包(下行) */
	public static final short CONNECTION_RESP_COfC_Create_Info = 0x1045;
	/** 商会基本信息(下行) */
	public static final short CONNECTION_RESP_COfC_Base_Info = 0x1046;
	/** 商会会员基本信息 */
	public static final short CONNECTION_RESP_COfC_Member_Info = 0x1048;
	/** 商会属性更新包 */
	public static final short CONNECTION_RESP_COfCStatusUpdate_Info = 0x1049;
	/** 商会列表信息 */
	public static final short CONNECTION_RESP_COfC_List_Info = 0x104a;
	/** 客户端请求处理结束包 */
	public static final short CONNECTION_RESP_REQUESTFINISH = 0x1061;
	/** 商会基本信息请求 */
	public static final short CONNECTION_SEND_COFC_Base_Info_Req = 0x107a;
	/** 商会邀请或申请的请求 */
	public static final short CONNECTION_SEND_COfC_Join_Req = 0x107b;
	/** 商会申请或邀请的确认包 */
	public static final short CONNECTION_SEND_CofC_Request_Resp = 0x107c;
	/** 商会邀请或申请的请求（下行） */
	public static final short CONNECTION_SEND_CofC_Request_Info = 0x1053;
	/** 商会成员变更 */
	public static final short CONNECTION_RESP_COfC_Member_Change_Info = 0x1057;
	/** 商会成员职务变更请求 */
	public static final short CONNECTION_SEND_COfC_Change_Duty_Req = 0x107d;
	/** 商会捐款请求 */
	public static final short CONNECTION_SEND_COfC_Fees_Req = 0x107e;
	/** 商会最近操作日志 */
	public static final short CONNECTION_RESP_CofcLogList = 0x1070;
	/** 商会操作日志追加 */
	public static final short CONNECTION_RESP_CofCLogAdd = 0x1071;
	/** 商会联合宣传模板信息包 */
	public static final short CONNECTION_RESP_JointAdvocacyTemplates = 0x1073;
	/** 商会联合宣传列表 */
	public static final short CONNECTION_RESP_JointAdvocacy_List = 0x1074;
	/** 发起商会联合宣传请求 */
	public static final short CONNECTION_SEND_Initiated_JointAdvocacy_Req = 0x1088;
	/** 参与商会联合宣传 */
	public static final short CONNECTION_SEND_JointAdvocacy_Req = 0x1089;
	/** 店铺修改卡上行包 */
	static public final short CONNECTION_RESP_TWOSURE_INPUT = 0x1104;// jiangxujin
																		// add
	/** 好友是否在线信息 */
	public static final short CONNECTION_RESP_IsOnline_Resp = 0x1055;// zhangxiaoqing
																		// add
	/** 好友关系列表 */
	public static final short CONNECTION_RESP_RelationList_Resp = 0x1056;
	/** 用户关系添加请求 */
	public static final short CONNECTION_Relation_Add_Req = 0x1080;
	/** 用户关系更新响应 */
	public static final short CONNECTION_Relation_Update_Resp = 0x1054;
	/** 用户关系删除请求 */
	public static final short CONNECTION_Relation_Delete_Req = 0x1081;
	/** 用户上下线提示 */
	public static final short CONNECTION_Relation_Status_Update_Resp = 0x1081;
	/** 搜索用户请求 */
	public static final short CONNECTION_Search_User_Req = 0x1064;
	/** 搜索用户结果列表 */
	public static final short CONNECTION_Search_User_List_Resp = 0x1035;
	/** 角色资料汇总 */
	public static final short CONNECTION_Search_User_Detail_Resp = 0x1063;
	/** 角色资料请求 */
	public static final short CONNECTION_Search_User_Detail_Req = 0x1084;
	/** 请求可招揽客流店铺列表 */
	public static final short CONNECTION_SHOP_Avai_Recruit_List_req = 0x1090;
	/** 店铺客流招揽请求 */
	public static final short CONNECTION_Shop_Recruit_Req = 0x1091;
	/** 店铺驱逐招揽请求 */
	public static final short CONNECTION_Shop_UnRecruit_Req = 0x1092;
	/** 招揽店铺信息 */
	public static final short CONNECTION_Shop_Recruit_Info_Resp = 0x1075;
	/** 招揽店铺信息列表 */
	public static final short CONNECTION_Shop_Avai_Recruit_List_Resp = 0x1076;
	/** 分页显示信息 */
	public static final short CONNECTIOIN_MESSAGE_PAGE = 0x1078;
	/** 通知客户端播放xx动画 */
	public static final short CONNECTION_ANIMATIONCONTROL = 0x1082;
	/** 推荐人列表请求 */
	public static final short CONNECTION_SEND_PROMOTER_Req = 0x1103;
	/** 推荐人列表 */
	public static final short CONNECTION_ACCEPT_PROMOTER_Req = 0x1088;
	
	/**
	 * 创业学堂操作请求
	 */
	public static final short CONNECTION_SEND_VENTURESCHOOL_Req = 0x1109;

	// =============================操作码=============================
	// 用于判断客户端操作结果
	/** 创建店铺 */
	public static final short Op_Create_Shop = 0x4001;
	/** 店铺批量进货 */
	public static final short Purchase_Goods_Batch = 0x4002;
	/** 创建商会 */
	public static final short cofc_create = 0x5001;
	/** 会员职务修改 */
	public static final short cofc_duty = 0x5002;

	/** 招聘员工 */
	public static final short STAFF_EMPLOYMENT = 0x6001;
	/** 员工技能模板 */
	public static final short STAFF_SKILLLIST = 0x6002;
	/** 员工续约 */
	public static final short STAFF_CONTINUE_CONTTACT = 0x6003;
	/** 员工操作 */
	public static final short STAFF_OP = 0x6004;

	/** 宣传 */
	public static final short ACTIVE_PUBLICITY = 0x7001;
	/** 开会 */
	public static final short ACTIVE_MEET = 0x7002;
	/** 义工 */
	public static final short ACTIVE_VOLUNTEER = 0x7003;
	/** 培训 */
	public static final short ACTIVE_TRAIN = 0x7004;

	// =================================================================

	/**
	 * add by zzx; 道具列表 道具更新 道具使用
	 */
	public static final short PROPS_LIST = 0x1059;
	public static final short PROPS_UPDATE = 0x1062;
	public static final short PROPS_USE = 0x1083;

	public static final short MALL_LIST = 0x1065;// 查询商城道具列表请求的响应包（道具列表）
	public static final short MALL_ASK_LIST = 0x1085;// 查询商城道具列表请求--上行包
	public static final short MALL_BUY = 0x1086;
	public static final short SYSTEM_SETING = 0x1087;
	public static final short SYSTEM_UPDATA = 0x1064;
	/** 随机事件下行包 */
	public static final short RANDOM_EVENT_RESP = 0x1068;
	/***/
	public static final short RANKING_LIST = 0x1077;
	public static final short RANKING_ASK = 0x1093;

	// 客服回复下行包

	public static final short CONNECTION_RESP_BugInfoList = 0x1079;
	public static final short CONNECTION_RESP_BugInfoDetail = 0x1080;
	public static final short CONNECTION_RESP_AddBugInfo = 0x1081;

	// 客服上行包
	public static final short CONNECTION_REQ_BufInfoDetailReq = 0x1096;
	public static final short CONNECTION_REQ_BufInfoSubmit = 0X1097;

	// 抽奖
	/**
	 * 抽奖上行包
	 */
	public static final short CONNECTION_REQ_LOTTERY = 0x1100;
	/**
	 * 抽奖下行包
	 */
	public static final short CONNECTION_RESP_LOTTERY = 0x1083;
	/**
	 * 彩票上行包 购买彩票 兑奖
	 * */
	public static final short LOTTERY_TACKET_BUY = 0x1098;//购买彩票
	public static final short LOTTERY_TACKET_GET_MONEY = 0x1099;//兑奖
	public static final short LOTTERY_TACKET_ASK_LIST = 0x1101;//开奖结果
	/** 彩票下行包 */
	public static final short LOTTERY_TACKET_GET_LIST = 0x1084;//开奖结果列表
	public static final short LOTTERY_TACKET_GET_INFO = 0x1087;//开奖结果消息
	/** 倒计时 */
	public static final short COUNTDOWNTIME = 0x1085;
	/**策划部店铺列表上行*/
	public static final short  SEARCHSHOP_REQ = 0x1110;
	/**策划部行为请求包 */
	public static final short PLANNINGACTION_REQ =0x1111;
	/**博士基本信息*/
	public static final short DOCTOR_BASE_INFO = 0x1091;
	/**博士更新包*/
	public static final short DOCTOR_STATE_UPDATE = 0x1092;
	/** */
    public static final short DoctorTrust = 0x1112;
	
	public static final short BuyShop = 0x1113;
	/**卡片列表包*/
	public static final short CardList = 0x1093;

	/** 1.05 新增协议*/
	public static final short PaperDollResp = 0x1094;// 纸娃娃系统

	public static final short SearchExpelShop = 0x1114;// 搜索可被驱逐的店铺列表

	public static final short ExchangeList = 0x1095;// 礼包列表
	public static final short AskExchangeList = 0x1116;// 申请礼包列表

	public static final short Exchange = 0x1115;// 兑换礼包

	public static final short JointPropaUpdata = 0x1096;// 联合宣传更新包

	public static final short SuperStaffWish_Req = 0x1120;// 实现愿望

	public static final short BadgeList = 0x1097;// 徽章列表

	public static final short Badge_Op_Req = 0x1117;// 徽章操作请求

	public static final short BadgeUpdate = 0x1099;// 徽章更新

	public static final short Election = 0x1121;// 竞选相关功能请求
	public static final short ElectionList = 0x2013;// 选区信息列表
	public static final short CandidateList = 0x2011;// 候选人信息列表

	public static final short AlloteFund = 0x1221;// 分配竞选基金
	public static final short ElectionAction = 0x1122;// 当选者职权请求

	public static final short Shop_Unrecruit_List_Req = 0x1114;

}
