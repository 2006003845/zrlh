package cn.zrong.connection;

import java.util.Vector;

import android.util.Log;
import cn.zrong.GameAPI;
import cn.zrong.io.BaseHttp;
import cn.zrong.io.HttpThread;
import cn.zrong.io.HttpThreadManager;
import cn.zrong.io.HttpWorker;

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

		if (GameAPI.packageStructure.equals(GameAPI.JSONTAG)) {
			p = new JsonPacket(type, data);
		} else {
			p = new ChunkPacket(type, data);
		}

		sendMessage(p, portType);
	}

	public static byte[] getDataSync(short type, byte[] data, int portType) {
		Packet p = null;

		if (GameAPI.packageStructure.equals(GameAPI.JSONTAG)) {
			p = new JsonPacket(type, data);
		} else {
			p = new ChunkPacket(type, data);
		}
		return getRespSync(p, portType);
	}

	private static byte[] getRespSync(Packet p, int portType) {
		BaseHttp http = HttpThreadManager.getThread(BaseHttp.HTTPWORKER);
		switch (portType) {
		case GameAPI.Port_Weibo:
			http.setPostURL(GameAPI.weiboUrl, p.getData());
			break;
		case GameAPI.Port_Role:
			http.setPostURL(GameAPI.roleUrl, p.getData());
			break;
		case GameAPI.Port_User:
			http.setPostURL(GameAPI.userUrl, p.getData());
			break;
		case GameAPI.Port_Game:
			http.setPostURL(GameAPI.gameUrl, p.getData());
			break;
		}

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
			switch (portType) {
			case GameAPI.Port_Weibo:
				http.setPostURL(GameAPI.weiboUrl, p.getData());
				break;
			case GameAPI.Port_Role:
				http.setPostURL(GameAPI.roleUrl, p.getData());
				break;
			case GameAPI.Port_User:
				http.setPostURL(GameAPI.userUrl, p.getData());
				break;
			case GameAPI.Port_Game:
				http.setPostURL(GameAPI.gameUrl, p.getData());
				break;
			}
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
				switch (portType) {
				case GameAPI.Port_Weibo:
					http.setPostURL(GameAPI.weiboUrl, p.getData());
					break;
				case GameAPI.Port_Role:
					http.setPostURL(GameAPI.roleUrl, p.getData());
					break;
				case GameAPI.Port_User:
					http.setPostURL(GameAPI.userUrl, p.getData());
					break;
				case GameAPI.Port_Game:
					http.setPostURL(GameAPI.gameUrl, p.getData());
					break;
				}
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
