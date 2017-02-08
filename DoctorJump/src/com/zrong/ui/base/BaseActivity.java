package com.zrong.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.zrong.engine.GameScreen;
import com.zrong.engine.GameView;
import com.zrong.entity.Music;

public abstract class BaseActivity extends Activity {
	private Context mContext;
	private GameView view;
	private boolean landscape;
	private boolean fullScreen;
	private boolean filter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init(this);
		mContext = this;
	}

	public void createEngine() {
		if (landscape)
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (fullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		GameScreen.screenW = getWindowManager().getDefaultDisplay().getWidth();
		GameScreen.screenH = getWindowManager().getDefaultDisplay().getHeight();
		// view = new GameView(this);
		// view.setUseFilter(filter);
		// setContentView(view);
	}

	public static void exit() {
		killProcess();
	}

	public static void killProcess() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public boolean isLandscape() {
		return landscape;
	}

	public void setLandscape(boolean landscape) {
		this.landscape = landscape;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public void showFPS(boolean b, int fpsColor) {
		view.setShowFPS(b, fpsColor);
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public GameView getView() {
		return this.view;
	}

	public void setBackGround(int color) {
		view.setBackgroundColor(color);
	}

	public void setScreen(GameScreen screen) {
		if (view == null)
			throw new RuntimeException("must be first use create engine");
		view.setCurScreen(screen);
		GameView.isRun = true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO
		return view.touchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_HOME
				|| keyCode == KeyEvent.KEYCODE_SEARCH) {
			Music.getInstance(this).stop();
		}
		// TODO
		return view.keyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO
		return view.keyUp(keyCode, event);
	}

	@Override
	protected void onResume() {
		Music.getInstance(mContext).resume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Music.getInstance(mContext).pause();
		super.onPause();
	}

	@Override
	protected void onStop() {
		Music.getInstance(mContext).stop();
		super.onStop();
	}

	public abstract void init(Context context);

	@Override
	protected void onRestart() {

		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}