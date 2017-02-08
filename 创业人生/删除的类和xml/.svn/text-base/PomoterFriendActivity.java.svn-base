package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PomoterFriendActivity extends GameActivity{
	
	/*private  String [] promoterList ={};*/
 
	public static PomoterFriendActivity mContext; 
 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
 
		mContext = this;
 
		this.setContentView(R.layout.promoter_detail);
		
		//friendlist = this.getIntent().getExtras().getString("friendlist");
		
		Button returnback = (Button)this.findViewById(R.id.promoter_button_returnback);
		
		Button cancel = (Button)this.findViewById(R.id.promoter_button_cancel);
		
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				PomoterFriendActivity.this.finish();
				
			}
		}
		);
		cancel.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) 
			{
				PomoterFriendActivity.this.finish();
				
			}
		}
		);
		 
		
		
		ListView lv = (ListView)this.findViewById(R.id.promoter_listview);
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		 HashMap<String, String> map;
		 for (int i = 0; i < GameData.friendList.length; i++) 
		 {
			 map = new HashMap<String, String>();
			 
			 map.put("promoterList", GameData.friendList[i].userName);
			 list.add(map);
		 }
		
		  SimpleAdapter listAdapter = new SimpleAdapter(this,list,   
	                R.layout.promoter_detail_item, new String[] {"promoterList"},   
	                new int[] {R.id.promoter_name});   
	  
	        lv.setAdapter(listAdapter);

   }
	

	 
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if(intent == null)
		{
			intent = new Intent();
		}
		
		intent.setClass(PomoterFriendActivity.this, calss);
		 
		
		this.startActivity(intent);
	}
	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
