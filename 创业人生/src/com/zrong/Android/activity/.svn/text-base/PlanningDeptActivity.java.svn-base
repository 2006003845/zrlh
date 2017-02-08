package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;


import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Social;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PlanningDeptActivity extends GameActivity{
	public static PlanningDeptActivity mContext = null;
	public static Social s = null;
	private boolean isjumpshopinfo = false;
	private boolean isjumpsocial = false;
	private TextView tv;
	public static int pd_index ;
	ArrayList<HashMap<String, Object>> list;
	private SimpleAdapter adapter;
	public static final String strategy_desc =String.valueOf(MainActivity.resources.getString(R.string.planningdept_strategy)) ;
	public static final String desc[] = strategy_desc.split("/");
	private int[] strategyIcionIDs = {R.drawable.planningdept_drink,R.drawable.planningdept_gossip,
			               	    R.drawable.planningdept_mediaexposure,R.drawable.planningdept_shopsniper,
			               	    R.drawable.planningdept_behind,R.drawable.planningdept_girl};
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		pd_index = -1;
		isjumpshopinfo=getIntent().getStringExtra("jumpshop") !=null;
		isjumpsocial=getIntent().getStringExtra("jumpsocial") !=null;
		
		setContentView(R.layout.planningdept);
		Button turnback = (Button)findViewById(R.id.planningdept_button_returnback);
		Button cancel = (Button)findViewById(R.id.planningdept_button_cancel);
	//	Button confirm = (Button)findViewById(R.id.planningdept_confirm);
	//	tv = (TextView)findViewById(R.id.planningdept_textview);
		GridView gv = (GridView)findViewById(R.id.planningdept_gridview);
		turnback.setOnClickListener(new Button.OnClickListener(){
			 
			public void onClick(View v) {
				PlanningDeptActivity.mContext.finish();
				mContext =null;
				
				
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){			
			 
			public void onClick(View v) {
				PlanningDeptActivity.mContext.finish();
				mContext =null;
				
			}});
		/*confirm.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				//发包操作
				
				if(pd_index==-1)
				{
					//pd_index = 0;
					//zhouzhilong add 给出选择策略的提示:
					Toast.makeText(mContext, "请选择策略", Toast.LENGTH_SHORT).show();					
				}
				
				if(!isjumpshopinfo&&!isjumpsocial&&pd_index != -1)
				{ 	Intent intent = new Intent();
					intent.putExtra("fromPlanningdept", "Planningdept");
					mContext.Activitychange(SocialActivity.class, intent);				
				}
				if(isjumpshopinfo&&(pd_index==2||pd_index==3||pd_index==4))
				{//zhouzhilong -->>>我认为在这里发包不是很合适(这里无法获取到作用目标的ID
					
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)ShopInfoActivity.player_ID,(byte)1,new long[]{ShopInfoActivity.shop_ID}));
					
				}if(isjumpsocial&&( pd_index==2||pd_index==3||pd_index==4))
				{//进入店铺列表
				
					  Intent intent = new Intent();
					  intent.setClass(PlanningDeptActivity.this,
							PlanningDeptShoplistActivity.class);
					  change = true;
					  PlanningDeptActivity.this.startActivity(intent);
						Connection.sendMessage(
								GameProtocol.SEARCHSHOP_REQ,
								ConstructData.getShopAskList((long)SocialActivity.s.id));					 
				}if(isjumpsocial&&( pd_index==1||pd_index==5))
				{
					//进入员工列表
					Intent intent = new Intent();

					Bundle bundle = new Bundle();

					bundle.putByte("type", (byte) 3);

					bundle.putString("staffname", SocialActivity.s .name);

					bundle.putString("mastername", "");

					bundle.putByte("size", (byte) 0);

					bundle.putByteArray("office", new byte[] { 0 });

					bundle.putByteArray("tType", new byte[] { 0 });

					bundle.putByteArray("level", new byte[] { 0 });

					bundle.putInt("begin", 0);

					bundle.putInt("count", 50);

					intent.putExtras(bundle);

					intent.setClass(PlanningDeptActivity.this,
							DiglistActivity.class);
					change = true;
					PlanningDeptActivity.this.startActivity(intent);

						Connection.sendMessage(
								GameProtocol.REQSearchStaff_Req,
								ConstructData.SearchStaff_Req((byte) 3,SocialActivity.s .name, "",
										 (byte) 0, new byte[] { 0 },
										new byte[] { 0 }, new byte[] { 0 },
										0, 50));
					
				}if(isjumpsocial&&( pd_index==6))
				{
					//进行发包
					s = (Social) GameData.friend.elementAt(SocialActivity.friend_ID);
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
					
				}
				
				
			}});*/
//		tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_drink));
		 list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map;	
		for(int i = 0;i<strategyIcionIDs.length;i++)
		{
			map = new HashMap<String, Object>();
			map.put("strategy", strategyIcionIDs[i]);
			map.put("times",GameData.planningNum[i][0]+"/"+GameData.planningNum[i][1] );
		
			list.add(map);
		}
	        adapter = new SimpleAdapter(this, list, R.layout.planningdept_grid,
				                                  new String[]{"strategy","times"}, new int[]{R.id.strategy,R.id.strategy_times});
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {			
				pd_index = position+1;
				dialog();
		/*		if(!isjumpshopinfo){
				switch(pd_index){
				case 1:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_drink));					
				break;
				case 2:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_gossip));
					break;
				case 3:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_media));
					break;
					
				case 4:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_sniper));
					break;
					
				case 5:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_behind));
					break;
					
				case 6:
					tv.setText(MainActivity.resources.getString(R.string.planningdept_strategy_girl));
					break;
					
				}
				}else{
					//发包
				}*/
				
			}
		});
		
	}
	   protected void dialog()
	   {
		   //二次提示
		   AlertDialog.Builder builder = new AlertDialog.Builder(PlanningDeptActivity.mContext);
	       builder.setMessage(desc[pd_index-1])
		          .setTitle("需要："+"\n金钱："+GameData.planning_costmoney[pd_index-1][0]+"，创业点数："+GameData.planning_costpoint[pd_index-1][0])
		          .setIcon(strategyIcionIDs[pd_index-1])
	              .setPositiveButton("确认", new DialogInterface.OnClickListener() {					
					 
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//进行发包操作						
						if(pd_index==-1)
						{
							//pd_index = 0;
							//zhouzhilong add 给出选择策略的提示:
							Toast.makeText(mContext, "请选择策略", Toast.LENGTH_SHORT).show();					
						}
						
						if(!isjumpshopinfo&&!isjumpsocial&&pd_index != -1)
						{ 	Intent intent = new Intent();
							intent.putExtra("fromPlanningdept", "Planningdept");
							mContext.Activitychange(SocialActivity.class, intent);				
						}
						if(isjumpshopinfo&&(pd_index==2||pd_index==3||pd_index==4))
						{//zhouzhilong -->>>我认为在这里发包不是很合适(这里无法获取到作用目标的ID
							
							Connection.sendMessage(
									GameProtocol.PLANNINGACTION_REQ,
									ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,
											/*(long)ShopInfoActivity.player_ID,(byte)1,new long[]{ShopInfoActivity.shop_ID}*/
											(long)OtherShopInfoActivity.player_ID,(byte)1,new long[]{OtherShopInfoActivity.shop_ID}));
							
						}if(isjumpsocial&&( pd_index==2||pd_index==3||pd_index==4))
						{//进入店铺列表
						
							  Intent intent = new Intent();
							  intent.setClass(PlanningDeptActivity.this,
									PlanningDeptShoplistActivity.class);
							 
							  PlanningDeptActivity.this.startActivity(intent);
								Connection.sendMessage(
										GameProtocol.SEARCHSHOP_REQ,
										ConstructData.getShopAskList((long)SocialActivity.s.id));					 
						}if(isjumpsocial&&( pd_index==1||pd_index==5))
						{
							//进入员工列表
							Intent intent = new Intent();

							Bundle bundle = new Bundle();

							bundle.putByte("type", (byte) 3);

							bundle.putString("staffname", SocialActivity.s .name);

							bundle.putString("mastername", "");

							bundle.putByte("size", (byte) 0);

							bundle.putByteArray("office", new byte[] { 0 });

							bundle.putByteArray("tType", new byte[] { 0 });

							bundle.putByteArray("level", new byte[] { 0 });

							bundle.putInt("begin", 0);

							bundle.putInt("count", 50);
							bundle.putString("fromPlanningDept", "planningdept");

							intent.putExtras(bundle);

							intent.setClass(PlanningDeptActivity.this,
									DiglistActivity.class);
							
							PlanningDeptActivity.this.startActivity(intent);

								Connection.sendMessage(
										GameProtocol.REQSearchStaff_Req,
										ConstructData.SearchStaff_Req((byte) 3,SocialActivity.s .name, "",
												 (byte) 0, new byte[] { 0 },
												new byte[] { 0 }, new byte[] { 0 },
												0, 50));
							
						}if(isjumpsocial&&( pd_index==6))
						{
							//进行发包
							s = (Social) GameData.friend.elementAt(SocialActivity.friend_ID);
							Connection.sendMessage(
									GameProtocol.PLANNINGACTION_REQ,
									ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
							
						}																							
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
	public void display()
	{
		Toast.makeText(this, "id"+pd_index, Toast.LENGTH_LONG).show();
	}
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(PlanningDeptActivity.this, calss);
		
		this.startActivity(intent);
		
	}
	public void updateAdapter(){
		//TODO 
		//byte[] states = GameData.trustType;
		for(int i= 0; i<list.size();i++){
			HashMap<String,Object> map2 = list.get(i);
			map2.put("times",GameData.planningNum[i][0]+"/"+GameData.planningNum[i][1] );
			list.set(i, map2);
		}	
		
		adapter.notifyDataSetChanged();
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
