package cn.zrong.connection;

import java.io.File;
import java.util.Date;
import java.util.List;

import json.JSONException;
import json.JSONStringer;
import android.app.LauncherActivity.ListItem;
import cn.zrong.entity.RoleInfo;
import cn.zrong.entity.RoleInfo.Gender;

/**
 * 
 * <p>
 * Titile:IOConstruct
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author yz
 * @version 1.0
 * @date 2012-5-4
 */
public class IOWriter {

	/**
	 * 
	 * <p>
	 * Description:json��ʽ������� ����
	 * </p>
	 * 
	 * @author yz
	 * @param userApp
	 * @param channel
	 * @param platform
	 * @param mac
	 *            手机唯一编码
	 * @param iMobile
	 * @return
	 */
	public static String getFastRegist(short msgType, String userApp,
			String channel, String platform, String mac, String iMobile) {
		try {
			JSONStringer jsonText = new JSONStringer();

			jsonText.object();

			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);

			jsonText.object();

			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));

			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);

			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);

			jsonText.key(Protocol.ProtocolKey.KEY_platform);
			jsonText.value(platform);

			jsonText.key(Protocol.ProtocolKey.KEY_mac);
			jsonText.value(mac);

			jsonText.key(Protocol.ProtocolKey.KEY_iMobile);
			jsonText.value(iMobile);

			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 *            处理标识
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param userApp
	 *            产品代号
	 * @param channel
	 *            渠道号码
	 * @param platform
	 *            客户端类型
	 * @param mac
	 *            手机唯一编码
	 * @param iMobile
	 *            移动类型
	 * @return
	 */
	public static String getRegist(short msgType, String userName,
			String password, String userApp, String channel, String platform,
			String mac, String iMobile) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(String.valueOf(userName));
			jsonText.key(Protocol.ProtocolKey.KEY_Pwd);
			jsonText.value(String.valueOf(password));
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);
			jsonText.key(Protocol.ProtocolKey.KEY_platform);
			jsonText.value(platform);
			jsonText.key(Protocol.ProtocolKey.KEY_mac);
			jsonText.value(mac);
			jsonText.key(Protocol.ProtocolKey.KEY_iMobile);
			jsonText.value(iMobile);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 *            处理标识
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param userApp
	 *            产品代号
	 * @param channel
	 *            渠道号码
	 * @param platform
	 *            客户端类型
	 * @param mac
	 *            设备唯一标识
	 * @param fastReg
	 *            是否开启快速注册
	 * @param iMobile
	 *            移动类型
	 * 
	 * @return
	 */
	public static String getLogin(short msgType, String userName,
			String password, String userApp, String channel, String platform,
			String mac, String fastReg, String iMobile) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(String.valueOf(userName));
			jsonText.key(Protocol.ProtocolKey.KEY_Pwd);
			jsonText.value(String.valueOf(password));
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);
			jsonText.key(Protocol.ProtocolKey.KEY_platform);
			jsonText.value(platform);
			jsonText.key(Protocol.ProtocolKey.KEY_mac);
			jsonText.value(mac);
			jsonText.key(Protocol.ProtocolKey.KEY_fastReg);
			jsonText.value(fastReg);
			jsonText.key("iMobile");
			jsonText.value(iMobile);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getRoleCreate(short msgType, String keyID,
			String userApp, String channel, String platform, String name,
			String sex) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);
			jsonText.key(Protocol.ProtocolKey.KEY_platform);
			jsonText.value(platform);
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(name);
			jsonText.key(Protocol.ProtocolKey.KEY_sex);
			jsonText.value(sex);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAlterRole(short msgType, String keyID,
			RoleInfo userInfo) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(userInfo.index);
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(userInfo.nickName);
			jsonText.key(Protocol.ProtocolKey.KEY_sex);
			jsonText.value(userInfo.gender.equals(Gender.男) ? 0 : 1 + "");
			jsonText.key(Protocol.ProtocolKey.KEY_city);
			jsonText.value(userInfo.city.index);
			jsonText.key(Protocol.ProtocolKey.KEY_intro);
			jsonText.value(userInfo.intro);
			jsonText.key(Protocol.ProtocolKey.KEY_label);
			jsonText.value("");
			jsonText.key(Protocol.ProtocolKey.KEY_sys);
			jsonText.value(userInfo.userSysPortrait);
			jsonText.key(Protocol.ProtocolKey.KEY_def);
			jsonText.value(userInfo.selfDefPortrait);
			jsonText.key(Protocol.ProtocolKey.KEY_use);
			jsonText.value(userInfo.usedPortrait);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSendWeibo(short msgType, String keyID, String wbID,
			String content, String type, String groupNum, String contentId,
			String img) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_content);
			jsonText.value(content);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(type);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(groupNum);
			jsonText.key(Protocol.ProtocolKey.KEY_contID);
			jsonText.value(contentId);
			jsonText.key("img");
			jsonText.value(img);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCommentWeibo(short msgType, String keyID,
			String wbID, String contID, String content) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_contID);
			jsonText.value(contID);
			jsonText.key(Protocol.ProtocolKey.KEY_content);
			jsonText.value(content);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCreateUserInfo(short msgType, String keyID,
			String userApp, String channel, String platform, String name,
			String sex) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);
			jsonText.key(Protocol.ProtocolKey.KEY_platform);
			jsonText.value(platform);
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(name);
			jsonText.key(Protocol.ProtocolKey.KEY_sex);
			jsonText.value(sex);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSearchHuati(short msgType, String keyID,
			String wbID, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSearchFriends(short msgType, String keyID,
			String userApp, String key, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_key);
			jsonText.value(key);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFriendsList(short msgType, String keyID,
			String type, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(type);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHallGames(short msgType, String keyID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getVerifyFriendsList(short msgType, String keyID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAddFriend(short msgType, String keyID, String roleId) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_roleId);
			jsonText.value(roleId);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAuthFriend(short msgType, String keyID,
			String index, int action) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(index);
			jsonText.key(Protocol.ProtocolKey.KEY_action);
			jsonText.value(String.valueOf(action));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAuthGroup(short msgType, String keyID, String wbId,
			String gNum, String index, int action) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbId);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(index);
			jsonText.key(Protocol.ProtocolKey.KEY_exec);
			jsonText.value(String.valueOf(action));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseStatus(short msgType, String keyID,
			String wbID, int type, String indexId, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(String.valueOf(type));
			jsonText.key(Protocol.ProtocolKey.KEY_indexID);
			jsonText.value(indexId);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseComments(short msgType, String keyID,
			String wbID, String contentId, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_contID);
			jsonText.value(contentId);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseGroupList(short msgType, String keyID,
			String wbID, String key, int type) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_key);
			jsonText.value(key);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(String.valueOf(type));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseVerifyGroupMemberList(short msgType,
			String keyID, String wbID, String gNum) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAlertGroup(short msgType, String keyID,
			String wbID, String gNum, String gName, String gExp, String gAuth,
			String gCap) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.key(Protocol.ProtocolKey.KEY_gName);
			jsonText.value(gName);
			jsonText.key(Protocol.ProtocolKey.KEY_gExp);
			jsonText.value(gExp);
			jsonText.key(Protocol.ProtocolKey.KEY_gAuth);
			jsonText.value(gAuth);
			jsonText.key(Protocol.ProtocolKey.KEY_gCap);
			jsonText.value(gCap);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseGroupInfo(short msgType, String keyID,
			String wbID, String gNum) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCreateGroup(short msgType, String keyID,
			String wbID, String groupName, String groupIntro,
			String groupNotice, String auth, String upperLimit,
			String addmembers, String gImg) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gName);
			jsonText.value(groupName);
			jsonText.key(Protocol.ProtocolKey.KEY_gExp);
			jsonText.value(groupIntro);
			jsonText.key(Protocol.ProtocolKey.KEY_gNot);
			jsonText.value(groupNotice);
			jsonText.key(Protocol.ProtocolKey.KEY_gAuth);
			jsonText.value(auth);
			jsonText.key(Protocol.ProtocolKey.KEY_gCap);
			jsonText.value(upperLimit);
			jsonText.key(Protocol.ProtocolKey.KEY_gMem);
			jsonText.value(addmembers);
			jsonText.key(Protocol.ProtocolKey.KEY_gImg);
			jsonText.value(gImg);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getJoinGroup(short msgType, String keyID, String wbID,
			String gNum, String gMsg) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.key(Protocol.ProtocolKey.KEY_gMsg);
			jsonText.value(gMsg);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getExitGroup(short msgType, String keyID, String wbID,
			String gNum) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAlterGroup(short msgType, String keyID,
			String wbID, String gNum, String gName, String gExp, String gNot,
			String gAuth, String gCap, String gImg) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.key(Protocol.ProtocolKey.KEY_gName);
			jsonText.value(gName);
			jsonText.key(Protocol.ProtocolKey.KEY_gExp);
			jsonText.value(gExp);
			jsonText.key(Protocol.ProtocolKey.KEY_gNot);
			jsonText.value(gNot);
			jsonText.key(Protocol.ProtocolKey.KEY_gAuth);
			jsonText.value(gAuth);
			jsonText.key(Protocol.ProtocolKey.KEY_gCap);
			jsonText.value(gCap);
			jsonText.key(Protocol.ProtocolKey.KEY_gImg);
			jsonText.value(gImg);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseGroupMembers(short msgType, String keyID,
			String wbID, String gNum) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseGroupMsgs(short msgType, String keyID,
			String wbID, String gNum, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGroupStatus(short msgType, String keyID,
			String wbID, String groupNum, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(groupNum);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSetAccountAndPsdRequest(short msgType,
			String keyID, String account, String psd) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(account);
			jsonText.key(Protocol.ProtocolKey.KEY_Pwd);
			jsonText.value(psd);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSetPsdRequest(short msgType, String keyID,
			String psd, String newpsd) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_Pwd);
			jsonText.value(psd);
			jsonText.key(Protocol.ProtocolKey.KEY_Npwd);
			jsonText.value(newpsd);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBrowseSystemDefins(short msgType, String keyID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 * @param keyID
	 * @param index
	 *            角色索引信息
	 * @return
	 */
	public static String getBrowseRoleInfo(short msgType, String keyID,
			String index) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(index);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 * @param keyID
	 * @param wbID
	 *            微博用户id
	 * @return
	 */
	public static String getWeiboUser(short msgType, String keyID, String wbID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 * @param keyID
	 * @param wbID
	 * @param attentionWbID
	 *            关注用户ID
	 * @param type
	 *            关注分类
	 * @return
	 */
	public static String getAddAttention(short msgType, String keyID,
			String wbID, String attentionWbID, String type) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_attentionID);
			jsonText.value(attentionWbID);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(type);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCancelAttention(short msgType, String keyID,
			String wbID, String attentionWbID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(attentionWbID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param msgType
	 * @param keyID
	 * @param id
	 *            微博用户id
	 * @return
	 */
	public static String getSearchUser(short msgType, String keyID, String key,
			int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_key);
			jsonText.value(key);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSearchWeibos(short msgType, String keyID,
			String wbID, String key, int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_key);
			jsonText.value(key);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getEmails(short msgType, String keyID, int type,
			String dtime) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_type);
			jsonText.value(String.valueOf(type));
			jsonText.key(Protocol.ProtocolKey.KEY_dtime);
			jsonText.value(dtime);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSysMsgs(short msgType, String keyID, String wbID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFriendRequest(short msgType, String keyID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGroupRequest(short msgType, String keyID,
			String wbID, String gNum) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gNum);
			jsonText.value(gNum);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGameItemListOfClient(short msgType, String keyID,
			int page, int size) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_page);
			jsonText.value(String.valueOf(page));
			jsonText.key(Protocol.ProtocolKey.KEY_size);
			jsonText.value(String.valueOf(size));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSendMsg(short msgType, String keyID, String wbID,
			String recID, String cont) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_recID);
			jsonText.value(recID);
			jsonText.key(Protocol.ProtocolKey.KEY_cont);
			jsonText.value(cont);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGetUserByLabel(short msgType, String keyID,
			String index, int level) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_INDEX);
			jsonText.value(index);
			jsonText.key(Protocol.ProtocolKey.KEY_level);
			jsonText.value(String.valueOf(level));
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSendFeedBack(short msgType, String keyID, String msg) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_msg);
			jsonText.value(msg);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getGameTypeList(short msgType, String keyID) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postGameList(short msgType, String keyID,
			List<ListItem> list) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_list);
			jsonText.array();

			for (int i = 0, len = list.size(); i < len; i++) {
				jsonText.object();
				jsonText.key(Protocol.ProtocolKey.KEY_gName);
				jsonText.value(list.get(i).label);
				jsonText.key(Protocol.ProtocolKey.KEY_gPack);
				jsonText.value(list.get(i).packageName);
				jsonText.key(Protocol.ProtocolKey.KEY_gTime);
				File file = new File(
						list.get(i).resolveInfo.activityInfo.applicationInfo.sourceDir);
				long time = file.lastModified();
				Date d = new Date(time);
				jsonText.value(d.toLocaleString());
				jsonText.endObject();
			}
			jsonText.endArray();
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCheckGroupNameRepeat(short msgType, String keyID,
			String wbID, String name) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_wbID);
			jsonText.value(wbID);
			jsonText.key(Protocol.ProtocolKey.KEY_gName);
			jsonText.value(name);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCheckRoleNameRepeat(short msgType, String keyID,
			String userApp, String channel, String name) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_userApp);
			jsonText.value(userApp);
			jsonText.key(Protocol.ProtocolKey.KEY_channel);
			jsonText.value(channel);
			jsonText.key(Protocol.ProtocolKey.KEY_Name);
			jsonText.value(name);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getRemoveGroup(short msgType, String keyID, String gId) {
		try {
			JSONStringer jsonText = new JSONStringer();
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_REQUEST);
			jsonText.object();
			jsonText.key(Protocol.ProtocolKey.KEY_msgType);
			jsonText.value(String.valueOf(msgType));
			jsonText.key(Protocol.ProtocolKey.KEY_keyID);
			jsonText.value(keyID);
			jsonText.key(Protocol.ProtocolKey.KEY_gId);
			jsonText.value(gId);
			jsonText.endObject();
			jsonText.endObject();
			return jsonText.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
