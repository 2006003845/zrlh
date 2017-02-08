package com.example.databasedemo;

public class Protocol {
	public static final short JSONTAG = 0x0001;

	public static String Request_Url_Regist = "User_Register_Req.jsp";
	public static String Request_Url_Login = "User_Login_Req.jsp";// 登录接口
	public static String Request_Url_Logout = "User_Logout_Req.jsp";// 退出接口
	public static String Request_Url_OrgSit = "Org_Site_Req.jsp";
	public static String Request_Url_Modify = "User_Modify_Req.jsp";
	public static String Request_Url_UserHead = "User_Head_Req.jsp";
	public static String Request_Url_UserBack = "User_Back_Req.jsp";
	public static String Request_Url_Account_Req = "User_Account_Req.jsp";
	public static String Request_Url_UserAuth = "User_Auth_Req.jsp";
	public static String Request_Url_TeamList = "Team_List_Req.jsp";// 班级列表接口
	public static String Request_Url_TeamMember = "Team_Member_Req.jsp";// 班级成员接口
	public static String Request_Url_Search_Account = "Search_Account_Req.jsp";// 查询本机使用过的账号
	public static String Request_Url_VerUpdateCheck = "Client_Update_Req.jsp";// 版本更新
	/**
	 * 好友操作请求接口(添加/删除)
	 */
	public static String Request_Url_FriendOper = "Friend_Op_Req.jsp";
	/**
	 * 获取好友列表请求接口
	 */
	public static String Request_Url_FriendList = "Friend_List_Req.jsp";
	/**
	 * 获取好友分组列表请求
	 */
	public static String Request_Url_FSortlist = "Friend_Sortlist_Req.jsp";

	/**
	 * 获取用户资料请求
	 */
	public static String Request_Url_UserInfo = "User_Info_Req.jsp";
	/**
	 * 获取消息列表请求接口
	 */
	public static String Request_Url_MsgList = "Msg_List_Req.jsp";
	public static String Request_Url_MsgSend = "Msg_Send_Req.jsp";
	/**
	 * 好友分组操作请求
	 */
	public static String Request_Url_SortOp = "Friend_SortOp_Req.jsp";
	public static String Request_Url_SearchF = "Search_Friend_Req.jsp";

	/**
	 * 好友分组请求
	 */
	public static String Request_Url_Sort = "Friend_Sort_Req.jsp";

	/**
	 * 消息云
	 */
	public static String Request_Url_MsgCloud = "Msg_Cloud_Req.jsp";
	/**
	 * 找回密码
	 */
	public static String Request_Url_FoundPwd = "Obtain_Pwd_Req.jsp";

	public class ProtocolKey {

		public static final String KEY_Uid = "Uid";

		public static final String KEY_Pwd = "Pwd";

		public static final String KEY_Platform = "Platform";

		public static final String KEY_Stat = "Stat";

		public static final String KEY_Msg = "Msg";

		public static final String KEY_BaseURL = "BaseURL";

		public static final String KEY_opType = "opType";

		public static final String KEY_Size = "Size";

		public static final String KEY_Begin = "Begin";

		public static final String KEY_Count = "Count";

		public static final String KEY_Result = "Result";

		public static final String KEY_Name = "Name";
		
		public static final String KEY_Aid = "Aid";

		public static final String KEY_Cert = "Cert";

		public static final String KEY_Uname = "Uname";

		public static final String KEY_user = "user";

		public static final String KEY_User = "User";

		public static final String KEY_Sex = "Sex";

		public static final String KEY_Email = "Email";

		public static final String KEY_Local = "Local";

		public static final String KEY_Phone = "Phone";

		public static final String KEY_Head = "Head";

		public static final String KEY_Back = "Back";

		public static final String KEY_Birth = "Birth";

		public static final String KEY_Prof = "Prof";

		public static final String KEY_ClientVersion = "ClientVersion";

		public static final String KEY_Subjects = "Subjects";

		public static final String KEY_Comments = "Comments";

		public static final String KEY_Resources = "Resources";

		public static final String KEY_InfoType = "InfoType";

		public static final String KEY_ResourceID = "ResourceID";

		public static final String KEY_Comment = "Comment";

		public static final String KEY_Sorce = "Sorce";

		public static final String KEY_List = "List";

		public static final String KEY_Addr = "Addr";

		public static final String KEY_X = "X";

		public static final String KEY_Y = "Y";

		/**
		 * 用户名
		 */
		public static final String KEY_userName = "userName";

		public static final String KEY_userId = "userId";
		/**
		 * 评分
		 */
		public static final String KEY_Scoure = "Scoure";

		/**
		 * 评论用户头像
		 */
		public static final String KEY_userHeadImg = "userHeadImg";
		/**
		 * 评论内容
		 */
		public static final String KEY_content = "content";

		/**
		 * 评论时间
		 */
		public static final String KEY_Time = "Time";

		public static final String KEY_Message = "Message";

		public static final String KEY_id = "id";
		public static final String KEY_name = "name";

		public static final String KEY_Type = "Type";

		public static final String KEY_img = "img";

		public static final String KEY_type = "type";

		public static final String KEY_updateTime = "updateTime";

		public static final String KEY_source = "source";

		public static final String KEY_score = "score";

		public static final String KEY_icon = "icon";
		public static final String KEY_url = "url";
		public static final String KEY_bgColor = "bgColor";

		public static final String KEY_Url = "Url";

		public static final String KEY_Id = "Id";

		public static final String KEY_Gid = "Gid";
		public static final String KEY_Gname = "Gname";

		public static final String KEY_Mid = "Mid";

		public static final String KEY_Content = "Content";

		public static final String KEY_Tid = "Tid";
		public static final String KEY_Tname = "Tname";
		public static final String KEY_Depart = "Depart";

		public static final String KEY_Manager = "Manager";

		public static final String KEY_Status = "Status";
		public static final String KEY_Rname = "Rname";

		public static final String KEY_Clinetid = "Clinetid";

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
