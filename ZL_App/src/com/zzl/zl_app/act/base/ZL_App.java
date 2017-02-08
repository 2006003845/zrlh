package com.zzl.zl_app.act.base;

import java.io.File;

import android.app.Application;
import android.webkit.CacheManager;

import com.zzl.zl_app.cache.ImageCache;

public class ZL_App extends Application {

	private static ZL_App mInstance;

	public boolean m_bKeyRight = true;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;

	}

	public static ZL_App getInstance() {
		return mInstance;
	}

	public long getFreeMenorySize() {
		long totleM = Runtime.getRuntime().freeMemory();
		return totleM;
	}

	public void cleanCache() {
		ImageCache.getInstance().clear();
		File file = CacheManager.getCacheFileBaseDir();
		if (file != null && file.exists() && file.isDirectory()) {
			for (File item : file.listFiles()) {
				item.delete();
			}
			file.delete();
		}
		this.deleteDatabase("webview.db");
		this.deleteDatabase("webviewCache.db");
	}

	public int version;

}
