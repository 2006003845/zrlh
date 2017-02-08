package com.zrong.Android.activity;

 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

 

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.RecruitShopItemAdapter;
import com.zrong.Android.Util.ShopItemAdapter;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class RecruitActivity extends GameActivity implements DataChangeListener{
	
	
	
	
	private ListView listview;
	
	public static RecruitActivity mContext = null; 
	
	private long recruitShopId;
	
	private byte type=0;//0表示已经选过自己的店铺了，1表示没有选过店铺
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		 
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		 getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		 Bundle bundle = this.getIntent().getExtras();
		 
		 recruitShopId = bundle.getLong("shopId");
		 
		 type = bundle.getByte("type");
		
		this.setContentView(R.layout.recruitshop);
		
		Button returnback = (Button)this.findViewById(R.id.recruitshop_button_returnback);
		
		Button cancel = (Button)this.findViewById(R.id.recruitshop_button_cancel);
		
		returnback.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				RecruitActivity.this.finish();
				
			}
		}
		);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{ 
				RecruitActivity.this.finish();
			}
		}
		);
		
		listview = (ListView)this.findViewById(R.id.recruitshop_listview);
		
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		HashMap map = new HashMap<String, Object>();
		
		String imagetag = "image";
		String shopnametag =MainActivity.resources.getString(R.string.recruit_shop);
		String flowtag = MainActivity.resources.getString(R.string.recruit_customernumber);
		String companynametag =MainActivity.resources.getString(R.string.recruit_company);
		String buttontag ="button";
		
		final Shop shop[] = GameData.recruitShop;
		
		for(int i =0 ; i < shop.length ; i++)
		{
			map = new HashMap<String, String>();
			 
			map.put("image", R.drawable.hostile);
			map.put("shopname", shopnametag+shop[i].simpleName);
			map.put("flow",flowtag+shop[i].flowGain);
			map.put("companyname", companynametag+shop[i].shopKeeper);
			map.put("button", R.drawable.button_recruit);
			list.add(map);
		}
		
		RecruitShopItemAdapter adpter = new RecruitShopItemAdapter(RecruitActivity.this,list,R.layout.recruitshop_item,new String[]{"image","shopname","flow","companyname","button"},new int[]{R.id.recruitshop_item_hostile,R.id.recruitshop_item_shopname,R.id.recruitshop_item_flow,R.id.recruitshop_item_companyname,R.id.recruitshop_item_button });
		
		listview.setAdapter(adpter);
		
		adpter.setOnItemButtonClickListener(new  AdapterView.OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
				final long rid = shop[position].buildingId;
				
				if(type ==0)
				{
					final Shop myshop = GameData.getShop(recruitShopId);
					
					String name[] = new String[myshop.employees.length];
					for(int i = 0 ; i < name.length; i++)
					{
						name[i] = myshop.employees[i].name;
					}
					final AlertDialog dlg2 = new AlertDialog.Builder(RecruitActivity.this).create();
					dlg2.show();
					dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
			    	ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					if(name.length ==0){
						dlg2.dismiss();
						Toast toast = Toast.makeText(MainActivity.mContext,MainActivity.resources.getString(R.string.recruit_toast1), Toast.LENGTH_LONG); 
						 toast.show();
						 RecruitActivity.mContext.finish();
					}else{

					for (int i = 0; i < name.length; i++) {
						map = new HashMap<String, String>();
						map.put("name", name[i]);
						list.add(map);
					}
					 SimpleAdapter listAdapter = new SimpleAdapter(RecruitActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
					lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.CONNECTION_Shop_Recruit_Req,
									ConstructData.getShop_Recruit_Req(myshop.buildingId,rid,myshop.employees[position].id,(short)myshop.map_id));
							dlg2.dismiss();
						}
					});
					 
				}}
				else//需要选择店铺
				{
					if(GameData.corporation.shop.length<=0)
					{
						 Toast toast = Toast.makeText(MainActivity.mContext,MainActivity.resources.getString(R.string.recruit_toast), Toast.LENGTH_LONG); 
						 toast.show();
						 return;
						 
					}
					
					final Vector vector = new Vector();
					
					for(int i = 0 ; i < GameData.corporation.shop.length; i++)
					{
						if(GameData.corporation.shop[i].employees.length>0&&GameData.corporation.shop[i].trade_id == shop[position].trade_id &&!GameData.corporation.shop[i].isRecruiting()&&shop[position].map_id==GameData.corporation.shop[i].map_id)
						{
							vector.addElement(GameData.corporation.shop[i]);
						}
					}
					
					if(vector.size()<=0)
					{
						Toast toast = Toast.makeText(MainActivity.mContext,MainActivity.resources.getString(R.string.recruit_toast1), Toast.LENGTH_LONG); 
						 toast.show();
						return;
					}
					
					String shopName[] = new String[vector.size()];
					
					for(int i =0 ; i < shopName.length; i++)
					{
						shopName[i] = ((Shop)vector.elementAt(i)).name;
					}
					final AlertDialog dlg2 = new AlertDialog.Builder(RecruitActivity.this).create();
					dlg2.show();
					dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
			    	ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					if (shopName.length == 0) {
						dlg2.dismiss();
						Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.recruit_toast1),
								Toast.LENGTH_LONG).show();
						
					}
					else if(shopName.length>0){
					for (int i = 0; i < shopName.length; i++) {
						map = new HashMap<String, String>();
						map.put("name", shopName[i]);
						list.add(map);
					}
					 SimpleAdapter listAdapter = new SimpleAdapter(RecruitActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);				
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Shop s = ((Shop)vector.elementAt(position));
							Connection.sendMessage(GameProtocol.CONNECTION_Shop_Recruit_Req,
									ConstructData.getShop_Recruit_Req(s.buildingId,rid,s.employees[0].id,(short)s.map_id));
							dlg2.dismiss();
						}
					});
					
					
					
					/*Builder builder = new AlertDialog.Builder(RecruitActivity.this);
					
					builder.setItems(shopName,  new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int index) 
						{
							Shop s = ((Shop)vector.elementAt(index));
							Connection.sendMessage(GameProtocol.CONNECTION_Shop_Recruit_Req,
									ConstructData.getShop_Recruit_Req(s.buildingId,rid,s.employees[0].id,(short)s.map_id));
						}
					}
					).create().show();*/
				}
			}
		}
			}
		);
	}

	

	public void OnDataChange(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}
	//======================================
	/* 暂停 */
	

	 
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

