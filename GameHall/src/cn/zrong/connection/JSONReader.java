package cn.zrong.connection;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import json.JSONTokener;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.activity.WelActivity;
import cn.zrong.entity.Group;
import cn.zrong.entity.RoleInfo;
import cn.zrong.entity.RoleInfo.Gender;
import cn.zrong.entity.User;
import cn.zrong.handler.MessageHandler;

public class JSONReader extends IOReader {
	public static final String TAG = "JSONReader";
	private MessageHandler handler;

	@Override
	public void recieve(byte[] data) {
		// TODO Auto-generated method stub
		if (data == null)
			return;
		String json = new String(data);

		Log.v("IO", json);

		JSONTokener jsonParser = new JSONTokener(json);

		try {
			JSONObject jsonObj = (JSONObject) jsonParser.nextValue();

			JSONObject response = jsonObj
					.getJSONObject(Protocol.ProtocolKey.KEY_RESPONSE);

			JSONArray queue = response
					.getJSONArray(Protocol.ProtocolKey.KEY_queue);

			for (int i = 0; i < queue.length(); i++) {
				JSONObject jsonType = queue.getJSONObject(0);

				String msgType = jsonType
						.getString(Protocol.ProtocolKey.KEY_msgType);

				parse(msgType, jsonType);
			}
			queue.length();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void parse(String msgType, JSONObject jsonObj) throws JSONException {
		if (msgType == null) {
			return;
		}
		if (handler == null) {
			handler = MessageHandler.sharedHandler();
		}
		short type = (short) Integer.parseInt(msgType);
		switch (type) {
		case Protocol.msgType_fastRegist:
			parseFastRegist(jsonObj);
			break;
		case Protocol.msgType_regist:
			parseRegist(jsonObj);
			break;
		case Protocol.msgType_login:
			parseLogin(jsonObj);
			break;
		case Protocol.msgType_createRole:
			parseCreateRole(jsonObj);
			break;
		case Protocol.msgType_loginRole:
			parseRole(jsonObj);
			break;
		case Protocol.msgType_alertRole:
			parseAlertRole(jsonObj);
			break;
		case Protocol.msgType_alertWeiboUser:
			parseAlertWeiboUser(jsonObj);
			break;
		case Protocol.msgType_listRole:
			parseListRole(jsonObj);
			break;
		case Protocol.msgType_createGroups:
			parseCreateGroup(jsonObj);
			break;
		case Protocol.msgType_joinGroup:
			parseJointGroup(jsonObj);
			break;
		case Protocol.msgType_changeAccountAndPsd:
			parseChangeAccountAndPwd(jsonObj);
			break;
		case Protocol.msgType_setPsd:
			parseSetPwd(jsonObj);
			break;
		case Protocol.msgType_sendMsg:
			parseSendMsg(jsonObj);
			break;
		case Protocol.msgType_addAttention:
			parseAddAttention(jsonObj);
			break;
		case Protocol.msgType_sendWeibo:
			parseSendWeibo(jsonObj);
			break;
		case Protocol.msgType_commentWeibo:
			parseSendWeibo(jsonObj);
			break;
		case Protocol.msgType_cancelAttention:
			parseCancelAttention(jsonObj);
			break;
		case Protocol.msgType_saveRoleShow:
			parseRoleShow(jsonObj);
			break;
		case Protocol.msgType_removeGroup:
			parseRemoveGroup(jsonObj);
			break;


		}
	}
	
	public void parseRemoveGroup(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_removeGroup;
			msg.arg1 = 200;
			handler.sendMessage(msg);
		} else {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_removeGroup;
			msg.arg1 =-1;
			handler.sendMessage(msg);
			handlerErrorRoleCode(stat);
		}
	}

	public void parseAlertRole(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_UPDATE_ROLE;
			handler.sendMessage(msg);
		} else {
			handlerErrorRoleCode(stat);
		}
	}

	private void parseRoleShow(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {

		} else {
			handlerErrorResponseCode(stat);
		}
	}

	private void parseCancelAttention(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {

		} else {
			handlerErrorResponseCode(stat);
		}

	}

