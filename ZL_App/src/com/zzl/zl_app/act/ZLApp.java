package com.zzl.zl_app.act;

import android.app.Application;

public class ZLApp extends Application {

	private static ZLApp mInstance;

	public static ZLApp getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

}
