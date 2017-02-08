package cn.zrong.weibobinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BroadcastSender {

	public static final String LAG_Action_shareWeibo = "android.intent.action.shareweibo";
	public static final String LAG_Content_shareWeibo = "_content";
	public static final String LAG_ImagUrl_shareWeibo = "_imgurl";
	private static BroadcastSender mInstance;
	private Activity actv;

	private BroadcastSender(Activity activ) {
		this.actv = activ;
	}

	public static BroadcastSender createInstance(Activity activ) {
		if (mInstance == null) {
			mInstance = new BroadcastSender(activ);
		}
		return mInstance;
	}

	/**
	 * 发送图片+文字
	 * 
	 * @param content
	 * @param imgUrl
	 */
	public void sendBroadcast(String content, String imgUrl) {
		Intent intent = new Intent();
		Bundle b = new Bundle();
		b.putString(LAG_Content_shareWeibo, content);
		b.putString(LAG_ImagUrl_shareWeibo, imgUrl);
		intent.putExtras(b);
		intent.setAction(LAG_Action_shareWeibo);
		actv.sendBroadcast(intent);
	}

	/**
	 * 发送文字
	 * 
	 * @param content
	 */
	public void sendBroadcast(String content) {
		Intent intent = new Intent();
		Bundle b = new Bundle();
		b.putString(LAG_Content_shareWeibo, content);
		intent.putExtras(b);
		intent.setAction(LAG_Action_shareWeibo);
		actv.sendBroadcast(intent);
	}

}
