package com.zrong.Android.activity;

import com.zrong.Android.logic.MainMenu;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

public class MusicService extends Service{

	private MediaPlayer player;
	private static int musicId;
	private static boolean loop;
	
	
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onStart(Intent intent,int startId)
	{
		Bundle bundle = intent.getExtras();
		int musicId = bundle.getInt("musicId");
		boolean loop = bundle.getBoolean("loop");
	    
		
		if(musicId == -1)//÷ÿ∆Ù“Ù¿÷
		{
			if(player != null)
			{
				if(!player.isPlaying())
				{
					player.start();
				}
			}
			else
			{
				player = MediaPlayer.create(this, MusicService.musicId);
				player.start();
				player.setLooping(MusicService.loop);
			}
			 
		}
		else if(musicId == -2)//pause music
		{
			if(player != null && player.isPlaying())
			{
				player.pause();
			}
		}
		else
		{
			if(player != null)
			{
				player.stop();
			}
			
			MusicService.musicId = musicId;
			MusicService.loop = loop;
			
//			if(MainMenu.seting[0])
			if(SystemSettingActivity.state[0])
			{
				player = MediaPlayer.create(this, musicId);
				player.start();
				player.setLooping(loop);
			}
		}
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		if(player != null)
		{
			player.stop();
		}
		
	}
	
	
}
