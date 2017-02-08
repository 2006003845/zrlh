package com.zrong.Android.activity;



import java.util.ArrayList;
import java.util.HashMap;



import com.zrong.Android.Util.Music;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class CommerceListActivity extends GameActivity{
	public static CommerceListActivity mContext = null;
	public static int idx=0;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.commercelist);
		Button returnback = (Button)findViewById(R.id.commercelist_returnback);
		Button cancel = (Button)findViewById(R.id.commercelist_cancel);
		Button join =(Button)findViewById(R.id.commerce_join);
		join.setText("申请\n"+"加入");
		Button create = (Button)findViewById(R.id.commerce_create);
		create.setText("创建\n"+"商会");
		join.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, MainActivity.resources.getString(R.string.toast_info),Toast.LENGTH_SHORT ).show();
				
		        byte  type = 1;//请求类型：0邀请加入；1申请加入 
			Connection.sendMessage(GameProtocol.
			CONNECTION_SEND_COfC_Join_Req,
			ConstructData.getCOfC_Join_Req(type,GameData.player.id,GameData.player.name,GameData.mber[idx].id));							

			
			}
		});
		create.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				byte value = 0;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_COfC_Create_Info_Req,
								ConstructData
										.getCOfC_Create_Info_ReqData(value));	
			}
		});
		
		returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceListActivity.this.finish();
				
			}});
		
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceListActivity.this.finish();
				
			}});
		

		 

		ListView lv = (ListView)this.findViewById(R.id.commercelistview);
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		 HashMap<String, String> map;
		 for (int i = 0; i < GameData.mber.length; i++) 
		 {
			 map = new HashMap<String, String>();
			 
			 map.put("name", GameData.mber[i].name);
			 map.put("rate", GameData.mber[i].level + "");
			 map.put("number", GameData.mber[i].memberNum + "/" + GameData.mber[i].maxMemberNum);
			 list.add(map);
		 }
		
		 SimpleAdapter listAdapter = new SimpleAdapter(this,list,   
	                R.layout.commercelist_item, new String[] {"name","rate","number"},   
	                new int[] {R.id.name,R.id.rate,R.id.number});   
	  
	        lv.setAdapter(listAdapter);
	        
	    lv.setOnItemClickListener(new OnItemClickListener(){

				 
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					 idx = arg2;
/*					String commercelist_menu = MainActivity.resources.getString(R.string.commmercelist_menu);
					String[] menu = new String[]{commercelist_menu};
			    	final AlertDialog dlg = new AlertDialog.Builder(CommerceListActivity.this).create();
					dlg.show();
					dlg.getWindow().setContentView(R.layout.commercelist_menu);
				    Button menu_1 =(Button)dlg.findViewById(R.id.commerce_menu);
					menu_1.setText(commercelist_menu);
//					menu_1.setTypeface(GameDefinition.face);
					menu_1.setOnClickListener(new Button.OnClickListener()
					{

						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, MainActivity.resources.getString(R.string.toast_info),Toast.LENGTH_SHORT ).show();
							
					        byte  type = 1;//请求类型：0邀请加入；1申请加入 
						Connection.sendMessage(GameProtocol.
						CONNECTION_SEND_COfC_Join_Req,
						ConstructData.getCOfC_Join_Req(type,GameData.player.id,GameData.player.name,GameData.mber[idx].id));							
				dlg.dismiss();
						}		
						
					}
					);*/
					/*
				Builder builder = new AlertDialog.Builder(CommerceListActivity.this);
					builder.setItems(menu, new DialogInterface.OnClickListener() {
						
						 
						public void onClick(DialogInterface dialog, int index) {
							// TODO Auto-generated method stub
							if(index==0)
							{
								Toast.makeText(mContext, MainActivity.resources.getString(R.string.toast_info),Toast.LENGTH_SHORT ).show();
								
						        byte  type = 1;//请求类型：0邀请加入；1申请加入 
								Connection.sendMessage(GameProtocol.
								CONNECTION_SEND_COfC_Join_Req,
								ConstructData.getCOfC_Join_Req(type,GameData.player.id,GameData.player.name,GameData.mber[idx].id));							
						
							}
						}
					}).show();*/
					
				}});
	}
	
	

	 
	public void Activitychange(Class calss, Intent intent) {		
		// TODO Auto-generated method stub
		if(intent==null)
		{
			intent = new Intent();
		}
		intent.setClass(CommerceListActivity.this, calss);
		
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
