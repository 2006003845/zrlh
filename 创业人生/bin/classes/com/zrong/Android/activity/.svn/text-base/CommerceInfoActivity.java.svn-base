package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import res.Draw;
import res.ResManager;
import res.UseResListNew;

import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.Util.ImageAdpter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.activity.CreateCommerceActivity.ImageAdapter;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.DigitsKeyListener;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;

import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class CommerceInfoActivity extends GameActivity implements OnGestureListener , OnDoubleTapListener {
	public static CommerceInfoActivity mContext = null;
	public static int idx =0;
	public static int idex =0;
	public static int index =0;
	private ViewFlipper mViewFlipper;    
    private  GestureDetector mGestureDetector;

	/**捐款类型*/
	byte optype = 1;
	private LayoutInflater factory = null;
	private String commerceuper_menu1 = MainActivity.resources.getString(R.string.commmerceuper_menu1);			 					
	private String commerceuper_menu2 = MainActivity.resources.getString(R.string.commmerceuper_menu2);			 					
	private String commerceuper_menu3 = MainActivity.resources.getString(R.string.commmerceuper_menu3);			 					
	private String commercestaff_menu1 = MainActivity.resources.getString(R.string.commercestaff_menu1);
	private String commercestaff_menu2 = MainActivity.resources.getString(R.string.commercestaff_menu2);
	private String commercestaff_menu3 = MainActivity.resources.getString(R.string.commercestaff_menu3);

	private String[] menu1 = commerceuper_menu1.split(",");
	private String[] menu2 = commerceuper_menu2.split(",");
	private String[] menu3 = commerceuper_menu3.split(",");
	private String[] staff_menu1 = commercestaff_menu1.split(",");
	private String[] staff_menu2 = commercestaff_menu2.split(",");
	private String[] staff_menu3 = commercestaff_menu3.split(",");
	
	private int[]	mImageIds = new int[]{
			1379,1380,1381,1382,1383,1384,1385,1386 };
	public String msg;
	 TextView notice;
	    
	Handler handler = new Handler();
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mContext = this;
		setContentView(R.layout.mycommerce);
		 mViewFlipper = (ViewFlipper) findViewById(R.id.flipper);
	//	mGestureDetector = new GestureDetector(this);  
//		setContentView(R.layout.commerceinfo);
		factory = LayoutInflater.from(this);
		//Button returnback = (Button)findViewById(R.id.commerceinfo_returnback);
		Button cancel = (Button)findViewById(R.id.commerceinfo_cancel);
		Button invite =(Button)findViewById(R.id.commerce_invite);
		Button operate =(Button)findViewById(R.id.commerce_operate);
		Button commerce_check =(Button)findViewById(R.id.commerce_check);
		Button interactive =(Button)findViewById(R.id.commerce_interactive);
		final Button joinpropa = (Button)findViewById(R.id.commerce_joinpropa);
		final Button holdpropa = (Button)findViewById(R.id.commerce_holdpropa);
		final Button propa_check = (Button)findViewById(R.id.commerce_checkpropa);
		final Button info = (Button)findViewById(R.id.commerceinfo_info);
		final Button dairy = (Button)findViewById(R.id.commerceinfo_dairy);
		final Button staff = (Button)findViewById(R.id.commerceinfo_staff);
		final Button propaganda = (Button)findViewById(R.id.commerceinfo_propaganda);
		final Button commercelist = (Button)findViewById(R.id.commerceinfo_commercelist);
		invite.setText("邀请\n"+"会员");
		operate.setText("商会\n"+"操作");	
		commerce_check.setText("查看\n"+"此人");
		joinpropa.setText("加入\n"+"宣传");
		holdpropa.setText("发起\n"+"宣传");
		propa_check.setText("查看\n"+"此人");
    	Connection
		.sendMessage(
				GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
				ConstructData
						.getMemberListRequestData(
								"", (short) 0,
								(short) 0,
								(short) 0,
								(short) 0));

		Connection
		.sendMessage(
				GameProtocol.CONNECTION_SEND_ClientDatas_Req,
				ConstructData
						.ClientDatas_Req((byte) 8));	
		
//		connection1();	
//		connection();	
		info.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				info.setBackgroundResource(R.drawable.selection_selected);
				dairy.setBackgroundResource(R.drawable.selection);
				staff.setBackgroundResource(R.drawable.selection);
				propaganda.setBackgroundResource(R.drawable.selection);
				commercelist.setBackgroundResource(R.drawable.selection);
				 mViewFlipper.setDisplayedChild(0);
			}
		});
		dairy.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				info.setBackgroundResource(R.drawable.selection);
				dairy.setBackgroundResource(R.drawable.selection_selected);
				staff.setBackgroundResource(R.drawable.selection);
				propaganda.setBackgroundResource(R.drawable.selection);
				commercelist.setBackgroundResource(R.drawable.selection);
				mViewFlipper.setDisplayedChild(1);
				
				TextView tv = (TextView)CommerceInfoActivity.mContext.findViewById(R.id.commercediarytextview);
				tv.setMovementMethod(ScrollingMovementMethod.getInstance());
            	StringBuffer buffer=new StringBuffer();
		    	String mebstr[];
		    	for(int i = 0; i< GameData.mebstr.size();i++)
		    	{
		    		mebstr=(String[])GameData.mebstr.elementAt(i);
		    		if(mebstr!=null)
		    		{
		    			for(int j=0;j<mebstr.length;j++)
		    			{
		    				buffer.append(mebstr[j]==null?"":mebstr[j]);
		    			}
		    		}
		    		buffer.append("\n");
		    	}    	
		    	tv.setText(buffer.toString());
			}
		});
		staff.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				info.setBackgroundResource(R.drawable.selection);
				dairy.setBackgroundResource(R.drawable.selection);
				staff.setBackgroundResource(R.drawable.selection_selected);
				propaganda.setBackgroundResource(R.drawable.selection);
				commercelist.setBackgroundResource(R.drawable.selection);
				mViewFlipper.setDisplayedChild(2);
				
				ListView	lv = (ListView)CommerceInfoActivity.mContext.findViewById(R.id.commercestafflistview);
				ArrayList<HashMap<String,Object>> list;
				 list = new ArrayList<HashMap<String,Object>>();
				 HashMap<String, Object> map;
				 for (int i = 0; i < GameData.member.memberNum; i++) 
				 {
					 map = new HashMap<String, Object>();
					 
					 map.put("name", GameData.member.employee[i].name);
					 map.put("companyrate", GameData.member.employee[i].companyLevel);
					 map.put("sex", GameData.member.employee[i].gender == 0 ? MainActivity.resources.getString(R.string.sex_men) : MainActivity.resources.getString(R.string.sex_lady) );
					 map.put("position", GameData.member.employee[i].duty == 99 ? "     "+MainActivity.resources.getString(R.string.chairman):GameData.member.employee[i].duty == 98 ? MainActivity.resources.getString(R.string.vice_chairman) : "     "+MainActivity.resources.getString(R.string.member));
					 list.add(map);
				 }
				 SimpleAdapter listAdapter;
				   listAdapter = new SimpleAdapter(CommerceInfoActivity.mContext,list,   
			                R.layout.commercestaff_item, new String[] {"name","companyrate","sex","position"},   
			                new int[] {R.id.name,R.id.companyrate,R.id.sex,R.id.position});   
			  
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

						 
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							idx=arg2;
						}
			        });
			        }
		});
		propaganda.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				info.setBackgroundResource(R.drawable.selection);
				dairy.setBackgroundResource(R.drawable.selection);
				staff.setBackgroundResource(R.drawable.selection);
				propaganda.setBackgroundResource(R.drawable.selection_selected);
				commercelist.setBackgroundResource(R.drawable.selection);
