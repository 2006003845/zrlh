package com.zzl.zl_app.net_port;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class FileDownLoader {

	private Context context;

	private Handler mHandler;// 消息处理器

	private static final int IDLE = 1;// 定义三种下载的状态：初始化状态，正在下载状态，暂停状态
	private static final int DOWNLOADING = 2;
	private int state = IDLE;
	private String downloadFileDir;

	public int downloadLimitCount = 4;
	/**
	 * 临时存放将要下载文件
	 */
	private Vector tempPool = new Vector();
	/**
	 * 等待下载文件
	 */
	private Vector packetPool = new Vector();
	/**
	 * 下载文件线程管理
	 */
	private Vector<DownloadThreadManager> downLoadThreadPool = new Vector<DownloadThreadManager>(
			3);

	private DownloadThreadManager getDownloadThreadManager() {
		if (downLoadThreadPool.size() < 3) {
			DownloadThreadManager dtm = new DownloadThreadManager();
			dtm.currentStat = DownloadThreadManager.STATE_INIT;
			downLoadThreadPool.addElement(dtm);
			return dtm;
		} else {
			for (int i = 0; i < downLoadThreadPool.size(); i++) {
				DownloadThreadManager dtm = downLoadThreadPool.elementAt(i);
				if (dtm.currentStat == DownloadThreadManager.STATE_IDLE) {
					return dtm;
				}
			}
		}
		return null;
	}

	private void notifyIdleAndDoLoad() {
		DownloadThreadManager dtm = getDownloadThreadManager();
		if (dtm == null) {
			return;
		} else {
			dtm.currentStat = DownloadThreadManager.STATE_INIT;
			Object dlfi = getDownLoadFileInfoFromPool();
			if (dlfi == null) {
				dtm.currentStat = DownloadThreadManager.STATE_IDLE;
			} else {
				dtm.thread.setDownloadFileInfo(dlfi);
				dtm.thread.start();
			}
		}
	}

	private Object getDownLoadFileInfoFromPool() {
		if (packetPool.size() == 0) {
			return null;
		}
		Object dlfi = packetPool.elementAt(0);
		packetPool.removeElementAt(0);
		return dlfi;
	}

	class DownloadThreadManager {
		/**
		 * 空闲状态
		 */
		public static final int STATE_IDLE = 0;
		/**
		 * 准备状态
		 */
		public static final int STATE_INIT = 1;
		/**
		 * 忙碌状态
		 */
		public static final int STATE_BUSY = 2;
		public int currentStat = STATE_IDLE;
		FileDownLoadThread thread = new FileDownLoadThread(this);
	}

	public FileDownLoader(Context context, String downloadFileDir,
			Handler mHandler) {
		this.context = context;

		this.mHandler = mHandler;
		this.downloadFileDir = downloadFileDir;
		this.mHandler = mHandler;
	}

	/**
	 * 判断是否正在下载
	 */
	public boolean isdownloading() {
		return state == DOWNLOADING;
	}

	private boolean validateUrl(String urlStr) {
		return urlStr != null && urlStr.length() > 0;
	}

	public void download() {

	}

	public class FileDownLoadThread implements Runnable {
		private Object downloadfileinfo;
		private DownloadThreadManager dtm;

		public FileDownLoadThread(DownloadThreadManager dtm) {
			this.dtm = dtm;
		}

		public void setDownloadFileInfo(Object downloadfileinfo) {
			this.downloadfileinfo = downloadfileinfo;
		}

		public FileDownLoadThread(Object downloadfileinfo) {
			this.downloadfileinfo = downloadfileinfo;
		}

		public void start() {
			run();
		}

		@Override
		public void run() {
			dtm.currentStat = DownloadThreadManager.STATE_BUSY;
			HttpURLConnection connection = null;
			RandomAccessFile randomAccessFile = null;
			InputStream is = null;
			try {
				URL url = new URL("");
				connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");

				long len = connection.getContentLength();

				// 设置范围，格式为Range：bytes x-y;

				File file = new File("");
				if (!file.exists()) {
					file.createNewFile();
				}
				if (randomAccessFile == null) {
					// 本地访问文件
					randomAccessFile = new RandomAccessFile("", "rwd");
					randomAccessFile.setLength(len);
				}

				// 将要下载的文件写到保存在保存路径下的文件中
				is = connection.getInputStream();
				byte[] buffer = new byte[10 * 1024];
				int length = -1;
				while ((length = is.read(buffer)) != -1) {
					randomAccessFile.write(buffer, 0, length);
					// 用消息将下载信息传给进度条，对进度条进行更新
					Message message = Message.obtain();
					message.what = 0;
					message.arg1 = length;

					mHandler.sendMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
					randomAccessFile.close();
					connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				dtm.currentStat = DownloadThreadManager.STATE_IDLE;

				notifyIdleAndDoLoad();
			}
		}
	}

}
