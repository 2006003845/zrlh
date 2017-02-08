package com.zzl.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

public class ConnectionCaller {

	/** get */
	public static final byte REQ_TYPE_GET = 0;
	/** post */
	public static final byte REQ_TYPE_POST = 1;

	/** HTTP请示类型 */
	byte reqType = 0; // 0:GET, 1:POST
	/** 使用POST方法时的提交数据 */
	byte[] reqData = null;

	public ConnectionCaller() {

	}

	public static String doGet(String uri) throws WSError {
		String data = null;
		URI encodeUri = null;
		HttpGet httpGet = null;

		try {
			encodeUri = new URI(uri);
			httpGet = new HttpGet(encodeUri);
		} catch (URISyntaxException e) {
			Log.i("Log", "连接异常");
			String encodedUrl = uri.replace(' ', '+');
			httpGet = new HttpGet(encodedUrl);
			e.printStackTrace();
		}
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);// 获取Http响应
			HttpEntity httpEntity = httpResponse.getEntity();// 获取响应实体
			Log.i("Log", "获取响应实体" + (httpEntity == null));
			if (httpEntity != null) {
				InputStream is = httpEntity.getContent();
				data = convertStreamToString(is);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String doGet(String fileName, Context context) throws WSError {
		InputStream is = null;
		try {
			is = context.getAssets().open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = null;
		if (is != null)
			data = convertStreamToString(is);
		return data;
	}

	public static byte[] doGetImgRes(String urlStr) {
		byte[] imageBuffer = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			BufferedInputStream is = new BufferedInputStream(
					con.getInputStream());
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int b = -1;
			while ((b = is.read()) != -1) {
				os.write(b);
			}
			is.close();
			os.close();
			imageBuffer = os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return imageBuffer;
	}

	/**
	 * 将输入流转换为字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String convertStreamToString(InputStream input) {
		Log.i("Log", "convertStreamToString" + (input == null));
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder builder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

}
