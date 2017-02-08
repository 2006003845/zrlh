package com.zzl.zl_app.connection;

public class Protocol {
	public static final short JSONTAG = 0x0001;

	/**
	 * 上传企业LOGO/营业执照
	 */
	public static String Request_Url_Company_ImgOp = "Company_ImgOp_Req.jsp";
	/**
	 * 删除岗位信息请求
	 */
	public static String Request_Url_Company_Ident = "Company_Ident_Req.jsp";

	/**
	 * 编辑/发布岗位信息请求
	 */
	public static String Request_Url_Recruit_Op = "Recruit_Op_Req.jsp";
	/**
	 * 删除岗位信息请求
	 */
	public static String Request_Url_Recruit_Delete = "Recruit_Delete_Req.jsp";
	/**
	 * 查询岗位详情信息请求
	 */
	public static String Request_Url_Recruit_info = "Recruit_info_Req.jsp";
	/**
	 * 获取岗位列表信息请求
	 */
	public static String Request_Url_Recruit_InfoList = "Recruit_InfoList_Req.jsp";

	public class ProtocolKey {
		/**
		 * Orgid Name Pwd Type Imgext Img
		 */
		public static final String KEY_Name = "Name";
		public static final String KEY_Orgid = "Orgid";
		public static final String KEY_Pwd = "Pwd";
		public static final String KEY_Type = "Type";
		public static final String KEY_Imgext = "Imgext";
		public static final String KEY_Img = "Img";

		public static final String KEY_Stat = "Stat";
		public static final String KEY_Msg = "Msg";
		public static final String KEY_Url = "Url";

		public static final String KEY_name = "name";
		public static final String KEY_phone = "phone";
		public static final String KEY_addr = "addr";
		public static final String KEY_intro = "intro";

		public static final String KEY_type = "type";
		public static final String KEY_job_city = "job_city";
		public static final String KEY_rid = "rid";
		public static final String KEY_people = "people";
		public static final String KEY_salary_min = "salary_min";
		public static final String KEY_salary_max = "salary_max";
		public static final String KEY_contacts_name = "contacts_name";
		public static final String KEY_tel = "tel";
		public static final String KEY_class_ot = "class_ot";
		public static final String KEY_jobdescription = "jobdescription";
		public static final String KEY_description = "description";
		public static final String KEY_outtime = "outtime";
		public static final String KEY_tag = "tag";
		public static final String KEY_address = "address";

		public static final String KEY_city = "city";
		public static final String KEY_postId = "postId";
		public static final String KEY_salary = "salary";
		public static final String KEY_online = "online";
		public static final String KEY_fabu_date = "fabu_date";
		public static final String KEY_lastOpTime = "lastOpTime";

		public static final String KEY_List = "List";

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
