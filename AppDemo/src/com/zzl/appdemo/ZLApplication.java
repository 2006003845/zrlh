package com.zzl.appdemo;

import java.io.File;

import net.tsz.afinal.FinalBitmap;
import android.app.Application;

import com.zzl.zl_app.util.Tools;

public class ZLApplication extends Application {

	private static ZLApplication mInstance;

	public boolean isFullScreen = false;
	public String url;
	public boolean isRotate = false;
	public String wel_name = "";

	public static ZLApplication getApp() {
		return mInstance;
	}

	public FinalBitmap fb = null;

	@Override
	public void onCreate() {
		mInstance = this;
		super.onCreate();
		fb = FinalBitmap.create(getApplicationContext());
		fb.configDiskCachePath(Tools.path + "/appdemo_cache/");

		init();
	}

	private void init() {
		File file = new File(Tools.path + "/" + "appdemo" + "/"
				+ "config.properties");
		if (file.exists()) {
			isFullScreen = "true".equals(Tools.getPropertyValue(this,
					"fullscreen", "appdemo"));
			url = Tools.getPropertyValue(this, "url", "appdemo");
			wel_name = Tools.getPropertyValue(this, "wel_img", "appdemo");
		} else {
			isFullScreen = "true".equals(Tools.getPropertyValue(this,
					"fullscreen"));
			url = Tools.getPropertyValue(this, "url");
			wel_name = Tools.getPropertyValue(this, "wel_img", "appdemo");
		}
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		System.exit(0);
	}

}
