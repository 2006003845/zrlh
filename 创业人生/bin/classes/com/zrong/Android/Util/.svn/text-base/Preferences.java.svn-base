package com.zrong.Android.Util;

import com.zrong.Android.game.GameGroupControl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
	private Context context;
	
	private GameGroupControl control;
	
	SharedPreferences preferences;
	
	public static final String SEVERLINE_ID = "severline_id";
	public static final String USERNAME ="userName";
	public static final String PASSWORD ="password";
	public static final String MUSICOPEN ="music";
	public static final String FRIENDACCEPT ="friend";
	public static final String WORLDCHATACCEPT ="world";
	public static final String PRIVATECHATACCEPT ="private";
	public static final String REGNAME ="regname";
	public static final String REGPASSWORD="regpassword";
	
	
	public Preferences(Context context,GameGroupControl control)
	{
		this.context = context;
		this.control = control;
		preferences = ((Activity)context).getPreferences(Activity.MODE_PRIVATE);
	}
	
	public boolean set(String key,boolean value)
	{
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putBoolean(key, value);
		editor.commit();
		return value;
	}
	
	public boolean set(String key,int value)
	{
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putInt(key, value);
		editor.commit();
		return true;
	}
	
	public boolean set(String key,String value)
	{
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(key, value);
		editor.commit();
		return true;
	}
	
	public boolean getBoolean(String key,boolean defaultValue)
	{
		return getSharedPreferences().getBoolean(key, defaultValue);
		 
	}
	
	public int getInt(String key)
	{
		return getSharedPreferences().getInt(key, -1);
		 
	}
	
	public String getString(String key)
	{
		return getSharedPreferences().getString(key, "");
		 
	}
	
	 
	
	private SharedPreferences getSharedPreferences()
	{
		if(preferences == null)
		{
			preferences = ((Activity)context).getPreferences(Activity.MODE_PRIVATE);
		}
		return preferences;
	}
	
}
