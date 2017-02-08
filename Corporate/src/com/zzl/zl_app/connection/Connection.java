package com.zzl.zl_app.connection;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.example.corporate.PlatformAPI;
import com.zzl.zl_app.io.BaseHttp;
import com.zzl.zl_app.io.HttpThread;
import com.zzl.zl_app.io.HttpThreadManager;
import com.zzl.zl_app.io.HttpWorker;

public class Connection {
	private static Vector<Packet> packetPool = new Vector<Packet>();
	private static Vector<Integer> portVector = new Vector<Integer>();

	/**
	 * 
	 * <p>
	 * Description:发送的数据方法
	 * </p>
	 * 
	 * @author yz
	 * @param type
	 *            数据类型 即包id
	 * @param data
	 *            数据
	 */
	public static void sendMessage(short type, byte[] data, int portType) {
		Packet p = null;

		if (PlatformAPI.packageStructure.equals(PlatformAPI.JSONTAG)) {
			p = new JsonPacket(data);
		} else {
			p = new ChunkPacket(data);
		}
		sendMessage(p, portType);
	}

	public static byte[] getDataSync(byte[] data,
			ArrayList<BasicNameValuePair> pairs, String requestUrl) {
		Packet p = null;

		if (PlatformAPI.packageStructure.equals(PlatformAPI.JSONTAG)) {
			p = new JsonPacket(data);
		} else if (PlatformAPI.packageStructure.equals(PlatformAPI.PAIRTAG)) {
			p = new PairPacket(pairs);
		} else {
			p = new ChunkPacket(data);
		}
		return getRespSync(p, PlatformAPI.BaseUrl + requestUrl);
	}

	private static byte[] getRespSync(Packet p, String requestUrl) {
		BaseHttp http = HttpThreadManager.getThread(BaseHttp.HTTPWORKER);
		// http.setPostURL(PlatformAPI.requestUrl, p.getData());
		Log.i("IO", requestUrl);
		http.setPostURL(requestUrl, p.getPairs());

		http.startRun();
		byte[] bytes = ((HttpWorker) http).getResponse();
		return bytes;
	}

	private static void sendMessage(Packet p, int portType) {
		BaseHttp http = HttpThreadManager.getThread(BaseHttp.HTTPTHREAD);

		if (http == null) {
			Log.d("DEBUG", "addRequest");
			addRequest(p, portType);
		} else {
			http.setPostURL(PlatformAPI.requestUrl, p.getData());
			// switch (portType) {
			// case PlatformAPI.Port_User:
			// http.setPostURL(PlatformAPI.requestUrl, p.getData());
			// break;
			// }
			http.startRun();
		}
	}

	private static synchronized void addRequest(Packet p, int portType) {
		packetPool.addElement(p);
		portVector.addElement(portType);
	}

	public static synchronized Packet getRequest() throws InterruptedException {
		if (!packetPool.isEmpty()) {
			Packet pak = (Packet) packetPool.elementAt(0);
			packetPool.removeElementAt(0);
			return pak;
		}
		return null;
	}

	public static synchronized int getPortType() throws InterruptedException {
		if (!portVector.isEmpty()) {
			int portType = portVector.elementAt(0);
			packetPool.removeElementAt(0);
			return portType;
		}
		return -1;
	}

	public static synchronized boolean isEmpty() {
		return packetPool.isEmpty();
	}

	/**
	 * 
	 * <p>
	 * Description: 发送packetPool里的数据
	 * </p>
	 * 
	 * @author yz
	 */
	public static void sendRequest() {
		Log.v("DEBUG", "sendRequest");

		BaseHttp http = HttpThreadManager.getThread(BaseHttp.HTTPTHREAD);

		if (http == null)
			return;

		try {
			Packet p = getRequest();
			int portType = getPortType();
			if (p != null) {
				http.setPostURL(PlatformAPI.requestUrl, p.getData());
				// switch (portType) {
				// case PlatformAPI.Port_User:
				// http.setPostURL(PlatformAPI.requestUrl, p.getData());
				// break;
				// }
				http.startRun();
			} else {
				http.setStat(HttpThread.IDLE);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block

			http.setStat(HttpThread.IDLE);
			e.printStackTrace();
		}
	}

}