	private void parseSendWeibo(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_sendWeibo_Success;
			handler.sendMessage(msg);
		} else {
			handlerErrorResponseCode(stat);
		}
	}

	private void parseAddAttention(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {

		} else {
			handlerErrorResponseCode(stat);
		}
	}

	private void parseSendMsg(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {

		} else {
			handlerErrorResponseCode(stat);
		}
	}

	private void parseRole(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {
			ApplicationData.currentUser.setUserInfo(new RoleInfo(jsonObj));
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_LOGIN_ROLE;
			handler.sendMessage(msg);
		} else {
			handlerErrorResponseCode(stat);
		}
	}

	public void parseFastRegist(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {
			String keyID = jsonObj.getString(Protocol.ProtocolKey.KEY_keyID);
			String name = jsonObj.getString(Protocol.ProtocolKey.KEY_Name);
			String psd = jsonObj.getString(Protocol.ProtocolKey.KEY_Pwd);
			int hb = jsonObj.getInt(Protocol.ProtocolKey.KEY_HB);
			ApplicationData.currentUser = new User(keyID, name, psd);
			Message msg = Message.obtain(handler);
			msg.obj = ApplicationData.currentUser;
			msg.what = MessageHandler.MES_INSERT_USER;
			handler.sendMessage(msg);
			Message msg2 = Message.obtain(handler);
			msg2.what = MessageHandler.MES_FastRegist_SUCCESS;
			handler.sendMessage(msg2);
		} else {
			handlerErrorResponseCode(stat);
		}

	}

	public void parseRegist(JSONObject jsonObj) {

	}

	int count = 0;

	public void parseLogin(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(Protocol.response_codes,
				status);
		if (status.equals("0")) {
			String keyID = jsonObj.getString(Protocol.ProtocolKey.KEY_keyID);
			int hb = jsonObj.getInt(Protocol.ProtocolKey.KEY_HB);
			boolean bool = ApplicationData.currentUser.getKeyID().equals(keyID);
			Log.i("Log2", "登录-key值" + bool);
			if (!bool) {
				ApplicationData.currentUser.setKeyID(keyID);
				if (ApplicationData.temporaryAccountName != null) {
					ApplicationData.currentUser
							.setU_name(ApplicationData.temporaryAccountName);
					ApplicationData.currentUser
							.setU_psd(ApplicationData.temporaryPwd);
				}
			}
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_LOGIN_SUCCESS;
			handler.sendMessage(msg);
		} else {
			if (WelActivity.mIntance != null) {
				if (count++ <= 2) {
					WelActivity.mIntance.loginOrRegist();
				} else {
					Toast.makeText(WelActivity.mIntance, "登录失败",
							Toast.LENGTH_SHORT).show();
				}

			}
			handlerErrorResponseCode(stat);
		}
	}

	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException
	 */
	public void parseCreateRole(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			ApplicationData.currentUser.setUserInfo(new RoleInfo(
					ApplicationData.currentUser.getU_name(), Gender.男));
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_CREATE_ROLE;
			handler.sendMessage(msg);
		} else {
			handlerErrorRoleCode(stat);
		}
	}

	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException
	 */
	public void parseAlertWeiboUser(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_UPDATE_WEIBOUSER;
			handler.sendMessage(msg);
		} else {
			handlerErrorRoleCode(stat);
		}
	}

	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException
	 */
	public void parseListRole(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {

		} else {
			handlerErrorRoleCode(stat);
		}
	}

	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException
	 */
	public void parseCreateGroup(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Group g = new Group();
			if (jsonObj.isNull(Protocol.ProtocolKey.KEY_gNum))
				g.setgNum(jsonObj.getString(Protocol.ProtocolKey.KEY_gNum));
			if (jsonObj.isNull(Protocol.ProtocolKey.KEY_gNum))
				g.setgName(jsonObj.getString(Protocol.ProtocolKey.KEY_gName));
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_CREATE_GROUP;
			handler.sendMessage(msg);

		} else {
			handlerErrorRoleCode(stat);
		}
	}

	public void parseJointGroup(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_JOINT_GROUP;
			handler.sendMessage(msg);
		} else {
			handlerErrorRoleCode(stat);
		}
	}

	public void parseChangeAccountAndPwd(JSONObject jsonObj)
			throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_CHANGE_AccountAndPwd;
			handler.sendMessage(msg);
		} else {
			handlerErrorRoleCode(stat);
		}
	}

	public void parseSetPwd(JSONObject jsonObj) throws JSONException {
		String status = jsonObj.getString(Protocol.ProtocolKey.KEY_status);
		int stat = ProtocolUtil.getResponseStatus(
				Protocol.operation_codes_role, status);
		if (status.equals("0")) {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_SET_Pwd;
			msg.arg1 = 0;
			handler.sendMessage(msg);
		} else {
			Message msg = Message.obtain(handler);
			msg.what = MessageHandler.MES_SET_Pwd;
			msg.arg1 = 1;
			handler.sendMessage(msg);
			handlerErrorRoleCode(stat);
		}
	}

	/**
	 * 处理错误
	 * 
	 * @param stat
	 */
	private void handlerErrorResponseCode(int stat) {
		switch (stat) {
		case Protocol.RESPONSE_CODE_EMPTY:
			break;
		case Protocol.RESPONSE_CODE_NULLKEY:
			break;
		case Protocol.RESPONSE_CODE_USER_INEXISTENCE:
			break;
		case Protocol.RESPONSE_CODE_USER_HAVEEXISTENCE:
			break;
		case Protocol.RESPONSE_CODE_INPUT_ERROR:
			break;
		case Protocol.RESPONSE_CODE_HAVE_LOGIN:
			break;
		case Protocol.RESPONSE_CODE_PHONENUM_ERROR_FORM:
			break;
		case Protocol.RESPONSE_CODE_ORIGINAL_PHONE_ERROR:
			break;
		case Protocol.RESPONSE_CODE_USER_SHIELDED:
			break;
		case Protocol.RESPONSE_CODE_ORIGINAL_PASSWORD_ERROR:
			break;
		case Protocol.RESPONSE_CODE_PRODUCT_INFO_ERROR:
			break;
		case Protocol.RESPONSE_CODE_CHANNEL_INEXISTENCE:
			break;
		case Protocol.RESPONSE_CODE_PRODUCT_ID_INEXISTENCE:
			break;
		case Protocol.RESPONSE_CODE_MOBILE_INFO_ERROR:
			break;
		case Protocol.RESPONSE_CODE_NULL_MOBILE_INFO:
			break;
		case Protocol.RESPONSE_CODE_ILLEGAL_OPERATION:

			break;
		case Protocol.RESPONSE_CODE_SYSTEM_ERROR:
			break;
		}
	}

	private void handlerErrorRoleCode(int stat) {
		switch (stat) {
		case Protocol.OPERATION_CODES_ROLE_NAME_EXISTENCE:
			break;
		case Protocol.OPERATION_CODES_ROLE_NAME_NULL:
			break;
		case Protocol.OPERATION_CODES_ROLE_INDEX_NULL:
			break;
		case Protocol.OPERATION_CODES_ROLE_INEXISTENCE:
			break;
		case Protocol.OPERATION_CODES_ROLE_INFO_NULL:
			break;
		}
	}

}