/*				if (GameData.pro==null||GameData.pro.length==0){
					joinpropa.setVisibility(View.INVISIBLE);
					propa_check.setVisibility(View.INVISIBLE);
				}*/
				mViewFlipper.setDisplayedChild(3);
				Connection
				.sendMessage(
						GameProtocol.CONNECTION_SEND_ClientDatas_Req,
						ConstructData
								.ClientDatas_Req((byte) 8));	
				
				ListView lv = (ListView)findViewById(R.id.propagandalist_listview);
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> map;
				if (GameData.pro!=null&&GameData.pro.length>0){
					joinpropa.setVisibility(View.VISIBLE);
					propa_check.setVisibility(View.VISIBLE);
				 for (int i = 0; i <GameData.pro.length; i++) 
				 {
					 map = new HashMap<String, Object>();
					 
				     map.put("image",String.valueOf(GameData.pro[i].resid) );
					 map.put("desc", GameData.pro[i].desc);
					 list.add(map);
				 }

				 ImageAdpter listAdapter = new ImageAdpter(CommerceInfoActivity.mContext,list,   
			                R.layout.propagandalist_item, new String[] {"image","desc"},   
			                new int[] {R.id.propagandalist_logo,R.id.propagandalist_detail});   
			  
			        lv.setAdapter(listAdapter);
			        
			        
				}else{
					joinpropa.setVisibility(View.INVISIBLE);
					propa_check.setVisibility(View.INVISIBLE);
					Toast.makeText(CommerceInfoActivity.mContext, MainActivity.resources.getString(R.string.propagandalist_toast),Toast.LENGTH_SHORT ).show();
					}
				lv.setOnItemClickListener(new OnItemClickListener(){
					public void onItemClick(AdapterView<?> arg0, View arg1, int position,
							long arg3) {
						idex=position;
					}
					});
			}
		});
		
		joinpropa.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 final AlertDialog dlg2 = new AlertDialog.Builder(CommerceInfoActivity.this).create();
					dlg2.show();					
					dlg2.getWindow().setContentView(R.layout.dialog_new);
					Button confirm =(Button)dlg2.findViewById(R.id.button_confirm);
					Button cancel =(Button)dlg2.findViewById(R.id.button_cancel);
					TextView tv = (TextView)dlg2.findViewById(R.id.notice_message);
					tv.setTextSize(16);
					tv.setText(MainActivity.resources.getString(R.string.propagandalist_toast1)+GameData.pro[index].money+MainActivity.resources.getString(R.string.propagandalist_toast2));
				    confirm.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_JointAdvocacy_Req,
									ConstructData.Join_JointAdvocacy_Req(GameData.pro[idex].id,0));//8宣传列表
							dlg2.dismiss();
						}
					});
				    cancel.setOnClickListener(new OnClickListener() {
				    	
				    	 
				    	public void onClick(View v) {
				    		// TODO Auto-generated method stub
				    		
				    		dlg2.dismiss();
				    	}
				    });
			}
		});
		holdpropa.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceInfoActivity.mContext.Activitychange(PropagandaActivity.class,null);
				CommerceInfoActivity.mContext.finish();
			}
		});
		commercelist.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				info.setBackgroundResource(R.drawable.selection);
				dairy.setBackgroundResource(R.drawable.selection);
				staff.setBackgroundResource(R.drawable.selection);
				propaganda.setBackgroundResource(R.drawable.selection);
				commercelist.setBackgroundResource(R.drawable.selection_selected);
				mViewFlipper.setDisplayedChild(4);
				
				Connection
				.sendMessage(
						GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
						ConstructData
								.getMemberListRequestData(
										"", (short) 0,
										(short) 0,
										(short) 0,
										(short) 0));

				ListView lv = (ListView)CommerceInfoActivity.mContext.findViewById(R.id.commercelistview);
				
				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
				 HashMap<String, String> map;
				 if(GameData.mber!=null&&GameData.mber.length>0){
				 for (int i = 0; i < GameData.mber.length; i++) 
				 {
					 map = new HashMap<String, String>();
					 
					 map.put("name", GameData.mber[i].name);
					 map.put("rate", GameData.mber[i].level + "");
					 map.put("number", GameData.mber[i].memberNum + "/" + GameData.mber[i].maxMemberNum);
					 list.add(map);
				 }
				 }
				
				 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.mContext,list,   
			                R.layout.commercelist_item, new String[] {"name","rate","number"},   
			                new int[] {R.id.name,R.id.rate,R.id.number});   
			  
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener(){

						 
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							index=arg2;
						}
			        });
			}
		});
		commerce_check.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		interactive.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final  AlertDialog dlg3 = new AlertDialog.Builder(CommerceInfoActivity.this).create();
				dlg3.show();
				dlg3.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg3.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				 if(GameData.player.capacityId==99){
				for (int i = 0; i < staff_menu1.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();	
						 map.put("name", staff_menu1[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							 Intent intent = new Intent();
							 Bundle bundle = new Bundle();
							switch(position){
							case 0://发送邮件
								 bundle.putString("receiver",  GameData.member.employee[idx].name);
								 
								 intent.putExtras(bundle);
								 
								 intent.setClass(CommerceInfoActivity.this, WriteMailActivity.class);
								 
								 CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
								 dlg3.dismiss();
								break;
							case 1://发送私聊
								
								bundle.putInt("type", 1);

								bundle.putString("destname", GameData.member.employee[idx].name);
								bundle.putLong("destId", GameData.member.employee[idx].memId);

								intent.putExtras(bundle);
								 intent.setClass(CommerceInfoActivity.this, ChatActivity.class);
								CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MESSAGE);
//								CommerceStaffActivity.this.Activitychange(
//										ChatActivity.class, intent);
								dlg3.dismiss();
								break;
							case 2://加为好友
								Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(GameData.member.employee[idx].memId, SocialActivity.FRIEND_TYPE_FRIEND));
								dlg3.dismiss();
								break;
							case 3://任命副会长
								Builder builde = new AlertDialog.Builder(
										CommerceInfoActivity.this);
								
								builde.setMessage(MainActivity.resources.getString(R.string.appointed)+GameData.member.employee[idx].name+MainActivity.resources.getString(R.string.as_vice_chairman))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										Connection.sendMessage(GameProtocol.
												CONNECTION_SEND_COfC_Change_Duty_Req,
												ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[index].memId,(byte)98));//98任命副会长
										
										dialog.dismiss();
										dlg3.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
										dlg3.dismiss();
									}} );
							builde.create().show();
							dlg3.dismiss();
								break;
							case 4://开除会员
								Builder build = new AlertDialog.Builder(
										CommerceInfoActivity.this);
								
								build.setMessage(MainActivity.resources.getString(R.string.fire_member))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										if(GameData.player.id == GameData.member.employee[idx].memId)
										{
											display_fire();
											
											return;
										}
										else
										{
											Connection.sendMessage(GameProtocol.
													CONNECTION_SEND_COfC_Change_Duty_Req,
													ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[idx].memId,(byte)-1));//-1退出商会
										}
										dialog.dismiss();
										dlg3.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
										dlg3.dismiss();
									}} );
							build.create().show();
							dlg3.dismiss();
								break;
							case 5://招揽人才
								
								dlg3.dismiss();
								break;
							case 6://使用策略
								
								dlg3.dismiss();
								break;
							  }
							}
						});
			}else if(GameData.player.capacityId==98){
				for (int i = 0; i < staff_menu2.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();	
						 map.put("name", staff_menu2[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							 Intent intent = new Intent();
							 Bundle bundle = new Bundle();
							switch(position){
							case 0://发送邮件
                                 bundle.putString("receiver",  GameData.member.employee[idx].name);
								 
								 intent.putExtras(bundle);
								 
								 intent.setClass(CommerceInfoActivity.this, WriteMailActivity.class);
								 
								 CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
								 dlg3.dismiss();	
								break;
							case 1://发送私聊

								bundle.putInt("type", 1);

								bundle.putString("destname", GameData.member.employee[idx].name);
								bundle.putLong("destId", GameData.member.employee[idx].memId);

								intent.putExtras(bundle);
								 intent.setClass(CommerceInfoActivity.this, ChatActivity.class);
								CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MESSAGE);
//								CommerceStaffActivity.this.Activitychange(
//										ChatActivity.class, intent);
								dlg3.dismiss();
								break;
							case 2://加为好友
								Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(GameData.member.employee[idx].memId, SocialActivity.FRIEND_TYPE_FRIEND));
								dlg3.dismiss();
								break;
							case 3://开除会员
								Builder build = new AlertDialog.Builder(
										CommerceInfoActivity.this);
								
								build.setMessage(MainActivity.resources.getString(R.string.fire_member))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										if(GameData.player.id == GameData.member.employee[idx].memId)
										{
											display_fire();
											
											return;
										}
										else
										{
											Connection.sendMessage(GameProtocol.
													CONNECTION_SEND_COfC_Change_Duty_Req,
													ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[idx].memId,(byte)-1));//-1退出商会
										}
										dialog.dismiss();
										dlg3.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
										dlg3.dismiss();
									}} );
							build.create().show();
							dlg3.dismiss();
								break;
							case 4://招揽人才
								
								
								dlg3.dismiss();
								break;
							case 5://使用策略
								
								dlg3.dismiss();
								break;
							  }
							}
						});
			}else{
				for (int i = 0; i < staff_menu3.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();	
						 map.put("name", staff_menu3[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							 Bundle bundle = new Bundle();
							switch(position){
							case 0://发送邮件
								bundle.putString("receiver",  GameData.member.employee[idx].name);
								 
								 intent.putExtras(bundle);
								 
								 intent.setClass(CommerceInfoActivity.this, WriteMailActivity.class);
								 
								 CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
								 dlg3.dismiss();	
								break;
							case 1://发送私聊

								bundle.putInt("type", 1);

								bundle.putString("destname", GameData.member.employee[idx].name);
								bundle.putLong("destId", GameData.member.employee[idx].memId);

								intent.putExtras(bundle);
								 intent.setClass(CommerceInfoActivity.this, ChatActivity.class);
								CommerceInfoActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MESSAGE);
//								CommerceStaffActivity.this.Activitychange(
//										ChatActivity.class, intent);
								dlg3.dismiss();
								break;
							case 2://加为好友
								Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(GameData.member.employee[idx].memId, SocialActivity.FRIEND_TYPE_FRIEND));
								dlg3.dismiss();
								break;
							case 3://招揽人才
								
								
								dlg3.dismiss();
								break;
							case 4://使用策略
								
								
								dlg3.dismiss();
								break;
							  }
							}
						});
			}
			}
		});
		if(GameData.player.capacityId < 98){
			invite.setVisibility(View.INVISIBLE);
		}
			
		invite.setOnClickListener(new Button.OnClickListener(){
			
			
			public void onClick(View v) {
				/*
				final  AlertDialog dlg1 = new AlertDialog.Builder(CommerceInfoActivity.this).create();
				dlg1.show();
				dlg1.getWindow().setContentView(R.layout.invite_player_dlg);
				LayoutInflater factory=LayoutInflater.from(CommerceInfoActivity.mContext);
				View view=factory.inflate(R.layout.invite_player_dlg, null);

				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.showSoftInput(view, 0);
				imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
				TextView tv = (TextView)dlg1.findViewById(R.id.notice_message);
				final EditText et = (EditText)dlg1.findViewById(R.id.notice_message_input);
				Button confirm = (Button)dlg1.findViewById(R.id.button_confirm);
				Button cancel = (Button)dlg1.findViewById(R.id.button_cancel);
				tv.setText(MainActivity.resources.getString(R.string.commmerceuper_inputname));
				confirm.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String name = (et.getText()
								.toString()).trim();
						 if(name != null )
						{
							byte  type = 0;//请求类型：0邀请加入；1申请加入
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_COfC_Join_Req,
									ConstructData.getCOfC_Join_Req(type,0,name,GameData.member.id));
							
						}
						 dlg1.dismiss();
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg1.dismiss();
					}
				});*/
				
				
				View view3 = factory.inflate(
						R.layout.invite_player_dialog,
						null);

				final EditText et = (EditText) view3
						.findViewById(R.id.invite_player_dialog_edit);
				TextView tv = (TextView)view3.findViewById(R.id.invite_player_dialog_text);
				tv.setText(MainActivity.resources.getString(R.string.commmerceuper_inputname));

				Builder builder = new AlertDialog.Builder(
						CommerceInfoActivity.this);

				builder.setView(view3);

				builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
						new DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface arg0, int arg1) {
								String name = (et.getText()
										.toString()).trim();
								 if(name != null )
								{
									byte  type = 0;//请求类型：0邀请加入；1申请加入
									Connection.sendMessage(GameProtocol.
											CONNECTION_SEND_COfC_Join_Req,
											ConstructData.getCOfC_Join_Req(type,0,name,GameData.member.id));
									
								}
							}

						});

				builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
						new DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface arg0, int arg1) {
							}

						}).create().show();
				
			}
			});
		operate.setOnClickListener(new Button.OnClickListener(){
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final  AlertDialog dlg2 = new AlertDialog.Builder(CommerceInfoActivity.this).create();
				dlg2.show();
				dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				 if(GameData.player.capacityId==99){
				for (int i = 0; i < menu1.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();	
						 map.put("name", menu1[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							switch(position){
							case 0: //修改公告
									View view1 = factory.inflate(
											R.layout.invite_player_dialog,
											null);

									final EditText et = (EditText) view1
											.findViewById(R.id.invite_player_dialog_edit);
									TextView tv = (TextView)view1.findViewById(R.id.invite_player_dialog_text);
									tv.setText(MainActivity.resources.getString(R.string.commerceinfo_changenotice));

									Builder builder = new AlertDialog.Builder(
											CommerceInfoActivity.this);

									builder.setView(view1);

									builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {
													
													String msgstr = et.getText()
															.toString();
													//notice.setText(msgstr);
													 if(msgstr != null )
													{
														 Connection.sendMessage(GameProtocol.CONNECTION_SEND_COfC_Change_Affiche_Req,ConstructData.getCOfC_Change_Affiche_ReqData(msgstr));
														
													}
													 dlg2.dismiss();
												}

											});

									builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {
													dlg2.dismiss();
												}

											}).create().show();
								break;
							case 1://设置捐款额度
								View view_1 = factory.inflate(
										R.layout.invite_player_dialog,
										null);

								final EditText edt = (EditText) view_1
										.findViewById(R.id.invite_player_dialog_edit);
								edt.setKeyListener(new DigitsKeyListener());
								TextView tv1 = (TextView)view_1.findViewById(R.id.invite_player_dialog_text);
								tv1.setText(MainActivity.resources.getString(R.string.commmerceuper_donate_number));

								Builder buil = new AlertDialog.Builder(
										CommerceInfoActivity.this);

								buil.setView(view_1);

								buil.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
										new DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface arg0, int arg1) {
												String num = edt.getText()
														.toString();
												 if(num != null )
												{
													 if(num.equals("") && optype == 2)
														{
															display();
															
															return;
														}else if(num.equals("") && optype == 1)
														{
															 
															display_1();
															return;
														}else
														{
													 long money = Long.parseLong(num);
														Connection.sendMessage(GameProtocol.
																CONNECTION_SEND_COfC_Fees_Req,
																ConstructData.getCOfC_Fees_Req(optype,money));
														}
													
												}
												 dlg2.dismiss();
											}

										});

								buil.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
										new DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface arg0, int arg1) {
												dlg2.dismiss();
											}

										}).create().show();
							 
								break;
							case 2://商会捐款
								 optype=2;
						    	 View view_2 = factory.inflate(
											R.layout.invite_player_dialog,
											null);

									final EditText edit = (EditText) view_2
											.findViewById(R.id.invite_player_dialog_edit);
									edit.setKeyListener(new DigitsKeyListener());
									TextView tv2 = (TextView)view_2.findViewById(R.id.invite_player_dialog_text);
									tv2.setText(MainActivity.resources.getString(R.string.commmerceuper_input_donate_number));

									Builder build = new AlertDialog.Builder(
											CommerceInfoActivity.this);

									build.setView(view_2);

									build.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {
													String num = edit.getText()
															.toString();
													 if(num != null )
													{
														 if(num.equals("") && optype == 2)
															{
																display();
																
																return;
															}else if(num.equals("") && optype == 1)
															{
																 
																display_1();
																return;
															}else
															{
														 long money = Long.parseLong(num);
															Connection.sendMessage(GameProtocol.
																	CONNECTION_SEND_COfC_Fees_Req,
																	ConstructData.getCOfC_Fees_Req(optype,money));
															}
														
													}
													 dlg2.dismiss();	
												}

											});

									build.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {
													dlg2.dismiss();
												}

											}).create().show();
								
								break;
							case 3://商会竞选
								
								
								
								break;
							}
						}
					});
				 }else if(GameData.player.capacityId==98){
					 for (int i = 0; i < menu2.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();	
								 map.put("name", menu2[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									switch(position){
									case 0://修改公告
										dlg2.dismiss();
										View view1 = factory.inflate(
												R.layout.invite_player_dialog,
												null);

										final EditText et = (EditText) view1
												.findViewById(R.id.invite_player_dialog_edit);
										TextView tv = (TextView)view1.findViewById(R.id.invite_player_dialog_text);
										tv.setText(MainActivity.resources.getString(R.string.commerceinfo_changenotice));

										Builder builder = new AlertDialog.Builder(
												CommerceInfoActivity.this);

										builder.setView(view1);

										builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {
														
														String msgstr = et.getText()
																.toString();
														//notice.setText(msgstr);
														 if(msgstr != null )
														{
															 Connection.sendMessage(GameProtocol.CONNECTION_SEND_COfC_Change_Affiche_Req,ConstructData.getCOfC_Change_Affiche_ReqData(msgstr));
															
														}
														
													}

												});

										builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {

													}

												}).create().show();
										break;
									case 1://设置捐款额度
										
										View view_1 = factory.inflate(
												R.layout.invite_player_dialog,
												null);

										final EditText edt = (EditText) view_1
												.findViewById(R.id.invite_player_dialog_edit);
										edt.setKeyListener(new DigitsKeyListener());
										TextView tv1 = (TextView)view_1.findViewById(R.id.invite_player_dialog_text);
										tv1.setText(MainActivity.resources.getString(R.string.commmerceuper_donate_number));

										Builder buil = new AlertDialog.Builder(
												CommerceInfoActivity.this);

										buil.setView(view_1);

										buil.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {
														String num = edt.getText()
																.toString();
														 if(num != null )
														{
															 if(num.equals("") && optype == 2)
																{
																	display();
																	
																	return;
																}else if(num.equals("") && optype == 1)
																{
																	 
																	display_1();
																	return;
																}else
																{
															 long money = Long.parseLong(num);
																Connection.sendMessage(GameProtocol.
																		CONNECTION_SEND_COfC_Fees_Req,
																		ConstructData.getCOfC_Fees_Req(optype,money));
																}
															
														}
														 dlg2.dismiss();
													}

												});

										buil.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {
														dlg2.dismiss();
													}

												}).create().show();
										
										break;
									case 2://商会捐款
										
										 optype=2;
								    	 View view_2 = factory.inflate(
													R.layout.invite_player_dialog,
													null);

											final EditText edit = (EditText) view_2
													.findViewById(R.id.invite_player_dialog_edit);
											edit.setKeyListener(new DigitsKeyListener());
											TextView tv2 = (TextView)view_2.findViewById(R.id.invite_player_dialog_text);
											tv2.setText(MainActivity.resources.getString(R.string.commmerceuper_input_donate_number));

											Builder build = new AlertDialog.Builder(
													CommerceInfoActivity.this);

											build.setView(view_2);

											build.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface arg0, int arg1) {
															String num = edit.getText()
																	.toString();
															 if(num != null )
															{
																 if(num.equals("") && optype == 2)
																	{
																		display();
																		
																		return;
																	}else if(num.equals("") && optype == 1)
																	{
																		 
																		display_1();
																		return;
																	}else
																	{
																 long money = Long.parseLong(num);
																	Connection.sendMessage(GameProtocol.
																			CONNECTION_SEND_COfC_Fees_Req,
																			ConstructData.getCOfC_Fees_Req(optype,money));
																	}
																
															}
															 dlg2.dismiss();	
														}

													});

											build.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface arg0, int arg1) {
															dlg2.dismiss();
														}

													}).create().show();
										
										break;
									case 3://退出商会
										
										Builder builde = new AlertDialog.Builder(
												CommerceInfoActivity.this);
										
										builde.setMessage(MainActivity.resources.getString(R.string.commmerceuper_exit))
									       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
											 
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												Connection.sendMessage(GameProtocol.
														CONNECTION_SEND_COfC_Change_Duty_Req,
														ConstructData.getCOfC_Change_Duty_Req(GameData.player.id,(byte)-1));//-1退出商会
												GameData.isExitMember = 1;
												
												dialog.dismiss();
												dlg2.dismiss();
											}})
											
									       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_return), new DialogInterface.OnClickListener(){

											 
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												dialog.dismiss();
												dlg2.dismiss();
											}} );
									builde.create().show();

										
										break;
									case 4://商会竞选
										
										break;
									}
								}
							});
					 }else{
						 for (int i = 0; i < menu3.length; i++) {
								//	shop[i] = GameData.corporation.shop[i].name;
									 map = new HashMap<String, String>();	
									 map.put("name", menu3[i]);
									 list.add(map);
								}

								 SimpleAdapter listAdapter = new SimpleAdapter(CommerceInfoActivity.this,list,   
							                R.layout.mapmenu3_item, new String[] {"name"},   
							                new int[] {R.id.mapmenu3_list_text});   
						        lv.setAdapter(listAdapter);
						        lv.setOnItemClickListener(new OnItemClickListener() {

									 
									public void onItemClick(AdapterView<?> parent,
											View view, int position, long id) {
										// TODO Auto-generated method stub
										switch(position){
										case 0://商会捐款
											
											 optype=2;
									    	 View view_2 = factory.inflate(
														R.layout.invite_player_dialog,
														null);

												final EditText edit = (EditText) view_2
														.findViewById(R.id.invite_player_dialog_edit);
												edit.setKeyListener(new DigitsKeyListener());
												TextView tv2 = (TextView)view_2.findViewById(R.id.invite_player_dialog_text);
												tv2.setText(MainActivity.resources.getString(R.string.commmerceuper_input_donate_number));

												Builder build = new AlertDialog.Builder(
														CommerceInfoActivity.this);

												build.setView(view_2);

												build.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
														new DialogInterface.OnClickListener() {

															public void onClick(
																	DialogInterface arg0, int arg1) {
																String num = edit.getText()
																		.toString();
																 if(num != null )
																{
																	 if(num.equals("") && optype == 2)
																		{
																			display();
																			
																			return;
																		}else if(num.equals("") && optype == 1)
																		{
																			 
																			display_1();
																			return;
																		}else
																		{
																	 long money = Long.parseLong(num);
																		Connection.sendMessage(GameProtocol.
																				CONNECTION_SEND_COfC_Fees_Req,
																				ConstructData.getCOfC_Fees_Req(optype,money));
																		}
																	
																}
																 dlg2.dismiss();	
															}

														});

												build.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
														new DialogInterface.OnClickListener() {

															public void onClick(
																	DialogInterface arg0, int arg1) {
																dlg2.dismiss();
															}

														}).create().show();
											
											break;
										case 1://退出商会
											
											Builder builde = new AlertDialog.Builder(
													CommerceInfoActivity.this);
											
											builde.setMessage(MainActivity.resources.getString(R.string.commmerceuper_exit))
										       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
												 
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													Connection.sendMessage(GameProtocol.
															CONNECTION_SEND_COfC_Change_Duty_Req,
															ConstructData.getCOfC_Change_Duty_Req(GameData.player.id,(byte)-1));//-1退出商会
													GameData.isExitMember = 1;
													
													dialog.dismiss();
													dlg2.dismiss();
												}})
												
										       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_return), new DialogInterface.OnClickListener(){

												 
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													dialog.dismiss();
													dlg2.dismiss();
												}} );
										builde.create().show();

											
											break;
										}
									}
								});
				 }
			}});
