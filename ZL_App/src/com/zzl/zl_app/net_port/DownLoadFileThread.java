package com.zzl.zl_app.net_port;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadFileThread extends Thread {
	private String originUrl;
	private String goalUrl;

	private URL originURL;

	private int fileSize;

	public DownLoadFileThread(String originUrl, String goalUrl) {
		this.originUrl = originUrl;
		this.goalUrl = goalUrl;

	}

	@Override
	public void run() {
		HttpURLConnection connection = null;
		RandomAccessFile randomAccessFile = null;
		InputStream is = null;
		try {
			URL url = new URL(originUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (fileSize == 0) {
				fileSize = connection.getContentLength();
			}
			File file = new File(goalUrl);
			if (!file.exists()) {
				file.createNewFile();
			}
			randomAccessFile = new RandomAccessFile(file, "rwd");
			randomAccessFile.setLength(fileSize);
			randomAccessFile.seek(0);
			// 将要下载的文件写到保存在保存路径下的文件中
			is = connection.getInputStream();
			byte[] buffer = new byte[4096];
			int length = -1;
			while ((length = is.read(buffer)) != -1) {
				randomAccessFile.write(buffer, 0, length);
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
		}
		super.run();
	}
}
