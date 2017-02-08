package com.zrong.Android.Util;

import com.zrong.Android.activity.MusicService;
import com.zrong.Android.activity.SystemSettingActivity;
import com.zrong.Android.logic.MainMenu;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	
	private Context mcontext;
	
	private MediaPlayer player;
	
	private int musicId = -1;
	private boolean loop;
	
	private static Music music;
	
	private Music(Context context)
	{
		this.mcontext = context;
	}
	
	public static synchronized Music getInstance(Context context)
	{
		if(music == null)
		{
			music = new Music(context);
		}
		return music;
	}
	
	public void resume()
	{
//		if(MainMenu.seting[0])
			if(SystemSettingActivity.state[0])
		{
			if(player != null && player.isPlaying())
			{
				return;
			}
			 
			if(player != null)
			{
				player.stop();
				player = null;
			}
				 
				player = MediaPlayer.create(this.mcontext, musicId);
				
				player.start();
				player.setLooping(loop);
			 
		}
		
	}
	
	public void start(int musicId,boolean loop)
	{
//		if(MainMenu.seting[0])
			if(SystemSettingActivity.state[0])
		{
			if(player != null)
			{
				if(this.musicId == musicId && player.isPlaying())
				{
					
				}
				else
				{
					 player.stop();
					 
					 player = null;
				}
			   
			}
			player = MediaPlayer.create(this.mcontext, musicId);
			player.start();
			player.setLooping(loop);
			
		}
		this.musicId = musicId;
		this.loop = loop;
		
	}
	
	public void stop()
	{
		if(player != null)
		{
			player.stop();
			
			player = null;
		}
	}
	
	public void pause()
	{
		if(player != null)
		{
			player.pause();
		}
	}
	
}
