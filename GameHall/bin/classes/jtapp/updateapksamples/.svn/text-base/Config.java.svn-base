package jtapp.updateapksamples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class Config 
{
	private static final String TAG = "Config";
	
	private static  String UPDATE_SERVER = "http://10.20.147.117/jtapp12/";
	private static  String UPDATE_VERJSON = "ver.json";
	private  static  String UPDATE_APKNAME = "jtapp-12-updateapksamples.apk";
	 
	private  static String UPDATE_SAVENAME = "updateapksamples.apk";
	
	private static int newVerCode = 0;
	private static String newVerName = "";
	
	private static String packageName;
	
	private static Activity mActivity;
	
	private static  ProgressDialog pBar;
	private static Handler handler = new Handler();
	/**
	 * 
	 * <p>Description:</p>
	 * @author yz
	 * @param activity
	 * @param pName 要更新的应用的包明
	 * @param updateSever 检查更新地址
	 * @param updateJSON 检查更新JSON文件  [{"appname":"jtapp12","apkname":"jtapp-12-updateapksamples.apk","verName":1.0.1,"verCode":2}]  
	 */
	public static void checkUpdate(Activity activity,String pName,String updateSever,String updateJSON)
	{
		mActivity = activity;
		packageName = pName;
		UPDATE_SERVER = updateSever;
		UPDATE_VERJSON = updateJSON;
		
		if (getServerVerCode(activity));
		{
			int vercode = Config.getVerCode(activity,packageName);
			
			if (newVerCode > vercode) 
			{
				doNewVersionUpdate();
			} else 
			{
				Log.v("DEBUG", "notNewVersionShow");
			}
		}
	}
	
	
	static void checkUpdate(Activity activity,String pName,String updateSever,int newVerCodes,String newVerNames,String UPDATE_APKNAMEs,String UPDATE_SAVENAMEs)
	{
		mActivity = activity;
		packageName = pName;
		
		newVerCode = newVerCodes;
		newVerName = newVerNames;
		UPDATE_APKNAME = UPDATE_APKNAMEs;
		UPDATE_SAVENAME = UPDATE_SAVENAMEs;
		
		int vercode = Config.getVerCode(activity,packageName);
		
		if (newVerCode > vercode) 
		{
			doNewVersionUpdate();
		} else 
		{
			Log.v("DEBUG", "notNewVersionShow");
		}
		
	}
	
	 static int getVerCode(Context context,String pName) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					/*"jtapp.updateapksamples"*/pName, 0).versionCode;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return verCode;
	}
	
	  static String getVerName(Context context,String pName) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					/*"jtapp.updateapksamples"*/pName, 0).versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return verName;	
	}
	
//	private static String getAppName(Context context) 
//	{
//		String verName = context.getResources()
//		.getText(R.string.app_name).toString();
//		return verName;
//	}
	
	
	
	private static boolean getServerVerCode(Activity activity) 
	{
		try {
			String verjson = NetworkTool.getContent(Config.UPDATE_SERVER
					+ Config.UPDATE_VERJSON,activity);
			JSONArray array = new JSONArray(verjson);
			if (array.length() > 0) 
				{
				JSONObject obj = array.getJSONObject(0);
				try {
					newVerCode = Integer.parseInt(obj.getString("verCode"));
					newVerName = obj.getString("verName");
					UPDATE_APKNAME = obj.getString("apkname");
					UPDATE_SAVENAME = obj.getString("appname");
				} catch (Exception e) {
					newVerCode = -1;
					newVerName = "";
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
			return false;
		}
		return true;
	}
	
	private static void doNewVersionUpdate() {
		int verCode = Config.getVerCode(mActivity,packageName);
		String verName = Config.getVerName(mActivity,packageName);
		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(verName);
		sb.append(" Code:");
		sb.append(verCode);
		sb.append(", 发现新版本:");
		sb.append(newVerName);
		sb.append(" Code:");
		sb.append(newVerCode);
		sb.append(", 是否更新?");
		final Dialog dialog = new AlertDialog.Builder(mActivity)
				.setTitle("软件更新")
				.setMessage(sb.toString())
				// 设置内容
				.setPositiveButton("更新",// 设置确定按钮
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								pBar = new ProgressDialog(mActivity);
								pBar.setTitle("正在下载");
								pBar.setMessage("请稍候...");
								pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
								downFile(Config.UPDATE_SERVER
										+ Config.UPDATE_APKNAME);
								dialog.dismiss();
							}

						})
				.setNegativeButton("暂不更新",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// 点击"取消"按钮之后退出程序
								//finish();
								dialog.dismiss();
							}
						}).create();// 创建
		// 显示对话框
		dialog.show();
	}

	private static void downFile(final String url) {
		pBar.show();
		new Thread() {
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				try {
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					long length = entity.getContentLength();
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;
					if (is != null) {

						File file = new File(
								Environment.getExternalStorageDirectory(),
								Config.UPDATE_SAVENAME);
						fileOutputStream = new FileOutputStream(file);

						byte[] buf = new byte[1024];
						int ch = -1;
						int count = 0;
						while ((ch = is.read(buf)) != -1) {
							fileOutputStream.write(buf, 0, ch);
							count += ch;
							if (length > 0) {
							}
						}

					}
					fileOutputStream.flush();
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}
					down();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}.start();

	}

	private static void down() {
		handler.post(new Runnable() {
			public void run() {
				pBar.cancel();
				update();
			}
		});

	}

	private static void update() {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), Config.UPDATE_SAVENAME)),
				"application/vnd.android.package-archive");
		mActivity.startActivity(intent);
	}
}
