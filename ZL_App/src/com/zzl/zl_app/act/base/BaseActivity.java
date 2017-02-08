package com.zzl.zl_app.act.base;

import java.util.Map;
import java.util.Set;

import net.tsz.afinal.FinalActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseActivity extends FinalActivity {

	public Map<String, BaseActivity> getActMap() {
		return ActContent.actMap;
	}

	public void addActMap(String key, BaseActivity act) {
		if (key != null && act != null) {
			ActContent.actMap.put(key, act);
		}
	}

	public BaseActivity getAct(String key) {
		return ActContent.actMap.get(key);
	}

	private void removeActFromMap(String key) {
		ActContent.actMap.remove(key);
	}

	public void switchActIn() {
		if (inAnimId == -1)
			return;
		overridePendingTransition(inAnimId, outAnimId);
	}

	private int inAnimId = -1, outAnimId = -1;

	public void setActAwitchAnim(int inAnimId, int outAnimId) {
		this.inAnimId = inAnimId;
		this.outAnimId = outAnimId;
	}

	public void closeOneAct(String key) {
		BaseActivity act = getAct(key);
		if (act != null) {
			act.finish();
			removeActFromMap(key);
			overridePendingTransition(inAnimId, outAnimId);
		}
	}

	public void finishAllActs() {
		Set<String> keySet = ActContent.actMap.keySet();
		for (String key : keySet) {
			BaseActivity act = ActContent.actMap.get(key);
			if (act != null) {
				act.finish();
			}
		}
		ActContent.actMap.clear();
		System.gc();
	}

	private boolean onChange = false;

	public void onChange() {
		onChange = true;
	}

	public void disOnChange() {
		onChange = false;
	}

	public boolean isOnChange() {
		return onChange;
	}

	public abstract BaseActivity getContext();

	public static int width, height;

	/**
	 * 系统当前的分辨率的宽
	 */
	public static double CURRENT_SCREEN_WIDTH;
	/**
	 * 系统当前的分辨率的高
	 */
	public static double CURRENT_SCREEN_HEIGHT;
	/**
	 * 当前屏幕密度
	 */
	public static double CURRENT_DENSITY;

	public static double Default_SCREEN_WIDTH = 480;

	public static double Default_SCREEN_HEIGHT = 800;

	/**
	 * 屏幕宽度比例
	 */
	public static double WIDTH_RATIO;
	/**
	 * 屏幕高度比例
	 */
	public static double HEIGHT_RATIO;

	public void init() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		CURRENT_SCREEN_WIDTH = displayMetrics.widthPixels;
		CURRENT_SCREEN_HEIGHT = displayMetrics.heightPixels;
		CURRENT_DENSITY = displayMetrics.densityDpi;
		// 计算宽高比率
		WIDTH_RATIO = CURRENT_SCREEN_WIDTH / Default_SCREEN_WIDTH;
		HEIGHT_RATIO = CURRENT_SCREEN_HEIGHT / Default_SCREEN_HEIGHT;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (!isOnChange()) {
			// Music.getInstance(GameContext()).pause();
		}
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// Music.getInstance(GameContext()).resume();
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// Music.getInstance(GameContext()).resume();
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		if (!isOnChange()) {
			// Music.getInstance(GameContext()).stop();
		} else {
			disOnChange();
		}
		super.onStop();
	}

	@Override
	public void finish() {
		onChange();
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		super.finishActivity(requestCode);
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		super.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void finishFromChild(Activity child) {
		super.finishFromChild(child);
	}

	@Override
	public void startActivity(Intent intent) {
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivity(intent);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivityFromChild(child, intent, requestCode);
	}

	@Override
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return super.startActivityIfNeeded(intent, requestCode);
	}

	Resources res;

	public Drawable getDrawable(int drawableId) {
		if (res == null) {
			res = getContext().getResources();
		}
		if (drawableId == -1 || drawableId == 0) {
			return null;
		}
		Drawable drawable = res.getDrawable(drawableId);
		drawable.setBounds(0, 0, 40, 40);
		return drawable;
	}

	/**
	 * 显示 自定义弹出窗口
	 * 
	 * @param msg
	 * @param dialogLayoutID
	 * @param context
	 * @param title
	 * @param content
	 */
	public void showMsgDialog(int id, int dialogLayoutID, Context context,
			String title, String content) {
		View layout = getLayoutInflater().inflate(dialogLayoutID, null);
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setLayout((int) CURRENT_SCREEN_WIDTH,
				(int) CURRENT_SCREEN_HEIGHT);
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		if (title != null)
			setDialogTitle(dialog, id, title);
		if (content != null)
			setDialogContent(dialog, id, content);
		setPositiveClickListener(dialog, id);
		setNegativeClickListener(dialog, id);
	}

	/**
	 * 设置弹出窗口的显示内容
	 * 
	 * @param dialog
	 * @param layoutId
	 * @param msg
	 */
	public abstract void setDialogContent(AlertDialog dialog, int id,
			String content);

	/**
	 * 设置弹出窗口的显示标题
	 * 
	 * @param dialog
	 * @param layoutId
	 * @param title
	 */
	public abstract void setDialogTitle(AlertDialog dialog, int id, String title);

	/**
	 * 设置弹出窗口的确定按键监听
	 * 
	 * @param dialog
	 * @param layoutId
	 * @return
	 */
	public abstract OnClickListener setPositiveClickListener(
			AlertDialog dialog, int id);

	/**
	 * 设置弹出窗口的取消按键监听
	 * 
	 * @param dialog
	 * @param layoutId
	 * @return
	 */
	public abstract OnClickListener setNegativeClickListener(
			AlertDialog dialog, int id);

}
