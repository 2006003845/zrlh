/**
 * 
 */
package com.zrong.Android.online.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.activity.MainActivity;

/**
 *<p>
 * Titile:HttpConnection
 * </p>
 *<p>
 * Description:
 * </p>
 *<p>
 * Copyright:Copyright(c)2010
 * </p>
 *<p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 */
public class HttpConnection extends BaseConnection {
	/** get */
	public static final byte REQ_TYPE_GET = 0;
	/** post */
	public static final byte REQ_TYPE_POST = 1;
	/** HTTP请示类型 */
	byte reqType = 0; // 0:GET, 1:POST
	/** 使用POST方法时的提交数据 mode1 */
	byte[] reqData = null;
	/** 响应缓存数据 mode1 */
	byte[] respData = null;

	short packetType;

	private static boolean isUseAgent = true;

	private boolean isUseThread = true;

	/** http构造函数 mode=1 开发给所有类调用,通用http方法，如果无特殊用途，请用这个类型的http */
	public HttpConnection(short packetType, ConnectionHandler recver) {
		handler = recver;

		this.packetType = packetType;
	}

	/** http构造函数 mode=1 供给resManager调用,如果没有特殊用途，不要使用 */
	public HttpConnection(boolean isUse) {
		this.isUseThread = isUse;
	}

	/**
	 * 启动目标为指定url的HTTP请求，供外部调用 mode1
	 * 
	 * @param httpURL
	 * @return 请求是否被正确发送
	 */
	public boolean startURL(String httpURL) {
		reqType = REQ_TYPE_GET;
		reqData = null;
		return startURL(httpURL, false);
	}

	/**
	 * 使用POST方法启动目标为指定url的HTTP请求，供外部调用 mode1
	 * 
	 * @param httpURL
	 * @param postData
	 * @return 请求是否被正确发送
	 */
	public boolean startURLPost(String httpURL, byte[] postData) {
		reqType = REQ_TYPE_POST;
		reqData = postData;
		return startURL(httpURL, false);
	}

	/**
	 * 启动目标为指定url的HTTP请求，内部调用
	 * 
	 * @param httpURL
	 * @return 请求是否被正确发送
	 */
	private boolean startURL(String httpURL, boolean isNeedInit) {
		if (isBusy())
			return false; // 链接正在联网中不接受URL启动
		if (!alive) {
			open();
		}
		this.URL = httpURL;
		return true;
	}

	/**
	 * 判断链接是否正在使用中 mode1
	 * 
	 * @return
	 */

	public boolean isBusy() {
		if (URL != null)
			return true;
		return false;
	}

	/**
	 * 检查响应数据，建议只做查看使用，若要得到数据可使用pollRespData()方法
	 * 
	 * @return
	 */
	public byte[] checkRespData() {
		return respData;
	}

	/**
	 * 获取并清空响应数据，建议与isBusy()方法配合使用，当isBusy()==false才读取响应数据
	 * 
	 * @return
	 */
	public byte[] pollRespData() {
		byte[] data = respData;
		respData = null;
		return data;
	}

	/** 设置ip和port mode0 */
	public void setServiceInfo(String ip) {
		alive = false;
		this.URL = "http://".concat(ip);

	}

	/** 打开网络线程 */

	public void open() {
		alive = true;
		if (isUseThread) {
			Thread thread = new Thread(this);
			thread.start();
		} else {
			run();
		}
	}

	public boolean sendMessage(short type, byte[] body) {

		return false;
	}

	/**
	 * http运行线程
	 */

	public void run(){
			ConnectivityManager connMng = (ConnectivityManager) MainActivity.mContext.getSystemService(MainActivity.mContext.CONNECTIVITY_SERVICE);   
			NetworkInfo netInf = connMng.getActiveNetworkInfo();   
			String proxyHost = android.net.Proxy.getDefaultHost();   
			if((netInf != null && "WIFI".equals(netInf.getTypeName())) || proxyHost == null)
			{
				isUseAgent = false;
			}
			else
			{
				isUseAgent = true;
			}
			 
		    working1();

	}

	/**
	 * http工作的主方法工作模式1,包括发送和接受数据包，如果alive不等于false,会一直循环运行
	 */

	public void working1() {
		while (alive) {
			try {
				if (URL == null) {
					SystemAPI.sleep(300);
				} else {
					// 生成HTTP连接对象
					
					
					javax.microedition.io.HttpConnection httpConn = open(
							this.URL, reqType, isUseAgent);

					DataOutputStream dos = null;

					// 当请求类型为POST时，提交数据
					if (reqType == REQ_TYPE_POST && reqData != null) {
						dos = httpConn.openDataOutputStream();
						dos.write(reqData);//字节数组写入此输出流
					}
					int code = httpConn.getResponseCode();//获取响应码
					//响应成功--连接成功
					if (code == 200) {
						DataInputStream dis = httpConn.openDataInputStream();		
						byte[] resp = new byte[512];//字节数组缓冲区
						int off = 0;
						int len = dis.read(resp);//输入流中的数据读取到resp中
						while (len >= 0) {
							off += len;
							if (off >= resp.length) {
								byte[] b = new byte[resp.length];
								System.arraycopy(resp, 0, b, 0, resp.length);
								resp = new byte[b.length + 10];
								System.arraycopy(b, 0, resp, 0, b.length);
							}
							len = dis.read(resp, off, resp.length - off);
						}

						if (off < resp.length) {
							respData = new byte[off];
							System.arraycopy(resp, 0, respData, 0,
									respData.length);
						}
						dis.close();
						httpConn.close();
					}
					if (dos != null)
						dos.close();
					URL = null; // 请求结束后URL清空
					if (handler != null) {
						handler.recvMessage(packetType, 0, respData);//接受数据包
						alive = false;// 有handler处理的都是一次性连接
					}
				}
			} catch (SecurityException se) {
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isUseThread) {
				alive = false;
			}
		}
	}

