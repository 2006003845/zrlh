package cn.zrong.connection;

public class Protocol {
	public static final short JSONTAG = 0x0001;

	// 以下是游戏逻辑协议
	/**
	 * 快速注册处理标识
	 */
	public static final short msgType_fastRegist = 1002;
	/**
	 * 注册处理标识
	 */
	public static final short msgType_regist = 1003;
	/**
	 * 登录处理标识
	 */
	public static final short msgType_login = 1004;
	/**
	 * 更改用户名跟密码
	 */
	public static final short msgType_changeAccountAndPsd = 1008;
	/**
	 * 设置密码
	 */
	public static final short msgType_setPsd = 1005;
	/**
	 * 发送新鲜事儿标识
	 */
	public static final short msgType_sendWeibo = 1704;
	/**
	 * 发送评论标识
	 */
	public static final short msgType_commentWeibo = 1706;
	/**
	 * 创建角色
	 */
	public static final short msgType_createRole = 7001;

	/**
	 * 登录角色
	 */
	public static final short msgType_loginRole = 7003;
	/**
	 * 更改个人信息
	 */
	public static final short msgType_alertRole = 7005;
	/**
	 * 修改微博用户信息
	 */
	public static final short msgType_alertWeiboUser = 1701;
	/**
	 * 列出角色信息
	 */
	public static final short msgType_listRole = 7002;

	/**
	 * 获取角色信息
	 */
	public static final short msgType_getRoleInfo = 7006;

	/**
	 * 浏览好友列表
	 */
	public static final short msgType_browseFriends = 1624;
	public static final short msgType_searchFriends = 7014;

	public static final short msgType_browseVerifyFriends = 1625;

	public static final short msgType_addAsFriend = 1621;

	public static final short msgType_removeFriend = 1622;

	public static final short msgType_authFriend = 1626;

	/**
	 * 搜索微博
	 */
	public static final short msgType_search_weibo = 1718;

	/**
	 * 搜索用户
	 */
	public static final short msgType_search_user = 1736;
	// /**
	// * 搜索微群
	// */
	// public static final short msgType_searchGroup = ;

	/**
	 * 查询话题
	 */
	public static final short msgType_searchHuati = 1719;

	/**
	 * 查询用户资料
	 */
	public static final short msgType_searchUserInfo = 1720;

	/**
	 * 浏览消息
	 */
	public static final short msgType_browseInfo = 1705;

	/**
	 * 浏览标签列表
	 */
	public static final short msgType_browseLabels = 7007;

	/**
	 * 浏览城市列表
	 */
	public static final short msgType_browseCitys = 7008;

	/**
	 * 浏览评论
	 */
	public static final short msgType_browseComments = 1707;

	/**
	 * 浏览微群
	 */
	public static final short msgType_browseWeiqunInfo = 1717;

	/**
	 * 浏览微群成员
	 */
	public static final short msgType_browseWeiqunMember = 1715;

	/**
	 * 浏览关注
	 */
	public static final short msgType_browseAttentions = 1709;

	/**
	 * 通过标签获取推荐用户
	 */
	public static final short msgType_getUsersByLabel = 7010;

	/**
	 * 浏览微群
	 */
	public static final short msgType_browseGroups = 1729;

	public static final short msgType_browseVerifyGroupMemberList = 1713;
	/**
	 * 浏览微群
	 */
	public static final short msgType_createGroups = 1711;
	/**
	 * 退出微群
	 */
	public static final short msgType_exitGroups = 1716;

	public static final short msgType_alterGroups = 1731;
	/**
	 * 获取群详情
	 */
	public static final short msgType_getGroupInfo = 1730;

	/**
	 * 加入微群
	 */
	public static final short msgType_joinGroup = 1712;

	/**
	 * 群资料
	 */
	public static final short msgType_groupInfo = 1234;

	/**
	 * 群消息
	 */
	public static final short msgType_groupMsg = 1717;

	/**
	 * 群成员
	 */
	public static final short msgType_groupMembers = 1715;

	/**
	 * 查询群名称是否存在（1733）
	 */
	public static final short msgType_groupRepeat = 1733;

