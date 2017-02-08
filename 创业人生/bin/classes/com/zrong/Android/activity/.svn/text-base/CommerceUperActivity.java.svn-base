package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.Music;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CommerceUperActivity extends GameActivity{
	public static CommerceUperActivity mContext = null;
	private LayoutInflater factory = null;
	private ListView lv;
	/**捐款类型*/
	byte optype = 1;
	private String commerceuper_menu = MainActivity.resources.getString(R.string.commmerceuper_menu);			 					
	private String[] menu = commerceuper_menu.split(",");
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.commerceuper);
		factory = LayoutInflater.from(this);
		Button returnback = (Button)findViewById(R.id.commerceuper_returnback);
		Button cancel = (Button)findViewById(R.id.commerceuper_cancel);
		Button info = (Button)findViewById(R.id.commerceuper_button_info);
		Button member = (Button)findViewById(R.id.commerceuper_button_member);
		Button diary = (Button)findViewById(R.id.commerceuper_button_diary);
		Button detail_info = (Button)findViewById(R.id.commerceuper_button_detail);
		
		returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceUperActivity.this.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
		 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceUperActivity.this.finish();
			}});
		info.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceInfoActivity.class, null);
				CommerceUperActivity.this.finish();
			}});
		member.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceStaffActivity.class, null);
				CommerceUperActivity.this.finish();
			}});
		diary.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceDiaryActivity.class, null);
				CommerceUperActivity.this.finish();
			}});
		detail_info.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
