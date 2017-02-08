package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Shop;
import com.zrong.Android.element.Social;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PlanningDeptShoplistActivity extends GameActivity{
	public static PlanningDeptShoplistActivity mContext = null;
	private int idex;
	private ListView lv;
	private TextView tv;
	private Shop shop;
	public static Social s = null;
	private boolean isfromSocial = false;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		isfromSocial = getIntent().getStringExtra("fromsocial") !=null;
		setContentView(R.layout.planningdept_shoplist);
		Button turnback = (Button)findViewById(R.id.shoplist_returnback);
		turnback.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				PlanningDeptShoplistActivity.this.finish();			
			}
		});
		Button cancel = (Button)findViewById(R.id.shoplist_cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				PlanningDeptShoplistActivity.this.finish();	
			}
		});
	
		tv =(TextView)findViewById(R.id.shopmoral);
		lv = (ListView)findViewById(R.id.shoplistview);
		update();
		/*ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map;
		
		//final Shop shop[] = GameData.corporation.shop;
		 shop = GameData.corporation.shop;
		for(int i=0;i<shop.length;i++)
		{//内部判断状态，更改tv,和list列表中的内容，士气，客流，知名度，收益。
			map = new HashMap<String, Object>();
			if(shop[i]!=null){
			map.put("name", shop[i].simpleName);
			map.put("rate", shop[i].level);
			if(PlanningDeptActivity.pd_index ==2)
			{
			map.put("moral", shop[i].morale); 
			
			} else if(PlanningDeptActivity.pd_index ==3)
				{  tv.setText(MainActivity.resources.getString(R.string.shop_list_popularity));
					map.put("moral", shop[i].popularity);
					}else if(PlanningDeptActivity.pd_index ==4)
						{  tv.setText(MainActivity.resources.getString(R.string.shop_list_goodslevel));
							map.put("moral", shop[i].goodsLv);
							}
			
			list.add(map);
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				                                 R.layout.planningdept_shoplist_item, 
				                                 new String[]{"name","rate","moral"}, 
				                                 new int[]{R.id.name,R.id.rate,R.id.moral});
		lv.setAdapter(adapter);*/
		lv.setOnItemClickListener(new OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 if(isfromSocial)
					{    
						Social s = (Social) GameData.friend.elementAt(SocialActivity.friend_ID);
						shop = GameData.corporation.shop[position];
						}
				idex=position;				
				dialog();
			}
		});
		
	/*	turnback.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PlanningDeptShoplistActivity.mContext.finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PlanningDeptShoplistActivity.mContext.finish();
			}ccccc
		});*/
		
		
	}

   protected void dialog()
   {
	   AlertDialog.Builder builder = new AlertDialog.Builder(PlanningDeptShoplistActivity.mContext);
       builder.setMessage("确认使用该策略吗？")
	          .setTitle("提示")
              .setPositiveButton("确认", new DialogInterface.OnClickListener() {
				
				 
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//进行发包操作
					s = (Social) GameData.friend.elementAt(SocialActivity.friend_ID );
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{shop.id}));
				//	ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{shop[idex].id}));
					
				dialog.dismiss();
				}})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					 
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
       builder.create().show();
   }
   public void update(){
	   ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map = null;
		
		//final Shop shop[] = GameData.corporation.shop;
		// shop = GameData.corporation.shop;
		for(int i=0;i<GameData.corporation.shop.length;i++)
		{//内部判断状态，更改tv,和list列表中的内容，士气，客流，知名度，收益。
			shop = GameData.corporation.shop[i];
			map = new HashMap<String, Object>();
			if(shop!=null){
			map.put("name", shop.simpleName);
			map.put("rate", shop.level);
			if(PlanningDeptActivity.pd_index ==2)
			{
			map.put("moral", shop.morale); 
			
			} else if(PlanningDeptActivity.pd_index ==3)
				{  tv.setText(MainActivity.resources.getString(R.string.shop_list_popularity));
					map.put("moral", shop.popularity);
					}else if(PlanningDeptActivity.pd_index ==4)
						{  tv.setText(MainActivity.resources.getString(R.string.shop_list_goodslevel));
							map.put("moral", shop.goodsLv);
							}
			
			list.add(map);
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				                                 R.layout.planningdept_shoplist_item, 
				                                 new String[]{"name","rate","moral"}, 
				                                 new int[]{R.id.name,R.id.rate,R.id.moral});
		lv.setAdapter(adapter);
   }
	
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(PlanningDeptShoplistActivity.this, calss);
		
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
