package com.zrong.Android.activity;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrong.Android.Util.GridImageAdapter;
import com.zrong.Android.Util.GridSpriteAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class DoctorShopActivity extends GameActivity{
	public static DoctorShopActivity mContext = null;
	public static int ID;
	private GridSpriteAdapter adapter;
	private List<Map<String,Object>> doctorshop_grid;
	GridView gv = null;
	 
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.doctor_shop);
		Button turnback = (Button)findViewById(R.id.doctor_button_returnback);
		Button cancel = (Button)findViewById(R.id.doctor_button_cancel);
		Button info = (Button)findViewById(R.id.doctor_button_info);
		Button custody = (Button)findViewById(R.id.doctor_button_custody);
		Button task = (Button)findViewById(R.id.doctor_button_task);
		turnback.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				DoctorShopActivity.mContext.finish();
			}});
			cancel.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorShopActivity.mContext.finish();
			}});
			info.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorInfoActivity.class, null);
				DoctorShopActivity.mContext.finish();
			}});
			custody.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorCustodyActivity.class, null);
				DoctorShopActivity.mContext.finish();
			}});
			task.setOnClickListener(new Button.OnClickListener()
			{
			
			 
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorTaskActivity.class, null);
				DoctorShopActivity.mContext.finish();
			}});
	         
		 gv = (GridView)findViewById(R.id.doctorshop_gridview);	
		
		doctorshop_grid = new ArrayList<Map<String,Object>>();
		Map<String , Object> map;
		for (int i = 0 ; i < GameData.hiddedShopId.length; i++) {
			map = new HashMap<String,Object>();
			int resID = 0;
			int scale = GameData.shopScale[GameData.hiddedShopId[i][0]];
			if(scale == 1)
			{ 
			     int id = (int) GameData.hiddedShopId[i][0];
			     id = id +2;
			     resID = GameData.shopLittleRes[id];
			}
			else
			{
				int id = (int) GameData.hiddedShopId[i][0];
				
				id = id+(scale==2?1:0);
				resID = GameData.shopLittleRes[id];
			}
			map.put("spriteId", resID);
			if(GameData.hiddedShopId[i][1]==0)
			{
				map.put("image", "unlock");			
			}else
			{
				map.put("image", "locked");
			}
			doctorshop_grid.add(map);
		}
		
		adapter = new GridSpriteAdapter(this, doctorshop_grid, R.layout.doctorshop_grid);
		//adapter.

		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//跳转到店铺信息界面，在店铺信息界面发
				ID = (int) GameData.hiddedShopId[position][0];
				mContext.Activitychange(DoctorShopinfoActivity.class, null);
			}
		});
	}
	public void init()
	{
		Map<String , Object> map = null;
		for(int i= 0; i<doctorshop_grid.size();i++)
		{
			map = doctorshop_grid.get(i);
			if(GameData.hiddedShopId[i][1]==0)
			{
				Log.i("yz","第"+i+"个店铺解锁");
				map.put("image", "unlock");		
			}else /*if((int)GameData.hiddedShopId[i][1]==1)*/
			{
				Log.i("yz","第"+i+"个店铺未解锁");
				map.put("image", "locked");
			}
			//doctorshop_grid.add(map);
		}
		
//		for(int i =0; i <doctorshop_grid.size();i++ )
//		{
//			map = doctorshop_grid.get(i);
//			int id = Integer.parseInt(String.valueOf(map.get("spriteId")));
//			Log.v("yz", "id ="+id);
//		}
//		adapter = new GridSpriteAdapter(this, doctorshop_grid, R.layout.doctorshop_grid);
//
//		gv.setAdapter(adapter);
		
		
		adapter.notifyDataSetChanged();
	}
	
	

	public void Activitychange(Class calss, Intent intent) 
	{
		if (intent == null) 
		{
			intent = new Intent();
		}
		intent.setClass(DoctorShopActivity.this, calss);
		 
		this.startActivity(intent);
	}
	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}


}