	/**
	 * 查询昵称是否存在（7011）
	 */
	public static final short msgType_nickNameRepeat = 7011;

	/**
	 * 添加关注
	 */
	public static final short msgType_addAttention = 1708;

	/**
	 * 微群加入验证
	 */
	public static final short msgType_agreeAddGroupRequest = 1714;
	/**
	 * 好友加入验证
	 */
	public static final short msgType_agreeAddFriendRequest = 1626;

	/**
	 * 取消关注
	 */
	public static final short msgType_cancelAttention = 1727;

	/**
	 * 接收邮件
	 */
	public static final short msgTYpe_getEmailList = 1602;

	/**
	 * 接收系统消息
	 */
	public static final short msgTYpe_getSysMsgList = 1734;

	/**
	 * 获取待认证好友
	 */
	public static final short msgType_getRequestListOfFriend = 1625;

	/**
	 * 获取待认证圈员
	 */
	public static final short msgType_getRequestListOfGroup = 1713;

	/**
	 * 发送私信消息
	 */
	public static final short msgType_sendMsg = 1735;

	/**
	 * 提交本地应用列表
	 */
	public static final short msgType_postGameItemListOfClient = 7101;
	/**
	 * 获取客户端内置游戏热度7102
	 */
	public static final short msgType_getGameItemListOfClient = 7102;

	/**
	 * 获取游戏分类列表
	 */
	public static final short msgType_getGameTypeList = 7103;

	/**
	 * 获取推广游戏列表
	 */
	public static final short msgType_getRecommendGameList = 7104;

	/**
	 * 获取游戏排行列表
	 */
	public static final short msgType_getGameItemRanking = 7105;

	public static final short msgType_getGoodsList = 2500;

	public static final short msgType_buyGoods = 2501;

	public static final short msgType_saveRoleShow = 7012;

	public static final short msgType_getRoleShow = 7013;

	public static final short msgType_getHallGames = 7108;

	public static final short msgType_removeGroup = 1737;

	/**
	 * 反馈
	 */
	public static final short msgType_sendFeedBack = 7107;
	/**
	 * Error Response
	 */
	public static final String[] response_codes = new String[] { "0", "s1001",
			"s1011", "s1012", "s1013", "s1014", "s1015", "s1016", "s1017",
			"s1018", "s1019", "s1020", "s1100", "s1200", "s5000", "s6000",
			"s4444", "s9999" };
	/**
	 * 操作成功 0
	 */
	public static final int RESPONSE_CODE_SUCCESS = 0;
	/**
	 * 用户名或者密码为空 1
	 */
	public static final int RESPONSE_CODE_EMPTY = 1;
	/**
	 * 没有伪码 2
	 */
	public static final int RESPONSE_CODE_NULLKEY = 2;
	/**
	 * 用户名不存在 3
	 */
	public static final int RESPONSE_CODE_USER_INEXISTENCE = 3;
	/**
	 * 用户名已存在 4
	 */
	public static final int RESPONSE_CODE_USER_HAVEEXISTENCE = 4;
	/**
	 * 用户名或密码输入有误 5
	 */
	public static final int RESPONSE_CODE_INPUT_ERROR = 5;
	/**
	 * 用户已登录 6
	 */
	public static final int RESPONSE_CODE_HAVE_LOGIN = 6;
	/**
	 * 手机号码格式不对 7
	 */
	public static final int RESPONSE_CODE_PHONENUM_ERROR_FORM = 7;
	/**
	 * 原手机号不对 8
	 */
	public static final int RESPONSE_CODE_ORIGINAL_PHONE_ERROR = 8;
	/**
	 * 用户被屏蔽 9
	 */
	public static final int RESPONSE_CODE_USER_SHIELDED = 9;
	/**
	 * 用户原密码不对 10
	 */
	public static final int RESPONSE_CODE_ORIGINAL_PASSWORD_ERROR = 10;
	/**
	 * 用户产品扩展信息不存在 11
	 */
	public static final int RESPONSE_CODE_PRODUCT_INFO_ERROR = 11;
	/**
	 * 渠道号码不存在 12
	 */
	public static final int RESPONSE_CODE_CHANNEL_INEXISTENCE = 12;
	/**
	 * 产品号码不存在 13
	 */
	public static final int RESPONSE_CODE_PRODUCT_ID_INEXISTENCE = 13;
	/**
	 * 移动信息不正确 14
	 */
	public static final int RESPONSE_CODE_MOBILE_INFO_ERROR = 14;
	/**
	 * 没有移动信息 15
	 */
	public static final int RESPONSE_CODE_NULL_MOBILE_INFO = 15;
	/**
	 * 非法操作 16
	 */
	public static final int RESPONSE_CODE_ILLEGAL_OPERATION = 16;
	/**
	 * 系统错误 17
	 */
	public static final int RESPONSE_CODE_SYSTEM_ERROR = 17;
	/**
	 * 社区+文明
	 */
	public static final String[] PRODUCT_CODES = new String[] { "99999",
			"90001" };
	/**
	 * 渠道代码 950 公司内部员工安卓版 900 公司内部员工 850 公司内安卓版 800 公司内渠道推广 350 当乐安卓版 300 当乐渠道
	 * 250 移动免费安卓版 200 移动免费版 150 移动安卓版 100 移动渠道推广
	 */
	public static final String CHANNEL_CODE = "950";

