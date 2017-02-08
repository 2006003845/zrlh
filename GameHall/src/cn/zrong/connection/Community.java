package cn.zrong.connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import json.JSONTokener;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.HomeActivity;
import cn.zrong.entity.City;
import cn.zrong.entity.Email;
import cn.zrong.entity.Friend;
import cn.zrong.entity.GameItem;
import cn.zrong.entity.GameType;
import cn.zrong.entity.GameUser;
import cn.zrong.entity.Group;
import cn.zrong.entity.Mail;
import cn.zrong.entity.Request;
import cn.zrong.entity.RoleInfo;
import cn.zrong.entity.Status;
import cn.zrong.handler.MessageHandler;
import cn.zrong.utils.Tools;

public class Community {

	private static Community weibo = null;
	private static Context mContext;

	private static Handler handler;

	public static void initHandler() {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				Tools.showToast(mContext, "网络中断.请检查您的网络连接");
			};
		};
	}

	public static Community getInstance(Context context) {
		mContext = context;
		if (weibo == null) {
			weibo = new Community();
		}
		if (checkNetWorkStatus(context)) {
			return weibo;
		} else {
			handler.sendEmptyMessage(1);
		}
		return null;
	}

	private static boolean checkNetWorkStatus(Context context) {
		boolean result;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public List<GameItem> getHallGames() throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_getHallGames,
				IOWriter.getHallGames(Protocol.msgType_getHallGames,
						ApplicationData.currentUser.getKeyID()).getBytes(),
				GameAPI.Port_Role);
		JSONArray array = getJSONArrayFromByte(data);
		if (array != null) {
			return GameItem.getGameItemList(array);
		}
		return null;
	}

	public List<Friend> getFriendList(String type, int page, int size)
			throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_browseFriends,
				IOWriter.getFriendsList(Protocol.msgType_browseFriends,
						ApplicationData.currentUser.getKeyID(), type, page,
						size).getBytes(), GameAPI.Port_Role);
		JSONArray array = getJSONArrayFromByte(data);
		if (array != null) {
			return Friend.getFriendList(array);
		}
		return null;
	}

	public List<Friend> searchFriendList(String key, int page, int size)
			throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_searchFriends,
				IOWriter.getSearchFriends(Protocol.msgType_searchFriends,
						ApplicationData.currentUser.getKeyID(),
						GameAPI.userApp, key, page, size).getBytes(),
				GameAPI.Port_Role);
		JSONArray array = getJSONArrayFromByte(data);
		if (array != null) {
			return Friend.getFriendList(array);
		}
		return null;
	}

	public int addAsFriend(String roleId) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_addAsFriend,
				IOWriter.getAddFriend(Protocol.msgType_addAsFriend,
						ApplicationData.currentUser.getKeyID(), roleId)
						.getBytes(), GameAPI.Port_Role);
		if (data == null) {
			return -1;
		}
		JSONObject obj = getJSONObjFromByte(data);
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		Message msg = Message.obtain(HomeActivity.mInstance.handler);

		if (obj != null && status != null && status.equals("0")) {
			msg.what = MessageHandler.MES_JOINT_GROUP;
			msg.obj = "申请成功发送...";
			HomeActivity.mInstance.handler.sendMessage(msg);
			return 0;
		} else {
			msg.what = MessageHandler.MES_JOINT_GROUP;
			msg.obj = "操作失败";
			HomeActivity.mInstance.handler.sendMessage(msg);
			return -1;
		}

	}

	public boolean removeFriend(String roleId) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_removeFriend,
				IOWriter.getAddFriend(Protocol.msgType_removeFriend,
						ApplicationData.currentUser.getKeyID(), roleId)
						.getBytes(), GameAPI.Port_Role);
		if (data == null) {
			return false;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj != null
				&& obj.getString(Protocol.ProtocolKey.KEY_status).equals("0")) {
			return true;
		}
		return false;
	}

	/**
	 * 认证好友
	 * 
	 * @param index
	 *            (角色Id)
	 * @param action
	 *            (0:拒绝 1:通过)
	 * @return
	 * @throws JSONException
	 */
	public boolean authFriend(String index, int action) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_authFriend,
				IOWriter.getAuthFriend(Protocol.msgType_authFriend,
						ApplicationData.currentUser.getKeyID(), index, action)
						.getBytes(), GameAPI.Port_Role);
		if (data == null) {
			return false;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj != null
				&& obj.getString(Protocol.ProtocolKey.KEY_status).equals("0")) {
			return true;
		}
		return false;
	}

	public List<Friend> getVerifyFriendList() throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_browseVerifyFriends,
				IOWriter.getVerifyFriendsList(
						Protocol.msgType_browseVerifyFriends,
						ApplicationData.currentUser.getKeyID()).getBytes(),
				GameAPI.Port_Role);
		JSONArray array = getJSONArrayFromByte(data);
		if (array != null) {
			return Friend.getFriendList(array);
		}
		return null;
	}

	public static final int TYPE_GROUP_KEY_ALL = 0;
	public static final int TYPE_GROUP_KEY_SELF = 1;

	public List<Group> getGroupList(String key, int type) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_browseGroups,
				IOWriter.getBrowseGroupList(Protocol.msgType_browseGroups,
						ApplicationData.currentUser.getKeyID(), "", key, type)
						.getBytes(), GameAPI.Port_Weibo);
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return Group.getGroupList(array);
	}

	public List<GameUser> getVerifyGroupMemberList(String gNum)
			throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_browseVerifyGroupMemberList,
				IOWriter.getBrowseVerifyGroupMemberList(
						Protocol.msgType_browseVerifyGroupMemberList,
						ApplicationData.currentUser.getKeyID(), "", gNum)
						.getBytes(), GameAPI.Port_Weibo);
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return GameUser.getWeibUserList(array);
	}

	public Group getGroupInfo(String gNum) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_getGroupInfo,
				IOWriter.getBrowseGroupInfo(Protocol.msgType_getGroupInfo,
						ApplicationData.currentUser.getKeyID(), "", gNum)
						.getBytes(), GameAPI.Port_Weibo);
		JSONObject jsonObj = getJSONObjFromByte(data);
		return new Group(jsonObj);
	}

	public List<City> getCityList() throws JSONException {
		return new ArrayList<City>();
//		String request = IOWriter.getBrowseSystemDefins(
//				Protocol.msgType_browseCitys,
//				ApplicationData.currentUser.getKeyID());
//		byte[] data = Connection.getDataSync(Protocol.msgType_browseCitys,
//				request.getBytes(), GameAPI.Port_Role);
//		if (data == null) {
//			return null;
//		}
//		JSONArray array = getJSONArrayFromByte(data);
//		if (array == null) {
//			return null;
//		}
//		return City.getCityList(array);
	}

	public RoleInfo getRoleInfo(String index) throws JSONException {
		String request = IOWriter.getBrowseRoleInfo(
				Protocol.msgType_getRoleInfo,
				ApplicationData.currentUser.getKeyID(), index);
		byte[] data = Connection.getDataSync(Protocol.msgType_getRoleInfo,
				request.getBytes(), GameAPI.Port_Role);
		if (data == null) {
			return null;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return null;
		}
		return new RoleInfo(obj);
	}

	public GameUser getGameUser(String wbId) throws JSONException {
		byte[] data = Connection.getDataSync(
				Protocol.msgType_searchUserInfo,
				IOWriter.getWeiboUser(Protocol.msgType_searchUserInfo,
						ApplicationData.currentUser.getKeyID(), wbId)
						.getBytes(), GameAPI.Port_Weibo);
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return null;
		}
		return new GameUser(obj);
	}

	public int joinGroup(String groupNum) throws JSONException {
		String request = IOWriter.getJoinGroup(Protocol.msgType_joinGroup,
				ApplicationData.currentUser.getKeyID(), "", groupNum,
				"i want to join in you!!please accept me");
		byte[] data = Connection.getDataSync(Protocol.msgType_joinGroup,
				request.getBytes(), GameAPI.Port_Weibo);
		if (data == null) {
			return -1;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return -1;
		}
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		Message msg = Message.obtain(HomeActivity.mInstance.handler);

		if (obj != null && status != null && status.equals("0")) {
			msg.what = MessageHandler.MES_JOINT_GROUP;
			msg.obj = "申请成功发送...";
			HomeActivity.mInstance.handler.sendMessage(msg);
			return 0;
		} else if (status.equals("wb1005")) {
			// 正在审核
			msg.what = MessageHandler.MES_JOINT_GROUP;
			msg.obj = "正在审核...";
			HomeActivity.mInstance.handler.sendMessage(msg);
			return 1;
		} else if (status.equals("wb1040")) {
			// 已经加入
			msg.what = MessageHandler.MES_JOINT_GROUP;
			msg.obj = "已经加入该群";
			HomeActivity.mInstance.handler.sendMessage(msg);
			return 2;
		}
		return -1;
	}

	public List<Status> getGroupStatusList(String gNum, int page, int size)
			throws JSONException {
		String request = IOWriter.getBrowseStatus(Protocol.msgType_browseInfo,
				ApplicationData.currentUser.getKeyID(), "", Status.TYPE_GROUP,
				gNum, page, size);
		byte[] data = Connection.getDataSync(Protocol.msgType_browseInfo,
				request.getBytes(), GameAPI.Port_Weibo);
		if (data == null) {
			return null;
		}
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return Status.getStatusList(array);
	}

	public boolean exitGroup(String groupNum) throws JSONException {
		String request = IOWriter.getExitGroup(Protocol.msgType_exitGroups,
				ApplicationData.currentUser.getKeyID(), "", groupNum);
		byte[] data = Connection.getDataSync(Protocol.msgType_exitGroups,
				request.getBytes(), GameAPI.Port_Weibo);
		if (data == null) {
			return false;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj != null
				&& obj.getString(Protocol.ProtocolKey.KEY_status).equals("0")) {
			return true;
		}
		return false;
	}

	public boolean alterGroup(Group group, String imgName) throws JSONException {
		String request = IOWriter.getAlterGroup(Protocol.msgType_alterGroups,
				ApplicationData.currentUser.getKeyID(), "", group.getgNum(),
				group.getgName(), group.getgExp(), group.getgNot(), "1",
				0 + "", imgName);
		byte[] data = Connection.getDataSync(Protocol.msgType_alterGroups,
				request.getBytes(), GameAPI.Port_Weibo);
		if (data == null) {
			return false;
		}
		JSONObject obj = getJSONObjFromByte(data);
		if (obj != null
				&& obj.getString(Protocol.ProtocolKey.KEY_status).equals("0")) {
			return true;
		}
		return false;
	}

	public List<GameItem> getGameItemListOfClient() throws JSONException {
		String request = IOWriter.getGameItemListOfClient(
				Protocol.msgType_getGameItemListOfClient,
				ApplicationData.currentUser.getKeyID(), 1, 100);
		byte[] data = Connection.getDataSync(
				Protocol.msgType_getGameItemListOfClient, request.getBytes(),
				GameAPI.Port_Role);
		if (data == null) {
			return null;
		}
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return GameItem.getGameItemList(array);
	}

	public List<GameType> getGameTypeList() throws JSONException {
		String request = IOWriter.getGameTypeList(
				Protocol.msgType_getGameTypeList,
				ApplicationData.currentUser.getKeyID());
		byte[] data = Connection.getDataSync(Protocol.msgType_getGameTypeList,
				request.getBytes(), GameAPI.Port_Role);
		if (data == null) {
			return null;
		}
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return GameType.getGameTypeList(array);
	}

	public List<GameItem> getRecommendGameItemList() throws JSONException {
		String request = IOWriter.getGameItemListOfClient(
				Protocol.msgType_getRecommendGameList,
				ApplicationData.currentUser.getKeyID(), 1, 20);
		byte[] data = Connection.getDataSync(
				Protocol.msgType_getRecommendGameList, request.getBytes(),
				GameAPI.Port_Role);
		if (data == null) {
			return null;
		}
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return GameItem.getGameItemList(array);
	}

	public List<GameItem> getGameItemRanking() throws JSONException {
		String request = IOWriter.getGameItemListOfClient(
				Protocol.msgType_getGameItemRanking,
				ApplicationData.currentUser.getKeyID(), 1, 20);
		byte[] data = Connection.getDataSync(
				Protocol.msgType_getGameItemRanking, request.getBytes(),
				GameAPI.Port_Role);
		if (data == null) {
			return null;
		}
		JSONArray array = getJSONArrayFromByte(data);
		if (array == null) {
			return null;
		}
		return GameItem.getGameItemList(array);
	}

	public boolean postNativeApplications(List<ListItem> gameList)
			throws JSONException {
		String request = IOWriter.postGameList(
				Protocol.msgType_postGameItemListOfClient,
				ApplicationData.currentUser.getKeyID(), gameList);
		byte[] data = Connection.getDataSync(
				Protocol.msgType_postGameItemListOfClient, request.getBytes(),
				GameAPI.Port_Role);
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return false;
		}
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		if ("0".equals(status)) {
			return true;
		}
		return false;
	}

	public boolean isGroupNameRepeat(String name) throws JSONException {
		String request = IOWriter.getCheckGroupNameRepeat(
				Protocol.msgType_groupRepeat,
				ApplicationData.currentUser.getKeyID(), "", name);
		byte[] data = Connection.getDataSync(Protocol.msgType_groupRepeat,
				request.getBytes(), GameAPI.Port_Weibo);
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return true;
		}
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		if ("0".equals(status)) {
			return false;
		}
		return true;
	}

	public boolean isRoleNameRepeat(String name) throws JSONException {
		String request = IOWriter.getCheckRoleNameRepeat(
				Protocol.msgType_nickNameRepeat,
				ApplicationData.currentUser.getKeyID(), GameAPI.userApp,
				GameAPI.channel, name);
		byte[] data = Connection.getDataSync(Protocol.msgType_nickNameRepeat,
				request.getBytes(), GameAPI.Port_Role);
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return false;
		}
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		if ("0".equals(status)) {
			return true;
		}
		return false;
	}

	public List<Email> getEmailList(int type, String dtime)
			throws JSONException {
		return new ArrayList<Email>();
//		String request = IOWriter.getEmails(Protocol.msgTYpe_getEmailList,
//				ApplicationData.currentUser.getKeyID(), type, dtime);
//		byte[] data = Connection.getDataSync(Protocol.msgTYpe_getEmailList,
//				request.getBytes(), GameAPI.Port_Role);
//		if (data == null) {
//			return null;
//		}
//		JSONArray array = getJSONArrayFromByte(data);
//		if (array == null) {
//			return null;
//		}
//		return Email.getMessageList(array);
	}

	public List<Request> getRequestListOfGroup() throws JSONException {
		return new ArrayList<Request>();
//		String request = IOWriter.getGroupRequest(
//				Protocol.msgType_getRequestListOfGroup,
//				ApplicationData.currentUser.getKeyID(), "", "");
//		byte[] data = Connection.getDataSync(
//				Protocol.msgType_getRequestListOfGroup, request.getBytes(),
//				GameAPI.Port_Weibo);
//		if (data == null) {
//			return null;
//		}
//		JSONArray array = getJSONArrayFromByte(data);
//		if (array == null) {
//			return null;
//		}
//		return Request.getRequestList(array, Request.REQUEST_TYPE_GROUP);
	}

	public List<Request> getRequestListOfFriend() throws JSONException {
		return new ArrayList<Request>();
//		String request = IOWriter.getFriendRequest(
//				Protocol.msgType_getRequestListOfFriend,
//				ApplicationData.currentUser.getKeyID());
//		byte[] data = Connection.getDataSync(
//				Protocol.msgType_getRequestListOfFriend, request.getBytes(),
//				GameAPI.Port_Role);
//		if (data == null) {
//			return null;
//		}
//		JSONArray array = getJSONArrayFromByte(data);
//		if (array == null) {
//			return null;
//		}
//		return Request.getRequestList(array, Request.REQUEST_TYPE_FRIEND);
	}

	public List<Mail> getMailList() throws JSONException {
		return new ArrayList<Mail>();
//		String request = IOWriter.getSysMsgs(Protocol.msgTYpe_getSysMsgList,
//				ApplicationData.currentUser.getKeyID(), "");
//		byte[] data = Connection.getDataSync(Protocol.msgTYpe_getSysMsgList,
//				request.getBytes(), GameAPI.Port_Weibo);
//		if (data == null) {
//			return null;
//		}
//		JSONArray array = getJSONArrayFromByte(data);
//		if (array == null) {
//			return null;
//		}
//		return Mail.getMailList(array);
	}

	public boolean sendMessage(String roleId, String content)
			throws JSONException {
		String request = IOWriter.getSendMsg(Protocol.msgType_sendMsg,
				ApplicationData.currentUser.getKeyID(), "", roleId, content);
		byte[] data = Connection.getDataSync(Protocol.msgType_sendMsg,
				request.getBytes(), GameAPI.Port_Weibo);
		JSONObject obj = getJSONObjFromByte(data);
		if (obj == null) {
			return false;
		}
		String status = obj.getString(Protocol.ProtocolKey.KEY_status);
		if ("0".equals(status)) {
			return true;
		}
		return false;
	}

	private JSONArray getJSONArrayFromByte(byte[] data) throws JSONException {
		if (data == null) {
			return null;
		}
		String json = new String(data);

		Log.v("IO", json);

		JSONTokener jsonParser = new JSONTokener(json);

		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();

		JSONObject response = jsonObj
				.getJSONObject(Protocol.ProtocolKey.KEY_RESPONSE);

		JSONArray queue = response.getJSONArray(Protocol.ProtocolKey.KEY_queue);

		for (int i = 0; i < queue.length(); i++) {
			JSONObject jsonType = queue.getJSONObject(0);

			String msgType = jsonType
					.getString(Protocol.ProtocolKey.KEY_msgType);
			String status = jsonType.getString(Protocol.ProtocolKey.KEY_status);
			if (!jsonType.isNull(Protocol.ProtocolKey.KEY_count)) {
				String count = jsonType
						.getString(Protocol.ProtocolKey.KEY_count);
			}

			if (status.equals("0")) {
				return jsonType.getJSONArray(Protocol.ProtocolKey.KEY_list);
			}
			return null;
		}
		return null;
	}

	private JSONArray parseDataOfRoleShow(String json) throws JSONException {
		Log.v("IO", json);

		JSONTokener jsonParser = new JSONTokener(json);

		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();

		return jsonObj.getJSONArray(Protocol.ProtocolKey.KEY_list);
	}

	private JSONObject getJSONObjFromByte(byte[] data) throws JSONException {
		if (data == null) {
			return null;
		}
		String json = new String(data);

		Log.v("IO", json);
		JSONTokener jsonParser = null;
		try {
			jsonParser = new JSONTokener(json);
		} catch (Exception e) {
			Log.e("error", e.getMessage());
			return null;
		}

		JSONObject jsonObj = (JSONObject) jsonParser.nextValue();

		JSONObject response = jsonObj
				.getJSONObject(Protocol.ProtocolKey.KEY_RESPONSE);

		JSONArray queue = response.getJSONArray(Protocol.ProtocolKey.KEY_queue);

		for (int i = 0; i < queue.length(); i++) {
			JSONObject jsonType = queue.getJSONObject(0);

			String msgType = jsonType
					.getString(Protocol.ProtocolKey.KEY_msgType);
			String status = jsonType.getString(Protocol.ProtocolKey.KEY_status);
			if (!jsonType.isNull(Protocol.ProtocolKey.KEY_count)) {
				String count = jsonType
						.getString(Protocol.ProtocolKey.KEY_count);
			}
			if (status.equals("0")) {
				return jsonType;
			} else if (status.equals("1040")) {
				return jsonType;
			} else if (status.equals("wb1005")) {
				return jsonType;
			}/*
			 * else if(status.equals("wb1005")){ return jsonType; }
			 */
			return null;
		}
		return null;
	}

	private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();

	public static Date parseDate(String str, String format) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			formatMap.put(format, sdf);
		}
		try {
			synchronized (sdf) {
				// SimpleDateFormat is not thread safe
				return sdf.parse(str);
			}
		} catch (ParseException e) {
			Log.e("error", e.getMessage());
		}
		return null;
	}

}
