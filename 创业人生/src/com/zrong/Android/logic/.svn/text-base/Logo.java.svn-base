package com.zrong.Android.logic;

import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.Android.Util.Music;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.SystemSettingActivity;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;

public class Logo extends LogicObject
{

	public Logo(Context context, GameGroupControl control) 
	{
		super(context, control);
		init(); 
	}

	 
	public void init() 
	{
		initView(); 
		
	}

	 
	public void initView() 
	{
		  
		MainActivity.mContext.setContentView(R.layout.openmusic);
		
		Button open = (Button)((MainActivity) context).findViewById(R.id.openmusic_open);
		
		
		Button close = (Button)((MainActivity) super.context).findViewById(R.id.openmusic_close);
		ImageView logo = (ImageView)((MainActivity) super.context).findViewById(R.id.openmusic_channellogo);
		
		TextView text = (TextView)((MainActivity) super.context).findViewById(R.id.openmusic_text1);
		if(GameDef.channelId.equals(GameDef.dangle))
		{
			logo.setBackgroundResource(R.drawable.danglelogo);
			text.setVisibility(View.GONE);
		}
		
		open.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{

//				MainMenu.seting[0] = true; 
				SystemSettingActivity.state[0] = true;
				
				Music.getInstance(MainActivity.mContext).start(R.raw.sound1, false);
				if(GameDef.channelId.equals(GameDef.dangle))
				{
					GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
					MainActivity.mContext.setContentView(GameGroupControl.gameGroupControl);
				}
				else
				{
			        MainActivity.mContext.setContentView(R.layout.slot);
				}
				
				
			}
			
		}
		
		);
		
		close.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) 
			{
//				MainMenu.seting[0] = false; 
			    SystemSettingActivity.state[0] = false;
				
			    Music.getInstance(MainActivity.mContext).start(R.raw.sound1, false);
			    
			    
			    if(GameDef.channelId.equals(GameDef.dangle))
				{
					GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
					MainActivity.mContext.setContentView(GameGroupControl.gameGroupControl);
				}
				else
				{
					
			        MainActivity.mContext.setContentView(R.layout.slot);
				}
			}
			
		}
		
		);
		
		
	}

	 
	public void synchviewdata() {
		// TODO Auto-generated method stub
		
	}

	 
	public void update() {
		// TODO Auto-generated method stub
		
	}

	 
	public void loadProperties(Vector v) {
		// TODO Auto-generated method stub
		
	}

	 
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	 
	protected void refurbish() 
	{
		 
		
	}

}