	/**
	 * 客户端类型: 0 Kjava客户端 1 Wap 2 Android客户端 3 IOS客户端 4 Web
	 */
	public static final int CLIENT_TYPE = 2;

	/**
	 * 聊天室基础操作代码
	 */
	public static final String[] operation_codes_chat = new String[] {};
	/**
	 * 邮件操作代码
	 */
	public static final String[] operation_codes_mail = new String[] {};
	/**
	 * 好友操作代码
	 */
	public static final String[] operation_codes_friend = new String[] {};
	/**
	 * 商城操作代码
	 */
	public static final String[] operation_codes_store = new String[] {};
	/**
	 * 角色操作代码
	 */
	public static final String[] operation_codes_role = new String[] { "0",
			"u1001", "u1002", "u1003", "u1004", "u1005" };
	public static final int OPERATION_CODES_ROLE_NAME_EXISTENCE = 1;
	public static final int OPERATION_CODES_ROLE_NAME_NULL = 2;
	public static final int OPERATION_CODES_ROLE_INDEX_NULL = 3;
	public static final int OPERATION_CODES_ROLE_INEXISTENCE = 4;
	public static final int OPERATION_CODES_ROLE_INFO_NULL = 5;
	/**
	 * 微博操作代码
	 */
	public static final String[] OPERATION_CODES_WEIBO = new String[] { "0",
			"wb1001", "wb1002", "wb1003", "wb1004", "wb1005", "wb1006",
			"wb1007", "wb1008", "wb1009", "wb1010", "wb1011", "wb1012",
			"wb1013", "wb1014", "wb1015", "wb1016", "wb1017", "wb1018",
			"wb1019", "wb1020", "wb1021", "wb1022", "wb1023", "wb1024",
			"wb1025", "wb1026", "wb1027", "wb1028", "wb1029", "wb1030",
			"wb1031", "wb1032", "wb1033", "wb1034", "wb1035" };
	/**
	 * 操作成功
	 */
	public static final int OPERATION_CODES_WEIBO_Success = 0;
	/**
	 * 昵称名称已存在
	 */
	public static final int OPERATION_CODES_WEIBO_NICKNAME_EXISTENCE = 1;

	/**
	 * 昵称名为空
	 */
	public static final int OPERATION_CODES_WEIBO_NICKNAME_NULL = 2;
	/**
	 * 昵称不存在
	 */
	public static final int OPERATION_CODES_WEIBO_NICKNAME_INEXISTENCE = 3;
	/**
	 * 没有昵称信息
	 */
	public static final int OPERATION_CODES_WEIBO_NICKNAME_INFO_NULL = 4;
	/**
	 * wb1005 等待微群审核
	 */
	public static final int OPERATION_CODES_WEIBO_VERIFYING = 5;
	/**
	 * wb1006 微群不存在
	 */
	public static final int OPERATION_CODES_WEIBO_OTHER1 = 6;
	/**
	 * wb1007 已达到群上限
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_OTHER12 = 7;
	/**
	 * wb1008 审核失败
	 */
	public static final int OPERATION_CODES_WEIBO_VERIFYING_FAILED = 8;