//					String commerceuper_menu = MainActivity.resources.getString(R.string.commmerceuper_menu);			 					
//					String[] menu = commerceuper_menu.split(",");
					final  AlertDialog dlg1 = new AlertDialog.Builder(CommerceUperActivity.this).create();
					dlg1.show();
					dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
					ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					for (int i = 0; i < menu.length; i++) {
						//	shop[i] = GameData.corporation.shop[i].name;
							 map = new HashMap<String, String>();						 
							 map.put("name", menu[i]);
							 list.add(map);
						}

						 SimpleAdapter listAdapter = new SimpleAdapter(CommerceUperActivity.this,list,   
					                R.layout.mapmenu3_item, new String[] {"name"},   
					                new int[] {R.id.mapmenu3_list_text});   
				        lv.setAdapter(listAdapter);
				        lv.setOnItemClickListener(new OnItemClickListener() {

							 
							public void onItemClick(AdapterView<?> parent,
									View view, int which, long id) {
								// TODO Auto-generated method stub
								switch(which){
								case 0:
									CommerceUperActivity.mContext .setContentView(R.layout.invite_commerce);
									final EditText et = (EditText)CommerceUperActivity.mContext 
									.findViewById(R.id.invite_player_dialog_edit);
									TextView tv = (TextView)CommerceUperActivity.mContext .findViewById(R.id.invite_player_dialog_text);
									tv.setText(MainActivity.resources.getString(R.string.commmerceuper_inputname));
									Button confirm = (Button)CommerceUperActivity.mContext.findViewById(R.id.chat_button_ok);
									Button cancel = (Button)CommerceUperActivity.mContext.findViewById(R.id.chat_button_cancel);
									Button title = (Button)CommerceUperActivity.mContext.findViewById(R.id.chat_title);
									title.setText(menu[0]);
									cancel.setOnClickListener(new Button.OnClickListener() {
										
										 
										public void onClick(View v) {
											// TODO Auto-generated method stub
											
										}
									});
									confirm.setOnClickListener(new Button.OnClickListener() {
										
										 
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
										}
									});
									
									/*View view3 = factory.inflate(
											R.layout.invite_player_dialog,
											null);

									final EditText et = (EditText) view3
											.findViewById(R.id.invite_player_dialog_edit);
									TextView tv = (TextView)view3.findViewById(R.id.invite_player_dialog_text);
									tv.setText(MainActivity.resources.getString(R.string.commmerceuper_inputname));

									Builder builder = new AlertDialog.Builder(
											CommerceUperActivity.this);

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
													dlg1.dismiss();
												}

											});

									builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {
													dlg1.dismiss();
												}

											}).create().show();
								
								*/
								break;
								
								case 1:
									View view_1 = factory.inflate(
												R.layout.invite_player_dialog,
												null);

										final EditText edt = (EditText) view_1
												.findViewById(R.id.invite_player_dialog_edit);
										edt.setKeyListener(new DigitsKeyListener());
										TextView tv1 = (TextView)view_1.findViewById(R.id.invite_player_dialog_text);
										tv1.setText(MainActivity.resources.getString(R.string.commmerceuper_donate_number));

										Builder buil = new AlertDialog.Builder(
												CommerceUperActivity.this);

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
														 dlg1.dismiss();
													}

												});

										buil.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {
														dlg1.dismiss();
													}

												}).create().show();
									 
									
									break;
								
						
								    case 2:
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
													CommerceUperActivity.this);

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
															 dlg1.dismiss();	
														}

													});

											build.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface arg0, int arg1) {
															dlg1.dismiss();
														}

													}).create().show();
										 
								
									
								break;
								
								case 3:
									Builder builde = new AlertDialog.Builder(
											CommerceUperActivity.this);
									
									builde.setMessage(MainActivity.resources.getString(R.string.commmerceuper_exit))
								       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											Connection.sendMessage(GameProtocol.
													CONNECTION_SEND_COfC_Change_Duty_Req,
													ConstructData.getCOfC_Change_Duty_Req(GameData.player.id,(byte)-1));//-1退出商会
											GameData.isExitMember = 1;
											
											dialog.dismiss();
											dlg1.dismiss();
										}})
										
								       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_return), new DialogInterface.OnClickListener(){

										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											dlg1.dismiss();
										}} );
								builde.create().show();

								
									
								break;
								}
							}});
					
					/*Builder builder = new AlertDialog.Builder(CommerceUperActivity.this);
					builder.setItems(menu,new OnClickListener(){

						 
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						switch(which){
							case 0:

								View view = factory.inflate(
										R.layout.invite_player_dialog,
										null);

								final EditText et = (EditText) view
										.findViewById(R.id.invite_player_dialog_edit);
								TextView tv = (TextView)view.findViewById(R.id.invite_player_dialog_text);
								tv.setText(MainActivity.resources.getString(R.string.commmerceuper_inputname));

								Builder builder = new AlertDialog.Builder(
										CommerceUperActivity.this);

								builder.setView(view);

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
							
							
							break;
							
							case 1:
								 View view_1 = factory.inflate(
											R.layout.invite_player_dialog,
											null);

									final EditText edt = (EditText) view_1
											.findViewById(R.id.invite_player_dialog_edit);
									edt.setKeyListener(new DigitsKeyListener());
									TextView tv1 = (TextView)view_1.findViewById(R.id.invite_player_dialog_text);
									tv1.setText(MainActivity.resources.getString(R.string.commmerceuper_donate_number));

									Builder buil = new AlertDialog.Builder(
											CommerceUperActivity.this);

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
													
												}

											});

									buil.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
											new DialogInterface.OnClickListener() {

												public void onClick(
														DialogInterface arg0, int arg1) {

												}

											}).create().show();
								 
								
								break;
							
					
							    case 2:
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
												CommerceUperActivity.this);

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
														
													}

												});

										build.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
												new DialogInterface.OnClickListener() {

													public void onClick(
															DialogInterface arg0, int arg1) {

													}

												}).create().show();
									 
							
								
							break;
							
							case 3:
								Builder builde = new AlertDialog.Builder(
										CommerceUperActivity.this);
								
								builde.setMessage(MainActivity.resources.getString(R.string.commmerceuper_exit))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										Connection.sendMessage(GameProtocol.
												CONNECTION_SEND_COfC_Change_Duty_Req,
												ConstructData.getCOfC_Change_Duty_Req(GameData.player.id,(byte)-1));//-1退出商会
										GameData.isExitMember = 1;
										
										dialog.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_return), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}} );
							builde.create().show();

							
								
							break;
							
							}
							
						}}).show();*/
					
					
				}
				
			});
		
		ListView lv = (ListView)this.findViewById(R.id.commerceuperlistview);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
		String commerceuper_title = MainActivity.resources.getString(R.string.commerceuper_title);
		String[] title = commerceuper_title.split(",");
		String[] detail = {GameData.member.createTime,String.valueOf(GameData.member.memberNum),
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
		if(intent==null)
		{
			intent = new Intent();
		}
		intent.setClass(CommerceUperActivity.this, calss);

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