	// public void working2()
	// {
	// Packet pack = null;
	// while(alive)
	// {
	// boolean handled = false;
	// try {
	// if ( pack == null ) {
	// pack = getRequest();
	// }
	// if ( pack == null ) {
	// handled = true;
	// alive = false;
	// throw new Exception( "http connection was killed" );
	// }
	// //handled = handlePackage( pack );
	//				 
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// closeStreams();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// closeStreams();
	// }
	// }
	// }

	/**
	 * 构造请求地址完成url
	 * 
	 * @param server
	 *            服务器地址
	 * @param port
	 *            端口
	 * @param path
	 *            地址路径
	 * @param gateway
	 *            是否走移动网关
	 * @return 完整url
	 */
	private String constructUrl(String server, int port, String path,
			boolean gateway) {
		StringBuffer urlbuf = new StringBuffer("http://");
		if (gateway) {
			urlbuf.append("10.0.0.172:80");
		} else {
			urlbuf.append(server);
			urlbuf.append(":");
			urlbuf.append(port);
		}
		urlbuf.append(path);
		String url = urlbuf.toString();
		return url;
	}

	/**
	 * 设置连接参数
	 */
	private void constructProperty(
			javax.microedition.io.HttpConnection connection, int len)
			throws IOException {
		// if ( len > 0 ) {
		// String slen = String.valueOf( len );
		// connection.setRequestProperty( "Content-Length", slen );
		// }
		// connection.setRequestProperty( "Connection", "keep-alive" );
		// connection.setRequestProperty( "Keep-Alive", "3600" );
		// //connection.setRequestProperty( "Connection", "close" );
		// connection.setRequestProperty( "Accept", "*/*" );
		// connection.setRequestProperty( "User-Agent",
		// "Profile/MIDP-1.0 Configuration/CLDC-1.0" );
		// connection.setRequestProperty( "Content-Language", "en-US" );
	}

	/**
	 * http处理数据包函数
	 * 
	 * @param pack
	 *            要发送的数据包
	 * @return 返回是否成功
	 * @throws IOException
	 */
	private boolean handlePackage(byte[] pack) throws IOException {
		javax.microedition.io.HttpConnection connection = (javax.microedition.io.HttpConnection) socket;
		constructProperty(connection, /* length */pack.length);
		os = socket.openDataOutputStream();
		os.write( /* data */pack);
		os.flush();
		int rc = connection.getResponseCode();
		if (rc == javax.microedition.io.HttpConnection.HTTP_INTERNAL_ERROR) {
			return false;
		}
		if (rc != javax.microedition.io.HttpConnection.HTTP_OK) {
			return false;
		}
		is = socket.openDataInputStream();
		issueRead();
		return true;
	}

	public void close() {
		super.close();
	}

	/**
	 * 具体解包函数
	 */
	private void issueRead() {

	}

	/**
	 * 自动根据代理配置生成HTTP连接对象
	 * 
	 * @param url
	 * @param reqType
	 *            TODO
	 * @return
	 * @throws Exception
	 */
	public static javax.microedition.io.HttpConnection open(String url,
			byte reqType, boolean isUserAgent) throws Exception {
		javax.microedition.io.HttpConnection httpConn = null;
		if (isUseAgent) {
			httpConn = (javax.microedition.io.HttpConnection) Connector
					.open(getURLAdnHost(url, true));
			httpConn.setRequestProperty("X-Online-Host", getURLAdnHost(url,
					false));
		} else {
			try {
				httpConn = (javax.microedition.io.HttpConnection) Connector
						.open(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		httpConn
				.setRequestMethod(reqType == REQ_TYPE_GET ? javax.microedition.io.HttpConnection.GET
						: javax.microedition.io.HttpConnection.POST);//设置请求方法(get/post)
		return httpConn;
	}

	/**
	 * 在代理连接时，自动分离请求URL和主机
	 * 
	 * @param url
	 * @param isReturnURL
	 *            true:返回请求URL，false:返回主机
	 * @return
	 */
	public static String getURLAdnHost(String url, boolean isReturnURL) {
		String xonlineUrl, sendUrl;
		int pointPos = url.indexOf("//");
		int cutPos = url.indexOf('/', pointPos + 2);
		if (cutPos != -1) {
			xonlineUrl = url.substring(pointPos + 2, cutPos);
			sendUrl = "http://10.0.0.172:80" + url.substring(cutPos);
		} else {
			xonlineUrl = url.substring(pointPos + 2);
			sendUrl = "http://10.0.0.172:80";
		}
		// //#ifdef Debug
		// Debug.println("httpUrl = " + (isReturnURL ? sendUrl : xonlineUrl));
		// //#endif
		return isReturnURL ? sendUrl : xonlineUrl;
	}

}