	/**
	 * wb1009 没有话题列表
	 */
	public static final int OPERATION_CODES_WEIBO_HUATI_LIST_NULL = 9;
	/**
	 * wb1010 没有标签列表
	 */
	public static final int OPERATION_CODES_WEIBO_LABEL_LIST_NULL = 10;
	/**
	 * wb1011 没有微博唯一ID
	 */
	public static final int OPERATION_CODES_WEIBO_ID_INEXISTENCE = 11;
	/**
	 * wb1012 没有消息列表
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_MSG_NULL = 12;
	/**
	 * wb1013 没有标签信息
	 */
	public static final int OPERATION_CODES_WEIBO_HUATI_INFO_NULL = 13;
	/**
	 * wb1014 原始消息不存在
	 */
	public static final int OPERATION_CODES_WEIBO_LABEL_INFO_NULL = 14;
	/**
	 * wb1015 评论消息不能为空
	 */
	public static final int OPERATION_CODES_WEIBO_ORIGINAL_MSG_INEXISTENCE = 25;
	/**
	 * wb1016 发送消息不能为空 wb1017
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_COMMENT_MSG_NULL_ERROR = 26;
	/**
	 * 没有评论信息
	 */
	public static final int OPERATION_CODES_WEIBO_SEND_MSG_NULL_ERROR = 27;
	/**
	 * wb1018 没有关注用户ID
	 */
	public static final int OPERATION_CODES_WEIBO_COMMENT_MSG_NULL = 28;
	/**
	 * wb1019 已经关注过该用户
	 */
	public static final int OPERATION_CODES_WEIBO_ATTENTION_USER_ID_NULL = 29;
	/**
	 * wb1020 没有关注用户列表
	 */
	public static final int OPERATION_CODES_WEIBO_ATTENTION_USER_LIST_NULL = 30;
	/**
	 * wb1021 没有粉丝用户列表
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_FANS_LIST_NULL = 31;
	/**
	 * wb1022 微群名称已存在
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_NAME_HAVE_EXISTENCE = 32;
	/**
	 * wb1023 微群名称不能为空
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_NAME_CANNOT_NULL = 33;
	/**
	 * wb1024 微群不存在
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_INEXISTENCE = 34;
	/**
	 * wb1025 超过微群成员上限
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_FULL = 35;
	/**
	 * wb1026 微群认证消息内容不能为空
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_authentication_info_NULL = 36;
	/**
	 * wb1027 不是微群群主，禁止操作
	 */
	public static final int OPERATION_CODES_WEIBO_FORBID_OPERATION = 37;
	/**
	 * wb1028 没有需要认证的信息
	 */
	public static final int OPERATION_CODES_WEIBO_NONE_NEED_authentication_info = 38;
	/**
	 * wb1029 微群认证信息不存在
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_GROUP_authentication_info_INEXISTENCE = 39;
	/**
	 * wb1030 无权浏览该群成员
	 */
	public static final int OPERATION_CODES_WEIBO_NONE_ROOT_SCAN = 40;
	/**
	 * wb1031 没有用户信息
	 */
	public static final int OPERATION_CODES_WEIBO_USER_INFO_INEXISTENCE = 41;
	/**
	 * wb1032 管理员不能退群
	 */
	public static final int OPERATION_CODES_WEIBO_MANAGER_CANNOT_EXIT = 42;
	/**
	 * wb1033 已经收藏该内容
	 */
	public static final int OPERATION_CODES_WEIBO_HAVE_COLLECTION = 43;
	/**
	 * wb1034 收藏内容不存在
	 * 
	 */
	public static final int OPERATION_CODES_WEIBO_COLLECTION_NULL = 44;
	/**
	 * wb1035 举报内容已存在
	 */
	public static final int OPERATION_CODES_WEIBO_REPORT_INFO_EXISTENCE = 45;
	/**
	 * wb1036 没有收藏列表
	 */
	public static final int OPERATION_CODES_WEIBO_COLLECTION_LIST_NULL = 46;

