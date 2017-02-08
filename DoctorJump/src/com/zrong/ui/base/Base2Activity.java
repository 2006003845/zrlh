package com.zrong.ui.base;

import com.zrong.entity.Music;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class Base2Activity extends Activity {
	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		init();
	}

	public void init() {
	};

	@Override
	protected void onResume() {
		Music.getInstance(mContext).resume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// Music.getInstance(mContext).pause();
		super.onPause();
	}

	@Override
	protected void onStop() {
		// Music.getInstance(mContext).stop();
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_HOME
				|| keyCode == KeyEvent.KEYCODE_SEARCH) {
			Music.getInstance(mContext).stop();
		}
		return super.onKeyDown(keyCode, event);
	}

	public static void exit2() {
		killProcess();
	}

	public static void killProcess() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