/*		dairy.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceDiaryActivity.class, null);
				CommerceInfoActivity.this.finish();
			}});*/
/*		staff.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceStaffActivity.class, null);
				CommerceInfoActivity.this.finish();
			}});
		propaganda.setOnClickListener(new Button.OnClickListener(){
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Connection
				.sendMessage(
						GameProtocol.CONNECTION_SEND_ClientDatas_Req,
						ConstructData
								.ClientDatas_Req((byte) 8));	
				CommerceInfoActivity.this.finish();
			}});
		commercelist.setOnClickListener(new Button.OnClickListener(){
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Connection
				.sendMessage(
						GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
						ConstructData
								.getMemberListRequestData(
										"", (short) 0,
										(short) 0,
										(short) 0,
										(short) 0));
				CommerceInfoActivity.this.finish();
			}});*/
		
	//	Button diary = (Button)findViewById(R.id.commerceinfo_button_diary);
		
		 notice = (TextView)findViewById(R.id.notice_text);
//		TextView name = (TextView)findViewById(R.id.info_name);
//		TextView rate = (TextView)findViewById(R.id.info_rate);
		ImageView imv = (ImageView)findViewById(R.id.commerceinfo_logo);
		// Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[1],0,(float)1);
		 Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[CreateCommerceActivity.rolepicId],0,(float)1);
		  imv.setImageBitmap(bitmap);		
		 msg = GameData.member.affiche;
		notice.setText(msg);	
		ListView lv = (ListView)this.findViewById(R.id.commerceuperlistview);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
		String commerceuper_title = MainActivity.resources.getString(R.string.commerceuper_title);
		String[] title = commerceuper_title.split(",");
		String[] detail = {GameData.member.name,String.valueOf(GameData.member.level),GameData.member.createTime,String.valueOf(GameData.member.memberNum),
						   GameData.member.chairManName,String.valueOf(GameData.member.rank),
						   String.valueOf(GameData.member.manageCost),String.valueOf(GameData.member.dues ),
						   String.valueOf(GameData.member.donation),String.valueOf(GameData.member.money),
						   String.valueOf(GameData.member.assets)};
		for(int i=0;i<title.length;i++)
		{
			map = new HashMap<String, String>();
			map.put("title", title[i]);
			map.put("detail", detail[i]);
			list.add(map);
		}
		SimpleAdapter ListAdapter = new SimpleAdapter(this,list,
				                    R.layout.commerceuper_item,new String[]{"title","detail"},
									new int[]{R.id.item_title,R.id.item_detail});
		lv.setAdapter(ListAdapter);
		