	public class ProtocolKey {
		/**
		 * request
		 */
		public static final String KEY_REQUEST = "request";
		/**
		 * response
		 */
		public static final String KEY_RESPONSE = "response";
		/**
		 * queue
		 */
		public static final String KEY_queue = "queue";

		/**
		 * List
		 */
		public static final String KEY_list = "List";
		/**
		 * 处理标识
		 */
		public static final String KEY_msgType = "msgType";

		/**
		 * 产品代号
		 */
		public static final String KEY_userApp = "userApp";
		/**
		 * 渠道号码
		 */
		public static final String KEY_channel = "channel";
		/**
		 * 客户端类型
		 */
		public static final String KEY_platform = "platform";
		/**
		 * 手机唯一编码
		 */
		public static final String KEY_mac = "mac";
		/**
		 * 
		 */
		public static final String KEY_fastReg = "faseReg";
		/**
		 * 移动信息
		 */
		public static final String KEY_iMobile = "iMobile";

		/**
		 * 处理结果代码
		 */
		public static final String KEY_status = "status";
		/**
		 * 处理结果list长度
		 */
		public static final String KEY_count = "count";
		/**
		 * 用户key
		 */
		public static final String KEY_keyID = "keyID";

		/**
		 * 用户名
		 */
		public static final String KEY_Name = "Name";

		public static final String KEY_header = "header";

		public static final String KEY_name = "name";
		/**
		 * 用户密码
		 */
		public static final String KEY_Pwd = "Pwd";
		/**
		 * 用户新密码
		 */
		public static final String KEY_Npwd = "Npwd";
		/**
		 * 心跳包发送间隔（秒）
		 */
		public static final String KEY_HB = "HB";

		/**
		 * 微博用户ID
		 */
		public static final String KEY_wbID = "wbID";
		/**
		 * 关注微博用户ID
		 */
		public static final String KEY_attentionID = "attentionID";
		/**
		 * 
		 */
		public static final String KEY_wbName = "wbName";
		/**
		 * 微博消息
		 */
		public static final String KEY_content = "content";
		public static final String KEY_img = "img";
		/**
		 * 原微博
		 */
		public static final String KEY_contUrl = "contUrl";
		public static final String KEY_contMsg = "contMsg";
		/**
		 * 微博用户头像
		 */
		public static final String KEY_wbHead = "wbHead";
		/**
		 * 微博用户头像类型
		 */
		public static final String KEY_hdType = "hdType";
		/**
		 * 微博发布时间
		 */
		public static final String KEY_createAt = "time";
		/**
		 * 评论数
		 */
		public static final String KEY_comm = "comm";
		/**
		 * 转发数
		 */
		public static final String KEY_for = "for";
		/**
		 * 点击数
		 */
		public static final String KEY_hit = "hit";
		/**
		 * indexID
		 */
		public static final String KEY_indexID = "indexID";
		/**
		 * 原微博
		 */
		public static final String KEY_source = "source";
		/**
		 * 发布类型
		 */
		public static final String KEY_type = "type";
		/**
		 * 原微博ID
		 */
		public static final String KEY_contID = "contID";
		/**
		 * 搜索key
		 */
		public static final String KEY_key = "key";
		/**
		 * 角色index
		 */
		public static final String KEY_INDEX = "index";

		// /**
		// * 角色名称(昵称)
		// */
		// public static final String KEY_Nick_name = "name";
		/**
		 * 角色性别
		 */
		public static final String KEY_sex = "sex";
		/**
		 * page
		 */
		public static final String KEY_page = "page";
		/**
		 * size
		 */
		public static final String KEY_size = "size";
		/**
		 * title
		 */
		public static final String KEY_title = "title";

		public static final String KEY_info = "info";

		public static final String KEY_Info = "Info";
		/**
		 * group_exp 介绍
		 */
		public static final String KEY_gExp = "gExp";
		/**
		 * group_notice 公告
		 */
		public static final String KEY_gNot = "gNot";
		/**
		 * auth 认证
		 */
		public static final String KEY_gAuth = "gAuth";
		/**
		 * cap 微群上限
		 */
		public static final String KEY_gCap = "gCap";
		/**
		 * gTime创建时间
		 */
		public static final String KEY_gTime = "gTime";
		/**
		 * mem 圈子成员列表
		 */
		public static final String KEY_gMem = "gMem";
		/**
		 * 圈子索引
		 */
		public static final String KEY_gId = "gId";
		/**
		 * gName 圈子名称
		 */
		public static final String KEY_gName = "gName";

