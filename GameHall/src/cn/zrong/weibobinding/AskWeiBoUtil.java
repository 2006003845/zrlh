package cn.zrong.weibobinding;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1;
import com.tencent.weibo.oauthv1.OAuthV1Client;
import com.tencent.weibo.webview.OAuthV1AuthorizeWebView;

public class AskWeiBoUtil {
	public static String strFormat = "json";
	public OAuthV1 oAuth;
	public Context context;

	private static AskWeiBoUtil mInstance;

	public static AskWeiBoUtil createInstance(Context context) {
		if (mInstance == null) {
			mInstance = new AskWeiBoUtil(context);
		}
		return mInstance;
	}

	private AskWeiBoUtil(Context context) {
		this.context = context;
		getIp();
	}

	public void initAskqqWeibo() {
		oAuth = new OAuthV1("null");
		oAuth.setOauthConsumerKey(Constants.CONSUMER_KEY);
		oAuth.setOauthConsumerSecret(Constants.CONSUMER_SECRET);
		oAuth.setOpenid("");

	}

	public void setOAuth(String key, String secret, String consumerKey,
			String consumerSecret, String oauthNonce, String oauthTimestamp,
			String oauthVerifier, String oauthVersion) {
		oAuth = new OAuthV1("null");
		oAuth.setOauthConsumerKey(consumerKey);
		oAuth.setOauthConsumerSecret(consumerSecret);
		oAuth.setOauthNonce(oauthNonce);
		oAuth.setOauthTimestamp(oauthTimestamp);
		oAuth.setOauthToken(key);
		oAuth.setOauthTokenSecret(secret);
		oAuth.setOauthVerifier(oauthVerifier);
		oAuth.setOauthVersion(oauthVersion);
	}

	public boolean accessUnauthorizedTokenOfqq() {
		boolean bool = false;
		try {
			Log.i("ftftft", "qian_token=" + oAuth.getOauthToken() + ";");
			oAuth = OAuthV1Client.requestToken(oAuth);
			Log.i("ftftft", "ask_Token_after:" + oAuth.getOauthToken() + ";");
			bool = true;
		} catch (Exception e) {
			bool = false;
			return bool;
		}
		return bool;
	}

	public void accessUnauthorizedTokeyCallBack(Activity activity) {
		Intent intent = new Intent(activity, OAuthV1AuthorizeWebView.class);
		intent.putExtra("oauth", oAuth);
		activity.startActivityForResult(intent, 1);
	}

	public void accessAuthorizedTokenOfqq(Intent data) {
		oAuth = (OAuthV1) data.getExtras().getSerializable("oauth");
		Log.i("Loh2", "AppFrom--" + oAuth.getAppFrom());
		Log.i("Loh2", "ClientIP--" + oAuth.getClientIP());
		Log.i("Loh2", "Msg--" + oAuth.getMsg());
		Log.i("Loh2", "OauthCallback--" + oAuth.getOauthCallback());
		Log.i("Loh2", "OauthConsumerKey--" + oAuth.getOauthConsumerKey());
		Log.i("Loh2", "OauthConsumerSecret--" + oAuth.getOauthConsumerSecret());
		Log.i("Loh2", "OauthNonce--" + oAuth.getOauthNonce());
		Log.i("Loh2",
				"OauthSignatureMethod--" + oAuth.getOauthSignatureMethod());
		Log.i("Loh2", "OauthTimestamp--" + oAuth.getOauthTimestamp());
		Log.i("Loh2", "OauthToken()--" + oAuth.getOauthToken());
		Log.i("Loh2", "OauthTokenSecret--" + oAuth.getOauthTokenSecret());
		Log.i("Loh2", "OauthVerifier--" + oAuth.getOauthVerifier());
		Log.i("Loh2", "OauthVersion--" + oAuth.getOauthVersion());
		Log.i("Loh2", "Openid--" + oAuth.getOpenid());
		Log.i("Loh2", "Openkey--" + oAuth.getOpenkey());
		Log.i("Loh2", "Scope--" + oAuth.getScope());
		Log.i("Loh2", "SeqId--" + oAuth.getSeqId());
		Log.i("Loh2", "Status--" + oAuth.getStatus());
		int i = 0;
		for (NameValuePair nv : oAuth.getAccessParams()) {
			Log.i("Loh2", "getAccessParam--" + (i++) + nv.getValue());
		}
		int j = 0;
		for (NameValuePair nv : oAuth.getTokenParamsList()) {
			Log.i("Loh2", "TokenParam--" + (j++) + nv.getValue());
		}
		try {
			oAuth = OAuthV1Client.accessToken(oAuth);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Log.i("ftftft", "access_token_after" + oAuth.getOauthTokenSecret());
	}

	public String accessUseInfoOfqq() {
		String response = "";
		UserAPI userAPI = new UserAPI(OAuthConstants.OAUTH_VERSION_1);

		try {
			response = userAPI.info(oAuth, "json");//
			// ((TextView)findViewById(R.id.textView)).setText(response+"\n");
			Log.i("ftftft", "response=" + response);
			userAPI.shutdownConnection();
		} catch (Exception e) {
			userAPI.shutdownConnection();
		}

		userAPI = null;
		return response;
	}

	public QQResult sendQQMessage(String strContent) {

		TAPI tSend = new TAPI(OAuthConstants.OAUTH_VERSION_1);
		String strReturn = "";
		QQResult result = null;
		try {
			Log.i("Loh",
					"openId-" + oAuth.getOpenid() + "----openKey-"
							+ oAuth.getOpenkey());
			strReturn = tSend.add(oAuth, strFormat, strContent, Ip);
			Log.i("Loh", strReturn);
			result = JsonUtils.parseQQResultJson(strReturn);
			tSend.shutdownConnection();
		} catch (Exception e) {
			tSend.shutdownConnection();
		}
		return result;
	}

	// static String picPath = "/png/icon.png";
	public QQResult sendQQMessageAndPic(String strContent, String pngPath) {
		TAPI tSend = new TAPI(OAuthConstants.OAUTH_VERSION_1);
		String strReturn = "";
		QQResult result = null;
		try {
			Log.i("ftftft", "qq_pngPath=" + pngPath);
			strReturn = tSend.addPic(oAuth, strFormat, strContent, Ip, pngPath);

			Log.i("ftftft", "sendPicReturn:" + strReturn);
			result = JsonUtils.parseQQResultJson(strReturn);
			tSend.shutdownConnection();
		} catch (Exception e) {
			tSend.shutdownConnection();
		}
		return result;
	}

	public static String Ip = "";

	public void getIp() {

		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		int wifiState = wifiManager.getWifiState();

		if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			Ip = intToIp(wifiInfo.getIpAddress());
		} else {
			try {
				for (Enumeration<NetworkInterface> en = NetworkInterface
						.getNetworkInterfaces(); en.hasMoreElements();) {
					NetworkInterface intf = en.nextElement();
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							Ip = inetAddress.getHostAddress().toString();
						}
					}
				}
			} catch (Exception ex) {
			}
		}
	}

	private String intToIp(int i) {
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	public OAuthV1 getOAuth() {
		return oAuth;
	}

	public void setOAuth(OAuthV1 auth) {
		oAuth = auth;
	}

}
