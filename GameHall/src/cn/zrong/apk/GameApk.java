package cn.zrong.apk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import cn.zrong.activity.HomeActivity;
import cn.zrong.apk.load.Downloader;
import cn.zrong.entity.GameItem;
import cn.zrong.utils.FileTools;
import dalvik.system.DexClassLoader;

public class GameApk {
	public String apkUrl;
	public String apkName;
	public GameItem gameItem;

	public GameApk(String apkUrl, String apkName, GameItem item) {
		this.apkUrl = apkUrl;
		this.apkName = apkName;
		this.gameItem = item;
	}

	/* http://game.91juhe.com/PassSystem/upDate/TestB.apk */
	private static final String sdCardPath = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	public static String apkStorePath = sdCardPath + "/hall/";

	public int loadOrLauchApk(Context context, Handler handler) {
		Bundle paramBundle = new Bundle();

		paramBundle.putBoolean("KEY_START_FROM_OTHER_ACTIVITY", true);

		FileTools.creatDir(apkStorePath);
		// String dexpath = apkStorePath + apkName;
		String dexpath = apkStorePath + "TextB.apk";

		String dexoutputpath = apkStorePath;

		int currentVersion = 0;
		int action = -1;
		try {
			action = getAPKAction(context, dexpath, dexoutputpath,
					currentVersion);
		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
		if (action == DOWNLOADGAME) {
			// Downloader loader = new Downloader(apkUrl, dexpath, 0, context,
			// handler, gameItem);
			Downloader loader = new Downloader(
					"http://game.91juhe.com/PassSystem/upDate/TestB.apk",
					dexpath, 0, context, handler, gameItem);
			if (loader.isdownloading()) {
				return 200;
			}
			try {
				loader.getDownloaderInfors();
				loader.download();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (action == UPDATEGAME) {
			Downloader loader = new Downloader(apkUrl, dexpath, 0, context,
					handler, gameItem);
			if (loader.isdownloading()) {
				return 200;
			}
			loader.getDownloaderInfors();
			loader.download();
		} else if (action == STARTGAME) {
			try {
				LoadAPK(context, paramBundle, dexpath, dexoutputpath);
			} catch (Exception e) {
				Log.e("error", e.getMessage());
			}

		}
		return action;
	}

	public static final byte DOWNLOADGAME = 0;
	public static final byte UPDATEGAME = 1;
	public static final byte STARTGAME = 2;

	/**
	 * 
	 * @param dexpath
	 *            sdcard path
	 * @param dexoutputpath
	 *            apk path
	 * @param curVersionCode
	 *            当前游戏的版本号，如果当前游戏没有安装,就是0
	 * @return apk应该执行的动作 0 应该下载apk,1应该更新apk,2应该开启apk
	 */
	private byte getAPKAction(Context context, String dexpath,
			String dexoutputpath, int lastVersionCode) {
		ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
		DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
				dexoutputpath, null, localClassLoader);

		PackageInfo plocalObject = context.getPackageManager()
				.getPackageArchiveInfo(dexpath, 1);
		// no game must download
		if (plocalObject == null) {
			return DOWNLOADGAME;
		} else {
			int versionCode = plocalObject.versionCode;
			// 当前版本号大于 手机中安装的游戏版本号,需要更新
			if (lastVersionCode > versionCode) {

				return UPDATEGAME;
			} else {
				return STARTGAME;
			}
		}
	}

	private void LoadAPK(Context context, Bundle paramBundle, String dexpath,
			String dexoutputpath) {
		ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
		DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
				dexoutputpath, null, localClassLoader);
		try {
			PackageInfo plocalObject = context.getPackageManager()
					.getPackageArchiveInfo(dexpath, 1);

			if ((plocalObject.activities != null)
					&& (plocalObject.activities.length > 0)) {
				String activityname = plocalObject.activities[0].name;
				Class localClass = localDexClassLoader.loadClass(activityname);
				Constructor localConstructor = localClass
						.getConstructor(new Class[] {});
				Object instance = null;
				try {
					instance = localConstructor.newInstance(new Object[] {});
				} catch (ExceptionInInitializerError e) {
					// TODO
				}
				if (instance == null) {
					return;
				}
				Method localMethodSetActivity = localClass.getDeclaredMethod(
						"setActivity", new Class[] { Activity.class });
				localMethodSetActivity.setAccessible(true);
				localMethodSetActivity.invoke(instance,
						new Object[] { context });
				Method methodonCreate = localClass.getDeclaredMethod(
						"onCreate", new Class[] { Bundle.class });
				methodonCreate.setAccessible(true);
				methodonCreate.invoke(instance, new Object[] { paramBundle });
				HomeActivity.mInstance.isLaucherToOtherAPK = true;
			}
		} catch (Exception ex) {
			Log.e("error", ex.getMessage());

		}
	}
}