//		name.setText(GameData.member.name);
//		rate.setText(String.valueOf(GameData.member.level));
		
		notice.setMovementMethod(ScrollingMovementMethod.getInstance());
		notice.setOnClickListener(new TextView.OnClickListener(){

			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				{ /*WindowManager m = getWindowManager(); 
				  Display d = m.getDefaultDisplay();
				  LayoutParams p = getWindow().getAttributes();
				  p.height = (int) (d.getHeight() * 0.6); 
				  p.width = (int) (d.getWidth() * 0.95);
				  getWindow().setAttributes(p); */
					/*View view = factory.inflate(
							R.layout.invite_player_dialog,
							null);

					final EditText et = (EditText) view
							.findViewById(R.id.invite_player_dialog_edit);
					TextView tv = (TextView)view.findViewById(R.id.invite_player_dialog_text);
					tv.setText(MainActivity.resources.getString(R.string.commerceinfo_changenotice));

					Builder builder = new AlertDialog.Builder(
							CommerceInfoActivity.this);

					builder.setView(view);

					builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
							new DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface arg0, int arg1) {
									
									String msgstr = et.getText()
											.toString();
									//notice.setText(msgstr);
									 if(msgstr != null )
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_COfC_Change_Affiche_Req,ConstructData.getCOfC_Change_Affiche_ReqData(msgstr));
										
									}
									
								}

							});

					builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
							new DialogInterface.OnClickListener() {

								public void onClick(
										DialogInterface arg0, int arg1) {

								}

							}).create().show();*/
				}
				/*else
				{
					display();
				}*/
			
			}});
		
