package com.zrong.Android.activity;

import com.zrong.Android.Util.Music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public abstract class GameActivity extends Activity{
	/**
	 * 判定是否是在"改变状态"，如果是就不需要停止音乐
	 */
	private boolean onChange = false;
	/**
	 * 开始"改变状态"
	 */
	public void onChange()
	{
		onChange = true;
	}
	/**
	 * 清楚"改变状态"
	 */
	public void disOnChange()
	{
		onChange = false;
	}
	/**
	 * 是否是"改变状态"
	 * @return
	 */
	public boolean isOnChange()
	{
		return onChange;
	}
	
	/**
	 * GameActivity自己或者子类的引用
	 */
	private GameActivity GameContext = null;
	
	
	//public abstract GameActivity getInstance();
	
	/**
	 * 获得GameActivity或者其子类的引用
	 * @return
	 */
	private GameActivity GameContext()
	{
		GameContext = getGameContext();
		return GameContext;
	}
	/**
	 * 子类要实现，返回子类本身的实例
	 * @return
	 */
	public abstract GameActivity getGameContext();
	 
	
	
	/**
	 * 清除GameActivity或者其子类的引用，这里基本上就是清理了引用指定的对象,除非还有其他引用指向这个对象,当然这种情况比较少见
	 */
	public void clearGameContext()
	{
		GameContext = null;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() 
	{
		if(!isOnChange())
		{
			Music.getInstance(GameContext()).pause();
		}
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Music.getInstance(GameContext()).resume();
		super.onRestart();
	}

	@Override
	protected void onResume() 
	{
		Music.getInstance(GameContext()).resume();
		super.onResume();
	}

	@Override
	protected void onStart() 
	{
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() 
	{
		
		if (!isOnChange())
		{
			
			Music.getInstance(GameContext()).stop();
		} 
		else 
		{
			disOnChange();
		}
		super.onStop();
	}

	@Override
	public void finish() 
	{
		clearGameContext();
		
		onChange();
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		// TODO Auto-generated method stub
		super.finishActivity(requestCode);
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		// TODO Auto-generated method stub
		super.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void finishFromChild(Activity child) {
		// TODO Auto-generated method stub
		super.finishFromChild(child);
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivity(intent);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) 
	{
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
	
	
}