		/**
		 * gNum 圈子编号
		 */
		public static final String KEY_gNum = "gNum";
		/**
		 * 圈子管理员ID
		 */
		public static final String KEY_gAdId = "gAdId";
		/**
		 * 圈子管理员名字
		 */
		public static final String KEY_gAdName = "gAdName";
		/**
		 * 是否为群管理员
		 */
		public static final String KEY_isAdmin = "isAdmin";
		/**
		 * 圈子成员数量
		 */
		public static final String KEY_gSize = "gSize";
		/**
		 * 加入圈子请求消息
		 */
		public static final String KEY_gMsg = "gMsg";
		/**
		 * 成员ID
		 */
		public static final String KEY_uID = "uID";

		public static final String KEY_roleID = "roleID";
		public static final String KEY_roleId = "roleId";
		public static final String KEY_action = "action";
		
		public static final String KEY_exec = "exec";

		public static final String KEY_roleName = "roleName";

		public static final String KEY_msg = "msg";

		/**
		 * 成员名
		 */
		public static final String KEY_uName = "uName";
		public static final String KEY_exp = "exp";
		public static final String KEY_city = "city";
		public static final String KEY_intro = "intro";
		public static final String KEY_label = "label";

		public static final String KEY_explain = "explain";
		public static final String KEY_money = "money";
		/**
		 * 用户系统头像索引
		 */
		public static final String KEY_sys = "sys";
		/**
		 * 用户自定义头像索引
		 */
		public static final String KEY_def = "def";

		public static final String KEY_use = "use";

		public static final String KEY_att = "att";

		public static final String KEY_atten = "atten";

		public static final String KEY_fans = "fans";

		public static final String KEY_speech = "speech";

		/**
		 * 产品代码
		 */
		public static final String KEY_appid = "appid";
		/**
		 * 截止时间
		 */
		public static final String KEY_dtime = "dtime";

		public static final String KEY_state = "state";
		public static final String KEY_aff = "aff";
		public static final String KEY_sender = "sender";

		public static final String KEY_cont = "cont";
		public static final String KEY_time = "time";

		public static final String KEY_recID = "recID";

		public static final String KEY_level = "level";

		public static final String KEY_gIndex = "gIndex";
		public static final String KEY_gClass = "gClass";
		public static final String KEY_gCom = "gCom";
		public static final String KEY_gPack = "gPack";
		public static final String KEY_gVer = "gVer";
		public static final String KEY_gImg = "gImg";
		public static final String KEY_gHit = "gHit";
		public static final String KEY_gTop = "gTop";
		public static final String KEY_gInst = "gInst";
		public static final String KEY_gStar = "gStar";
		public static final String KEY_gDown = "gDown";
		public static final String KEY_gSupp = "gSupp";
		public static final String KEY_gOn = "gOn";

		public static final String KEY_uSex = "uSex";

		public static final String KEY_id = "id";

		public static final String KEY_CLASS = "class";
		public static final String KEY_LIMIT = "limit";
		public static final String KEY_RES = "res";
		public static final String KEY_WAY = "way";

		public static final String KEY_addr = "addr";

		public static final String KEY_pay = "pay";
		public static final String KEY_owning = "owning";
		public static final String KEY_equip = "equip";

		public static final String KEY_spriteurl = "spriteurl";
		public static final String KEY_pngurl = "pngurl";
	}

	public static class ProtocolWeibo {
		/**
		 * 普通微博
		 */
		public static final String TYPE_WeiboSend_NORMAL = "1";
		/**
		 * 群微博
		 */
		public static final String TYPE_WeiboSend_GROUP = "2";
		/**
		 * 转发
		 */
		public static final String TYPE_WeiboSend_Transpond = "3";
		/**
		 * 评论
		 */
		public static final String Comment = "";

	}
}