//		returnback.setOnClickListener(new Button.OnClickListener(){
//
//			 
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				CommerceInfoActivity.this.finish();
//			}});
		
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceInfoActivity.this.finish();
			}});
		/*uper.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceUperActivity.class, null);
				CommerceInfoActivity.this.finish();
			}});
		member.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceStaffActivity.class, null);
				CommerceInfoActivity.this.finish();
			}});
		diary.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceDiaryActivity.class, null);
				CommerceInfoActivity.this.finish();
			}});*/
	}
	
	public void update(Bundle  bundle)
	{
		
		notice.setText(bundle.getString("msg"));
	}
	/*public void update(String str)
	{
		notice.setText(str);
	}*/
	public void connection(){
		Connection
		.sendMessage(
				GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
				ConstructData
						.getMemberListRequestData(
								"", (short) 0,
								(short) 0,
								(short) 0,
								(short) 0));

	}
	public void connection1(){
		
		Connection
		.sendMessage(
				GameProtocol.CONNECTION_SEND_ClientDatas_Req,
				ConstructData
						.ClientDatas_Req((byte) 8));	
	}
	public void display(){
		Toast.makeText(this, MainActivity.resources.getString(R.string.commerce_donate), Toast.LENGTH_SHORT).show();
	}
	public void display_1(){
		Toast.makeText(this,MainActivity.resources.getString(R.string.set_donate_number), Toast.LENGTH_SHORT).show();
	}
	public void display_fire(){
		Toast.makeText(this,MainActivity.resources.getString(R.string.fire_member_self), Toast.LENGTH_SHORT).show();
	}
	 
    public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if(intent == null)
		{
			intent = new Intent();
		}
		
		 intent.setClass(CommerceInfoActivity.this, calss);
		 
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

	 
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	 
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	 
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	 
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
