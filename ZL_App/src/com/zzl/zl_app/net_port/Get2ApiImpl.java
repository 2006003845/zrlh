package com.zzl.zl_app.net_port;

import android.content.Context;
import android.util.Log;

import com.zzl.zl_app.cache.TxTCache;

public class Get2ApiImpl implements IGet2Api {

	private Context context;

	public Get2ApiImpl(Context context) {
		this.context = context;
	}

	public String doGet(String loadUrl) throws WSError {
		String str = null;
		str = TxTCache.getInstance().getUnSyncTxt(loadUrl);
		// str = ConnectionCaller.doGet(loadUrl);
		return str;
	}

	public byte[] doGetImgRes(String urlStr) {
		return ConnectionCaller.doGetImgRes(urlStr);
	}

	/**
	 * never used
	 * 
	 * @param infoMark
	 * @param index
	 * @param level
	 * @return
	 * @throws WSError
	 */
	public Object getInfoList(String infoMark, String index, String level)
			throws WSError {
		ConnectionCaller connectionCaller = new ConnectionCaller();
		connectionCaller.startURLPost("",
				getXMLRequest(new String[] {}, new String[] {}));
		return null;
	}

	public static byte[] getXMLRequest(String[] key, String[] value) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
			if (key != null) {
				for (int i = 0; i < key.length; i++) {
					buffer.append("<").append(key[i]).append(">")
							.append(value[i]).append("</").append(key[i])
							.append(">");
				}
			}
			buffer.append("</request>");
			Log.i("Log", buffer + "");
			String s = buffer.toString();
			buffer.delete(0, buffer.length());
			byte[] data = s.getBytes("UTF-8");
			return data;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean loadResPackConfig(String configUrl) {
		return false;
	}

}
