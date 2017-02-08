package com.zrong.Android.activity;



import java.util.ArrayList;
import java.util.HashMap;



import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.ShopItemAdapter;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DoctorCustodyActivity extends GameActivity{
	public static DoctorCustodyActivity mContext = null;
	public static int ID;
	//public static int []state = {0,0,0,0,0,0,0,0,0};
	
	
	
	ArrayList<HashMap<String, String>> list;
	private SimpleAdapter listaAdapter;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.doctor_custody);
		ListView lv = (ListView)findViewById(R.id.doctorcustody_listview);
		list = new ArrayList<HashMap<String,String>>();
		
		
		HashMap<String, String> map ;
		if(GameData.trustName !=null){
		for(int i=0;i<GameData.trustName .length;i++)
		{
			map = new HashMap<String, String>();
			map.put("menu", GameData.trustName [i]);
//			 if(i<GameData.trustName .length)
			 {
				 map.put("state", GameData.trustType[i]==0? MainActivity.resources.getString(R.string.doctor_custody): MainActivity.resources.getString(R.string.doctor_custody_giveup));
			 }	
			list.add(map);
		}
		}
		listaAdapter = new SimpleAdapter(this, list, R.layout.custody_item, 
				                                       new String[] {"menu","state"}, new int[]{R.id.custody_items,R.id.custody_state});
		lv.setAdapter(listaAdapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//发包操作；
				ID=position;
				if(GameData.trustTarget[position] == 0)
				{
					//直接發包
					if(GameData.doctorLevel >= GameData.trustlevel[position])
					{
						Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[position], (short)GameData.trustId[position], (short)0, (byte)0, (byte)(0), (byte)0, null));
					}
					else
					{
						display();
					}
				}else if(GameData.trustTarget[position] == 1){
					Intent intent = new Intent();
					intent.putExtra("type", SimpleShopListActivity.TYPE_DOCTOR);
					//進入店鋪
					if(GameData.trustType[position]==0){
					  if(GameData.doctorLevel >= GameData.trustlevel[position])
					  {
					      mContext.Activitychange(SimpleShopListActivity.class, intent);
					  }
					}
					  else if(GameData.trustType[position]==1)
					 {
						Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[DoctorCustodyActivity.ID], (short)GameData.trustId[position], (short)0, (byte)1, (byte)1, (byte)0,null));
					 }
				}else if(GameData.trustTarget[position] == 2)
				{
					Intent intent = new Intent();
					intent.putExtra("type", SimpleShopListActivity.TYPE_DOCTOR);
					
					if(GameData.trustType[position]==0)
					{    
						//进入员工列表，在员工列表中点击某一项，发包。
						if(GameData.doctorLevel>=GameData.trustlevel[position])
						{
							mContext.Activitychange(SimpleEmployeelistActivity.class, intent);
						}else
						{
							display();
						}
					}else if(GameData.trustType[position]==1)
					{
						Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[DoctorCustodyActivity.ID], (short)GameData.trustId[position], (short)0, (byte)1, (byte)2, (byte)0,null));

					}
				}
		
			}
		});
		Button turnback = (Button)findViewById(R.id.doctor_button_returnback);
		Button cancel = (Button)findViewById(R.id.doctor_button_cancel);
		Button info = (Button)findViewById(R.id.doctor_button_info);
		Button task = (Button)findViewById(R.id.doctor_button_task);
		Button shop = (Button)findViewById(R.id.doctor_button_shop);
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorCustodyActivity.mContext.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorCustodyActivity.mContext.finish();
			}});
		info.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorInfoActivity.class, null);
				DoctorCustodyActivity.mContext.finish();
			}});
		task.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorTaskActivity.class, null);
				DoctorCustodyActivity.mContext.finish();
			}});
		shop.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//发包请求图片数据，然后在收包处，跳转界面
				mContext.Activitychange(DoctorShopActivity.class, null);
				DoctorCustodyActivity.mContext.finish();
			}});
	}
	public void display(){
		Toast.makeText(this, MainActivity.resources.getString(R.string.doctorlevel_toast), Toast.LENGTH_SHORT).show();
	}
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(DoctorCustodyActivity.this, calss);
		//zhouzhilong add---
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); 
		
		this.startActivity(intent);
		
	}
	
	public void updateAdapter(){
		//TODO 
		byte[] states = GameData.trustType;
		for(int i= 0; i<list.size();i++){
			HashMap<String,String> map2 = list.get(i);
			map2.put("state", states[i] == 0?MainActivity.resources.getString(R.string.doctor_custody): MainActivity.resources.getString(R.string.doctor_custody_giveup));
			list.set(i, map2);
		}	
		
		listaAdapter.notifyDataSetChanged();
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

	